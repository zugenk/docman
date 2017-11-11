package com.app.module.basic;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.bson.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.LoginHistoryService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.shared.ApplicationFactory;
import com.meterware.httpunit.HttpHeader;
import com.simas.db.MongoManager;
import com.simas.webservice.Utility;

public class LoginManager extends BaseUtil{
	private static Logger log = Logger.getLogger(LoginManager.class.getName());
	
	
	public static Document login(String loginName,String passwd) throws Exception{
		init();
		UserService userService = UserService.getInstance();
		
		User loginUser = null;
		loginUser = userService.getBy(" AND user.loginName = '"+loginName+"' AND user.status <> '"+BLOCKED_USER_STATUS.getId()+"' "); 
		if(loginUser==null) throw new Exception("error.login.failed");
//		log.debug(Utility.debug(loginUser));
		
		String encriptedPassword=ApplicationFactory.encrypt(passwd);
	 	//String encriptedPassword=pwd;
	 	if(!encriptedPassword.equals(loginUser.getLoginPassword())){
	 		recordLoginHistory(loginUser,"rejected",null,"Wrong Password");
	 		UserManager.incrementLoginCounter(loginUser);
	 		throw new Exception("error.login.password");
	 	} 
	 	Document iPass=PassportManager.issuePassport(loginUser);
	 	Long lhId=recordLoginHistory(loginUser,"approved",(String) iPass.get("passport"),"Login Success via Rest");
	 	iPass.put("loginHistId",lhId);
	 	//iPass.put("loginUser",loginUser);
	 	return iPass;
	}
	
	
	
	public static Document loginWithBasicAuth(String basicAuth) throws Exception{ // user, String password, HttpHeaders headers) {
    	String plain=new String(Base64.getDecoder().decode(basicAuth.substring(6)));
		//String plain=new String(org.springframework.security.crypto.codec.Base64.decode(basicAuth.substring(6).getBytes()));
    	int idx=plain.indexOf(':');
    	String loginName=plain.substring(0,idx);
    	String passwd=plain.substring(idx+1);
    	//log.debug("["+loginName+"] -> ["+passwd+"]");
    	return login(loginName, passwd); // null; // 
 	}
	    
	public static String makeBasicAuth(String user, String password) {
    	String plainCreds = user+":"+password;//"okmAdmin:admin";
    	byte[] plainCredsBytes = plainCreds.getBytes();
    	byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
    	String base64Creds = new String(base64CredsBytes);
    	return "Basic " + base64Creds;
	}
	
	
	
	public static Long recordLoginHistory(User user,String statusCode,String sessionId,String description) {
		LoginHistory hist=new LoginHistory();
		try {
			hist.setLoginTime(new Date());
//			hist.setLogoutTime(logoutTime);
//			hist.setSessionId(sessionId);
			hist.setLastAccess(new Date());
			hist.setDescription(description);
			hist.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory", statusCode));
			hist.setUser(user);
			LoginHistoryService.getInstance().update(hist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hist.getId();
	}
	
	private static Long updateLoginHistory(Long lhId,String statusCode,String sessionId,String description) {
		LoginHistory hist=null; //new LoginHistory();
		try {
			hist=LoginHistoryService.getInstance().get(lhId);
//			hist.setLoginTime(new Date());
//			hist.setLogoutTime(logoutTime);
			if(sessionId!=null) hist.setSessionId(sessionId);
			hist.setLastAccess(new Date());
			if(description!=null) hist.setDescription(description);
//			hist.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory", statusCode));
//			hist.setUser(user);
			LoginHistoryService.getInstance().update(hist);
		} catch (Exception e) {
		}
		return hist.getId();
	}
	private static Long logoutLoginHistory(Long lhId) {
		LoginHistory hist=null; //new LoginHistory();
		try {
			hist=LoginHistoryService.getInstance().get(lhId);
			hist.setLogoutTime(new Date());
			hist.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory", "logout"));
			LoginHistoryService.getInstance().update(hist);
		} catch (Exception e) {
		}
		return hist.getId();
	}
	
	
	public static Document authHeader(HttpHeaders httpHeaders) throws Exception{
		return authenticate(httpHeaders.getFirst("ipassport"), httpHeaders.getFirst("Authorization"));
	}
	
	public static Document authenticate(String ipassport, String basicAuth) throws Exception{
		log.debug("Authenticate ["+ipassport+":"+basicAuth+"]");
		Document iPass=null;
		if(ipassport !=null && ipassport.length()>0) {
			try {
				iPass=PassportManager.checkPassport(ipassport);
			} catch (Exception e) {
			}
		} 
		
		if (iPass==null && basicAuth!=null && basicAuth.length()>0) {
			try {
				iPass=LoginManager.loginWithBasicAuth(basicAuth);
			} catch (Exception e) {
			}
		}
		if (iPass==null) throw new Exception("error.session.invalid");
		return iPass;
	}

	public static ResponseEntity<Map> preFilter(Map map,String ipassport, String basicAuth, String uri) {
		try {
			log.debug("["+ipassport+"] - payload : "+map);
			Document iPass=authenticate(ipassport,basicAuth); 
			if(iPass==null)	{
				log.debug("["+ipassport+"] - Login Failed");
				map.put("errorMessage", "error.authentication.failed");
				return new ResponseEntity<Map>(map,HttpStatus.UNAUTHORIZED);  
			} else {
				log.debug("["+ipassport+"] - Login Success");
				return new ResponseEntity<Map>((Map)iPass,HttpStatus.OK);  
			}
		} catch (Exception e) {
			log.debug("["+ipassport+"] - Login Failed");
			map.put("errorMessage", "error.authentication.failed");
			return new ResponseEntity<Map>(map,HttpStatus.BAD_GATEWAY);  
		}
	}
	
	public static void main(String[] args) {
		LoginManager lm=new LoginManager();
		String bauth=lm.makeBasicAuth("admin","admin");
		try {
			lm.loginWithBasicAuth(bauth);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

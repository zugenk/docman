package com.app.module.basic;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

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
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class LoginManager extends BaseUtil{
	private static Logger log = Logger.getLogger(LoginManager.class.getName());
	
	
	public static Document loginWithToken(String itoken) throws Exception{
		init();
		String apiToken=null;
		String nip=null;
		try{
			apiToken=new String(Base64.getDecoder().decode(itoken));
			nip=apiToken.substring(apiToken.lastIndexOf(":")+1);
			apiToken=apiToken.substring(0, apiToken.indexOf("::"));
			System.out.println(apiToken+"::"+nip);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error.invalid.token");
		}
		if (!APIKEY_MAP.containsKey(apiToken)) throw new Exception("error.invalid.token");
		log.debug("Login from system ["+APIKEY_MAP.get(apiToken)+"]");
		User loginUser = UserService.getInstance().getAllBy(" AND user.loginName = '"+nip+"' "); 
		if(loginUser==null) throw new Exception("error.login.notFound");
		if(!"active".equals(loginUser.getStatus().getState())) throw new Exception("error.login.userNotActive");
		UserManager.resetLoginCounter(loginUser);
	 	Document iPass=PassportManager.issuePassport(loginUser,itoken);
	 	if(iPass.get("_id")==null){
	 	 	Long lhId=recordLoginHistory(loginUser,"approved",(String) iPass.get("passport"),"Login Success via Rest");
		 	PassportManager.savePassport(iPass,new Document("loginHistId", lhId));
	 	}
	 	return iPass;
	}
	
	public static Document login(String loginName,String passwd) throws Exception{
		init();
		User loginUser = UserService.getInstance().getAllBy(" AND user.loginName = '"+loginName+"' "); 
		if(loginUser==null) throw new Exception("error.login.notFound");
		if(!"active".equals(loginUser.getStatus().getState())) throw new Exception("error.login.userNotActive");
		String encriptedPassword=ApplicationFactory.encrypt(passwd);
	 	if(!encriptedPassword.equals(loginUser.getLoginPassword())){
	 		recordLoginHistory(loginUser,"rejected",null,"Wrong Password");
	 		UserManager.incrementLoginCounter(loginUser);
	 		throw new Exception("error.login.password");
	 	} 
	 	UserManager.resetLoginCounter(loginUser);
	 	Document iPass=PassportManager.issuePassport(loginUser,null);
	 	if(iPass.get("_id")==null){
	 	 	Long lhId=recordLoginHistory(loginUser,"approved",(String) iPass.get("passport"),"Login Success via Rest");
		 	PassportManager.savePassport(iPass,new Document("loginHistId", lhId));
	 	}
	 	return iPass;
	}
	
	
	public static Document loginWithBasicAuth(String basicAuth) throws Exception{ // user, String password, HttpHeaders headers) {
    	String plain=new String(Base64.getDecoder().decode(basicAuth.substring(6)));
		int idx=plain.indexOf(':');
    	String loginName=plain.substring(0,idx);
    	String passwd=plain.substring(idx+1);
    	return login(loginName, passwd); // null; // 
 	}
	
	public static String encryptPassword(String basicAuth) throws Exception{ // user, String password, HttpHeaders headers) {
    	String plain=new String(Base64.getDecoder().decode(basicAuth.substring(6)));
		//String plain=new String(org.springframework.security.crypto.codec.Base64.decode(basicAuth.substring(6).getBytes()));
    	int idx=plain.indexOf(':');
    	//String loginName=plain.substring(0,idx);
    	String passwd=plain.substring(idx+1);
    	return ApplicationFactory.encrypt(passwd);
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
		return authenticate(httpHeaders.getFirst("itoken"),httpHeaders.getFirst("ipassport"), httpHeaders.getFirst("Authorization"));
	}
	
	public static Document authenticate(String itoken,String ipassport, String basicAuth) throws Exception{
		log.debug("Authenticate ["+itoken+":"+ipassport+":"+basicAuth+"]");
		Document iPass=null;
		if(!nvl(itoken)){
			iPass=PassportManager.checkPassportByToken(itoken);
			if(iPass==null) iPass=loginWithToken(itoken);
		}
		if(iPass==null && !nvl(ipassport)){ 
			iPass=iPass=PassportManager.checkPassport(ipassport);
		} 
		
		if (iPass==null && !nvl(basicAuth)){ 
			iPass=LoginManager.loginWithBasicAuth(basicAuth);
		}
		if (iPass==null) throw new Exception("error.session.invalid");
		return iPass;
	}
	
	public static void logout(String ipassport) throws Exception{
		log.debug("Logout ["+ipassport+"]");
		Document iPass=null;
		if(!nvl(ipassport)) PassportManager.logoutPassport(ipassport);
	}

	public static ResponseEntity<Map> preFilter(Map map,String itoken,String ipassport, String basicAuth, String uri) {
		try {
			log.debug("["+ipassport+"] - payload : "+map);
			Document iPass=authenticate(itoken,ipassport,basicAuth); 
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
//		LoginManager lm=new LoginManager();
//		String bauth=lm.makeBasicAuth("admin","admin");
		try {
//			lm.loginWithBasicAuth(bauth);
			
			String itoken= "r02u7JZu2p7uGMdQKCycCrsM6pANO34E::"+System.currentTimeMillis()+":"+"198611102014031002";//+"admin";
			System.out.println("itoken=["+itoken+"]");
			String enc=Base64.getEncoder().encodeToString(itoken.getBytes());
			//String enc="cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTI5NzA3MjkzMzQ6bWFydGlu"; martin
			//String enc="cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTI5NzA5MjE5MzQ6YWRtaW4="; admin
			//String enc="cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTI5MzIzNzQ3OTY6MTk4NjExMTAyMDE0MDMxMDAy";
			//String enc="cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTM1MTgxNjc1NTI6MTk2NDAyMDUxOTg5MDMxMDkz";
			System.out.println("enc=["+enc+"]");
			System.out.println("dec=["+new String(Base64.getDecoder().decode(enc.getBytes()))+"]");
		
			//198611102014031002 = cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTM1MDM3NDUzODI6MTk4NjExMTAyMDE0MDMxMDAy
			//admin = cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTM1MDM2MjUzMTY6YWRtaW4=
			//196402051989031093 = cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTM1MDM3MTIxMDk6MTk2NDAyMDUxOTg5MDMxMDkz
			//196402051989031001 = cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTM1MjQ2MDQ3ODc6MTk4NjExMTAyMDE0MDMxMDAy
			//198611102014031002 = cjAydTdKWnUycDd1R01kUUtDeWNDcnNNNnBBTk8zNEU6OjE1MTM1NTgwMDUwODM6MTk4NjExMTAyMDE0MDMxMDAy
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

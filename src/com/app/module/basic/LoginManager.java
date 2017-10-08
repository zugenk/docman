package com.app.module.basic;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionError;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.LoginHistoryService;
import com.app.docmgr.service.UserService;
import com.app.shared.ApplicationFactory;

public class LoginManager {
	public static User login(String loginName,String passwd) throws Exception{
		// TODO Auto-generated method stub
		UserService userService = UserService.getInstance();
		User loginUser = null;
		 //List userList = userService.getList(" AND lower(user.loginName) = lower('"+id+"') ", null); //AND appUser.loginPassword='"+pwd+"' 
		 List userList = userService.getList(" AND user.loginName = '"+loginName+"' ", null); 
		 if(userList.size()<=0) throw new Exception("error.login.failed");
	 	loginUser = (User) userList.iterator().next();
	 	String encriptedPassword=ApplicationFactory.encrypt(passwd);
	 	//String encriptedPassword=pwd;
	 	if(!encriptedPassword.equals(loginUser.getLoginPassword())){
	 		throw new Exception("error.login.password");
	 	} 
	 	//recordLoginHistory();
	 	return loginUser;
	}
	
	public static User loginWithBasicAuth(String basicAuth) throws Exception{ // user, String password, HttpHeaders headers) {
    	String plain=new String(Base64.getDecoder().decode(basicAuth.substring(6)));
    	int idx=plain.indexOf(':');
    	String loginName=plain.substring(0,idx);
    	String passwd=plain.substring(idx+1);
    	System.out.println("["+loginName+"] -> ["+passwd+"]");
    	return login(loginName, passwd); // null; // 
 	}
	    
	public static String makeBasicAuth(String user, String password) {
    	String plainCreds = user+":"+password;//"okmAdmin:admin";
    	byte[] plainCredsBytes = plainCreds.getBytes();
    	byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
    	String base64Creds = new String(base64CredsBytes);
    	return "Basic " + base64Creds;
	}
	
	
	
	private static LoginHistory recordLoginHistory(User user,Status status,String sessionId,String description) {
		LoginHistory hist=new LoginHistory();
		try {
			hist.setLoginTime(new Date());
//			hist.setLogoutTime(logoutTime);
//			hist.setSessionId(sessionId);
			hist.getLastAccess();
			hist.setDescription(description);
			hist.setStatus(status);
			hist.setUser(user);
			LoginHistoryService.getInstance().add(hist);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return hist;
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

package com.app.module.basic;

import java.util.List;

import org.apache.struts.action.ActionError;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.User;
import com.app.docmgr.service.UserService;
import com.app.shared.ApplicationFactory;

public class LoginManager {
	public User login(String loginName,String passwd) throws Exception{
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
	 	recordLoginHistory();
	 	return loginUser;
	}
	
	public void checkPassport() {
		// TODO Auto-generated method stub

	}
	
	private void recordLoginHistory() {
		// TODO Auto-generated method stub
		LoginHistory hist=new LoginHistory();
		hist.setLoginTime(loginTime);
		hist.setLogoutTime(logoutTime);
		hist.setSessionId(sessionId);
		hist.getLastAccess();
		hist.setDescription(description);
		hist.setStatus(status);
		hist.setUser(user);
		
	}
}

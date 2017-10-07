package com.app.module.basic;

import java.util.Date;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.User;

public class PassportManager {
	private void issuePassport(User usr,LoginHistory lh) {
		// TODO Passport should contain user, loginHistory, ipassportId, etc
		lh.setLastAccess(new Date());
		
	}
	
	public void checkPassport(String ipassport) {
		// TODO Auto-generated method stub
		
//		lh.setLastAccess(new Date());
		
	}
	
	private void generateNewPassport() {
		// TODO Auto-generated method stub

	}

}

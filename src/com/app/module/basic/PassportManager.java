package com.app.module.basic;

import java.util.Date;

import org.bson.Document;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.User;
import com.simas.db.MongoManager;


public class PassportManager {
static String IPASSPORT_COLLECTION="IPassportData";
	
	private static String issuePassport(User usr, LoginHistory lh) {
		// TODO Passport should contain user, loginHistory, ipassportId, etc
		lh.setLastAccess(new Date());
		String ipass="";
		
		return ipass;
	}
	
	
	public static Document checkPassport(String ipassport) throws Exception{
		Document searchQ=new Document("ipassport", ipassport);
		return MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
	}
	
	private void generateNewPassport() {
		// TODO Auto-generated method stub

	}

}

package com.app.module.basic;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.User;
import com.simas.db.MongoManager;


public class PassportManager {
static String IPASSPORT_COLLECTION="IPassportData";
	
	public static Document issuePassport(User user) {
		Document iPass=null;
		try {
			iPass=checkLastPassport(user.getId());
		} catch (Exception e) {
		}
		if (iPass==null) {
			iPass=new Document();// HashMap<String, Object>();
			iPass.put("ipassport", genPassId());
			iPass.put("userId", user.getId());
			//iPass.put("loginHistId", lhId);
			iPass.put("fullName", user.getFullName());
			iPass.put("mobileNumber", user.getMobileNumber());
			iPass.put("phoneNumber", user.getHomePhoneNumber());
			iPass.put("userLevel", user.getUserLevel().getCode());
			iPass.put("securityLevel", user.getSecurityLevel().getCode());
			
		}
	//	iPass.put("favTopicIds", user.getFavorites()); //TODO : on UserService create Favorite List of TopicIds
		return iPass;
	}
	
	private static String genPassId() {
		// TODO Auto-generated method stub
		return "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";

	}
	
	public static Document checkLastPassport(Long userId) throws Exception{
		Document searchQ=new Document("userId", userId).append("lastAccess", System.currentTimeMillis());
//		return MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		Document iPass= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		if (iPass!=null) {
			iPass.put("lastAccess", System.currentTimeMillis());
		}
		return iPass;
	}
	
	public static Document checkPassport(String ipassport) throws Exception{
		Document searchQ=new Document("ipassport", ipassport).append("lastAccess", System.currentTimeMillis());
		Document iPass= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		if (iPass!=null) {
			iPass.put("lastAccess", System.currentTimeMillis());
		}
		return iPass;
	}
	
	
}

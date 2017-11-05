package com.app.module.basic;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.User;
import com.app.docmgr.service.UserService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.IndexOptions;
import com.simas.db.MongoManager;
import com.simas.webservice.Utility;

public class PassportManager {
static String IPASSPORT_COLLECTION="IPassportData";
//static String DB_CFG="DEFAULT|mongo-docman|27017|DOCMAN";
static String DB_CFG="DEFAULT|localhost|27017|DOCMAN";
static boolean inited=false;

static int TIMEOUT_PERIOD=600000; //10 Mins
//static int TIMEOUT_PERIOD=1800000; //30 Mins

private static Logger log = Logger.getLogger(PassportManager.class.getName());
	
	public static Document issuePassport(User user) {
		Document iPass=null;
		try {
			iPass=checkLastPassport(user.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (iPass==null) {
			iPass=new Document();// HashMap<String, Object>();
			iPass.put("ipassport", genPassId());
			iPass.put("userId", user.getId());
			iPass.put("loginName", user.getLoginName());
			iPass.put("fullName", user.getFullName());
			iPass.put("mobileNumber", user.getMobileNumber());
			iPass.put("phoneNumber", user.getHomePhoneNumber());
			iPass.put("userLevel", user.getUserLevel().getCode());
			if(user.getSecurityLevel()!=null) {
				iPass.put("securityLevel", user.getSecurityLevel().getName());
				iPass.put("securityLevelId", user.getSecurityLevel().getId());
			}
			iPass.put("lastAccess", System.currentTimeMillis());
			log.debug("PASSPORT ISSUED ="+iPass.getString("ipassport"));
		}
		iPass.put("favTopicIds", UserService.getFavTopicIds(user));
		iPass.put("roleNames", UserService.getRoleNames(user)); 
		savePassport(iPass);
		return iPass;
	}
	
	private static String genPassId() {
		// TODO Auto-generated method stub
		return "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";

	}
	
	public static void savePassport(Document iPass) {
		init();
//		MongoManager.insertOne(IPASSPORT_COLLECTION, iPass);
		Document query=new Document("userId",iPass.get("userId"));
		MongoManager.findOneAndUpdate(IPASSPORT_COLLECTION, query, iPass, true);
	}
	
	private static void init() {
		if(inited) return;
		MongoManager.init(DB_CFG);
	    MongoManager.getCollection(IPASSPORT_COLLECTION).createIndex(new Document("userId", 1),new IndexOptions().unique(true).name("UniqueUserId"));
	    MongoManager.getCollection(IPASSPORT_COLLECTION).createIndex(new Document("ipassport", 1),new IndexOptions().unique(true).name("UniqueIPassport"));
	    inited=true;
	}
	
	public static Document checkLastPassport(Long userId) throws Exception{
		init();
		Document searchQ=new Document("userId", userId); //.append("lastAccess", new Document("$gte", (System.currentTimeMillis()-TIMEOUT_PERIOD)));
//		return MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		Document iPass= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		if (iPass!=null) {
			iPass.put("lastAccess", System.currentTimeMillis());
			savePassport(iPass);
			log.debug("PASSPORT CHECKED OK ="+iPass.getString("ipassport"));
		}
		return iPass;
	}
	
	public static Document checkPassport(String ipassport) throws Exception{
		init();
		
		Document searchQ=new Document("ipassport", ipassport);//.append("lastAccess", new Document("$gte", (System.currentTimeMillis()-TIMEOUT_PERIOD)));
		Document iPass= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		if (iPass!=null) {
			iPass.put("lastAccess", System.currentTimeMillis());
			savePassport(iPass);
			log.debug("PASSPORT CHECKED OK ="+iPass.getString("ipassport"));
		} else  log.debug("PASSPORT INVALID "+ipassport);
		return iPass;
	}
	
	public static void main(String[] args) {
		try {
			MongoManager.init(DB_CFG);
//			User user=UserService.getInstance().get(new Long(1));
//			issuePassport(user);
			FindIterable<Document> result=MongoManager.getCollection(IPASSPORT_COLLECTION).find();
			System.out.println(Utility.debug(result.first()));
			System.out.println("==========================================================================================");
			System.out.println(Utility.debug(checkPassport("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef")));
			
			System.out.println("==========================================================================================");
			
			
			//MongoManager.getCollection("Testing").insertOne(new Document("Nama","Martin").append("Role","Manager"));
			//System.out.println(Utility.debug(MongoManager.find("Testing", new Document()).first()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

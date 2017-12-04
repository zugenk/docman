package com.app.module.basic;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.User;
import com.app.docmgr.service.ForumService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.UserService;
import com.app.docmgr.service.base.UserServiceBase;
import com.app.module.forum.ForumManager;
import com.app.shared.PartialList;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexOptions;
import com.simas.db.MongoManager;
import com.simas.webservice.Utility;

import bsh.util.Util;

public class PassportManager extends BaseUtil{

private static Logger log = Logger.getLogger(PassportManager.class.getName());
	
	public static Document issuePassport(User user) throws Exception{
		Document iPass=null;
		iPass=checkLastPassport(user.getId());
		if (iPass==null) {
			iPass=new Document();
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
			if(user.getOrganization()!=null){
				iPass.put("organization", user.getOrganization().getName());
				iPass.put("organizationId", user.getOrganization().getId());
				if(iPass.get("securityLevel")==null){
					Organization org=OrganizationService.getInstance().get(iPass.getLong("organizationId"));
					if(org.getSecurityLevel()!=null) {
						iPass.put("securityLevel", org.getSecurityLevel().getName());
						iPass.put("securityLevelId", org.getSecurityLevel().getId());
					}
				}
			}
			createNewPassport(iPass);
			System.out.println("====================NEW PASSPORT ISSUED======================");
		}
//		iPass.put("favTopicIds", UserManager.getFavTopicIds(user));
//		iPass.put("roleNames", UserManager.getRoleNames(user)); 
//		System.out.println(Utility.debug(iPass));
		iPass.put("lastAccess", System.currentTimeMillis());
		savePassport(iPass,null);
		return iPass;
	}
	
	//private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	//private static final int PASSPORT_LENGTH=32;
	
	private static String genPassId() {
		// TODO Auto-generated method stub
		//return "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
		/*
		int count=PASSPORT_LENGTH;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		*/
		return RandomStringUtils.randomAlphanumeric(PASSPORT_LENGTH);

	}
	
	public static void createNewPassport(Document iPass) {
		init();
		String  ipassport=null;
		Document result=null; 
		Document searchQ=new Document("ipassport", ipassport).append("lastAccess", new Document("$gte", (System.currentTimeMillis()-SESSION_TIMEOUT_PERIOD)));
		do {
			try {
				ipassport=genPassId();
				searchQ.put("ipassport", ipassport);
				result= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
			} catch (Exception e) {
				log.error("Error creating/clearing New Passport",e);
			}
		}while(result!=null && ipassport!=null);
		//its safe to clear the passport now..
		MongoManager.getCollection(IPASSPORT_COLLECTION).deleteMany(new Document("ipassport", ipassport));	
		iPass.put("ipassport", ipassport);
		log.debug("PASSPORT ISSUED ="+iPass.getString("ipassport"));
	}
	
	public static void savePassport(Document iPass,Document updatePart) {
		init();
//		System.out.println("Saving Passport ");
		Document query=new Document("userId",iPass.get("userId"));
		Document updateDoc=new Document();
		if (updatePart!=null){
			updateDoc.append("$set", updatePart);
		} else {
			updateDoc.append("$set", iPass); //.remove("_id")
		}
//		FindOneAndReplaceOptions opt=new FindOneAndReplaceOptions();
//		opt.upsert(true);
//		Document result=MongoManager.getCollection(IPASSPORT_COLLECTION).findOneAndReplace(query, iPass,opt); 
		FindOneAndUpdateOptions opt=new FindOneAndUpdateOptions();
		opt.upsert(true);
		//opt.returnDocument(true);
		Document result=MongoManager.getCollection(IPASSPORT_COLLECTION).findOneAndUpdate(query, updateDoc,opt); 
	//	System.out.println("=====>>>> Passport Saving result="+Utility.debug(result));
//		if(result==null) System.out.println(" Result is null");
//		else System.out.println("Not Null:"+Utility.debug(result));
		//MongoManager.findOneAndUpdate(IPASSPORT_COLLECTION, query, iPass, true);
		log.debug("Passport Saved/Updated..");
	}
	
	public static Document checkLastPassport(Long userId) throws Exception{
		init();
		Document searchQ=new Document("userId", userId).append("lastAccess", new Document("$gte", (System.currentTimeMillis()-SESSION_TIMEOUT_PERIOD)));
//		return MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		Document iPass= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		if (iPass!=null) {
			//savePassport(iPass,new Document("lastAccess", System.currentTimeMillis()));
			log.debug("PASSPORT CHECKED OK ="+iPass.getString("ipassport"));
		}
		return iPass;
	}
	
	public static Document checkPassport(String ipassport) throws Exception{
		init();
		Document searchQ=new Document("ipassport", ipassport).append("lastAccess", new Document("$gte", (System.currentTimeMillis()-SESSION_TIMEOUT_PERIOD)));
		Document iPass= MongoManager.getCollection(IPASSPORT_COLLECTION).find(searchQ).first();
		if (iPass!=null) {
			savePassport(iPass,new Document("lastAccess", System.currentTimeMillis()));
			log.debug("PASSPORT CHECKED OK ="+iPass.getString("ipassport"));
		} else  log.debug("PASSPORT INVALID "+ipassport);
		return iPass;
	}
	
	public static void logoutPassport(String ipassport) throws Exception{
		init();
		Document searchQ=new Document("ipassport", ipassport);		
		MongoManager.deleteOne(IPASSPORT_COLLECTION, searchQ);
		log.debug("PASSPORT LOGGED OUT "+ipassport);
	}
	
	public static void main(String[] args) {
		try {
			/*
			MongoManager.init(DB_CFG);
			User user=UserService.getInstance().get(new Long(1));
			Document iPass=issuePassport(user);
			String ipassport= iPass.getString("ipassport");
			FindIterable<Document> result=MongoManager.getCollection(IPASSPORT_COLLECTION).find();
			System.out.println(Utility.debug(result.first()));
			System.out.println("==========================================================================================");
			System.out.println(Utility.debug(checkPassport(ipassport)));
			
			System.out.println("==========================================================================================");
			*/
			
			PartialList result=ForumService.getInstance().getPartialList(null,null, 0,20);
			System.out.println("lewat kok");
			ForumManager.toDocList(result);
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Document user = (Document) iterator.next();
				System.out.println(Utility.debug(user));
			}
			System.out.println("showing "+result.getStart()+" to "+(result.getStart()+result.getCount())+" of "+result.getTotal());
			//System.out.println(Utility.debug(lsUsr));
			//MongoManager.getCollection("Testing").insertOne(new Document("Nama","Martin").append("Role","Manager"));
			//System.out.println(Utility.debug(MongoManager.find("Testing", new Document()).first()));
		} catch (Exception e) {
			System.out.println("Exception nih");
			e.printStackTrace();
		}
	}
	
}

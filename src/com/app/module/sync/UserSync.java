package com.app.module.sync;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.User;
import com.app.docmgr.service.UserService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.OrganizationManager;


public class UserSync extends BaseUtil{
	private static Long lastUpdateDate=null;
	private static Logger log = Logger.getLogger(OrganizationManager.class);
	

	private List<Document> getUserDeltaData() throws Exception {
		return LegacyDbConn.getList("Delta User", "sqlQuery");
	}
	
	private void syncUp() {
		try {
			UserService usrSvc=UserService.getInstance();
			List<Document> userList=getUserDeltaData();
			if (userList==null || userList.isEmpty()) {
				log.debug("No Delta Data for User !");
				return;
			}
			
			for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
				Document usrDoc = (Document) iterator.next();
				User user=usrSvc.getBy(" AND user.employeeNumber='"+usrDoc.get("employeeNumber")+"' ");  //" AND lastUpdate > '"++"' "); 
				if (user==null)  user= new User();
				updateFromDoc(user, usrDoc);
				if(user.getId()==null) usrSvc.add(user);
				else {
					usrSvc.update(user);
					log.debug("Updating user with employeeNumber=["+usrDoc.get("employeeNumber")+"]");
				}
			}
			log.debug("Done sync User.. ");
		} catch (Exception e) {
			log.error("Error during user Sync... ",e);
		}

	}
	
	private void updateFromDoc(User user, Document usrDoc) {
		if (user.getId()==null) {
			user.setLoginName(usrDoc.getString("name"));
			user.setCreatedBy("system");
			user.setCreatedDate(new Date());
			user.setEmployeeNumber(usrDoc.getString("employeeNumber"));
		}
		user.setName(usrDoc.getString("name"));
		user.setEmail(usrDoc.getString("email"));
		//user.setOrganization(organization);
//		user.setUserLevel(userLevel);
		user.setLastUpdatedBy("system");
		user.setLastUpdatedDate(new Date());
	}
}

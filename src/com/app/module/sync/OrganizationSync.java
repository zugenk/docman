package com.app.module.sync;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Organization;
import com.app.docmgr.service.OrganizationService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.OrganizationManager;


public class OrganizationSync extends BaseUtil{
		private static long lastUpdateDate;
		private static Logger log = Logger.getLogger(OrganizationManager.class);
		

		private List<Document> getorgDeltaData() throws Exception {
			return LegacyDbConn.getList("Delta org", "sqlQuery");
		}
		
		private void syncUp() {
			try {
				OrganizationService orgSvc=OrganizationService.getInstance();
				List<Document> orgList=getorgDeltaData();
				if (orgList==null || orgList.isEmpty()) {
					log.debug("No Delta Data for org !");
					return;
				}
				
				for (Iterator iterator = orgList.iterator(); iterator.hasNext();) {
					Document orgDoc = (Document) iterator.next();
					Organization org=orgSvc.getBy(" AND organization.employeeNumber='"+orgDoc.get("employeeNumber")+"'  "); //" AND lastUpdate > '"++"' "); 
					if (org==null)  org= new Organization();
					updateFromDoc(org, orgDoc);
					if(org.getId()==null) orgSvc.add(org);
					else {
						orgSvc.update(org);
						log.debug("Updating org with employeeNumber=["+orgDoc.get("employeeNumber")+"]");
					}
				}
				log.debug("Done sync org.. ");
			} catch (Exception e) {
				log.error("Error during org Sync... ",e);
			}

		}
		
		private void updateFromDoc(Organization org, Document orgDoc) {
			if (org.getId()==null) {
				org.setName(orgDoc.getString("name"));
				org.setCreatedBy("system");
				org.setCreatedDate(new Date());
			}
			org.setName(orgDoc.getString("name"));
			org.setMailingList(orgDoc.getString("mailingList"));
	//		org.setOrganizationType(organizationType);
	//		org.set
		//org.setOrganization(organization);
//			org.setorgLevel(orgLevel);
			org.setLastUpdatedBy("system");
			org.setLastUpdatedDate(new Date());
		}
	}


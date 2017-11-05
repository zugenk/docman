package com.app.module.basic;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class UserManager {
	private static Logger log = Logger.getLogger(UserManager.class);
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create User :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		User obj= new User();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("User", "new"));
		if(!errors.isEmpty()) throw new Exception(BaseUtil.listToString(errors));
		UserService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create User :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		User obj= UserService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("User", "new"));
		if(!errors.isEmpty()) throw new Exception(BaseUtil.listToString(errors));
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		User obj=UserService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("User", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		UserService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		User obj=UserService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		return toDocument(obj);
	}
	
	public static PartialList list(Document passport,Map data) throws Exception{
		String filterParam=null;
		String orderParam=null;
		int start=0;
		if(data!=null && !data.isEmpty()) {
			try {
				start= Integer.parseInt((String) data.get("start"));
			} catch (Exception e) {
				start=0;
			}
			
			Map filterMap= (Map) data.get("filter");
			if (filterMap!=null && !filterMap.isEmpty()) {
				StringBuffer filterBuff=new StringBuffer("");
				for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					filterBuff.append(" AND user."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					orderParam+=(orderParam!=null?", ":"")+" user."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		PartialList result=UserService.getInstance().getPartialList(filterParam.toString(), orderParam, start, BaseUtil.itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(User obj, Map data,List<String> errors) {
		obj.setEmail((String) data.get("email"));
		obj.setEmployeeNumber((String) data.get("employeeNumber"));
		//obj.setFavoriteTopics(favoriteTopics);
		obj.setFullName((String) data.get("fullName"));
		obj.setHomePhoneNumber((String) data.get("homePhoneNumber"));
		obj.setLoginName((String) data.get("loginName"));
		obj.setMobileNumber((String) data.get("mobileNumber"));
		obj.setMobilePhoneNumber((String) data.get("mobilePhoneNumber"));
		obj.setName((String) data.get("name"));
		obj.setTitle((String) data.get("title"));

		obj.setAlias((String) data.get("alias"));
		obj.setLoginPassword(ApplicationFactory.encrypt((String) data.get("loginPassword")));
		try {
			long orgId=(Long)data.get("organizationId");
			Organization org= OrganizationService.getInstance().get(orgId);
			if(org!=null)obj.setOrganization(org);
		} catch (Exception e) {
			errors.add("error.invalid.organization");
		}
		try {
			//Lookup position= LookupService.getInstance().getByTypeandCode("position", (String)data.get("position"));
			long posId=Long.parseLong((String)data.get("positionId"));
			Lookup position= LookupService.getInstance().get(posId);
			if(position!=null) obj.setPosition(position);
		} catch (Exception e) {
			errors.add("error.invalid.position");
		}
		try {
			//Lookup userLevel= LookupService.getInstance().getByTypeandCode("userLevel", (String)data.get("userLevel"));
			long levelId=Long.parseLong((String)data.get("userLevelId"));
			Lookup userLevel= LookupService.getInstance().get(levelId);
			if(userLevel!=null) obj.setUserLevel(userLevel);
		} catch (Exception e) {
			errors.add("error.invalid.userLevel");
		}
		try {
			//Lookup securityLevel= LookupService.getInstance().getByTypeandCode("securityLevel", (String)data.get("securityLevel"));
			long secId=Long.parseLong((String)data.get("securityLevelId"));
			Lookup securityLevel= LookupService.getInstance().get(secId);
			if(securityLevel!=null) obj.setSecurityLevel(securityLevel);
		} catch (Exception e) {
			errors.add("error.invalid.securityLevel");
		}
		try {
			//Lookup securityLevel= LookupService.getInstance().getByTypeandCode("securityLevel", (String)data.get("securityLevel"));
			long statusId=Long.parseLong((String)data.get("statusId"));
			Status status= StatusService.getInstance().get(statusId);
			if(status!=null) obj.setStatus(status);
		} catch (Exception e) {
			errors.add("error.invalid.status");
		}
//		RoleService.getInstance().getBy("role.name='ADMINISTRATOR'");
//		obj.setRoles(roles);

//		obj.setPicture(picture);
//		obj.setFirstLogin("true");
//		obj.setIPassport(IPassport);
//		obj.setLanguage(language);
//		obj.setLastActive(lastActive);
//		obj.setLastPassword(lastPassword);
//		obj.setLastPasswordUpdate(lastPasswordUpdate);
//		obj.setLastPinCode(lastPinCode);
//		obj.setLastPinCodeUpdate(lastPinCodeUpdate);
//		obj.setLastReleasedDate(lastReleasedDate);
//		obj.setLastUpdatedBy(lastUpdatedBy);
//		obj.setLastUpdatedDate(lastUpdatedDate);
//		obj.setLoginFailed(loginFailed);
//		obj.setMaxRelease(maxRelease);
//		
//		obj.setPinCode(pinCode);
//		obj.setSessionCode(sessionCode);
	}
	
	public static Document toDocument(User obj) {
		Document doc=new Document();
		doc.append("alias", obj.getAlias());
		doc.append("fullName", obj.getFullName());
		doc.append("title", obj.getTitle());
		doc.append("email", obj.getEmail());
		doc.append("employeeNumber", obj.getEmployeeNumber());
		doc.append("homePhoneNumber", obj.getHomePhoneNumber());
		doc.append("language", obj.getLanguage());
		doc.append("mobilePhoneNumber", obj.getMobilePhoneNumber());
		doc.append("loginName", obj.getLoginName());
		if(obj.getPosition()!=null) {
			doc.append("position", obj.getPosition().getName());
			doc.append("positionId", obj.getPosition().getId());
		}
		if(obj.getSecurityLevel()!=null){
			doc.append("securityLevel", obj.getSecurityLevel().getName());
			doc.append("securityLevelId", obj.getSecurityLevel().getId());
		}
		if (obj.getUserLevel()!=null) {
			doc.append("userLevel", obj.getUserLevel().getName());
			doc.append("userLevelId", obj.getUserLevel().getId());
		}
		if(obj.getStatus()!=null){
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId",obj.getStatus().getId());
		}
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			User obj = (User) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

}

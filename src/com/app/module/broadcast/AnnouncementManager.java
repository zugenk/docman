package com.app.module.broadcast;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Announcement;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.AnnouncementService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.UserManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

public class AnnouncementManager extends BaseUtil {
	//Requested only for Create and Blast email..
	
	
	private static Logger log = Logger.getLogger(AnnouncementManager.class);
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Announcement :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Announcement obj= new Announcement();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Announcement", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		AnnouncementService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Announcement :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Announcement obj= AnnouncementService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
	//	obj.setStatus(StatusService.getInstance().getByTypeandCode("Announcement", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		AnnouncementService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Announcement obj=AnnouncementService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Announcement", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		AnnouncementService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Announcement obj=AnnouncementService.getInstance().get(usrId);
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
					filterBuff.append(" AND announcement."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" announcement."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", announcement."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");

				}
			}
		}
		PartialList result=AnnouncementService.getInstance().getPartialList(filterParam.toString(), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Announcement obj, Map data,List<String> errors) {
		obj.setContent((String) data.get("content"));
		obj.setTargetOrganizations((String) data.get("targetOrganizations"));
		obj.setTargetUsers((String) data.get("targetUsers"));
		
		if(!nvl(data.get("announcementTypeId"))){
			try {
				Lookup announcementType= LookupService.getInstance().get(toLong(data.get("announcementTypeId")));
				if(announcementType!=null) obj.setAnnouncementType(announcementType);
			} catch (Exception e) {
				errors.add("error.invalid.announcemenType");
			}
		}
		if(!nvl(data.get("statusId"))){
			try {
				Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
	}
	
	public static Document toDocument(Announcement obj) {
		Document doc=new Document();
		//doc.append("",obj.get
		doc.append("modelClass", obj.getClass().getName());
		doc.append("name",obj.getAnnouncementType().getName());
		doc.append("content",obj.getContent());
		doc.append("id",obj.getId());
		
		doc.append("targetOrganization",obj.getTargetOrganizations());
		doc.append("targetUsers",obj.getTargetUsers());
		if(obj.getAnnouncementType()!=null){
			doc.append("announcementType", obj.getAnnouncementType().getName());
			doc.append("announcementTypeId", obj.getAnnouncementType().getId());
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
			Announcement obj = (Announcement) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	
}

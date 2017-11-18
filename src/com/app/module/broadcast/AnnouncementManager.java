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
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.EmailManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

public class AnnouncementManager extends BaseUtil {
	private static Logger log = Logger.getLogger(AnnouncementManager.class);
	private static String ACL_MODE="PUBLIC";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		List<String> errors=new LinkedList<String>();
		Announcement obj= new Announcement();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Announcement", "new"));
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		AnnouncementService.getInstance().add(obj);
		blastEmail(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		List<String> errors=new LinkedList<String>();
		Announcement obj= AnnouncementService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		AnnouncementService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
//		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
//		long usrId= Long.parseLong(objId);
		Announcement obj=AnnouncementService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Announcement", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		AnnouncementService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		//log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		//long usrId= Long.parseLong(objId);
		Announcement obj=AnnouncementService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		String filterParam=null;
		String orderParam=null;
		int start=0;
		boolean noPaging=false;
		if(data!=null && !data.isEmpty()) {
			noPaging=("Y".equalsIgnoreCase((String)data.get("noPaging")));
			start= toInt(data.get("start"),1);
			Map filterMap= (Map) data.get("filter");
			if (filterMap!=null && !filterMap.isEmpty()) {
				StringBuffer filterBuff=new StringBuffer("");
				for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					filterBuff.append(constructQuery("announcement",key,filterMap.get(key))); //filterBuff.append(" AND announcement."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
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
		if(noPaging){
			List result=AnnouncementService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=AnnouncementService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Announcement obj, Map data,List<String> errors) {
		obj.setContent((String) data.get("content"));
		obj.setSubject((String) data.get("subject"));
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
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", obj.getCreatedBy());
		doc.append("createdDate",obj.getCreatedDate());
		doc.append("content",obj.getContent());
		doc.append("subject",obj.getSubject());
		
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
	
	private static boolean blastEmail(Announcement ann) {
//		String message=ApplicationFactory.applyTemplate(emailContext, emailTemplate)
//		String subject="Announcement Blast ";
		ann.getAnnouncementType();
		String message=ann.getContent();
		String subject=ann.getSubject();
		ann.getCreatedBy();
		ann.getCreatedDate();
		ann.getStatus();
		List<String> toAddress=new LinkedList<String>();
		if(!nvl(ann.getTargetOrganizations())){
			String[] orgArr=ann.getTargetOrganizations().split("\\|");
			for (int i = 0; i < orgArr.length; i++) {
				try {
					Organization org=OrganizationService.getInstance().get(toLong(orgArr[i]));
					if (org!=null){
						if(!nvl(org.getMailingList())){
							toAddress.add(org.getMailingList());
						}
					} else {
						try {
							List<User> usrList=UserService.getInstance().getListAll("user.organization.id='"+org.getId()+"' ", null);
							for (Iterator iterator = usrList.iterator(); iterator.hasNext();) {
								User user = (User) iterator.next();
								if(!nvl(user.getEmail())) toAddress.add(user.getEmail());
							}
						} catch (Exception e) {
							log.error("Error populate toAddress through Announcement organization.user",e);
						}
					}
				} catch (Exception e) {
					log.error("Error populate toAddress through Announcement targetOrganizations",e);
				}
			}
		}
		if(!nvl(ann.getTargetUsers())){
			String[] usrArr=ann.getTargetUsers().split("\\|");
			for (int i = 0; i < usrArr.length; i++) {
				try {
					User usr=UserService.getInstance().get(toLong(usrArr[i]));
					if (usr!=null){
						if(!nvl(usr.getEmail())){
							toAddress.add(usr.getEmail());
						}
					}
				} catch (Exception e) {
					log.error("Error populate toAddress through Announcement targetUsers",e);
				}
			}
			
		}
		String from="";
		try {
			User sender=UserService.getInstance().getBy("user.loginName='"+ann.getCreatedBy()+"' ");
			from=sender.getEmail();
			EmailManager.sendMail(from, toAddress, subject, message);
			
		} catch (Exception e) {
			log.error("Error populate toAddress through Announcement targetUsers",e);
		}
	
		return false;
	}
	
	public static void checkValidity(Announcement obj,List errors) {
		if (obj.getAnnouncementType()==null) errors.add("error.announcementType.null");
		if (obj.getContent()==null) errors.add("error.content.null");
	}
	
}

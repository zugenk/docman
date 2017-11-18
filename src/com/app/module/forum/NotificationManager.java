package com.app.module.forum;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Message;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.model.Notification;
import com.app.docmgr.service.BookmarkService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.MessageService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.docmgr.service.NotificationService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class NotificationManager extends BaseUtil {
	private static Logger log = Logger.getLogger(NotificationManager.class);
	private static String ACL_MODE="PRIVATE";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Notification :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Notification obj= new Notification();
		updateFromMap(obj, data,errors);
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		NotificationService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Notification :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Notification obj= NotificationService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		NotificationService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Notification obj=NotificationService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		NotificationService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Notification obj=NotificationService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	public static Document markRead(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Notification obj=NotificationService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "markRead", toDocument(obj));
		obj.setFlag("READ");
		NotificationService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		String filterParam=null;
		if("PRIVATE".equals(ACL_MODE) && !isAdmin(passport)) filterParam+=" AND notification.subscriber.id='"+passport.getLong("userId")+"' ";
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
					filterBuff.append(constructQuery("notification",key,filterMap.get(key))); //filterBuff.append(" AND notification."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					orderParam+=(orderParam!=null?", ":"")+" notification."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		if(noPaging){
			List result=NotificationService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=NotificationService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Notification obj, Map data,List<String> errors) {
		obj.setFlag((String) data.get("flag"));
		try {
			Message postMessage= MessageService.getInstance().get(toLong(data.get("postMessageId")));
			if(postMessage!=null) obj.setPostMessage(postMessage);
		} catch (Exception e) {
			errors.add("error.invalid.postMessage");
		}
		try {
			Lookup notificationType= LookupService.getInstance().get(toLong(data.get("positionId")));
			if(notificationType!=null) obj.setNotificationType(notificationType);
		} catch (Exception e) {
			errors.add("error.invalid.notificationType");
		}
		try {
			User subscriber= UserService.getInstance().get(toLong(data.get("userLevelId")));
			if(subscriber!=null) obj.setSubscriber(subscriber);
		} catch (Exception e) {
			errors.add("error.invalid.subscriber");
		}
/*		try {
			long statusId=Long.parseLong((String)data.get("statusId"));
			Status status= StatusService.getInstance().get(statusId);
			if(status!=null) obj.setStatus(status);
		} catch (Exception e) {
			errors.add("error.invalid.status");
		}
*/
	}
	
	
	
	public static PartialList listByOwner(Document passport,int start) throws Exception{
		String filterParam=" AND notification.subscriber.id='"+passport.getLong("userId")+"' AND notification.flag is null";
		String orderParam=" notification.id DESC";
		PartialList result=NotificationService.getInstance().getPartialList(filterParam, orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	public static Document toDocument(Notification obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("flag", obj.getFlag());
		if(obj.getNotificationType()!=null) {
			doc.append("notificationType", obj.getNotificationType().getName());
			doc.append("notificationTypeId", obj.getNotificationType().getId());
		}
		if(obj.getPostMessage()!=null){
			doc.append("message", obj.getPostMessage().getContent());
			doc.append("messageId", obj.getPostMessage().getId());
		}
		if (obj.getSubscriber()!=null) {
			doc.append("subsriber", obj.getSubscriber().getLoginName());
			doc.append("subsriberId", obj.getSubscriber().getId());
		}
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Notification obj = (Notification) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	
	public static void checkValidity(Notification obj,List errors) {
		if (obj.getNotificationType()==null) errors.add("error.notificationType.null");
		if (obj.getPostMessage()==null) errors.add("error.postMessage.null");
		if (obj.getSubscriber()==null) errors.add("error.subscriber.null");
	}
}

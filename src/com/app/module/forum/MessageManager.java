package com.app.module.forum;

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
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.Message;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.TopicService;
import com.app.module.basic.BaseUtil;
import com.app.docmgr.service.MessageService;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class MessageManager extends BaseUtil{
	private static Logger log = Logger.getLogger(MessageManager.class);
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Message :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Message obj= new Message();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Message", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		MessageService.getInstance().add(obj);
		TopicManager.generateNotification(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Message :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Message obj= MessageService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("Message", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		MessageService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Message obj=MessageService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Message", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		MessageService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Message obj=MessageService.getInstance().get(usrId);
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
					filterBuff.append(" AND message."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" message."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", message."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		PartialList result=MessageService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Message obj, Map data,List<String> errors) {
		obj.setContent((String) data.get("content"));
		obj.setFilterCode((String) data.get("filterCode"));
		if(!nvl(data.get("topicId"))){
			try {
				Topic topic= TopicService.getInstance().get(toLong(data.get("topicId")));
				if(topic!=null) obj.setTopic(topic);
			} catch (Exception e) {
				errors.add("error.invalid.topic");
			}
		}
		if(!nvl(data.get("postTypeId"))){	
			try {
				Lookup postType= LookupService.getInstance().get(toLong(data.get("postTypeId")));
				if(postType!=null) obj.setPostType(postType);
			} catch (Exception e) {
				errors.add("error.invalid.postType");
			}
		}
		if(!nvl(data.get("parentId"))){
			try {
				Message parent= MessageService.getInstance().get(toLong(data.get("parentId")));
				if(parent!=null) obj.setParent(parent);
				
			} catch (Exception e) {
				errors.add("error.invalid.parent");
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
	
	public static Document toDocument(Message obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getName());
		doc.append("content", obj.getContent());
		doc.append("filterCode", obj.getFilterCode());
		doc.append("id", obj.getId());
		doc.append("createBy", obj.getCreatedBy());
		
		if(obj.getPostType()!=null) {
			doc.append("postType", obj.getPostType().getName());
			doc.append("postTypeId", obj.getPostType().getId());
		}
		if(obj.getTopic()!=null){
			doc.append("topic", obj.getTopic().getName());
			doc.append("topicId", obj.getTopic().getId());
		}
		if (obj.getParent()!=null) {
			doc.append("parentContent", obj.getParent().getContent());
			doc.append("parentId", obj.getParent().getId());
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
			Message obj = (Message) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

}

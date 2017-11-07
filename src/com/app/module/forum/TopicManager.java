package com.app.module.forum;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Forum;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Role;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.ForumService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.TopicService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.UserManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class TopicManager extends BaseUtil{
	private static Logger log = Logger.getLogger(TopicManager.class);
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Topic :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Topic obj= new Topic();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		TopicService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Topic :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Topic obj= TopicService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		TopicService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Topic obj=TopicService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		TopicService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Topic obj=TopicService.getInstance().get(usrId);
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
					filterBuff.append(" AND topic."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" topic."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", topic."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		System.out.println("filterParam=["+(filterParam!=null?filterParam.toString():null)+"]");
		System.out.println("orderParam"+ orderParam);
		PartialList result=TopicService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Topic obj, Map data,List<String> errors) {
		obj.setCode((String) data.get("code"));
		obj.setDescription((String) data.get("description"));
		obj.setFilterCode((String) data.get("filterCode"));
		obj.setIcon((String) data.get("icon"));
		obj.setName((String) data.get("name"));
		if(!nvl(data.get("parentForumId"))){
			try {
				Forum parentForum= ForumService.getInstance().get(toLong(data.get("parentForumId")));
				if(parentForum!=null)obj.setParentForum(parentForum);
			} catch (Exception e) {
				errors.add("error.invalid.parentForum");
			}
		}
		if(!nvl(data.get("statusId"))){
			try {
				long statusId=Long.parseLong((String)data.get("statusId"));
				Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
	}
	
	/*
	private void subscribe(Document passport, String topicId) throws Exception{
		long tpId=Long.parseLong(topicId);
		Topic topic=TopicService.getInstance().get(tpId);
		Set<User> subscriberSet=topic.getSubscribers();
		subscriberSet.add(UserService.getInstance().get(passport.getLong("userId")));
		TopicService.getInstance().update(topic);		
	}
	
	private void listSubscriber(Document passport, String topicId) {
		long tpId=Long.parseLong(topicId);
		Topic topic=TopicService.getInstance().get(tpId);
		topic.getSubscribers();

	}
	*/
	public static PartialList getTopicList(int start) throws Exception{
		PartialList resultList=null;
		String filterParam=null; 
		String orderParam=" ORDER BY topic.id ASC ";
		resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
		toDocList(resultList);
		return resultList;
	}
	
	public static PartialList getTopicListByForum(Forum forum,int start) throws Exception{
		PartialList resultList=null;
		String filterParam=" AND topic.forum="+forum.getId(); 
		String orderParam=" ORDER BY topic.id ASC ";
		resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
		toDocList(resultList);
		return resultList;
	}
	
	public static Document toDocument(Topic obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getName());
		doc.append("code", obj.getCode());
		doc.append("description", obj.getDescription());
		doc.append("filterCode", obj.getFilterCode());
		
		doc.append("icon", obj.getIcon());
		doc.append("id", obj.getId());
		doc.append("name", obj.getName());
		doc.append("numberOfLike", obj.getNumberOfLike());
		doc.append("numberOfPost", obj.getNumberOfPost());
		
		if(obj.getParentForum()!=null){
			doc.append("parentForum", obj.getParentForum().getName());
			doc.append("parentForumId", obj.getParentForum().getId());
		}
		if(obj.getStatus()==null) {
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId", obj.getStatus().getId());
		}
		doc.append("subscribers", getSubscriberList(obj));
		return doc;
	}
	
	public static List<Document> getSubscriberList(Topic topic) {
		List<Document> subscriberList=new LinkedList<Document>();
		for (Iterator<User> iterator = topic.getSubscribers().iterator(); iterator.hasNext();) {
			User user = iterator.next();
			subscriberList.add(UserManager.toDocument(user));
		}
		return subscriberList;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Topic obj = (Topic) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

}

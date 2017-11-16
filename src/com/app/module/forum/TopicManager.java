package com.app.module.forum;

import java.text.SimpleDateFormat;
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
import com.app.docmgr.model.Message;
import com.app.docmgr.model.Notification;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Role;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.ForumService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.NotificationService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.TopicService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.UserManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class TopicManager extends BaseUtil{
	private static Logger log = Logger.getLogger(TopicManager.class);
	private static String ACL_MODE="PUBLIC"; 
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Topic :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Topic obj= new Topic();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "new"));
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		TopicService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Topic :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Topic obj= TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		TopicService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+"] "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		TopicService.getInstance().update(obj);
	}
	
	public static void close(Document passport,String objId) throws Exception {
		log.debug("Closing obj["+objId+"] "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CLOSE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "closed"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		TopicService.getInstance().update(obj);
	}
	
	public static void archive(Document passport,String objId) throws Exception {
		log.debug("Archiving obj["+objId+"] "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_ARCHIVE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Topic", "archived"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		TopicService.getInstance().update(obj);
		//TODO: ARCHIVE TOPIC to file;
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj))) throw new Exception("error.unauthorized");
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
					filterBuff.append(constructQuery("topic",key,filterMap.get(key))); //filterBuff.append(" AND topic."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
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
		PartialList result=TopicService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	
	public static Document subscribe(Document passport,String objId) throws Exception {
		log.debug("Subscribe to topic["+objId+"] by "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "subscribe", toDocument(obj))) throw new Exception("error.unauthorized");
		User subscriber=UserService.getInstance().get(passport.getLong("userId"));
		obj.getSubscribers().add(subscriber);
		TopicService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document unSubscribe(Document passport,String objId) throws Exception {
		log.debug("Unsubscribe from topic["+objId+"] by "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "unSubscribe", toDocument(obj))) throw new Exception("error.unauthorized");
		User subscriber=UserService.getInstance().get(passport.getLong("userId"));
		obj.getSubscribers().remove(subscriber);
		TopicService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static List<Document> getSubscriberList(Topic topic) {
		List<Document> subscriberList=new LinkedList<Document>();
		for (Iterator<User> iterator = topic.getSubscribers().iterator(); iterator.hasNext();) {
			User user = iterator.next();
			subscriberList.add(UserManager.toDocument(user));
		}
		return subscriberList;
	}
	
	public static Document like(Document passport,String objId) throws Exception {
		log.debug("Like the topic["+objId+"] by "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "like", toDocument(obj))) throw new Exception("error.unauthorized");
		User loginUser=UserService.getInstance().get(passport.getLong("userId"));
		loginUser.getFavoriteTopics().add(obj);
		UserService.getInstance().update(loginUser);
		obj.setNumberOfLike(obj.getNumberOfLike()+1);
		TopicService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document unLike(Document passport,String objId) throws Exception {
		log.debug("Unlike the topic["+objId+"] by "+passport.getString("loginName"));
		Topic obj=TopicService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "unLike", toDocument(obj))) throw new Exception("error.unauthorized");
		User loginUser=UserService.getInstance().get(passport.getLong("userId"));
		loginUser.getFavoriteTopics().remove(obj);
		UserService.getInstance().update(loginUser);
		obj.setNumberOfLike(obj.getNumberOfLike()+1);
		TopicService.getInstance().update(obj);
		return toDocument(obj);
	}

	private static void updateFromMap(Topic obj, Map data,List<String> errors) {
		obj.setCode((String) data.get("code"));
		obj.setDescription((String) data.get("description"));
		obj.setFilterCode((String) data.get("filterCode"));
		obj.setIcon((String) data.get("icon"));
		obj.setName((String) data.get("name"));
		if(!nvl(data.get("forumId"))){
			try {
				Forum forum= ForumService.getInstance().get(toLong(data.get("forumId")));
				if(forum!=null)obj.setForum(forum);
			} catch (Exception e) {
				errors.add("error.invalid.parentForum");
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
	

	public static PartialList getTopicList(int start) throws Exception{
		PartialList resultList=null;
		String filterParam=null; 
		String orderParam=" ORDER BY topic.id ASC ";
		resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, ITEM_PER_PAGE);
		toDocList(resultList);
		return resultList;
	}
	
	public static PartialList getTopicListByForum(Forum forum,int start) throws Exception{
		PartialList resultList=null;
		String filterParam=" AND topic.forum="+forum.getId(); 
		String orderParam=" ORDER BY topic.id ASC ";
		resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, ITEM_PER_PAGE);
		toDocList(resultList);
		return resultList;
	}
	
	
	
	public static boolean generateNotification(Message postMessage){
		if(postMessage.getTopic()!=null){
			try{
				Topic topic= TopicService.getInstance().get(postMessage.getTopic().getId());
				topic.setNumberOfPost(topic.getNumberOfPost()+1);
				if (topic.getSubscribers()!=null || topic.getSubscribers().size()<=0) {
					NotificationService notifService=NotificationService.getInstance();
					Lookup notificationType=LookupService.getInstance().getByTypeandCode("notificationType","postMessage");
					Notification notification;
					User subscriber;
					int i=0;
					for (Iterator iterator = topic.getSubscribers().iterator(); iterator.hasNext();) {
						subscriber = (User) iterator.next();
						notification=new Notification();
						//notify.setFlag(null);
						notification.setNotificationType(notificationType);
						notification.setPostMessage(postMessage);
						notification.setSubscriber(subscriber);
						notifService.add(notification);
						i++;
					} 
					log.debug("Generate Notification to "+i+"  Subscribers is Success");
					return true;
				}
				log.debug("Not Generate any Notification, PostMessage.Topic doesnt have any Subscribers..");
				return true;
			}catch (Exception e) {
				log.error("Error Generating Notification for messageId["+postMessage.getId()+"]",e);
			}
			return false;
		}
		log.debug("Not generating Notification, PostMessage doesnt have any Topic");
		return false;
	}
	
	public static Document toDocument(Topic obj) {
		SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", obj.getCreatedBy());
		if(obj.getCreatedDate()!=null) doc.append("createdDate", sdf.format(obj.getCreatedDate()));
		if(obj.getLastUpdatedDate()!=null) doc.append("lastUpdatedDate", sdf.format(obj.getLastUpdatedDate()));
		
		doc.append("code", obj.getCode());
		doc.append("description", obj.getDescription());
		doc.append("filterCode", obj.getFilterCode());
		
		doc.append("icon", obj.getIcon());
		doc.append("name", obj.getName());
		doc.append("numberOfLike", obj.getNumberOfLike());
		doc.append("numberOfPost", obj.getNumberOfPost());
		
		if(obj.getForum()!=null){
			doc.append("forum", obj.getForum().getName());
			doc.append("forumId", obj.getForum().getId());
		}
		if(obj.getStatus()==null) {
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId", obj.getStatus().getId());
		}
		doc.append("subscribers", getSubscriberList(obj));
		return doc;
	}
	

	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Topic obj = (Topic) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	public static List toDocList(Set set){
		List list=new LinkedList();
		if(set.isEmpty()) return list;
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Topic obj = (Topic) iterator.next();
			list.add(toDocument(obj));
		}
		return list;
	}
	
	public static void checkValidity(Topic obj,List errors) {
		if (obj.getForum()==null) errors.add("error.forum.null");
		if (obj.getName()==null) errors.add("error.name.null");
	}

}

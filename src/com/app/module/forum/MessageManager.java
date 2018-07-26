package com.app.module.forum;

import java.lang.reflect.Method;
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
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.docmgr.service.MessageService;
import com.app.shared.ApplicationConstant;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;
import com.temenos.tocf.event.test;

public class MessageManager extends BaseUtil{
	private static Logger log = Logger.getLogger(MessageManager.class);
	private static String ACL_MODE="PUBLIC";
	
	
	public static List<Map> getTree(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getTree("+startId+")", new Document("modelClass","Message"));
		return getTree( startId);
	}
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT message.id as id, message.id||'' as tree, COALESCE(message.parent,0) as parent, 0 AS level FROM message "+
	   ((startId==null||startId.length()==0)?" WHERE message.parent is null":" WHERE message.id='"+startId+"' ") +
	   " UNION  ALL"+
	   " SELECT f.id as id, (c.tree||'.'||f.id) as tree, COALESCE(f.parent,0) as parent, (c.level + 1) as level FROM frm  c "+
	   " JOIN message f ON f.parent = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("MessageTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return constructTreeList(list);
	}	
	
	public static List<Map> getDownline(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getDownline("+startId+")", new Document("modelClass","Message"));
		return getDownline( startId);
	}
	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT message.id, message.parent, 1 as level FROM message"+
		  " WHERE message.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.parent, (q.level+1) as level FROM message  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("MessageDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static List<Document> getTreeByTopic(String topicId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT message.id, message.parent, 1 as level "
		+ " FROM message , status s "+
		  " WHERE message.parent is null" +
		  (nvl(topicId)?"":" and message.topic="+topicId)+
		  " and s.id=message.status  and s.state='active' "+
		  " UNION ALL"+
		  " SELECT x.id, x.parent, (q.level+1) as level FROM message  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC ";
		List<Document> result= DBQueryManager.getList("MessageDownline", sqlQuery, null); //new String[]{startId});
		return idListToDoc(result);
	}	
	
	
	public static List getTreeByTopic(String topicId,String start,String pageSize) throws Exception{
		PartialList resultList=new PartialList();
		String countQuery="select count(message.id) as \"total\" from message ,status s where "+
				 " s.id=message.status  and s.state='active' "+
				 (nvl(topicId)?"":" and message.topic="+topicId);
		Map rsCount=DBQueryManager.getFirst("queryCount",countQuery, null);
		int total=0;
		if(!rsCount.isEmpty()) {
			total=toInt(rsCount.get("total"));
	//		log.debug("count Query Total="+total+":"+rsCount.get("total").getClass());
	//		log.debug(rsCount);
		}
		if (total<=0) {
			return resultList;
		}
		int istart=toInt(start,defaulStart);
		int ipageSize=toInt(pageSize,ITEM_PER_PAGE);
		log.debug("========>>>>>"+start+":"+istart+":"+defaulStart);
		resultList.setStart(istart);
		resultList.setCount(ipageSize);
		resultList.setTotal(total);
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT message.id, message.parent, 1 as level "
		+ " FROM message , status s "+
		  " WHERE message.parent is null" +
		  (nvl(topicId)?"":" and message.topic="+topicId)+
		  " and s.id=message.status  and s.state='active' "+
		  " UNION ALL"+
		  " SELECT x.id, x.parent, (q.level+1) as level FROM message  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC limit "+ipageSize+" offset "+istart;
		List<Document> result= DBQueryManager.getList("MessageDownline", sqlQuery, null);
		resultList.addAll(result);
		return idListToDoc(resultList);
	}
	
	
	public static List<Map> getUpline(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getUpline("+startId+")", new Document("modelClass","Message"));
		return getUpline( startId);
	}
	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT message.id, message.parent, 1 as level FROM message"+
		  " WHERE message.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.parent, (q.level+1) as level FROM message  x"+
		  " JOIN q ON q.parent = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("MessageUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static List<Map> getFullTree(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getFullTree("+startId+")", new Document("modelClass","Message"));
		return getFullTree( startId);
	}
	public static List getFullTree(String startId) throws Exception {
		List upList=getUpline(startId);
		if (upList.isEmpty()) return upList;
		Map root=(Map) upList.get(0);
		return getTree(toString(root.get("id")));
	}
	
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Message :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Message obj= new Message();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Message", "new"));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		MessageService.getInstance().add(obj);
		TopicManager.generateNotification(obj,passport.getLong("userId"));
		
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Message :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
//		long uid=Long.parseLong(objId);
		Message obj= MessageService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		MessageService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
//		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
//		long usrId= Long.parseLong(objId);
		Message obj=MessageService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Message", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		MessageService.getInstance().update(obj);
		TopicManager.decreaseNumOfPost(obj.getTopic());
	}

	public static Document read(Document passport,String objId) throws Exception {
//		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
//		long usrId= Long.parseLong(objId);
		Message obj=MessageService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","Message"));
		String filterParam=null;
		String orderParam=null;
		String mode=null;
		if(data!=null && !data.isEmpty()) {
			mode=(String)data.get("mode");
			Map filterMap= (Map) data.get("filter");
			if (filterMap!=null && !filterMap.isEmpty()) {
				StringBuffer filterBuff=new StringBuffer("");
				for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					filterBuff.append(constructQuery("message",key,filterMap.get(key))); //filterBuff.append(" AND message."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
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
		if("ALL".equals(mode)){
			List result=MessageService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=MessageService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=MessageService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, toInt(data.get("start"),defaulStart), toInt(data.get("pageSize"),ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	
	
	public static List listByTopic(Document passport, long topicId,String start) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "byTopic("+topicId+")", new Document("modelClass","Message"));
		
		String filterParam= " AND message.topic.id='"+topicId+"' ";
		PartialList result=MessageService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), " message.id ASC ", toInt(start,defaulStart), ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	public static List treeByTopic(Document passport, String topicId,String start) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "treeByTopic("+topicId+")", new Document("modelClass","Message"));
		return getTreeByTopic(topicId, start, null);
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
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", getUserByLName(obj.getCreatedBy()));
		doc.append("lastUpdatedBy", getUserByLName(obj.getLastUpdatedBy()));
		if(obj.getCreatedDate()!=null) doc.append("createdDate", sdf.format(obj.getCreatedDate()));
		if(obj.getLastUpdatedDate()!=null) doc.append("lastUpdatedDate", sdf.format(obj.getLastUpdatedDate()));
		doc.append("content", obj.getContent());
		doc.append("filterCode", obj.getFilterCode());
		
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
			doc.append("parentCreatedBy", getUserByLName(obj.getParent().getCreatedBy()));
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
	
	@SuppressWarnings("unchecked")
	public static List<Document> idListToDoc(List<Document> idList){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(idList.isEmpty()) return idList;
		for (int i = 0; i < idList.size(); i++) {
			Map obj = (Map) idList.get(i);
			try{
				Message msg=MessageService.getInstance().get((Long) obj.get("id"));
				obj.putAll(toDocument(msg));
			} catch (Exception e) {
			}
			//idList.set(i, toDocument(msg));
		}
		return idList;
	}
	
	public static void checkValidity(Message obj,List errors) {
	//	if (obj.getPostType()==null) errors.add("error.postType.null");
		if (obj.getTopic()==null) errors.add("error.topic.null");
	}
	
	private static Document sqlToDoc(Document obj) {		
		Document doc=new Document();
		doc.append("createdBy", getUserByLName((String) obj.get("created_by")));
		doc.append("lastUpdatedBy", getUserByLName((String) obj.get("last_updated_by")));
		doc.append("createdDate", sdf.format(obj.get("created_date")));
		doc.append("lastUpdatedDate", sdf.format(obj.get("last_updated_date")));
		
		doc.append("content", obj.get("content"));
		doc.append("filterCode", obj.get("filterCode"));
		Long idx=null;
		
		idx=(Long) obj.get("topic");
		if(idx!=null){
			try {
				Topic topic=TopicService.getInstance().get(idx);
				doc.append("topicId", idx);
				doc.append("topic", topic.getName());
			} catch (Exception e) {
			}
		}
		
		idx=(Long) obj.get("parent");
		if(idx!=null){
			try{
				Message message=MessageService.getInstance().get(idx);
				doc.append("parentId", idx);
				doc.append("parentContent", message.getContent());
				doc.append("parentCreateBy", getUserByLName(message.getCreatedBy()));
			} catch (Exception e) {
			}
		}

		idx=(Long) obj.get("status");
		if(idx!=null){
			doc.append("statusId", idx);
			doc.append("status", ApplicationConstant.getStatusById(idx));
		}
		
		idx=(Long) obj.get("post_type");
		if(idx!=null){
			doc.append("postTypeId", idx);
			doc.append("postType", ApplicationConstant.getLookupById(idx));
		}
		return doc;
	}
	
	public static List<Document> sqlToDocList(List<Document> sqlList){
		List<Document> result=new LinkedList<Document>();
		for (Document obj : sqlList) {
			result.add(sqlToDoc(obj));
		}
		return result;
	}

}

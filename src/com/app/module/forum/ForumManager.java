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
import com.app.docmgr.model.Forum;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.docmgr.service.ForumService;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class ForumManager extends BaseUtil{
	private static Logger log = Logger.getLogger(ForumManager.class);
	private static String ACL_MODE="SYSTEM"; 
	
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT forum.id as id, forum.code,forum.name,forum.id||'' as tree, COALESCE(forum.parent,0) as parent, 0 AS level FROM forum "+
	   ((startId==null||startId.length()==0)?" WHERE forum.parent is null":" WHERE forum.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent,0) as parent, (c.level + 1) as level FROM frm  c "+
	   " JOIN forum f ON f.parent = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("ForumTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return BaseUtil.constructTreeList(list);
	}	

	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT forum.id, forum.code,forum.name, forum.parent, 1 as level FROM forum"+
		  " WHERE forum.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM forum  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("ForumDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT forum.id, forum.code,forum.name, forum.parent, 1 as level FROM forum"+
		  " WHERE forum.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM forum  x"+
		  " JOIN q ON q.parent = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("ForumUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static List getFullTree(String startId) throws Exception {
		//if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, toDocument(obj))) throw new Exception("error.unauthorized");
		List upList=getUpline(startId);
		if (upList.isEmpty()) return upList;
		Map root=(Map) upList.get(0);
		return getTree(toString(root.get("id")));
	}
	
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Forum :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Forum obj= new Forum();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Forum", "new"));
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		ForumService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Forum :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		//long uid=Long.parseLong(objId);
		Forum obj= ForumService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		ForumService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
//		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
//		long usrId= Long.parseLong(objId);
		Forum obj=ForumService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Forum", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		ForumService.getInstance().update(obj);
	}

	public static Document detail(Document passport,String objId) throws Exception {
//		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
//		long usrId= Long.parseLong(objId);
		Forum obj=ForumService.getInstance().get(toLong(objId));
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
					filterBuff.append(constructQuery("forum",key,filterMap.get(key))); //filterBuff.append(" AND forum."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" forum."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", forum."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		PartialList result=ForumService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Forum obj, Map data,List<String> errors) {
		obj.setCode((String) data.get("code"));
		obj.setDescription((String) data.get("description"));
		obj.setFilterCode((String) data.get("filterCode"));
		obj.setIcon((String) data.get("icon"));
		obj.setName((String) data.get("name"));
		if(!nvl(data.get("parentId"))){
			try {
				Forum parent= ForumService.getInstance().get(toLong(data.get("parentId")));
				if(parent!=null) obj.setParent(parent);
			} catch (Exception e) {
				errors.add("error.invalid.parentForum");
			}
		}
		if(!nvl(data.get("forumTypeId"))){
			try {
				Lookup forumType= LookupService.getInstance().get(toLong(data.get("forumTypeId")));
				if(forumType!=null) obj.setForumType(forumType);
			} catch (Exception e) {
				errors.add("error.invalid.forumType");
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
	
	public static Document toDocument(Forum obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", obj.getCreatedBy());
		doc.append("code", obj.getCode());
		doc.append("description", obj.getDescription());
		doc.append("filterCode", obj.getFilterCode());
		doc.append("icon", obj.getIcon());
		doc.append("name", obj.getName());
		if(obj.getForumType()!=null) {
			doc.append("forumType", obj.getForumType().getName());
			doc.append("forumTypeId", obj.getForumType().getId());
		}
		if(obj.getParent()!=null){
			doc.append("parent", obj.getParent().getName());
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
			Forum obj = (Forum) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	
	public static void checkValidity(Forum obj,List errors) {
		if (obj.getForumType()==null) errors.add("error.forumType.null");
		if (obj.getName()==null) errors.add("error.name.null");
	}

}

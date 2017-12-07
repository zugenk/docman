package com.app.module.document;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.docmgr.model.Folder;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.FolderService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.module.basic.LoginManager;
import com.app.module.basic.UserManager;
import com.app.module.document.FolderManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class FolderManager extends BaseUtil {
	private static Logger log = Logger.getLogger(FolderManager.class);
	private static String ACL_MODE="PUBLIC";

	public static List<Map> getTree(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getTree("+startId+")", new Document("modelClass","Folder"));
		return getTree( startId);
	}
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT folder.id as id, folder.code,folder.name,folder.id||'' as tree, COALESCE(folder.parent,0) as parent, 0 AS level FROM folder "+
	   ((startId==null||startId.length()==0)?" WHERE folder.parent is null":" WHERE folder.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent,0) as parent, (c.level + 1) as level FROM frm  c "+
	   " JOIN folder f ON f.parent = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("FolderTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return constructTreeList(list);
	}	
	
	public static List<Map> getDownline(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getDownline("+startId+")", new Document("modelClass","Folder"));
		return getDownline(startId);
	}
	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT folder.id, folder.code,folder.name, folder.parent, 1 as level FROM folder"+
		  " WHERE folder.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM folder  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("FolderDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List<Map> getUpline(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getUpline("+startId+")", new Document("modelClass","Folder"));
		return getUpline(startId);
	}
	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT folder.id, folder.code,folder.name, folder.parent, 1 as level FROM folder"+
		  " WHERE folder.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM folder  x"+
		  " JOIN q ON q.parent = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("FolderUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List<Map> getFullTree(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getFullTree("+startId+")", new Document("modelClass","Folder"));
		return getFullTree(startId);
	}
	public static List getFullTree(String startId) throws Exception {
		List upList=getUpline(startId);
		if (upList.isEmpty()) return upList;
		Map root=(Map) upList.get(0);
		return getTree(toString(root.get("id")));
	}
	
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Folder :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Folder obj= new Folder();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Folder", "new"));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		FolderService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Folder :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		//long uid=Long.parseLong(objId);
		Folder obj= FolderService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		FolderService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		//log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		//long usrId= Long.parseLong(objId);
		Folder obj=FolderService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Folder", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		FolderService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		//log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		//long usrId= Long.parseLong(objId);
		Folder obj=FolderService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","Folder"));
		String filterParam=null;
		String orderParam=null;
		int start=defaulStart;
		String mode=null;
		if(data!=null && !data.isEmpty()) {
			mode=(String)data.get("mode");
			start= toInt(data.get("start"),defaulStart);
			Map filterMap= (Map) data.get("filter");
			if (filterMap!=null && !filterMap.isEmpty()) {
				StringBuffer filterBuff=new StringBuffer("");
				for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					filterBuff.append(constructQuery("folder",key,filterMap.get(key))); //filterBuff.append(" AND folder."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" folder."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", folder."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");

				}
			}
		}
		if("ALL".equals(mode)){
			List result=FolderService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=FolderService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=FolderService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}			
	
	private static void updateFromMap(Folder obj, Map data,List<String> errors) {
		obj.setCode((String)data.get("code"));
		obj.setFolderRepoId((String)data.get("folderRepoId"));
		obj.setName((String)data.get("name"));
		if(!nvl(data.get("parentFolderId"))){
			try {
				Folder parent= FolderService.getInstance().get(toLong(data.get("parentFolderId")));
				if(parent!=null)obj.setParent(parent);
			} catch (Exception e) {
				errors.add("error.invalid.parentFolder");
			}
		}
		if(!nvl(data.get("folderTypeId"))){
			try {
				Lookup folderType= LookupService.getInstance().get(toLong(data.get("folderTypeId")));
				if(folderType!=null) obj.setFolderType(folderType);
			} catch (Exception e) {
				errors.add("error.invalid.folderType");
			}
		}
		if(!nvl(data.get("statusId"))){
			try {
				Status status= StatusService.getInstance().get(toLong((String)data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
	}
	
	public static Document toDocument(Folder obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getName());
		doc.append("code", obj.getCode());
		doc.append("folderRepoId", obj.getFolderRepoId());
		doc.append("id", obj.getId());
		doc.append("name", obj.getName());
		if(obj.getFolderType()!=null) {
			doc.append("folderType", obj.getFolderType().getName());
			doc.append("folderTypeIds", obj.getFolderType().getId());
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
			Folder obj = (Folder) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	
	public static void checkValidity(Folder obj,List errors) {
		if (obj.getName()==null) errors.add("error.name.null");
	}
	
}


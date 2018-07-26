package com.app.module.document;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.app.docmgr.model.Document;
import com.app.docmgr.model.Folder;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.service.DocumentService;
import com.app.docmgr.service.FolderService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.shared.PartialList;
import com.mongodb.util.JSON;
import com.simas.webservice.Utility;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;


public class DocumentManager extends BaseUtil {
	private static Logger log = Logger.getLogger(DocumentManager.class);
	private static String ACL_MODE="DOCUMENT";

	public static PartialList getDocumentList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY document.id ASC ";
			resultList= DocumentService.getInstance().getPartialList(filterParam, orderParam, 0, ITEM_PER_PAGE);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	public static List getTree(org.bson.Document passport,String startId) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getFullTree("+startId+")", new org.bson.Document("modelClass","Document"));
		return getTree(startId);
	}
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT document.id as id, document.document_number,document.repository_id,document.name,document.id||'' as tree, COALESCE(document.parent,0) as parent, 0 AS level FROM document "+
	   ((startId==null||startId.length()==0)?" WHERE document.parent is null":" WHERE document.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.document_number,f.repository_id, f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent,0) as parent, (c.level + 1) as level FROM frm  c "+
	   " JOIN document f ON f.parent = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("DocumentTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return constructTreeList(list);
	}	
	

	public static List getDownline(org.bson.Document passport,String startId) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getDownline("+startId+")", new org.bson.Document("modelClass","Document"));
		return getDownline(startId);
	}
	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT document.id,  document.document_number,document.repository_id, document.name, document.parent, 1 as level FROM document"+
		  " WHERE document.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.document_number,x.repository_id, x.name, x.parent, (q.level+1) as level FROM document  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("DocumentDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(org.bson.Document passport,String startId) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getUpline("+startId+")", new org.bson.Document("modelClass","Document"));
		return getUpline(startId);
	}
	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT document.id, document.document_number,document.repository_id, document.name, document.parent, 1 as level FROM document"+
		  " WHERE document.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.document_number,x.repository_id, x.name, x.parent, (q.level+1) as level FROM document  x"+
		  " JOIN q ON q.parent = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("DocumentUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static List getFullTree(org.bson.Document passport,String startId) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getFullTree("+startId+")", new org.bson.Document("modelClass","Document"));
		return getFullTree(startId);
	}
	public static List getFullTree(String startId) throws Exception {
		List upList=getUpline(startId);
		if (upList.isEmpty()) return upList;
		Map root=(Map) upList.get(0);
		return getTree(toString(root.get("id")));
	}
	
	
	public static org.bson.Document create(org.bson.Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Document :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Document obj= new Document();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Document", "new"));
		obj.setOwner(UserService.getInstance().get(passport.getLong("userId")));
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		if(nvl(obj.getDocumentVersion())) obj.setDocumentVersion("1.0.0");
		DocumentService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static org.bson.Document update(org.bson.Document passport,Map data,String objId) throws Exception{
		log.debug("Updating obj["+objId+" "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Document obj= DocumentService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		obj.setDocumentVersion(updateVersion(obj.getDocumentVersion()));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		DocumentService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(org.bson.Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Document obj=DocumentService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Document", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		DocumentService.getInstance().update(obj);
	}

	public static org.bson.Document detail(org.bson.Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Document obj=DocumentService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(org.bson.Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new org.bson.Document("modelClass","Document"));
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
					filterBuff.append(constructQuery("document",key,filterMap.get(key))); //filterBuff.append(" AND document."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" document."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", document."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		if("ALL".equals(mode)){
			List result=DocumentService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=DocumentService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=DocumentService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, toInt(data.get("start"),defaulStart), toInt(data.get("pageSize"),ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	
	public static PartialList myList(org.bson.Document passport,String start,String pageSize) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "myList", new org.bson.Document("modelClass","Document"));
		//return ownBy(passport, passport.getLong("userId"),start);
		PartialList result=ownBy(passport.getLong("userId"), toInt(start,defaulStart),toInt(pageSize,ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	
	public static PartialList ownBy(org.bson.Document passport,long userId,String start,String pageSize) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new org.bson.Document("modelClass","Document"));
		PartialList result=ownBy(userId, toInt(start,defaulStart),toInt(pageSize,ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	public static PartialList ownBy(long userId,int start,int pageSize) throws Exception{
		String filterParam=" AND document.owner.id='"+userId+"' ";
		String orderParam=" document.contentType ASC, document.name ASC ";
		return DocumentService.getInstance().getPartialList(filterParam, orderParam, start, pageSize);
	}
	
	public static org.bson.Document getByRepoId(org.bson.Document passport, String fileId) throws Exception{
		String filterParam="AND document.repositoryId='"+fileId+"' ";
		Document obj=DocumentService.getInstance().getBy(filterParam);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	public static org.bson.Document downByRepoId(org.bson.Document passport, String fileId) throws Exception{
		String filterParam="AND document.repositoryId='"+fileId+"' ";
		Document obj=DocumentService.getInstance().getBy(filterParam);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "downloadRepo", toDocument(obj));
		return toDocument(obj);
	}
	public static org.bson.Document getByDocNumber(org.bson.Document passport, String docNumber) throws Exception{
		String filterParam="AND document.documentNumber='"+docNumber+"' ";
		Document obj=DocumentService.getInstance().getBy(filterParam);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static org.bson.Document updateRepoId(org.bson.Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Document :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Document obj= DocumentService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "updateRepoId", toDocument(obj));
		if(!nvl(obj.getRepositoryId()) && !obj.getRepositoryId().equals(data.get("newRepositoryId"))) throw new Exception("error.repositoryId.mismatch");
		obj.setRepositoryId(toString(data.get("newRepositoryId")));
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		obj.setDocumentVersion(updateVersion(obj.getDocumentVersion()));
		DocumentService.getInstance().update(obj);
		return toDocument(obj);
	}
	
/*	private static boolean isOwner(Document obj,org.bson.Document passport) {
		if(!nvl(obj.getOwner())) return passport.getLong("userId")==obj.getOwner().getId();
		return passport.getString("loginName").equals(obj.getCreatedBy());
	}*/
	
	public static org.bson.Document getRepoFolder(org.bson.Document passport,String folderName) throws Exception{
		RestTemplate restTemplate= new RestTemplate();
    	if(REPO_FOLDER_MAP.isEmpty()) {
    		List folderList=RepositoryManager.getTree(); 
        	for (Iterator iterator = folderList.iterator(); iterator.hasNext();) {
        		org.bson.Document folder=toDocument(iterator.next());
        		REPO_FOLDER_MAP.put(folder.getString("text"),folder);
    			if("/".equals(folder.getString("text"))) REPO_ROOT_FOLDER_ID=folder.getString("id");
    		}
    	}
    	if (REPO_FOLDER_MAP.containsKey(folderName)) return REPO_FOLDER_MAP.get(folderName);
		Map resp=RepositoryManager.createFolder(folderName, REPO_ROOT_FOLDER_ID);  
			if("success".equals(resp.get("status"))){
	    	List folderList= RepositoryManager.getTree(); 
	    	for (Iterator iterator = folderList.iterator(); iterator.hasNext();) {
	    		org.bson.Document folder = toDocument(iterator.next());
				REPO_FOLDER_MAP.put(folder.getString("text"),folder);
			}
	    }
		if (REPO_FOLDER_MAP.containsKey(folderName)) return REPO_FOLDER_MAP.get(folderName);
		throw new Exception("error.folderName.invalid");
	}
	
	private static String updateVersion(String curVersion){	
		if(curVersion==null) return "1.0.0";
		String[] vArr=curVersion.split("\\.");
		int level=vArr.length;
		boolean kick=false;int x;
		String version="";
		do{
			level--;
			x=toInt(vArr[level],0);
			x++;
			if (x>99) x=0;
			vArr[level]=""+x;
		}while(x==0 && level>0);		
		
		for (int j = 0; j < vArr.length; j++) {
			version+=(j==0?"":".")+vArr[j];
		}
		return version;
	}
	
	private static void updateFromMap(Document obj, Map data,List<String> errors) throws Exception{
		obj.setContentType((String) data.get("contentType"));
		obj.setDescription((String) data.get("description"));
		obj.setTag((String) data.get("tag"));
		obj.setDocumentNumber(toString(data.get("documentNumber")));
		//obj.setDocumentVersion(toString(data.get("documentVersion")));
		obj.setName((String) data.get("name"));
		obj.setPriority(toInt(data.get("priority")));
		obj.setRepositoryId((String) data.get("repositoryId"));
		obj.setDocumentType(toString(data.get("documentType")));
//		obj.set((String) data.get(""));
		if(!nvl(data.get("parentId"))){
			try {
				Document parent= DocumentService.getInstance().get(toLong(data.get("parentId")));
				if(parent!=null)obj.setParent(parent);;
			} catch (Exception e) {
				errors.add("error.invalid.parentDocument");
			}
		}
		if(!nvl(data.get("folderId"))){
			try {
				Folder folder= FolderService.getInstance().get(toLong(data.get("folderId")));
				if(folder!=null)obj.setFolder(folder);
			} catch (Exception e) {
				errors.add("error.invalid.parentFolder");
			}
		}
		if(!nvl(data.get("securityLevelId"))){	
			try {
				Lookup securityLevel= LookupService.getInstance().get(toLong(data.get("securityLevelId")));
				if(securityLevel!=null) obj.setSecurityLevel(securityLevel);
			} catch (Exception e) {
				errors.add("error.invalid.securityLevel");
			}
		}
	}
	public static org.bson.Document toDocument(Document obj) {
		org.bson.Document doc=new org.bson.Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", getUserByLName(obj.getCreatedBy()));
		doc.append("lastUpdatedBy", getUserByLName(obj.getLastUpdatedBy()));
		if(obj.getCreatedDate()!=null) doc.append("createdDate", sdf.format(obj.getCreatedDate()));
		if(obj.getLastUpdatedDate()!=null) doc.append("lastUpdatedDate", sdf.format(obj.getLastUpdatedDate()));
		doc.append("contentType", obj.getContentType());
		doc.append("description", obj.getDescription());
		doc.append("tag", obj.getTag());
		doc.append("documentNumber", obj.getDocumentNumber());
		doc.append("documentType", obj.getDocumentType());
		doc.append("documentVersion", obj.getDocumentVersion());
		doc.append("repositoryId", obj.getRepositoryId());
		doc.append("priority", obj.getPriority());
		doc.append("name", obj.getName());
		if(obj.getOwner()!=null) {
			doc.append("owner", obj.getOwner().getLoginName());
			doc.append("ownerId", obj.getOwner().getId());
		}
		if(obj.getParent()!=null) {
			doc.append("parent", obj.getParent().getName());
			doc.append("parentId", obj.getParent().getId());
		}
		if(obj.getFolder()!=null) {
			doc.append("folder", obj.getFolder().getName());
			doc.append("folderId", obj.getFolder().getId());
		}
		if(obj.getSecurityLevel()!=null){
			doc.append("securityLevel", obj.getSecurityLevel().getName());
			doc.append("securityLevelId", obj.getSecurityLevel().getId());
		}
		if(obj.getStatus()!=null){
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId", obj.getStatus().getId());
		}
		return doc;
	}
	
	public static org.bson.Document toSimpleDocument(Document obj) {
		org.bson.Document doc=new org.bson.Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", getUserByLName(obj.getCreatedBy()));
		doc.append("lastUpdatedBy", getUserByLName(obj.getLastUpdatedBy()));
		if(obj.getCreatedDate()!=null) doc.append("createdDate", sdf.format(obj.getCreatedDate()));
		if(obj.getLastUpdatedDate()!=null) doc.append("lastUpdatedDate", sdf.format(obj.getLastUpdatedDate()));
		
//		doc.append("contentType", obj.getContentType());
//		doc.append("description", obj.getDescription());
//		doc.append("documentNumber", obj.getDocumentNumber());
//		doc.append("documentType", obj.getDocumentType());
//		doc.append("documentVersion", obj.getDocumentVersion());
//		doc.append("repositoryId", obj.getRepositoryId());
//		doc.append("priority", obj.getPriority());
		doc.append("name", obj.getName());
		if(obj.getOwner()!=null) {
			doc.append("owner", obj.getOwner().getLoginName());
			doc.append("ownerId", obj.getOwner().getId());
		}
//		if(obj.getParent()!=null) {
//			doc.append("parent", obj.getParent().getName());
//			doc.append("parentId", obj.getParent().getId());
//		}
//		if(obj.getFolder()!=null) {
//			doc.append("folder", obj.getFolder().getName());
//			doc.append("folderId", obj.getFolder().getId());
//		}
		if(obj.getSecurityLevel()!=null){
			doc.append("securityLevel", obj.getSecurityLevel().getName());
			doc.append("securityLevelId", obj.getSecurityLevel().getId());
		}
//		if(obj.getStatus()!=null){
//			doc.append("status", obj.getStatus().getName());
//			doc.append("statusId", obj.getStatus().getId());
//		}
		return doc;
	}
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Document obj = (Document) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	public static void toSimpleDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Document obj = (Document) list.get(i);
			list.set(i, toSimpleDocument(obj));
		}
	}
	
	public static void checkValidity(Document obj,List errors) {
		if (obj.getOwner()==null) errors.add("error.owner.null");
	}
}

package com.app.module.document;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.app.docmgr.model.Document;
import com.app.docmgr.model.Folder;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.User;
import com.app.docmgr.service.DocumentService;
import com.app.docmgr.service.FolderService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.module.basic.UserManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;


public class DocumentManager extends BaseUtil {
	private static Logger log = Logger.getLogger(DocumentManager.class);

	public static PartialList getDocumentList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY document.id ASC ";
			resultList= DocumentService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT document.id as id, document.document_number,document.repository_id,document.name,document.id||'' as tree, COALESCE(document.parent_document,0) as parent_document, 0 AS level FROM document "+
	   ((startId==null||startId.length()==0)?" WHERE document.parent_document is null":" WHERE document.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.document_number,f.repository_id, f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent_document,0) as parent_document, (c.level + 1) as level FROM frm  c "+
	   " JOIN document f ON f.parent_document = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("DocumentTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return constructTreeList(list);
	}	
	


	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT document.id,  document.document_number,document.repository_id, document.name, document.parent_document, 1 as level FROM document"+
		  " WHERE document.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.document_number,x.repository_id, x.name, x.parent_document, (q.level+1) as level FROM document  x"+
		  " JOIN q ON q.id = x.parent_document) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("DocumentDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT document.id, document.document_number,document.repository_id, document.name, document.parent_document, 1 as level FROM document"+
		  " WHERE document.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.document_number,x.repository_id, x.name, x.parent_document, (q.level+1) as level FROM document  x"+
		  " JOIN q ON q.parent_document = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("DocumentUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	
	public static org.bson.Document create(org.bson.Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Document :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Document obj= new Document();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Document", "new"));
		obj.setOwner(UserService.getInstance().get(passport.getLong("userId")));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		DocumentService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static org.bson.Document update(org.bson.Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Document :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Document obj= DocumentService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("Document", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		DocumentService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(org.bson.Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Document obj=DocumentService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Document", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		DocumentService.getInstance().update(obj);
	}

	public static org.bson.Document read(org.bson.Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Document obj=DocumentService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		return toDocument(obj);
	}
	
	public static PartialList list(org.bson.Document passport,Map data) throws Exception{
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
					filterBuff.append(" AND document."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
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
		PartialList result=DocumentService.getInstance().getPartialList(filterParam.toString(), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(Document obj, Map data,List<String> errors) throws Exception{
		obj.setContentType((String) data.get("contentType"));
		obj.setDescription((String) data.get("description"));
		obj.setDocumentNumber(toString(data.get("documentNumber")));
		obj.setDocumentVersion(toString(data.get("documentVersion")));
		obj.setName((String) data.get("name"));
		obj.setRepositoryId((String) data.get("repositoryId"));
//		obj.set((String) data.get(""));
	
		if(!nvl(data.get("parentDocumentId"))){
			try {
				Document parentDocument= DocumentService.getInstance().get(toLong(data.get("parentDocumentId")));
				if(parentDocument!=null)obj.setParentDocument(parentDocument);;
			} catch (Exception e) {
				errors.add("error.invalid.parentDocument");
			}
		}
		if(!nvl(data.get("parentFolderId"))){
			try {
				Folder parentFolder= FolderService.getInstance().get(toLong(data.get("parentFolderId")));
				if(parentFolder!=null)obj.setParentFolder(parentFolder);
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
		//doc.append("", obj.get);
		doc.append("modelClass", obj.getClass().getName());
		doc.append("contentType", obj.getContentType());
		doc.append("description", obj.getDescription());
		doc.append("documentNumber", obj.getDocumentNumber());
		doc.append("documentType", obj.getDocumentType());
		doc.append("documentVersion", obj.getDocumentVersion());
		doc.append("repositoryId", obj.getRepositoryId());
		doc.append("id", obj.getId());
		doc.append("name", obj.getName());
		if(obj.getOwner()!=null) {
			doc.append("owner", obj.getOwner().getLoginName());
			doc.append("ownerId", obj.getOwner().getId());
		}
		if(obj.getParentDocument()!=null) {
			doc.append("parentDocument", obj.getParentDocument().getName());
			doc.append("parentDocumentId", obj.getParentDocument().getId());
		}
		if(obj.getParentFolder()!=null) {
			doc.append("parentFolder", obj.getParentFolder().getName());
			doc.append("parentFolderId", obj.getParentFolder().getId());
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
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Document obj = (Document) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	
}

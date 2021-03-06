package com.app.module.document;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.SharedDocument;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.DocumentService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.docmgr.service.SharedDocumentService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;


public class SharedDocumentManager extends BaseUtil{
	private static Logger log = Logger.getLogger(SharedDocumentManager.class);
	private static String ACL_MODE="PRIVATE";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create SharedDocument :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		SharedDocument obj= new SharedDocument();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument", "new"));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		SharedDocumentService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create SharedDocument :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		//long uid=Long.parseLong(objId);
		SharedDocument obj= SharedDocumentService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		SharedDocumentService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		//long usrId= Long.parseLong(objId);
		SharedDocument obj=SharedDocumentService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		SharedDocumentService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		//long usrId= Long.parseLong(objId);
		SharedDocument obj=SharedDocumentService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","SharedDocument"));
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
					filterBuff.append(constructQuery("sharedDocument",key,filterMap.get(key))); //filterBuff.append(" AND sharedDocument."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" sharedDocument."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", sharedDocument."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");

				}
			}
		}
		if("ALL".equals(mode)){
			List result=SharedDocumentService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=SharedDocumentService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=SharedDocumentService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, toInt(data.get("start"),defaulStart), toInt(data.get("pageSize"),ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(SharedDocument obj, Map data,List<String> errors) {
		obj.setGrantAction((String) data.get("grantAction"));
	
		if(!nvl(data.get("documentId"))){
			try {
				com.app.docmgr.model.Document document= DocumentService.getInstance().get(toLong(data.get("documentId")));
				if(document!=null) obj.setDocument(document);
			} catch (Exception e) {
				errors.add("error.invalid.document");
			}
		}
		if(!nvl(data.get("targetOrganizationId"))){
			try {
				Organization targetOrganization= OrganizationService.getInstance().get(toLong(data.get("targetOrganizationId")));
				if(targetOrganization!=null)obj.setTargetOrganization(targetOrganization);
			} catch (Exception e) {
				errors.add("error.invalid.targetOrganization");
			}
		}
		if(!nvl(data.get("targetUserId"))){
			try {
				User targetUser= UserService.getInstance().get(toLong(data.get("targetUserId")));
				if(targetUser!=null) obj.setTargetUser(targetUser);
			} catch (Exception e) {
				errors.add("error.invalid.targetUser");
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
	
	public static Document toDocument(SharedDocument obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", getUserByLName(obj.getCreatedBy()));
		doc.append("lastUpdatedBy", getUserByLName(obj.getLastUpdatedBy()));
		if(obj.getCreatedDate()!=null) doc.append("createdDate", sdf.format(obj.getCreatedDate()));
		if(obj.getLastUpdatedDate()!=null) doc.append("lastUpdatedDate", sdf.format(obj.getLastUpdatedDate()));
		doc.append("grantAction", obj.getGrantAction());
		if (obj.getDocument()!=null) {
			doc.append("document", obj.getDocument().getName());
			doc.append("documentId", obj.getDocument().getId());
		}
		if(obj.getTargetUser()!=null) {
			doc.append("targetUser", obj.getTargetUser().getLoginName());
			doc.append("targetUserId", obj.getTargetUser().getId());
		}
		if(obj.getTargetOrganization()!=null){
			doc.append("targetOrganization", obj.getTargetOrganization().getName());
			doc.append("targetOrganizationId", obj.getTargetOrganization().getId());
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
			SharedDocument obj = (SharedDocument) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

	public static void checkValidity(SharedDocument obj,List errors) {
		if (obj.getDocument()==null) errors.add("error.document.null");
	}
}

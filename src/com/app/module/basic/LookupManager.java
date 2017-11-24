package com.app.module.basic;

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
import com.app.docmgr.model.Lookup;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.LookupService;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class LookupManager extends BaseUtil{
	private static Logger log = Logger.getLogger(LookupManager.class);
	private static String ACL_MODE="SYSTEM";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		log.debug("Creating Lookup : "+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		Lookup obj= new Lookup();
		updateFromMap(obj, data,errors);
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Lookup", "new"));
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		LookupService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		log.debug("Update Lookup :/n/r"+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Lookup obj= LookupService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		LookupService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Lookup obj=LookupService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Lookup", "deleted"));
		LookupService.getInstance().update(obj);
	}

	public static Document detail(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Lookup obj=LookupService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","Lookup"));
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
					filterBuff.append(constructQuery("lookup",key,filterMap.get(key))); //filterBuff.append(" AND lookup."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" lookup."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", lookup."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		if("ALL".equals(mode)){
			List result=LookupService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=LookupService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=LookupService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	public static List findByType(Document passport, String type) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "byType", new Document("modelClass","Lookup"));
		List result=LookupService.getInstance().findbyType(type);
		LookupManager.toDocList(result);
		return result;
	}
	public static Document getByTypeAndCode(Document passport, String type, String code) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "byTypeAndCode", new Document("modelClass","Lookup"));
		return toDocument(LookupService.getInstance().getByTypeandCode(type, code));
	}
	
	private static void updateFromMap(Lookup obj, Map data,List<String> errors) {
		try {
			obj.setCode((String) data.get("code"));
			obj.setDescription((String) data.get("description"));
			obj.setFilter((String) data.get("filter"));
			obj.setName((String) data.get("name"));
			obj.setPriority(toInt(data.get("priority")));
			obj.setShortname((String) data.get("shortname"));
			obj.setType((String) data.get("type"));
			/*
			if(!nvl(data.get("statusId"))){
				try {
					Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
					if(status!=null) obj.setStatus(status);
				} catch (Exception e) {
					errors.add("error.invalid.status");
				}
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.getMessage());
		}


	}
	
	public static Document toDocument(Lookup obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("code", obj.getCode());
		doc.append("description", obj.getDescription());
		doc.append("name", obj.getName());
		doc.append("status", obj.getStatus().getName());
		doc.append("statusId", obj.getStatus().getId());
		doc.append("type", obj.getType());
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Lookup obj = (Lookup) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
		

}

package com.app.module.basic;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Status;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.StatusService;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class StatusManager extends BaseUtil{
	private static Logger log = Logger.getLogger(StatusManager.class);
	private static String ACL_MODE="SYSTEM";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		log.debug("Creating Status : "+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		Status obj= new Status();
		updateFromMap(obj, data,errors);
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		StatusService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		log.debug("Update Status :/n/r"+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Status obj= StatusService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("Status", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		StatusService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Status obj=StatusService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		StatusService.getInstance().update(obj);
	}

	public static Document detail(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Status obj=StatusService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","Status"));
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
					filterBuff.append(constructQuery("status",key,filterMap.get(key))); //filterBuff.append(" AND status."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" status."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", status."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		if("ALL".equals(mode)){
			List result=StatusService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=StatusService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=StatusService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, toInt(data.get("start"),defaulStart), toInt(data.get("pageSize"),ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	
	public static List findByType(Document passport, String type) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "byType", new Document("modelClass","Status"));
		List result=StatusService.getInstance().findbyType(type);
		StatusManager.toDocList(result);
		return result;
	}
	public static Document getByTypeAndCode(Document passport, String type, String code) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "byTypeAndCode", new Document("modelClass","Status"));
		return toDocument(StatusService.getInstance().getByTypeandCode(type, code));
	}

	private static void updateFromMap(Status obj, Map data,List<String> errors) {
		obj.setState((String)data.get("state"));
		obj.setCode((String)data.get("code"));
		obj.setDescription((String)data.get("description"));
		obj.setName((String)data.get("name"));
		obj.setType((String)data.get("type"));
	}
	
	
	
	public static Document toDocument(Status obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("name", obj.getName());
		doc.append("code", obj.getCode());
		doc.append("description", obj.getDescription());
		doc.append("state", obj.getState());
		doc.append("type", obj.getType());
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Status obj = (Status) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

}

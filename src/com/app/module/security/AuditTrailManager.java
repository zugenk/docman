package com.app.module.security;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.AuditTrail;
import com.app.docmgr.service.AuditTrailService;
import com.app.module.basic.BaseUtil;
import com.app.module.security.AuditTrailManager;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class AuditTrailManager extends BaseUtil{
	private static Logger log = Logger.getLogger(AuditTrailManager.class);
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		log.debug("Creating AuditTrail : "+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		AuditTrail obj= new AuditTrail();
		updateFromMap(obj, data,errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		AuditTrailService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		log.debug("Update AuditTrail :/n/r"+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		AuditTrail obj= AuditTrailService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		//obj.setAuditTrail(AuditTrailService.getInstance().getByTypeandCode("AuditTrail", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		AuditTrailService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		AuditTrail obj=AuditTrailService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		AuditTrailService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		AuditTrail obj=AuditTrailService.getInstance().get(usrId);
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
					filterBuff.append(" AND auditTrail."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" auditTrail."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", auditTrail."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		PartialList result=AuditTrailService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(AuditTrail obj, Map data,List<String> errors) {
		obj.setAction((String) data.get("action"));
		obj.setApprovedBy((String) data.get("approvedBy"));
		//obj.setAuditTime(new Date());
		obj.setDescription((String)data.get("description"));
		obj.setDoneBy((String)data.get("doneBy"));
		obj.setEntity((String)data.get("entity"));
		obj.setSessionId((String)data.get("sessionId"));
	}
	
	
	
	public static Document toDocument(AuditTrail obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getName());
		doc.append("id", obj.getId());
		doc.append("action", obj.getAction());
		doc.append("approvedBy", obj.getApprovedBy());
		doc.append("description", obj.getDescription());
		doc.append("auditTime", obj.getAuditTime());
		doc.append("doneBy", obj.getDoneBy());
		doc.append("entity", obj.getEntity());
		doc.append("entityId", obj.getEntityId());
		doc.append("sessionId", obj.getSessionId());
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			AuditTrail obj = (AuditTrail) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

	
}

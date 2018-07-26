package com.app.module.basic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.SystemParameter;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.SystemParameterService;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class SystemParameterManager extends BaseUtil{
	private static Logger log = Logger.getLogger(SystemParameterManager.class);
	private static String ACL_MODE="SYSTEM";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		log.debug("Creating SystemParameter : "+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		SystemParameter obj= new SystemParameter();
		updateFromMap(obj, data,errors);
		obj.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter", "new"));
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		SystemParameterService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		log.debug("Update SystemParameter :/n/r"+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		SystemParameter obj= SystemParameterService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		SystemParameterService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		SystemParameter obj=SystemParameterService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter", "deleted"));
		SystemParameterService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		SystemParameter obj=SystemParameterService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","SystemParameter"));
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
					filterBuff.append(constructQuery("systemParameter",key,filterMap.get(key))); //filterBuff.append(" AND systemParameter."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" systemParameter."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", systemParameter."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		if("ALL".equals(mode)){
			List result=SystemParameterService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=SystemParameterService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=SystemParameterService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, toInt(data.get("start"),defaulStart), toInt(data.get("pageSize"),ITEM_PER_PAGE));
		toDocList(result);
		return result;
	}
	
	public static List listByVgroup(Document passport,String vgroup) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "byVgroup", new Document("modelClass","SystemParameter")); 
		String filterParam=" AND systemParameter.vgroup='"+vgroup+"' ";
		String orderParam=" systemParameter.id asc ";
		List<SystemParameter> result= SystemParameterService.getInstance().getList(filterParam, orderParam);
		toDocList(result);
		return result;
	}
	
	public static Document getByVgroupAndParameter(Document passport,String vgroup, String parameter) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "byVgroupAndParameter", new Document("modelClass","SystemParameter"));
		String filterParam=" AND systemParameter.vgroup='"+vgroup+"' AND systemParameter.parameter='"+parameter+"' ";
		SystemParameter obj= SystemParameterService.getInstance().getBy(filterParam);
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "byVgroupAndParameter", toDocument(obj));
		return toDocument(obj);
	}
	
	private static void updateFromMap(SystemParameter obj, Map data,List<String> errors) {
		try {
			obj.setDescription((String) data.get("description"));
			obj.setParameter((String) data.get("parameter"));
			obj.setSvalue((String) data.get("svalue"));
			obj.setVgroup((String) data.get("vgroup"));
			
			if(!nvl(data.get("userLevelId"))){
				try {
					Lookup userLevel= LookupService.getInstance().get(toLong(data.get("userLevelId")));
					if(userLevel!=null) obj.setUserLevel(userLevel);
				} catch (Exception e) {
					errors.add("error.invalid.userLevel");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.getMessage());
		}


	}
	
	public static Document toDocument(SystemParameter obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("vgroup", obj.getVgroup());
		doc.append("description", obj.getDescription());
		doc.append("status", obj.getStatus().getName());
		doc.append("statusId", obj.getStatus().getId());
		doc.append("parameter", obj.getParameter());
		doc.append("svalue", obj.getSvalue());
		doc.append("createdBy", obj.getCreatedBy());
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			SystemParameter obj = (SystemParameter) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
		

}

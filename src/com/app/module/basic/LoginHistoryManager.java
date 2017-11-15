package com.app.module.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.service.LoginHistoryService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.RoleService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.LookupService;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class LoginHistoryManager extends BaseUtil{
	private static Logger log = Logger.getLogger(LoginHistoryManager.class);
	private static String ACL_MODE="SYSTEM";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		log.debug("Creating LoginHistory : "+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		LoginHistory obj= new LoginHistory();
		
		updateFromMap(obj, data,errors);
		obj.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory", "new"));
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		LoginHistoryService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		log.debug("Update LoginHistory :/n/r"+Utility.debug(data)+" by "+passport.getString("loginName"));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		LoginHistory obj= LoginHistoryService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		updateFromMap(obj,data,errors) ;
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		LoginHistoryService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		LoginHistory obj=LoginHistoryService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		if(!ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj))) throw new Exception("error.unauthorized");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory", "deleted"));
		LoginHistoryService.getInstance().update(obj);
	}

	
	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		LoginHistory obj=LoginHistoryService.getInstance().get(usrId);
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
					filterBuff.append(" AND loginHistory."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" loginHistory."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", loginHistory."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		PartialList result=LoginHistoryService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	private static void updateFromMap(LoginHistory obj, Map data,List<String> errors) {
		try {
		//	SimpleDateFormat sdf=new SimpleDateFormat(pattern)
			obj.setDescription((String) data.get("description"));
		//	obj.setLastAccess((String) data.get("lastAccess"));
		//	obj.setLoginTime((String) data.get("loginTime"));
		//	obj.setLogoutTime((String) data.get("logoutTime)");
			obj.setSessionId((String) data.get("sessionId"));
		//	obj.setUser(user);
			
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
	
	public static Document toDocument(LoginHistory obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("lastAccess", obj.getLastAccess());
		doc.append("description", obj.getDescription());
		doc.append("loginTime", obj.getLoginTime());
		if(obj.getStatus()!=null) {
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId", obj.getStatus().getId());
		}
		doc.append("logoutTime", obj.getLogoutTime());
		doc.append("sessionId", obj.getSessionId());
		if(obj.getUser()!=null) {
			doc.append("user", obj.getUser().getLoginName());
			doc.append("userId", obj.getUser().getId());
		}
		
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			LoginHistory obj = (LoginHistory) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
		

}

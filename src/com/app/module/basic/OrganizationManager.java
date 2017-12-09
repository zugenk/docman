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
import com.app.docmgr.model.User;
import com.app.docmgr.model.Organization;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.OrganizationService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class OrganizationManager extends BaseUtil {
	private static Logger log = Logger.getLogger(OrganizationManager.class);
	private static String ACL_MODE="SYSTEM";
	
	public static List<Map> getTree(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getTree("+startId+")", new Document("modelClass","Organization"));
		return getTree( startId);
	}
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT organization.id as id, organization.code,organization.name,organization.id||'' as tree, COALESCE(organization.parent,0) as parent, 0 AS level FROM organization "+
	   ((startId==null||startId.length()==0)?" WHERE organization.parent is null":" WHERE organization.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent,0) as parent, (c.level + 1) as level FROM frm  c "+
	   " JOIN organization f ON f.parent = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("OrganizationTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return constructTreeList(list);
	}	
	

	public static List<Map> getDownline(Document passport,String startId)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getDownline("+startId+")", new Document("modelClass","Organization"));
		return getDownline( startId);
	}
	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT organization.id, organization.code,organization.name, organization.parent, 1 as level FROM organization"+
		  " WHERE organization.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM organization  x"+
		  " JOIN q ON q.id = x.parent) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("OrganizationDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(Document passport,String startId) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getUpline("+startId+")", new Document("modelClass","Organization"));
		return getUpline(startId);
	}
	
	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT organization.id, organization.code,organization.name, organization.parent, 1 as level FROM organization"+
		  " WHERE organization.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM organization  x"+
		  " JOIN q ON q.parent = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("OrganizationUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static List getFullTree(Document passport,String startId) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getFullTree("+startId+")", new Document("modelClass","Organization"));
		return getFullTree(startId);
	}
	public static List getFullTree(String startId) throws Exception {
		List upList=getUpline(startId);
		if (upList.isEmpty()) return upList;
		Map root=(Map) upList.get(0);
		return getTree(toString(root.get("id")));
	}
	
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		List<String> errors=new LinkedList<String>();
		Organization obj= new Organization();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Organization", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		OrganizationService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Organization :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		//long uid=Long.parseLong(organizationId);
		Organization obj= OrganizationService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		//organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		OrganizationService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
//		long usrId= Long.parseLong(objId);
		Organization obj=OrganizationService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Organization", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		OrganizationService.getInstance().update(obj);
	}

	public static Document detail(Document passport,String objId) throws Exception {
		log.debug("Read organization["+objId+" "+passport.getString("loginName"));
		Organization obj=OrganizationService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","Organization"));
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
					filterBuff.append(constructQuery("organization",key,filterMap.get(key))); //" AND organization."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" organization."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", organization."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		log.trace("filterParam=["+filterParam+"]");
		if("ALL".equals(mode)){
			List result=OrganizationService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=OrganizationService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=OrganizationService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	
	private static void updateFromMap(Organization obj, Map data,List<String> errors) {
		obj.setAddress((String) data.get("address"));
		obj.setCode((String) data.get("code"));
		obj.setFilterCode((String) data.get("filterCode"));
		obj.setMailingList((String) data.get("mailingList"));
		obj.setMnemonic((String) data.get("mnemonic"));
		obj.setName((String) data.get("name"));
		
		if(!nvl(data.get("parentId"))){
			try {
				long orgId=(Long)data.get("parentId");
				Organization parent= OrganizationService.getInstance().get(orgId);
				if(parent!=null)obj.setParent(parent);
			} catch (Exception e) {
				errors.add("error.invalid.parent");
			}
		}
		if(!nvl(data.get("organizationTypeId"))){
			try {
				Lookup organizationType= LookupService.getInstance().get(toLong(data.get("organizationTypeId")));
				if(organizationType!=null) obj.setOrganizationType(organizationType);
			} catch (Exception e) {
				errors.add("error.invalid.organizationType");
			}
		}
		if(!nvl(data.get("securityLevelId"))){
			try {
				//Lookup securityLevel= LookupService.getInstance().getByTypeandCode("securityLevel", (String)data.get("securityLevel"));
				Lookup securityLevel= LookupService.getInstance().get(toLong(data.get("securityLevelId")));
				if(securityLevel!=null) obj.setSecurityLevel(securityLevel);
			} catch (Exception e) {
				errors.add("error.invalid.securityLevel");
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
	
	public static Document toDocument(Organization obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", obj.getCreatedBy());
		//doc.append("createdDate", obj.getCreatedDate());
		doc.append("address", obj.getAddress());
		doc.append("code", obj.getCode());
		doc.append("filterCode", obj.getFilterCode());
		doc.append("mailingList", obj.getMailingList());
		doc.append("mnemonic", obj.getMnemonic());
		doc.append("name", obj.getName());
		
		if(obj.getParent()!=null) {
			doc.append("parent", obj.getParent().getName());
			doc.append("parentId", obj.getParent().getId());
		}
		if (obj.getOrganizationType()!=null) {
			doc.append("organizationType", obj.getOrganizationType().getName());
			doc.append("organizationTypeId", obj.getOrganizationType().getId());
		}
		if(obj.getSecurityLevel()!=null){
			doc.append("securityLevel", obj.getSecurityLevel().getName());
			doc.append("securityLevelId", obj.getSecurityLevel().getId());
		}
/*		if (obj.getStatus()!=null) {
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId", obj.getStatus().getId());
		} */
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Organization organization = (Organization) list.get(i);
			list.set(i, toDocument(organization));
		}
	}
	
	public static void checkValidity(Organization obj,List errors) {
		//login_name,login_password,userLevel,name
//		if (obj.getLoginName()==null) errors.add("error.loginName.null");
//		if (obj.getUserLevel()==null) errors.add("error.userLevel.null");
		if (obj.getName()==null) errors.add("error.name.null");
	}
}

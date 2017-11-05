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

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT organization.id, organization.code,organization.name, organization.parent, 1 as level FROM organization"+
		  " WHERE organization.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM organization  x"+
		  " JOIN q ON q.parent_organization = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("OrganizationUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Organization :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Organization organization= new Organization();
		updateFromMap(organization, data,errors);
		organization.setCreatedBy(passport.getString("loginName"));
		organization.setCreatedDate(new Date());
		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		OrganizationService.getInstance().add(organization);
		return toDocument(organization);
	}
	
	public static Document update(Document passport,Map data,String organizationId) throws Exception{
		//log.debug("Create Organization :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(organizationId);
		Organization organization= OrganizationService.getInstance().get(uid);
		if (organization==null) throw new Exception("error.object.notfound");
		updateFromMap(organization,data,errors) ;
		organization.setLastUpdatedBy(passport.getString("loginName"));
		organization.setLastUpdatedDate(new Date());
		//organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		OrganizationService.getInstance().update(organization);
		return toDocument(organization);
	}
	
	public static void delete(Document passport,String organizationId) throws Exception {
		log.debug("Deleting organization["+organizationId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(organizationId);
		Organization organization=OrganizationService.getInstance().get(usrId);
		if (organization==null) throw new Exception("error.object.notfound");
		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization", "deleted"));
		organization.setLastUpdatedDate(new Date());
		organization.setLastUpdatedBy(passport.getString("loginName"));
		OrganizationService.getInstance().update(organization);
	}

	public static Document read(Document passport,String organizationId) throws Exception {
		log.debug("Read organization["+organizationId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(organizationId);
		Organization organization=OrganizationService.getInstance().get(usrId);
		if (organization==null) throw new Exception("error.object.notfound");
		return toDocument(organization);
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
					filterBuff.append(" AND organization."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
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
		PartialList result=OrganizationService.getInstance().getPartialList(filterParam.toString(), orderParam, start, itemPerPage);
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
		doc.append("modelClass", obj.getClass().getName());
		doc.append("address", obj.getAddress());
		doc.append("code", obj.getCode());
		doc.append("filterCode", obj.getFilterCode());
		doc.append("id", obj.getId());
		doc.append("mailingList", obj.getMailingList());
		
		if(obj.getParent()!=null) {
			doc.append("parent", obj.getParent().getName());
			doc.append("parentId", obj.getParent().getId());
		}
		if (obj.getOrganizationType()!=null) {
			doc.append("organizationType", obj.getOrganizationType().getName());
			doc.append("organizationTypeId", obj.getOrganizationType().getId());
		}
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
	
}

package com.app.module.basic;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.app.docmgr.model.Organization;
import com.app.docmgr.service.OrganizationService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class OrganizationManager {
	private static Logger log = Logger.getLogger(OrganizationManager.class.getName());
	public static int itemPerPage=20;
	
	public static PartialList getOrganizationList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY organization.id ASC ";
			resultList= OrganizationService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	private List<Organization> getOrganizations() throws Exception {
		// TODO Auto-generated method stub
		return OrganizationService.getInstance().getListAll(null, null);
		
		
	}
	
	private void createOrganization() {
		// TODO Auto-generated method stub

	}
	
	private void updateOrganization() {
		// TODO Auto-generated method stub

	}
	
	private void addTopic() {
		// TODO Auto-generated method stub

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
		return BaseUtil.constructTreeList(list);
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

}

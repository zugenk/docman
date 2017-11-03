package com.app.module.document;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Folder;
import com.app.docmgr.service.FolderService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.module.basic.LoginManager;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class FolderManager {
	private static Logger log = Logger.getLogger(FolderManager.class.getName());
	public static int itemPerPage=20;
	
	public static PartialList getFolderList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY folder.id ASC ";
			resultList= FolderService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	private List<Folder> getFolders() throws Exception {
		// TODO Auto-generated method stub
		return FolderService.getInstance().getListAll(null, null);
	}
	
	private void createFolder() {
		// TODO Auto-generated method stub

	}
	
	private void updateFolder() {
		// TODO Auto-generated method stub

	}
	
	private void addTopic() {
		// TODO Auto-generated method stub

	}
	
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT folder.id as id, folder.code,folder.name,folder.id||'' as tree, COALESCE(folder.parent_folder,0) as parent_folder, 0 AS level FROM folder "+
	   ((startId==null||startId.length()==0)?" WHERE folder.parent_folder is null":" WHERE folder.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent_folder,0) as parent_folder, (c.level + 1) as level FROM frm  c "+
	   " JOIN folder f ON f.parent_folder = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("FolderTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return BaseUtil.constructTreeList(list);
	}	
	


	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT folder.id, folder.code,folder.name, folder.parent_folder, 1 as level FROM folder"+
		  " WHERE folder.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent_folder, (q.level+1) as level FROM folder  x"+
		  " JOIN q ON q.id = x.parent_folder) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("FolderDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT folder.id, folder.code,folder.name, folder.parent_folder, 1 as level FROM folder"+
		  " WHERE folder.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent_folder, (q.level+1) as level FROM folder  x"+
		  " JOIN q ON q.parent_folder = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("FolderUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	private void create(Map data, Document passportData) {
		// TODO Auto-generated method stub
	/*	Folder folder= new Folder();
		folder.setCode(data.get("code"));
		folder.setCreatedBy(passportData.getString(""));
		folder.setCreatedDate(createdDate);
		folder.setFolderType(folderType);
		//folder.setLastUpdatedBy(lastUpdatedBy);
		folder.setName(data.get("name"));
		folder.setParentFolder(data.get("parentFolder");
		FolderService.getInstance().add(folder);
	*/	
	}
	
	private void delete() {
		// TODO Auto-generated method stub

	}
	
	private void rename() {
		// TODO Auto-generated method stub

	}
	
	
	
}


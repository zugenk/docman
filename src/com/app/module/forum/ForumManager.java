package com.app.module.forum;

import java.util.List;
import java.util.Map;

import com.app.docmgr.model.Forum;
import com.app.docmgr.service.ForumService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.shared.PartialList;

public class ForumManager {
	public static int itemPerPage=20;
	
	public static PartialList getForumList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY forum.id ASC ";
			resultList= ForumService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	private List<Forum> getForums() throws Exception {
		// TODO Auto-generated method stub
		return ForumService.getInstance().getListAll(null, null);
		
		
	}
	
	private void createForum() {
		// TODO Auto-generated method stub

	}
	
	private void updateForum() {
		// TODO Auto-generated method stub

	}
	
	private void addTopic() {
		// TODO Auto-generated method stub

	}
	
	
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT forum.id as id, forum.code,forum.name,forum.id||'' as tree, COALESCE(forum.parent_forum,0) as parent_forum, 0 AS level FROM forum "+
	   ((startId==null||startId.length()==0)?" WHERE forum.parent_forum is null":" WHERE forum.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent_forum,0) as parent_forum, (c.level + 1) as level FROM frm  c "+
	   " JOIN forum f ON f.parent_forum = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("ForumTree", sqlQuery, null);
		//System.out.println(Utility.debug(list));
		return BaseUtil.constructTreeList(list);
	}	
	


	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT forum.id, forum.code,forum.name, forum.parent_forum, 1 as level FROM forum"+
		  " WHERE forum.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent_forum, (q.level+1) as level FROM forum  x"+
		  " JOIN q ON q.id = x.parent_forum) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("ForumDownline", sqlQuery, null); //new String[]{startId});
		//System.out.println(Utility.debug(list));
		return list;
	}	

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT forum.id, forum.code,forum.name, forum.parent_forum, 1 as level FROM forum"+
		  " WHERE forum.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent_forum, (q.level+1) as level FROM forum  x"+
		  " JOIN q ON q.parent_forum = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("ForumUpline", sqlQuery, null);// new String[]{startId});
		//System.out.println(Utility.debug(list));
		return list;
	}	

}

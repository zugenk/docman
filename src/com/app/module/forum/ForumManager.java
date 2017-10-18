package com.app.module.forum;

import java.util.List;

import org.bson.Document;

import com.app.docmgr.model.Forum;
import com.app.docmgr.service.ForumService;
import com.app.shared.PartialList;

public class ForumManager {
	public static int itemPerPage=20;
	
	public static PartialList getForumList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY message.id ASC ";
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
	

}

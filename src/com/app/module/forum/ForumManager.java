package com.app.module.forum;

import java.util.List;

import org.bson.Document;

import com.app.docmgr.model.Forum;
import com.app.docmgr.service.ForumService;
import com.app.module.basic.LoginManager;

public class ForumManager {
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

package com.app.docmgr.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.app.docmgr.model.Role;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.base.UserServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class UserService extends com.app.docmgr.service.base.UserServiceBase{
	private static UserService instance = null;

	public static synchronized UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
	
	public static List<String> getRoleNames(User user) {
		List<String> roleNames=new LinkedList<String>();
		for (Iterator iterator = user.getRoles().iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			roleNames.add(role.getName());
		}
		return roleNames;
	}

	public static List<Long> getFavTopicIds(User user) {
		List<Long> favTopicIds=new LinkedList<Long>();
		for (Iterator iterator = user.getFavoriteTopics().iterator(); iterator.hasNext();) {
			Topic topic = (Topic) iterator.next();
			favTopicIds.add(topic.getId());
		}
		return favTopicIds;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.UserHistoryServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class UserHistoryService extends com.app.docmgr.service.base.UserHistoryServiceBase{
	private static UserHistoryService instance = null;

	public static synchronized UserHistoryService getInstance(){
		if(instance == null){
			instance = new UserHistoryService();
		}
		return instance;
	}
}

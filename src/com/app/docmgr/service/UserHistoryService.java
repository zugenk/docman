package com.app.docmgr.service;

import com.app.docmgr.service.base.UserHistoryServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

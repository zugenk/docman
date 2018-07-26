package com.app.docmgr.service;

import com.app.docmgr.service.base.LoginHistoryServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class LoginHistoryService extends com.app.docmgr.service.base.LoginHistoryServiceBase{
	private static LoginHistoryService instance = null;

	public static synchronized LoginHistoryService getInstance(){
		if(instance == null){
			instance = new LoginHistoryService();
		}
		return instance;
	}
}

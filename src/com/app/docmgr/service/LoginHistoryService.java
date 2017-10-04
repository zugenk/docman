package com.app.docmgr.service;

import com.app.docmgr.service.base.LoginHistoryServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

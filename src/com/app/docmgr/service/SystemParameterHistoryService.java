package com.app.docmgr.service;

import com.app.docmgr.service.base.SystemParameterHistoryServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class SystemParameterHistoryService extends com.app.docmgr.service.base.SystemParameterHistoryServiceBase{
	private static SystemParameterHistoryService instance = null;

	public static synchronized SystemParameterHistoryService getInstance(){
		if(instance == null){
			instance = new SystemParameterHistoryService();
		}
		return instance;
	}
}

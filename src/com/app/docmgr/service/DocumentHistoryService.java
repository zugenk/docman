package com.app.docmgr.service;

import com.app.docmgr.service.base.DocumentHistoryServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class DocumentHistoryService extends com.app.docmgr.service.base.DocumentHistoryServiceBase{
	private static DocumentHistoryService instance = null;

	public static synchronized DocumentHistoryService getInstance(){
		if(instance == null){
			instance = new DocumentHistoryService();
		}
		return instance;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.EmailLogServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class EmailLogService extends com.app.docmgr.service.base.EmailLogServiceBase{
	private static EmailLogService instance = null;

	public static synchronized EmailLogService getInstance(){
		if(instance == null){
			instance = new EmailLogService();
		}
		return instance;
	}
}

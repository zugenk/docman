package com.app.docmgr.service;

import com.app.docmgr.service.base.StatusServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class StatusService extends com.app.docmgr.service.base.StatusServiceBase{
	private static StatusService instance = null;

	public static synchronized StatusService getInstance(){
		if(instance == null){
			instance = new StatusService();
		}
		return instance;
	}
}

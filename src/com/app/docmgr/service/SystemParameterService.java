package com.app.docmgr.service;

import com.app.docmgr.service.base.SystemParameterServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class SystemParameterService extends com.app.docmgr.service.base.SystemParameterServiceBase{
	private static SystemParameterService instance = null;

	public static synchronized SystemParameterService getInstance(){
		if(instance == null){
			instance = new SystemParameterService();
		}
		return instance;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.LookupServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class LookupService extends com.app.docmgr.service.base.LookupServiceBase{
	private static LookupService instance = null;

	public static synchronized LookupService getInstance(){
		if(instance == null){
			instance = new LookupService();
		}
		return instance;
	}
}

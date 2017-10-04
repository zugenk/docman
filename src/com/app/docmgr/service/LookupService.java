package com.app.docmgr.service;

import com.app.docmgr.service.base.LookupServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

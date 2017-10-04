package com.app.docmgr.service;

import com.app.docmgr.service.base.SystemSequenceServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class SystemSequenceService extends com.app.docmgr.service.base.SystemSequenceServiceBase{
	private static SystemSequenceService instance = null;

	public static synchronized SystemSequenceService getInstance(){
		if(instance == null){
			instance = new SystemSequenceService();
		}
		return instance;
	}
}

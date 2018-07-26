package com.app.docmgr.service;

import com.app.docmgr.service.base.AuditTrailServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class AuditTrailService extends com.app.docmgr.service.base.AuditTrailServiceBase{
	private static AuditTrailService instance = null;

	public static synchronized AuditTrailService getInstance(){
		if(instance == null){
			instance = new AuditTrailService();
		}
		return instance;
	}
}

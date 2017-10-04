package com.app.docmgr.service;

import com.app.docmgr.service.base.AuditTrailServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

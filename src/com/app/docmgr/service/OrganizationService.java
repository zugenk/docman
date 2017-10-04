package com.app.docmgr.service;

import com.app.docmgr.service.base.OrganizationServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class OrganizationService extends com.app.docmgr.service.base.OrganizationServiceBase{
	private static OrganizationService instance = null;

	public static synchronized OrganizationService getInstance(){
		if(instance == null){
			instance = new OrganizationService();
		}
		return instance;
	}
}

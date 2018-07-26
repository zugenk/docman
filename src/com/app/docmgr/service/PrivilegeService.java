package com.app.docmgr.service;

import com.app.docmgr.service.base.PrivilegeServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class PrivilegeService extends com.app.docmgr.service.base.PrivilegeServiceBase{
	private static PrivilegeService instance = null;

	public static synchronized PrivilegeService getInstance(){
		if(instance == null){
			instance = new PrivilegeService();
		}
		return instance;
	}
}

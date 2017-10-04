package com.app.docmgr.service;

import com.app.docmgr.service.base.RoleServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class RoleService extends com.app.docmgr.service.base.RoleServiceBase{
	private static RoleService instance = null;

	public static synchronized RoleService getInstance(){
		if(instance == null){
			instance = new RoleService();
		}
		return instance;
	}
}

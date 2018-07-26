package com.app.docmgr.service;

import com.app.docmgr.service.base.RoleServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
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

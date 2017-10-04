package com.app.docmgr.service;

import com.app.docmgr.service.base.UserServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class UserService extends com.app.docmgr.service.base.UserServiceBase{
	private static UserService instance = null;

	public static synchronized UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.ForumServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class ForumService extends com.app.docmgr.service.base.ForumServiceBase{
	private static ForumService instance = null;

	public static synchronized ForumService getInstance(){
		if(instance == null){
			instance = new ForumService();
		}
		return instance;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.FolderServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class FolderService extends com.app.docmgr.service.base.FolderServiceBase{
	private static FolderService instance = null;

	public static synchronized FolderService getInstance(){
		if(instance == null){
			instance = new FolderService();
		}
		return instance;
	}
}

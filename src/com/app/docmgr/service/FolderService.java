package com.app.docmgr.service;

import com.app.docmgr.service.base.FolderServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

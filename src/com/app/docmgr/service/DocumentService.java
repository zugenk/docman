package com.app.docmgr.service;

import com.app.docmgr.service.base.DocumentServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class DocumentService extends com.app.docmgr.service.base.DocumentServiceBase{
	private static DocumentService instance = null;

	public static synchronized DocumentService getInstance(){
		if(instance == null){
			instance = new DocumentService();
		}
		return instance;
	}
}

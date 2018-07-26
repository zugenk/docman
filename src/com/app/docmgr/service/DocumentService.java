package com.app.docmgr.service;

import com.app.docmgr.service.base.DocumentServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
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

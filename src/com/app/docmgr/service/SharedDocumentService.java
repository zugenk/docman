package com.app.docmgr.service;

import com.app.docmgr.service.base.SharedDocumentServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 06-10-2017 20:50:25
 */

public class SharedDocumentService extends com.app.docmgr.service.base.SharedDocumentServiceBase{
	private static SharedDocumentService instance = null;

	public static synchronized SharedDocumentService getInstance(){
		if(instance == null){
			instance = new SharedDocumentService();
		}
		return instance;
	}
}

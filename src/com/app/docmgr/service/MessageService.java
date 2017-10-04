package com.app.docmgr.service;

import com.app.docmgr.service.base.MessageServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class MessageService extends com.app.docmgr.service.base.MessageServiceBase{
	private static MessageService instance = null;

	public static synchronized MessageService getInstance(){
		if(instance == null){
			instance = new MessageService();
		}
		return instance;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.TopicServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class TopicService extends com.app.docmgr.service.base.TopicServiceBase{
	private static TopicService instance = null;

	public static synchronized TopicService getInstance(){
		if(instance == null){
			instance = new TopicService();
		}
		return instance;
	}
}

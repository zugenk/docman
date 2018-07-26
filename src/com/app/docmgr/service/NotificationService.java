package com.app.docmgr.service;

import com.app.docmgr.service.base.NotificationServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class NotificationService extends com.app.docmgr.service.base.NotificationServiceBase{
	private static NotificationService instance = null;

	public static synchronized NotificationService getInstance(){
		if(instance == null){
			instance = new NotificationService();
		}
		return instance;
	}
}

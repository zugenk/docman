package com.app.docmgr.service;

import com.app.docmgr.service.base.AnnouncementServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class AnnouncementService extends com.app.docmgr.service.base.AnnouncementServiceBase{
	private static AnnouncementService instance = null;

	public static synchronized AnnouncementService getInstance(){
		if(instance == null){
			instance = new AnnouncementService();
		}
		return instance;
	}
}

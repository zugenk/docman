package com.app.docmgr.service;

import com.app.docmgr.service.base.AnnouncementServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 01-11-2017 11:16:24
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

package com.app.docmgr.service;

import com.app.docmgr.service.base.BookmarkServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class BookmarkService extends com.app.docmgr.service.base.BookmarkServiceBase{
	private static BookmarkService instance = null;

	public static synchronized BookmarkService getInstance(){
		if(instance == null){
			instance = new BookmarkService();
		}
		return instance;
	}
}

package com.app.docmgr.service;

import com.app.docmgr.service.base.BookmarkServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

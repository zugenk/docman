package com.app.docmgr.service;

import com.app.docmgr.service.base.IPassportPoolServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class IPassportPoolService extends com.app.docmgr.service.base.IPassportPoolServiceBase{
	private static IPassportPoolService instance = null;

	public static synchronized IPassportPoolService getInstance(){
		if(instance == null){
			instance = new IPassportPoolService();
		}
		return instance;
	}
}

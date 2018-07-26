package com.app.docmgr.service;

import com.app.docmgr.service.base.IPassportPoolServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
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

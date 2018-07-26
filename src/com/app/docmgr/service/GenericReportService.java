package com.app.docmgr.service;

import com.app.docmgr.service.base.GenericReportServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class GenericReportService extends com.app.docmgr.service.base.GenericReportServiceBase{
	private static GenericReportService instance = null;

	public static synchronized GenericReportService getInstance(){
		if(instance == null){
			instance = new GenericReportService();
		}
		return instance;
	}
}

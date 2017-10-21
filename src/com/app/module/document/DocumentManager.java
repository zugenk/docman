package com.app.module.document;

import com.app.docmgr.service.DocumentService;
import com.app.docmgr.service.ForumService;
import com.app.shared.PartialList;

public class DocumentManager {
	public static int itemPerPage=20;

	public static PartialList getForumList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY document.id ASC ";
			resultList= DocumentService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	
	
}

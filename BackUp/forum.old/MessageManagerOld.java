package com.app.module.forum.old;

import com.app.docmgr.model.Message;
import com.app.docmgr.model.Topic;
import com.app.docmgr.service.MessageService;
import com.app.shared.PartialList;

public class MessageManagerOld {
	public static int itemPerPage=20;
	
	public static PartialList getMessageListByTopic(Topic topic,int start){
		PartialList resultList=null;
		try {
			String filterParam=" AND message.topic="+topic.getId(); 
			String orderParam=" ORDER BY message.id ASC ";
			resultList= MessageService.getInstance().getPartialList(filterParam, orderParam, start, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}

	public static PartialList getMessageListByTopicAndMessage(Topic topic,Message parent,int start){
		PartialList resultList=null;
		try {
			String filterParam=" AND message.topic="+topic.getId()+"AND message.parent="+parent.getId()+" AND message.status.state='active' "; 
			String orderParam=" ORDER BY message.id ASC ";
			resultList= MessageService.getInstance().getPartialList(filterParam, orderParam, start, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
}

package com.app.module.forum;

import com.app.docmgr.model.Forum;
import com.app.docmgr.model.User;
import com.app.docmgr.service.TopicService;
import com.app.shared.PartialList;

public class TopicManager {
	public static int itemPerPage=20;
	
	public static PartialList getTopicList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY topic.id ASC ";
			resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	public static PartialList getTopicListByForum(Forum forum,int start){
		PartialList resultList=null;
		try {
			String filterParam=" AND topic.forum="+forum.getId(); 
			String orderParam=" ORDER BY topic.id ASC ";
			resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
//	public static PartialList getTopicListBySubscriber(User subscriber,int start){
//		PartialList resultList=null;
//		try {
//			String filterParam=" AND topic.subscriber="+subscriber.getId(); 
//			String orderParam=" ORDER BY message.id ASC ";
//			resultList= TopicService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
//			//if(!resultList.isEmpty()) return true;
//		} catch (Exception e) {
////			e.printStackTrace();
//		}
//		return resultList;
//	}
	
	public static void createTopic() {
		// TODO Auto-generated method stub

	}
	
	public static void updateTopic() {
		// TODO Auto-generated method stub

	}
	
	public static void deleteTopic() {
		// TODO Auto-generated method stub

	}
	
}

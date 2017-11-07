package com.app.module.forum.old;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.app.docmgr.model.Forum;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Message;
import com.app.docmgr.model.Notification;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.ForumService;
import com.app.docmgr.service.MessageService;
import com.app.docmgr.service.NotificationService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.module.basic.LoginManager;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class ForumManagerOld {
	private static Logger log = Logger.getLogger(ForumManagerOld.class.getName());
	public static int itemPerPage=20;
	
	public static PartialList getForumList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY forum.id ASC ";
			resultList= ForumService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	private List<Forum> getForums() throws Exception {
		// TODO Auto-generated method stub
		return ForumService.getInstance().getListAll(null, null);
		
		
	}
	
	private void createForum() {
		// TODO Auto-generated method stub

	}
	
	private void updateForum() {
		// TODO Auto-generated method stub

	}
	
	private void addTopic() {
		// TODO Auto-generated method stub

	}
	
	
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT forum.id as id, forum.code,forum.name,forum.id||'' as tree, COALESCE(forum.parent_forum,0) as parent_forum, 0 AS level FROM forum "+
	   ((startId==null||startId.length()==0)?" WHERE forum.parent_forum is null":" WHERE forum.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.code,f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent_forum,0) as parent_forum, (c.level + 1) as level FROM frm  c "+
	   " JOIN forum f ON f.parent_forum = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("ForumTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return BaseUtil.constructTreeList(list);
	}	
	


	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT forum.id, forum.code,forum.name, forum.parent_forum, 1 as level FROM forum"+
		  " WHERE forum.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent_forum, (q.level+1) as level FROM forum  x"+
		  " JOIN q ON q.id = x.parent_forum) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("ForumDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT forum.id, forum.code,forum.name, forum.parent_forum, 1 as level FROM forum"+
		  " WHERE forum.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.code,x.name, x.parent_forum, (q.level+1) as level FROM forum  x"+
		  " JOIN q ON q.parent_forum = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("ForumUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	
	
	public static Boolean addForum(String code,String icon,String name, Lookup forumType, String address,Forum parentForum,String createdBy) {
		Forum forum=new Forum();
		try {
			forum.setCode(code);
			forum.setIcon(icon);
			forum.setName(name);
			forum.setForumType(forumType);
			forum.setAddress(address);
			forum.setParentForum(parentForum);
			forum.setCreatedBy(createdBy);
			forum.setCreatedDate(new Date());
			
			ForumService.getInstance().add(forum);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Boolean postMessage(String content,Lookup postType,Topic topic,String createdBy, String filterCode) {
		Message message=new Message();
		try {
			message.setContent(content);
			message.setPostType(postType);
			message.setTopic(topic);
			message.setFilterCode(filterCode);
			message.setCreatedBy(createdBy);
			message.setCreatedDate(new Date());
			
			MessageService.getInstance().add(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	public static Boolean subscribe(Lookup notificationType,Message postMessage,User subscriber, String flag) {
		Notification notification=new Notification();
		try {
			notification.setNotificationType(notificationType);
			notification.setPostMessage(postMessage);
			notification.setSubscriber(subscriber);
			notification.setFlag(flag);
			
			NotificationService.getInstance().add(notification);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//generate unique code
		public static String generateReferralCode(String prefix, String name) {
			String result ="";
			Long currentTimeMillis = System.currentTimeMillis();
			if (prefix == null || "".equals(prefix)) prefix = "DCM";
			if (name != null && !"".equals(name) && name.length() >= 3){
				name = name.substring(0, 3);
			}else{
				name = getRandomText();
				if (name != null && !"".equals(name) && name.length() >= 3){
					name = name.substring(0, 3);
				}else{
					name = "Docman";
				}
			}
			String randomText = getRandomText();
			if (randomText != null && !"".equals(randomText) && randomText.length() >= 4){
				randomText = randomText.substring(0, 4);
			}
			
			result = prefix+"_"+name+randomText;
			result = result.toUpperCase();
			
			return result;
		}
		

		 public static String getRandomText() {
			    int MAXIMUM_BIT_LENGTH = 100;
			    int RADIX = 32;
		        // cryptographically strong random number generator
		        SecureRandom random = new SecureRandom();
		 
		        // randomly generated BigInteger
		        BigInteger bigInteger = new BigInteger(MAXIMUM_BIT_LENGTH, random);
		 
		        // String representation of this BigInteger in the given radix.
		        String randomText = bigInteger.toString(RADIX);
		         
		        return randomText;
		 }

	public static void main(String[] args) {
		System.out.println(getRandomText()); 
		System.out.println(generateReferralCode("VKM-", "")); 
	}


}

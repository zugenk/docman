package com.app.module.basic;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.TargetDataLine;

import com.app.docmgr.model.Document;
import com.app.docmgr.model.Forum;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;



public class ACLManager {
	static String DOCMAN_PACKAGE="com.app.docmgr.model";
	static List<String> actionList=null;
	static boolean inited=false;
	public static void init(){
		if (inited) return;
		actionList=new LinkedList<String>();
		actionList.add("READ");
		actionList.add("UPDATE");
		actionList.add("DELETE");
		actionList.add("CREATE");
		actionList.add("LIST");
		actionList.add("SEARCH");
		actionList.add("SHARE");
//		actionList.add("");
//		actionList.add("");
	}
	
	public static boolean isAllowed(String toAction,Object targetObj,User actor){
		//todo: Need to add accessType to all object (Public/Private/Confidential
		//Target object: Topic,
		if (targetObj instanceof Topic) {
			Topic t=(Topic) targetObj;
			t.getCreatedBy();
		}
		if (targetObj instanceof Document) {
			Document d=(Document) targetObj;
			d.getCreatedBy();
		}
		
		if (targetObj.getClass().getPackage().getName().equals(DOCMAN_PACKAGE) ){
			try {
				Method gcb=targetObj.getClass().getMethod("getCreatedBy");
				String creator=(String) gcb.invoke(targetObj, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return isSharedTo(toAction,targetObj,actor);
		}
		
		return false;
	}
	
	private void isSibling() {
		// TODO Auto-generated method stub

	}
	
	private void isSuperior() {
		// TODO Auto-generated method stub

	}
	private void isDescendent() {
		// TODO Auto-generated method stub

	}
	
	public static boolean isSharedTo(String toAction,Object targetObj,User actor){
		
		return false;
	}
	
	private void hasPrivilege() {
		// TODO Auto-generated method stub

	}
	

}

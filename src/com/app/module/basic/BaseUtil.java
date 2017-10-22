package com.app.module.basic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BaseUtil {
	public static boolean nvl(String s) {
		if(s==null || s.trim().length() == 0 ){
			return true;
		}
		return false;
	}

	public static List constructTreeList(List list){
		List treeList=new LinkedList();
		Map[] parent=new Map[10];
		int lastLvl=-1;
		int objLvl;
		List childrenList=null;
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map obj = (Map) iterator.next();
			objLvl=(Integer) obj.get("level");
			if (objLvl==0) {
				parent[objLvl]=obj;
				childrenList=new LinkedList();
				obj.put("children",childrenList);
				treeList.add(obj);
			} else if(objLvl==lastLvl){
				childrenList.add(obj);
			} else {
				childrenList=(List) parent[objLvl-1].get("children");
				if(childrenList==null) {
					childrenList=new LinkedList();
					parent[objLvl-1].put("children",childrenList);
				}
				childrenList.add(obj);
				parent[objLvl]=obj;
				lastLvl=objLvl;	
			}
		}
		
		return treeList;
	}
	
}

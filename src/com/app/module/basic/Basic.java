package com.app.module.basic;

public class Basic {
	public static boolean nvl(String s) {
		if(s==null || s.trim().length() == 0 ){
			return true;
		}
		return false;
	}

}

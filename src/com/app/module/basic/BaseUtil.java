package com.app.module.basic;

import java.io.FileInputStream;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.app.docmgr.model.AuditTrail;
import com.app.docmgr.model.Status;
import com.app.docmgr.service.AuditTrailService;
import com.app.docmgr.service.StatusService;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;
import com.thoughtworks.xstream.io.xml.DocumentReader;

public class BaseUtil {
	public static String ADMIN_ROLE="GOD"; //"ADMIN";
	public static int itemPerPage=20;
	final static int MAX_WRONG_PASSWD_ATTEMPT=3;
	public static Status BLOCKED_USER_STATUS=null; //StatusService.getInstance().getByTypeandCode("User", "Blocked");
	private static boolean inited=false;
	
	
	public static void init(){
		if (inited) return;
		try {
			BLOCKED_USER_STATUS=StatusService.getInstance().getByTypeandCode("User", "blocked");
			inited=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean nvl(String s) {
		if(s==null || s.trim().length() == 0 ){
			return true;
		}
		return false;
	}
	
	public static boolean nvl(Object o) {
		if(o==null) return true;
		if(o instanceof String) {
			if(((String)o).trim().length() == 0 ) return true;
		}
		return false;
	}
	
	public static long toLong(Object obj) throws Exception{
		if(obj==null) return 0;
		if(obj instanceof Long) return (Long) obj; 
		if(obj instanceof Integer) return new Long((Integer) obj);
		if(obj instanceof String)  return Long.parseLong((String) obj);
//		throw new Exception("Expecting Long value instead of "+obj.getClass().getName());
		return Long.parseLong((String) obj);
	}

	public static String toString(Object obj) throws Exception{
		if(obj==null) return null;
		if(obj instanceof Long) return ""+((Long) obj).longValue(); 
		if(obj instanceof Integer) return ""+((Integer) obj).intValue();
		if(obj instanceof String)  return (String) obj;
//		throw new Exception("Expecting String value instead of "+obj.getClass().getName());
		return obj.toString();
	}
	public static int toInt(Object obj) throws Exception{
		if(obj==null) return 0;
		if(obj instanceof Integer) return ((Integer) obj).intValue();
		if(obj instanceof String)  return Integer.parseInt((String) obj);
//		throw new Exception("Expecting Integer value instead of "+obj.getClass().getName());
		return 0;
	}
	
	public static void putList(Map response,String key,Object result) {
		if (result!=null && result instanceof PartialList) {
			PartialList plist=(PartialList) result;
			response.put("pageCtrl-start",plist.getStart()); //(plist.getStart()<=0?1:plist.getStart()));
			response.put("pageCtrl-count",plist.getCount());
			response.put("pageCtrl-total",plist.getTotal());
		}
		response.put((key==null?"result":key), result);
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
	
	
	public static String listToString(List<String> list) {
		StringBuffer result=new StringBuffer("");
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			result.append(iterator.next()+"|");
		}
		return result.toString();
	}
	
	
	
	public static org.springframework.util.MultiValueMap<String, Object> toMultiValueMap(Map<String, Object> request){
    	MultiValueMap<String, Object> requestMap= new LinkedMultiValueMap<String, Object>();
    	if (request!=null && !request.isEmpty()){
    		for (Iterator<String> iterator = request.keySet().iterator(); iterator.hasNext();) {
    			String key = (String) iterator.next();
				requestMap.add(key, request.get(key));
			}
    	};
    	return requestMap;
	}
	
	public static void setBasicAuth(String user, String password, HttpHeaders headers) {
    	String plainCreds = user+":"+password;//"okmAdmin:admin";
    	byte[] plainCredsBytes = plainCreds.getBytes();
    	byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
    	String base64Creds = new String(base64CredsBytes);
    	headers.add("Authorization", "Basic " + base64Creds);
	}
	
	public static MediaType toMediaType(String contentType)throws Exception{
		MediaType ct=MediaType.valueOf(contentType);
    	if (ct==null) {
    		contentType=MediaType.APPLICATION_JSON_VALUE;
     		throw new Exception("Unknown or undefined MediaType defaulting to ["+MediaType.APPLICATION_JSON_VALUE+"]");
     	} else System.out.println("Setting Media Type = ["+ct.toString()+"]");
    	return ct;
	}
	
	public static boolean isAdmin(Document passport){
		return "admin".equals(passport.getString("userLevel"));
		
//		List<String> roles= (List) iPass.get("roleNames");
//		return !roles.contains(BaseUtil.ADMIN_ROLE); 
	}
	
	public boolean isSame(Document passport, String loginName) {
		return passport.getString("loginName").equals(loginName);
	}
	
	public static ResponseEntity<Document> reply(Document response) {
		if(!nvl(response.getString("errorMessage"))) {
			//System.out.println(response.getString("errorMessage") +" ====>>> "+response.getString("errorMessage").equals("error.unauthorized"));
			if (response.getString("errorMessage").contains("error.unauthorized")) return new ResponseEntity<Document>(response,HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<Document>(response,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Document>(response,HttpStatus.OK);
	}
	
	
	public static void auditLog(Document passport,String actions,String entity,String description,String approvedBy) {
		try {
			AuditTrail auditTrail =new AuditTrail();
			auditTrail.setActions(actions);
			auditTrail.setApprovedBy(approvedBy);
			auditTrail.setAuditTime(new Date());
			auditTrail.setDescription(description);
			auditTrail.setDoneBy(passport.getString("loginName"));
			auditTrail.setSessionId(passport.getString("ipassport"));
			auditTrail.setEntity(entity);
			AuditTrailService.getInstance().add(auditTrail);
		} catch (Exception e) {
			//log.error("Error logging AuditTrail",e);
			e.printStackTrace();
		}
	}
	
	public static String genRandomText(int len) {
		return RandomStringUtils.randomAlphanumeric(len);
	}
	
	public static void main2(String[] args) {
		//0#Terverifikasi#
		try{
			FileInputStream fis=new FileInputStream("/Users/it.atsbanksinarmas.com/Documents/EKTP-R/Susiana.txt");
			byte[] b=new byte[1024];
			int l=0;
			String data="";
			while((l=fis.read(b))>0){
				data+=new String(b,0,l);
			}
			data=data.substring(data.lastIndexOf('#')+1);
//			
			//String data="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";
			System.out.println(data);
			System.out.println("============================================================================================================");
			byte[] result=Base64.getDecoder().decode(data.getBytes());
			System.out.println(new String(result));
			System.out.println("============================================================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			
			//Object test="21322";
	//		Object test=new Integer(21322);
	//		Object test=new Long(21322);
			//long x=(Long) test;
			//System.out.println(""+x);
			
		//	long x=Long.parseLong((String) test);
	//		System.out.println(""+toLong(test)/2); 
			
			Document response=new Document("errorMessage","error.unauthorized");
			ResponseEntity<Document> reply=reply(response);
			System.out.println(Utility.debug(reply.getHeaders()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

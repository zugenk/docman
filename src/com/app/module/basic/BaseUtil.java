package com.app.module.basic;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.SystemParameter;
import com.app.docmgr.model.User;
import com.app.docmgr.service.UserService;
import com.app.shared.ApplicationConstant;
import com.app.shared.PartialList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.util.JSON;
import com.simas.db.MongoManager;
import com.simas.webservice.Utility;

public class BaseUtil {
//	public static String ADMIN_ROLE="GOD"; //"ADMIN";
	public static int ITEM_PER_PAGE=20;
	public static final int defaulStart=0;
	public static int MAX_WRONG_PASSWD_ATTEMPT=3;
	public static Status BLOCKED_USER_STATUS=null; //StatusService.getInstance().getByTypeandCode("User", "Blocked");
	private static boolean inited=false;
//	public static String DOCMAN_BASE_URL="http://128.199.128.32:8080/DocumentManager/rest/v1";
	public static String SERVER_BASE_URL="http://localhost:8080/DocumentManager/rest/v1";
	
//	public static String REPO_BASE_URL="http://localhost:8081/PHIDataEngine/file";
	public static String REPO_BASE_URL="http://52.187.54.220:8081/PHIDataEngine";
	public static String RESOURCE_URL="http://52.187.54.220:8080/PHIDataEngine";
	public static String REPO_API_KEY="c02ae64c-fd69-42eb-975b-0a3607c388a7";
	public static Map<String, Document> REPO_FOLDER_MAP=new HashMap<>(); 
	public static String REPO_ROOT_FOLDER_ID=null;
	public static String TEMP_FILE_PREFIX="DocMan_";
	public static boolean useDbEncryption=true;
	
	static String IPASSPORT_COLLECTION="IPassportData";
	static String MONGO_DB_CFG="DEFAULT|mongo-docman|27017|DOCMAN";
	static int PASSPORT_LENGTH=32;
	//static String DB_CFG="DEFAULT|localhost|27017|DOCMAN";
	//private static boolean inited=false;

	static long SESSION_TIMEOUT_PERIOD=600000; //10 Mins
	public static Map<String,String> APIKEY_MAP= new HashMap<String,String>();
	public static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static Map<String,Map> USER_CACHE=new HashMap<String,Map>();

	
	public static void init() {
		if(inited) return;
		try {
			//ApplicationConstant.reset();
			Map<String, SystemParameter> coreParam=ApplicationConstant.getSystemParamMap("CORE");
		    ITEM_PER_PAGE=toInt(coreParam.get("ITEM_PER_PAGE").getSvalue());
			MAX_WRONG_PASSWD_ATTEMPT=toInt(coreParam.get("MAX_WRONG_PASSWD_ATTEMPT").getSvalue());
			SERVER_BASE_URL=coreParam.get("SERVER_BASE_URL").getSvalue();
			IPASSPORT_COLLECTION=coreParam.get("IPASSPORT_COLLECTION").getSvalue();
			MONGO_DB_CFG=coreParam.get("MONGO_DB_CFG").getSvalue();
			SESSION_TIMEOUT_PERIOD=toLong(coreParam.get("SESSION_TIMEOUT_PERIOD").getSvalue());
			PASSPORT_LENGTH=toInt(coreParam.get("PASSPORT_LENGTH").getSvalue(),32);
			
			Map<String, SystemParameter> repoParam=ApplicationConstant.getSystemParamMap("REPOSITORY");
			REPO_BASE_URL=repoParam.get("REPO_BASE_URL").getSvalue();
			RESOURCE_URL=repoParam.get("RESOURCE_URL").getSvalue(); 
			REPO_API_KEY=repoParam.get("REPO_API_KEY").getSvalue(); 
			
			Map<String, SystemParameter> tokenApiParam=ApplicationConstant.getSystemParamMap("API_TOKEN");
			if(tokenApiParam!=null && !tokenApiParam.isEmpty()){
				for (Iterator iterator = tokenApiParam.values().iterator(); iterator.hasNext();) {
					SystemParameter sParam = (SystemParameter) iterator.next();
					APIKEY_MAP.put(sParam.getSvalue(),sParam.getParameter());
				}
	//			APIKEY_MAP.put("VeritaKM-FE", "r02u7JZu2p7uGMdQKCycCrsM6pANO34E");
			}
			MongoManager.init(MONGO_DB_CFG);
		    MongoManager.getCollection(IPASSPORT_COLLECTION).createIndex(new Document("userId", 1),new IndexOptions().unique(true).name("UniqueUserId"));
		    MongoManager.getCollection(IPASSPORT_COLLECTION).createIndex(new Document("ipassport", 1),new IndexOptions().unique(true).name("UniqueIPassport"));
		  //  MongoManager.getCollection(IPASSPORT_COLLECTION).createIndex(new Document("itoken", 1),new IndexOptions().unique(true).name("UniqueIToken"));

			BLOCKED_USER_STATUS=ApplicationConstant.getStatus("User", "blocked");	
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
		if(obj instanceof Double) return ((Double) obj).longValue();
		if(obj instanceof String)  return Long.parseLong((String) obj);
		return Long.parseLong((String) obj);
	}
	public static long toLong(Object obj,long x) {
		try{
			if(obj==null) return x;
			if(obj instanceof Long) return (Long) obj; 
			if(obj instanceof Integer) return new Long((Integer) obj);
			if(obj instanceof Double) return ((Double) obj).longValue();
			if(obj instanceof String)  return Long.parseLong((String) obj);
			return Long.parseLong((String) obj);
		}catch(Exception ex){
		}
		return x;
	}
	
	public static String toString(Object obj) throws Exception{
		if(obj==null) return null;
		if(obj instanceof Long) return ""+((Long) obj).longValue(); 
		if(obj instanceof Integer) return ""+((Integer) obj).intValue();
		if(obj instanceof Double) return ""+((Double) obj).doubleValue();
		if(obj instanceof String)  return (String) obj;
//		throw new Exception("Expecting String value instead of "+obj.getClass().getName());
		return obj.toString();
	}
	
	public static int toInt(Object obj) throws Exception{
		if(obj==null) return 0;
		if(obj instanceof Integer) return ((Integer) obj).intValue();
		if(obj instanceof Long) return ((Long) obj).intValue(); 
		if(obj instanceof Double) return ((Double) obj).intValue();
		if(obj instanceof String)  return Integer.parseInt((String) obj);
//		throw new Exception("Expecting Integer value instead of "+obj.getClass().getName());
		return 0;
	}
	
	public static int toInt(Object obj,int x){
		try {
			if(obj==null) return x;
			if(obj instanceof Integer) return ((Integer) obj).intValue();
			if(obj instanceof Long) return ((Long) obj).intValue(); 
			if(obj instanceof Double) return ((Double) obj).intValue();
			if(obj instanceof String)  return Integer.parseInt((String) obj);
		} catch (Exception e) {
		}
		return x;
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
	
/*	public static String constructQuery(String objName,String key,Object objValue) throws Exception{
		if(!nvl(objValue) && ((String)objValue).contains(";")) throw new Exception("Possible SQL Injection");
		//String query=" AND "+objName+"."+key;
		String query=" AND "+(objName!=null?objName+".":"")+key;
		String value=toString(objValue);
		if(value==null) return query+" ISNULL";
		value=value.trim();
		if(value.contains("=")) throw new Exception("error.filter.invalidChar");
		if(value.equals("$ISNULL")) return query+" ISNULL ";
		if(value.equals("!$ISNULL")) return query+" IS NOT NULL ";
		if(value.startsWith("$EQ|")) return query+" = '"+value.substring(4)+"' ";
		if(value.startsWith("$GT|")) return query+" > '"+value.substring(4)+"' ";
		if(value.startsWith("$GE|")) return query+" >= '"+value.substring(4)+"' ";
		if(value.startsWith("$LT|")) return query+" < '"+value.substring(4)+"' ";
		if(value.startsWith("$LE|")) return query+" <= '"+value.substring(4)+"' ";
		if(value.startsWith("$LK|")) return query+" LIKE '"+value.substring(4)+"' ";

		if(value.startsWith("$BT|")) {
			String[] vArr=value.split("\\|");
			//if (vArr.length==3)  
			//return query+" >= '"+vArr[1]+"' "+query+" <= '"+vArr[2]+"' ";
			return query+" BETWEEN '"+vArr[1]+"' AND  '"+vArr[2]+"' ";
			
		}
		String lowQuery=" AND lower("+(objName!=null?objName+".":"")+key+") ";
		if(value.startsWith("$EL|")) return lowQuery+" = '"+value.substring(4)+"' ";
		if(value.startsWith("$LL|")) return lowQuery+" LIKE '"+value.substring(4)+"' ";

		return query+" LIKE '%"+value+"%'";
	} */
	public static String constructQuery(String objName,String key,Object objValue) throws Exception{
		String sval=(String) objValue;
		String query=" AND "+(objName!=null?objName+".":"")+key;
		if(sval==null) return query+" ISNULL";
		
		if(sval.contains(";")) throw new Exception("Possible SQL Injection");
		if(sval.contains("=")) throw new Exception("error.filter.invalidChar");
		
		sval=sval.trim();
		if(sval.equals("$ISNULL")) return query+" ISNULL ";
		if(sval.equals("!$ISNULL")) return query+" IS NOT NULL ";
		
		if(key.contains("(")) {
			if(objName==null) {
				query=" AND "+key;
			} else {
				int x= key.indexOf("(");
				String func=key.substring(0,x+1);
				key=key.substring(x+1);
				query=" AND "+func+objName+"."+key;
			}
		}
		System.out.println("Trace query "+query);
		
		if(!sval.startsWith("$")) return query+" LIKE '%"+sval+"%'";
		String[] vArr=sval.split("\\|");
		String lowQuery=" AND lower("+(objName!=null?objName+".":"")+key+") ";
		if("$EL".equals(vArr[0])) return lowQuery+" = '"+vArr[1]+"' ";
		if("$LL".equals(vArr[0])) return lowQuery+" LIKE '"+vArr[1]+"' ";

		if("$BT".equals(vArr[0]) && vArr.length==3) return query+" BETWEEN '"+vArr[1]+"' AND  '"+vArr[2]+"' ";
		return query+toOpr(vArr[0])+" '"+vArr[1]+"' ";
	}
	
	public static String constructSQLPeriodFilter(String periodField ,String fromPeriod,String reportPeriod) {
		if(!nvl(fromPeriod) && fromPeriod.contains("|")){
			String[] vArr=fromPeriod.split("\\|");
			if (vArr[0].equals("$LA")){
				int from=toInt(vArr[1],1);
				return " AND "+periodField+" > now() - interval '"+from+" "+reportPeriod+"' ";
			}
			if (vArr.length==2) {
				int from=toInt(vArr[1],0);
				//if (from>0) return " AND date_part('"+reportPeriod+"',"+periodField+") "+toOpr(vArr[0])+" "+from+" ";
				//if (from==0) return " AND date_part('"+reportPeriod+"',"+periodField+") "+toOpr(vArr[0])+" date_part('"+reportPeriod+"',current_date) ";
				return " AND date_part('"+reportPeriod+"',"+periodField+") "+toOpr(vArr[0])+(from>0?from:" (date_part('"+reportPeriod+"',current_date)+ "+from+") ");
			}
			
			if (fromPeriod.startsWith("$BT|") && vArr.length==3){
				int from=toInt(vArr[1],0);
				int to=toInt(vArr[2],0);
				return " AND date_part('"+reportPeriod+"',"+periodField+") between "+(from>0?from:" (date_part('"+reportPeriod+"',current_date)+ "+from+") ")+" and "+(to>0?to:" (date_part('"+reportPeriod+"',current_date)+ "+to+") ")+" ";
			}
		}
		return "";

	}
	
	public static String toOpr(String sOperand) {
		if("$EQ".equals(sOperand)) return " = ";
		if("$GT".equals(sOperand)) return " > ";
		if("$GE".equals(sOperand)) return " >= ";
		if("$LT".equals(sOperand)) return " < ";
		if("$LE".equals(sOperand)) return " <= ";
		if("$LK".equals(sOperand)) return " LIKE ";
		else return " ";
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
	
	public static Document toDocument(Object obj) {
		if(obj instanceof com.mongodb.BasicDBObject){
			Map<String,Object> dbo=(Map<String,Object>) obj;
			Document result=new Document();
			result.putAll(dbo);
			return result;
		}
		return (Document) obj;
	}
	
	
	public static MediaType toMediaType(String contentType)throws Exception{
		MediaType ct=MediaType.valueOf(contentType);
    	if (ct==null) {
    		contentType=MediaType.APPLICATION_JSON_VALUE;
     		throw new Exception("Unknown or undefined MediaType defaulting to ["+MediaType.APPLICATION_JSON_VALUE+"]");
     	} else System.out.println("Setting Media Type = ["+ct.toString()+"]");
    	return ct;
	}
	
	public static boolean isAdminExec(Document passport){
		return "|admin|executive|".contains(passport.getString("userLevelCode"));
	}
	
	public boolean isSame(Document passport, String loginName) {
		return passport.getString("loginName").equals(loginName);
	}
	
	public static ResponseEntity<Document> reply(Document response) {
		if(response==null)  new ResponseEntity<Document>(new Document("errorMessage","error.object.notFound"),HttpStatus.BAD_REQUEST);
		if(!nvl(response.getString("errorMessage"))) {
			//System.out.println(response.getString("errorMessage") +" ====>>> "+response.getString("errorMessage").equals("error.unauthorized"));
			if (response.getString("errorMessage").contains("error.forbidden")) return new ResponseEntity<Document>(response,HttpStatus.FORBIDDEN);
			if (response.getString("errorMessage").contains("error.session.invalid")) return new ResponseEntity<Document>(response,HttpStatus.UNAUTHORIZED);
			//if (response.getString("errorMessage").contains("error.login.password")||response.getString("errorMessage").contains("error.login.failed")) return new ResponseEntity<Document>(response,HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<Document>(response,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Document>(response,HttpStatus.OK);
	}
	
	
	public static String genRandomText(int len) {
		return RandomStringUtils.randomAlphanumeric(len);
	}
	
	
	public static void clearTempFile(List<File> attachments){
		for (Iterator iterator = attachments.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			if(file.exists()) file.delete();
		}
	}
	
	public static String getFNamebyLname(String loginName) {
		Map usr=getUserByLName(loginName);
		return (usr==null?"":(String) usr.get("fullName"));
	}
	
	/*
	public static User getUserByLName(String loginName) {
		if(loginName==null) return null;
		if (USER_CACHE.containsKey(loginName)) return USER_CACHE.get(loginName);
		try{
			User user=UserService.getInstance().getBy(" and user.loginName='"+loginName+"'");
			USER_CACHE.put(loginName, user);
			return user;
		}catch (Exception e) {
		}
		return null;	
	}
*/
	
	public static Map getUserByLName(String loginName) {
		if(loginName==null) return null;
		if (USER_CACHE.containsKey(loginName)) return USER_CACHE.get(loginName);
		try{
			String sqlQuery="select id, full_name as \"fullName\", login_name as \"loginName\", picture as \"picture\"  from app_user order by id asc ";
			List userList=DBQueryManager.getList("loginNameCache", sqlQuery, null);
			DBQueryManager.decryptList(userList, new String[]{"fullName"});
			for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
				Map usr = (Map) iterator.next();
				USER_CACHE.put((String)usr.get("loginName"), usr);
			}
			if (USER_CACHE.containsKey(loginName)) return USER_CACHE.get(loginName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	public static void updateUserChache(User updatedUser) {
		if(updatedUser==null) return;
		try{
			Map usr= USER_CACHE.get(updatedUser.getLoginName());
			if (usr==null)usr=new HashMap();
			usr.put("id", updatedUser.getId());
			usr.put("fullName", updatedUser.getFullName());
			usr.put("loginName", updatedUser.getLoginName());
			usr.put("picture", updatedUser.getPicture());
			USER_CACHE.put(updatedUser.getLoginName(), usr);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean unHandled(Exception ex) {
		if (ex==null) return true;
		if(ex.getMessage()==null) return true;
		if(ex.getMessage().startsWith("error.")) return false;
		return true;
	}
	
	 public static String getHash(Map dataMap) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String buff=JSON.serialize(dataMap);
        byte[] mdbytes = md.digest(buff.getBytes());

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());
	        
       //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<mdbytes.length;i++) {
    	  hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
    	}

    	System.out.println("Hex format : " + hexString.toString());
    	return hexString.toString();
	}
	
	 
	public static void testHash() {
		 String jsonString="{ \"user\": { "+
       " \"row_id\": 242,"+
       " \"user\": \"196402051989031001\","+
       " \"pass\": \"09039ee80d5f4342b4244de63422e90d\","+
       " \"admin\": 0"+
       " }, \"profile\": {"+
       " \"row_id\": 9,"+
       " \"nip_lama\": \"\","+
       " \"nip_baru\": \"196402051989031001\","+
       " \"instansi_induk\": \"Badan Nasional Penanggulangan Terorisme\","+
       " \"instansi_asal\": \"Kementerian Luar Negeri\","+
       " \"stts_pegawai\": \"Organik\","+
       " \"unit_kerja\": \"3\","+
       " \"gelar_depan\": \"\","+
       " \"nm_lengkap\": \"Mohamad Kamal\","+
       " \"gelar_belakang\": \"SH.LL.M\","+
       " \"jabatan\": \"Staf Khusus Biro Umum\","+
       " \"pendidikan\": \"S II\","+
       " \"pangkat\": \"PEMBINA TK.I IV/B\","+
       " \"angkatan\": \"PNS\","+
       " \"korps_tni\": \"\","+
       " \"sumber_pa\": \"\","+
       " \"tpt_lahir\": \"Jakarta\","+
       " \"tgl_lahir\": \"1964-02-05\","+
       " \"status_nikah\": \"Menikah\","+
       " \"agama\": \"Islam\","+
       " \"jns_kelamin\": \"Laki - laki\","+
       " \"gol_darah\": \"O\","+
       " \"no_karis\": \"\","+
       " \"no_karpeg\": \"E 744277\","+
       " \"no_taspen\": \"0000038162529\","+
       " \"no_askes\": \"0000038162529\","+
       " \"no_npwp\": \"05.939.719.0-025.000\","+
       " \"data_rekbank\": \"001201152504509\","+
       " \"email\": \"adekamal@gmails.com\","+
       " \"no_tlp_kntr\": \"\","+
       " \"no_tlp_rmh\": \"\","+
       " \"no_hp\": \"081290331864\","+
       " \"no_randis\": \"\","+
       " \"no_kpi\": \"\","+
       " \"no_kta\": \"\","+
       " \"no_label_sec\": \"\","+
       " \"no_ktp\": 0,"+
       " \"foto\": \"201710261543232.jpg\","+
       " \"status\": 1"+
       "} }";
		
		System.out.println(jsonString);
		Map dataMap=(Map) JSON.parse(jsonString);
		try {
			System.out.println(getHash(dataMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//c6591719113c898f8741c6b7cf106f679e306343479fd84cb1e21aeb6b40f115
		//c6591719113c898f8741c6b7cf106f679e306343479fd84cb1e21aeb6b40f115
		//eb4fb6e816af56422c6dfd68435e31953b1a42849f6f16fa482eb594744a67e5
	 }
	 
	public static void main(String[] args) {
		testHash();
	/*	
		File f=null;
		try {
			f=File.createTempFile(TEMP_FILE_PREFIX, "original Name.txt");
			System.out.println("Absolute path "+f.getAbsolutePath());
			
			System.out.println(f.getName().substring(TEMP_FILE_PREFIX.length()+19));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(f!=null && f.exists()) f.delete();
			System.out.println("Temp File Cleared");
		}
		*/
	}
	
	public static void main2(String[] args) {
		try{
			FileInputStream fis=new FileInputStream("test.txt");
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
}

package com.app.module.report;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.functors.ForClosure;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.hibernate.classic.Validatable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.docmgr.model.Folder;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.FolderService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.ACLManager;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.module.basic.LoginManager;
import com.app.module.basic.UserManager;
import com.app.module.document.FolderManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class StatisticManager extends BaseUtil {
	private static Logger log = Logger.getLogger(StatisticManager.class);
	private static String ACL_MODE="PUBLIC";
//	String validPeriod="millennium|century|decade|year|quarter|month|week|day|hour|minute|second|milliseconds|microseconds";
	private static String validPeriod="year|quarter|month|week|day|hour|minute";
	
	public static  List<Map<String, Object>> getUserStatistic(Document passport,Map data)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getUserStatistic", new org.bson.Document("modelClass","Report"));
		String recordPeriod= (String) data.get("recordPeriod");
		if(!validPeriod.contains(recordPeriod)) throw new Exception("error.invalid.recordPeriod");
		if(recordPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String reportPeriod=(String) data.get("reportPeriod");
		if(!validPeriod.contains(reportPeriod)) throw new Exception("error.invalid.reportPeriod");
		if(reportPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String filterParam=constructSQLPeriodFilter("audit_time", (String) data.get("periodValue"), reportPeriod);
		Map filterMap= (Map) data.get("filter");
		if (filterMap!=null && !filterMap.isEmpty()) {
			StringBuffer filterBuff=new StringBuffer("");
			for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				filterBuff.append(constructQuery(null,key,filterMap.get(key)));
			}
			filterParam+=filterBuff.toString();
		}
		String start=(String) data.get("start");
		String pageSize=(String) data.get("pageSize");
		
		String sqlQuery = " select done_by, entity, to_char(date_trunc('"+recordPeriod+"', audit_time),'DD-MM-YYYY') AS period, count(id) as Freq from audit_trail auditTrail "+
				" WHERE 1=1 "+filterParam+
				" group by 1,2,3";
		System.out.println("REPORT QUERY=["+sqlQuery+"]");
		if (!nvl(start)) return DBQueryManager.getPartialList("User Activity Statistic", sqlQuery,toInt(start,defaulStart), toInt(pageSize,ITEM_PER_PAGE), null);
		return DBQueryManager.getList("User Activity Statistic", sqlQuery, null);
	}	
	
	public static  List<Map<String, Object>> getLoginStat(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getLoginStatistic", new org.bson.Document("modelClass","Report"));
		String recordPeriod= (String) data.get("recordPeriod");
		if(!validPeriod.contains(recordPeriod)) throw new Exception("error.invalid.recordPeriod");
		if(recordPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String reportPeriod=(String) data.get("reportPeriod");
		if(!validPeriod.contains(reportPeriod)) throw new Exception("error.invalid.reportPeriod");
		if(reportPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String filterParam=constructSQLPeriodFilter("login_time", (String) data.get("periodValue"), reportPeriod);
		String start=(String) data.get("start");
		String pageSize=(String) data.get("pageSize");
		Map filterMap= (Map) data.get("filter");
		if (filterMap!=null && !filterMap.isEmpty()) {
			StringBuffer filterBuff=new StringBuffer("");
			for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				filterBuff.append(constructQuery(null,key,filterMap.get(key)));
			}
			filterParam+=filterBuff.toString();
		}
		/*
		 select appUser.login_name, appUser.full_name,main.* from 
(SELECT to_char(date_trunc('day', lh.login_time),'DD-MM-YYYY') AS period , lh.user_id , count(lh.*) AS Freq 
FROM login_history lh   WHERE date_part('month', lh.login_time)  >= 10 
GROUP BY 1,2  ORDER BY 1  offset 0 limit 20) main, app_user appUser
where appUser.id=main.user_id  
		 */
		String sqlQuery = " select appUser.login_name, appUser.full_name, main.period, main.freq from "+
				" (SELECT to_char(date_trunc('"+recordPeriod+"', lh.login_time),'DD-MM-YYYY') AS period , lh.user_id , count(*) AS Freq "+
				"FROM login_history lh "+
				" WHERE 1=1 "+filterParam+" GROUP BY 1,2  ORDER BY 1) main , app_user appUser where appUser.id=main.user_id  ";
		
//		String sqlQuery = " SELECT to_char(date_trunc('"+recordPeriod+"', login_time),'DD-MM-YYYY') AS period , appUser.login_name , count(*) AS Freq FROM login_history loginHistory, app_user appUser "+
//				" WHERE appUser.id=loginHistory.user_id "+filterParam+
//				" GROUP BY 1,2  ORDER BY 1 ";
		System.out.println("REPORT QUERY=["+sqlQuery+"]");
		String[] keys=new String[]{"full_name"};
		if (!nvl(start)) return DBQueryManager.decryptList(DBQueryManager.getPartialList("User Login Statistic", sqlQuery,toInt(start,defaulStart), toInt(pageSize,ITEM_PER_PAGE), null),keys);
		return DBQueryManager.decryptList(DBQueryManager.getList("User Login Statistic", sqlQuery, null),keys);
	}
	
	public static  List<Map<String, Object>> getDocumentStatistic(Document passport,Map data)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getDocumentStatistic", new org.bson.Document("modelClass","Report"));
		String recordPeriod= (String) data.get("recordPeriod");
		if(!validPeriod.contains(recordPeriod)) throw new Exception("error.invalid.recordPeriod");
		if(recordPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String reportPeriod=(String) data.get("reportPeriod");
		if(!validPeriod.contains(reportPeriod)) throw new Exception("error.invalid.reportPeriod");
		if(reportPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String filterParam=constructSQLPeriodFilter("created_date", (String) data.get("periodValue"), reportPeriod);
		Map filterMap= (Map) data.get("filter");
		if (filterMap!=null && !filterMap.isEmpty()) {
			StringBuffer filterBuff=new StringBuffer("");
			for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				filterBuff.append(constructQuery(null,key,filterMap.get(key)));
			}
			filterParam+=filterBuff.toString();
		}
		String start=(String) data.get("start");
		String pageSize=(String) data.get("pageSize");
		
		String sqlQuery = " select created_by as createdBy, content_type as contentType, to_char(date_trunc('"+recordPeriod+"', created_date),'DD-MM-YYYY') AS period, count(id) as Count from document doc "+
				" WHERE 1=1 "+filterParam+
				" group by 1,2,3";
		System.out.println("REPORT QUERY=["+sqlQuery+"]");
		String[] keys=new String[]{"createdBy"};
		if (!nvl(start)) return DBQueryManager.decryptList(DBQueryManager.getPartialList("Document Statistic", sqlQuery,toInt(start,defaulStart), toInt(pageSize,ITEM_PER_PAGE), null),keys);
		return DBQueryManager.decryptList(DBQueryManager.getList("Document Statistic", sqlQuery, null),keys);
	}	
	
	
	public static  List<Map<String, Object>> getForumStatistic(Document passport,Map data)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "getForumStatistic", new org.bson.Document("modelClass","Report"));
		String recordPeriod= (String) data.get("recordPeriod");
		if(!validPeriod.contains(recordPeriod)) throw new Exception("error.invalid.recordPeriod");
		if(recordPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String reportPeriod=(String) data.get("reportPeriod");
		if(!validPeriod.contains(reportPeriod)) throw new Exception("error.invalid.reportPeriod");
		if(reportPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String filterParam=constructSQLPeriodFilter("created_date", (String) data.get("periodValue"), reportPeriod);
		Map filterMap= (Map) data.get("filter");
		if (filterMap!=null && !filterMap.isEmpty()) {
			StringBuffer filterBuff=new StringBuffer("");
			for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				filterBuff.append(constructQuery(null,key,filterMap.get(key)));
			}
			filterParam+=filterBuff.toString();
		}
		String start=(String) data.get("start");
		String pageSize=(String) data.get("pageSize");
		
		String sqlQuery = " select created_by as createdBy, content_type as contentType, to_char(date_trunc('"+recordPeriod+"', created_date),'DD-MM-YYYY') AS period, count(t.id) as numberOfTopic from forum f, topic t "+
				" WHERE t.forum=f.id  "+filterParam+
				" group by 1,2,3";
		System.out.println("REPORT QUERY=["+sqlQuery+"]");
		String[] keys=new String[]{"createdBy"};
		if (!nvl(start)) return DBQueryManager.decryptList(DBQueryManager.getPartialList("Forum Statistic", sqlQuery,toInt(start,defaulStart), toInt(pageSize,ITEM_PER_PAGE), null),keys);
		return DBQueryManager.decryptList(DBQueryManager.getList("Forum Statistic", sqlQuery, null),keys);
	}	
	
	/* ===============================+ Dynamic Report =====================================*/
	public static  List<Map<String, Object>> getDynamicReport(Document passport,Map data,String reportName,String sqlReportQuery, String groupBy, String[] encryptedKeys)  throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, reportName, new org.bson.Document("modelClass","Report"));
		String recordPeriod= (String) data.get("recordPeriod");
		if(!validPeriod.contains(recordPeriod)) throw new Exception("error.invalid.recordPeriod");
		if(recordPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String reportPeriod=(String) data.get("reportPeriod");
		if(!validPeriod.contains(reportPeriod)) throw new Exception("error.invalid.reportPeriod");
		if(reportPeriod.contains(";")) throw new Exception("Possible SQL Injection");
		String filterParam=constructSQLPeriodFilter("created_date", (String) data.get("periodValue"), reportPeriod);
		Map filterMap= (Map) data.get("filter");
		if (filterMap!=null && !filterMap.isEmpty()) {
			StringBuffer filterBuff=new StringBuffer("");
			for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				filterBuff.append(constructQuery(null,key,filterMap.get(key)));
			}
			filterParam+=filterBuff.toString();
		}
		String start=(String) data.get("start");			
		String pageSize=(String) data.get("pageSize");
		
		String sqlQuery = sqlReportQuery.replaceAll("${recordPeriod}", recordPeriod) //" select created_by as createdBy, content_type as contentType, to_char(date_trunc('"+recordPeriod+"', created_date),'DD-MM-YYYY') AS period, count(id) as Count from document doc "+
				+ " WHERE 1=1 "+filterParam+
				(nvl(groupBy)?"":" group by "+groupBy+" ");
		System.out.println("REPORT QUERY=["+sqlQuery+"]");
		
		//String[] keys= new String[]{"createdBy"};
		if (!nvl(start)) return DBQueryManager.decryptList(DBQueryManager.getPartialList(reportName, sqlQuery,toInt(start,defaulStart), toInt(pageSize,ITEM_PER_PAGE), null),encryptedKeys);
		return DBQueryManager.decryptList(DBQueryManager.getList(reportName, sqlQuery, null),encryptedKeys);
	}	
	
	
	
/*	private static String constructPeriodFilter(String periodField ,String fromPeriod,String reportPeriod) {
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

	private static String toOpr(String sOperand) {
		if("$EQ".equals(sOperand)) return "=";
		if("$GT".equals(sOperand)) return ">";
		if("$GE".equals(sOperand)) return ">=";
		if("$LT".equals(sOperand)) return "<";
		if("$LE".equals(sOperand)) return "<=";
		else return "";
	}
	*/
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		/*
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("start", "0");
		data.put("recordPeriod", "day");
		data.put("reportPeriod", "month");
	//	data.put("periodValue", "$EQ|0");
	//	data.put("periodValue", "$EQ|11");
		//data.put("periodValue", "$GE|-3");
		//data.put("periodValue", "$BT|10|12");
		data.put("periodValue", "$BT|-6|0");
		
		Map<String,String> filter=new HashMap<String,String>();
		filter.put("auditTrail.done_by", "$EQ|admin");
		//filter.put("auditTrail.done_by","$LK|admin%");
		data.put("filter", filter);
		System.out.println(Utility.debug(data));
		try{
		System.out.println("filter = "+constructSQLPeriodFilter("audit_time",(String) data.get("periodValue"),(String)data.get("reportPeriod")));
		System.out.println("add = "+constructQuery("auditTrail","done_by","$EQ|admin"));
		
		String sqlQuery="select done_by, entity, to_char(date_trunc('week', audit_time),'DD-MM-YYYY') AS period, count(id) as Freq from audit_trail auditTrail  WHERE 1=1  AND audit_time > now() - interval '1 month'  group by 1,2,3";

		System.out.println("COUNT Query =["+DBQueryManager.createCountQuery(sqlQuery)+"]");
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
}


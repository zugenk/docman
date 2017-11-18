package com.app.module.report;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	
	public static  List<Map<String, Object>> getUserStatistic(String perPeriod, String reportIntv)  throws Exception{
		if(!validPeriod.contains(perPeriod)) throw new Exception("error.invalid.perPeriod");
		String rptPeriod=reportIntv;
		if(reportIntv.indexOf(" ")>0){
			rptPeriod=reportIntv.substring(reportIntv.lastIndexOf(' ')+1 );
		}
		if(!validPeriod.contains(rptPeriod)) throw new Exception("error.invalid.reportIntv");
		String sqlQuery = " select done_by, entity,date_trunc('"+perPeriod+"', audit_time) AS period, count(id) as Freq from audit_trail "+
				// "date_trunc('month', audit_time)='2017-11' "+
				" WHERE audit_time > now() - interval '"+reportIntv+"' "+
				" group by 1,2,3";
		System.out.println(sqlQuery);
		return DBQueryManager.getList("User Activity Statistic", sqlQuery, null);
		
	}	
	
	/*
	 select done_by, entity,date_trunc('day', audit_time) AS "Month", count(id) as Freq from audit_trail 
where date_trunc('month', audit_time)='2017-11-01'
group by 1,2,3

	 */
	
	public static  List<Map<String, Object>> getLoginStat(String perPeriod, String reportIntv) throws Exception{ //("day","1 year")
		if(!validPeriod.contains(perPeriod)) throw new Exception("error.invalid.perPeriod");
		String rptPeriod=reportIntv;
		if(reportIntv.indexOf(" ")>0){
			rptPeriod=reportIntv.substring(reportIntv.lastIndexOf(' ')+1 );
		}
		if(!validPeriod.contains(rptPeriod)) throw new Exception("error.invalid.reportIntv");
		String sqlQuery = " SELECT date_trunc('"+perPeriod+"', login_time) AS period , user_id, count(*) AS Freq FROM login_history "+
				" WHERE login_time > now() - interval '"+reportIntv+"' "+
				" GROUP BY 1,2  ORDER BY 1;";
		System.out.println(sqlQuery);
		return DBQueryManager.getList("User Login Statistic", sqlQuery, null); 
		//return list;
	}
	/* 
	 SELECT date_trunc('day', login_time) AS "Month" , user_id, count(*) AS "freq"
FROM login_history
where date_trunc('month', login_time)='2017-11-01'
-- WHERE login_time > now() - interval '1 month' 
GROUP BY 1,2
ORDER BY 1;
	 
	 */
}


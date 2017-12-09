package com.app.module.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.document.FolderManager;
import com.app.module.forum.ForumManager;
import com.app.module.report.StatisticManager;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1/report")
public class ReportController extends BaseUtil{
private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());

/*	@RequestMapping(value = "/activity",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> activity(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestParam(value="perPeriod", defaultValue="day") String perPeriod,
			@RequestParam(value="reportIntv", defaultValue="1 month") String reportIntv,
			@RequestParam(value="start", required=false) String start ) {
		Document response=new Document();
		try {
			log.trace("/v1/report/activity = "+ipassport);			
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result",StatisticManager.getUserStatistic(perPeriod, reportIntv,start));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}*/
	
	@RequestMapping(value = "/activity",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> activity(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/report/activity = "+ipassport);			
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result",StatisticManager.getUserStatistic(iPass,dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
/*	@RequestMapping(value = "/login",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> login(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestParam(value="perPeriod", defaultValue="day") String perPeriod,
			@RequestParam(value="reportIntv", defaultValue="1 month") String reportIntv,
			@RequestParam(value="start", required=false) String start ) { 
		Document response=new Document();
		try {
			log.trace("/v1/report/login = "+ipassport);			
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result",StatisticManager.getLoginStat(perPeriod, reportIntv,start));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
*/	
	@RequestMapping(value = "/login",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> login(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/report/login = "+ipassport);			
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result",StatisticManager.getLoginStat(iPass,dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
}

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
import com.app.module.basic.BookmarkManager;
import com.app.module.basic.LoginManager;
import com.app.module.forum.NotificationManager;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1/notification")
public class NotificationController  extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NotificationController.class);

/*
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",NotificationManager.create(iPass, dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/update",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> update(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap,
			@PathVariable(value="ID") String notificationId) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",NotificationManager.update(iPass, dataMap, notificationId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> delete(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String notificationId) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			NotificationManager.delete(iPass, notificationId);
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	*/
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/"+objId+"/ ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",NotificationManager.detail(iPass, objId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/"+objId+"/",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/read",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> markRead(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/"+objId+"/read ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",NotificationManager.markRead(iPass, objId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/"+objId+"/read",e);
		}
		return reply(response);  
	}
	@RequestMapping(value = "readAll",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> markReadAll(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/readAll ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));			
			BaseUtil.putList(response,"result",NotificationManager.markReadAll(iPass));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/readAll",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "list",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> list(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/list ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", NotificationManager.list(iPass, dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/list",e);
		}
		return reply(response);  
	}
	@RequestMapping(value = "clist",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> complexList(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/clist ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", NotificationManager.complexList(iPass, dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/clist",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "myList",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> myList(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "pageSize", required = false) String pageSize) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/myList ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" My Notification list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", NotificationManager.listByOwner(iPass, start,pageSize));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/myList",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "myCList",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> myComplexList(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "pageSize", required = false) String pageSize) {
		Document response=new Document();
		try {
			log.trace("/v1/notification/myCList ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" My Notification list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", NotificationManager.listComplexByOwner(iPass, start,pageSize));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/notification/myCList",e);
		}
		return reply(response);  
	}
}

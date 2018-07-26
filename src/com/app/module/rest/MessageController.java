package com.app.module.rest;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
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
import com.app.module.forum.MessageManager;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1/message")
public class MessageController  extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MessageController.class);
	/*
	@RequestMapping(value = "tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/message/tree = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.getTree(iPass,null));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/tree",e);
		}
		return reply(response); 
	} */
	
	@RequestMapping(value = "{ID}/tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+startId+"/tree = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.getTree(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+startId+"/tree",e);
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/fullTree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getFullTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+startId+"/fullTree = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.getFullTree(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+startId+"/fullTree",e);
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/downline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getDownline(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId
			) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+startId+"/downline = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.getDownline(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+startId+"/downline",e);
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/upline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getUpline(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+startId+"/upline = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.getUpline(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+startId+"/upline",e);
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/message/create/="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.create(iPass, dataMap));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/create",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/update",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> update(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+objId+"/update/="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.update(iPass, dataMap, objId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+objId+"/update",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> delete(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+objId+"/delete/="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			MessageManager.delete(iPass, objId);
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+objId+"/delete",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> read(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/message/"+objId+"/ ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",MessageManager.read(iPass, objId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/"+objId+"/",e);
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
			log.trace("/v1/message/list="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", MessageManager.list(iPass, dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/list",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "list/{topicId}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> listByTopic(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="topicId") String topicId,
			@RequestParam(value="start",defaultValue="0") String start) {
		Document response=new Document();
		try {
			log.trace("/v1/message/list/"+topicId+"="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", MessageManager.listByTopic(iPass, toLong(topicId),start));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/list/"+topicId,e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "tree/{topicId}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> treeByTopic(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="topicId") String topicId,
			@RequestParam(value="start",defaultValue="0") String start) {
		Document response=new Document();
		try {
			log.trace("/v1/message/list/"+topicId+"="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", MessageManager.treeByTopic(iPass, topicId,start));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/message/list/"+topicId,e);
		}
		return reply(response);  
	}
}

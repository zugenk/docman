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
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.forum.ForumManager;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1/forum")
public class ForumController extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ForumController.class);
	
	@RequestMapping(value = "tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/tree = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.getTree(iPass,null));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+startId+"/tree = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.getTree(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/fullTree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getFullTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+startId+"/fullTree = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.getFullTree(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/downline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getDownline(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId
			) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+startId+"/downline = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.getDownline(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/upline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getUpline(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+startId+"/upline = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.getUpline(iPass,startId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/create = "+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.create(iPass, dataMap));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/update",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> update(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+objId+"/update = "+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.update(iPass, dataMap, objId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> delete(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+objId+"/delete = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			ForumManager.delete(iPass, objId);
			response.put("ipassport",iPass.get("ipassport"));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/"+objId+"/ = "+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",ForumManager.detail(iPass, objId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "list",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> list(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/forum/list = "+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", ForumManager.list(iPass, dataMap));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
}

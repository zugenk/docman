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


import com.app.docmgr.service.StatusService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.basic.LookupManager;
import com.app.module.basic.StatusManager;
import com.simas.webservice.Utility;
 
@Controller
@RequestMapping("/v1/status")
public class StatusController extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StatusController.class);

/*	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			
			
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",StatusManager.create(iPass, dataMap));
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
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			
			
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",StatusManager.update(iPass, dataMap, objId));
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
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			
			response.put("ipassport",iPass.get("ipassport"));
			
//			response.put("result",StatusManager.delete(iPass, objId));
			StatusManager.delete(iPass, objId);
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	*/
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/status/"+objId+"/ ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",StatusManager.detail(iPass, objId));
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
			log.trace("/v1/status/list ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", StatusManager.list(iPass, dataMap));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	
	@RequestMapping(value = "/list/{type}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> listByType(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="type") String type) {
		Document response=new Document();
		try {
			log.trace("/v1/status/list/"+type+" ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", StatusManager.findByType(iPass, type));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{type}/{code}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getByTypeandCode(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="type") String type,
			@PathVariable(value="code") String code) {
		Document response=new Document();
		try {
			log.trace("/v1/status/"+type+"/"+code+" ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",StatusManager.getByTypeAndCode(iPass, type, code));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
}
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

import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Status;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.StatusService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.basic.LookupManager;
import com.simas.webservice.Utility;


@Controller
@RequestMapping("/v1/lookup")
public class LookupController extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LookupController.class);

	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/lookup/create ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LookupManager.create(iPass, dataMap));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/create",e);
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
			log.trace("/v1/lookup/"+objId+"/update ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LookupManager.update(iPass, dataMap, objId));
			return reply(response);  	
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/"+objId+"/update",e);
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
			log.trace("/v1/lookup/"+objId+"/delete ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			LookupManager.delete(iPass, objId);
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/"+objId+"/delete",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/lookup/"+objId+"/ ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LookupManager.detail(iPass, objId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/"+objId+"/",e);
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
			log.trace("/v1/lookup/list ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", LookupManager.list(iPass, dataMap));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/list",e);
		}
		return reply(response);  
	}
	
	
	@RequestMapping(value = "/list/{type}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> listByType(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="type") String type) {
		Document response=new Document();
		try {
			log.trace("/v1/lookup/list/"+type+" ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", LookupManager.findByType(iPass, type));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/list/"+type,e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{type}/{code}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getByTypeAndCode(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="type") String type,
			@PathVariable(value="code") String code) {
		Document response=new Document();
		try {
			log.trace("/v1/lookup/"+type+"/"+code+" ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LookupManager.getByTypeAndCode(iPass, type, code));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/lookup/"+type+"/"+code,e);
		}
		return reply(response);  
	}
			

	
}
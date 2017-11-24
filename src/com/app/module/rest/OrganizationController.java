package com.app.module.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.app.module.basic.OrganizationManager;
import com.app.module.basic.UserManager;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1/organization")
public class OrganizationController  extends BaseUtil{
	private Logger log = Logger.getLogger(OrganizationController.class);

	@RequestMapping(value = "tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/organization/tree ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.getTree(iPass,null));
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
			log.trace("/v1/organization/"+startId+"/tree ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.getTree(iPass,startId));
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
			log.trace("/v1/organization/"+startId+"/fullTree ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.getFullTree(iPass,startId));
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
			log.trace("/v1/organization/"+startId+"/downline ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.getDownline(iPass,startId));
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
			log.trace("/v1/organization/"+startId+"/upline ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.getUpline(iPass,startId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	/* */
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/organization/create/="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.create(iPass, dataMap));
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
			log.trace("/v1/organization/"+objId+"/update ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.update(iPass, dataMap,objId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> addForum(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/organization/"+objId+"/delete ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			OrganizationManager.delete(iPass, objId);
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
			log.trace("/v1/organization/"+objId+"/ ="+ipassport);
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",OrganizationManager.detail(iPass, objId));
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
			log.trace("/v1/organization/list ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", OrganizationManager.list(iPass, dataMap));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
}

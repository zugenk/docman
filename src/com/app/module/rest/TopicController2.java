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
import com.app.module.forum.TopicManager2;

@Controller
@RequestMapping("/v1/topic2")
public class TopicController2 {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TopicController2.class);
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> create(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",TopicManager2.create(iPass, dataMap));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "{ID}/update",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> update(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap,
			@PathVariable(value="ID") String topicId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",TopicManager2.update(iPass, dataMap, topicId));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> delete(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String topicId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
//			response.put("result",TopicManager2.delete(iPass, topicId));
			TopicManager2.delete(iPass, topicId);
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> read(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String topicId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",TopicManager2.read(iPass, topicId));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "list",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> list(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",TopicManager2.list(iPass, dataMap));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
}

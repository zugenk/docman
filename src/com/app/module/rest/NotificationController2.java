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
import com.app.module.forum.NotificationManager2;

@Controller
@RequestMapping("/v1/notification2")
public class NotificationController2 {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NotificationController2.class);

/*
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
			
			response.put("result",NotificationManager2.create(iPass, dataMap));
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
			@PathVariable(value="ID") String notificationId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",NotificationManager2.update(iPass, dataMap, notificationId));
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
			@PathVariable(value="ID") String notificationId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
//			response.put("result",NotificationManager2.delete(iPass, notificationId));
			NotificationManager2.delete(iPass, notificationId);
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
			@PathVariable(value="ID") String notificationId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",NotificationManager2.read(iPass, notificationId));
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
			
			response.put("result",NotificationManager2.list(iPass, dataMap));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
*/
	
	@RequestMapping(value = "myList",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> list(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestParam(value = "start", required = false) String start) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			int startId=0;
			if(start!=null && start!=""){
				try {
					startId=Integer.parseInt(start);
				} catch (Exception e) {
					startId=0;
				}
			}
			response.put("result",NotificationManager2.listByOwner(iPass, startId));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting Bookmark-ListByOwner",e);
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
}

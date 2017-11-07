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
import com.app.module.basic.BookmarkManager;


@Controller
@RequestMapping("/v1/bookmark")
public class BookmarkController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BookmarkController.class);
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> create(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",BookmarkManager.create(iPass, dataMap));
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
			@PathVariable(value="ID") String bookmarkId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",BookmarkManager.update(iPass, dataMap, bookmarkId));
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
			@PathVariable(value="ID") String bookmarkId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			response.put("ipassport",iPass.get("ipassport"));
//			response.put("result",BookmarkManager.delete(iPass, bookmarkId));
			BookmarkManager.delete(iPass, bookmarkId);
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
			@PathVariable(value="ID") String bookmarkId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",BookmarkManager.read(iPass, bookmarkId));
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
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", BookmarkManager.list(iPass, dataMap));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "myList",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> list(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestParam(value = "start", required = false) String start) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			log.debug(" My Bookmark list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", BookmarkManager.listByOwner(iPass,BaseUtil.toInt(start)));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting Bookmark-ListByOwner",e);
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
}
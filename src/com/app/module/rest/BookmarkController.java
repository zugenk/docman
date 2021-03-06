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
import com.simas.webservice.Utility;
import com.app.module.basic.BookmarkManager;


@Controller
@RequestMapping("/v1/bookmark")
public class BookmarkController  extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BookmarkController.class);
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/bookmark/create="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
//			if(!isAdmin(iPass)) return new ResponseEntity<Document>(response,HttpStatus.UNAUTHORIZED);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",BookmarkManager.create(iPass, dataMap));
			return reply(response); 
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/create",e);
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
			log.trace("/v1/bookmark/"+objId+"/update ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",BookmarkManager.update(iPass, dataMap, objId));
			return reply(response); 
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/"+objId+"/update",e);
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
			log.trace("/v1/bookmark/"+objId+"/delete/="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BookmarkManager.delete(iPass, objId);
			return reply(response); 
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/"+objId+"/delete",e);
		}
		return new ResponseEntity<Document>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();//Document response=new Document();
		try {
			log.trace("/v1/bookmark/"+objId+"/="+ipassport); 
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
//			if(!isAdmin(iPass)) return new ResponseEntity<Document>(response,HttpStatus.UNAUTHORIZED);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",BookmarkManager.detail(iPass, objId));
			return reply(response);//return reply(response); 
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/"+objId+"/",e);
		}
		return reply(response);//return reply(response); 
	}
	
	@RequestMapping(value = "list",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> list(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/bookmark/list ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
//			if(!isAdmin(iPass)) return new ResponseEntity<Document>(response,HttpStatus.UNAUTHORIZED);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", BookmarkManager.list(iPass, dataMap));
			return reply(response); 
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/list",e);
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "myList",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> mylist(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "pageSize", required = false) String pageSize) {
		Document response=new Document();
		try {
			log.trace("/v1/bookmark/myList="+ipassport+" category="+category+" start="+start);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" My Bookmark list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", BookmarkManager.listByOwner(iPass,category,orderBy,start,pageSize));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/myList",e);
			//if(unHandled(e))log.error("Error geting Bookmark-ListByOwner",e);
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "myCategories",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> myCategories(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/bookmark/myCategories="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" My Bookmark list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", BookmarkManager.getMyCategories(iPass));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("/v1/bookmark/myCategories",e);
			//if(unHandled(e))log.error("Error geting Bookmark-ListByOwner",e);
		}
		return reply(response); 
	}
}
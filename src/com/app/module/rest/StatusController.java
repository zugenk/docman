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

import com.app.docmgr.model.Status;
import com.app.docmgr.model.User;
import com.app.docmgr.service.StatusService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.basic.UserManager;

@Controller
@RequestMapping("/v1/status")
public class StatusController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StatusController.class);

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
			
			response.put("result",UserManager.create(iPass, dataMap));
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
			@PathVariable(value="ID") String userId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",UserManager.update(iPass, dataMap, userId));
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
			@PathVariable(value="ID") String userId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List<String> roles= (List)iPass.get("roleNames");
			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
//			response.put("result",UserManager.delete(iPass, userId));
			UserManager.delete(iPass, userId);
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
			@PathVariable(value="ID") String userId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
//			List<String> roles= (List)iPass.get("roleNames");
//			if (!roles.contains(BaseUtil.ADMIN_ROLE)) return new ResponseEntity<Map>(response,HttpStatus.UNAUTHORIZED);
			
			response.put("result",UserManager.read(iPass, userId));
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
			
			response.put("result",UserManager.list(iPass, dataMap));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	*/
	
	@RequestMapping(value = "/list/{type}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> listByType(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="type") String type) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			List result=StatusService.getInstance().findbyType(type);
			toDocList(result);
			response.put("result",result);
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{type}/{code}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> list(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="type") String type,
			@PathVariable(value="code") String code) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			response.put("result",toDocument(StatusService.getInstance().getByTypeandCode(type, code)));
			return new ResponseEntity<Map>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
			
	public static Document toDocument(Status obj) {
		Document doc=new Document();
		doc.append("code", obj.getCode());
		doc.append("description", obj.getDescription());
		doc.append("id", obj.getId());
		doc.append("name", obj.getName());
		doc.append("state", obj.getState());
		doc.append("type", obj.getType());
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Status obj = (Status) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
		
	
}
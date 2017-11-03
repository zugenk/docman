package com.app.module.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.module.basic.LoginManager;
import com.app.module.forum.ForumManager;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1")
public class ForumController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "forum/tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				List res=ForumManager.getTree(null);
				System.out.println(Utility.debug(res));
				response.put("result",res);
				
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "forum/{ID}/tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",ForumManager.getTree(startId));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "forum/{ID}/downline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getDownline(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId
			) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",ForumManager.getDownline(startId));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "forum/{ID}/upline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getUpline(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",ForumManager.getUpline(startId));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}


}

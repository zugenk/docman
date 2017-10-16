package com.app.module.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.module.basic.LoginManager;

@Controller
@RequestMapping("/v1")
public class ForumController {
private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
/*	
	@RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "data")
	@ResponseBody
	public ResponseEntity<Data> getData(@RequestHeader(value="User-Agent") String userAgent, @RequestParam(value = "ID", defaultValue = "") String id)    
	{
	//code goes here
	}

	@RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "data")
	@ResponseBody
	public ResponseEntity<Data> getData(HttpServletRequest request, @RequestParam(value = "ID", defaultValue = "") String id)    
	{
	    String userAgent = request.getHeader("user-agent");
	}
* /	
	
	@RequestMapping(value = "forum")
	public @ResponseBody ResponseEntity<Map> login(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass==null) {
				//Not authenticated/failed
				
			}
			
			//return new ResponseEntity<Map>(LoginManager.authenticate(ipassport,basicAuth),HttpStatus.OK);
		} catch (Exception e) {
			Map resp=new HashMap();
			resp.put("errormMessage", e.getMessage());
			return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
		}
	}
	
	*/


}

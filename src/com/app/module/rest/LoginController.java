package com.app.module.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.basic.PassportManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.DbEncryptionUtility;
import com.simas.webservice.Utility;

@Controller
@RequestMapping("/v1")
public class LoginController  extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value = "action/login",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> login(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/action/login = "+itoken+":"+ipassport+":"+basicAuth);
			Document iPass=LoginManager.authenticate(itoken,ipassport,basicAuth); 
			if(iPass==null)	{
				response.put("errorMessage", "error.authentication.failed");
			} else {
				return reply(iPass);
			}
			
		} catch (Exception e) {	
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("action/login",e);
		}
		return reply(response);
	}																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																												
	
	@RequestMapping(value = "utility/encryptDb",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> encryptDb(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/utility/encryptDb = "+itoken+":"+ipassport+":"+basicAuth);
			Document iPass=LoginManager.authenticate(itoken,ipassport,basicAuth); 
			if(iPass==null)	{
				response.put("errorMessage", "error.authentication.failed");
			} else {
				return reply(DbEncryptionUtility.encryptDb(iPass));
			}
			
		} catch (Exception e) {	
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("action/login",e);
		}
		return reply(response);
	}
	
	
}
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

import com.app.module.basic.LoginManager;
import com.app.module.basic.PassportManager;
import com.app.shared.ApplicationFactory;




@Controller
@RequestMapping("/v1")
public class LoginController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value = "action/login")
	public String login(@RequestHeader HttpHeaders httpHeaders,
//			@RequestHeader(value="Accept") String accept,
//			@RequestHeader(value="Accept-Language") String acceptLanguage,
//			@RequestHeader(value="Authorization") String basicAuth,
//			@RequestHeader(value="ipassport") String ipassport,
	
			HttpServletResponse response) {
		
		String ipassport =httpHeaders.getFirst("ipassport");
		log.debug("Try Login REST Api... [");
		Document iPassDoc=null;
		if(ipassport !=null) {
			log.debug("Login with ipassport");
			try {
				iPassDoc=PassportManager.checkPassport(ipassport);
			} catch (Exception e) {
			}
		} 
		if (iPassDoc==null && httpHeaders.getFirst("Authorization")!=null) {
			try {
				log.debug("Login with basic auth =["+httpHeaders.getFirst("Authorization")+"]");
				LoginManager.loginWithBasicAuth(httpHeaders.getFirst("Authorization"));
							
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return null;
	}
	
	
}
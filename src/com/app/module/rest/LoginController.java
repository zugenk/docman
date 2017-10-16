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
	
	@RequestMapping(value = "action/login",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> login( //){
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		Map resp=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport,basicAuth); //(null,"Basic YWRtaW46YWRtaW4="); //
			if(iPass==null)	{
				System.out.println("LOGIN >> FAILED");
				log.debug("Login Failed..");
				resp.put("errorMessage", "error.authentication.failed");
			} else {
				log.debug("HOREEE LOGIN SUCCESS");
				System.out.println("LOGIN >> SUCCESFULL");
				return new ResponseEntity<Map>((Map)iPass,HttpStatus.OK); //resp,HttpStatus.OK); // 
			}
		} catch (Exception e) {	
			resp.put("errorMessage", e.getMessage());
			log.debug("Login Exception..");
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
}
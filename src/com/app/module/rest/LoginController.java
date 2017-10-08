package com.app.module.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.shared.ApplicationFactory;




@Controller
@RequestMapping("/docmgr/v1")
public class LoginController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value = "/login")
	public String login(
			@RequestHeader(value="Accept") String accept,
			@RequestHeader(value="Accept-Language") String acceptLanguage,
			@RequestHeader(value="Authorisation") String bauth,
			@RequestHeader(value="ipassport") String ipass,
			HttpServletResponse response) {
		
		if(ipass !=null) {
			log.debug("Login with ipassport");
			LoginManager.
		} 
		
		return null;
	}
	
	@RequestMapping(value="action/login",method = {RequestMethod.POST})
	public @ResponseBody ResponseEntity doLogin(@RequestBody final Map userMap) {
		log.debug("doLogin!");
		
		Map map = new HashMap();
		// klo ipassport gak ketemu return true, trus lanjut ke bawah (login proses)

		ResponseEntity<Map> responseMapFilter = ControllerMapFilter.getInstance().preFilterV3(userMap, "/login");
		if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
		
		if(ApplicationFactory.isEmpty(challenge) 
				&& ApplicationFactory.isEmpty(sessionCode)){
			map.put("responseCode", "01");
			map.put("responseMessage", "failed");
			return new ResponseEntity<Map>(map, HttpStatus.BAD_REQUEST);
		}
	
		
	}
}
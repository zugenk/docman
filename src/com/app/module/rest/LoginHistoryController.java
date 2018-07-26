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

import com.app.docmgr.model.LoginHistory;
import com.app.docmgr.model.Status;
import com.app.docmgr.service.LoginHistoryService;
import com.app.docmgr.service.StatusService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.basic.LoginHistoryManager;
import com.simas.webservice.Utility;


@Controller
@RequestMapping("/v1/loginHistory")
public class LoginHistoryController extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginHistoryController.class);
/*	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LoginHistoryManager.create(iPass, dataMap));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/update",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> update(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map dataMap,
			@PathVariable(value="ID") String userId) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LoginHistoryManager.update(iPass, dataMap, userId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> delete(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String userId) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
//			response.put("result",LoginHistoryManager.delete(iPass, userId));
			LoginHistoryManager.delete(iPass, userId);
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	*/
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/loginHistory/"+objId+"/ = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LoginHistoryManager.detail(iPass, objId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	
	@RequestMapping(value = "list",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> list(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/loginHistory/list = "+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", LoginHistoryManager.list(iPass, dataMap));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
/*	
	@RequestMapping(value = "/list/{type}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> listByType(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="type") String type) {
		Document response=new Document();
		try {
			log.debug(">>>>>List LoginHistory by type =["+type+"]");
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			List result=LoginHistoryService.getInstance().findbyType(type);
			//System.out.println(Utility.debug(result));
			LoginHistoryManager.toDocList(result);
			BaseUtil.putList(response,"result", result);
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			if(unHandled(e))log.error("Error getting LoginHistory list by type ",e);
			e.printStackTrace();
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{type}/{code}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getByTypeandCode(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="type") String type,
			@PathVariable(value="code") String code) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",LoginHistoryManager.toDocument(LoginHistoryService.getInstance().getByTypeandCode(type, code)));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
			
*/
	
}
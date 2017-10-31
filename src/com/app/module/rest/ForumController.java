package com.app.module.rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.app.docmgr.model.Forum;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.service.ForumService;
import com.app.docmgr.service.LookupService;
import com.app.module.basic.LoginManager;
import com.app.module.forum.ForumManager;

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
				response.put("result",ForumManager.getTree(null));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
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
	
	@RequestMapping(value = "action/add-forum",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> addForum(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map forumMap) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(forumMap,ipassport,basicAuth,"add-forum");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);
	
			String name = (String)forumMap.get("name");
			log.debug("["+sessionId+"] - name: " +name);
			
			Integer forumTypeId = (Integer)forumMap.get("forumTypeId");
			log.debug("["+sessionId+"] - forumTypeId: " +forumTypeId);
			
			if ("".equals(name) || name == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.forumName.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}
			
			Lookup forumTypeObj = null;
			if (forumTypeId == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.forumTypeId.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}else{
	//			forumTypeObj = LookupService.getInstance().getByTypeandCode("forum", forumType);
				forumTypeObj = LookupService.getInstance().get(Long.valueOf(forumTypeId.toString()));
				log.debug("["+sessionId+"] - forumTypeObj: " +forumTypeObj);
				if (forumTypeObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.forumType.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
			}
			
			Boolean isUniqueCode = false;
			String code = "";
			Forum forumObj = null;
			while (!isUniqueCode) {
				code = ForumManager.generateReferralCode("F", name);
				forumObj = ForumService.getInstance().getBy(" and forum.code = '"+code+"' ");
				if (forumObj == null) isUniqueCode = true;
			}
			log.debug("["+sessionId+"] - isUniqueCode : "+isUniqueCode);
			log.debug("["+sessionId+"] - code: " +code);
			
			String createdBy = (String)preFilterMap.get("fullName");
			log.debug("["+sessionId+"] - createdBy: " +createdBy);
			
			/*String icon = (String)forumMap.get("icon");
			log.debug("["+sessionId+"] - icon: " +icon);*/
			
			/*if ("".equals(icon) || icon == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.forumIcon.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}*/
			
			
			if (!ForumManager.addForum(code, "ico.jpg", name, forumTypeObj, null, null, createdBy)){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.addforum.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}
	
			resp.put("responseCode", "00");
			resp.put("responseMessage", "add-forum Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);			
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "action/get-forum/{ID}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getForum(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String forumId) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(new HashMap(),ipassport,basicAuth,"get-forum");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);
	
//			Integer forumId = (Integer)forumMap.get("forumId");
			log.debug("["+sessionId+"] - forumId: " +forumId);
			
			if ("all".equalsIgnoreCase(forumId)){
				List forumList = new LinkedList();
				forumList = ForumService.getInstance().getListAll("", "id asc");
				log.debug("[app-config] - forumList: " +forumList);
				List forumListNew = new LinkedList();
				for (int i=0; i< forumList.size(); i++){
					Map forumData = new HashMap();
					Forum forumObj = (Forum)forumList.get(i);
					forumData.put("id", forumObj.getId());
					forumData.put("code", forumObj.getCode());
					forumData.put("name", forumObj.getName());
					forumData.put("parentForum", forumObj.getParentForum());
					
					forumListNew.add(forumData);
				}
				resp.put("forumList", forumListNew);
			}else{
				Forum forumObj = null;
				if (forumId == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.forumId.failed");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}else{
		//			forumTypeObj = LookupService.getInstance().getByTypeandCode("forum", forumType);
					forumObj = ForumService.getInstance().get(Long.valueOf(forumId));
					log.debug("["+sessionId+"] - forumObj: " +forumObj);
					if (forumObj == null){
						resp.put("responseCode", "01");
						resp.put("errorMessage", "error.forumId.notFound");
						return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
					}
				}
				
				Map forumDetail = new HashMap();
				forumDetail.put("id", forumObj.getId());
				forumDetail.put("code", forumObj.getCode());
				forumDetail.put("name", forumObj.getName());
				forumDetail.put("parentForum", forumObj.getParentForum());
				resp.put("forumDetail", forumDetail);
			}
			
			resp.put("responseCode", "00");
			resp.put("responseMessage", "get-forum Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);				
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "action/del-forum/{ID}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> delForum(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String forumId) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(new HashMap(),ipassport,basicAuth,"del-forum");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);
			
			Forum forumObj = null;
			if (forumId == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.forumId.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}else{
	//			forumTypeObj = LookupService.getInstance().getByTypeandCode("forum", forumType);
				forumObj = ForumService.getInstance().get(Long.valueOf(forumId));
				log.debug("["+sessionId+"] - forumObj: " +forumObj);
				if (forumObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.forumId.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
			}

			Map forumDetail = new HashMap();
			forumDetail.put("id", forumObj.getId());
			forumDetail.put("code", forumObj.getCode());
			forumDetail.put("name", forumObj.getName());
			forumDetail.put("parentForum", forumObj.getParentForum());
			resp.put("forumDetail", forumDetail);
			
			ForumService.getInstance().delete(forumObj);
			resp.put("responseCode", "00");
			resp.put("responseMessage", "del-forum Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);		
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}


}

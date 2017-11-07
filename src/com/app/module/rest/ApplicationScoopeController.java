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
import com.app.module.forum.old.ForumManagerOld;

@Controller
@RequestMapping("/v1")
public class ApplicationScoopeController {
private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "action/forum-config",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> appConfig(){
		Map resp=new HashMap();
		try {
			 
			List forumCatList = new LinkedList();
			forumCatList = LookupService.getInstance().findbyType("forum");
			log.debug("[app-config] - forumCatList: " +forumCatList);
			List forumCatListNew = new LinkedList();
			for (int i=0; i< forumCatList.size(); i++){
				Map forumCatData = new HashMap();
				Lookup forumObj = (Lookup)forumCatList.get(i);
				forumCatData.put("id", forumObj.getId());
				forumCatData.put("type", forumObj.getType());
				forumCatData.put("code", forumObj.getCode());
				forumCatData.put("name", forumObj.getName());
				forumCatData.put("description", forumObj.getDescription());
				forumCatListNew.add(forumCatData);
			}
			resp.put("forumCategoryList", forumCatListNew);
			

			List contentTypeList = new LinkedList();
			contentTypeList = LookupService.getInstance().findbyType("content");
			log.debug("[app-config] - contentTypeList: " +contentTypeList);
			List contentTypeListNew = new LinkedList();
			for (int i=0; i< contentTypeList.size(); i++){
				Map contentTypeData = new HashMap();
				Lookup contentObj = (Lookup)contentTypeList.get(i);
				contentTypeData.put("id", contentObj.getId());
				contentTypeData.put("type", contentObj.getType());
				contentTypeData.put("code", contentObj.getCode());
				contentTypeData.put("name", contentObj.getName());
				contentTypeData.put("description", contentObj.getDescription());
				contentTypeListNew.add(contentTypeData);
			}
			resp.put("contentTypeList", contentTypeListNew);
			/*
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
			resp.put("forumList", forumListNew);*/
			
			
			resp.put("responseCode", "00");
			resp.put("responseMessage", "app-config Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);				
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("[app-config] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}


}

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
import com.app.docmgr.model.Message;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.ForumService;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.MessageService;
import com.app.docmgr.service.TopicService;
import com.app.docmgr.service.UserService;
import com.app.module.basic.LoginManager;
import com.app.module.forum.ForumManager;

@Controller
@RequestMapping("/v1") 
public class MessageController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "action/post-message",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map> postMessage(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@RequestBody final Map messageMap) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(messageMap,ipassport,basicAuth,"post-message");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);
	
			String content = (String)messageMap.get("content");
			log.debug("["+sessionId+"] - content: " +content);
			
			if ("".equals(content) || content == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.content.null");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}

			Integer contentType = (Integer)messageMap.get("contentType");
			log.debug("["+sessionId+"] - contentType: " +contentType);
			
			Integer topicId = (Integer)messageMap.get("topicId");
			log.debug("["+sessionId+"] - topicId: " +topicId);
			
			
			Lookup contentTypeObj = null;
			if (contentType == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.contentType.null");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}else{
	//			contentTypeObj = LookupService.getInstance().getByTypeandCode("content", forumType);
				contentTypeObj = LookupService.getInstance().get(Long.valueOf(contentType.toString()));
				log.debug("["+sessionId+"] - contentTypeObj: " +contentTypeObj);
				if (contentTypeObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.contentType.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
			}
			  
			Topic topicTypeObj = null;
			if (topicId == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.topicId.null");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}else{
	//			contentTypeObj = LookupService.getInstance().getByTypeandCode("topic", forumType);
				topicTypeObj = TopicService.getInstance().get(Long.valueOf(topicId.toString()));
				log.debug("["+sessionId+"] - topicTypeObj: " +topicTypeObj);
				if (topicTypeObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.contentType.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
			}
			
			Long userId = (Long)preFilterMap.get("userId");
			log.debug("["+sessionId+"] - userId: " +userId);

			String loginName = (String)preFilterMap.get("loginName");
			log.debug("["+sessionId+"] - loginName: " +loginName);
			
			String createdBy = (String)preFilterMap.get("fullName");
			log.debug("["+sessionId+"] - createdBy: " +createdBy);
			
			if (!ForumManager.postMessage(content,contentTypeObj,topicTypeObj,loginName,"")){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.postMessage.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}
	
			resp.put("responseCode", "00");
			resp.put("responseMessage", "post-message Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);			
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "action/get-message/{ID}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getMessage(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String messageId) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(new HashMap(),ipassport,basicAuth,"get-message");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);

			Long userId = (Long)preFilterMap.get("userId");
			log.debug("["+sessionId+"] - userId: " +userId);

			String loginName = (String)preFilterMap.get("loginName");
			log.debug("["+sessionId+"] - loginName: " +loginName);
			
//			Integer forumId = (Integer)forumMap.get("forumId");
			log.debug("["+sessionId+"] - messageId: " +messageId);
			
			if ("all".equalsIgnoreCase(messageId) || "".equals(messageId.trim())){
				List messageList = new LinkedList();
				messageList = MessageService.getInstance().getListAll(" and message.createdBy = '"+loginName+"' ", "id asc");
				log.debug("["+sessionId+"] - messageList: " +messageList);
				List messageListNew = new LinkedList();
				for (int i=0; i< messageList.size(); i++){
					Map messageData = new HashMap();
					Map postTypeData = new HashMap();
					Message messageObj = (Message)messageList.get(i);
					messageData.put("id", messageObj.getId());
					messageData.put("code", messageObj.getContent());
						postTypeData.put("id", messageObj.getPostType().getId());
						postTypeData.put("type", messageObj.getPostType().getType());
						postTypeData.put("code", messageObj.getPostType().getCode());
						postTypeData.put("name", messageObj.getPostType().getName());
					messageData.put("postType",postTypeData); 
					if (messageObj.getTopic() != null){
						messageData.put("parentTopicId", messageObj.getTopic().getId());
					}else{
						messageData.put("parentTopicId", null);
					}
					
					messageListNew.add(messageData);
				}
				resp.put("messageList", messageListNew);
			}else{
				Message messageObj = null;
				messageObj = MessageService.getInstance().get(Long.valueOf(messageId));
				log.debug("["+sessionId+"] - messageObj: " +messageObj);
				if (messageObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.messageId.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
				
				Map messageDetail = new HashMap();
				Map postTypeData = new HashMap();
				messageDetail.put("id", messageObj.getId());
				messageDetail.put("code", messageObj.getContent());
					postTypeData.put("id", messageObj.getPostType().getId());
					postTypeData.put("type", messageObj.getPostType().getType());
					postTypeData.put("code", messageObj.getPostType().getCode());
					postTypeData.put("name", messageObj.getPostType().getName());
					messageDetail.put("postType",postTypeData); 
				if (messageObj.getTopic() != null){
					messageDetail.put("parentTopicId", messageObj.getTopic().getId());
				}else{
					messageDetail.put("parentTopicId", null);
				}
				
				resp.put("messageDetail", messageDetail);
			}
			
			resp.put("responseCode", "00");
			resp.put("responseMessage", "get-message Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);				
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "action/del-message/{ID}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> delMessage(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String messageId) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(new HashMap(),ipassport,basicAuth,"del-message");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);

			Long userId = (Long)preFilterMap.get("userId");
			log.debug("["+sessionId+"] - userId: " +userId);
			
//			Integer forumId = (Integer)forumMap.get("forumId");
			log.debug("["+sessionId+"] - messageId: " +messageId);
			
			Message messageObj = null;
			if (messageId == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.messageId.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}else{
				messageObj = MessageService.getInstance().get(Long.valueOf(messageId));
				log.debug("["+sessionId+"] - messageObj: " +messageObj);
				if (messageObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.messageId.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
			}
			 
			Map messageDetail = new HashMap();
			Map postTypeData = new HashMap();
			messageDetail.put("id", messageObj.getId());
			messageDetail.put("code", messageObj.getContent());
				postTypeData.put("id", messageObj.getPostType().getId());
				postTypeData.put("type", messageObj.getPostType().getType());
				postTypeData.put("code", messageObj.getPostType().getCode());
				postTypeData.put("name", messageObj.getPostType().getName());
				messageDetail.put("postType",postTypeData); 
			if (messageObj.getTopic() != null){
				messageDetail.put("parentTopicId", messageObj.getTopic().getId());
			}else{
				messageDetail.put("parentTopicId", null);
			}
			
			resp.put("messageDetail", messageDetail);
			
			MessageService.getInstance().delete(messageObj);
			resp.put("responseCode", "00");
			resp.put("responseMessage", "del-message Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);		
				
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}


	@RequestMapping(value = "action/subscribe-message/{ID}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> susbscribeMessage(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String messageId) {
		Map resp=new HashMap();
		String sessionId = "";
		try {
			ResponseEntity<Map> responseMapFilter = LoginManager.preFilter(new HashMap(),ipassport,basicAuth,"del-message");
			if(responseMapFilter.getStatusCode() != HttpStatus.OK) return responseMapFilter;
			
			Map preFilterMap = (Map) responseMapFilter.getBody();
			sessionId = (String)preFilterMap.get("ipassport");
			log.debug("["+sessionId+"] - ipassport: " +ipassport);

			Long userId = (Long)preFilterMap.get("userId");
			log.debug("["+sessionId+"] - userId: " +userId);
			
//			Integer forumId = (Integer)forumMap.get("forumId");
			log.debug("["+sessionId+"] - messageId: " +messageId);
			
			Message messageObj = null;
			if (messageId == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.messageId.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}else{
				messageObj = MessageService.getInstance().get(Long.valueOf(messageId));
				log.debug("["+sessionId+"] - messageObj: " +messageObj);
				if (messageObj == null){
					resp.put("responseCode", "01");
					resp.put("errorMessage", "error.messageId.notFound");
					return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
				}
			}
			 
			Map messageDetail = new HashMap();
			Map postTypeData = new HashMap();
			messageDetail.put("id", messageObj.getId());
			messageDetail.put("code", messageObj.getContent());
				postTypeData.put("id", messageObj.getPostType().getId());
				postTypeData.put("type", messageObj.getPostType().getType());
				postTypeData.put("code", messageObj.getPostType().getCode());
				postTypeData.put("name", messageObj.getPostType().getName());
				messageDetail.put("postType",postTypeData); 
			if (messageObj.getTopic() != null){
				messageDetail.put("parentTopicId", messageObj.getTopic().getId());
			}else{
				messageDetail.put("parentTopicId", null);
			}
			
			resp.put("messageDetail", messageDetail);
			
			//MessageService.getInstance().delete(messageObj);
			User user = UserService.getInstance().get(userId);
			if (user == null){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.user.notFound");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}
			
			Lookup notificationType = LookupService.getInstance().getByTypeandCode("message", "subscriber");
			if (!ForumManager.subscribe(notificationType, messageObj, user, null)){
				resp.put("responseCode", "01");
				resp.put("errorMessage", "error.subscriber.failed");
				return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
			}
			
			resp.put("responseCode", "00");
			resp.put("responseMessage", "subscribe-message Success");
			return new ResponseEntity<Map>(resp,HttpStatus.OK);		
				
		} catch (Exception e) {	
			e.printStackTrace();
			log.debug("["+sessionId+"] - error : " +e+" : "+e.getMessage());
			resp.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(resp,HttpStatus.BAD_REQUEST);
	}
	
}

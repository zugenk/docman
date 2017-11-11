package com.app.docmgr.action.base;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.upload.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.*;
import com.app.shared.*;

import com.app.docmgr.model.*;
import com.app.docmgr.service.*;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
 */


public class TopicActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.TopicActionBase");	
	public  String _doneBy="guest";
    public  static final String allowableAction="list:detail:create:edit:delete:approve:activate:reject:pending:process:close:cancel:block";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ActionForward forward = null;
    	String action = request.getParameter("action");
    	if (action==null || action.length()==0) action="list";
    	String actionRoot=action;    	
    	if(action.indexOf("_")>0){
    		actionRoot=action.substring(0,action.indexOf("_"));
    	}
    	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
    	response.setHeader("Pragma","no-cache"); //HTTP 1.0
    	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		if (allowableAction.contains(actionRoot)) {
	    	if("list".equalsIgnoreCase(action)){
	    		forward = doList(mapping, form, request, response);
	    	}else if("list_popup".equalsIgnoreCase(action)){
	    		forward = doListPopup(mapping, form, request, response);
	    	}else if("detail".equalsIgnoreCase(action)){
	    		forward = doDetail(mapping, form, request, response);
	    	}else if("detail_popup".equalsIgnoreCase(action)){
	    		forward = doDetailPopup(mapping, form, request, response);
	    	}else if("create".equalsIgnoreCase(action)){
	    		forward = doCreate(mapping, form, request, response);
	    	}else if("create_confirm".equalsIgnoreCase(action)){
	    		forward = doCreateConfirm(mapping, form, request, response);
	    	}else if("create_ok".equalsIgnoreCase(action)){
	    		doCreateOk(mapping, form, request, response);
	    	}else if("edit".equalsIgnoreCase(action)){
	    		forward = doEdit(mapping, form, request, response);
	    	}else if("edit_confirm".equalsIgnoreCase(action)){
	    		forward = doEditConfirm(mapping, form, request, response);
	    	}else if("edit_ok".equalsIgnoreCase(action)){
	    		doEditOk(mapping, form, request, response);
	    	}else if("delete_confirm".equalsIgnoreCase(action)){
	    		forward = doDeleteConfirm(mapping, form, request, response);
	    	}else if("delete_ok".equalsIgnoreCase(action)){
	    		doDeleteOk(mapping, form, request, response);
	    	}else if("submit_confirm".equalsIgnoreCase(action)){
	    		forward = doSubmitConfirm(mapping, form, request, response);
	    	}else if("submit_ok".equalsIgnoreCase(action)){
	    		doSubmitOk(mapping, form, request, response);
	    	}else if("approve_confirm".equalsIgnoreCase(action)){
	    		forward = doApproveConfirm(mapping, form, request, response);
	    	}else if("approve_ok".equalsIgnoreCase(action)){
	    		doApproveOk(mapping, form, request, response);
	    	}else if("reject_confirm".equalsIgnoreCase(action)){
	    		forward = doRejectConfirm(mapping, form, request, response);
	    	}else if("reject_ok".equalsIgnoreCase(action)){
	    		doRejectOk(mapping, form, request, response);
	    	}else if("pending_confirm".equalsIgnoreCase(action)){
	    		forward = doPendingConfirm(mapping, form, request, response);
	    	}else if("pending_ok".equalsIgnoreCase(action)){
	    		doPendingOk(mapping, form, request, response);
	    	}else if("process_confirm".equalsIgnoreCase(action)){
	    		forward = doProcessConfirm(mapping, form, request, response);
	    	}else if("process_ok".equalsIgnoreCase(action)){
	    		doProcessOk(mapping, form, request, response);
	    	}else if("activate_confirm".equalsIgnoreCase(action)){
	    		forward = doActivateConfirm(mapping, form, request, response);
	    	}else if("activate_ok".equalsIgnoreCase(action)){
	    		doActivateOk(mapping, form, request, response);
	    	}else if("close_confirm".equalsIgnoreCase(action)){
	    		forward = doCloseConfirm(mapping, form, request, response);
	    	}else if("close_ok".equalsIgnoreCase(action)){
	    		doCloseOk(mapping, form, request, response);
	    	}else if("archive_confirm".equalsIgnoreCase(action)){
	    		forward = doArchiveConfirm(mapping, form, request, response);
	    	}else if("archive_ok".equalsIgnoreCase(action)){
	    		doArchiveOk(mapping, form, request, response);
	    	}else if("remove_confirm".equalsIgnoreCase(action)){
	    		forward = doRemoveConfirm(mapping, form, request, response);
	    	}else if("remove_ok".equalsIgnoreCase(action)){
	    		doRemoveOk(mapping, form, request, response);
	    	}else if("block_confirm".equalsIgnoreCase(action)){
	    		forward = doBlockConfirm(mapping, form, request, response);
	    	}else if("block_ok".equalsIgnoreCase(action)){
	    		doBlockOk(mapping, form, request, response);
	    	}else if("cancel_confirm".equalsIgnoreCase(action)){
	    		forward = doCancelConfirm(mapping, form, request, response);
	    	}else if("cancel_ok".equalsIgnoreCase(action)){
	    		doCancelOk(mapping, form, request, response);
			}
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}

		return forward;
    }	    
    
    public ActionForward doList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	
    	request.getSession().removeAttribute("topic");
		
		String topic_filter = request.getParameter("topic_filter");
		//this for orderBy field ASC/DESC
		String topic_fieldOrder = request.getParameter("topic_fieldOrder");
		String topic_orderType = request.getParameter("topic_orderType");
		
		if(topic_fieldOrder == null || topic_fieldOrder.length() == 0) topic_fieldOrder = null;
		if(topic_orderType == null || topic_orderType.length() == 0) topic_orderType = null;
		request.getSession().setAttribute("topic_fieldOrder", topic_fieldOrder==null?"":topic_fieldOrder);	 
		request.getSession().setAttribute("topic_orderType", topic_orderType==null?"":topic_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Topic'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.ForumService forumService = com.app.docmgr.service.ForumService.getInstance();
			List forumList = forumService.getList(null, null);
			request.setAttribute("forumList", forumList);
		}catch(Exception ex){
		
		}
		StringBuffer topic_filterSb = new StringBuffer();
		String param_topic_code_filter = "";
		if(request.getParameter("topic_code_filter")!=null){
			param_topic_code_filter = request.getParameter("topic_code_filter");
			if(param_topic_code_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.code like '%"+param_topic_code_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_code_filter", param_topic_code_filter);
		String param_topic_icon_filter = "";
		if(request.getParameter("topic_icon_filter")!=null){
			param_topic_icon_filter = request.getParameter("topic_icon_filter");
			if(param_topic_icon_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.icon like '%"+param_topic_icon_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_icon_filter", param_topic_icon_filter);
		String param_topic_name_filter = "";
		if(request.getParameter("topic_name_filter")!=null){
			param_topic_name_filter = request.getParameter("topic_name_filter");
			if(param_topic_name_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.name like '%"+param_topic_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_name_filter", param_topic_name_filter);
		String param_topic_description_filter = "";
		if(request.getParameter("topic_description_filter")!=null){
			param_topic_description_filter = request.getParameter("topic_description_filter");
			if(param_topic_description_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.description like '%"+param_topic_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_description_filter", param_topic_description_filter);
		String param_topic_createdDate_filter_start = "";
		if(request.getParameter("topic_createdDate_filter_start")!=null){
			param_topic_createdDate_filter_start = request.getParameter("topic_createdDate_filter_start");
			if(param_topic_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_topic_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_topic_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_topic_createdDate_filter_start));
					String param_topic_createdDate_filter_start_cal_val = param_topic_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_topic_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_topic_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					topic_filterSb.append("  AND topic.createdDate >= '"+param_topic_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("topic_createdDate_filter_start", param_topic_createdDate_filter_start);

		String param_topic_createdDate_filter_end = "";
		if(request.getParameter("topic_createdDate_filter_end")!=null){
			param_topic_createdDate_filter_end = request.getParameter("topic_createdDate_filter_end");
			if(param_topic_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_topic_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_topic_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_topic_createdDate_filter_end));
					String param_topic_createdDate_filter_end_cal_val = param_topic_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_topic_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_topic_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					topic_filterSb.append("  AND topic.createdDate  <= '"+param_topic_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("topic_createdDate_filter_end", param_topic_createdDate_filter_end);

		String param_topic_createdBy_filter = "";
		if(request.getParameter("topic_createdBy_filter")!=null){
			param_topic_createdBy_filter = request.getParameter("topic_createdBy_filter");
			if(param_topic_createdBy_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.createdBy like '%"+param_topic_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_createdBy_filter", param_topic_createdBy_filter);
		String param_topic_lastUpdatedDate_filter_start = "";
		if(request.getParameter("topic_lastUpdatedDate_filter_start")!=null){
			param_topic_lastUpdatedDate_filter_start = request.getParameter("topic_lastUpdatedDate_filter_start");
			if(param_topic_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_topic_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_topic_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_topic_lastUpdatedDate_filter_start));
					String param_topic_lastUpdatedDate_filter_start_cal_val = param_topic_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_topic_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_topic_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					topic_filterSb.append("  AND topic.lastUpdatedDate >= '"+param_topic_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("topic_lastUpdatedDate_filter_start", param_topic_lastUpdatedDate_filter_start);

		String param_topic_lastUpdatedDate_filter_end = "";
		if(request.getParameter("topic_lastUpdatedDate_filter_end")!=null){
			param_topic_lastUpdatedDate_filter_end = request.getParameter("topic_lastUpdatedDate_filter_end");
			if(param_topic_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_topic_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_topic_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_topic_lastUpdatedDate_filter_end));
					String param_topic_lastUpdatedDate_filter_end_cal_val = param_topic_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_topic_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_topic_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					topic_filterSb.append("  AND topic.lastUpdatedDate  <= '"+param_topic_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("topic_lastUpdatedDate_filter_end", param_topic_lastUpdatedDate_filter_end);

		String param_topic_lastUpdatedBy_filter = "";
		if(request.getParameter("topic_lastUpdatedBy_filter")!=null){
			param_topic_lastUpdatedBy_filter = request.getParameter("topic_lastUpdatedBy_filter");
			if(param_topic_lastUpdatedBy_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.lastUpdatedBy like '%"+param_topic_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_lastUpdatedBy_filter", param_topic_lastUpdatedBy_filter);
		String param_topic_filterCode_filter = "";
		if(request.getParameter("topic_filterCode_filter")!=null){
			param_topic_filterCode_filter = request.getParameter("topic_filterCode_filter");
			if(param_topic_filterCode_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.filterCode like '%"+param_topic_filterCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("topic_filterCode_filter", param_topic_filterCode_filter);
		String param_topic_status_filter = "";
		if(request.getParameter("topic_status_filter")!=null){
			param_topic_status_filter = request.getParameter("topic_status_filter");
			if(param_topic_status_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.status = '"+param_topic_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("topic_status_filter", param_topic_status_filter);
		String param_topic_forum_filter = "";
		if(request.getParameter("topic_forum_filter")!=null){
			param_topic_forum_filter = request.getParameter("topic_forum_filter");
			if(param_topic_forum_filter.length() > 0 ){				
				topic_filterSb.append("  AND topic.forum = '"+param_topic_forum_filter+"' ");
			}
		}		
		request.getSession().setAttribute("topic_forum_filter", param_topic_forum_filter);
		
		if(topic_fieldOrder!=null && topic_orderType != null )topic_filterSb.append(" ORDER BY "+topic_fieldOrder+" "+topic_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		TopicService topicService = TopicService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList topicList = topicService.getPartialList(topic_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, topicList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, topicList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("topicList", topicList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("topic");
		
		//this for list popup n add to textbox in parent
		String parentForm = request.getParameter("parentForm");
		if(parentForm == null) parentForm = "";
		request.setAttribute("parentForm", parentForm);
		String parentField = request.getParameter("parentField");
		if(parentField == null) parentField = "";
		request.setAttribute("parentField", parentField);
		String parentFilterField = request.getParameter("parentFilterField");
		String parentFilterValue = request.getParameter("parentFilterValue");
		String parentFilter = "";
		if(parentFilterField!=null && parentFilterValue!=null){
			parentFilter = " AND "+parentFilterField+" = '"+parentFilterValue+"' ";
		}


    	ActionForward forward = null;
    	try{
    		TopicService topicService = TopicService.getInstance();
    		List topicList = topicService.getList(parentFilter , null);
    		request.setAttribute("topicList", topicList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do");
    			return null;
    		}
    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("detail");
    	}catch(Exception ex){
    		forward = mapping.findForward("list");
    		ex.printStackTrace();
    	}
    	
    	return forward;
    }


    public ActionForward doDetailPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do");
    			return null;
    		}
    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		
    		forward = mapping.findForward("detail_popup");
    	}catch(Exception ex){
    		forward = mapping.findForward("list");
    		ex.printStackTrace();
    	}
    	
    	return forward;
    }


    public ActionForward doCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null) topic = new Topic();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Topic'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.ForumService forumService = com.app.docmgr.service.ForumService.getInstance();
			List forumList = forumService.getList(null, null);
			request.setAttribute("forumList", forumList);

			UserService userService = UserService.getInstance();
			List userSetList = userService.getList(null, null);
			request.setAttribute("userSetList", userSetList);

			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			
    		request.getSession().setAttribute("topic", topic);
    		forward = mapping.findForward("create");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return forward;
    }



    public ActionForward doCreateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		ActionErrors errors = new ActionErrors();
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, topic, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Topic'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.ForumService forumService = com.app.docmgr.service.ForumService.getInstance();
			List forumList = forumService.getList(null, null);
			request.setAttribute("forumList", forumList);
			UserService userService = UserService.getInstance();
			List userSetList = userService.getList(null, null);
			request.setAttribute("userSetList", userSetList);
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);
			
	
    		if(errors.isEmpty()){
    			forward = mapping.findForward("create_confirm");
    		}else{	
    			saveErrors(request, errors);
    			forward = mapping.findForward("create");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return forward;
    }

    public void doCreateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","new"));
			topic.setLastUpdatedDate(new Date());
			topic.setCreatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
			topic.setCreatedBy(_doneBy);
    		TopicService.getInstance().add(topic);
    		request.getSession().removeAttribute("topic");
    		response.sendRedirect("topic.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Topic'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.ForumService forumService = com.app.docmgr.service.ForumService.getInstance();
			List forumList = forumService.getList(null, null);
			request.setAttribute("forumList", forumList);
			UserService userService = UserService.getInstance();
			List userSetList = userService.getList(null, null);
			request.setAttribute("userSetList", userSetList);
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("edit");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return forward;
    }

    public ActionForward doEditConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		ActionErrors errors = new ActionErrors();
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, topic, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Topic'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.ForumService forumService = com.app.docmgr.service.ForumService.getInstance();
			List forumList = forumService.getList(null, null);
			request.setAttribute("forumList", forumList);

			UserService userService = UserService.getInstance();
			List userSetList = userService.getList(null, null);
			request.setAttribute("userSetList", userSetList);
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			
    		if(errors.isEmpty()){
    			forward = mapping.findForward("edit_confirm");
    		}else{	
    			saveErrors(request, errors);
    			forward = mapping.findForward("edit");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	return forward;
    }

    public void doEditOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=edit_confirm");
    		}
    		
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		request.getSession().removeAttribute("topic");
    		response.sendRedirect("topic.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=delete_confirm");
    		}
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","deleted"));
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("topic.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=submit_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","submitted"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=approve_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","approved"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=reject_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","rejected"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=pending_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","pending"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=process_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","processed"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("activate_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=activate_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","activated"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=close_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","closed"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=archive_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","archived"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=remove_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","removed"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("block_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=block_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","blocked"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if (topic == null){
	    		topic = TopicService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("topic", topic);
	    	}
    		if(topic == null){
    			response.sendRedirect("topic.do?action=detail");
    			return null;
    		}
    		    		
			Set userSet = topic.getSubscribers();			
			if(userSet == null) userSet = new HashSet();
			request.setAttribute("userSet", userSet);			

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("topic.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Topic topic = (Topic) request.getSession().getAttribute("topic");
    		if(topic == null){
    			response.sendRedirect("topic.do?action=cancel_confirm");
    		}
    		topic.setStatus(StatusService.getInstance().getByTypeandCode("Topic","cancelled"));
			topic.setLastUpdatedDate(new Date());
			topic.setLastUpdatedBy(_doneBy);
    		TopicService.getInstance().update(topic);
    		response.sendRedirect("topic.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("topic.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Topic topic, ActionErrors errors){
    	try{    		
			String code = request.getParameter("code");
			topic.setCode(code);
			String icon = request.getParameter("icon");
			topic.setIcon(icon);
			String name = request.getParameter("name");
			topic.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("topic.name", new ActionError("error.topic.name"));
			}
			String description = request.getParameter("description");
			topic.setDescription(description);
			String numberOfLike = request.getParameter("numberOfLike");
			try{
				topic.setNumberOfLike(new java.lang.Integer(numberOfLike));
			}catch(Exception ex){}
			String numberOfPost = request.getParameter("numberOfPost");
			try{
				topic.setNumberOfPost(new java.lang.Integer(numberOfPost));
			}catch(Exception ex){}
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				topic.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					topic.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("topic.createdDate", new ActionError("error.topic.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			topic.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("topic.createdBy", new ActionError("error.topic.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				topic.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					topic.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			topic.setLastUpdatedBy(lastUpdatedBy);
*/ 			String filterCode = request.getParameter("filterCode");
			topic.setFilterCode(filterCode);

/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					topic.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					topic.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("topic.status", new ActionError("error.topic.status"));
			}
*/ 			com.app.docmgr.model.Forum  forumObj =null;
			com.app.docmgr.service.ForumService forumService = com.app.docmgr.service.ForumService.getInstance();
			try{
				String forumStr = request.getParameter("forum");
				
				if(forumStr == null || forumStr.trim().length() == 0 ){
					topic.setForum(null);
				}else{			
					forumObj = forumService.get(new Long(forumStr));
					topic.setForum(forumObj);
				}
			}catch(Exception ex){}	
			if(forumObj==null){
				errors.add("topic.forum", new ActionError("error.topic.forum"));
			}

			Set userSet = new HashSet();
			String[] _selectedUser=request.getParameterValues("selected_user");
			if (_selectedUser!=null && _selectedUser.length>0) {
				UserService userService = UserService.getInstance();
				Set _orgUserSet = topic.getSubscribers();
				if (_orgUserSet==null)_orgUserSet=new HashSet();
				User item=null;
				boolean found=false;
				long itemId =0;
				for (int i = 0; i < _selectedUser.length; i++) {
					itemId = Long.parseLong(_selectedUser[i]);
					found=false;
					try {
						for (Iterator iter = _orgUserSet.iterator(); iter.hasNext()&& !found;) {
							item = (User) iter.next();
							if (itemId==item.getId()){
								found=true;
								userSet.add(item);
							}
						}
						if (!found) userSet.add(userService.get(itemId));
					} catch (Exception e) {
					}
				}
			}
			topic.setSubscribers(userSet);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

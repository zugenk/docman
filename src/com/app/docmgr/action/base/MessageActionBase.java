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
 * @createDate 05-11-2017 15:05:21
 */


public class MessageActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.MessageActionBase");	
	public  String _doneBy="guest";
    public  static final String allowableAction="list:detail:create:edit:delete:approve:reject:pending:process:close:cancel";
	
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
    	
    	request.getSession().removeAttribute("message");
		
		String message_filter = request.getParameter("message_filter");
		//this for orderBy field ASC/DESC
		String message_fieldOrder = request.getParameter("message_fieldOrder");
		String message_orderType = request.getParameter("message_orderType");
		
		if(message_fieldOrder == null || message_fieldOrder.length() == 0) message_fieldOrder = null;
		if(message_orderType == null || message_orderType.length() == 0) message_orderType = null;
		request.getSession().setAttribute("message_fieldOrder", message_fieldOrder==null?"":message_fieldOrder);	 
		request.getSession().setAttribute("message_orderType", message_orderType==null?"":message_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService postTypeService = com.app.docmgr.service.LookupService.getInstance();
			List postTypeList = postTypeService.getList("  and lookup.type='postType'  ", null);
			request.setAttribute("postTypeList", postTypeList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Message'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.TopicService topicService = com.app.docmgr.service.TopicService.getInstance();
			List topicList = topicService.getList(null, null);
			request.setAttribute("topicList", topicList);
			com.app.docmgr.service.MessageService parentService = com.app.docmgr.service.MessageService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
		}catch(Exception ex){
		
		}
		StringBuffer message_filterSb = new StringBuffer();
		String param_message_content_filter = "";
		if(request.getParameter("message_content_filter")!=null){
			param_message_content_filter = request.getParameter("message_content_filter");
			if(param_message_content_filter.length() > 0 ){				
				message_filterSb.append("  AND message.content like '%"+param_message_content_filter+"%' ");
			}
		}
		request.getSession().setAttribute("message_content_filter", param_message_content_filter);
		String param_message_createdDate_filter_start = "";
		if(request.getParameter("message_createdDate_filter_start")!=null){
			param_message_createdDate_filter_start = request.getParameter("message_createdDate_filter_start");
			if(param_message_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_message_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_message_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_message_createdDate_filter_start));
					String param_message_createdDate_filter_start_cal_val = param_message_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_message_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_message_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					message_filterSb.append("  AND message.createdDate >= '"+param_message_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("message_createdDate_filter_start", param_message_createdDate_filter_start);

		String param_message_createdDate_filter_end = "";
		if(request.getParameter("message_createdDate_filter_end")!=null){
			param_message_createdDate_filter_end = request.getParameter("message_createdDate_filter_end");
			if(param_message_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_message_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_message_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_message_createdDate_filter_end));
					String param_message_createdDate_filter_end_cal_val = param_message_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_message_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_message_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					message_filterSb.append("  AND message.createdDate  <= '"+param_message_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("message_createdDate_filter_end", param_message_createdDate_filter_end);

		String param_message_createdBy_filter = "";
		if(request.getParameter("message_createdBy_filter")!=null){
			param_message_createdBy_filter = request.getParameter("message_createdBy_filter");
			if(param_message_createdBy_filter.length() > 0 ){				
				message_filterSb.append("  AND message.createdBy like '%"+param_message_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("message_createdBy_filter", param_message_createdBy_filter);
		String param_message_lastUpdatedDate_filter_start = "";
		if(request.getParameter("message_lastUpdatedDate_filter_start")!=null){
			param_message_lastUpdatedDate_filter_start = request.getParameter("message_lastUpdatedDate_filter_start");
			if(param_message_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_message_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_message_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_message_lastUpdatedDate_filter_start));
					String param_message_lastUpdatedDate_filter_start_cal_val = param_message_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_message_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_message_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					message_filterSb.append("  AND message.lastUpdatedDate >= '"+param_message_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("message_lastUpdatedDate_filter_start", param_message_lastUpdatedDate_filter_start);

		String param_message_lastUpdatedDate_filter_end = "";
		if(request.getParameter("message_lastUpdatedDate_filter_end")!=null){
			param_message_lastUpdatedDate_filter_end = request.getParameter("message_lastUpdatedDate_filter_end");
			if(param_message_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_message_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_message_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_message_lastUpdatedDate_filter_end));
					String param_message_lastUpdatedDate_filter_end_cal_val = param_message_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_message_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_message_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					message_filterSb.append("  AND message.lastUpdatedDate  <= '"+param_message_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("message_lastUpdatedDate_filter_end", param_message_lastUpdatedDate_filter_end);

		String param_message_lastUpdatedBy_filter = "";
		if(request.getParameter("message_lastUpdatedBy_filter")!=null){
			param_message_lastUpdatedBy_filter = request.getParameter("message_lastUpdatedBy_filter");
			if(param_message_lastUpdatedBy_filter.length() > 0 ){				
				message_filterSb.append("  AND message.lastUpdatedBy like '%"+param_message_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("message_lastUpdatedBy_filter", param_message_lastUpdatedBy_filter);
		String param_message_filterCode_filter = "";
		if(request.getParameter("message_filterCode_filter")!=null){
			param_message_filterCode_filter = request.getParameter("message_filterCode_filter");
			if(param_message_filterCode_filter.length() > 0 ){				
				message_filterSb.append("  AND message.filterCode like '%"+param_message_filterCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("message_filterCode_filter", param_message_filterCode_filter);
		String param_message_postType_filter = "";
		if(request.getParameter("message_postType_filter")!=null){
			param_message_postType_filter = request.getParameter("message_postType_filter");
			if(param_message_postType_filter.length() > 0 ){				
				message_filterSb.append("  AND message.postType = '"+param_message_postType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("message_postType_filter", param_message_postType_filter);
		String param_message_status_filter = "";
		if(request.getParameter("message_status_filter")!=null){
			param_message_status_filter = request.getParameter("message_status_filter");
			if(param_message_status_filter.length() > 0 ){				
				message_filterSb.append("  AND message.status = '"+param_message_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("message_status_filter", param_message_status_filter);
		String param_message_topic_filter = "";
		if(request.getParameter("message_topic_filter")!=null){
			param_message_topic_filter = request.getParameter("message_topic_filter");
			if(param_message_topic_filter.length() > 0 ){				
				message_filterSb.append("  AND message.topic = '"+param_message_topic_filter+"' ");
			}
		}		
		request.getSession().setAttribute("message_topic_filter", param_message_topic_filter);
		String param_message_parent_filter = "";
		if(request.getParameter("message_parent_filter")!=null){
			param_message_parent_filter = request.getParameter("message_parent_filter");
			if(param_message_parent_filter.length() > 0 ){				
				message_filterSb.append("  AND message.parent = '"+param_message_parent_filter+"' ");
			}
		}		
		request.getSession().setAttribute("message_parent_filter", param_message_parent_filter);
		
		if(message_fieldOrder!=null && message_orderType != null )message_filterSb.append(" ORDER BY "+message_fieldOrder+" "+message_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		MessageService messageService = MessageService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList messageList = messageService.getPartialList(message_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, messageList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, messageList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("messageList", messageList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("message");
		
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
    		MessageService messageService = MessageService.getInstance();
    		List messageList = messageService.getList(parentFilter , null);
    		request.setAttribute("messageList", messageList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do");
    			return null;
    		}
    		

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
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do");
    			return null;
    		}
    		

    		
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
    		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null) message = new Message();
    		
			com.app.docmgr.service.LookupService postTypeService = com.app.docmgr.service.LookupService.getInstance();
			List postTypeList = postTypeService.getList("  and lookup.type='postType'  ", null);
			request.setAttribute("postTypeList", postTypeList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Message'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.TopicService topicService = com.app.docmgr.service.TopicService.getInstance();
			List topicList = topicService.getList(null, null);
			request.setAttribute("topicList", topicList);
			com.app.docmgr.service.MessageService parentService = com.app.docmgr.service.MessageService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

    		request.getSession().setAttribute("message", message);
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
    		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, message, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService postTypeService = com.app.docmgr.service.LookupService.getInstance();
			List postTypeList = postTypeService.getList("  and lookup.type='postType'  ", null);
			request.setAttribute("postTypeList", postTypeList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Message'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.TopicService topicService = com.app.docmgr.service.TopicService.getInstance();
			List topicList = topicService.getList(null, null);
			request.setAttribute("topicList", topicList);
			com.app.docmgr.service.MessageService parentService = com.app.docmgr.service.MessageService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
	
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
    		Message message = (Message) request.getSession().getAttribute("message");
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","new"));
			message.setLastUpdatedDate(new Date());
			message.setCreatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
			message.setCreatedBy(_doneBy);
    		MessageService.getInstance().add(message);
    		request.getSession().removeAttribute("message");
    		response.sendRedirect("message.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService postTypeService = com.app.docmgr.service.LookupService.getInstance();
			List postTypeList = postTypeService.getList("  and lookup.type='postType'  ", null);
			request.setAttribute("postTypeList", postTypeList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Message'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.TopicService topicService = com.app.docmgr.service.TopicService.getInstance();
			List topicList = topicService.getList(null, null);
			request.setAttribute("topicList", topicList);
			com.app.docmgr.service.MessageService parentService = com.app.docmgr.service.MessageService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

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
    		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, message, errors);
    		
			com.app.docmgr.service.LookupService postTypeService = com.app.docmgr.service.LookupService.getInstance();
			List postTypeList = postTypeService.getList("  and lookup.type='postType'  ", null);
			request.setAttribute("postTypeList", postTypeList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Message'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.TopicService topicService = com.app.docmgr.service.TopicService.getInstance();
			List topicList = topicService.getList(null, null);
			request.setAttribute("topicList", topicList);
			com.app.docmgr.service.MessageService parentService = com.app.docmgr.service.MessageService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

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
    		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=edit_confirm");
    		}
    		
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		request.getSession().removeAttribute("message");
    		response.sendRedirect("message.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=delete_confirm");
    		}
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","deleted"));
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("message.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=submit_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","submitted"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=approve_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","approved"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=reject_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","rejected"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=pending_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","pending"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=process_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","processed"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=close_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","closed"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=archive_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","archived"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=remove_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","removed"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Message message = (Message) request.getSession().getAttribute("message");
    		if (message == null){
	    		message = MessageService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("message", message);
	    	}
    		if(message == null){
    			response.sendRedirect("message.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("message.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Message message = (Message) request.getSession().getAttribute("message");
    		if(message == null){
    			response.sendRedirect("message.do?action=cancel_confirm");
    		}
    		message.setStatus(StatusService.getInstance().getByTypeandCode("Message","cancelled"));
			message.setLastUpdatedDate(new Date());
			message.setLastUpdatedBy(_doneBy);
    		MessageService.getInstance().update(message);
    		response.sendRedirect("message.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("message.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Message message, ActionErrors errors){
    	try{    		
			String content = request.getParameter("content");
			message.setContent(content);
			if(content==null || content.trim().length() == 0 ){
				errors.add("message.content", new ActionError("error.message.content"));
			}
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				message.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					message.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("message.createdDate", new ActionError("error.message.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			message.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("message.createdBy", new ActionError("error.message.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				message.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					message.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			message.setLastUpdatedBy(lastUpdatedBy);
*/ 			String filterCode = request.getParameter("filterCode");
			message.setFilterCode(filterCode);

			com.app.docmgr.model.Lookup  postTypeObj =null;
			com.app.docmgr.service.LookupService postTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String postTypeStr = request.getParameter("postType");
				
				if(postTypeStr == null || postTypeStr.trim().length() == 0 ){
					message.setPostType(null);
				}else{			
					postTypeObj = postTypeService.get(new Long(postTypeStr));
					message.setPostType(postTypeObj);
				}
			}catch(Exception ex){}	
			if(postTypeObj==null){
				errors.add("message.postType", new ActionError("error.message.postType"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					message.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					message.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("message.status", new ActionError("error.message.status"));
			}
*/ 			com.app.docmgr.model.Topic  topicObj =null;
			com.app.docmgr.service.TopicService topicService = com.app.docmgr.service.TopicService.getInstance();
			try{
				String topicStr = request.getParameter("topic");
				
				if(topicStr == null || topicStr.trim().length() == 0 ){
					message.setTopic(null);
				}else{			
					topicObj = topicService.get(new Long(topicStr));
					message.setTopic(topicObj);
				}
			}catch(Exception ex){}	
			if(topicObj==null){
				errors.add("message.topic", new ActionError("error.message.topic"));
			}
			com.app.docmgr.model.Message  parentObj =null;
			com.app.docmgr.service.MessageService parentService = com.app.docmgr.service.MessageService.getInstance();
			try{
				String parentStr = request.getParameter("parent");
				
				if(parentStr == null || parentStr.trim().length() == 0 ){
					message.setParent(null);
				}else{			
					parentObj = parentService.get(new Long(parentStr));
					message.setParent(parentObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

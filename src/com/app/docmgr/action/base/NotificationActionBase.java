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


public class NotificationActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.NotificationActionBase");	
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
			}
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}

		return forward;
    }	    
    
    public ActionForward doList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	
    	request.getSession().removeAttribute("notification");
		
		String notification_filter = request.getParameter("notification_filter");
		//this for orderBy field ASC/DESC
		String notification_fieldOrder = request.getParameter("notification_fieldOrder");
		String notification_orderType = request.getParameter("notification_orderType");
		
		if(notification_fieldOrder == null || notification_fieldOrder.length() == 0) notification_fieldOrder = null;
		if(notification_orderType == null || notification_orderType.length() == 0) notification_orderType = null;
		request.getSession().setAttribute("notification_fieldOrder", notification_fieldOrder==null?"":notification_fieldOrder);	 
		request.getSession().setAttribute("notification_orderType", notification_orderType==null?"":notification_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService notificationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List notificationTypeList = notificationTypeService.getList("  and lookup.type='notificationType'  ", null);
			request.setAttribute("notificationTypeList", notificationTypeList);
			com.app.docmgr.service.MessageService postMessageService = com.app.docmgr.service.MessageService.getInstance();
			List postMessageList = postMessageService.getList(null, null);
			request.setAttribute("postMessageList", postMessageList);
			com.app.docmgr.service.UserService subscriberService = com.app.docmgr.service.UserService.getInstance();
			List subscriberList = subscriberService.getList(null, null);
			request.setAttribute("subscriberList", subscriberList);
		}catch(Exception ex){
		
		}
		StringBuffer notification_filterSb = new StringBuffer();
		String param_notification_flag_filter = "";
		if(request.getParameter("notification_flag_filter")!=null){
			param_notification_flag_filter = request.getParameter("notification_flag_filter");
			if(param_notification_flag_filter.length() > 0 ){				
				notification_filterSb.append("  AND notification.flag like '%"+param_notification_flag_filter+"%' ");
			}
		}
		request.getSession().setAttribute("notification_flag_filter", param_notification_flag_filter);
		String param_notification_notificationType_filter = "";
		if(request.getParameter("notification_notificationType_filter")!=null){
			param_notification_notificationType_filter = request.getParameter("notification_notificationType_filter");
			if(param_notification_notificationType_filter.length() > 0 ){				
				notification_filterSb.append("  AND notification.notificationType = '"+param_notification_notificationType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("notification_notificationType_filter", param_notification_notificationType_filter);
		String param_notification_postMessage_filter = "";
		if(request.getParameter("notification_postMessage_filter")!=null){
			param_notification_postMessage_filter = request.getParameter("notification_postMessage_filter");
			if(param_notification_postMessage_filter.length() > 0 ){				
				notification_filterSb.append("  AND notification.postMessage = '"+param_notification_postMessage_filter+"' ");
			}
		}		
		request.getSession().setAttribute("notification_postMessage_filter", param_notification_postMessage_filter);
		String param_notification_subscriber_filter = "";
		if(request.getParameter("notification_subscriber_filter")!=null){
			param_notification_subscriber_filter = request.getParameter("notification_subscriber_filter");
			if(param_notification_subscriber_filter.length() > 0 ){				
				notification_filterSb.append("  AND notification.subscriber = '"+param_notification_subscriber_filter+"' ");
			}
		}		
		request.getSession().setAttribute("notification_subscriber_filter", param_notification_subscriber_filter);
		
		if(notification_fieldOrder!=null && notification_orderType != null )notification_filterSb.append(" ORDER BY "+notification_fieldOrder+" "+notification_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		NotificationService notificationService = NotificationService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList notificationList = notificationService.getPartialList(notification_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, notificationList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, notificationList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("notificationList", notificationList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("notification");
		
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
    		NotificationService notificationService = NotificationService.getInstance();
    		List notificationList = notificationService.getList(parentFilter , null);
    		request.setAttribute("notificationList", notificationList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if (notification == null){
	    		notification = NotificationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("notification", notification);
	    	}
    		if(notification == null){
    			response.sendRedirect("notification.do");
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
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if (notification == null){
	    		notification = NotificationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("notification", notification);
	    	}
    		if(notification == null){
    			response.sendRedirect("notification.do");
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
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if(notification == null) notification = new Notification();
    		
			com.app.docmgr.service.LookupService notificationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List notificationTypeList = notificationTypeService.getList("  and lookup.type='notificationType'  ", null);
			request.setAttribute("notificationTypeList", notificationTypeList);
			com.app.docmgr.service.MessageService postMessageService = com.app.docmgr.service.MessageService.getInstance();
			List postMessageList = postMessageService.getList(null, null);
			request.setAttribute("postMessageList", postMessageList);
			com.app.docmgr.service.UserService subscriberService = com.app.docmgr.service.UserService.getInstance();
			List subscriberList = subscriberService.getList(null, null);
			request.setAttribute("subscriberList", subscriberList);

    		request.getSession().setAttribute("notification", notification);
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
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if(notification == null){
    			response.sendRedirect("notification.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, notification, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService notificationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List notificationTypeList = notificationTypeService.getList("  and lookup.type='notificationType'  ", null);
			request.setAttribute("notificationTypeList", notificationTypeList);
			com.app.docmgr.service.MessageService postMessageService = com.app.docmgr.service.MessageService.getInstance();
			List postMessageList = postMessageService.getList(null, null);
			request.setAttribute("postMessageList", postMessageList);
			com.app.docmgr.service.UserService subscriberService = com.app.docmgr.service.UserService.getInstance();
			List subscriberList = subscriberService.getList(null, null);
			request.setAttribute("subscriberList", subscriberList);
	
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
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		NotificationService.getInstance().add(notification);
    		request.getSession().removeAttribute("notification");
    		response.sendRedirect("notification.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("notification.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if (notification == null){
	    		notification = NotificationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("notification", notification);
	    	}
    		if(notification == null){
    			response.sendRedirect("notification.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService notificationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List notificationTypeList = notificationTypeService.getList("  and lookup.type='notificationType'  ", null);
			request.setAttribute("notificationTypeList", notificationTypeList);
			com.app.docmgr.service.MessageService postMessageService = com.app.docmgr.service.MessageService.getInstance();
			List postMessageList = postMessageService.getList(null, null);
			request.setAttribute("postMessageList", postMessageList);
			com.app.docmgr.service.UserService subscriberService = com.app.docmgr.service.UserService.getInstance();
			List subscriberList = subscriberService.getList(null, null);
			request.setAttribute("subscriberList", subscriberList);

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
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if(notification == null){
    			response.sendRedirect("notification.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, notification, errors);
    		
			com.app.docmgr.service.LookupService notificationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List notificationTypeList = notificationTypeService.getList("  and lookup.type='notificationType'  ", null);
			request.setAttribute("notificationTypeList", notificationTypeList);
			com.app.docmgr.service.MessageService postMessageService = com.app.docmgr.service.MessageService.getInstance();
			List postMessageList = postMessageService.getList(null, null);
			request.setAttribute("postMessageList", postMessageList);
			com.app.docmgr.service.UserService subscriberService = com.app.docmgr.service.UserService.getInstance();
			List subscriberList = subscriberService.getList(null, null);
			request.setAttribute("subscriberList", subscriberList);

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
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if(notification == null){
    			response.sendRedirect("notification.do?action=edit_confirm");
    		}
    		
    		NotificationService.getInstance().update(notification);
    		request.getSession().removeAttribute("notification");
    		response.sendRedirect("notification.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("notification.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if (notification == null){
	    		notification = NotificationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("notification", notification);
	    	}
    		if(notification == null){
    			response.sendRedirect("notification.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("notification.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Notification notification = (Notification) request.getSession().getAttribute("notification");
    		if(notification == null){
    			response.sendRedirect("notification.do?action=delete_confirm");
    		}
    		NotificationService.getInstance().delete(notification);
    		request.getSession().removeAttribute("notification");
    		response.sendRedirect("notification.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("notification.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Notification notification, ActionErrors errors){
    	try{    		
			String flag = request.getParameter("flag");
			notification.setFlag(flag);

			com.app.docmgr.model.Lookup  notificationTypeObj =null;
			com.app.docmgr.service.LookupService notificationTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String notificationTypeStr = request.getParameter("notificationType");
				
				if(notificationTypeStr == null || notificationTypeStr.trim().length() == 0 ){
					notification.setNotificationType(null);
				}else{			
					notificationTypeObj = notificationTypeService.get(new Long(notificationTypeStr));
					notification.setNotificationType(notificationTypeObj);
				}
			}catch(Exception ex){}	
			if(notificationTypeObj==null){
				errors.add("notification.notificationType", new ActionError("error.notification.notificationType"));
			}
			com.app.docmgr.model.Message  postMessageObj =null;
			com.app.docmgr.service.MessageService postMessageService = com.app.docmgr.service.MessageService.getInstance();
			try{
				String postMessageStr = request.getParameter("postMessage");
				
				if(postMessageStr == null || postMessageStr.trim().length() == 0 ){
					notification.setPostMessage(null);
				}else{			
					postMessageObj = postMessageService.get(new Long(postMessageStr));
					notification.setPostMessage(postMessageObj);
				}
			}catch(Exception ex){}	
			if(postMessageObj==null){
				errors.add("notification.postMessage", new ActionError("error.notification.postMessage"));
			}
			com.app.docmgr.model.User  subscriberObj =null;
			com.app.docmgr.service.UserService subscriberService = com.app.docmgr.service.UserService.getInstance();
			try{
				String subscriberStr = request.getParameter("subscriber");
				
				if(subscriberStr == null || subscriberStr.trim().length() == 0 ){
					notification.setSubscriber(null);
				}else{			
					subscriberObj = subscriberService.get(new Long(subscriberStr));
					notification.setSubscriber(subscriberObj);
				}
			}catch(Exception ex){}	
			if(subscriberObj==null){
				errors.add("notification.subscriber", new ActionError("error.notification.subscriber"));
			}

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

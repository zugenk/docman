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
 * @createDate 07-10-2017 06:18:15
 */


public class EmailLogActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.EmailLogActionBase");	
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
    	
    	request.getSession().removeAttribute("emailLog");
		
		String emailLog_filter = request.getParameter("emailLog_filter");
		//this for orderBy field ASC/DESC
		String emailLog_fieldOrder = request.getParameter("emailLog_fieldOrder");
		String emailLog_orderType = request.getParameter("emailLog_orderType");
		
		if(emailLog_fieldOrder == null || emailLog_fieldOrder.length() == 0) emailLog_fieldOrder = null;
		if(emailLog_orderType == null || emailLog_orderType.length() == 0) emailLog_orderType = null;
		request.getSession().setAttribute("emailLog_fieldOrder", emailLog_fieldOrder==null?"":emailLog_fieldOrder);	 
		request.getSession().setAttribute("emailLog_orderType", emailLog_orderType==null?"":emailLog_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='EmailLog'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer emailLog_filterSb = new StringBuffer();
		String param_emailLog_type_filter = "";
		if(request.getParameter("emailLog_type_filter")!=null){
			param_emailLog_type_filter = request.getParameter("emailLog_type_filter");
			if(param_emailLog_type_filter.length() > 0 ){				
				emailLog_filterSb.append("  AND emailLog.type like '%"+param_emailLog_type_filter+"%' ");
			}
		}
		request.getSession().setAttribute("emailLog_type_filter", param_emailLog_type_filter);
		String param_emailLog_sender_filter = "";
		if(request.getParameter("emailLog_sender_filter")!=null){
			param_emailLog_sender_filter = request.getParameter("emailLog_sender_filter");
			if(param_emailLog_sender_filter.length() > 0 ){				
				emailLog_filterSb.append("  AND emailLog.sender like '%"+param_emailLog_sender_filter+"%' ");
			}
		}
		request.getSession().setAttribute("emailLog_sender_filter", param_emailLog_sender_filter);
		String param_emailLog_rcptTo_filter = "";
		if(request.getParameter("emailLog_rcptTo_filter")!=null){
			param_emailLog_rcptTo_filter = request.getParameter("emailLog_rcptTo_filter");
			if(param_emailLog_rcptTo_filter.length() > 0 ){				
				emailLog_filterSb.append("  AND emailLog.rcptTo like '%"+param_emailLog_rcptTo_filter+"%' ");
			}
		}
		request.getSession().setAttribute("emailLog_rcptTo_filter", param_emailLog_rcptTo_filter);
		String param_emailLog_subject_filter = "";
		if(request.getParameter("emailLog_subject_filter")!=null){
			param_emailLog_subject_filter = request.getParameter("emailLog_subject_filter");
			if(param_emailLog_subject_filter.length() > 0 ){				
				emailLog_filterSb.append("  AND emailLog.subject like '%"+param_emailLog_subject_filter+"%' ");
			}
		}
		request.getSession().setAttribute("emailLog_subject_filter", param_emailLog_subject_filter);
		String param_emailLog_message_filter = "";
		if(request.getParameter("emailLog_message_filter")!=null){
			param_emailLog_message_filter = request.getParameter("emailLog_message_filter");
			if(param_emailLog_message_filter.length() > 0 ){				
				emailLog_filterSb.append("  AND emailLog.message like '%"+param_emailLog_message_filter+"%' ");
			}
		}
		request.getSession().setAttribute("emailLog_message_filter", param_emailLog_message_filter);
		String param_emailLog_dueDate_filter_start = "";
		if(request.getParameter("emailLog_dueDate_filter_start")!=null){
			param_emailLog_dueDate_filter_start = request.getParameter("emailLog_dueDate_filter_start");
			if(param_emailLog_dueDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_emailLog_dueDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_emailLog_dueDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_emailLog_dueDate_filter_start));
					String param_emailLog_dueDate_filter_start_cal_val = param_emailLog_dueDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_emailLog_dueDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_emailLog_dueDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					emailLog_filterSb.append("  AND emailLog.dueDate >= '"+param_emailLog_dueDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("emailLog_dueDate_filter_start", param_emailLog_dueDate_filter_start);

		String param_emailLog_dueDate_filter_end = "";
		if(request.getParameter("emailLog_dueDate_filter_end")!=null){
			param_emailLog_dueDate_filter_end = request.getParameter("emailLog_dueDate_filter_end");
			if(param_emailLog_dueDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_emailLog_dueDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_emailLog_dueDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_emailLog_dueDate_filter_end));
					String param_emailLog_dueDate_filter_end_cal_val = param_emailLog_dueDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_emailLog_dueDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_emailLog_dueDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					emailLog_filterSb.append("  AND emailLog.dueDate  <= '"+param_emailLog_dueDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("emailLog_dueDate_filter_end", param_emailLog_dueDate_filter_end);

		String param_emailLog_sentDate_filter_start = "";
		if(request.getParameter("emailLog_sentDate_filter_start")!=null){
			param_emailLog_sentDate_filter_start = request.getParameter("emailLog_sentDate_filter_start");
			if(param_emailLog_sentDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_emailLog_sentDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_emailLog_sentDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_emailLog_sentDate_filter_start));
					String param_emailLog_sentDate_filter_start_cal_val = param_emailLog_sentDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_emailLog_sentDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_emailLog_sentDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					emailLog_filterSb.append("  AND emailLog.sentDate >= '"+param_emailLog_sentDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("emailLog_sentDate_filter_start", param_emailLog_sentDate_filter_start);

		String param_emailLog_sentDate_filter_end = "";
		if(request.getParameter("emailLog_sentDate_filter_end")!=null){
			param_emailLog_sentDate_filter_end = request.getParameter("emailLog_sentDate_filter_end");
			if(param_emailLog_sentDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_emailLog_sentDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_emailLog_sentDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_emailLog_sentDate_filter_end));
					String param_emailLog_sentDate_filter_end_cal_val = param_emailLog_sentDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_emailLog_sentDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_emailLog_sentDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					emailLog_filterSb.append("  AND emailLog.sentDate  <= '"+param_emailLog_sentDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("emailLog_sentDate_filter_end", param_emailLog_sentDate_filter_end);

		String param_emailLog_lastTrialDate_filter_start = "";
		if(request.getParameter("emailLog_lastTrialDate_filter_start")!=null){
			param_emailLog_lastTrialDate_filter_start = request.getParameter("emailLog_lastTrialDate_filter_start");
			if(param_emailLog_lastTrialDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_emailLog_lastTrialDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_emailLog_lastTrialDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_emailLog_lastTrialDate_filter_start));
					String param_emailLog_lastTrialDate_filter_start_cal_val = param_emailLog_lastTrialDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_emailLog_lastTrialDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_emailLog_lastTrialDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					emailLog_filterSb.append("  AND emailLog.lastTrialDate >= '"+param_emailLog_lastTrialDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("emailLog_lastTrialDate_filter_start", param_emailLog_lastTrialDate_filter_start);

		String param_emailLog_lastTrialDate_filter_end = "";
		if(request.getParameter("emailLog_lastTrialDate_filter_end")!=null){
			param_emailLog_lastTrialDate_filter_end = request.getParameter("emailLog_lastTrialDate_filter_end");
			if(param_emailLog_lastTrialDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_emailLog_lastTrialDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_emailLog_lastTrialDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_emailLog_lastTrialDate_filter_end));
					String param_emailLog_lastTrialDate_filter_end_cal_val = param_emailLog_lastTrialDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_emailLog_lastTrialDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_emailLog_lastTrialDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					emailLog_filterSb.append("  AND emailLog.lastTrialDate  <= '"+param_emailLog_lastTrialDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("emailLog_lastTrialDate_filter_end", param_emailLog_lastTrialDate_filter_end);

		String param_emailLog_status_filter = "";
		if(request.getParameter("emailLog_status_filter")!=null){
			param_emailLog_status_filter = request.getParameter("emailLog_status_filter");
			if(param_emailLog_status_filter.length() > 0 ){				
				emailLog_filterSb.append("  AND emailLog.status = '"+param_emailLog_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("emailLog_status_filter", param_emailLog_status_filter);
		
		if(emailLog_fieldOrder!=null && emailLog_orderType != null )emailLog_filterSb.append(" ORDER BY "+emailLog_fieldOrder+" "+emailLog_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		EmailLogService emailLogService = EmailLogService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList emailLogList = emailLogService.getPartialList(emailLog_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, emailLogList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, emailLogList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("emailLogList", emailLogList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("emailLog");
		
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
    		EmailLogService emailLogService = EmailLogService.getInstance();
    		List emailLogList = emailLogService.getList(parentFilter , null);
    		request.setAttribute("emailLogList", emailLogList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do");
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
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do");
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
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null) emailLog = new EmailLog();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='EmailLog'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("emailLog", emailLog);
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
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, emailLog, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='EmailLog'  ", null);
			request.setAttribute("statusList", statusList);
*/	
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
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","new"));
    		EmailLogService.getInstance().add(emailLog);
    		request.getSession().removeAttribute("emailLog");
    		response.sendRedirect("emailLog.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='EmailLog'  ", null);
			request.setAttribute("statusList", statusList);
*/ 
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
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, emailLog, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='EmailLog'  ", null);
			request.setAttribute("statusList", statusList);
 */
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
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=edit_confirm");
    		}
    		
    		EmailLogService.getInstance().update(emailLog);
    		request.getSession().removeAttribute("emailLog");
    		response.sendRedirect("emailLog.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=delete_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","deleted"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("emailLog.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=submit_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","submitted"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=approve_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","approved"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=reject_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","rejected"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=pending_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","pending"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=process_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","processed"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=close_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","closed"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=remove_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","removed"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if (emailLog == null){
	    		emailLog = EmailLogService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("emailLog", emailLog);
	    	}
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("emailLog.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		EmailLog emailLog = (EmailLog) request.getSession().getAttribute("emailLog");
    		if(emailLog == null){
    			response.sendRedirect("emailLog.do?action=cancel_confirm");
    		}
    		emailLog.setStatus(StatusService.getInstance().getByTypeandCode("EmailLog","cancelled"));
    		EmailLogService.getInstance().update(emailLog);
    		response.sendRedirect("emailLog.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("emailLog.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, EmailLog emailLog, ActionErrors errors){
    	try{    		
			String type = request.getParameter("type");
			emailLog.setType(type);
			String sender = request.getParameter("sender");
			emailLog.setSender(sender);
			String rcptTo = request.getParameter("rcptTo");
			emailLog.setRcptTo(rcptTo);
			if(rcptTo==null || rcptTo.trim().length() == 0 ){
				errors.add("emailLog.rcptTo", new ActionError("error.emailLog.rcptTo"));
			}
			String subject = request.getParameter("subject");
			emailLog.setSubject(subject);
			if(subject==null || subject.trim().length() == 0 ){
				errors.add("emailLog.subject", new ActionError("error.emailLog.subject"));
			}
			String message = request.getParameter("message");
			emailLog.setMessage(message);
			if(message==null || message.trim().length() == 0 ){
				errors.add("emailLog.message", new ActionError("error.emailLog.message"));
			}
			String retry = request.getParameter("retry");
			try{
				emailLog.setRetry(new java.lang.Integer(retry));
			}catch(Exception ex){}
			String dueDate = request.getParameter("dueDate");
			if(dueDate==null || dueDate.trim().length() == 0 ){
				emailLog.setDueDate(null);
			}else{
				try{
					java.util.Calendar dueDateCalendar = java.util.Calendar.getInstance();
					dueDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dueDate));			
					emailLog.setDueDate(dueDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(dueDate==null || dueDate.trim().length() == 0 ){
				errors.add("emailLog.dueDate", new ActionError("error.emailLog.dueDate"));
			}
			String sentDate = request.getParameter("sentDate");
			if(sentDate==null || sentDate.trim().length() == 0 ){
				emailLog.setSentDate(null);
			}else{
				try{
					java.util.Calendar sentDateCalendar = java.util.Calendar.getInstance();
					sentDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(sentDate));			
					emailLog.setSentDate(sentDateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastTrialDate = request.getParameter("lastTrialDate");
			if(lastTrialDate==null || lastTrialDate.trim().length() == 0 ){
				emailLog.setLastTrialDate(null);
			}else{
				try{
					java.util.Calendar lastTrialDateCalendar = java.util.Calendar.getInstance();
					lastTrialDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastTrialDate));			
					emailLog.setLastTrialDate(lastTrialDateCalendar.getTime());
				}catch(Exception ex){}
			}

/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					emailLog.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					emailLog.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("emailLog.status", new ActionError("error.emailLog.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

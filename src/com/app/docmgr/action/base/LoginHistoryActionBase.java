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


public class LoginHistoryActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.LoginHistoryActionBase");	
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
    	
    	request.getSession().removeAttribute("loginHistory");
		
		String loginHistory_filter = request.getParameter("loginHistory_filter");
		//this for orderBy field ASC/DESC
		String loginHistory_fieldOrder = request.getParameter("loginHistory_fieldOrder");
		String loginHistory_orderType = request.getParameter("loginHistory_orderType");
		
		if(loginHistory_fieldOrder == null || loginHistory_fieldOrder.length() == 0) loginHistory_fieldOrder = null;
		if(loginHistory_orderType == null || loginHistory_orderType.length() == 0) loginHistory_orderType = null;
		request.getSession().setAttribute("loginHistory_fieldOrder", loginHistory_fieldOrder==null?"":loginHistory_fieldOrder);	 
		request.getSession().setAttribute("loginHistory_orderType", loginHistory_orderType==null?"":loginHistory_orderType);
		
		try{ 
			com.app.docmgr.service.UserService userService = com.app.docmgr.service.UserService.getInstance();
			List userList = userService.getList(null, null);
			request.setAttribute("userList", userList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='LoginHistory'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer loginHistory_filterSb = new StringBuffer();
		String param_loginHistory_loginTime_filter_start = "";
		if(request.getParameter("loginHistory_loginTime_filter_start")!=null){
			param_loginHistory_loginTime_filter_start = request.getParameter("loginHistory_loginTime_filter_start");
			if(param_loginHistory_loginTime_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_loginHistory_loginTime_filter_start_cal = java.util.Calendar.getInstance();				
					param_loginHistory_loginTime_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_loginHistory_loginTime_filter_start));
					String param_loginHistory_loginTime_filter_start_cal_val = param_loginHistory_loginTime_filter_start_cal.get(Calendar.YEAR)+"-"+(param_loginHistory_loginTime_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_loginHistory_loginTime_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					loginHistory_filterSb.append("  AND loginHistory.loginTime >= '"+param_loginHistory_loginTime_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("loginHistory_loginTime_filter_start", param_loginHistory_loginTime_filter_start);

		String param_loginHistory_loginTime_filter_end = "";
		if(request.getParameter("loginHistory_loginTime_filter_end")!=null){
			param_loginHistory_loginTime_filter_end = request.getParameter("loginHistory_loginTime_filter_end");
			if(param_loginHistory_loginTime_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_loginHistory_loginTime_filter_end_cal = java.util.Calendar.getInstance();				
					param_loginHistory_loginTime_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_loginHistory_loginTime_filter_end));
					String param_loginHistory_loginTime_filter_end_cal_val = param_loginHistory_loginTime_filter_end_cal.get(Calendar.YEAR)+"-"+(param_loginHistory_loginTime_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_loginHistory_loginTime_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					loginHistory_filterSb.append("  AND loginHistory.loginTime  <= '"+param_loginHistory_loginTime_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("loginHistory_loginTime_filter_end", param_loginHistory_loginTime_filter_end);

		String param_loginHistory_lastAccess_filter_start = "";
		if(request.getParameter("loginHistory_lastAccess_filter_start")!=null){
			param_loginHistory_lastAccess_filter_start = request.getParameter("loginHistory_lastAccess_filter_start");
			if(param_loginHistory_lastAccess_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_loginHistory_lastAccess_filter_start_cal = java.util.Calendar.getInstance();				
					param_loginHistory_lastAccess_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_loginHistory_lastAccess_filter_start));
					String param_loginHistory_lastAccess_filter_start_cal_val = param_loginHistory_lastAccess_filter_start_cal.get(Calendar.YEAR)+"-"+(param_loginHistory_lastAccess_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_loginHistory_lastAccess_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					loginHistory_filterSb.append("  AND loginHistory.lastAccess >= '"+param_loginHistory_lastAccess_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("loginHistory_lastAccess_filter_start", param_loginHistory_lastAccess_filter_start);

		String param_loginHistory_lastAccess_filter_end = "";
		if(request.getParameter("loginHistory_lastAccess_filter_end")!=null){
			param_loginHistory_lastAccess_filter_end = request.getParameter("loginHistory_lastAccess_filter_end");
			if(param_loginHistory_lastAccess_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_loginHistory_lastAccess_filter_end_cal = java.util.Calendar.getInstance();				
					param_loginHistory_lastAccess_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_loginHistory_lastAccess_filter_end));
					String param_loginHistory_lastAccess_filter_end_cal_val = param_loginHistory_lastAccess_filter_end_cal.get(Calendar.YEAR)+"-"+(param_loginHistory_lastAccess_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_loginHistory_lastAccess_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					loginHistory_filterSb.append("  AND loginHistory.lastAccess  <= '"+param_loginHistory_lastAccess_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("loginHistory_lastAccess_filter_end", param_loginHistory_lastAccess_filter_end);

		String param_loginHistory_logoutTime_filter_start = "";
		if(request.getParameter("loginHistory_logoutTime_filter_start")!=null){
			param_loginHistory_logoutTime_filter_start = request.getParameter("loginHistory_logoutTime_filter_start");
			if(param_loginHistory_logoutTime_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_loginHistory_logoutTime_filter_start_cal = java.util.Calendar.getInstance();				
					param_loginHistory_logoutTime_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_loginHistory_logoutTime_filter_start));
					String param_loginHistory_logoutTime_filter_start_cal_val = param_loginHistory_logoutTime_filter_start_cal.get(Calendar.YEAR)+"-"+(param_loginHistory_logoutTime_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_loginHistory_logoutTime_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					loginHistory_filterSb.append("  AND loginHistory.logoutTime >= '"+param_loginHistory_logoutTime_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("loginHistory_logoutTime_filter_start", param_loginHistory_logoutTime_filter_start);

		String param_loginHistory_logoutTime_filter_end = "";
		if(request.getParameter("loginHistory_logoutTime_filter_end")!=null){
			param_loginHistory_logoutTime_filter_end = request.getParameter("loginHistory_logoutTime_filter_end");
			if(param_loginHistory_logoutTime_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_loginHistory_logoutTime_filter_end_cal = java.util.Calendar.getInstance();				
					param_loginHistory_logoutTime_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_loginHistory_logoutTime_filter_end));
					String param_loginHistory_logoutTime_filter_end_cal_val = param_loginHistory_logoutTime_filter_end_cal.get(Calendar.YEAR)+"-"+(param_loginHistory_logoutTime_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_loginHistory_logoutTime_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					loginHistory_filterSb.append("  AND loginHistory.logoutTime  <= '"+param_loginHistory_logoutTime_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("loginHistory_logoutTime_filter_end", param_loginHistory_logoutTime_filter_end);

		String param_loginHistory_sessionId_filter = "";
		if(request.getParameter("loginHistory_sessionId_filter")!=null){
			param_loginHistory_sessionId_filter = request.getParameter("loginHistory_sessionId_filter");
			if(param_loginHistory_sessionId_filter.length() > 0 ){				
				loginHistory_filterSb.append("  AND loginHistory.sessionId like '%"+param_loginHistory_sessionId_filter+"%' ");
			}
		}
		request.getSession().setAttribute("loginHistory_sessionId_filter", param_loginHistory_sessionId_filter);
		String param_loginHistory_description_filter = "";
		if(request.getParameter("loginHistory_description_filter")!=null){
			param_loginHistory_description_filter = request.getParameter("loginHistory_description_filter");
			if(param_loginHistory_description_filter.length() > 0 ){				
				loginHistory_filterSb.append("  AND loginHistory.description like '%"+param_loginHistory_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("loginHistory_description_filter", param_loginHistory_description_filter);
		String param_loginHistory_user_filter = "";
		if(request.getParameter("loginHistory_user_filter")!=null){
			param_loginHistory_user_filter = request.getParameter("loginHistory_user_filter");
			if(param_loginHistory_user_filter.length() > 0 ){				
				loginHistory_filterSb.append("  AND loginHistory.user = '"+param_loginHistory_user_filter+"' ");
			}
		}		
		request.getSession().setAttribute("loginHistory_user_filter", param_loginHistory_user_filter);
		String param_loginHistory_status_filter = "";
		if(request.getParameter("loginHistory_status_filter")!=null){
			param_loginHistory_status_filter = request.getParameter("loginHistory_status_filter");
			if(param_loginHistory_status_filter.length() > 0 ){				
				loginHistory_filterSb.append("  AND loginHistory.status = '"+param_loginHistory_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("loginHistory_status_filter", param_loginHistory_status_filter);
		
		if(loginHistory_fieldOrder!=null && loginHistory_orderType != null )loginHistory_filterSb.append(" ORDER BY "+loginHistory_fieldOrder+" "+loginHistory_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		LoginHistoryService loginHistoryService = LoginHistoryService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList loginHistoryList = loginHistoryService.getPartialList(loginHistory_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, loginHistoryList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, loginHistoryList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("loginHistoryList", loginHistoryList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("loginHistory");
		
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
    		LoginHistoryService loginHistoryService = LoginHistoryService.getInstance();
    		List loginHistoryList = loginHistoryService.getList(parentFilter , null);
    		request.setAttribute("loginHistoryList", loginHistoryList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do");
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
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do");
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
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null) loginHistory = new LoginHistory();
    		
			com.app.docmgr.service.UserService userService = com.app.docmgr.service.UserService.getInstance();
			List userList = userService.getList(null, null);
			request.setAttribute("userList", userList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='LoginHistory'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("loginHistory", loginHistory);
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
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, loginHistory, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.UserService userService = com.app.docmgr.service.UserService.getInstance();
			List userList = userService.getList(null, null);
			request.setAttribute("userList", userList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='LoginHistory'  ", null);
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
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","new"));
    		LoginHistoryService.getInstance().add(loginHistory);
    		request.getSession().removeAttribute("loginHistory");
    		response.sendRedirect("loginHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do");
    			return null;
    		}
    		
			com.app.docmgr.service.UserService userService = com.app.docmgr.service.UserService.getInstance();
			List userList = userService.getList(null, null);
			request.setAttribute("userList", userList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='LoginHistory'  ", null);
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
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, loginHistory, errors);
    		
			com.app.docmgr.service.UserService userService = com.app.docmgr.service.UserService.getInstance();
			List userList = userService.getList(null, null);
			request.setAttribute("userList", userList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='LoginHistory'  ", null);
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
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=edit_confirm");
    		}
    		
    		LoginHistoryService.getInstance().update(loginHistory);
    		request.getSession().removeAttribute("loginHistory");
    		response.sendRedirect("loginHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=delete_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","deleted"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("loginHistory.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=submit_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","submitted"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=approve_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","approved"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=reject_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","rejected"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=pending_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","pending"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=process_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","processed"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=close_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","closed"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=remove_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","removed"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if (loginHistory == null){
	    		loginHistory = LoginHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("loginHistory", loginHistory);
	    	}
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("loginHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		LoginHistory loginHistory = (LoginHistory) request.getSession().getAttribute("loginHistory");
    		if(loginHistory == null){
    			response.sendRedirect("loginHistory.do?action=cancel_confirm");
    		}
    		loginHistory.setStatus(StatusService.getInstance().getByTypeandCode("LoginHistory","cancelled"));
    		LoginHistoryService.getInstance().update(loginHistory);
    		response.sendRedirect("loginHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("loginHistory.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, LoginHistory loginHistory, ActionErrors errors){
    	try{    		
			String loginTime = request.getParameter("loginTime");
			if(loginTime==null || loginTime.trim().length() == 0 ){
				loginHistory.setLoginTime(null);
			}else{
				try{
					java.util.Calendar loginTimeCalendar = java.util.Calendar.getInstance();
					loginTimeCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(loginTime));			
					loginHistory.setLoginTime(loginTimeCalendar.getTime());
				}catch(Exception ex){}
			}
			if(loginTime==null || loginTime.trim().length() == 0 ){
				errors.add("loginHistory.loginTime", new ActionError("error.loginHistory.loginTime"));
			}
			String lastAccess = request.getParameter("lastAccess");
			if(lastAccess==null || lastAccess.trim().length() == 0 ){
				loginHistory.setLastAccess(null);
			}else{
				try{
					java.util.Calendar lastAccessCalendar = java.util.Calendar.getInstance();
					lastAccessCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastAccess));			
					loginHistory.setLastAccess(lastAccessCalendar.getTime());
				}catch(Exception ex){}
			}
			if(lastAccess==null || lastAccess.trim().length() == 0 ){
				errors.add("loginHistory.lastAccess", new ActionError("error.loginHistory.lastAccess"));
			}
			String logoutTime = request.getParameter("logoutTime");
			if(logoutTime==null || logoutTime.trim().length() == 0 ){
				loginHistory.setLogoutTime(null);
			}else{
				try{
					java.util.Calendar logoutTimeCalendar = java.util.Calendar.getInstance();
					logoutTimeCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(logoutTime));			
					loginHistory.setLogoutTime(logoutTimeCalendar.getTime());
				}catch(Exception ex){}
			}
			String sessionId = request.getParameter("sessionId");
			loginHistory.setSessionId(sessionId);
			String description = request.getParameter("description");
			loginHistory.setDescription(description);

			com.app.docmgr.model.User  userObj =null;
			com.app.docmgr.service.UserService userService = com.app.docmgr.service.UserService.getInstance();
			try{
				String userStr = request.getParameter("user");
				
				if(userStr == null || userStr.trim().length() == 0 ){
					loginHistory.setUser(null);
				}else{			
					userObj = userService.get(new Long(userStr));
					loginHistory.setUser(userObj);
				}
			}catch(Exception ex){}	
			if(userObj==null){
				errors.add("loginHistory.user", new ActionError("error.loginHistory.user"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					loginHistory.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					loginHistory.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("loginHistory.status", new ActionError("error.loginHistory.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

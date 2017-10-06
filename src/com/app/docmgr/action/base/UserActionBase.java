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


public class UserActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.UserActionBase");	
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
    	
    	request.getSession().removeAttribute("user");
		
		String user_filter = request.getParameter("user_filter");
		//this for orderBy field ASC/DESC
		String user_fieldOrder = request.getParameter("user_fieldOrder");
		String user_orderType = request.getParameter("user_orderType");
		
		if(user_fieldOrder == null || user_fieldOrder.length() == 0) user_fieldOrder = null;
		if(user_orderType == null || user_orderType.length() == 0) user_orderType = null;
		request.getSession().setAttribute("user_fieldOrder", user_fieldOrder==null?"":user_fieldOrder);	 
		request.getSession().setAttribute("user_orderType", user_orderType==null?"":user_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList = organizationService.getList(null, null);
			request.setAttribute("organizationList", organizationList);
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
		}catch(Exception ex){
		
		}
		StringBuffer user_filterSb = new StringBuffer();
		String param_user_loginName_filter = "";
		if(request.getParameter("user_loginName_filter")!=null){
			param_user_loginName_filter = request.getParameter("user_loginName_filter");
			if(param_user_loginName_filter.length() > 0 ){				
				user_filterSb.append("  AND user.loginName like '%"+param_user_loginName_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_loginName_filter", param_user_loginName_filter);
		String param_user_loginPassword_filter = "";
		if(request.getParameter("user_loginPassword_filter")!=null){
			param_user_loginPassword_filter = request.getParameter("user_loginPassword_filter");
			if(param_user_loginPassword_filter.length() > 0 ){				
				user_filterSb.append("  AND user.loginPassword like '%"+param_user_loginPassword_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_loginPassword_filter", param_user_loginPassword_filter);
		String param_user_pinCode_filter = "";
		if(request.getParameter("user_pinCode_filter")!=null){
			param_user_pinCode_filter = request.getParameter("user_pinCode_filter");
			if(param_user_pinCode_filter.length() > 0 ){				
				user_filterSb.append("  AND user.pinCode like '%"+param_user_pinCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_pinCode_filter", param_user_pinCode_filter);
		String param_user_mobileNumber_filter = "";
		if(request.getParameter("user_mobileNumber_filter")!=null){
			param_user_mobileNumber_filter = request.getParameter("user_mobileNumber_filter");
			if(param_user_mobileNumber_filter.length() > 0 ){				
				user_filterSb.append("  AND user.mobileNumber like '%"+param_user_mobileNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_mobileNumber_filter", param_user_mobileNumber_filter);
		String param_user_language_filter = "";
		if(request.getParameter("user_language_filter")!=null){
			param_user_language_filter = request.getParameter("user_language_filter");
			if(param_user_language_filter.length() > 0 ){				
				user_filterSb.append("  AND user.language like '%"+param_user_language_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_language_filter", param_user_language_filter);
		String param_user_title_filter = "";
		if(request.getParameter("user_title_filter")!=null){
			param_user_title_filter = request.getParameter("user_title_filter");
			if(param_user_title_filter.length() > 0 ){				
				user_filterSb.append("  AND user.title like '%"+param_user_title_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_title_filter", param_user_title_filter);
		String param_user_name_filter = "";
		if(request.getParameter("user_name_filter")!=null){
			param_user_name_filter = request.getParameter("user_name_filter");
			if(param_user_name_filter.length() > 0 ){				
				user_filterSb.append("  AND user.name like '%"+param_user_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_name_filter", param_user_name_filter);
		String param_user_email_filter = "";
		if(request.getParameter("user_email_filter")!=null){
			param_user_email_filter = request.getParameter("user_email_filter");
			if(param_user_email_filter.length() > 0 ){				
				user_filterSb.append("  AND user.email like '%"+param_user_email_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_email_filter", param_user_email_filter);
		String param_user_fullName_filter = "";
		if(request.getParameter("user_fullName_filter")!=null){
			param_user_fullName_filter = request.getParameter("user_fullName_filter");
			if(param_user_fullName_filter.length() > 0 ){				
				user_filterSb.append("  AND user.fullName like '%"+param_user_fullName_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_fullName_filter", param_user_fullName_filter);
		String param_user_homePhoneNumber_filter = "";
		if(request.getParameter("user_homePhoneNumber_filter")!=null){
			param_user_homePhoneNumber_filter = request.getParameter("user_homePhoneNumber_filter");
			if(param_user_homePhoneNumber_filter.length() > 0 ){				
				user_filterSb.append("  AND user.homePhoneNumber like '%"+param_user_homePhoneNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_homePhoneNumber_filter", param_user_homePhoneNumber_filter);
		String param_user_mobilePhoneNumber_filter = "";
		if(request.getParameter("user_mobilePhoneNumber_filter")!=null){
			param_user_mobilePhoneNumber_filter = request.getParameter("user_mobilePhoneNumber_filter");
			if(param_user_mobilePhoneNumber_filter.length() > 0 ){				
				user_filterSb.append("  AND user.mobilePhoneNumber like '%"+param_user_mobilePhoneNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_mobilePhoneNumber_filter", param_user_mobilePhoneNumber_filter);
		String param_user_employeeNumber_filter = "";
		if(request.getParameter("user_employeeNumber_filter")!=null){
			param_user_employeeNumber_filter = request.getParameter("user_employeeNumber_filter");
			if(param_user_employeeNumber_filter.length() > 0 ){				
				user_filterSb.append("  AND user.employeeNumber like '%"+param_user_employeeNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_employeeNumber_filter", param_user_employeeNumber_filter);
		String param_user_createdDate_filter_start = "";
		if(request.getParameter("user_createdDate_filter_start")!=null){
			param_user_createdDate_filter_start = request.getParameter("user_createdDate_filter_start");
			if(param_user_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_user_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_user_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_createdDate_filter_start));
					String param_user_createdDate_filter_start_cal_val = param_user_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_user_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_user_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.createdDate >= '"+param_user_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_createdDate_filter_start", param_user_createdDate_filter_start);

		String param_user_createdDate_filter_end = "";
		if(request.getParameter("user_createdDate_filter_end")!=null){
			param_user_createdDate_filter_end = request.getParameter("user_createdDate_filter_end");
			if(param_user_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_user_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_user_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_createdDate_filter_end));
					String param_user_createdDate_filter_end_cal_val = param_user_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_user_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_user_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.createdDate  <= '"+param_user_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_createdDate_filter_end", param_user_createdDate_filter_end);

		String param_user_createdBy_filter = "";
		if(request.getParameter("user_createdBy_filter")!=null){
			param_user_createdBy_filter = request.getParameter("user_createdBy_filter");
			if(param_user_createdBy_filter.length() > 0 ){				
				user_filterSb.append("  AND user.createdBy like '%"+param_user_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_createdBy_filter", param_user_createdBy_filter);
		String param_user_lastUpdatedDate_filter_start = "";
		if(request.getParameter("user_lastUpdatedDate_filter_start")!=null){
			param_user_lastUpdatedDate_filter_start = request.getParameter("user_lastUpdatedDate_filter_start");
			if(param_user_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_user_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_user_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastUpdatedDate_filter_start));
					String param_user_lastUpdatedDate_filter_start_cal_val = param_user_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_user_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastUpdatedDate >= '"+param_user_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastUpdatedDate_filter_start", param_user_lastUpdatedDate_filter_start);

		String param_user_lastUpdatedDate_filter_end = "";
		if(request.getParameter("user_lastUpdatedDate_filter_end")!=null){
			param_user_lastUpdatedDate_filter_end = request.getParameter("user_lastUpdatedDate_filter_end");
			if(param_user_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_user_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_user_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastUpdatedDate_filter_end));
					String param_user_lastUpdatedDate_filter_end_cal_val = param_user_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_user_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastUpdatedDate  <= '"+param_user_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastUpdatedDate_filter_end", param_user_lastUpdatedDate_filter_end);

		String param_user_lastUpdatedBy_filter = "";
		if(request.getParameter("user_lastUpdatedBy_filter")!=null){
			param_user_lastUpdatedBy_filter = request.getParameter("user_lastUpdatedBy_filter");
			if(param_user_lastUpdatedBy_filter.length() > 0 ){				
				user_filterSb.append("  AND user.lastUpdatedBy like '%"+param_user_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_lastUpdatedBy_filter", param_user_lastUpdatedBy_filter);
		String param_user_firstLogin_filter = "";
		if(request.getParameter("user_firstLogin_filter")!=null){
			param_user_firstLogin_filter = request.getParameter("user_firstLogin_filter");
			if(param_user_firstLogin_filter.length() > 0 ){				
				user_filterSb.append("  AND user.firstLogin like '%"+param_user_firstLogin_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_firstLogin_filter", param_user_firstLogin_filter);
		String param_user_lastPasswordUpdate_filter_start = "";
		if(request.getParameter("user_lastPasswordUpdate_filter_start")!=null){
			param_user_lastPasswordUpdate_filter_start = request.getParameter("user_lastPasswordUpdate_filter_start");
			if(param_user_lastPasswordUpdate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_user_lastPasswordUpdate_filter_start_cal = java.util.Calendar.getInstance();				
					param_user_lastPasswordUpdate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastPasswordUpdate_filter_start));
					String param_user_lastPasswordUpdate_filter_start_cal_val = param_user_lastPasswordUpdate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_user_lastPasswordUpdate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastPasswordUpdate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastPasswordUpdate >= '"+param_user_lastPasswordUpdate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastPasswordUpdate_filter_start", param_user_lastPasswordUpdate_filter_start);

		String param_user_lastPasswordUpdate_filter_end = "";
		if(request.getParameter("user_lastPasswordUpdate_filter_end")!=null){
			param_user_lastPasswordUpdate_filter_end = request.getParameter("user_lastPasswordUpdate_filter_end");
			if(param_user_lastPasswordUpdate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_user_lastPasswordUpdate_filter_end_cal = java.util.Calendar.getInstance();				
					param_user_lastPasswordUpdate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastPasswordUpdate_filter_end));
					String param_user_lastPasswordUpdate_filter_end_cal_val = param_user_lastPasswordUpdate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_user_lastPasswordUpdate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastPasswordUpdate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastPasswordUpdate  <= '"+param_user_lastPasswordUpdate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastPasswordUpdate_filter_end", param_user_lastPasswordUpdate_filter_end);

		String param_user_lastPinCodeUpdate_filter_start = "";
		if(request.getParameter("user_lastPinCodeUpdate_filter_start")!=null){
			param_user_lastPinCodeUpdate_filter_start = request.getParameter("user_lastPinCodeUpdate_filter_start");
			if(param_user_lastPinCodeUpdate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_user_lastPinCodeUpdate_filter_start_cal = java.util.Calendar.getInstance();				
					param_user_lastPinCodeUpdate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastPinCodeUpdate_filter_start));
					String param_user_lastPinCodeUpdate_filter_start_cal_val = param_user_lastPinCodeUpdate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_user_lastPinCodeUpdate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastPinCodeUpdate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastPinCodeUpdate >= '"+param_user_lastPinCodeUpdate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastPinCodeUpdate_filter_start", param_user_lastPinCodeUpdate_filter_start);

		String param_user_lastPinCodeUpdate_filter_end = "";
		if(request.getParameter("user_lastPinCodeUpdate_filter_end")!=null){
			param_user_lastPinCodeUpdate_filter_end = request.getParameter("user_lastPinCodeUpdate_filter_end");
			if(param_user_lastPinCodeUpdate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_user_lastPinCodeUpdate_filter_end_cal = java.util.Calendar.getInstance();				
					param_user_lastPinCodeUpdate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastPinCodeUpdate_filter_end));
					String param_user_lastPinCodeUpdate_filter_end_cal_val = param_user_lastPinCodeUpdate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_user_lastPinCodeUpdate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastPinCodeUpdate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastPinCodeUpdate  <= '"+param_user_lastPinCodeUpdate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastPinCodeUpdate_filter_end", param_user_lastPinCodeUpdate_filter_end);

		String param_user_lastPassword_filter = "";
		if(request.getParameter("user_lastPassword_filter")!=null){
			param_user_lastPassword_filter = request.getParameter("user_lastPassword_filter");
			if(param_user_lastPassword_filter.length() > 0 ){				
				user_filterSb.append("  AND user.lastPassword like '%"+param_user_lastPassword_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_lastPassword_filter", param_user_lastPassword_filter);
		String param_user_lastPinCode_filter = "";
		if(request.getParameter("user_lastPinCode_filter")!=null){
			param_user_lastPinCode_filter = request.getParameter("user_lastPinCode_filter");
			if(param_user_lastPinCode_filter.length() > 0 ){				
				user_filterSb.append("  AND user.lastPinCode like '%"+param_user_lastPinCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_lastPinCode_filter", param_user_lastPinCode_filter);
		String param_user_lastReleasedDate_filter_start = "";
		if(request.getParameter("user_lastReleasedDate_filter_start")!=null){
			param_user_lastReleasedDate_filter_start = request.getParameter("user_lastReleasedDate_filter_start");
			if(param_user_lastReleasedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_user_lastReleasedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_user_lastReleasedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastReleasedDate_filter_start));
					String param_user_lastReleasedDate_filter_start_cal_val = param_user_lastReleasedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_user_lastReleasedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastReleasedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastReleasedDate >= '"+param_user_lastReleasedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastReleasedDate_filter_start", param_user_lastReleasedDate_filter_start);

		String param_user_lastReleasedDate_filter_end = "";
		if(request.getParameter("user_lastReleasedDate_filter_end")!=null){
			param_user_lastReleasedDate_filter_end = request.getParameter("user_lastReleasedDate_filter_end");
			if(param_user_lastReleasedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_user_lastReleasedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_user_lastReleasedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastReleasedDate_filter_end));
					String param_user_lastReleasedDate_filter_end_cal_val = param_user_lastReleasedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_user_lastReleasedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastReleasedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastReleasedDate  <= '"+param_user_lastReleasedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastReleasedDate_filter_end", param_user_lastReleasedDate_filter_end);

		String param_user_lastActive_filter_start = "";
		if(request.getParameter("user_lastActive_filter_start")!=null){
			param_user_lastActive_filter_start = request.getParameter("user_lastActive_filter_start");
			if(param_user_lastActive_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_user_lastActive_filter_start_cal = java.util.Calendar.getInstance();				
					param_user_lastActive_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastActive_filter_start));
					String param_user_lastActive_filter_start_cal_val = param_user_lastActive_filter_start_cal.get(Calendar.YEAR)+"-"+(param_user_lastActive_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastActive_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastActive >= '"+param_user_lastActive_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastActive_filter_start", param_user_lastActive_filter_start);

		String param_user_lastActive_filter_end = "";
		if(request.getParameter("user_lastActive_filter_end")!=null){
			param_user_lastActive_filter_end = request.getParameter("user_lastActive_filter_end");
			if(param_user_lastActive_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_user_lastActive_filter_end_cal = java.util.Calendar.getInstance();				
					param_user_lastActive_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_user_lastActive_filter_end));
					String param_user_lastActive_filter_end_cal_val = param_user_lastActive_filter_end_cal.get(Calendar.YEAR)+"-"+(param_user_lastActive_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_user_lastActive_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					user_filterSb.append("  AND user.lastActive  <= '"+param_user_lastActive_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("user_lastActive_filter_end", param_user_lastActive_filter_end);

		String param_user_sessionCode_filter = "";
		if(request.getParameter("user_sessionCode_filter")!=null){
			param_user_sessionCode_filter = request.getParameter("user_sessionCode_filter");
			if(param_user_sessionCode_filter.length() > 0 ){				
				user_filterSb.append("  AND user.sessionCode like '%"+param_user_sessionCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_sessionCode_filter", param_user_sessionCode_filter);
		String param_user_IPassport_filter = "";
		if(request.getParameter("user_IPassport_filter")!=null){
			param_user_IPassport_filter = request.getParameter("user_IPassport_filter");
			if(param_user_IPassport_filter.length() > 0 ){				
				user_filterSb.append("  AND user.IPassport like '%"+param_user_IPassport_filter+"%' ");
			}
		}
		request.getSession().setAttribute("user_IPassport_filter", param_user_IPassport_filter);
		String param_user_userLevel_filter = "";
		if(request.getParameter("user_userLevel_filter")!=null){
			param_user_userLevel_filter = request.getParameter("user_userLevel_filter");
			if(param_user_userLevel_filter.length() > 0 ){				
				user_filterSb.append("  AND user.userLevel = '"+param_user_userLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("user_userLevel_filter", param_user_userLevel_filter);
		String param_user_status_filter = "";
		if(request.getParameter("user_status_filter")!=null){
			param_user_status_filter = request.getParameter("user_status_filter");
			if(param_user_status_filter.length() > 0 ){				
				user_filterSb.append("  AND user.status = '"+param_user_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("user_status_filter", param_user_status_filter);
		String param_user_organization_filter = "";
		if(request.getParameter("user_organization_filter")!=null){
			param_user_organization_filter = request.getParameter("user_organization_filter");
			if(param_user_organization_filter.length() > 0 ){				
				user_filterSb.append("  AND user.organization = '"+param_user_organization_filter+"' ");
			}
		}		
		request.getSession().setAttribute("user_organization_filter", param_user_organization_filter);
		String param_user_securityLevel_filter = "";
		if(request.getParameter("user_securityLevel_filter")!=null){
			param_user_securityLevel_filter = request.getParameter("user_securityLevel_filter");
			if(param_user_securityLevel_filter.length() > 0 ){				
				user_filterSb.append("  AND user.securityLevel = '"+param_user_securityLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("user_securityLevel_filter", param_user_securityLevel_filter);
		
		if(user_fieldOrder!=null && user_orderType != null )user_filterSb.append(" ORDER BY "+user_fieldOrder+" "+user_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		UserService userService = UserService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList userList = userService.getPartialList(user_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, userList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, userList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("userList", userList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("user");
		
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
    		UserService userService = UserService.getInstance();
    		List userList = userService.getList(parentFilter , null);
    		request.setAttribute("userList", userList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do");
    			return null;
    		}
    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

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
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do");
    			return null;
    		}
    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		
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
    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList = organizationService.getList(null, null);
			request.setAttribute("organizationList", organizationList);
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);

			RoleService roleService = RoleService.getInstance();
			List roleSetList = roleService.getList(null, null);
			request.setAttribute("roleSetList", roleSetList);

			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);

			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			
    		request.getSession().setAttribute("user", user);
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
    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, user, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList = organizationService.getList(null, null);
			request.setAttribute("organizationList", organizationList);
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			RoleService roleService = RoleService.getInstance();
			List roleSetList = roleService.getList(null, null);
			request.setAttribute("roleSetList", roleSetList);
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);
			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);
			
	
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
    		User user = (User) request.getSession().getAttribute("user");
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","new"));
			user.setLastUpdatedDate(new Date());
			user.setCreatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
			user.setCreatedBy(_doneBy);
    		UserService.getInstance().add(user);
    		request.getSession().removeAttribute("user");
    		response.sendRedirect("user.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList = organizationService.getList(null, null);
			request.setAttribute("organizationList", organizationList);
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			RoleService roleService = RoleService.getInstance();
			List roleSetList = roleService.getList(null, null);
			request.setAttribute("roleSetList", roleSetList);
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

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
    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, user, errors);
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList = organizationService.getList(null, null);
			request.setAttribute("organizationList", organizationList);
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);

			RoleService roleService = RoleService.getInstance();
			List roleSetList = roleService.getList(null, null);
			request.setAttribute("roleSetList", roleSetList);
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			
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
    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=edit_confirm");
    		}
    		
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		request.getSession().removeAttribute("user");
    		response.sendRedirect("user.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=delete_confirm");
    		}
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","deleted"));
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("user.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=submit_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","submitted"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=approve_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","approved"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=reject_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","rejected"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=pending_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","pending"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=process_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","processed"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=close_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","closed"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=remove_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","removed"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		User user = (User) request.getSession().getAttribute("user");
    		if (user == null){
	    		user = UserService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("user", user);
	    	}
    		if(user == null){
    			response.sendRedirect("user.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = user.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = user.getFavoriteTopics();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("user.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		User user = (User) request.getSession().getAttribute("user");
    		if(user == null){
    			response.sendRedirect("user.do?action=cancel_confirm");
    		}
    		user.setStatus(StatusService.getInstance().getByTypeandCode("User","cancelled"));
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(_doneBy);
    		UserService.getInstance().update(user);
    		response.sendRedirect("user.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("user.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, User user, ActionErrors errors){
    	try{    		
			String loginName = request.getParameter("loginName");
			user.setLoginName(loginName);
			if(loginName==null || loginName.trim().length() == 0 ){
				errors.add("user.loginName", new ActionError("error.user.loginName"));
			}
			String loginPassword = request.getParameter("loginPassword");
			user.setLoginPassword(loginPassword);
			if(loginPassword==null || loginPassword.trim().length() == 0 ){
				errors.add("user.loginPassword", new ActionError("error.user.loginPassword"));
			}
			String pinCode = request.getParameter("pinCode");
			user.setPinCode(pinCode);
			String mobileNumber = request.getParameter("mobileNumber");
			user.setMobileNumber(mobileNumber);
			String language = request.getParameter("language");
			user.setLanguage(language);
			String title = request.getParameter("title");
			user.setTitle(title);
			String name = request.getParameter("name");
			user.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("user.name", new ActionError("error.user.name"));
			}
			String email = request.getParameter("email");
			user.setEmail(email);
			String fullName = request.getParameter("fullName");
			user.setFullName(fullName);
			String homePhoneNumber = request.getParameter("homePhoneNumber");
			user.setHomePhoneNumber(homePhoneNumber);
			String mobilePhoneNumber = request.getParameter("mobilePhoneNumber");
			user.setMobilePhoneNumber(mobilePhoneNumber);
			String employeeNumber = request.getParameter("employeeNumber");
			user.setEmployeeNumber(employeeNumber);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				user.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					user.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("user.createdDate", new ActionError("error.user.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			user.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("user.createdBy", new ActionError("error.user.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				user.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					user.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			user.setLastUpdatedBy(lastUpdatedBy);
*/ 			String firstLogin = request.getParameter("firstLogin");
			user.setFirstLogin(firstLogin);
			String lastPasswordUpdate = request.getParameter("lastPasswordUpdate");
			if(lastPasswordUpdate==null || lastPasswordUpdate.trim().length() == 0 ){
				user.setLastPasswordUpdate(null);
			}else{
				try{
					java.util.Calendar lastPasswordUpdateCalendar = java.util.Calendar.getInstance();
					lastPasswordUpdateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastPasswordUpdate));			
					user.setLastPasswordUpdate(lastPasswordUpdateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastPinCodeUpdate = request.getParameter("lastPinCodeUpdate");
			if(lastPinCodeUpdate==null || lastPinCodeUpdate.trim().length() == 0 ){
				user.setLastPinCodeUpdate(null);
			}else{
				try{
					java.util.Calendar lastPinCodeUpdateCalendar = java.util.Calendar.getInstance();
					lastPinCodeUpdateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastPinCodeUpdate));			
					user.setLastPinCodeUpdate(lastPinCodeUpdateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastPassword = request.getParameter("lastPassword");
			user.setLastPassword(lastPassword);
			String lastPinCode = request.getParameter("lastPinCode");
			user.setLastPinCode(lastPinCode);
			String loginFailed = request.getParameter("loginFailed");
			try{
				user.setLoginFailed(new java.lang.Integer(loginFailed));
			}catch(Exception ex){}
			String maxRelease = request.getParameter("maxRelease");
			try{
				user.setMaxRelease(new java.lang.Integer(maxRelease));
			}catch(Exception ex){}
			String lastReleasedDate = request.getParameter("lastReleasedDate");
			if(lastReleasedDate==null || lastReleasedDate.trim().length() == 0 ){
				user.setLastReleasedDate(null);
			}else{
				try{
					java.util.Calendar lastReleasedDateCalendar = java.util.Calendar.getInstance();
					lastReleasedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastReleasedDate));			
					user.setLastReleasedDate(lastReleasedDateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastActive = request.getParameter("lastActive");
			if(lastActive==null || lastActive.trim().length() == 0 ){
				user.setLastActive(null);
			}else{
				try{
					java.util.Calendar lastActiveCalendar = java.util.Calendar.getInstance();
					lastActiveCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastActive));			
					user.setLastActive(lastActiveCalendar.getTime());
				}catch(Exception ex){}
			}
			String sessionCode = request.getParameter("sessionCode");
			user.setSessionCode(sessionCode);
			String IPassport = request.getParameter("IPassport");
			user.setIPassport(IPassport);

			com.app.docmgr.model.Lookup  userLevelObj =null;
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String userLevelStr = request.getParameter("userLevel");
				
				if(userLevelStr == null || userLevelStr.trim().length() == 0 ){
					user.setUserLevel(null);
				}else{			
					userLevelObj = userLevelService.get(new Long(userLevelStr));
					user.setUserLevel(userLevelObj);
				}
			}catch(Exception ex){}	
			if(userLevelObj==null){
				errors.add("user.userLevel", new ActionError("error.user.userLevel"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					user.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					user.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("user.status", new ActionError("error.user.status"));
			}
*/ 			com.app.docmgr.model.Organization  organizationObj =null;
			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			try{
				String organizationStr = request.getParameter("organization");
				
				if(organizationStr == null || organizationStr.trim().length() == 0 ){
					user.setOrganization(null);
				}else{			
					organizationObj = organizationService.get(new Long(organizationStr));
					user.setOrganization(organizationObj);
				}
			}catch(Exception ex){}	
			com.app.docmgr.model.Lookup  securityLevelObj =null;
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String securityLevelStr = request.getParameter("securityLevel");
				
				if(securityLevelStr == null || securityLevelStr.trim().length() == 0 ){
					user.setSecurityLevel(null);
				}else{			
					securityLevelObj = securityLevelService.get(new Long(securityLevelStr));
					user.setSecurityLevel(securityLevelObj);
				}
			}catch(Exception ex){}	

			Set roleSet = new HashSet();
			String[] _selectedRole=request.getParameterValues("selected_role");
			if (_selectedRole!=null && _selectedRole.length>0) {
				RoleService roleService = RoleService.getInstance();
				Set _orgRoleSet = user.getRoles();
				if (_orgRoleSet==null)_orgRoleSet=new HashSet();
				Role item=null;
				boolean found=false;
				long itemId =0;
				for (int i = 0; i < _selectedRole.length; i++) {
					itemId = Long.parseLong(_selectedRole[i]);
					found=false;
					try {
						for (Iterator iter = _orgRoleSet.iterator(); iter.hasNext()&& !found;) {
							item = (Role) iter.next();
							if (itemId==item.getId()){
								found=true;
								roleSet.add(item);
							}
						}
						if (!found) roleSet.add(roleService.get(itemId));
					} catch (Exception e) {
					}
				}
			}
			user.setRoles(roleSet);
			Set topicSet = new HashSet();
			String[] _selectedTopic=request.getParameterValues("selected_topic");
			if (_selectedTopic!=null && _selectedTopic.length>0) {
				TopicService topicService = TopicService.getInstance();
				Set _orgTopicSet = user.getFavoriteTopics();
				if (_orgTopicSet==null)_orgTopicSet=new HashSet();
				Topic item=null;
				boolean found=false;
				long itemId =0;
				for (int i = 0; i < _selectedTopic.length; i++) {
					itemId = Long.parseLong(_selectedTopic[i]);
					found=false;
					try {
						for (Iterator iter = _orgTopicSet.iterator(); iter.hasNext()&& !found;) {
							item = (Topic) iter.next();
							if (itemId==item.getId()){
								found=true;
								topicSet.add(item);
							}
						}
						if (!found) topicSet.add(topicService.get(itemId));
					} catch (Exception e) {
					}
				}
			}
			user.setFavoriteTopics(topicSet);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

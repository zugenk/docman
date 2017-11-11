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


public class UserHistoryActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.UserHistoryActionBase");	
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
    	
    	request.getSession().removeAttribute("userHistory");
		
		String userHistory_filter = request.getParameter("userHistory_filter");
		//this for orderBy field ASC/DESC
		String userHistory_fieldOrder = request.getParameter("userHistory_fieldOrder");
		String userHistory_orderType = request.getParameter("userHistory_orderType");
		
		if(userHistory_fieldOrder == null || userHistory_fieldOrder.length() == 0) userHistory_fieldOrder = null;
		if(userHistory_orderType == null || userHistory_orderType.length() == 0) userHistory_orderType = null;
		request.getSession().setAttribute("userHistory_fieldOrder", userHistory_fieldOrder==null?"":userHistory_fieldOrder);	 
		request.getSession().setAttribute("userHistory_orderType", userHistory_orderType==null?"":userHistory_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
			List positionList = positionService.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList", positionList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='UserHistory'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList = organizationService.getList(null, null);
			request.setAttribute("organizationList", organizationList);
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
		}catch(Exception ex){
		
		}
		StringBuffer userHistory_filterSb = new StringBuffer();
		String param_userHistory_historyDate_filter_start = "";
		if(request.getParameter("userHistory_historyDate_filter_start")!=null){
			param_userHistory_historyDate_filter_start = request.getParameter("userHistory_historyDate_filter_start");
			if(param_userHistory_historyDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_historyDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_historyDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_historyDate_filter_start));
					String param_userHistory_historyDate_filter_start_cal_val = param_userHistory_historyDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_historyDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_historyDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.historyDate >= '"+param_userHistory_historyDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_historyDate_filter_start", param_userHistory_historyDate_filter_start);

		String param_userHistory_historyDate_filter_end = "";
		if(request.getParameter("userHistory_historyDate_filter_end")!=null){
			param_userHistory_historyDate_filter_end = request.getParameter("userHistory_historyDate_filter_end");
			if(param_userHistory_historyDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_historyDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_historyDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_historyDate_filter_end));
					String param_userHistory_historyDate_filter_end_cal_val = param_userHistory_historyDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_historyDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_historyDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.historyDate  <= '"+param_userHistory_historyDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_historyDate_filter_end", param_userHistory_historyDate_filter_end);

		String param_userHistory_historyBy_filter = "";
		if(request.getParameter("userHistory_historyBy_filter")!=null){
			param_userHistory_historyBy_filter = request.getParameter("userHistory_historyBy_filter");
			if(param_userHistory_historyBy_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.historyBy like '%"+param_userHistory_historyBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_historyBy_filter", param_userHistory_historyBy_filter);
		String param_userHistory_loginName_filter = "";
		if(request.getParameter("userHistory_loginName_filter")!=null){
			param_userHistory_loginName_filter = request.getParameter("userHistory_loginName_filter");
			if(param_userHistory_loginName_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.loginName like '%"+param_userHistory_loginName_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_loginName_filter", param_userHistory_loginName_filter);
		String param_userHistory_loginPassword_filter = "";
		if(request.getParameter("userHistory_loginPassword_filter")!=null){
			param_userHistory_loginPassword_filter = request.getParameter("userHistory_loginPassword_filter");
			if(param_userHistory_loginPassword_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.loginPassword like '%"+param_userHistory_loginPassword_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_loginPassword_filter", param_userHistory_loginPassword_filter);
		String param_userHistory_pinCode_filter = "";
		if(request.getParameter("userHistory_pinCode_filter")!=null){
			param_userHistory_pinCode_filter = request.getParameter("userHistory_pinCode_filter");
			if(param_userHistory_pinCode_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.pinCode like '%"+param_userHistory_pinCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_pinCode_filter", param_userHistory_pinCode_filter);
		String param_userHistory_picture_filter = "";
		if(request.getParameter("userHistory_picture_filter")!=null){
			param_userHistory_picture_filter = request.getParameter("userHistory_picture_filter");
			if(param_userHistory_picture_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.picture like '%"+param_userHistory_picture_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_picture_filter", param_userHistory_picture_filter);
		String param_userHistory_language_filter = "";
		if(request.getParameter("userHistory_language_filter")!=null){
			param_userHistory_language_filter = request.getParameter("userHistory_language_filter");
			if(param_userHistory_language_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.language like '%"+param_userHistory_language_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_language_filter", param_userHistory_language_filter);
		String param_userHistory_title_filter = "";
		if(request.getParameter("userHistory_title_filter")!=null){
			param_userHistory_title_filter = request.getParameter("userHistory_title_filter");
			if(param_userHistory_title_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.title like '%"+param_userHistory_title_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_title_filter", param_userHistory_title_filter);
		String param_userHistory_name_filter = "";
		if(request.getParameter("userHistory_name_filter")!=null){
			param_userHistory_name_filter = request.getParameter("userHistory_name_filter");
			if(param_userHistory_name_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.name like '%"+param_userHistory_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_name_filter", param_userHistory_name_filter);
		String param_userHistory_alias_filter = "";
		if(request.getParameter("userHistory_alias_filter")!=null){
			param_userHistory_alias_filter = request.getParameter("userHistory_alias_filter");
			if(param_userHistory_alias_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.alias like '%"+param_userHistory_alias_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_alias_filter", param_userHistory_alias_filter);
		String param_userHistory_email_filter = "";
		if(request.getParameter("userHistory_email_filter")!=null){
			param_userHistory_email_filter = request.getParameter("userHistory_email_filter");
			if(param_userHistory_email_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.email like '%"+param_userHistory_email_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_email_filter", param_userHistory_email_filter);
		String param_userHistory_fullName_filter = "";
		if(request.getParameter("userHistory_fullName_filter")!=null){
			param_userHistory_fullName_filter = request.getParameter("userHistory_fullName_filter");
			if(param_userHistory_fullName_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.fullName like '%"+param_userHistory_fullName_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_fullName_filter", param_userHistory_fullName_filter);
		String param_userHistory_homePhoneNumber_filter = "";
		if(request.getParameter("userHistory_homePhoneNumber_filter")!=null){
			param_userHistory_homePhoneNumber_filter = request.getParameter("userHistory_homePhoneNumber_filter");
			if(param_userHistory_homePhoneNumber_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.homePhoneNumber like '%"+param_userHistory_homePhoneNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_homePhoneNumber_filter", param_userHistory_homePhoneNumber_filter);
		String param_userHistory_mobilePhoneNumber_filter = "";
		if(request.getParameter("userHistory_mobilePhoneNumber_filter")!=null){
			param_userHistory_mobilePhoneNumber_filter = request.getParameter("userHistory_mobilePhoneNumber_filter");
			if(param_userHistory_mobilePhoneNumber_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.mobilePhoneNumber like '%"+param_userHistory_mobilePhoneNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_mobilePhoneNumber_filter", param_userHistory_mobilePhoneNumber_filter);
		String param_userHistory_employeeNumber_filter = "";
		if(request.getParameter("userHistory_employeeNumber_filter")!=null){
			param_userHistory_employeeNumber_filter = request.getParameter("userHistory_employeeNumber_filter");
			if(param_userHistory_employeeNumber_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.employeeNumber like '%"+param_userHistory_employeeNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_employeeNumber_filter", param_userHistory_employeeNumber_filter);
		String param_userHistory_createdDate_filter_start = "";
		if(request.getParameter("userHistory_createdDate_filter_start")!=null){
			param_userHistory_createdDate_filter_start = request.getParameter("userHistory_createdDate_filter_start");
			if(param_userHistory_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_createdDate_filter_start));
					String param_userHistory_createdDate_filter_start_cal_val = param_userHistory_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.createdDate >= '"+param_userHistory_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_createdDate_filter_start", param_userHistory_createdDate_filter_start);

		String param_userHistory_createdDate_filter_end = "";
		if(request.getParameter("userHistory_createdDate_filter_end")!=null){
			param_userHistory_createdDate_filter_end = request.getParameter("userHistory_createdDate_filter_end");
			if(param_userHistory_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_createdDate_filter_end));
					String param_userHistory_createdDate_filter_end_cal_val = param_userHistory_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.createdDate  <= '"+param_userHistory_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_createdDate_filter_end", param_userHistory_createdDate_filter_end);

		String param_userHistory_createdBy_filter = "";
		if(request.getParameter("userHistory_createdBy_filter")!=null){
			param_userHistory_createdBy_filter = request.getParameter("userHistory_createdBy_filter");
			if(param_userHistory_createdBy_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.createdBy like '%"+param_userHistory_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_createdBy_filter", param_userHistory_createdBy_filter);
		String param_userHistory_lastUpdatedDate_filter_start = "";
		if(request.getParameter("userHistory_lastUpdatedDate_filter_start")!=null){
			param_userHistory_lastUpdatedDate_filter_start = request.getParameter("userHistory_lastUpdatedDate_filter_start");
			if(param_userHistory_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastUpdatedDate_filter_start));
					String param_userHistory_lastUpdatedDate_filter_start_cal_val = param_userHistory_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastUpdatedDate >= '"+param_userHistory_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastUpdatedDate_filter_start", param_userHistory_lastUpdatedDate_filter_start);

		String param_userHistory_lastUpdatedDate_filter_end = "";
		if(request.getParameter("userHistory_lastUpdatedDate_filter_end")!=null){
			param_userHistory_lastUpdatedDate_filter_end = request.getParameter("userHistory_lastUpdatedDate_filter_end");
			if(param_userHistory_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastUpdatedDate_filter_end));
					String param_userHistory_lastUpdatedDate_filter_end_cal_val = param_userHistory_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastUpdatedDate  <= '"+param_userHistory_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastUpdatedDate_filter_end", param_userHistory_lastUpdatedDate_filter_end);

		String param_userHistory_lastUpdatedBy_filter = "";
		if(request.getParameter("userHistory_lastUpdatedBy_filter")!=null){
			param_userHistory_lastUpdatedBy_filter = request.getParameter("userHistory_lastUpdatedBy_filter");
			if(param_userHistory_lastUpdatedBy_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.lastUpdatedBy like '%"+param_userHistory_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_lastUpdatedBy_filter", param_userHistory_lastUpdatedBy_filter);
		String param_userHistory_firstLogin_filter = "";
		if(request.getParameter("userHistory_firstLogin_filter")!=null){
			param_userHistory_firstLogin_filter = request.getParameter("userHistory_firstLogin_filter");
			if(param_userHistory_firstLogin_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.firstLogin like '%"+param_userHistory_firstLogin_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_firstLogin_filter", param_userHistory_firstLogin_filter);
		String param_userHistory_lastPasswordUpdate_filter_start = "";
		if(request.getParameter("userHistory_lastPasswordUpdate_filter_start")!=null){
			param_userHistory_lastPasswordUpdate_filter_start = request.getParameter("userHistory_lastPasswordUpdate_filter_start");
			if(param_userHistory_lastPasswordUpdate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastPasswordUpdate_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastPasswordUpdate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastPasswordUpdate_filter_start));
					String param_userHistory_lastPasswordUpdate_filter_start_cal_val = param_userHistory_lastPasswordUpdate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastPasswordUpdate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastPasswordUpdate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastPasswordUpdate >= '"+param_userHistory_lastPasswordUpdate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastPasswordUpdate_filter_start", param_userHistory_lastPasswordUpdate_filter_start);

		String param_userHistory_lastPasswordUpdate_filter_end = "";
		if(request.getParameter("userHistory_lastPasswordUpdate_filter_end")!=null){
			param_userHistory_lastPasswordUpdate_filter_end = request.getParameter("userHistory_lastPasswordUpdate_filter_end");
			if(param_userHistory_lastPasswordUpdate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastPasswordUpdate_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastPasswordUpdate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastPasswordUpdate_filter_end));
					String param_userHistory_lastPasswordUpdate_filter_end_cal_val = param_userHistory_lastPasswordUpdate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastPasswordUpdate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastPasswordUpdate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastPasswordUpdate  <= '"+param_userHistory_lastPasswordUpdate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastPasswordUpdate_filter_end", param_userHistory_lastPasswordUpdate_filter_end);

		String param_userHistory_lastPinCodeUpdate_filter_start = "";
		if(request.getParameter("userHistory_lastPinCodeUpdate_filter_start")!=null){
			param_userHistory_lastPinCodeUpdate_filter_start = request.getParameter("userHistory_lastPinCodeUpdate_filter_start");
			if(param_userHistory_lastPinCodeUpdate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastPinCodeUpdate_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastPinCodeUpdate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastPinCodeUpdate_filter_start));
					String param_userHistory_lastPinCodeUpdate_filter_start_cal_val = param_userHistory_lastPinCodeUpdate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastPinCodeUpdate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastPinCodeUpdate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastPinCodeUpdate >= '"+param_userHistory_lastPinCodeUpdate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastPinCodeUpdate_filter_start", param_userHistory_lastPinCodeUpdate_filter_start);

		String param_userHistory_lastPinCodeUpdate_filter_end = "";
		if(request.getParameter("userHistory_lastPinCodeUpdate_filter_end")!=null){
			param_userHistory_lastPinCodeUpdate_filter_end = request.getParameter("userHistory_lastPinCodeUpdate_filter_end");
			if(param_userHistory_lastPinCodeUpdate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastPinCodeUpdate_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastPinCodeUpdate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastPinCodeUpdate_filter_end));
					String param_userHistory_lastPinCodeUpdate_filter_end_cal_val = param_userHistory_lastPinCodeUpdate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastPinCodeUpdate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastPinCodeUpdate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastPinCodeUpdate  <= '"+param_userHistory_lastPinCodeUpdate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastPinCodeUpdate_filter_end", param_userHistory_lastPinCodeUpdate_filter_end);

		String param_userHistory_lastPassword_filter = "";
		if(request.getParameter("userHistory_lastPassword_filter")!=null){
			param_userHistory_lastPassword_filter = request.getParameter("userHistory_lastPassword_filter");
			if(param_userHistory_lastPassword_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.lastPassword like '%"+param_userHistory_lastPassword_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_lastPassword_filter", param_userHistory_lastPassword_filter);
		String param_userHistory_lastPinCode_filter = "";
		if(request.getParameter("userHistory_lastPinCode_filter")!=null){
			param_userHistory_lastPinCode_filter = request.getParameter("userHistory_lastPinCode_filter");
			if(param_userHistory_lastPinCode_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.lastPinCode like '%"+param_userHistory_lastPinCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_lastPinCode_filter", param_userHistory_lastPinCode_filter);
		String param_userHistory_lastReleasedDate_filter_start = "";
		if(request.getParameter("userHistory_lastReleasedDate_filter_start")!=null){
			param_userHistory_lastReleasedDate_filter_start = request.getParameter("userHistory_lastReleasedDate_filter_start");
			if(param_userHistory_lastReleasedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastReleasedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastReleasedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastReleasedDate_filter_start));
					String param_userHistory_lastReleasedDate_filter_start_cal_val = param_userHistory_lastReleasedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastReleasedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastReleasedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastReleasedDate >= '"+param_userHistory_lastReleasedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastReleasedDate_filter_start", param_userHistory_lastReleasedDate_filter_start);

		String param_userHistory_lastReleasedDate_filter_end = "";
		if(request.getParameter("userHistory_lastReleasedDate_filter_end")!=null){
			param_userHistory_lastReleasedDate_filter_end = request.getParameter("userHistory_lastReleasedDate_filter_end");
			if(param_userHistory_lastReleasedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastReleasedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastReleasedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastReleasedDate_filter_end));
					String param_userHistory_lastReleasedDate_filter_end_cal_val = param_userHistory_lastReleasedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastReleasedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastReleasedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastReleasedDate  <= '"+param_userHistory_lastReleasedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastReleasedDate_filter_end", param_userHistory_lastReleasedDate_filter_end);

		String param_userHistory_lastActive_filter_start = "";
		if(request.getParameter("userHistory_lastActive_filter_start")!=null){
			param_userHistory_lastActive_filter_start = request.getParameter("userHistory_lastActive_filter_start");
			if(param_userHistory_lastActive_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastActive_filter_start_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastActive_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastActive_filter_start));
					String param_userHistory_lastActive_filter_start_cal_val = param_userHistory_lastActive_filter_start_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastActive_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastActive_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastActive >= '"+param_userHistory_lastActive_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastActive_filter_start", param_userHistory_lastActive_filter_start);

		String param_userHistory_lastActive_filter_end = "";
		if(request.getParameter("userHistory_lastActive_filter_end")!=null){
			param_userHistory_lastActive_filter_end = request.getParameter("userHistory_lastActive_filter_end");
			if(param_userHistory_lastActive_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_userHistory_lastActive_filter_end_cal = java.util.Calendar.getInstance();				
					param_userHistory_lastActive_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_userHistory_lastActive_filter_end));
					String param_userHistory_lastActive_filter_end_cal_val = param_userHistory_lastActive_filter_end_cal.get(Calendar.YEAR)+"-"+(param_userHistory_lastActive_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_userHistory_lastActive_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					userHistory_filterSb.append("  AND userHistory.lastActive  <= '"+param_userHistory_lastActive_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("userHistory_lastActive_filter_end", param_userHistory_lastActive_filter_end);

		String param_userHistory_sessionCode_filter = "";
		if(request.getParameter("userHistory_sessionCode_filter")!=null){
			param_userHistory_sessionCode_filter = request.getParameter("userHistory_sessionCode_filter");
			if(param_userHistory_sessionCode_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.sessionCode like '%"+param_userHistory_sessionCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_sessionCode_filter", param_userHistory_sessionCode_filter);
		String param_userHistory_IPassport_filter = "";
		if(request.getParameter("userHistory_IPassport_filter")!=null){
			param_userHistory_IPassport_filter = request.getParameter("userHistory_IPassport_filter");
			if(param_userHistory_IPassport_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.IPassport like '%"+param_userHistory_IPassport_filter+"%' ");
			}
		}
		request.getSession().setAttribute("userHistory_IPassport_filter", param_userHistory_IPassport_filter);
		String param_userHistory_userLevel_filter = "";
		if(request.getParameter("userHistory_userLevel_filter")!=null){
			param_userHistory_userLevel_filter = request.getParameter("userHistory_userLevel_filter");
			if(param_userHistory_userLevel_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.userLevel = '"+param_userHistory_userLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("userHistory_userLevel_filter", param_userHistory_userLevel_filter);
		String param_userHistory_position_filter = "";
		if(request.getParameter("userHistory_position_filter")!=null){
			param_userHistory_position_filter = request.getParameter("userHistory_position_filter");
			if(param_userHistory_position_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.position = '"+param_userHistory_position_filter+"' ");
			}
		}		
		request.getSession().setAttribute("userHistory_position_filter", param_userHistory_position_filter);
		String param_userHistory_status_filter = "";
		if(request.getParameter("userHistory_status_filter")!=null){
			param_userHistory_status_filter = request.getParameter("userHistory_status_filter");
			if(param_userHistory_status_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.status = '"+param_userHistory_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("userHistory_status_filter", param_userHistory_status_filter);
		String param_userHistory_organization_filter = "";
		if(request.getParameter("userHistory_organization_filter")!=null){
			param_userHistory_organization_filter = request.getParameter("userHistory_organization_filter");
			if(param_userHistory_organization_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.organization = '"+param_userHistory_organization_filter+"' ");
			}
		}		
		request.getSession().setAttribute("userHistory_organization_filter", param_userHistory_organization_filter);
		String param_userHistory_securityLevel_filter = "";
		if(request.getParameter("userHistory_securityLevel_filter")!=null){
			param_userHistory_securityLevel_filter = request.getParameter("userHistory_securityLevel_filter");
			if(param_userHistory_securityLevel_filter.length() > 0 ){				
				userHistory_filterSb.append("  AND userHistory.securityLevel = '"+param_userHistory_securityLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("userHistory_securityLevel_filter", param_userHistory_securityLevel_filter);
		
		if(userHistory_fieldOrder!=null && userHistory_orderType != null )userHistory_filterSb.append(" ORDER BY "+userHistory_fieldOrder+" "+userHistory_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		UserHistoryService userHistoryService = UserHistoryService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList userHistoryList = userHistoryService.getPartialList(userHistory_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, userHistoryList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, userHistoryList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("userHistoryList", userHistoryList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("userHistory");
		
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
    		UserHistoryService userHistoryService = UserHistoryService.getInstance();
    		List userHistoryList = userHistoryService.getList(parentFilter , null);
    		request.setAttribute("userHistoryList", userHistoryList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do");
    			return null;
    		}
    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
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
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do");
    			return null;
    		}
    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
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
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null) userHistory = new UserHistory();
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
			List positionList = positionService.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList", positionList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='UserHistory'  ", null);
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

			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);

			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			
    		request.getSession().setAttribute("userHistory", userHistory);
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
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, userHistory, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
			List positionList = positionService.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList", positionList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='UserHistory'  ", null);
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
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);
			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);
			Set topicSet = userHistory.getFavoriteTopic();			
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
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","new"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setCreatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
			userHistory.setCreatedBy(_doneBy);
    		UserHistoryService.getInstance().add(userHistory);
    		request.getSession().removeAttribute("userHistory");
    		response.sendRedirect("userHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
			List positionList = positionService.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList", positionList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='UserHistory'  ", null);
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
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);
			Set topicSet = userHistory.getFavoriteTopic();			
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
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, userHistory, errors);
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
			List positionList = positionService.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList", positionList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='UserHistory'  ", null);
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
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			TopicService topicService = TopicService.getInstance();
			List topicSetList = topicService.getList(null, null);
			request.setAttribute("topicSetList", topicSetList);
			Set topicSet = userHistory.getFavoriteTopic();			
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
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=edit_confirm");
    		}
    		
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		request.getSession().removeAttribute("userHistory");
    		response.sendRedirect("userHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=delete_confirm");
    		}
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","deleted"));
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("userHistory.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=submit_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","submitted"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=approve_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","approved"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=reject_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","rejected"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=pending_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","pending"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=process_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","processed"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("activate_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=activate_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","activated"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=close_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","closed"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=archive_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","archived"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=remove_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","removed"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("block_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=block_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","blocked"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if (userHistory == null){
	    		userHistory = UserHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("userHistory", userHistory);
	    	}
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}
    		    		
			Set roleSet = userHistory.getRoles();			
			if(roleSet == null) roleSet = new HashSet();
			request.setAttribute("roleSet", roleSet);			
			Set topicSet = userHistory.getFavoriteTopic();			
			if(topicSet == null) topicSet = new HashSet();
			request.setAttribute("topicSet", topicSet);			

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("userHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		UserHistory userHistory = (UserHistory) request.getSession().getAttribute("userHistory");
    		if(userHistory == null){
    			response.sendRedirect("userHistory.do?action=cancel_confirm");
    		}
    		userHistory.setStatus(StatusService.getInstance().getByTypeandCode("UserHistory","cancelled"));
			userHistory.setLastUpdatedDate(new Date());
			userHistory.setLastUpdatedBy(_doneBy);
    		UserHistoryService.getInstance().update(userHistory);
    		response.sendRedirect("userHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("userHistory.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, UserHistory userHistory, ActionErrors errors){
    	try{    		
			String historyDate = request.getParameter("historyDate");
			if(historyDate==null || historyDate.trim().length() == 0 ){
				userHistory.setHistoryDate(null);
			}else{
				try{
					java.util.Calendar historyDateCalendar = java.util.Calendar.getInstance();
					historyDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(historyDate));			
					userHistory.setHistoryDate(historyDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(historyDate==null || historyDate.trim().length() == 0 ){
				errors.add("userHistory.historyDate", new ActionError("error.userHistory.historyDate"));
			}
			String historyBy = request.getParameter("historyBy");
			userHistory.setHistoryBy(historyBy);
			if(historyBy==null || historyBy.trim().length() == 0 ){
				errors.add("userHistory.historyBy", new ActionError("error.userHistory.historyBy"));
			}
			String auditTrailId = request.getParameter("auditTrailId");
			try{
				userHistory.setAuditTrailId(new java.lang.Long(auditTrailId));
			}catch(Exception ex){}
			if(auditTrailId==null || auditTrailId.trim().length() == 0 ){
				errors.add("userHistory.auditTrailId", new ActionError("error.userHistory.auditTrailId"));
			}
			String userId = request.getParameter("userId");
			try{
				userHistory.setUserId(new java.lang.Long(userId));
			}catch(Exception ex){}
			if(userId==null || userId.trim().length() == 0 ){
				errors.add("userHistory.userId", new ActionError("error.userHistory.userId"));
			}
			String loginName = request.getParameter("loginName");
			userHistory.setLoginName(loginName);
			if(loginName==null || loginName.trim().length() == 0 ){
				errors.add("userHistory.loginName", new ActionError("error.userHistory.loginName"));
			}
			String loginPassword = request.getParameter("loginPassword");
			userHistory.setLoginPassword(loginPassword);
			if(loginPassword==null || loginPassword.trim().length() == 0 ){
				errors.add("userHistory.loginPassword", new ActionError("error.userHistory.loginPassword"));
			}
			String pinCode = request.getParameter("pinCode");
			userHistory.setPinCode(pinCode);
			String picture = request.getParameter("picture");
			userHistory.setPicture(picture);
			String language = request.getParameter("language");
			userHistory.setLanguage(language);
			String title = request.getParameter("title");
			userHistory.setTitle(title);
			String name = request.getParameter("name");
			userHistory.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("userHistory.name", new ActionError("error.userHistory.name"));
			}
			String alias = request.getParameter("alias");
			userHistory.setAlias(alias);
			String email = request.getParameter("email");
			userHistory.setEmail(email);
			String fullName = request.getParameter("fullName");
			userHistory.setFullName(fullName);
			String homePhoneNumber = request.getParameter("homePhoneNumber");
			userHistory.setHomePhoneNumber(homePhoneNumber);
			String mobilePhoneNumber = request.getParameter("mobilePhoneNumber");
			userHistory.setMobilePhoneNumber(mobilePhoneNumber);
			String employeeNumber = request.getParameter("employeeNumber");
			userHistory.setEmployeeNumber(employeeNumber);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				userHistory.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					userHistory.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("userHistory.createdDate", new ActionError("error.userHistory.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			userHistory.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("userHistory.createdBy", new ActionError("error.userHistory.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				userHistory.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					userHistory.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			userHistory.setLastUpdatedBy(lastUpdatedBy);
*/ 			String firstLogin = request.getParameter("firstLogin");
			userHistory.setFirstLogin(firstLogin);
			String lastPasswordUpdate = request.getParameter("lastPasswordUpdate");
			if(lastPasswordUpdate==null || lastPasswordUpdate.trim().length() == 0 ){
				userHistory.setLastPasswordUpdate(null);
			}else{
				try{
					java.util.Calendar lastPasswordUpdateCalendar = java.util.Calendar.getInstance();
					lastPasswordUpdateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastPasswordUpdate));			
					userHistory.setLastPasswordUpdate(lastPasswordUpdateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastPinCodeUpdate = request.getParameter("lastPinCodeUpdate");
			if(lastPinCodeUpdate==null || lastPinCodeUpdate.trim().length() == 0 ){
				userHistory.setLastPinCodeUpdate(null);
			}else{
				try{
					java.util.Calendar lastPinCodeUpdateCalendar = java.util.Calendar.getInstance();
					lastPinCodeUpdateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastPinCodeUpdate));			
					userHistory.setLastPinCodeUpdate(lastPinCodeUpdateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastPassword = request.getParameter("lastPassword");
			userHistory.setLastPassword(lastPassword);
			String lastPinCode = request.getParameter("lastPinCode");
			userHistory.setLastPinCode(lastPinCode);
			String loginFailed = request.getParameter("loginFailed");
			try{
				userHistory.setLoginFailed(new java.lang.Integer(loginFailed));
			}catch(Exception ex){}
			String maxRelease = request.getParameter("maxRelease");
			try{
				userHistory.setMaxRelease(new java.lang.Integer(maxRelease));
			}catch(Exception ex){}
			String lastReleasedDate = request.getParameter("lastReleasedDate");
			if(lastReleasedDate==null || lastReleasedDate.trim().length() == 0 ){
				userHistory.setLastReleasedDate(null);
			}else{
				try{
					java.util.Calendar lastReleasedDateCalendar = java.util.Calendar.getInstance();
					lastReleasedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastReleasedDate));			
					userHistory.setLastReleasedDate(lastReleasedDateCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastActive = request.getParameter("lastActive");
			if(lastActive==null || lastActive.trim().length() == 0 ){
				userHistory.setLastActive(null);
			}else{
				try{
					java.util.Calendar lastActiveCalendar = java.util.Calendar.getInstance();
					lastActiveCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastActive));			
					userHistory.setLastActive(lastActiveCalendar.getTime());
				}catch(Exception ex){}
			}
			String sessionCode = request.getParameter("sessionCode");
			userHistory.setSessionCode(sessionCode);
			String IPassport = request.getParameter("IPassport");
			userHistory.setIPassport(IPassport);

			com.app.docmgr.model.Lookup  userLevelObj =null;
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String userLevelStr = request.getParameter("userLevel");
				
				if(userLevelStr == null || userLevelStr.trim().length() == 0 ){
					userHistory.setUserLevel(null);
				}else{			
					userLevelObj = userLevelService.get(new Long(userLevelStr));
					userHistory.setUserLevel(userLevelObj);
				}
			}catch(Exception ex){}	
			if(userLevelObj==null){
				errors.add("userHistory.userLevel", new ActionError("error.userHistory.userLevel"));
			}
			com.app.docmgr.model.Lookup  positionObj =null;
			com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String positionStr = request.getParameter("position");
				
				if(positionStr == null || positionStr.trim().length() == 0 ){
					userHistory.setPosition(null);
				}else{			
					positionObj = positionService.get(new Long(positionStr));
					userHistory.setPosition(positionObj);
				}
			}catch(Exception ex){}	
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					userHistory.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					userHistory.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("userHistory.status", new ActionError("error.userHistory.status"));
			}
*/ 			com.app.docmgr.model.Organization  organizationObj =null;
			com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
			try{
				String organizationStr = request.getParameter("organization");
				
				if(organizationStr == null || organizationStr.trim().length() == 0 ){
					userHistory.setOrganization(null);
				}else{			
					organizationObj = organizationService.get(new Long(organizationStr));
					userHistory.setOrganization(organizationObj);
				}
			}catch(Exception ex){}	
			com.app.docmgr.model.Lookup  securityLevelObj =null;
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String securityLevelStr = request.getParameter("securityLevel");
				
				if(securityLevelStr == null || securityLevelStr.trim().length() == 0 ){
					userHistory.setSecurityLevel(null);
				}else{			
					securityLevelObj = securityLevelService.get(new Long(securityLevelStr));
					userHistory.setSecurityLevel(securityLevelObj);
				}
			}catch(Exception ex){}	

			Set roleSet = new HashSet();
			String[] _selectedRole=request.getParameterValues("selected_role");
			if (_selectedRole!=null && _selectedRole.length>0) {
				RoleService roleService = RoleService.getInstance();
				Set _orgRoleSet = userHistory.getRoles();
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
			userHistory.setRoles(roleSet);
			Set topicSet = new HashSet();
			String[] _selectedTopic=request.getParameterValues("selected_topic");
			if (_selectedTopic!=null && _selectedTopic.length>0) {
				TopicService topicService = TopicService.getInstance();
				Set _orgTopicSet = userHistory.getFavoriteTopic();
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
			userHistory.setFavoriteTopic(topicSet);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

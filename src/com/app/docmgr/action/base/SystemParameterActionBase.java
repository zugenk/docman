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
 * @createDate 03-10-2017 20:59:59
 */


public class SystemParameterActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.SystemParameterActionBase");	
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
    	
    	request.getSession().removeAttribute("systemParameter");
		
		String systemParameter_filter = request.getParameter("systemParameter_filter");
		//this for orderBy field ASC/DESC
		String systemParameter_fieldOrder = request.getParameter("systemParameter_fieldOrder");
		String systemParameter_orderType = request.getParameter("systemParameter_orderType");
		
		if(systemParameter_fieldOrder == null || systemParameter_fieldOrder.length() == 0) systemParameter_fieldOrder = null;
		if(systemParameter_orderType == null || systemParameter_orderType.length() == 0) systemParameter_orderType = null;
		request.getSession().setAttribute("systemParameter_fieldOrder", systemParameter_fieldOrder==null?"":systemParameter_fieldOrder);	 
		request.getSession().setAttribute("systemParameter_orderType", systemParameter_orderType==null?"":systemParameter_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameter'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
		}catch(Exception ex){
		
		}
		StringBuffer systemParameter_filterSb = new StringBuffer();
		String param_systemParameter_vgroup_filter = "";
		if(request.getParameter("systemParameter_vgroup_filter")!=null){
			param_systemParameter_vgroup_filter = request.getParameter("systemParameter_vgroup_filter");
			if(param_systemParameter_vgroup_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.vgroup like '%"+param_systemParameter_vgroup_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameter_vgroup_filter", param_systemParameter_vgroup_filter);
		String param_systemParameter_parameter_filter = "";
		if(request.getParameter("systemParameter_parameter_filter")!=null){
			param_systemParameter_parameter_filter = request.getParameter("systemParameter_parameter_filter");
			if(param_systemParameter_parameter_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.parameter like '%"+param_systemParameter_parameter_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameter_parameter_filter", param_systemParameter_parameter_filter);
		String param_systemParameter_svalue_filter = "";
		if(request.getParameter("systemParameter_svalue_filter")!=null){
			param_systemParameter_svalue_filter = request.getParameter("systemParameter_svalue_filter");
			if(param_systemParameter_svalue_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.svalue like '%"+param_systemParameter_svalue_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameter_svalue_filter", param_systemParameter_svalue_filter);
		String param_systemParameter_description_filter = "";
		if(request.getParameter("systemParameter_description_filter")!=null){
			param_systemParameter_description_filter = request.getParameter("systemParameter_description_filter");
			if(param_systemParameter_description_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.description like '%"+param_systemParameter_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameter_description_filter", param_systemParameter_description_filter);
		String param_systemParameter_createdDate_filter_start = "";
		if(request.getParameter("systemParameter_createdDate_filter_start")!=null){
			param_systemParameter_createdDate_filter_start = request.getParameter("systemParameter_createdDate_filter_start");
			if(param_systemParameter_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_systemParameter_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_systemParameter_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameter_createdDate_filter_start));
					String param_systemParameter_createdDate_filter_start_cal_val = param_systemParameter_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_systemParameter_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameter_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					systemParameter_filterSb.append("  AND systemParameter.createdDate >= '"+param_systemParameter_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameter_createdDate_filter_start", param_systemParameter_createdDate_filter_start);

		String param_systemParameter_createdDate_filter_end = "";
		if(request.getParameter("systemParameter_createdDate_filter_end")!=null){
			param_systemParameter_createdDate_filter_end = request.getParameter("systemParameter_createdDate_filter_end");
			if(param_systemParameter_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_systemParameter_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_systemParameter_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameter_createdDate_filter_end));
					String param_systemParameter_createdDate_filter_end_cal_val = param_systemParameter_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_systemParameter_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameter_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					systemParameter_filterSb.append("  AND systemParameter.createdDate  <= '"+param_systemParameter_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameter_createdDate_filter_end", param_systemParameter_createdDate_filter_end);

		String param_systemParameter_createdBy_filter = "";
		if(request.getParameter("systemParameter_createdBy_filter")!=null){
			param_systemParameter_createdBy_filter = request.getParameter("systemParameter_createdBy_filter");
			if(param_systemParameter_createdBy_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.createdBy like '%"+param_systemParameter_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameter_createdBy_filter", param_systemParameter_createdBy_filter);
		String param_systemParameter_lastUpdatedDate_filter_start = "";
		if(request.getParameter("systemParameter_lastUpdatedDate_filter_start")!=null){
			param_systemParameter_lastUpdatedDate_filter_start = request.getParameter("systemParameter_lastUpdatedDate_filter_start");
			if(param_systemParameter_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_systemParameter_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_systemParameter_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameter_lastUpdatedDate_filter_start));
					String param_systemParameter_lastUpdatedDate_filter_start_cal_val = param_systemParameter_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_systemParameter_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameter_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					systemParameter_filterSb.append("  AND systemParameter.lastUpdatedDate >= '"+param_systemParameter_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameter_lastUpdatedDate_filter_start", param_systemParameter_lastUpdatedDate_filter_start);

		String param_systemParameter_lastUpdatedDate_filter_end = "";
		if(request.getParameter("systemParameter_lastUpdatedDate_filter_end")!=null){
			param_systemParameter_lastUpdatedDate_filter_end = request.getParameter("systemParameter_lastUpdatedDate_filter_end");
			if(param_systemParameter_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_systemParameter_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_systemParameter_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameter_lastUpdatedDate_filter_end));
					String param_systemParameter_lastUpdatedDate_filter_end_cal_val = param_systemParameter_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_systemParameter_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameter_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					systemParameter_filterSb.append("  AND systemParameter.lastUpdatedDate  <= '"+param_systemParameter_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameter_lastUpdatedDate_filter_end", param_systemParameter_lastUpdatedDate_filter_end);

		String param_systemParameter_lastUpdatedBy_filter = "";
		if(request.getParameter("systemParameter_lastUpdatedBy_filter")!=null){
			param_systemParameter_lastUpdatedBy_filter = request.getParameter("systemParameter_lastUpdatedBy_filter");
			if(param_systemParameter_lastUpdatedBy_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.lastUpdatedBy like '%"+param_systemParameter_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameter_lastUpdatedBy_filter", param_systemParameter_lastUpdatedBy_filter);
		String param_systemParameter_status_filter = "";
		if(request.getParameter("systemParameter_status_filter")!=null){
			param_systemParameter_status_filter = request.getParameter("systemParameter_status_filter");
			if(param_systemParameter_status_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.status = '"+param_systemParameter_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("systemParameter_status_filter", param_systemParameter_status_filter);
		String param_systemParameter_userLevel_filter = "";
		if(request.getParameter("systemParameter_userLevel_filter")!=null){
			param_systemParameter_userLevel_filter = request.getParameter("systemParameter_userLevel_filter");
			if(param_systemParameter_userLevel_filter.length() > 0 ){				
				systemParameter_filterSb.append("  AND systemParameter.userLevel = '"+param_systemParameter_userLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("systemParameter_userLevel_filter", param_systemParameter_userLevel_filter);
		
		if(systemParameter_fieldOrder!=null && systemParameter_orderType != null )systemParameter_filterSb.append(" ORDER BY "+systemParameter_fieldOrder+" "+systemParameter_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		SystemParameterService systemParameterService = SystemParameterService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList systemParameterList = systemParameterService.getPartialList(systemParameter_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, systemParameterList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, systemParameterList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("systemParameterList", systemParameterList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("systemParameter");
		
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
    		SystemParameterService systemParameterService = SystemParameterService.getInstance();
    		List systemParameterList = systemParameterService.getList(parentFilter , null);
    		request.setAttribute("systemParameterList", systemParameterList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do");
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
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do");
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
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null) systemParameter = new SystemParameter();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameter'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);

    		request.getSession().setAttribute("systemParameter", systemParameter);
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
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, systemParameter, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameter'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
	
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
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","new"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setCreatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
			systemParameter.setCreatedBy(_doneBy);
    		SystemParameterService.getInstance().add(systemParameter);
    		request.getSession().removeAttribute("systemParameter");
    		response.sendRedirect("systemParameter.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameter'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);

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
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, systemParameter, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameter'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);

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
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=edit_confirm");
    		}
    		
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		request.getSession().removeAttribute("systemParameter");
    		response.sendRedirect("systemParameter.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=delete_confirm");
    		}
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","deleted"));
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("systemParameter.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=submit_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","submitted"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=approve_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","approved"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=reject_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","rejected"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=pending_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","pending"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=process_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","processed"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=close_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","closed"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=remove_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","removed"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if (systemParameter == null){
	    		systemParameter = SystemParameterService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameter", systemParameter);
	    	}
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameter.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameter systemParameter = (SystemParameter) request.getSession().getAttribute("systemParameter");
    		if(systemParameter == null){
    			response.sendRedirect("systemParameter.do?action=cancel_confirm");
    		}
    		systemParameter.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameter","cancelled"));
			systemParameter.setLastUpdatedDate(new Date());
			systemParameter.setLastUpdatedBy(_doneBy);
    		SystemParameterService.getInstance().update(systemParameter);
    		response.sendRedirect("systemParameter.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameter.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, SystemParameter systemParameter, ActionErrors errors){
    	try{    		
			String vgroup = request.getParameter("vgroup");
			systemParameter.setVgroup(vgroup);
			if(vgroup==null || vgroup.trim().length() == 0 ){
				errors.add("systemParameter.vgroup", new ActionError("error.systemParameter.vgroup"));
			}
			String parameter = request.getParameter("parameter");
			systemParameter.setParameter(parameter);
			if(parameter==null || parameter.trim().length() == 0 ){
				errors.add("systemParameter.parameter", new ActionError("error.systemParameter.parameter"));
			}
			String svalue = request.getParameter("svalue");
			systemParameter.setSvalue(svalue);
			String description = request.getParameter("description");
			systemParameter.setDescription(description);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				systemParameter.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					systemParameter.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("systemParameter.createdDate", new ActionError("error.systemParameter.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			systemParameter.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("systemParameter.createdBy", new ActionError("error.systemParameter.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				systemParameter.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					systemParameter.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			systemParameter.setLastUpdatedBy(lastUpdatedBy);
*/ 
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					systemParameter.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					systemParameter.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("systemParameter.status", new ActionError("error.systemParameter.status"));
			}
*/ 			com.app.docmgr.model.Lookup  userLevelObj =null;
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String userLevelStr = request.getParameter("userLevel");
				
				if(userLevelStr == null || userLevelStr.trim().length() == 0 ){
					systemParameter.setUserLevel(null);
				}else{			
					userLevelObj = userLevelService.get(new Long(userLevelStr));
					systemParameter.setUserLevel(userLevelObj);
				}
			}catch(Exception ex){}	
			if(userLevelObj==null){
				errors.add("systemParameter.userLevel", new ActionError("error.systemParameter.userLevel"));
			}

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

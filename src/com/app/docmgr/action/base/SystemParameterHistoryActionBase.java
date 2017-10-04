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


public class SystemParameterHistoryActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.SystemParameterHistoryActionBase");	
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
    	
    	request.getSession().removeAttribute("systemParameterHistory");
		
		String systemParameterHistory_filter = request.getParameter("systemParameterHistory_filter");
		//this for orderBy field ASC/DESC
		String systemParameterHistory_fieldOrder = request.getParameter("systemParameterHistory_fieldOrder");
		String systemParameterHistory_orderType = request.getParameter("systemParameterHistory_orderType");
		
		if(systemParameterHistory_fieldOrder == null || systemParameterHistory_fieldOrder.length() == 0) systemParameterHistory_fieldOrder = null;
		if(systemParameterHistory_orderType == null || systemParameterHistory_orderType.length() == 0) systemParameterHistory_orderType = null;
		request.getSession().setAttribute("systemParameterHistory_fieldOrder", systemParameterHistory_fieldOrder==null?"":systemParameterHistory_fieldOrder);	 
		request.getSession().setAttribute("systemParameterHistory_orderType", systemParameterHistory_orderType==null?"":systemParameterHistory_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameterHistory'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer systemParameterHistory_filterSb = new StringBuffer();
		String param_systemParameterHistory_historyDate_filter_start = "";
		if(request.getParameter("systemParameterHistory_historyDate_filter_start")!=null){
			param_systemParameterHistory_historyDate_filter_start = request.getParameter("systemParameterHistory_historyDate_filter_start");
			if(param_systemParameterHistory_historyDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_systemParameterHistory_historyDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_systemParameterHistory_historyDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameterHistory_historyDate_filter_start));
					String param_systemParameterHistory_historyDate_filter_start_cal_val = param_systemParameterHistory_historyDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_systemParameterHistory_historyDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameterHistory_historyDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					systemParameterHistory_filterSb.append("  AND systemParameterHistory.historyDate >= '"+param_systemParameterHistory_historyDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameterHistory_historyDate_filter_start", param_systemParameterHistory_historyDate_filter_start);

		String param_systemParameterHistory_historyDate_filter_end = "";
		if(request.getParameter("systemParameterHistory_historyDate_filter_end")!=null){
			param_systemParameterHistory_historyDate_filter_end = request.getParameter("systemParameterHistory_historyDate_filter_end");
			if(param_systemParameterHistory_historyDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_systemParameterHistory_historyDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_systemParameterHistory_historyDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameterHistory_historyDate_filter_end));
					String param_systemParameterHistory_historyDate_filter_end_cal_val = param_systemParameterHistory_historyDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_systemParameterHistory_historyDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameterHistory_historyDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					systemParameterHistory_filterSb.append("  AND systemParameterHistory.historyDate  <= '"+param_systemParameterHistory_historyDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameterHistory_historyDate_filter_end", param_systemParameterHistory_historyDate_filter_end);

		String param_systemParameterHistory_historyBy_filter = "";
		if(request.getParameter("systemParameterHistory_historyBy_filter")!=null){
			param_systemParameterHistory_historyBy_filter = request.getParameter("systemParameterHistory_historyBy_filter");
			if(param_systemParameterHistory_historyBy_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.historyBy like '%"+param_systemParameterHistory_historyBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_historyBy_filter", param_systemParameterHistory_historyBy_filter);
		String param_systemParameterHistory_vgroup_filter = "";
		if(request.getParameter("systemParameterHistory_vgroup_filter")!=null){
			param_systemParameterHistory_vgroup_filter = request.getParameter("systemParameterHistory_vgroup_filter");
			if(param_systemParameterHistory_vgroup_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.vgroup like '%"+param_systemParameterHistory_vgroup_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_vgroup_filter", param_systemParameterHistory_vgroup_filter);
		String param_systemParameterHistory_parameter_filter = "";
		if(request.getParameter("systemParameterHistory_parameter_filter")!=null){
			param_systemParameterHistory_parameter_filter = request.getParameter("systemParameterHistory_parameter_filter");
			if(param_systemParameterHistory_parameter_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.parameter like '%"+param_systemParameterHistory_parameter_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_parameter_filter", param_systemParameterHistory_parameter_filter);
		String param_systemParameterHistory_svalue_filter = "";
		if(request.getParameter("systemParameterHistory_svalue_filter")!=null){
			param_systemParameterHistory_svalue_filter = request.getParameter("systemParameterHistory_svalue_filter");
			if(param_systemParameterHistory_svalue_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.svalue like '%"+param_systemParameterHistory_svalue_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_svalue_filter", param_systemParameterHistory_svalue_filter);
		String param_systemParameterHistory_description_filter = "";
		if(request.getParameter("systemParameterHistory_description_filter")!=null){
			param_systemParameterHistory_description_filter = request.getParameter("systemParameterHistory_description_filter");
			if(param_systemParameterHistory_description_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.description like '%"+param_systemParameterHistory_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_description_filter", param_systemParameterHistory_description_filter);
		String param_systemParameterHistory_createdDate_filter_start = "";
		if(request.getParameter("systemParameterHistory_createdDate_filter_start")!=null){
			param_systemParameterHistory_createdDate_filter_start = request.getParameter("systemParameterHistory_createdDate_filter_start");
			if(param_systemParameterHistory_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_systemParameterHistory_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_systemParameterHistory_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameterHistory_createdDate_filter_start));
					String param_systemParameterHistory_createdDate_filter_start_cal_val = param_systemParameterHistory_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_systemParameterHistory_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameterHistory_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					systemParameterHistory_filterSb.append("  AND systemParameterHistory.createdDate >= '"+param_systemParameterHistory_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameterHistory_createdDate_filter_start", param_systemParameterHistory_createdDate_filter_start);

		String param_systemParameterHistory_createdDate_filter_end = "";
		if(request.getParameter("systemParameterHistory_createdDate_filter_end")!=null){
			param_systemParameterHistory_createdDate_filter_end = request.getParameter("systemParameterHistory_createdDate_filter_end");
			if(param_systemParameterHistory_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_systemParameterHistory_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_systemParameterHistory_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameterHistory_createdDate_filter_end));
					String param_systemParameterHistory_createdDate_filter_end_cal_val = param_systemParameterHistory_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_systemParameterHistory_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameterHistory_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					systemParameterHistory_filterSb.append("  AND systemParameterHistory.createdDate  <= '"+param_systemParameterHistory_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameterHistory_createdDate_filter_end", param_systemParameterHistory_createdDate_filter_end);

		String param_systemParameterHistory_createdBy_filter = "";
		if(request.getParameter("systemParameterHistory_createdBy_filter")!=null){
			param_systemParameterHistory_createdBy_filter = request.getParameter("systemParameterHistory_createdBy_filter");
			if(param_systemParameterHistory_createdBy_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.createdBy like '%"+param_systemParameterHistory_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_createdBy_filter", param_systemParameterHistory_createdBy_filter);
		String param_systemParameterHistory_lastUpdatedDate_filter_start = "";
		if(request.getParameter("systemParameterHistory_lastUpdatedDate_filter_start")!=null){
			param_systemParameterHistory_lastUpdatedDate_filter_start = request.getParameter("systemParameterHistory_lastUpdatedDate_filter_start");
			if(param_systemParameterHistory_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_systemParameterHistory_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_systemParameterHistory_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameterHistory_lastUpdatedDate_filter_start));
					String param_systemParameterHistory_lastUpdatedDate_filter_start_cal_val = param_systemParameterHistory_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_systemParameterHistory_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameterHistory_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					systemParameterHistory_filterSb.append("  AND systemParameterHistory.lastUpdatedDate >= '"+param_systemParameterHistory_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameterHistory_lastUpdatedDate_filter_start", param_systemParameterHistory_lastUpdatedDate_filter_start);

		String param_systemParameterHistory_lastUpdatedDate_filter_end = "";
		if(request.getParameter("systemParameterHistory_lastUpdatedDate_filter_end")!=null){
			param_systemParameterHistory_lastUpdatedDate_filter_end = request.getParameter("systemParameterHistory_lastUpdatedDate_filter_end");
			if(param_systemParameterHistory_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_systemParameterHistory_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_systemParameterHistory_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_systemParameterHistory_lastUpdatedDate_filter_end));
					String param_systemParameterHistory_lastUpdatedDate_filter_end_cal_val = param_systemParameterHistory_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_systemParameterHistory_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_systemParameterHistory_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					systemParameterHistory_filterSb.append("  AND systemParameterHistory.lastUpdatedDate  <= '"+param_systemParameterHistory_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("systemParameterHistory_lastUpdatedDate_filter_end", param_systemParameterHistory_lastUpdatedDate_filter_end);

		String param_systemParameterHistory_lastUpdatedBy_filter = "";
		if(request.getParameter("systemParameterHistory_lastUpdatedBy_filter")!=null){
			param_systemParameterHistory_lastUpdatedBy_filter = request.getParameter("systemParameterHistory_lastUpdatedBy_filter");
			if(param_systemParameterHistory_lastUpdatedBy_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.lastUpdatedBy like '%"+param_systemParameterHistory_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemParameterHistory_lastUpdatedBy_filter", param_systemParameterHistory_lastUpdatedBy_filter);
		String param_systemParameterHistory_status_filter = "";
		if(request.getParameter("systemParameterHistory_status_filter")!=null){
			param_systemParameterHistory_status_filter = request.getParameter("systemParameterHistory_status_filter");
			if(param_systemParameterHistory_status_filter.length() > 0 ){				
				systemParameterHistory_filterSb.append("  AND systemParameterHistory.status = '"+param_systemParameterHistory_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("systemParameterHistory_status_filter", param_systemParameterHistory_status_filter);
		
		if(systemParameterHistory_fieldOrder!=null && systemParameterHistory_orderType != null )systemParameterHistory_filterSb.append(" ORDER BY "+systemParameterHistory_fieldOrder+" "+systemParameterHistory_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		SystemParameterHistoryService systemParameterHistoryService = SystemParameterHistoryService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList systemParameterHistoryList = systemParameterHistoryService.getPartialList(systemParameterHistory_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, systemParameterHistoryList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, systemParameterHistoryList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("systemParameterHistoryList", systemParameterHistoryList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("systemParameterHistory");
		
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
    		SystemParameterHistoryService systemParameterHistoryService = SystemParameterHistoryService.getInstance();
    		List systemParameterHistoryList = systemParameterHistoryService.getList(parentFilter , null);
    		request.setAttribute("systemParameterHistoryList", systemParameterHistoryList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do");
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
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do");
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
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null) systemParameterHistory = new SystemParameterHistory();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameterHistory'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
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
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, systemParameterHistory, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameterHistory'  ", null);
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
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","new"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setCreatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
			systemParameterHistory.setCreatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().add(systemParameterHistory);
    		request.getSession().removeAttribute("systemParameterHistory");
    		response.sendRedirect("systemParameterHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameterHistory'  ", null);
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
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, systemParameterHistory, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SystemParameterHistory'  ", null);
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
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=edit_confirm");
    		}
    		
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		request.getSession().removeAttribute("systemParameterHistory");
    		response.sendRedirect("systemParameterHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=delete_confirm");
    		}
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","deleted"));
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=submit_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","submitted"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=approve_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","approved"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=reject_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","rejected"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=pending_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","pending"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=process_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","processed"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=close_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","closed"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=remove_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","removed"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if (systemParameterHistory == null){
	    		systemParameterHistory = SystemParameterHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemParameterHistory", systemParameterHistory);
	    	}
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemParameterHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SystemParameterHistory systemParameterHistory = (SystemParameterHistory) request.getSession().getAttribute("systemParameterHistory");
    		if(systemParameterHistory == null){
    			response.sendRedirect("systemParameterHistory.do?action=cancel_confirm");
    		}
    		systemParameterHistory.setStatus(StatusService.getInstance().getByTypeandCode("SystemParameterHistory","cancelled"));
			systemParameterHistory.setLastUpdatedDate(new Date());
			systemParameterHistory.setLastUpdatedBy(_doneBy);
    		SystemParameterHistoryService.getInstance().update(systemParameterHistory);
    		response.sendRedirect("systemParameterHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemParameterHistory.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, SystemParameterHistory systemParameterHistory, ActionErrors errors){
    	try{    		
			String historyDate = request.getParameter("historyDate");
			if(historyDate==null || historyDate.trim().length() == 0 ){
				systemParameterHistory.setHistoryDate(null);
			}else{
				try{
					java.util.Calendar historyDateCalendar = java.util.Calendar.getInstance();
					historyDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(historyDate));			
					systemParameterHistory.setHistoryDate(historyDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(historyDate==null || historyDate.trim().length() == 0 ){
				errors.add("systemParameterHistory.historyDate", new ActionError("error.systemParameterHistory.historyDate"));
			}
			String historyBy = request.getParameter("historyBy");
			systemParameterHistory.setHistoryBy(historyBy);
			if(historyBy==null || historyBy.trim().length() == 0 ){
				errors.add("systemParameterHistory.historyBy", new ActionError("error.systemParameterHistory.historyBy"));
			}
			String auditTrailId = request.getParameter("auditTrailId");
			try{
				systemParameterHistory.setAuditTrailId(new java.lang.Long(auditTrailId));
			}catch(Exception ex){}
			if(auditTrailId==null || auditTrailId.trim().length() == 0 ){
				errors.add("systemParameterHistory.auditTrailId", new ActionError("error.systemParameterHistory.auditTrailId"));
			}
			String systemParameterId = request.getParameter("systemParameterId");
			try{
				systemParameterHistory.setSystemParameterId(new java.lang.Long(systemParameterId));
			}catch(Exception ex){}
			if(systemParameterId==null || systemParameterId.trim().length() == 0 ){
				errors.add("systemParameterHistory.systemParameterId", new ActionError("error.systemParameterHistory.systemParameterId"));
			}
			String vgroup = request.getParameter("vgroup");
			systemParameterHistory.setVgroup(vgroup);
			if(vgroup==null || vgroup.trim().length() == 0 ){
				errors.add("systemParameterHistory.vgroup", new ActionError("error.systemParameterHistory.vgroup"));
			}
			String parameter = request.getParameter("parameter");
			systemParameterHistory.setParameter(parameter);
			if(parameter==null || parameter.trim().length() == 0 ){
				errors.add("systemParameterHistory.parameter", new ActionError("error.systemParameterHistory.parameter"));
			}
			String svalue = request.getParameter("svalue");
			systemParameterHistory.setSvalue(svalue);
			String description = request.getParameter("description");
			systemParameterHistory.setDescription(description);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				systemParameterHistory.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					systemParameterHistory.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("systemParameterHistory.createdDate", new ActionError("error.systemParameterHistory.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			systemParameterHistory.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("systemParameterHistory.createdBy", new ActionError("error.systemParameterHistory.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				systemParameterHistory.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					systemParameterHistory.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			systemParameterHistory.setLastUpdatedBy(lastUpdatedBy);
*/ 
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					systemParameterHistory.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					systemParameterHistory.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("systemParameterHistory.status", new ActionError("error.systemParameterHistory.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

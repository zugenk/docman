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


public class GenericReportActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.GenericReportActionBase");	
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
    	
    	request.getSession().removeAttribute("genericReport");
		
		String genericReport_filter = request.getParameter("genericReport_filter");
		//this for orderBy field ASC/DESC
		String genericReport_fieldOrder = request.getParameter("genericReport_fieldOrder");
		String genericReport_orderType = request.getParameter("genericReport_orderType");
		
		if(genericReport_fieldOrder == null || genericReport_fieldOrder.length() == 0) genericReport_fieldOrder = null;
		if(genericReport_orderType == null || genericReport_orderType.length() == 0) genericReport_orderType = null;
		request.getSession().setAttribute("genericReport_fieldOrder", genericReport_fieldOrder==null?"":genericReport_fieldOrder);	 
		request.getSession().setAttribute("genericReport_orderType", genericReport_orderType==null?"":genericReport_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='GenericReport'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer genericReport_filterSb = new StringBuffer();
		String param_genericReport_name_filter = "";
		if(request.getParameter("genericReport_name_filter")!=null){
			param_genericReport_name_filter = request.getParameter("genericReport_name_filter");
			if(param_genericReport_name_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.name like '%"+param_genericReport_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("genericReport_name_filter", param_genericReport_name_filter);
		String param_genericReport_reportFields_filter = "";
		if(request.getParameter("genericReport_reportFields_filter")!=null){
			param_genericReport_reportFields_filter = request.getParameter("genericReport_reportFields_filter");
			if(param_genericReport_reportFields_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.reportFields like '%"+param_genericReport_reportFields_filter+"%' ");
			}
		}
		request.getSession().setAttribute("genericReport_reportFields_filter", param_genericReport_reportFields_filter);
		String param_genericReport_searchFields_filter = "";
		if(request.getParameter("genericReport_searchFields_filter")!=null){
			param_genericReport_searchFields_filter = request.getParameter("genericReport_searchFields_filter");
			if(param_genericReport_searchFields_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.searchFields like '%"+param_genericReport_searchFields_filter+"%' ");
			}
		}
		request.getSession().setAttribute("genericReport_searchFields_filter", param_genericReport_searchFields_filter);
		String param_genericReport_reportSql_filter = "";
		if(request.getParameter("genericReport_reportSql_filter")!=null){
			param_genericReport_reportSql_filter = request.getParameter("genericReport_reportSql_filter");
			if(param_genericReport_reportSql_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.reportSql like '%"+param_genericReport_reportSql_filter+"%' ");
			}
		}
		request.getSession().setAttribute("genericReport_reportSql_filter", param_genericReport_reportSql_filter);
		String param_genericReport_description_filter = "";
		if(request.getParameter("genericReport_description_filter")!=null){
			param_genericReport_description_filter = request.getParameter("genericReport_description_filter");
			if(param_genericReport_description_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.description like '%"+param_genericReport_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("genericReport_description_filter", param_genericReport_description_filter);
		String param_genericReport_columnAction_filter = "";
		if(request.getParameter("genericReport_columnAction_filter")!=null){
			param_genericReport_columnAction_filter = request.getParameter("genericReport_columnAction_filter");
			if(param_genericReport_columnAction_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.columnAction like '%"+param_genericReport_columnAction_filter+"%' ");
			}
		}
		request.getSession().setAttribute("genericReport_columnAction_filter", param_genericReport_columnAction_filter);
		String param_genericReport_status_filter = "";
		if(request.getParameter("genericReport_status_filter")!=null){
			param_genericReport_status_filter = request.getParameter("genericReport_status_filter");
			if(param_genericReport_status_filter.length() > 0 ){				
				genericReport_filterSb.append("  AND genericReport.status = '"+param_genericReport_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("genericReport_status_filter", param_genericReport_status_filter);
		
		if(genericReport_fieldOrder!=null && genericReport_orderType != null )genericReport_filterSb.append(" ORDER BY "+genericReport_fieldOrder+" "+genericReport_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		GenericReportService genericReportService = GenericReportService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList genericReportList = genericReportService.getPartialList(genericReport_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, genericReportList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, genericReportList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("genericReportList", genericReportList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("genericReport");
		
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
    		GenericReportService genericReportService = GenericReportService.getInstance();
    		List genericReportList = genericReportService.getList(parentFilter , null);
    		request.setAttribute("genericReportList", genericReportList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do");
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
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do");
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
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null) genericReport = new GenericReport();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='GenericReport'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("genericReport", genericReport);
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
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, genericReport, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='GenericReport'  ", null);
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
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","new"));
    		GenericReportService.getInstance().add(genericReport);
    		request.getSession().removeAttribute("genericReport");
    		response.sendRedirect("genericReport.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='GenericReport'  ", null);
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
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, genericReport, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='GenericReport'  ", null);
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
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=edit_confirm");
    		}
    		
    		GenericReportService.getInstance().update(genericReport);
    		request.getSession().removeAttribute("genericReport");
    		response.sendRedirect("genericReport.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=delete_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","deleted"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("genericReport.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=submit_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","submitted"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=approve_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","approved"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=reject_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","rejected"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=pending_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","pending"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=process_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","processed"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("activate_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=activate_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","activated"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=close_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","closed"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=archive_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","archived"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=remove_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","removed"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("block_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=block_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","blocked"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if (genericReport == null){
	    		genericReport = GenericReportService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("genericReport", genericReport);
	    	}
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("genericReport.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		GenericReport genericReport = (GenericReport) request.getSession().getAttribute("genericReport");
    		if(genericReport == null){
    			response.sendRedirect("genericReport.do?action=cancel_confirm");
    		}
    		genericReport.setStatus(StatusService.getInstance().getByTypeandCode("GenericReport","cancelled"));
    		GenericReportService.getInstance().update(genericReport);
    		response.sendRedirect("genericReport.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("genericReport.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, GenericReport genericReport, ActionErrors errors){
    	try{    		
			String name = request.getParameter("name");
			genericReport.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("genericReport.name", new ActionError("error.genericReport.name"));
			}
			String reportFields = request.getParameter("reportFields");
			genericReport.setReportFields(reportFields);
			String searchFields = request.getParameter("searchFields");
			genericReport.setSearchFields(searchFields);
			String reportSql = request.getParameter("reportSql");
			genericReport.setReportSql(reportSql);
			String description = request.getParameter("description");
			genericReport.setDescription(description);
			String columnAction = request.getParameter("columnAction");
			genericReport.setColumnAction(columnAction);

/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					genericReport.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					genericReport.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("genericReport.status", new ActionError("error.genericReport.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

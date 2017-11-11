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


public class SharedDocumentActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.SharedDocumentActionBase");	
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
    	
    	request.getSession().removeAttribute("sharedDocument");
		
		String sharedDocument_filter = request.getParameter("sharedDocument_filter");
		//this for orderBy field ASC/DESC
		String sharedDocument_fieldOrder = request.getParameter("sharedDocument_fieldOrder");
		String sharedDocument_orderType = request.getParameter("sharedDocument_orderType");
		
		if(sharedDocument_fieldOrder == null || sharedDocument_fieldOrder.length() == 0) sharedDocument_fieldOrder = null;
		if(sharedDocument_orderType == null || sharedDocument_orderType.length() == 0) sharedDocument_orderType = null;
		request.getSession().setAttribute("sharedDocument_fieldOrder", sharedDocument_fieldOrder==null?"":sharedDocument_fieldOrder);	 
		request.getSession().setAttribute("sharedDocument_orderType", sharedDocument_orderType==null?"":sharedDocument_orderType);
		
		try{ 
			com.app.docmgr.service.DocumentService documentService = com.app.docmgr.service.DocumentService.getInstance();
			List documentList = documentService.getList(null, null);
			request.setAttribute("documentList", documentList);
			com.app.docmgr.service.UserService targetUserService = com.app.docmgr.service.UserService.getInstance();
			List targetUserList = targetUserService.getList(null, null);
			request.setAttribute("targetUserList", targetUserList);
			com.app.docmgr.service.OrganizationService targetOrganizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List targetOrganizationList = targetOrganizationService.getList(null, null);
			request.setAttribute("targetOrganizationList", targetOrganizationList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SharedDocument'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer sharedDocument_filterSb = new StringBuffer();
		String param_sharedDocument_grantAction_filter = "";
		if(request.getParameter("sharedDocument_grantAction_filter")!=null){
			param_sharedDocument_grantAction_filter = request.getParameter("sharedDocument_grantAction_filter");
			if(param_sharedDocument_grantAction_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.grantAction like '%"+param_sharedDocument_grantAction_filter+"%' ");
			}
		}
		request.getSession().setAttribute("sharedDocument_grantAction_filter", param_sharedDocument_grantAction_filter);
		String param_sharedDocument_createdDate_filter_start = "";
		if(request.getParameter("sharedDocument_createdDate_filter_start")!=null){
			param_sharedDocument_createdDate_filter_start = request.getParameter("sharedDocument_createdDate_filter_start");
			if(param_sharedDocument_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_sharedDocument_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_sharedDocument_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_sharedDocument_createdDate_filter_start));
					String param_sharedDocument_createdDate_filter_start_cal_val = param_sharedDocument_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_sharedDocument_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_sharedDocument_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					sharedDocument_filterSb.append("  AND sharedDocument.createdDate >= '"+param_sharedDocument_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("sharedDocument_createdDate_filter_start", param_sharedDocument_createdDate_filter_start);

		String param_sharedDocument_createdDate_filter_end = "";
		if(request.getParameter("sharedDocument_createdDate_filter_end")!=null){
			param_sharedDocument_createdDate_filter_end = request.getParameter("sharedDocument_createdDate_filter_end");
			if(param_sharedDocument_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_sharedDocument_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_sharedDocument_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_sharedDocument_createdDate_filter_end));
					String param_sharedDocument_createdDate_filter_end_cal_val = param_sharedDocument_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_sharedDocument_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_sharedDocument_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					sharedDocument_filterSb.append("  AND sharedDocument.createdDate  <= '"+param_sharedDocument_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("sharedDocument_createdDate_filter_end", param_sharedDocument_createdDate_filter_end);

		String param_sharedDocument_createdBy_filter = "";
		if(request.getParameter("sharedDocument_createdBy_filter")!=null){
			param_sharedDocument_createdBy_filter = request.getParameter("sharedDocument_createdBy_filter");
			if(param_sharedDocument_createdBy_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.createdBy like '%"+param_sharedDocument_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("sharedDocument_createdBy_filter", param_sharedDocument_createdBy_filter);
		String param_sharedDocument_lastUpdatedDate_filter_start = "";
		if(request.getParameter("sharedDocument_lastUpdatedDate_filter_start")!=null){
			param_sharedDocument_lastUpdatedDate_filter_start = request.getParameter("sharedDocument_lastUpdatedDate_filter_start");
			if(param_sharedDocument_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_sharedDocument_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_sharedDocument_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_sharedDocument_lastUpdatedDate_filter_start));
					String param_sharedDocument_lastUpdatedDate_filter_start_cal_val = param_sharedDocument_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_sharedDocument_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_sharedDocument_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					sharedDocument_filterSb.append("  AND sharedDocument.lastUpdatedDate >= '"+param_sharedDocument_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("sharedDocument_lastUpdatedDate_filter_start", param_sharedDocument_lastUpdatedDate_filter_start);

		String param_sharedDocument_lastUpdatedDate_filter_end = "";
		if(request.getParameter("sharedDocument_lastUpdatedDate_filter_end")!=null){
			param_sharedDocument_lastUpdatedDate_filter_end = request.getParameter("sharedDocument_lastUpdatedDate_filter_end");
			if(param_sharedDocument_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_sharedDocument_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_sharedDocument_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_sharedDocument_lastUpdatedDate_filter_end));
					String param_sharedDocument_lastUpdatedDate_filter_end_cal_val = param_sharedDocument_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_sharedDocument_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_sharedDocument_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					sharedDocument_filterSb.append("  AND sharedDocument.lastUpdatedDate  <= '"+param_sharedDocument_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("sharedDocument_lastUpdatedDate_filter_end", param_sharedDocument_lastUpdatedDate_filter_end);

		String param_sharedDocument_lastUpdatedBy_filter = "";
		if(request.getParameter("sharedDocument_lastUpdatedBy_filter")!=null){
			param_sharedDocument_lastUpdatedBy_filter = request.getParameter("sharedDocument_lastUpdatedBy_filter");
			if(param_sharedDocument_lastUpdatedBy_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.lastUpdatedBy like '%"+param_sharedDocument_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("sharedDocument_lastUpdatedBy_filter", param_sharedDocument_lastUpdatedBy_filter);
		String param_sharedDocument_document_filter = "";
		if(request.getParameter("sharedDocument_document_filter")!=null){
			param_sharedDocument_document_filter = request.getParameter("sharedDocument_document_filter");
			if(param_sharedDocument_document_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.document = '"+param_sharedDocument_document_filter+"' ");
			}
		}		
		request.getSession().setAttribute("sharedDocument_document_filter", param_sharedDocument_document_filter);
		String param_sharedDocument_targetUser_filter = "";
		if(request.getParameter("sharedDocument_targetUser_filter")!=null){
			param_sharedDocument_targetUser_filter = request.getParameter("sharedDocument_targetUser_filter");
			if(param_sharedDocument_targetUser_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.targetUser = '"+param_sharedDocument_targetUser_filter+"' ");
			}
		}		
		request.getSession().setAttribute("sharedDocument_targetUser_filter", param_sharedDocument_targetUser_filter);
		String param_sharedDocument_targetOrganization_filter = "";
		if(request.getParameter("sharedDocument_targetOrganization_filter")!=null){
			param_sharedDocument_targetOrganization_filter = request.getParameter("sharedDocument_targetOrganization_filter");
			if(param_sharedDocument_targetOrganization_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.targetOrganization = '"+param_sharedDocument_targetOrganization_filter+"' ");
			}
		}		
		request.getSession().setAttribute("sharedDocument_targetOrganization_filter", param_sharedDocument_targetOrganization_filter);
		String param_sharedDocument_status_filter = "";
		if(request.getParameter("sharedDocument_status_filter")!=null){
			param_sharedDocument_status_filter = request.getParameter("sharedDocument_status_filter");
			if(param_sharedDocument_status_filter.length() > 0 ){				
				sharedDocument_filterSb.append("  AND sharedDocument.status = '"+param_sharedDocument_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("sharedDocument_status_filter", param_sharedDocument_status_filter);
		
		if(sharedDocument_fieldOrder!=null && sharedDocument_orderType != null )sharedDocument_filterSb.append(" ORDER BY "+sharedDocument_fieldOrder+" "+sharedDocument_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		SharedDocumentService sharedDocumentService = SharedDocumentService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList sharedDocumentList = sharedDocumentService.getPartialList(sharedDocument_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, sharedDocumentList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, sharedDocumentList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("sharedDocumentList", sharedDocumentList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("sharedDocument");
		
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
    		SharedDocumentService sharedDocumentService = SharedDocumentService.getInstance();
    		List sharedDocumentList = sharedDocumentService.getList(parentFilter , null);
    		request.setAttribute("sharedDocumentList", sharedDocumentList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do");
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
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do");
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
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null) sharedDocument = new SharedDocument();
    		
			com.app.docmgr.service.DocumentService documentService = com.app.docmgr.service.DocumentService.getInstance();
			List documentList = documentService.getList(null, null);
			request.setAttribute("documentList", documentList);
			com.app.docmgr.service.UserService targetUserService = com.app.docmgr.service.UserService.getInstance();
			List targetUserList = targetUserService.getList(null, null);
			request.setAttribute("targetUserList", targetUserList);
			com.app.docmgr.service.OrganizationService targetOrganizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List targetOrganizationList = targetOrganizationService.getList(null, null);
			request.setAttribute("targetOrganizationList", targetOrganizationList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SharedDocument'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("sharedDocument", sharedDocument);
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
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, sharedDocument, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.DocumentService documentService = com.app.docmgr.service.DocumentService.getInstance();
			List documentList = documentService.getList(null, null);
			request.setAttribute("documentList", documentList);
			com.app.docmgr.service.UserService targetUserService = com.app.docmgr.service.UserService.getInstance();
			List targetUserList = targetUserService.getList(null, null);
			request.setAttribute("targetUserList", targetUserList);
			com.app.docmgr.service.OrganizationService targetOrganizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List targetOrganizationList = targetOrganizationService.getList(null, null);
			request.setAttribute("targetOrganizationList", targetOrganizationList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SharedDocument'  ", null);
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
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","new"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setCreatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
			sharedDocument.setCreatedBy(_doneBy);
    		SharedDocumentService.getInstance().add(sharedDocument);
    		request.getSession().removeAttribute("sharedDocument");
    		response.sendRedirect("sharedDocument.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do");
    			return null;
    		}
    		
			com.app.docmgr.service.DocumentService documentService = com.app.docmgr.service.DocumentService.getInstance();
			List documentList = documentService.getList(null, null);
			request.setAttribute("documentList", documentList);
			com.app.docmgr.service.UserService targetUserService = com.app.docmgr.service.UserService.getInstance();
			List targetUserList = targetUserService.getList(null, null);
			request.setAttribute("targetUserList", targetUserList);
			com.app.docmgr.service.OrganizationService targetOrganizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List targetOrganizationList = targetOrganizationService.getList(null, null);
			request.setAttribute("targetOrganizationList", targetOrganizationList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SharedDocument'  ", null);
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
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, sharedDocument, errors);
    		
			com.app.docmgr.service.DocumentService documentService = com.app.docmgr.service.DocumentService.getInstance();
			List documentList = documentService.getList(null, null);
			request.setAttribute("documentList", documentList);
			com.app.docmgr.service.UserService targetUserService = com.app.docmgr.service.UserService.getInstance();
			List targetUserList = targetUserService.getList(null, null);
			request.setAttribute("targetUserList", targetUserList);
			com.app.docmgr.service.OrganizationService targetOrganizationService = com.app.docmgr.service.OrganizationService.getInstance();
			List targetOrganizationList = targetOrganizationService.getList(null, null);
			request.setAttribute("targetOrganizationList", targetOrganizationList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='SharedDocument'  ", null);
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
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=edit_confirm");
    		}
    		
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		request.getSession().removeAttribute("sharedDocument");
    		response.sendRedirect("sharedDocument.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=delete_confirm");
    		}
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","deleted"));
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("sharedDocument.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=submit_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","submitted"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=approve_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","approved"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=reject_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","rejected"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=pending_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","pending"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=process_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","processed"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("activate_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=activate_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","activated"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=close_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","closed"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=archive_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","archived"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=remove_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","removed"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("block_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=block_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","blocked"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if (sharedDocument == null){
	    		sharedDocument = SharedDocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("sharedDocument", sharedDocument);
	    	}
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("sharedDocument.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		SharedDocument sharedDocument = (SharedDocument) request.getSession().getAttribute("sharedDocument");
    		if(sharedDocument == null){
    			response.sendRedirect("sharedDocument.do?action=cancel_confirm");
    		}
    		sharedDocument.setStatus(StatusService.getInstance().getByTypeandCode("SharedDocument","cancelled"));
			sharedDocument.setLastUpdatedDate(new Date());
			sharedDocument.setLastUpdatedBy(_doneBy);
    		SharedDocumentService.getInstance().update(sharedDocument);
    		response.sendRedirect("sharedDocument.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("sharedDocument.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, SharedDocument sharedDocument, ActionErrors errors){
    	try{    		
			String grantAction = request.getParameter("grantAction");
			sharedDocument.setGrantAction(grantAction);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				sharedDocument.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					sharedDocument.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("sharedDocument.createdDate", new ActionError("error.sharedDocument.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			sharedDocument.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("sharedDocument.createdBy", new ActionError("error.sharedDocument.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				sharedDocument.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					sharedDocument.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			sharedDocument.setLastUpdatedBy(lastUpdatedBy);
*/ 
			com.app.docmgr.model.Document  documentObj =null;
			com.app.docmgr.service.DocumentService documentService = com.app.docmgr.service.DocumentService.getInstance();
			try{
				String documentStr = request.getParameter("document");
				
				if(documentStr == null || documentStr.trim().length() == 0 ){
					sharedDocument.setDocument(null);
				}else{			
					documentObj = documentService.get(new Long(documentStr));
					sharedDocument.setDocument(documentObj);
				}
			}catch(Exception ex){}	
			if(documentObj==null){
				errors.add("sharedDocument.document", new ActionError("error.sharedDocument.document"));
			}
			com.app.docmgr.model.User  targetUserObj =null;
			com.app.docmgr.service.UserService targetUserService = com.app.docmgr.service.UserService.getInstance();
			try{
				String targetUserStr = request.getParameter("targetUser");
				
				if(targetUserStr == null || targetUserStr.trim().length() == 0 ){
					sharedDocument.setTargetUser(null);
				}else{			
					targetUserObj = targetUserService.get(new Long(targetUserStr));
					sharedDocument.setTargetUser(targetUserObj);
				}
			}catch(Exception ex){}	
			com.app.docmgr.model.Organization  targetOrganizationObj =null;
			com.app.docmgr.service.OrganizationService targetOrganizationService = com.app.docmgr.service.OrganizationService.getInstance();
			try{
				String targetOrganizationStr = request.getParameter("targetOrganization");
				
				if(targetOrganizationStr == null || targetOrganizationStr.trim().length() == 0 ){
					sharedDocument.setTargetOrganization(null);
				}else{			
					targetOrganizationObj = targetOrganizationService.get(new Long(targetOrganizationStr));
					sharedDocument.setTargetOrganization(targetOrganizationObj);
				}
			}catch(Exception ex){}	
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					sharedDocument.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					sharedDocument.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("sharedDocument.status", new ActionError("error.sharedDocument.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

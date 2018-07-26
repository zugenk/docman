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
 * @createDate 07-04-2018 18:40:05
 */


public class DocumentHistoryActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.DocumentHistoryActionBase");	
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
    	
    	request.getSession().removeAttribute("documentHistory");
		
		String documentHistory_filter = request.getParameter("documentHistory_filter");
		//this for orderBy field ASC/DESC
		String documentHistory_fieldOrder = request.getParameter("documentHistory_fieldOrder");
		String documentHistory_orderType = request.getParameter("documentHistory_orderType");
		
		if(documentHistory_fieldOrder == null || documentHistory_fieldOrder.length() == 0) documentHistory_fieldOrder = null;
		if(documentHistory_orderType == null || documentHistory_orderType.length() == 0) documentHistory_orderType = null;
		request.getSession().setAttribute("documentHistory_fieldOrder", documentHistory_fieldOrder==null?"":documentHistory_fieldOrder);	 
		request.getSession().setAttribute("documentHistory_orderType", documentHistory_orderType==null?"":documentHistory_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='DocumentHistory'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.FolderService folderService = com.app.docmgr.service.FolderService.getInstance();
			List folderList = folderService.getList(null, null);
			request.setAttribute("folderList", folderList);
			com.app.docmgr.service.DocumentService parentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
		}catch(Exception ex){
		
		}
		StringBuffer documentHistory_filterSb = new StringBuffer();
		String param_documentHistory_historyDate_filter_start = "";
		if(request.getParameter("documentHistory_historyDate_filter_start")!=null){
			param_documentHistory_historyDate_filter_start = request.getParameter("documentHistory_historyDate_filter_start");
			if(param_documentHistory_historyDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_documentHistory_historyDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_documentHistory_historyDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_documentHistory_historyDate_filter_start));
					String param_documentHistory_historyDate_filter_start_cal_val = param_documentHistory_historyDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_documentHistory_historyDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_documentHistory_historyDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					documentHistory_filterSb.append("  AND documentHistory.historyDate >= '"+param_documentHistory_historyDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("documentHistory_historyDate_filter_start", param_documentHistory_historyDate_filter_start);

		String param_documentHistory_historyDate_filter_end = "";
		if(request.getParameter("documentHistory_historyDate_filter_end")!=null){
			param_documentHistory_historyDate_filter_end = request.getParameter("documentHistory_historyDate_filter_end");
			if(param_documentHistory_historyDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_documentHistory_historyDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_documentHistory_historyDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_documentHistory_historyDate_filter_end));
					String param_documentHistory_historyDate_filter_end_cal_val = param_documentHistory_historyDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_documentHistory_historyDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_documentHistory_historyDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					documentHistory_filterSb.append("  AND documentHistory.historyDate  <= '"+param_documentHistory_historyDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("documentHistory_historyDate_filter_end", param_documentHistory_historyDate_filter_end);

		String param_documentHistory_historyBy_filter = "";
		if(request.getParameter("documentHistory_historyBy_filter")!=null){
			param_documentHistory_historyBy_filter = request.getParameter("documentHistory_historyBy_filter");
			if(param_documentHistory_historyBy_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.historyBy like '%"+param_documentHistory_historyBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_historyBy_filter", param_documentHistory_historyBy_filter);
		String param_documentHistory_name_filter = "";
		if(request.getParameter("documentHistory_name_filter")!=null){
			param_documentHistory_name_filter = request.getParameter("documentHistory_name_filter");
			if(param_documentHistory_name_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.name like '%"+param_documentHistory_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_name_filter", param_documentHistory_name_filter);
		String param_documentHistory_documentType_filter = "";
		if(request.getParameter("documentHistory_documentType_filter")!=null){
			param_documentHistory_documentType_filter = request.getParameter("documentHistory_documentType_filter");
			if(param_documentHistory_documentType_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.documentType like '%"+param_documentHistory_documentType_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_documentType_filter", param_documentHistory_documentType_filter);
		String param_documentHistory_contentType_filter = "";
		if(request.getParameter("documentHistory_contentType_filter")!=null){
			param_documentHistory_contentType_filter = request.getParameter("documentHistory_contentType_filter");
			if(param_documentHistory_contentType_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.contentType like '%"+param_documentHistory_contentType_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_contentType_filter", param_documentHistory_contentType_filter);
		String param_documentHistory_documentNumber_filter = "";
		if(request.getParameter("documentHistory_documentNumber_filter")!=null){
			param_documentHistory_documentNumber_filter = request.getParameter("documentHistory_documentNumber_filter");
			if(param_documentHistory_documentNumber_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.documentNumber like '%"+param_documentHistory_documentNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_documentNumber_filter", param_documentHistory_documentNumber_filter);
		String param_documentHistory_repositoryId_filter = "";
		if(request.getParameter("documentHistory_repositoryId_filter")!=null){
			param_documentHistory_repositoryId_filter = request.getParameter("documentHistory_repositoryId_filter");
			if(param_documentHistory_repositoryId_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.repositoryId like '%"+param_documentHistory_repositoryId_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_repositoryId_filter", param_documentHistory_repositoryId_filter);
		String param_documentHistory_documentVersion_filter = "";
		if(request.getParameter("documentHistory_documentVersion_filter")!=null){
			param_documentHistory_documentVersion_filter = request.getParameter("documentHistory_documentVersion_filter");
			if(param_documentHistory_documentVersion_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.documentVersion like '%"+param_documentHistory_documentVersion_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_documentVersion_filter", param_documentHistory_documentVersion_filter);
		String param_documentHistory_description_filter = "";
		if(request.getParameter("documentHistory_description_filter")!=null){
			param_documentHistory_description_filter = request.getParameter("documentHistory_description_filter");
			if(param_documentHistory_description_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.description like '%"+param_documentHistory_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_description_filter", param_documentHistory_description_filter);
		String param_documentHistory_tag_filter = "";
		if(request.getParameter("documentHistory_tag_filter")!=null){
			param_documentHistory_tag_filter = request.getParameter("documentHistory_tag_filter");
			if(param_documentHistory_tag_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.tag like '%"+param_documentHistory_tag_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_tag_filter", param_documentHistory_tag_filter);
		String param_documentHistory_createdDate_filter_start = "";
		if(request.getParameter("documentHistory_createdDate_filter_start")!=null){
			param_documentHistory_createdDate_filter_start = request.getParameter("documentHistory_createdDate_filter_start");
			if(param_documentHistory_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_documentHistory_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_documentHistory_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_documentHistory_createdDate_filter_start));
					String param_documentHistory_createdDate_filter_start_cal_val = param_documentHistory_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_documentHistory_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_documentHistory_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					documentHistory_filterSb.append("  AND documentHistory.createdDate >= '"+param_documentHistory_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("documentHistory_createdDate_filter_start", param_documentHistory_createdDate_filter_start);

		String param_documentHistory_createdDate_filter_end = "";
		if(request.getParameter("documentHistory_createdDate_filter_end")!=null){
			param_documentHistory_createdDate_filter_end = request.getParameter("documentHistory_createdDate_filter_end");
			if(param_documentHistory_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_documentHistory_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_documentHistory_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_documentHistory_createdDate_filter_end));
					String param_documentHistory_createdDate_filter_end_cal_val = param_documentHistory_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_documentHistory_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_documentHistory_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					documentHistory_filterSb.append("  AND documentHistory.createdDate  <= '"+param_documentHistory_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("documentHistory_createdDate_filter_end", param_documentHistory_createdDate_filter_end);

		String param_documentHistory_createdBy_filter = "";
		if(request.getParameter("documentHistory_createdBy_filter")!=null){
			param_documentHistory_createdBy_filter = request.getParameter("documentHistory_createdBy_filter");
			if(param_documentHistory_createdBy_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.createdBy like '%"+param_documentHistory_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_createdBy_filter", param_documentHistory_createdBy_filter);
		String param_documentHistory_lastUpdatedDate_filter_start = "";
		if(request.getParameter("documentHistory_lastUpdatedDate_filter_start")!=null){
			param_documentHistory_lastUpdatedDate_filter_start = request.getParameter("documentHistory_lastUpdatedDate_filter_start");
			if(param_documentHistory_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_documentHistory_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_documentHistory_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_documentHistory_lastUpdatedDate_filter_start));
					String param_documentHistory_lastUpdatedDate_filter_start_cal_val = param_documentHistory_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_documentHistory_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_documentHistory_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					documentHistory_filterSb.append("  AND documentHistory.lastUpdatedDate >= '"+param_documentHistory_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("documentHistory_lastUpdatedDate_filter_start", param_documentHistory_lastUpdatedDate_filter_start);

		String param_documentHistory_lastUpdatedDate_filter_end = "";
		if(request.getParameter("documentHistory_lastUpdatedDate_filter_end")!=null){
			param_documentHistory_lastUpdatedDate_filter_end = request.getParameter("documentHistory_lastUpdatedDate_filter_end");
			if(param_documentHistory_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_documentHistory_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_documentHistory_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_documentHistory_lastUpdatedDate_filter_end));
					String param_documentHistory_lastUpdatedDate_filter_end_cal_val = param_documentHistory_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_documentHistory_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_documentHistory_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					documentHistory_filterSb.append("  AND documentHistory.lastUpdatedDate  <= '"+param_documentHistory_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("documentHistory_lastUpdatedDate_filter_end", param_documentHistory_lastUpdatedDate_filter_end);

		String param_documentHistory_lastUpdatedBy_filter = "";
		if(request.getParameter("documentHistory_lastUpdatedBy_filter")!=null){
			param_documentHistory_lastUpdatedBy_filter = request.getParameter("documentHistory_lastUpdatedBy_filter");
			if(param_documentHistory_lastUpdatedBy_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.lastUpdatedBy like '%"+param_documentHistory_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("documentHistory_lastUpdatedBy_filter", param_documentHistory_lastUpdatedBy_filter);
		String param_documentHistory_securityLevel_filter = "";
		if(request.getParameter("documentHistory_securityLevel_filter")!=null){
			param_documentHistory_securityLevel_filter = request.getParameter("documentHistory_securityLevel_filter");
			if(param_documentHistory_securityLevel_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.securityLevel = '"+param_documentHistory_securityLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("documentHistory_securityLevel_filter", param_documentHistory_securityLevel_filter);
		String param_documentHistory_owner_filter = "";
		if(request.getParameter("documentHistory_owner_filter")!=null){
			param_documentHistory_owner_filter = request.getParameter("documentHistory_owner_filter");
			if(param_documentHistory_owner_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.owner = '"+param_documentHistory_owner_filter+"' ");
			}
		}		
		request.getSession().setAttribute("documentHistory_owner_filter", param_documentHistory_owner_filter);
		String param_documentHistory_status_filter = "";
		if(request.getParameter("documentHistory_status_filter")!=null){
			param_documentHistory_status_filter = request.getParameter("documentHistory_status_filter");
			if(param_documentHistory_status_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.status = '"+param_documentHistory_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("documentHistory_status_filter", param_documentHistory_status_filter);
		String param_documentHistory_folder_filter = "";
		if(request.getParameter("documentHistory_folder_filter")!=null){
			param_documentHistory_folder_filter = request.getParameter("documentHistory_folder_filter");
			if(param_documentHistory_folder_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.folder = '"+param_documentHistory_folder_filter+"' ");
			}
		}		
		request.getSession().setAttribute("documentHistory_folder_filter", param_documentHistory_folder_filter);
		String param_documentHistory_parent_filter = "";
		if(request.getParameter("documentHistory_parent_filter")!=null){
			param_documentHistory_parent_filter = request.getParameter("documentHistory_parent_filter");
			if(param_documentHistory_parent_filter.length() > 0 ){				
				documentHistory_filterSb.append("  AND documentHistory.parent = '"+param_documentHistory_parent_filter+"' ");
			}
		}		
		request.getSession().setAttribute("documentHistory_parent_filter", param_documentHistory_parent_filter);
		
		if(documentHistory_fieldOrder!=null && documentHistory_orderType != null )documentHistory_filterSb.append(" ORDER BY "+documentHistory_fieldOrder+" "+documentHistory_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		DocumentHistoryService documentHistoryService = DocumentHistoryService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList documentHistoryList = documentHistoryService.getPartialList(documentHistory_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, documentHistoryList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, documentHistoryList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("documentHistoryList", documentHistoryList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("documentHistory");
		
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
    		DocumentHistoryService documentHistoryService = DocumentHistoryService.getInstance();
    		List documentHistoryList = documentHistoryService.getList(parentFilter , null);
    		request.setAttribute("documentHistoryList", documentHistoryList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do");
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
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do");
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
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null) documentHistory = new DocumentHistory();
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='DocumentHistory'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.FolderService folderService = com.app.docmgr.service.FolderService.getInstance();
			List folderList = folderService.getList(null, null);
			request.setAttribute("folderList", folderList);
			com.app.docmgr.service.DocumentService parentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

    		request.getSession().setAttribute("documentHistory", documentHistory);
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
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, documentHistory, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='DocumentHistory'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.FolderService folderService = com.app.docmgr.service.FolderService.getInstance();
			List folderList = folderService.getList(null, null);
			request.setAttribute("folderList", folderList);
			com.app.docmgr.service.DocumentService parentService = com.app.docmgr.service.DocumentService.getInstance();
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
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","new"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setCreatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
			documentHistory.setCreatedBy(_doneBy);
    		DocumentHistoryService.getInstance().add(documentHistory);
    		request.getSession().removeAttribute("documentHistory");
    		response.sendRedirect("documentHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='DocumentHistory'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.FolderService folderService = com.app.docmgr.service.FolderService.getInstance();
			List folderList = folderService.getList(null, null);
			request.setAttribute("folderList", folderList);
			com.app.docmgr.service.DocumentService parentService = com.app.docmgr.service.DocumentService.getInstance();
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
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, documentHistory, errors);
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='DocumentHistory'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.FolderService folderService = com.app.docmgr.service.FolderService.getInstance();
			List folderList = folderService.getList(null, null);
			request.setAttribute("folderList", folderList);
			com.app.docmgr.service.DocumentService parentService = com.app.docmgr.service.DocumentService.getInstance();
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
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=edit_confirm");
    		}
    		
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		request.getSession().removeAttribute("documentHistory");
    		response.sendRedirect("documentHistory.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=delete_confirm");
    		}
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","deleted"));
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("documentHistory.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=submit_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","submitted"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=approve_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","approved"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=reject_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","rejected"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=pending_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","pending"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=process_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","processed"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("activate_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=activate_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","activated"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=close_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","closed"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=archive_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","archived"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=remove_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","removed"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("block_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=block_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","blocked"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if (documentHistory == null){
	    		documentHistory = DocumentHistoryService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("documentHistory", documentHistory);
	    	}
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("documentHistory.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		DocumentHistory documentHistory = (DocumentHistory) request.getSession().getAttribute("documentHistory");
    		if(documentHistory == null){
    			response.sendRedirect("documentHistory.do?action=cancel_confirm");
    		}
    		documentHistory.setStatus(StatusService.getInstance().getByTypeandCode("DocumentHistory","cancelled"));
			documentHistory.setLastUpdatedDate(new Date());
			documentHistory.setLastUpdatedBy(_doneBy);
    		DocumentHistoryService.getInstance().update(documentHistory);
    		response.sendRedirect("documentHistory.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("documentHistory.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, DocumentHistory documentHistory, ActionErrors errors){
    	try{    		
			String historyDate = request.getParameter("historyDate");
			if(historyDate==null || historyDate.trim().length() == 0 ){
				documentHistory.setHistoryDate(null);
			}else{
				try{
					java.util.Calendar historyDateCalendar = java.util.Calendar.getInstance();
					historyDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(historyDate));			
					documentHistory.setHistoryDate(historyDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(historyDate==null || historyDate.trim().length() == 0 ){
				errors.add("documentHistory.historyDate", new ActionError("error.documentHistory.historyDate"));
			}
			String historyBy = request.getParameter("historyBy");
			documentHistory.setHistoryBy(historyBy);
			if(historyBy==null || historyBy.trim().length() == 0 ){
				errors.add("documentHistory.historyBy", new ActionError("error.documentHistory.historyBy"));
			}
			String auditTrailId = request.getParameter("auditTrailId");
			try{
				documentHistory.setAuditTrailId(new java.lang.Long(auditTrailId));
			}catch(Exception ex){}
			if(auditTrailId==null || auditTrailId.trim().length() == 0 ){
				errors.add("documentHistory.auditTrailId", new ActionError("error.documentHistory.auditTrailId"));
			}
			String documentId = request.getParameter("documentId");
			try{
				documentHistory.setDocumentId(new java.lang.Long(documentId));
			}catch(Exception ex){}
			if(documentId==null || documentId.trim().length() == 0 ){
				errors.add("documentHistory.documentId", new ActionError("error.documentHistory.documentId"));
			}
			String name = request.getParameter("name");
			documentHistory.setName(name);
			String documentType = request.getParameter("documentType");
			documentHistory.setDocumentType(documentType);
			if(documentType==null || documentType.trim().length() == 0 ){
				errors.add("documentHistory.documentType", new ActionError("error.documentHistory.documentType"));
			}
			String contentType = request.getParameter("contentType");
			documentHistory.setContentType(contentType);
			if(contentType==null || contentType.trim().length() == 0 ){
				errors.add("documentHistory.contentType", new ActionError("error.documentHistory.contentType"));
			}
			String documentNumber = request.getParameter("documentNumber");
			documentHistory.setDocumentNumber(documentNumber);
			String repositoryId = request.getParameter("repositoryId");
			documentHistory.setRepositoryId(repositoryId);
			String documentVersion = request.getParameter("documentVersion");
			documentHistory.setDocumentVersion(documentVersion);
			String description = request.getParameter("description");
			documentHistory.setDescription(description);
			String tag = request.getParameter("tag");
			documentHistory.setTag(tag);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				documentHistory.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					documentHistory.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("documentHistory.createdDate", new ActionError("error.documentHistory.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			documentHistory.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("documentHistory.createdBy", new ActionError("error.documentHistory.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				documentHistory.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					documentHistory.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			documentHistory.setLastUpdatedBy(lastUpdatedBy);
*/ 
			com.app.docmgr.model.Lookup  securityLevelObj =null;
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String securityLevelStr = request.getParameter("securityLevel");
				
				if(securityLevelStr == null || securityLevelStr.trim().length() == 0 ){
					documentHistory.setSecurityLevel(null);
				}else{			
					securityLevelObj = securityLevelService.get(new Long(securityLevelStr));
					documentHistory.setSecurityLevel(securityLevelObj);
				}
			}catch(Exception ex){}	
			com.app.docmgr.model.User  ownerObj =null;
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			try{
				String ownerStr = request.getParameter("owner");
				
				if(ownerStr == null || ownerStr.trim().length() == 0 ){
					documentHistory.setOwner(null);
				}else{			
					ownerObj = ownerService.get(new Long(ownerStr));
					documentHistory.setOwner(ownerObj);
				}
			}catch(Exception ex){}	
			if(ownerObj==null){
				errors.add("documentHistory.owner", new ActionError("error.documentHistory.owner"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					documentHistory.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					documentHistory.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("documentHistory.status", new ActionError("error.documentHistory.status"));
			}
*/ 			com.app.docmgr.model.Folder  folderObj =null;
			com.app.docmgr.service.FolderService folderService = com.app.docmgr.service.FolderService.getInstance();
			try{
				String folderStr = request.getParameter("folder");
				
				if(folderStr == null || folderStr.trim().length() == 0 ){
					documentHistory.setFolder(null);
				}else{			
					folderObj = folderService.get(new Long(folderStr));
					documentHistory.setFolder(folderObj);
				}
			}catch(Exception ex){}	
			if(folderObj==null){
				errors.add("documentHistory.folder", new ActionError("error.documentHistory.folder"));
			}
			com.app.docmgr.model.Document  parentObj =null;
			com.app.docmgr.service.DocumentService parentService = com.app.docmgr.service.DocumentService.getInstance();
			try{
				String parentStr = request.getParameter("parent");
				
				if(parentStr == null || parentStr.trim().length() == 0 ){
					documentHistory.setParent(null);
				}else{			
					parentObj = parentService.get(new Long(parentStr));
					documentHistory.setParent(parentObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

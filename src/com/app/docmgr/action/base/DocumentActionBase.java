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
 * @createDate 06-10-2017 22:19:39
 */


public class DocumentActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.DocumentActionBase");	
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
    	
    	request.getSession().removeAttribute("document");
		
		String document_filter = request.getParameter("document_filter");
		//this for orderBy field ASC/DESC
		String document_fieldOrder = request.getParameter("document_fieldOrder");
		String document_orderType = request.getParameter("document_orderType");
		
		if(document_fieldOrder == null || document_fieldOrder.length() == 0) document_fieldOrder = null;
		if(document_orderType == null || document_orderType.length() == 0) document_orderType = null;
		request.getSession().setAttribute("document_fieldOrder", document_fieldOrder==null?"":document_fieldOrder);	 
		request.getSession().setAttribute("document_orderType", document_orderType==null?"":document_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Document'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
			com.app.docmgr.service.DocumentService parentDocumentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentDocumentList = parentDocumentService.getList(null, null);
			request.setAttribute("parentDocumentList", parentDocumentList);
		}catch(Exception ex){
		
		}
		StringBuffer document_filterSb = new StringBuffer();
		String param_document_name_filter = "";
		if(request.getParameter("document_name_filter")!=null){
			param_document_name_filter = request.getParameter("document_name_filter");
			if(param_document_name_filter.length() > 0 ){				
				document_filterSb.append("  AND document.name like '%"+param_document_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_name_filter", param_document_name_filter);
		String param_document_documentType_filter = "";
		if(request.getParameter("document_documentType_filter")!=null){
			param_document_documentType_filter = request.getParameter("document_documentType_filter");
			if(param_document_documentType_filter.length() > 0 ){				
				document_filterSb.append("  AND document.documentType like '%"+param_document_documentType_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_documentType_filter", param_document_documentType_filter);
		String param_document_contentType_filter = "";
		if(request.getParameter("document_contentType_filter")!=null){
			param_document_contentType_filter = request.getParameter("document_contentType_filter");
			if(param_document_contentType_filter.length() > 0 ){				
				document_filterSb.append("  AND document.contentType like '%"+param_document_contentType_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_contentType_filter", param_document_contentType_filter);
		String param_document_documentNumber_filter = "";
		if(request.getParameter("document_documentNumber_filter")!=null){
			param_document_documentNumber_filter = request.getParameter("document_documentNumber_filter");
			if(param_document_documentNumber_filter.length() > 0 ){				
				document_filterSb.append("  AND document.documentNumber like '%"+param_document_documentNumber_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_documentNumber_filter", param_document_documentNumber_filter);
		String param_document_repositoryId_filter = "";
		if(request.getParameter("document_repositoryId_filter")!=null){
			param_document_repositoryId_filter = request.getParameter("document_repositoryId_filter");
			if(param_document_repositoryId_filter.length() > 0 ){				
				document_filterSb.append("  AND document.repositoryId like '%"+param_document_repositoryId_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_repositoryId_filter", param_document_repositoryId_filter);
		String param_document_documentVersion_filter = "";
		if(request.getParameter("document_documentVersion_filter")!=null){
			param_document_documentVersion_filter = request.getParameter("document_documentVersion_filter");
			if(param_document_documentVersion_filter.length() > 0 ){				
				document_filterSb.append("  AND document.documentVersion like '%"+param_document_documentVersion_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_documentVersion_filter", param_document_documentVersion_filter);
		String param_document_description_filter = "";
		if(request.getParameter("document_description_filter")!=null){
			param_document_description_filter = request.getParameter("document_description_filter");
			if(param_document_description_filter.length() > 0 ){				
				document_filterSb.append("  AND document.description like '%"+param_document_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_description_filter", param_document_description_filter);
		String param_document_createdDate_filter_start = "";
		if(request.getParameter("document_createdDate_filter_start")!=null){
			param_document_createdDate_filter_start = request.getParameter("document_createdDate_filter_start");
			if(param_document_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_document_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_document_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_document_createdDate_filter_start));
					String param_document_createdDate_filter_start_cal_val = param_document_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_document_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_document_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					document_filterSb.append("  AND document.createdDate >= '"+param_document_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("document_createdDate_filter_start", param_document_createdDate_filter_start);

		String param_document_createdDate_filter_end = "";
		if(request.getParameter("document_createdDate_filter_end")!=null){
			param_document_createdDate_filter_end = request.getParameter("document_createdDate_filter_end");
			if(param_document_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_document_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_document_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_document_createdDate_filter_end));
					String param_document_createdDate_filter_end_cal_val = param_document_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_document_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_document_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					document_filterSb.append("  AND document.createdDate  <= '"+param_document_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("document_createdDate_filter_end", param_document_createdDate_filter_end);

		String param_document_createdBy_filter = "";
		if(request.getParameter("document_createdBy_filter")!=null){
			param_document_createdBy_filter = request.getParameter("document_createdBy_filter");
			if(param_document_createdBy_filter.length() > 0 ){				
				document_filterSb.append("  AND document.createdBy like '%"+param_document_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_createdBy_filter", param_document_createdBy_filter);
		String param_document_lastUpdatedDate_filter_start = "";
		if(request.getParameter("document_lastUpdatedDate_filter_start")!=null){
			param_document_lastUpdatedDate_filter_start = request.getParameter("document_lastUpdatedDate_filter_start");
			if(param_document_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_document_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_document_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_document_lastUpdatedDate_filter_start));
					String param_document_lastUpdatedDate_filter_start_cal_val = param_document_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_document_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_document_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					document_filterSb.append("  AND document.lastUpdatedDate >= '"+param_document_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("document_lastUpdatedDate_filter_start", param_document_lastUpdatedDate_filter_start);

		String param_document_lastUpdatedDate_filter_end = "";
		if(request.getParameter("document_lastUpdatedDate_filter_end")!=null){
			param_document_lastUpdatedDate_filter_end = request.getParameter("document_lastUpdatedDate_filter_end");
			if(param_document_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_document_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_document_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_document_lastUpdatedDate_filter_end));
					String param_document_lastUpdatedDate_filter_end_cal_val = param_document_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_document_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_document_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					document_filterSb.append("  AND document.lastUpdatedDate  <= '"+param_document_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("document_lastUpdatedDate_filter_end", param_document_lastUpdatedDate_filter_end);

		String param_document_lastUpdatedBy_filter = "";
		if(request.getParameter("document_lastUpdatedBy_filter")!=null){
			param_document_lastUpdatedBy_filter = request.getParameter("document_lastUpdatedBy_filter");
			if(param_document_lastUpdatedBy_filter.length() > 0 ){				
				document_filterSb.append("  AND document.lastUpdatedBy like '%"+param_document_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("document_lastUpdatedBy_filter", param_document_lastUpdatedBy_filter);
		String param_document_securityLevel_filter = "";
		if(request.getParameter("document_securityLevel_filter")!=null){
			param_document_securityLevel_filter = request.getParameter("document_securityLevel_filter");
			if(param_document_securityLevel_filter.length() > 0 ){				
				document_filterSb.append("  AND document.securityLevel = '"+param_document_securityLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("document_securityLevel_filter", param_document_securityLevel_filter);
		String param_document_status_filter = "";
		if(request.getParameter("document_status_filter")!=null){
			param_document_status_filter = request.getParameter("document_status_filter");
			if(param_document_status_filter.length() > 0 ){				
				document_filterSb.append("  AND document.status = '"+param_document_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("document_status_filter", param_document_status_filter);
		String param_document_parentFolder_filter = "";
		if(request.getParameter("document_parentFolder_filter")!=null){
			param_document_parentFolder_filter = request.getParameter("document_parentFolder_filter");
			if(param_document_parentFolder_filter.length() > 0 ){				
				document_filterSb.append("  AND document.parentFolder = '"+param_document_parentFolder_filter+"' ");
			}
		}		
		request.getSession().setAttribute("document_parentFolder_filter", param_document_parentFolder_filter);
		String param_document_parentDocument_filter = "";
		if(request.getParameter("document_parentDocument_filter")!=null){
			param_document_parentDocument_filter = request.getParameter("document_parentDocument_filter");
			if(param_document_parentDocument_filter.length() > 0 ){				
				document_filterSb.append("  AND document.parentDocument = '"+param_document_parentDocument_filter+"' ");
			}
		}		
		request.getSession().setAttribute("document_parentDocument_filter", param_document_parentDocument_filter);
		
		if(document_fieldOrder!=null && document_orderType != null )document_filterSb.append(" ORDER BY "+document_fieldOrder+" "+document_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		DocumentService documentService = DocumentService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList documentList = documentService.getPartialList(document_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, documentList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, documentList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("documentList", documentList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("document");
		
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
    		DocumentService documentService = DocumentService.getInstance();
    		List documentList = documentService.getList(parentFilter , null);
    		request.setAttribute("documentList", documentList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do");
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
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do");
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
    		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null) document = new Document();
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Document'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
			com.app.docmgr.service.DocumentService parentDocumentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentDocumentList = parentDocumentService.getList(null, null);
			request.setAttribute("parentDocumentList", parentDocumentList);

    		request.getSession().setAttribute("document", document);
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
    		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, document, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Document'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
			com.app.docmgr.service.DocumentService parentDocumentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentDocumentList = parentDocumentService.getList(null, null);
			request.setAttribute("parentDocumentList", parentDocumentList);
	
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
    		Document document = (Document) request.getSession().getAttribute("document");
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","new"));
			document.setLastUpdatedDate(new Date());
			document.setCreatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
			document.setCreatedBy(_doneBy);
    		DocumentService.getInstance().add(document);
    		request.getSession().removeAttribute("document");
    		response.sendRedirect("document.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Document'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
			com.app.docmgr.service.DocumentService parentDocumentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentDocumentList = parentDocumentService.getList(null, null);
			request.setAttribute("parentDocumentList", parentDocumentList);

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
    		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, document, errors);
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Document'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
			com.app.docmgr.service.DocumentService parentDocumentService = com.app.docmgr.service.DocumentService.getInstance();
			List parentDocumentList = parentDocumentService.getList(null, null);
			request.setAttribute("parentDocumentList", parentDocumentList);

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
    		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=edit_confirm");
    		}
    		
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		request.getSession().removeAttribute("document");
    		response.sendRedirect("document.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=delete_confirm");
    		}
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","deleted"));
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("document.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=submit_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","submitted"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=approve_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","approved"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=reject_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","rejected"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=pending_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","pending"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=process_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","processed"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=close_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","closed"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=remove_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","removed"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Document document = (Document) request.getSession().getAttribute("document");
    		if (document == null){
	    		document = DocumentService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("document", document);
	    	}
    		if(document == null){
    			response.sendRedirect("document.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("document.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Document document = (Document) request.getSession().getAttribute("document");
    		if(document == null){
    			response.sendRedirect("document.do?action=cancel_confirm");
    		}
    		document.setStatus(StatusService.getInstance().getByTypeandCode("Document","cancelled"));
			document.setLastUpdatedDate(new Date());
			document.setLastUpdatedBy(_doneBy);
    		DocumentService.getInstance().update(document);
    		response.sendRedirect("document.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("document.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Document document, ActionErrors errors){
    	try{    		
			String name = request.getParameter("name");
			document.setName(name);
			String documentType = request.getParameter("documentType");
			document.setDocumentType(documentType);
			if(documentType==null || documentType.trim().length() == 0 ){
				errors.add("document.documentType", new ActionError("error.document.documentType"));
			}
			String contentType = request.getParameter("contentType");
			document.setContentType(contentType);
			if(contentType==null || contentType.trim().length() == 0 ){
				errors.add("document.contentType", new ActionError("error.document.contentType"));
			}
			String documentNumber = request.getParameter("documentNumber");
			document.setDocumentNumber(documentNumber);
			String repositoryId = request.getParameter("repositoryId");
			document.setRepositoryId(repositoryId);
			String documentVersion = request.getParameter("documentVersion");
			document.setDocumentVersion(documentVersion);
			String description = request.getParameter("description");
			document.setDescription(description);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				document.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					document.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("document.createdDate", new ActionError("error.document.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			document.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("document.createdBy", new ActionError("error.document.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				document.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					document.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			document.setLastUpdatedBy(lastUpdatedBy);
*/ 
			com.app.docmgr.model.Lookup  securityLevelObj =null;
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String securityLevelStr = request.getParameter("securityLevel");
				
				if(securityLevelStr == null || securityLevelStr.trim().length() == 0 ){
					document.setSecurityLevel(null);
				}else{			
					securityLevelObj = securityLevelService.get(new Long(securityLevelStr));
					document.setSecurityLevel(securityLevelObj);
				}
			}catch(Exception ex){}	
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					document.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					document.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("document.status", new ActionError("error.document.status"));
			}
*/ 			com.app.docmgr.model.Folder  parentFolderObj =null;
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			try{
				String parentFolderStr = request.getParameter("parentFolder");
				
				if(parentFolderStr == null || parentFolderStr.trim().length() == 0 ){
					document.setParentFolder(null);
				}else{			
					parentFolderObj = parentFolderService.get(new Long(parentFolderStr));
					document.setParentFolder(parentFolderObj);
				}
			}catch(Exception ex){}	
			if(parentFolderObj==null){
				errors.add("document.parentFolder", new ActionError("error.document.parentFolder"));
			}
			com.app.docmgr.model.Document  parentDocumentObj =null;
			com.app.docmgr.service.DocumentService parentDocumentService = com.app.docmgr.service.DocumentService.getInstance();
			try{
				String parentDocumentStr = request.getParameter("parentDocument");
				
				if(parentDocumentStr == null || parentDocumentStr.trim().length() == 0 ){
					document.setParentDocument(null);
				}else{			
					parentDocumentObj = parentDocumentService.get(new Long(parentDocumentStr));
					document.setParentDocument(parentDocumentObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

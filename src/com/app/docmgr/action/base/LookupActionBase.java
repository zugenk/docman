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


public class LookupActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.LookupActionBase");	
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
    	
    	request.getSession().removeAttribute("lookup");
		
		String lookup_filter = request.getParameter("lookup_filter");
		//this for orderBy field ASC/DESC
		String lookup_fieldOrder = request.getParameter("lookup_fieldOrder");
		String lookup_orderType = request.getParameter("lookup_orderType");
		
		if(lookup_fieldOrder == null || lookup_fieldOrder.length() == 0) lookup_fieldOrder = null;
		if(lookup_orderType == null || lookup_orderType.length() == 0) lookup_orderType = null;
		request.getSession().setAttribute("lookup_fieldOrder", lookup_fieldOrder==null?"":lookup_fieldOrder);	 
		request.getSession().setAttribute("lookup_orderType", lookup_orderType==null?"":lookup_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Lookup'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer lookup_filterSb = new StringBuffer();
		String param_lookup_type_filter = "";
		if(request.getParameter("lookup_type_filter")!=null){
			param_lookup_type_filter = request.getParameter("lookup_type_filter");
			if(param_lookup_type_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.type like '%"+param_lookup_type_filter+"%' ");
			}
		}
		request.getSession().setAttribute("lookup_type_filter", param_lookup_type_filter);
		String param_lookup_code_filter = "";
		if(request.getParameter("lookup_code_filter")!=null){
			param_lookup_code_filter = request.getParameter("lookup_code_filter");
			if(param_lookup_code_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.code like '%"+param_lookup_code_filter+"%' ");
			}
		}
		request.getSession().setAttribute("lookup_code_filter", param_lookup_code_filter);
		String param_lookup_name_filter = "";
		if(request.getParameter("lookup_name_filter")!=null){
			param_lookup_name_filter = request.getParameter("lookup_name_filter");
			if(param_lookup_name_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.name like '%"+param_lookup_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("lookup_name_filter", param_lookup_name_filter);
		String param_lookup_description_filter = "";
		if(request.getParameter("lookup_description_filter")!=null){
			param_lookup_description_filter = request.getParameter("lookup_description_filter");
			if(param_lookup_description_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.description like '%"+param_lookup_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("lookup_description_filter", param_lookup_description_filter);
		String param_lookup_shortname_filter = "";
		if(request.getParameter("lookup_shortname_filter")!=null){
			param_lookup_shortname_filter = request.getParameter("lookup_shortname_filter");
			if(param_lookup_shortname_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.shortname like '%"+param_lookup_shortname_filter+"%' ");
			}
		}
		request.getSession().setAttribute("lookup_shortname_filter", param_lookup_shortname_filter);
		String param_lookup_filter_filter = "";
		if(request.getParameter("lookup_filter_filter")!=null){
			param_lookup_filter_filter = request.getParameter("lookup_filter_filter");
			if(param_lookup_filter_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.filter like '%"+param_lookup_filter_filter+"%' ");
			}
		}
		request.getSession().setAttribute("lookup_filter_filter", param_lookup_filter_filter);
		String param_lookup_status_filter = "";
		if(request.getParameter("lookup_status_filter")!=null){
			param_lookup_status_filter = request.getParameter("lookup_status_filter");
			if(param_lookup_status_filter.length() > 0 ){				
				lookup_filterSb.append("  AND lookup.status = '"+param_lookup_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("lookup_status_filter", param_lookup_status_filter);
		
		if(lookup_fieldOrder!=null && lookup_orderType != null )lookup_filterSb.append(" ORDER BY "+lookup_fieldOrder+" "+lookup_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		LookupService lookupService = LookupService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList lookupList = lookupService.getPartialList(lookup_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, lookupList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, lookupList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("lookupList", lookupList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("lookup");
		
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
    		LookupService lookupService = LookupService.getInstance();
    		List lookupList = lookupService.getList(parentFilter , null);
    		request.setAttribute("lookupList", lookupList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do");
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
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do");
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
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null) lookup = new Lookup();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Lookup'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("lookup", lookup);
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
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, lookup, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Lookup'  ", null);
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
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","new"));
    		LookupService.getInstance().add(lookup);
    		request.getSession().removeAttribute("lookup");
    		response.sendRedirect("lookup.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Lookup'  ", null);
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
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, lookup, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Lookup'  ", null);
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
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=edit_confirm");
    		}
    		
    		LookupService.getInstance().update(lookup);
    		request.getSession().removeAttribute("lookup");
    		response.sendRedirect("lookup.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=delete_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","deleted"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("lookup.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=submit_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","submitted"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=approve_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","approved"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=reject_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","rejected"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=pending_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","pending"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=process_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","processed"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=close_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","closed"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=remove_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","removed"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if (lookup == null){
	    		lookup = LookupService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("lookup", lookup);
	    	}
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("lookup.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Lookup lookup = (Lookup) request.getSession().getAttribute("lookup");
    		if(lookup == null){
    			response.sendRedirect("lookup.do?action=cancel_confirm");
    		}
    		lookup.setStatus(StatusService.getInstance().getByTypeandCode("Lookup","cancelled"));
    		LookupService.getInstance().update(lookup);
    		response.sendRedirect("lookup.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("lookup.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Lookup lookup, ActionErrors errors){
    	try{    		
			String type = request.getParameter("type");
			lookup.setType(type);
			if(type==null || type.trim().length() == 0 ){
				errors.add("lookup.type", new ActionError("error.lookup.type"));
			}
			String code = request.getParameter("code");
			lookup.setCode(code);
			if(code==null || code.trim().length() == 0 ){
				errors.add("lookup.code", new ActionError("error.lookup.code"));
			}
			String name = request.getParameter("name");
			lookup.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("lookup.name", new ActionError("error.lookup.name"));
			}
			String priority = request.getParameter("priority");
			try{
				lookup.setPriority(new java.lang.Integer(priority));
			}catch(Exception ex){}
			String description = request.getParameter("description");
			lookup.setDescription(description);
			String shortname = request.getParameter("shortname");
			lookup.setShortname(shortname);
			String filter = request.getParameter("filter");
			lookup.setFilter(filter);

/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					lookup.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					lookup.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("lookup.status", new ActionError("error.lookup.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

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


public class StatusActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.StatusActionBase");	
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
			}
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}

		return forward;
    }	    
    
    public ActionForward doList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	
    	request.getSession().removeAttribute("status");
		
		String status_filter = request.getParameter("status_filter");
		//this for orderBy field ASC/DESC
		String status_fieldOrder = request.getParameter("status_fieldOrder");
		String status_orderType = request.getParameter("status_orderType");
		
		if(status_fieldOrder == null || status_fieldOrder.length() == 0) status_fieldOrder = null;
		if(status_orderType == null || status_orderType.length() == 0) status_orderType = null;
		request.getSession().setAttribute("status_fieldOrder", status_fieldOrder==null?"":status_fieldOrder);	 
		request.getSession().setAttribute("status_orderType", status_orderType==null?"":status_orderType);
		
		try{ 
		}catch(Exception ex){
		
		}
		StringBuffer status_filterSb = new StringBuffer();
		String param_status_type_filter = "";
		if(request.getParameter("status_type_filter")!=null){
			param_status_type_filter = request.getParameter("status_type_filter");
			if(param_status_type_filter.length() > 0 ){				
				status_filterSb.append("  AND status.type like '%"+param_status_type_filter+"%' ");
			}
		}
		request.getSession().setAttribute("status_type_filter", param_status_type_filter);
		String param_status_code_filter = "";
		if(request.getParameter("status_code_filter")!=null){
			param_status_code_filter = request.getParameter("status_code_filter");
			if(param_status_code_filter.length() > 0 ){				
				status_filterSb.append("  AND status.code like '%"+param_status_code_filter+"%' ");
			}
		}
		request.getSession().setAttribute("status_code_filter", param_status_code_filter);
		String param_status_state_filter = "";
		if(request.getParameter("status_state_filter")!=null){
			param_status_state_filter = request.getParameter("status_state_filter");
			if(param_status_state_filter.length() > 0 ){				
				status_filterSb.append("  AND status.state like '%"+param_status_state_filter+"%' ");
			}
		}
		request.getSession().setAttribute("status_state_filter", param_status_state_filter);
		String param_status_name_filter = "";
		if(request.getParameter("status_name_filter")!=null){
			param_status_name_filter = request.getParameter("status_name_filter");
			if(param_status_name_filter.length() > 0 ){				
				status_filterSb.append("  AND status.name like '%"+param_status_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("status_name_filter", param_status_name_filter);
		String param_status_description_filter = "";
		if(request.getParameter("status_description_filter")!=null){
			param_status_description_filter = request.getParameter("status_description_filter");
			if(param_status_description_filter.length() > 0 ){				
				status_filterSb.append("  AND status.description like '%"+param_status_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("status_description_filter", param_status_description_filter);
		
		if(status_fieldOrder!=null && status_orderType != null )status_filterSb.append(" ORDER BY "+status_fieldOrder+" "+status_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		StatusService statusService = StatusService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList statusList = statusService.getPartialList(status_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, statusList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, statusList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("statusList", statusList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("status");
		
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
    		StatusService statusService = StatusService.getInstance();
    		List statusList = statusService.getList(parentFilter , null);
    		request.setAttribute("statusList", statusList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Status status = (Status) request.getSession().getAttribute("status");
    		if (status == null){
	    		status = StatusService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("status", status);
	    	}
    		if(status == null){
    			response.sendRedirect("status.do");
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
    		Status status = (Status) request.getSession().getAttribute("status");
    		if (status == null){
	    		status = StatusService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("status", status);
	    	}
    		if(status == null){
    			response.sendRedirect("status.do");
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
    		Status status = (Status) request.getSession().getAttribute("status");
    		if(status == null) status = new Status();
    		

    		request.getSession().setAttribute("status", status);
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
    		Status status = (Status) request.getSession().getAttribute("status");
    		if(status == null){
    			response.sendRedirect("status.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, status, errors);
    		//set Many To One Property
    		
	
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
    		Status status = (Status) request.getSession().getAttribute("status");
    		StatusService.getInstance().add(status);
    		request.getSession().removeAttribute("status");
    		response.sendRedirect("status.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("status.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Status status = (Status) request.getSession().getAttribute("status");
    		if (status == null){
	    		status = StatusService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("status", status);
	    	}
    		if(status == null){
    			response.sendRedirect("status.do");
    			return null;
    		}
    		

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
    		Status status = (Status) request.getSession().getAttribute("status");
    		if(status == null){
    			response.sendRedirect("status.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, status, errors);
    		

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
    		Status status = (Status) request.getSession().getAttribute("status");
    		if(status == null){
    			response.sendRedirect("status.do?action=edit_confirm");
    		}
    		
    		StatusService.getInstance().update(status);
    		request.getSession().removeAttribute("status");
    		response.sendRedirect("status.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("status.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Status status = (Status) request.getSession().getAttribute("status");
    		if (status == null){
	    		status = StatusService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("status", status);
	    	}
    		if(status == null){
    			response.sendRedirect("status.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("status.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Status status = (Status) request.getSession().getAttribute("status");
    		if(status == null){
    			response.sendRedirect("status.do?action=delete_confirm");
    		}
    		StatusService.getInstance().delete(status);
    		request.getSession().removeAttribute("status");
    		response.sendRedirect("status.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("status.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Status status, ActionErrors errors){
    	try{    		
			String type = request.getParameter("type");
			status.setType(type);
			if(type==null || type.trim().length() == 0 ){
				errors.add("status.type", new ActionError("error.status.type"));
			}
			String code = request.getParameter("code");
			status.setCode(code);
			if(code==null || code.trim().length() == 0 ){
				errors.add("status.code", new ActionError("error.status.code"));
			}
			String state = request.getParameter("state");
			status.setState(state);
			if(state==null || state.trim().length() == 0 ){
				errors.add("status.state", new ActionError("error.status.state"));
			}
			String name = request.getParameter("name");
			status.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("status.name", new ActionError("error.status.name"));
			}
			String description = request.getParameter("description");
			status.setDescription(description);


    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

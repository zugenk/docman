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


public class SystemSequenceActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.SystemSequenceActionBase");	
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
    	
    	request.getSession().removeAttribute("systemSequence");
		
		String systemSequence_filter = request.getParameter("systemSequence_filter");
		//this for orderBy field ASC/DESC
		String systemSequence_fieldOrder = request.getParameter("systemSequence_fieldOrder");
		String systemSequence_orderType = request.getParameter("systemSequence_orderType");
		
		if(systemSequence_fieldOrder == null || systemSequence_fieldOrder.length() == 0) systemSequence_fieldOrder = null;
		if(systemSequence_orderType == null || systemSequence_orderType.length() == 0) systemSequence_orderType = null;
		request.getSession().setAttribute("systemSequence_fieldOrder", systemSequence_fieldOrder==null?"":systemSequence_fieldOrder);	 
		request.getSession().setAttribute("systemSequence_orderType", systemSequence_orderType==null?"":systemSequence_orderType);
		
		try{ 
		}catch(Exception ex){
		
		}
		StringBuffer systemSequence_filterSb = new StringBuffer();
		String param_systemSequence_type_filter = "";
		if(request.getParameter("systemSequence_type_filter")!=null){
			param_systemSequence_type_filter = request.getParameter("systemSequence_type_filter");
			if(param_systemSequence_type_filter.length() > 0 ){				
				systemSequence_filterSb.append("  AND systemSequence.type like '%"+param_systemSequence_type_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemSequence_type_filter", param_systemSequence_type_filter);
		String param_systemSequence_param_filter = "";
		if(request.getParameter("systemSequence_param_filter")!=null){
			param_systemSequence_param_filter = request.getParameter("systemSequence_param_filter");
			if(param_systemSequence_param_filter.length() > 0 ){				
				systemSequence_filterSb.append("  AND systemSequence.param like '%"+param_systemSequence_param_filter+"%' ");
			}
		}
		request.getSession().setAttribute("systemSequence_param_filter", param_systemSequence_param_filter);
		
		if(systemSequence_fieldOrder!=null && systemSequence_orderType != null )systemSequence_filterSb.append(" ORDER BY "+systemSequence_fieldOrder+" "+systemSequence_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		SystemSequenceService systemSequenceService = SystemSequenceService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList systemSequenceList = systemSequenceService.getPartialList(systemSequence_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, systemSequenceList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, systemSequenceList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("systemSequenceList", systemSequenceList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("systemSequence");
		
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
    		SystemSequenceService systemSequenceService = SystemSequenceService.getInstance();
    		List systemSequenceList = systemSequenceService.getList(parentFilter , null);
    		request.setAttribute("systemSequenceList", systemSequenceList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if (systemSequence == null){
	    		systemSequence = SystemSequenceService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemSequence", systemSequence);
	    	}
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do");
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
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if (systemSequence == null){
	    		systemSequence = SystemSequenceService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemSequence", systemSequence);
	    	}
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do");
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
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if(systemSequence == null) systemSequence = new SystemSequence();
    		

    		request.getSession().setAttribute("systemSequence", systemSequence);
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
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, systemSequence, errors);
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
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		SystemSequenceService.getInstance().add(systemSequence);
    		request.getSession().removeAttribute("systemSequence");
    		response.sendRedirect("systemSequence.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemSequence.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if (systemSequence == null){
	    		systemSequence = SystemSequenceService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemSequence", systemSequence);
	    	}
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do");
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
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, systemSequence, errors);
    		

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
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do?action=edit_confirm");
    		}
    		
    		SystemSequenceService.getInstance().update(systemSequence);
    		request.getSession().removeAttribute("systemSequence");
    		response.sendRedirect("systemSequence.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("systemSequence.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if (systemSequence == null){
	    		systemSequence = SystemSequenceService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("systemSequence", systemSequence);
	    	}
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("systemSequence.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		SystemSequence systemSequence = (SystemSequence) request.getSession().getAttribute("systemSequence");
    		if(systemSequence == null){
    			response.sendRedirect("systemSequence.do?action=delete_confirm");
    		}
    		SystemSequenceService.getInstance().delete(systemSequence);
    		request.getSession().removeAttribute("systemSequence");
    		response.sendRedirect("systemSequence.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("systemSequence.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, SystemSequence systemSequence, ActionErrors errors){
    	try{    		
			String type = request.getParameter("type");
			systemSequence.setType(type);
			if(type==null || type.trim().length() == 0 ){
				errors.add("systemSequence.type", new ActionError("error.systemSequence.type"));
			}
			String param = request.getParameter("param");
			systemSequence.setParam(param);
			if(param==null || param.trim().length() == 0 ){
				errors.add("systemSequence.param", new ActionError("error.systemSequence.param"));
			}
			String sequence = request.getParameter("sequence");
			try{
				systemSequence.setSequence(new java.lang.Long(sequence));
			}catch(Exception ex){}
			if(sequence==null || sequence.trim().length() == 0 ){
				errors.add("systemSequence.sequence", new ActionError("error.systemSequence.sequence"));
			}


    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

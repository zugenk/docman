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


public class PrivilegeActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.PrivilegeActionBase");	
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
			}
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}

		return forward;
    }	    
    
    public ActionForward doList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	
    	request.getSession().removeAttribute("privilege");
		
		String privilege_filter = request.getParameter("privilege_filter");
		//this for orderBy field ASC/DESC
		String privilege_fieldOrder = request.getParameter("privilege_fieldOrder");
		String privilege_orderType = request.getParameter("privilege_orderType");
		
		if(privilege_fieldOrder == null || privilege_fieldOrder.length() == 0) privilege_fieldOrder = null;
		if(privilege_orderType == null || privilege_orderType.length() == 0) privilege_orderType = null;
		request.getSession().setAttribute("privilege_fieldOrder", privilege_fieldOrder==null?"":privilege_fieldOrder);	 
		request.getSession().setAttribute("privilege_orderType", privilege_orderType==null?"":privilege_orderType);
		
		try{ 
		}catch(Exception ex){
		
		}
		StringBuffer privilege_filterSb = new StringBuffer();
		String param_privilege_name_filter = "";
		if(request.getParameter("privilege_name_filter")!=null){
			param_privilege_name_filter = request.getParameter("privilege_name_filter");
			if(param_privilege_name_filter.length() > 0 ){				
				privilege_filterSb.append("  AND privilege.name like '%"+param_privilege_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("privilege_name_filter", param_privilege_name_filter);
		String param_privilege_vgroup_filter = "";
		if(request.getParameter("privilege_vgroup_filter")!=null){
			param_privilege_vgroup_filter = request.getParameter("privilege_vgroup_filter");
			if(param_privilege_vgroup_filter.length() > 0 ){				
				privilege_filterSb.append("  AND privilege.vgroup like '%"+param_privilege_vgroup_filter+"%' ");
			}
		}
		request.getSession().setAttribute("privilege_vgroup_filter", param_privilege_vgroup_filter);
		String param_privilege_description_filter = "";
		if(request.getParameter("privilege_description_filter")!=null){
			param_privilege_description_filter = request.getParameter("privilege_description_filter");
			if(param_privilege_description_filter.length() > 0 ){				
				privilege_filterSb.append("  AND privilege.description like '%"+param_privilege_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("privilege_description_filter", param_privilege_description_filter);
		
		if(privilege_fieldOrder!=null && privilege_orderType != null )privilege_filterSb.append(" ORDER BY "+privilege_fieldOrder+" "+privilege_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		PrivilegeService privilegeService = PrivilegeService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList privilegeList = privilegeService.getPartialList(privilege_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, privilegeList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, privilegeList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("privilegeList", privilegeList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("privilege");
		
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
    		PrivilegeService privilegeService = PrivilegeService.getInstance();
    		List privilegeList = privilegeService.getList(parentFilter , null);
    		request.setAttribute("privilegeList", privilegeList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if (privilege == null){
	    		privilege = PrivilegeService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("privilege", privilege);
	    	}
    		if(privilege == null){
    			response.sendRedirect("privilege.do");
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
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if (privilege == null){
	    		privilege = PrivilegeService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("privilege", privilege);
	    	}
    		if(privilege == null){
    			response.sendRedirect("privilege.do");
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
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if(privilege == null) privilege = new Privilege();
    		

    		request.getSession().setAttribute("privilege", privilege);
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
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if(privilege == null){
    			response.sendRedirect("privilege.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, privilege, errors);
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
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		PrivilegeService.getInstance().add(privilege);
    		request.getSession().removeAttribute("privilege");
    		response.sendRedirect("privilege.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("privilege.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if (privilege == null){
	    		privilege = PrivilegeService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("privilege", privilege);
	    	}
    		if(privilege == null){
    			response.sendRedirect("privilege.do");
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
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if(privilege == null){
    			response.sendRedirect("privilege.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, privilege, errors);
    		

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
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if(privilege == null){
    			response.sendRedirect("privilege.do?action=edit_confirm");
    		}
    		
    		PrivilegeService.getInstance().update(privilege);
    		request.getSession().removeAttribute("privilege");
    		response.sendRedirect("privilege.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("privilege.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if (privilege == null){
	    		privilege = PrivilegeService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("privilege", privilege);
	    	}
    		if(privilege == null){
    			response.sendRedirect("privilege.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("privilege.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Privilege privilege = (Privilege) request.getSession().getAttribute("privilege");
    		if(privilege == null){
    			response.sendRedirect("privilege.do?action=delete_confirm");
    		}
    		PrivilegeService.getInstance().delete(privilege);
    		request.getSession().removeAttribute("privilege");
    		response.sendRedirect("privilege.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("privilege.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Privilege privilege, ActionErrors errors){
    	try{    		
			String name = request.getParameter("name");
			privilege.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("privilege.name", new ActionError("error.privilege.name"));
			}
			String vgroup = request.getParameter("vgroup");
			privilege.setVgroup(vgroup);
			if(vgroup==null || vgroup.trim().length() == 0 ){
				errors.add("privilege.vgroup", new ActionError("error.privilege.vgroup"));
			}
			String description = request.getParameter("description");
			privilege.setDescription(description);


    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

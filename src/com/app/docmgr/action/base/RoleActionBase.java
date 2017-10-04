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


public class RoleActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.RoleActionBase");	
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
    	
    	request.getSession().removeAttribute("role");
		
		String role_filter = request.getParameter("role_filter");
		//this for orderBy field ASC/DESC
		String role_fieldOrder = request.getParameter("role_fieldOrder");
		String role_orderType = request.getParameter("role_orderType");
		
		if(role_fieldOrder == null || role_fieldOrder.length() == 0) role_fieldOrder = null;
		if(role_orderType == null || role_orderType.length() == 0) role_orderType = null;
		request.getSession().setAttribute("role_fieldOrder", role_fieldOrder==null?"":role_fieldOrder);	 
		request.getSession().setAttribute("role_orderType", role_orderType==null?"":role_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Role'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer role_filterSb = new StringBuffer();
		String param_role_name_filter = "";
		if(request.getParameter("role_name_filter")!=null){
			param_role_name_filter = request.getParameter("role_name_filter");
			if(param_role_name_filter.length() > 0 ){				
				role_filterSb.append("  AND role.name like '%"+param_role_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("role_name_filter", param_role_name_filter);
		String param_role_description_filter = "";
		if(request.getParameter("role_description_filter")!=null){
			param_role_description_filter = request.getParameter("role_description_filter");
			if(param_role_description_filter.length() > 0 ){				
				role_filterSb.append("  AND role.description like '%"+param_role_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("role_description_filter", param_role_description_filter);
		String param_role_createdDate_filter_start = "";
		if(request.getParameter("role_createdDate_filter_start")!=null){
			param_role_createdDate_filter_start = request.getParameter("role_createdDate_filter_start");
			if(param_role_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_role_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_role_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_role_createdDate_filter_start));
					String param_role_createdDate_filter_start_cal_val = param_role_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_role_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_role_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					role_filterSb.append("  AND role.createdDate >= '"+param_role_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("role_createdDate_filter_start", param_role_createdDate_filter_start);

		String param_role_createdDate_filter_end = "";
		if(request.getParameter("role_createdDate_filter_end")!=null){
			param_role_createdDate_filter_end = request.getParameter("role_createdDate_filter_end");
			if(param_role_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_role_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_role_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_role_createdDate_filter_end));
					String param_role_createdDate_filter_end_cal_val = param_role_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_role_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_role_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					role_filterSb.append("  AND role.createdDate  <= '"+param_role_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("role_createdDate_filter_end", param_role_createdDate_filter_end);

		String param_role_createdBy_filter = "";
		if(request.getParameter("role_createdBy_filter")!=null){
			param_role_createdBy_filter = request.getParameter("role_createdBy_filter");
			if(param_role_createdBy_filter.length() > 0 ){				
				role_filterSb.append("  AND role.createdBy like '%"+param_role_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("role_createdBy_filter", param_role_createdBy_filter);
		String param_role_lastUpdatedDate_filter_start = "";
		if(request.getParameter("role_lastUpdatedDate_filter_start")!=null){
			param_role_lastUpdatedDate_filter_start = request.getParameter("role_lastUpdatedDate_filter_start");
			if(param_role_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_role_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_role_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_role_lastUpdatedDate_filter_start));
					String param_role_lastUpdatedDate_filter_start_cal_val = param_role_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_role_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_role_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					role_filterSb.append("  AND role.lastUpdatedDate >= '"+param_role_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("role_lastUpdatedDate_filter_start", param_role_lastUpdatedDate_filter_start);

		String param_role_lastUpdatedDate_filter_end = "";
		if(request.getParameter("role_lastUpdatedDate_filter_end")!=null){
			param_role_lastUpdatedDate_filter_end = request.getParameter("role_lastUpdatedDate_filter_end");
			if(param_role_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_role_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_role_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_role_lastUpdatedDate_filter_end));
					String param_role_lastUpdatedDate_filter_end_cal_val = param_role_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_role_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_role_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					role_filterSb.append("  AND role.lastUpdatedDate  <= '"+param_role_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("role_lastUpdatedDate_filter_end", param_role_lastUpdatedDate_filter_end);

		String param_role_lastUpdatedBy_filter = "";
		if(request.getParameter("role_lastUpdatedBy_filter")!=null){
			param_role_lastUpdatedBy_filter = request.getParameter("role_lastUpdatedBy_filter");
			if(param_role_lastUpdatedBy_filter.length() > 0 ){				
				role_filterSb.append("  AND role.lastUpdatedBy like '%"+param_role_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("role_lastUpdatedBy_filter", param_role_lastUpdatedBy_filter);
		String param_role_userLevel_filter = "";
		if(request.getParameter("role_userLevel_filter")!=null){
			param_role_userLevel_filter = request.getParameter("role_userLevel_filter");
			if(param_role_userLevel_filter.length() > 0 ){				
				role_filterSb.append("  AND role.userLevel = '"+param_role_userLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("role_userLevel_filter", param_role_userLevel_filter);
		String param_role_status_filter = "";
		if(request.getParameter("role_status_filter")!=null){
			param_role_status_filter = request.getParameter("role_status_filter");
			if(param_role_status_filter.length() > 0 ){				
				role_filterSb.append("  AND role.status = '"+param_role_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("role_status_filter", param_role_status_filter);
		
		if(role_fieldOrder!=null && role_orderType != null )role_filterSb.append(" ORDER BY "+role_fieldOrder+" "+role_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		RoleService roleService = RoleService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList roleList = roleService.getPartialList(role_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, roleList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, roleList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("roleList", roleList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("role");
		
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
    		RoleService roleService = RoleService.getInstance();
    		List roleList = roleService.getList(parentFilter , null);
    		request.setAttribute("roleList", roleList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do");
    			return null;
    		}
    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

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
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do");
    			return null;
    		}
    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		
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
    		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null) role = new Role();
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Role'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
			PrivilegeService privilegeService = PrivilegeService.getInstance();
			List privilegeSetList = privilegeService.getList(null, null);
			request.setAttribute("privilegeSetList", privilegeSetList);

			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			
    		request.getSession().setAttribute("role", role);
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
    		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, role, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Role'  ", null);
			request.setAttribute("statusList", statusList);
*/			PrivilegeService privilegeService = PrivilegeService.getInstance();
			List privilegeSetList = privilegeService.getList(null, null);
			request.setAttribute("privilegeSetList", privilegeSetList);
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);
			
	
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
    		Role role = (Role) request.getSession().getAttribute("role");
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","new"));
			role.setLastUpdatedDate(new Date());
			role.setCreatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
			role.setCreatedBy(_doneBy);
    		RoleService.getInstance().add(role);
    		request.getSession().removeAttribute("role");
    		response.sendRedirect("role.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Role'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			PrivilegeService privilegeService = PrivilegeService.getInstance();
			List privilegeSetList = privilegeService.getList(null, null);
			request.setAttribute("privilegeSetList", privilegeSetList);
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

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
    		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, role, errors);
    		
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList = userLevelService.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList", userLevelList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Role'  ", null);
			request.setAttribute("statusList", statusList);
 */
			PrivilegeService privilegeService = PrivilegeService.getInstance();
			List privilegeSetList = privilegeService.getList(null, null);
			request.setAttribute("privilegeSetList", privilegeSetList);
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			
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
    		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=edit_confirm");
    		}
    		
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		request.getSession().removeAttribute("role");
    		response.sendRedirect("role.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=delete_confirm");
    		}
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","deleted"));
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("role.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=submit_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","submitted"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=approve_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","approved"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=reject_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","rejected"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=pending_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","pending"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=process_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","processed"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=close_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","closed"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=remove_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","removed"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Role role = (Role) request.getSession().getAttribute("role");
    		if (role == null){
	    		role = RoleService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("role", role);
	    	}
    		if(role == null){
    			response.sendRedirect("role.do?action=detail");
    			return null;
    		}
    		    		
			Set privilegeSet = role.getPrivileges();			
			if(privilegeSet == null) privilegeSet = new HashSet();
			request.setAttribute("privilegeSet", privilegeSet);			

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("role.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Role role = (Role) request.getSession().getAttribute("role");
    		if(role == null){
    			response.sendRedirect("role.do?action=cancel_confirm");
    		}
    		role.setStatus(StatusService.getInstance().getByTypeandCode("Role","cancelled"));
			role.setLastUpdatedDate(new Date());
			role.setLastUpdatedBy(_doneBy);
    		RoleService.getInstance().update(role);
    		response.sendRedirect("role.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("role.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Role role, ActionErrors errors){
    	try{    		
			String name = request.getParameter("name");
			role.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("role.name", new ActionError("error.role.name"));
			}
			String description = request.getParameter("description");
			role.setDescription(description);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				role.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					role.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("role.createdDate", new ActionError("error.role.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			role.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("role.createdBy", new ActionError("error.role.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				role.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					role.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				errors.add("role.lastUpdatedDate", new ActionError("error.role.lastUpdatedDate"));
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			role.setLastUpdatedBy(lastUpdatedBy);
			if(lastUpdatedBy==null || lastUpdatedBy.trim().length() == 0 ){
				errors.add("role.lastUpdatedBy", new ActionError("error.role.lastUpdatedBy"));
			}
*/ 
			com.app.docmgr.model.Lookup  userLevelObj =null;
			com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String userLevelStr = request.getParameter("userLevel");
				
				if(userLevelStr == null || userLevelStr.trim().length() == 0 ){
					role.setUserLevel(null);
				}else{			
					userLevelObj = userLevelService.get(new Long(userLevelStr));
					role.setUserLevel(userLevelObj);
				}
			}catch(Exception ex){}	
			if(userLevelObj==null){
				errors.add("role.userLevel", new ActionError("error.role.userLevel"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					role.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					role.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("role.status", new ActionError("error.role.status"));
			}
*/ 
			Set privilegeSet = new HashSet();
			String[] _selectedPrivilege=request.getParameterValues("selected_privilege");
			if (_selectedPrivilege!=null && _selectedPrivilege.length>0) {
				PrivilegeService privilegeService = PrivilegeService.getInstance();
				Set _orgPrivilegeSet = role.getPrivileges();
				if (_orgPrivilegeSet==null)_orgPrivilegeSet=new HashSet();
				Privilege item=null;
				boolean found=false;
				long itemId =0;
				for (int i = 0; i < _selectedPrivilege.length; i++) {
					itemId = Long.parseLong(_selectedPrivilege[i]);
					found=false;
					try {
						for (Iterator iter = _orgPrivilegeSet.iterator(); iter.hasNext()&& !found;) {
							item = (Privilege) iter.next();
							if (itemId==item.getId()){
								found=true;
								privilegeSet.add(item);
							}
						}
						if (!found) privilegeSet.add(privilegeService.get(itemId));
					} catch (Exception e) {
					}
				}
			}
			role.setPrivileges(privilegeSet);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

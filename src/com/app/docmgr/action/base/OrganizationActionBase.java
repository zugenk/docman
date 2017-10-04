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


public class OrganizationActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.OrganizationActionBase");	
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
		}else if("add_User".equalsIgnoreCase(action)){
			forward = doAddUser(mapping, form, request, response);
		}else if("edit_User".equalsIgnoreCase(action)){
			doEditUser(mapping, form, request, response);
		}else if("delete_User".equalsIgnoreCase(action)){
			doDeleteUser(mapping, form, request, response);
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}

		return forward;
    }	    
    
    public ActionForward doList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	
    	request.getSession().removeAttribute("organization");
		
		String organization_filter = request.getParameter("organization_filter");
		//this for orderBy field ASC/DESC
		String organization_fieldOrder = request.getParameter("organization_fieldOrder");
		String organization_orderType = request.getParameter("organization_orderType");
		
		if(organization_fieldOrder == null || organization_fieldOrder.length() == 0) organization_fieldOrder = null;
		if(organization_orderType == null || organization_orderType.length() == 0) organization_orderType = null;
		request.getSession().setAttribute("organization_fieldOrder", organization_fieldOrder==null?"":organization_fieldOrder);	 
		request.getSession().setAttribute("organization_orderType", organization_orderType==null?"":organization_orderType);
		
		try{ 
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
		}catch(Exception ex){
		
		}
		StringBuffer organization_filterSb = new StringBuffer();
		String param_organization_code_filter = "";
		if(request.getParameter("organization_code_filter")!=null){
			param_organization_code_filter = request.getParameter("organization_code_filter");
			if(param_organization_code_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.code like '%"+param_organization_code_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_code_filter", param_organization_code_filter);
		String param_organization_mnemonic_filter = "";
		if(request.getParameter("organization_mnemonic_filter")!=null){
			param_organization_mnemonic_filter = request.getParameter("organization_mnemonic_filter");
			if(param_organization_mnemonic_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.mnemonic like '%"+param_organization_mnemonic_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_mnemonic_filter", param_organization_mnemonic_filter);
		String param_organization_name_filter = "";
		if(request.getParameter("organization_name_filter")!=null){
			param_organization_name_filter = request.getParameter("organization_name_filter");
			if(param_organization_name_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.name like '%"+param_organization_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_name_filter", param_organization_name_filter);
		String param_organization_address_filter = "";
		if(request.getParameter("organization_address_filter")!=null){
			param_organization_address_filter = request.getParameter("organization_address_filter");
			if(param_organization_address_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.address like '%"+param_organization_address_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_address_filter", param_organization_address_filter);
		String param_organization_createdDate_filter_start = "";
		if(request.getParameter("organization_createdDate_filter_start")!=null){
			param_organization_createdDate_filter_start = request.getParameter("organization_createdDate_filter_start");
			if(param_organization_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_organization_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_organization_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_organization_createdDate_filter_start));
					String param_organization_createdDate_filter_start_cal_val = param_organization_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_organization_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_organization_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					organization_filterSb.append("  AND organization.createdDate >= '"+param_organization_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("organization_createdDate_filter_start", param_organization_createdDate_filter_start);

		String param_organization_createdDate_filter_end = "";
		if(request.getParameter("organization_createdDate_filter_end")!=null){
			param_organization_createdDate_filter_end = request.getParameter("organization_createdDate_filter_end");
			if(param_organization_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_organization_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_organization_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_organization_createdDate_filter_end));
					String param_organization_createdDate_filter_end_cal_val = param_organization_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_organization_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_organization_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					organization_filterSb.append("  AND organization.createdDate  <= '"+param_organization_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("organization_createdDate_filter_end", param_organization_createdDate_filter_end);

		String param_organization_createdBy_filter = "";
		if(request.getParameter("organization_createdBy_filter")!=null){
			param_organization_createdBy_filter = request.getParameter("organization_createdBy_filter");
			if(param_organization_createdBy_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.createdBy like '%"+param_organization_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_createdBy_filter", param_organization_createdBy_filter);
		String param_organization_lastUpdatedDate_filter_start = "";
		if(request.getParameter("organization_lastUpdatedDate_filter_start")!=null){
			param_organization_lastUpdatedDate_filter_start = request.getParameter("organization_lastUpdatedDate_filter_start");
			if(param_organization_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_organization_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_organization_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_organization_lastUpdatedDate_filter_start));
					String param_organization_lastUpdatedDate_filter_start_cal_val = param_organization_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_organization_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_organization_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					organization_filterSb.append("  AND organization.lastUpdatedDate >= '"+param_organization_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("organization_lastUpdatedDate_filter_start", param_organization_lastUpdatedDate_filter_start);

		String param_organization_lastUpdatedDate_filter_end = "";
		if(request.getParameter("organization_lastUpdatedDate_filter_end")!=null){
			param_organization_lastUpdatedDate_filter_end = request.getParameter("organization_lastUpdatedDate_filter_end");
			if(param_organization_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_organization_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_organization_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_organization_lastUpdatedDate_filter_end));
					String param_organization_lastUpdatedDate_filter_end_cal_val = param_organization_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_organization_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_organization_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					organization_filterSb.append("  AND organization.lastUpdatedDate  <= '"+param_organization_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("organization_lastUpdatedDate_filter_end", param_organization_lastUpdatedDate_filter_end);

		String param_organization_lastUpdatedBy_filter = "";
		if(request.getParameter("organization_lastUpdatedBy_filter")!=null){
			param_organization_lastUpdatedBy_filter = request.getParameter("organization_lastUpdatedBy_filter");
			if(param_organization_lastUpdatedBy_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.lastUpdatedBy like '%"+param_organization_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_lastUpdatedBy_filter", param_organization_lastUpdatedBy_filter);
		String param_organization_filterCode_filter = "";
		if(request.getParameter("organization_filterCode_filter")!=null){
			param_organization_filterCode_filter = request.getParameter("organization_filterCode_filter");
			if(param_organization_filterCode_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.filterCode like '%"+param_organization_filterCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_filterCode_filter", param_organization_filterCode_filter);
		String param_organization_parent_filter = "";
		if(request.getParameter("organization_parent_filter")!=null){
			param_organization_parent_filter = request.getParameter("organization_parent_filter");
			if(param_organization_parent_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.parent = '"+param_organization_parent_filter+"' ");
			}
		}		
		request.getSession().setAttribute("organization_parent_filter", param_organization_parent_filter);
		
		if(organization_fieldOrder!=null && organization_orderType != null )organization_filterSb.append(" ORDER BY "+organization_fieldOrder+" "+organization_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		OrganizationService organizationService = OrganizationService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList organizationList = organizationService.getPartialList(organization_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, organizationList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, organizationList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("organizationList", organizationList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("organization");
		
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
    		OrganizationService organizationService = OrganizationService.getInstance();
    		List organizationList = organizationService.getList(parentFilter , null);
    		request.setAttribute("organizationList", organizationList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if (organization == null){
	    		organization = OrganizationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("organization", organization);
	    	}
    		if(organization == null){
    			response.sendRedirect("organization.do");
    			return null;
    		}
    		

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if (organization == null){
	    		organization = OrganizationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("organization", organization);
	    	}
    		if(organization == null){
    			response.sendRedirect("organization.do");
    			return null;
    		}
    		

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);
    		
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null) organization = new Organization();
    		
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);

    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
			request.setAttribute("user", user);
			com.app.docmgr.service.LookupService userLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList_user = userLevelService_user.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList_user", userLevelList_user);
/*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList_user", statusList_user);
*/			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
    		request.getSession().setAttribute("organization", organization);
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=create&url=create_confirm");
    			return null;
    		}

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);

    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
			request.setAttribute("user", user);

			com.app.docmgr.service.LookupService userLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList_user = userLevelService_user.getList(null, null);
			request.setAttribute("userLevelList_user", userLevelList_user);
 /*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList(null, null);
			request.setAttribute("statusList_user", statusList_user);
 */ 			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
    		loadParameter(request, form, organization, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
			organization.setLastUpdatedDate(new Date());
			organization.setCreatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
			organization.setCreatedBy(_doneBy);
    		OrganizationService.getInstance().add(organization);
    		request.getSession().removeAttribute("organization");
    		response.sendRedirect("organization.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if (organization == null){
	    		organization = OrganizationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("organization", organization);
	    	}
    		if(organization == null){
    			response.sendRedirect("organization.do");
    			return null;
    		}
    		
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);

    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
			request.setAttribute("user", user);

			com.app.docmgr.service.LookupService userLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList_user = userLevelService_user.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList_user", userLevelList_user);
/*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList_user", statusList_user);
*/			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=edit");
    			return null;
    		}

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);

    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
			request.setAttribute("user", user);

			com.app.docmgr.service.LookupService userLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList_user = userLevelService_user.getList(null, null);
			request.setAttribute("userLevelList_user", userLevelList_user);
/*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList(null, null);
			request.setAttribute("statusList_user", statusList_user);
*/			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
    		loadParameter(request, form, organization, errors);
    		
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=edit_confirm");
    		}
    		
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		request.getSession().removeAttribute("organization");
    		response.sendRedirect("organization.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if (organization == null){
	    		organization = OrganizationService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("organization", organization);
	    	}
    		if(organization == null){
    			response.sendRedirect("organization.do?action=detail");
    			return null;
    		}

			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);
    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("organization.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=delete_confirm");
    		}
    		OrganizationService.getInstance().delete(organization);
    		request.getSession().removeAttribute("organization");
    		response.sendRedirect("organization.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("organization.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Organization organization, ActionErrors errors){
    	try{    		
			String code = request.getParameter("code");
			organization.setCode(code);
			if(code==null || code.trim().length() == 0 ){
				errors.add("organization.code", new ActionError("error.organization.code"));
			}
			String mnemonic = request.getParameter("mnemonic");
			organization.setMnemonic(mnemonic);
			if(mnemonic==null || mnemonic.trim().length() == 0 ){
				errors.add("organization.mnemonic", new ActionError("error.organization.mnemonic"));
			}
			String name = request.getParameter("name");
			organization.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("organization.name", new ActionError("error.organization.name"));
			}
			String address = request.getParameter("address");
			organization.setAddress(address);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				organization.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					organization.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("organization.createdDate", new ActionError("error.organization.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			organization.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("organization.createdBy", new ActionError("error.organization.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				organization.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					organization.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			organization.setLastUpdatedBy(lastUpdatedBy);
*/ 			String filterCode = request.getParameter("filterCode");
			organization.setFilterCode(filterCode);

			com.app.docmgr.model.Organization  parentObj =null;
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			try{
				String parentStr = request.getParameter("parent");
				
				if(parentStr == null || parentStr.trim().length() == 0 ){
					organization.setParent(null);
				}else{			
					parentObj = parentService.get(new Long(parentStr));
					organization.setParent(parentObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
	public ActionForward doAddUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){	
    	ActionForward forward = null;
		String redirect = request.getParameter("redirect");
		if(redirect == null ) redirect = "list";
    	try{
    		ActionErrors errors = new ActionErrors();
    		ActionErrors errorsUser = new ActionErrors();
    		Organization organization = (Organization) request.getSession().getAttribute("organization");

			//SET
			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);
			
			//Object
    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();

    		if(organization == null){
    			response.sendRedirect("organization.do?action=create&url=create_confirm");
    			return null;
    		}

			loadParameterUser(request, user, errorsUser);
			loadParameter(request, form, organization, errors);

    		if(errorsUser.isEmpty()){
					organization.setLastUpdatedBy(_doneBy);
				if(user.getId()==null) {
					user.setStatus(StatusService.getInstance().getByTypeandCode("User","new"));
					user.setCreatedDate(new Date());
					organization.setCreatedBy(_doneBy);			
				}
						
				userSet.add(user);
				organization.setMembers(userSet);
	    		request.getSession().setAttribute("user", null);
    			response.sendRedirect("organization.do?action="+redirect);
    			return null;
				
    		}else{	
    			saveErrors(request, errorsUser);
	    		request.getSession().setAttribute("user", user);
	    		if("create".equalsIgnoreCase(redirect)){
    				forward = doCreate(mapping, form, request, response);
				}else if("edit".equalsIgnoreCase(redirect)){
					forward = doEdit(mapping, form, request, response);
				}else{
					forward = doList(mapping, form, request, response);
				}
    		}


    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    	
    	return forward;
	}

	public void doEditUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){	
		String redirect = request.getParameter("redirect");
		if(redirect == null ) redirect = "list";
		try{
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null ){
    			response.sendRedirect("organization.do?action="+redirect);
    		}

			Set userSet = organization.getMembers();			
			if(userSet == null)userSet = new HashSet();
			
			Iterator itr = userSet.iterator();
			try{
				int seq = Integer.parseInt(request.getParameter("sequence"));
				int i = 0;
				while(itr.hasNext()){
					i++;
					User user = (User)itr.next();
					if(i == seq){
						request.getSession().setAttribute("user", user);						
					}					
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}	
	
			response.sendRedirect("organization.do?action="+redirect);

		}catch(Exception ex){
			ex.printStackTrace();
		}			
	}

	public void doDeleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){	
		String redirect = request.getParameter("redirect");
		if(redirect == null ) redirect = "list";

		try{
			List userList = new LinkedList();
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null ){
    			response.sendRedirect("organization.do?action="+redirect);
    		}
    		
			Set userSet = organization.getMembers();			
			if(userSet == null)userSet = new HashSet();
			
			Iterator itr = userSet.iterator();
			try{
				int seq = Integer.parseInt(request.getParameter("sequence"));
				int i = 0;
				while(itr.hasNext()){
					i++;
					User user = (User)itr.next();
					if(i == seq){
						userList.add(user);
					}					
				}
				
				Iterator itrDelete = userList.iterator();
				while(itrDelete.hasNext()){
					User user = (User)itrDelete.next();
					userSet.remove(user);
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}	
	
			response.sendRedirect("organization.do?action="+redirect);

		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	public void loadParameterUser(HttpServletRequest request, User user, ActionErrors errorsUser){
			String loginName = request.getParameter("loginName_item");
			user.setLoginName(loginName);		

			if(loginName==null || loginName.trim().length() == 0 ){
				errorsUser.add("user.loginName", new ActionError("error.user.loginName"));
			}
			String loginPassword = request.getParameter("loginPassword_item");
			user.setLoginPassword(loginPassword);		

			if(loginPassword==null || loginPassword.trim().length() == 0 ){
				errorsUser.add("user.loginPassword", new ActionError("error.user.loginPassword"));
			}
			String pinCode = request.getParameter("pinCode_item");
			user.setPinCode(pinCode);		

			String mobileNumber = request.getParameter("mobileNumber_item");
			user.setMobileNumber(mobileNumber);		

			String language = request.getParameter("language_item");
			user.setLanguage(language);		

			String title = request.getParameter("title_item");
			user.setTitle(title);		

			String name = request.getParameter("name_item");
			user.setName(name);		

			if(name==null || name.trim().length() == 0 ){
				errorsUser.add("user.name", new ActionError("error.user.name"));
			}
			String email = request.getParameter("email_item");
			user.setEmail(email);		

			String fullName = request.getParameter("fullName_item");
			user.setFullName(fullName);		

			String homePhoneNumber = request.getParameter("homePhoneNumber_item");
			user.setHomePhoneNumber(homePhoneNumber);		

			String mobilePhoneNumber = request.getParameter("mobilePhoneNumber_item");
			user.setMobilePhoneNumber(mobilePhoneNumber);		

			String employeeNumber = request.getParameter("employeeNumber_item");
			user.setEmployeeNumber(employeeNumber);		

/* 			String createdDate = request.getParameter("createdDate_item");
			try{
				java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
				createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
				user.setCreatedDate(createdDateCalendar.getTime());
			}catch(Exception ex){
			}

			if(createdDate==null || createdDate.trim().length() == 0 ){
				errorsUser.add("user.createdDate", new ActionError("error.user.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy_item");
			user.setCreatedBy(createdBy);		

			if(createdBy==null || createdBy.trim().length() == 0 ){
				errorsUser.add("user.createdBy", new ActionError("error.user.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate_item");
			try{
				java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
				lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
				user.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
			}catch(Exception ex){
			}

*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy_item");
			user.setLastUpdatedBy(lastUpdatedBy);		

*/ 			String firstLogin = request.getParameter("firstLogin_item");
			user.setFirstLogin(firstLogin);		

			String lastPasswordUpdate = request.getParameter("lastPasswordUpdate_item");
			try{
				java.util.Calendar lastPasswordUpdateCalendar = java.util.Calendar.getInstance();
				lastPasswordUpdateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastPasswordUpdate));			
				user.setLastPasswordUpdate(lastPasswordUpdateCalendar.getTime());
			}catch(Exception ex){
			}

			String lastPinCodeUpdate = request.getParameter("lastPinCodeUpdate_item");
			try{
				java.util.Calendar lastPinCodeUpdateCalendar = java.util.Calendar.getInstance();
				lastPinCodeUpdateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastPinCodeUpdate));			
				user.setLastPinCodeUpdate(lastPinCodeUpdateCalendar.getTime());
			}catch(Exception ex){
			}

			String lastPassword = request.getParameter("lastPassword_item");
			user.setLastPassword(lastPassword);		

			String lastPinCode = request.getParameter("lastPinCode_item");
			user.setLastPinCode(lastPinCode);		

			String loginFailed = request.getParameter("loginFailed_item");
			try{
				user.setLoginFailed(new java.lang.Integer(loginFailed));
			}catch(Exception ex){}

			String maxRelease = request.getParameter("maxRelease_item");
			try{
				user.setMaxRelease(new java.lang.Integer(maxRelease));
			}catch(Exception ex){}

			String lastReleasedDate = request.getParameter("lastReleasedDate_item");
			try{
				java.util.Calendar lastReleasedDateCalendar = java.util.Calendar.getInstance();
				lastReleasedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastReleasedDate));			
				user.setLastReleasedDate(lastReleasedDateCalendar.getTime());
			}catch(Exception ex){
			}

			String lastActive = request.getParameter("lastActive_item");
			try{
				java.util.Calendar lastActiveCalendar = java.util.Calendar.getInstance();
				lastActiveCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastActive));			
				user.setLastActive(lastActiveCalendar.getTime());
			}catch(Exception ex){
			}

			String sessionCode = request.getParameter("sessionCode_item");
			user.setSessionCode(sessionCode);		

			String IPassport = request.getParameter("IPassport_item");
			user.setIPassport(IPassport);		


			com.app.docmgr.model.Lookup  userLevelObj =null;
			try{
				com.app.docmgr.service.LookupService userLevelService = com.app.docmgr.service.LookupService.getInstance();
				String userLevelStr = request.getParameter("userLevel");
				if(userLevelStr == null || userLevelStr.trim().length() == 0 ){
					user.setUserLevel(null);
				}else{			
					userLevelObj = userLevelService.get(new Long(userLevelStr));
					user.setUserLevel(userLevelObj);
				}
			}catch(Exception ex){}				
			if(userLevelObj==null){
				errorsUser.add("user.userLevel", new ActionError("error.user.userLevel"));
			}
 /* 			com.app.docmgr.model.Status  statusObj =null;
			try{
				com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
				String statusStr = request.getParameter("status");
				if(statusStr == null || statusStr.trim().length() == 0 ){
					user.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					user.setStatus(statusObj);
				}
			}catch(Exception ex){}				
			if(statusObj==null){
				errorsUser.add("user.status", new ActionError("error.user.status"));
			}
 */ 			com.app.docmgr.model.Organization  organizationObj =null;
			try{
				com.app.docmgr.service.OrganizationService organizationService = com.app.docmgr.service.OrganizationService.getInstance();
				String organizationStr = request.getParameter("organization");
				if(organizationStr == null || organizationStr.trim().length() == 0 ){
					user.setOrganization(null);
				}else{			
					organizationObj = organizationService.get(new Long(organizationStr));
					user.setOrganization(organizationObj);
				}
			}catch(Exception ex){}				
	}
	
    
}

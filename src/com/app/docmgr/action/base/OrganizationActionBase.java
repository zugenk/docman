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


public class OrganizationActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.OrganizationActionBase");	
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
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
			com.app.docmgr.service.LookupService organizationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List organizationTypeList = organizationTypeService.getList("  and lookup.type='organizationType'  ", null);
			request.setAttribute("organizationTypeList", organizationTypeList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Organization'  ", null);
			request.setAttribute("statusList", statusList);
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
		String param_organization_mailingList_filter = "";
		if(request.getParameter("organization_mailingList_filter")!=null){
			param_organization_mailingList_filter = request.getParameter("organization_mailingList_filter");
			if(param_organization_mailingList_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.mailingList like '%"+param_organization_mailingList_filter+"%' ");
			}
		}
		request.getSession().setAttribute("organization_mailingList_filter", param_organization_mailingList_filter);
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
		String param_organization_securityLevel_filter = "";
		if(request.getParameter("organization_securityLevel_filter")!=null){
			param_organization_securityLevel_filter = request.getParameter("organization_securityLevel_filter");
			if(param_organization_securityLevel_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.securityLevel = '"+param_organization_securityLevel_filter+"' ");
			}
		}		
		request.getSession().setAttribute("organization_securityLevel_filter", param_organization_securityLevel_filter);
		String param_organization_parent_filter = "";
		if(request.getParameter("organization_parent_filter")!=null){
			param_organization_parent_filter = request.getParameter("organization_parent_filter");
			if(param_organization_parent_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.parent = '"+param_organization_parent_filter+"' ");
			}
		}		
		request.getSession().setAttribute("organization_parent_filter", param_organization_parent_filter);
		String param_organization_organizationType_filter = "";
		if(request.getParameter("organization_organizationType_filter")!=null){
			param_organization_organizationType_filter = request.getParameter("organization_organizationType_filter");
			if(param_organization_organizationType_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.organizationType = '"+param_organization_organizationType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("organization_organizationType_filter", param_organization_organizationType_filter);
		String param_organization_status_filter = "";
		if(request.getParameter("organization_status_filter")!=null){
			param_organization_status_filter = request.getParameter("organization_status_filter");
			if(param_organization_status_filter.length() > 0 ){				
				organization_filterSb.append("  AND organization.status = '"+param_organization_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("organization_status_filter", param_organization_status_filter);
		
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
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
			com.app.docmgr.service.LookupService organizationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List organizationTypeList = organizationTypeService.getList("  and lookup.type='organizationType'  ", null);
			request.setAttribute("organizationTypeList", organizationTypeList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Organization'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);

    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
			request.setAttribute("user", user);
			com.app.docmgr.service.LookupService userLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList_user = userLevelService_user.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList_user", userLevelList_user);
			com.app.docmgr.service.LookupService positionService_user = com.app.docmgr.service.LookupService.getInstance();
			List positionList_user = positionService_user.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList_user", positionList_user);
/*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList_user", statusList_user);
*/			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
			com.app.docmgr.service.LookupService securityLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList_user = securityLevelService_user.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList_user", securityLevelList_user);
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
			com.app.docmgr.service.LookupService positionService_user = com.app.docmgr.service.LookupService.getInstance();
			List positionList_user = positionService_user.getList(null, null);
			request.setAttribute("positionList_user", positionList_user);
 /*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList(null, null);
			request.setAttribute("statusList_user", statusList_user);
 */ 			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
			com.app.docmgr.service.LookupService securityLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList_user = securityLevelService_user.getList(null, null);
			request.setAttribute("securityLevelList_user", securityLevelList_user);
    		loadParameter(request, form, organization, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
			com.app.docmgr.service.LookupService organizationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List organizationTypeList = organizationTypeService.getList("  and lookup.type='organizationType'  ", null);
			request.setAttribute("organizationTypeList", organizationTypeList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Organization'  ", null);
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
    		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","new"));
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
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
			com.app.docmgr.service.LookupService organizationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List organizationTypeList = organizationTypeService.getList("  and lookup.type='organizationType'  ", null);
			request.setAttribute("organizationTypeList", organizationTypeList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Organization'  ", null);
			request.setAttribute("statusList", statusList);
*/ 
			Set userSet = organization.getMembers();
			if(userSet == null)userSet = new HashSet();
			request.setAttribute("userSet", userSet);

    		User user = (User) request.getSession().getAttribute("user");
    		if(user == null) user = new User();
			request.setAttribute("user", user);

			com.app.docmgr.service.LookupService userLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List userLevelList_user = userLevelService_user.getList("  and lookup.type='userLevel'  ", null);
			request.setAttribute("userLevelList_user", userLevelList_user);
			com.app.docmgr.service.LookupService positionService_user = com.app.docmgr.service.LookupService.getInstance();
			List positionList_user = positionService_user.getList("  and lookup.type='position'  ", null);
			request.setAttribute("positionList_user", positionList_user);
/*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList("  and status.type='User'  ", null);
			request.setAttribute("statusList_user", statusList_user);
*/			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
			com.app.docmgr.service.LookupService securityLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList_user = securityLevelService_user.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList_user", securityLevelList_user);
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
			com.app.docmgr.service.LookupService positionService_user = com.app.docmgr.service.LookupService.getInstance();
			List positionList_user = positionService_user.getList(null, null);
			request.setAttribute("positionList_user", positionList_user);
/*			com.app.docmgr.service.StatusService statusService_user = com.app.docmgr.service.StatusService.getInstance();
			List statusList_user = statusService_user.getList(null, null);
			request.setAttribute("statusList_user", statusList_user);
*/			com.app.docmgr.service.OrganizationService organizationService_user = com.app.docmgr.service.OrganizationService.getInstance();
			List organizationList_user = organizationService_user.getList(null, null);
			request.setAttribute("organizationList_user", organizationList_user);
			com.app.docmgr.service.LookupService securityLevelService_user = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList_user = securityLevelService_user.getList(null, null);
			request.setAttribute("securityLevelList_user", securityLevelList_user);
    		loadParameter(request, form, organization, errors);
    		
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			List securityLevelList = securityLevelService.getList("  and lookup.type='securityLevel'  ", null);
			request.setAttribute("securityLevelList", securityLevelList);
			com.app.docmgr.service.OrganizationService parentService = com.app.docmgr.service.OrganizationService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
			com.app.docmgr.service.LookupService organizationTypeService = com.app.docmgr.service.LookupService.getInstance();
			List organizationTypeList = organizationTypeService.getList("  and lookup.type='organizationType'  ", null);
			request.setAttribute("organizationTypeList", organizationTypeList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Organization'  ", null);
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
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","deleted"));
    		OrganizationService.getInstance().update(organization);
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

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("submit_confirm");
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

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=submit_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","submitted"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("approve_confirm");
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

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=approve_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","approved"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("reject_confirm");
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

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=reject_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","rejected"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("pending_confirm");
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

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=pending_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","pending"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("process_confirm");
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

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=process_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","processed"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("activate_confirm");
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

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=activate_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","activated"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("close_confirm");
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

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=close_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","closed"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("archive_confirm");
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

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=archive_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","archived"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("remove_confirm");
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

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=remove_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","removed"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("block_confirm");
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

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=block_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","blocked"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
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
    		forward = mapping.findForward("cancel_confirm");
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

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Organization organization = (Organization) request.getSession().getAttribute("organization");
    		if(organization == null){
    			response.sendRedirect("organization.do?action=cancel_confirm");
    		}
    		organization.setStatus(StatusService.getInstance().getByTypeandCode("Organization","cancelled"));
			organization.setLastUpdatedDate(new Date());
			organization.setLastUpdatedBy(_doneBy);
    		OrganizationService.getInstance().update(organization);
    		response.sendRedirect("organization.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("organization.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Organization organization, ActionErrors errors){
    	try{    		
			String code = request.getParameter("code");
			organization.setCode(code);
			String mnemonic = request.getParameter("mnemonic");
			organization.setMnemonic(mnemonic);
			String name = request.getParameter("name");
			organization.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("organization.name", new ActionError("error.organization.name"));
			}
			String address = request.getParameter("address");
			organization.setAddress(address);
			String mailingList = request.getParameter("mailingList");
			organization.setMailingList(mailingList);
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

			com.app.docmgr.model.Lookup  securityLevelObj =null;
			com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String securityLevelStr = request.getParameter("securityLevel");
				
				if(securityLevelStr == null || securityLevelStr.trim().length() == 0 ){
					organization.setSecurityLevel(null);
				}else{			
					securityLevelObj = securityLevelService.get(new Long(securityLevelStr));
					organization.setSecurityLevel(securityLevelObj);
				}
			}catch(Exception ex){}	
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
			com.app.docmgr.model.Lookup  organizationTypeObj =null;
			com.app.docmgr.service.LookupService organizationTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String organizationTypeStr = request.getParameter("organizationType");
				
				if(organizationTypeStr == null || organizationTypeStr.trim().length() == 0 ){
					organization.setOrganizationType(null);
				}else{			
					organizationTypeObj = organizationTypeService.get(new Long(organizationTypeStr));
					organization.setOrganizationType(organizationTypeObj);
				}
			}catch(Exception ex){}	
			if(organizationTypeObj==null){
				errors.add("organization.organizationType", new ActionError("error.organization.organizationType"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					organization.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					organization.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("organization.status", new ActionError("error.organization.status"));
			}
*/ 
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

			String picture = request.getParameter("picture_item");
			user.setPicture(picture);		

			String language = request.getParameter("language_item");
			user.setLanguage(language);		

			String title = request.getParameter("title_item");
			user.setTitle(title);		

			String name = request.getParameter("name_item");
			user.setName(name);		

			if(name==null || name.trim().length() == 0 ){
				errorsUser.add("user.name", new ActionError("error.user.name"));
			}
			String alias = request.getParameter("alias_item");
			user.setAlias(alias);		

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
			com.app.docmgr.model.Lookup  positionObj =null;
			try{
				com.app.docmgr.service.LookupService positionService = com.app.docmgr.service.LookupService.getInstance();
				String positionStr = request.getParameter("position");
				if(positionStr == null || positionStr.trim().length() == 0 ){
					user.setPosition(null);
				}else{			
					positionObj = positionService.get(new Long(positionStr));
					user.setPosition(positionObj);
				}
			}catch(Exception ex){}				
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
			com.app.docmgr.model.Lookup  securityLevelObj =null;
			try{
				com.app.docmgr.service.LookupService securityLevelService = com.app.docmgr.service.LookupService.getInstance();
				String securityLevelStr = request.getParameter("securityLevel");
				if(securityLevelStr == null || securityLevelStr.trim().length() == 0 ){
					user.setSecurityLevel(null);
				}else{			
					securityLevelObj = securityLevelService.get(new Long(securityLevelStr));
					user.setSecurityLevel(securityLevelObj);
				}
			}catch(Exception ex){}				
	}
	
    
}

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


public class ForumActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.ForumActionBase");	
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
    	
    	request.getSession().removeAttribute("forum");
		
		String forum_filter = request.getParameter("forum_filter");
		//this for orderBy field ASC/DESC
		String forum_fieldOrder = request.getParameter("forum_fieldOrder");
		String forum_orderType = request.getParameter("forum_orderType");
		
		if(forum_fieldOrder == null || forum_fieldOrder.length() == 0) forum_fieldOrder = null;
		if(forum_orderType == null || forum_orderType.length() == 0) forum_orderType = null;
		request.getSession().setAttribute("forum_fieldOrder", forum_fieldOrder==null?"":forum_fieldOrder);	 
		request.getSession().setAttribute("forum_orderType", forum_orderType==null?"":forum_orderType);
		
		try{ 
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Forum'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.LookupService forumTypeService = com.app.docmgr.service.LookupService.getInstance();
			List forumTypeList = forumTypeService.getList("  and lookup.type='forumType'  ", null);
			request.setAttribute("forumTypeList", forumTypeList);
			com.app.docmgr.service.ForumService parentService = com.app.docmgr.service.ForumService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);
		}catch(Exception ex){
		
		}
		StringBuffer forum_filterSb = new StringBuffer();
		String param_forum_code_filter = "";
		if(request.getParameter("forum_code_filter")!=null){
			param_forum_code_filter = request.getParameter("forum_code_filter");
			if(param_forum_code_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.code like '%"+param_forum_code_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_code_filter", param_forum_code_filter);
		String param_forum_icon_filter = "";
		if(request.getParameter("forum_icon_filter")!=null){
			param_forum_icon_filter = request.getParameter("forum_icon_filter");
			if(param_forum_icon_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.icon like '%"+param_forum_icon_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_icon_filter", param_forum_icon_filter);
		String param_forum_name_filter = "";
		if(request.getParameter("forum_name_filter")!=null){
			param_forum_name_filter = request.getParameter("forum_name_filter");
			if(param_forum_name_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.name like '%"+param_forum_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_name_filter", param_forum_name_filter);
		String param_forum_description_filter = "";
		if(request.getParameter("forum_description_filter")!=null){
			param_forum_description_filter = request.getParameter("forum_description_filter");
			if(param_forum_description_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.description like '%"+param_forum_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_description_filter", param_forum_description_filter);
		String param_forum_createdDate_filter_start = "";
		if(request.getParameter("forum_createdDate_filter_start")!=null){
			param_forum_createdDate_filter_start = request.getParameter("forum_createdDate_filter_start");
			if(param_forum_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_forum_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_forum_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_forum_createdDate_filter_start));
					String param_forum_createdDate_filter_start_cal_val = param_forum_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_forum_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_forum_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					forum_filterSb.append("  AND forum.createdDate >= '"+param_forum_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("forum_createdDate_filter_start", param_forum_createdDate_filter_start);

		String param_forum_createdDate_filter_end = "";
		if(request.getParameter("forum_createdDate_filter_end")!=null){
			param_forum_createdDate_filter_end = request.getParameter("forum_createdDate_filter_end");
			if(param_forum_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_forum_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_forum_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_forum_createdDate_filter_end));
					String param_forum_createdDate_filter_end_cal_val = param_forum_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_forum_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_forum_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					forum_filterSb.append("  AND forum.createdDate  <= '"+param_forum_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("forum_createdDate_filter_end", param_forum_createdDate_filter_end);

		String param_forum_createdBy_filter = "";
		if(request.getParameter("forum_createdBy_filter")!=null){
			param_forum_createdBy_filter = request.getParameter("forum_createdBy_filter");
			if(param_forum_createdBy_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.createdBy like '%"+param_forum_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_createdBy_filter", param_forum_createdBy_filter);
		String param_forum_lastUpdatedDate_filter_start = "";
		if(request.getParameter("forum_lastUpdatedDate_filter_start")!=null){
			param_forum_lastUpdatedDate_filter_start = request.getParameter("forum_lastUpdatedDate_filter_start");
			if(param_forum_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_forum_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_forum_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_forum_lastUpdatedDate_filter_start));
					String param_forum_lastUpdatedDate_filter_start_cal_val = param_forum_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_forum_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_forum_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					forum_filterSb.append("  AND forum.lastUpdatedDate >= '"+param_forum_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("forum_lastUpdatedDate_filter_start", param_forum_lastUpdatedDate_filter_start);

		String param_forum_lastUpdatedDate_filter_end = "";
		if(request.getParameter("forum_lastUpdatedDate_filter_end")!=null){
			param_forum_lastUpdatedDate_filter_end = request.getParameter("forum_lastUpdatedDate_filter_end");
			if(param_forum_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_forum_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_forum_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_forum_lastUpdatedDate_filter_end));
					String param_forum_lastUpdatedDate_filter_end_cal_val = param_forum_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_forum_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_forum_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					forum_filterSb.append("  AND forum.lastUpdatedDate  <= '"+param_forum_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("forum_lastUpdatedDate_filter_end", param_forum_lastUpdatedDate_filter_end);

		String param_forum_lastUpdatedBy_filter = "";
		if(request.getParameter("forum_lastUpdatedBy_filter")!=null){
			param_forum_lastUpdatedBy_filter = request.getParameter("forum_lastUpdatedBy_filter");
			if(param_forum_lastUpdatedBy_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.lastUpdatedBy like '%"+param_forum_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_lastUpdatedBy_filter", param_forum_lastUpdatedBy_filter);
		String param_forum_filterCode_filter = "";
		if(request.getParameter("forum_filterCode_filter")!=null){
			param_forum_filterCode_filter = request.getParameter("forum_filterCode_filter");
			if(param_forum_filterCode_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.filterCode like '%"+param_forum_filterCode_filter+"%' ");
			}
		}
		request.getSession().setAttribute("forum_filterCode_filter", param_forum_filterCode_filter);
		String param_forum_status_filter = "";
		if(request.getParameter("forum_status_filter")!=null){
			param_forum_status_filter = request.getParameter("forum_status_filter");
			if(param_forum_status_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.status = '"+param_forum_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("forum_status_filter", param_forum_status_filter);
		String param_forum_forumType_filter = "";
		if(request.getParameter("forum_forumType_filter")!=null){
			param_forum_forumType_filter = request.getParameter("forum_forumType_filter");
			if(param_forum_forumType_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.forumType = '"+param_forum_forumType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("forum_forumType_filter", param_forum_forumType_filter);
		String param_forum_parent_filter = "";
		if(request.getParameter("forum_parent_filter")!=null){
			param_forum_parent_filter = request.getParameter("forum_parent_filter");
			if(param_forum_parent_filter.length() > 0 ){				
				forum_filterSb.append("  AND forum.parent = '"+param_forum_parent_filter+"' ");
			}
		}		
		request.getSession().setAttribute("forum_parent_filter", param_forum_parent_filter);
		
		if(forum_fieldOrder!=null && forum_orderType != null )forum_filterSb.append(" ORDER BY "+forum_fieldOrder+" "+forum_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		ForumService forumService = ForumService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList forumList = forumService.getPartialList(forum_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, forumList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, forumList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("forumList", forumList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("forum");
		
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
    		ForumService forumService = ForumService.getInstance();
    		List forumList = forumService.getList(parentFilter , null);
    		request.setAttribute("forumList", forumList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do");
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
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do");
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
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null) forum = new Forum();
    		
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Forum'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.LookupService forumTypeService = com.app.docmgr.service.LookupService.getInstance();
			List forumTypeList = forumTypeService.getList("  and lookup.type='forumType'  ", null);
			request.setAttribute("forumTypeList", forumTypeList);
			com.app.docmgr.service.ForumService parentService = com.app.docmgr.service.ForumService.getInstance();
			List parentList = parentService.getList(null, null);
			request.setAttribute("parentList", parentList);

    		request.getSession().setAttribute("forum", forum);
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
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, forum, errors);
    		//set Many To One Property
    		
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Forum'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.LookupService forumTypeService = com.app.docmgr.service.LookupService.getInstance();
			List forumTypeList = forumTypeService.getList("  and lookup.type='forumType'  ", null);
			request.setAttribute("forumTypeList", forumTypeList);
			com.app.docmgr.service.ForumService parentService = com.app.docmgr.service.ForumService.getInstance();
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
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","new"));
			forum.setLastUpdatedDate(new Date());
			forum.setCreatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
			forum.setCreatedBy(_doneBy);
    		ForumService.getInstance().add(forum);
    		request.getSession().removeAttribute("forum");
    		response.sendRedirect("forum.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do");
    			return null;
    		}
    		
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Forum'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.LookupService forumTypeService = com.app.docmgr.service.LookupService.getInstance();
			List forumTypeList = forumTypeService.getList("  and lookup.type='forumType'  ", null);
			request.setAttribute("forumTypeList", forumTypeList);
			com.app.docmgr.service.ForumService parentService = com.app.docmgr.service.ForumService.getInstance();
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
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, forum, errors);
    		
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Forum'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.LookupService forumTypeService = com.app.docmgr.service.LookupService.getInstance();
			List forumTypeList = forumTypeService.getList("  and lookup.type='forumType'  ", null);
			request.setAttribute("forumTypeList", forumTypeList);
			com.app.docmgr.service.ForumService parentService = com.app.docmgr.service.ForumService.getInstance();
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
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=edit_confirm");
    		}
    		
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		request.getSession().removeAttribute("forum");
    		response.sendRedirect("forum.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=delete_confirm");
    		}
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","deleted"));
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("forum.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=submit_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","submitted"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=approve_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","approved"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=reject_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","rejected"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=pending_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","pending"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=process_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","processed"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doActivateConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("activate_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doActivateOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=activate_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","activated"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=activate_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=close_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","closed"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=archive_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","archived"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=remove_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","removed"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doBlockConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("block_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doBlockOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=block_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","blocked"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=block_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if (forum == null){
	    		forum = ForumService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("forum", forum);
	    	}
    		if(forum == null){
    			response.sendRedirect("forum.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("forum.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Forum forum = (Forum) request.getSession().getAttribute("forum");
    		if(forum == null){
    			response.sendRedirect("forum.do?action=cancel_confirm");
    		}
    		forum.setStatus(StatusService.getInstance().getByTypeandCode("Forum","cancelled"));
			forum.setLastUpdatedDate(new Date());
			forum.setLastUpdatedBy(_doneBy);
    		ForumService.getInstance().update(forum);
    		response.sendRedirect("forum.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("forum.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Forum forum, ActionErrors errors){
    	try{    		
			String code = request.getParameter("code");
			forum.setCode(code);
			String icon = request.getParameter("icon");
			forum.setIcon(icon);
			String name = request.getParameter("name");
			forum.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("forum.name", new ActionError("error.forum.name"));
			}
			String description = request.getParameter("description");
			forum.setDescription(description);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				forum.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					forum.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("forum.createdDate", new ActionError("error.forum.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			forum.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("forum.createdBy", new ActionError("error.forum.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				forum.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					forum.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			forum.setLastUpdatedBy(lastUpdatedBy);
*/ 			String filterCode = request.getParameter("filterCode");
			forum.setFilterCode(filterCode);

/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					forum.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					forum.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("forum.status", new ActionError("error.forum.status"));
			}
*/ 			com.app.docmgr.model.Lookup  forumTypeObj =null;
			com.app.docmgr.service.LookupService forumTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String forumTypeStr = request.getParameter("forumType");
				
				if(forumTypeStr == null || forumTypeStr.trim().length() == 0 ){
					forum.setForumType(null);
				}else{			
					forumTypeObj = forumTypeService.get(new Long(forumTypeStr));
					forum.setForumType(forumTypeObj);
				}
			}catch(Exception ex){}	
			if(forumTypeObj==null){
				errors.add("forum.forumType", new ActionError("error.forum.forumType"));
			}
			com.app.docmgr.model.Forum  parentObj =null;
			com.app.docmgr.service.ForumService parentService = com.app.docmgr.service.ForumService.getInstance();
			try{
				String parentStr = request.getParameter("parent");
				
				if(parentStr == null || parentStr.trim().length() == 0 ){
					forum.setParent(null);
				}else{			
					parentObj = parentService.get(new Long(parentStr));
					forum.setParent(parentObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

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
 * @createDate 05-11-2017 15:05:21
 */


public class AnnouncementActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.AnnouncementActionBase");	
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
	    	}else if("archive_confirm".equalsIgnoreCase(action)){
	    		forward = doArchiveConfirm(mapping, form, request, response);
	    	}else if("archive_ok".equalsIgnoreCase(action)){
	    		doArchiveOk(mapping, form, request, response);
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
    	
    	request.getSession().removeAttribute("announcement");
		
		String announcement_filter = request.getParameter("announcement_filter");
		//this for orderBy field ASC/DESC
		String announcement_fieldOrder = request.getParameter("announcement_fieldOrder");
		String announcement_orderType = request.getParameter("announcement_orderType");
		
		if(announcement_fieldOrder == null || announcement_fieldOrder.length() == 0) announcement_fieldOrder = null;
		if(announcement_orderType == null || announcement_orderType.length() == 0) announcement_orderType = null;
		request.getSession().setAttribute("announcement_fieldOrder", announcement_fieldOrder==null?"":announcement_fieldOrder);	 
		request.getSession().setAttribute("announcement_orderType", announcement_orderType==null?"":announcement_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService announcementTypeService = com.app.docmgr.service.LookupService.getInstance();
			List announcementTypeList = announcementTypeService.getList("  and lookup.type='announcementType'  ", null);
			request.setAttribute("announcementTypeList", announcementTypeList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Announcement'  ", null);
			request.setAttribute("statusList", statusList);
		}catch(Exception ex){
		
		}
		StringBuffer announcement_filterSb = new StringBuffer();
		String param_announcement_content_filter = "";
		if(request.getParameter("announcement_content_filter")!=null){
			param_announcement_content_filter = request.getParameter("announcement_content_filter");
			if(param_announcement_content_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.content like '%"+param_announcement_content_filter+"%' ");
			}
		}
		request.getSession().setAttribute("announcement_content_filter", param_announcement_content_filter);
		String param_announcement_targetUsers_filter = "";
		if(request.getParameter("announcement_targetUsers_filter")!=null){
			param_announcement_targetUsers_filter = request.getParameter("announcement_targetUsers_filter");
			if(param_announcement_targetUsers_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.targetUsers like '%"+param_announcement_targetUsers_filter+"%' ");
			}
		}
		request.getSession().setAttribute("announcement_targetUsers_filter", param_announcement_targetUsers_filter);
		String param_announcement_targetOrganizations_filter = "";
		if(request.getParameter("announcement_targetOrganizations_filter")!=null){
			param_announcement_targetOrganizations_filter = request.getParameter("announcement_targetOrganizations_filter");
			if(param_announcement_targetOrganizations_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.targetOrganizations like '%"+param_announcement_targetOrganizations_filter+"%' ");
			}
		}
		request.getSession().setAttribute("announcement_targetOrganizations_filter", param_announcement_targetOrganizations_filter);
		String param_announcement_createdDate_filter_start = "";
		if(request.getParameter("announcement_createdDate_filter_start")!=null){
			param_announcement_createdDate_filter_start = request.getParameter("announcement_createdDate_filter_start");
			if(param_announcement_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_announcement_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_announcement_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_announcement_createdDate_filter_start));
					String param_announcement_createdDate_filter_start_cal_val = param_announcement_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_announcement_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_announcement_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					announcement_filterSb.append("  AND announcement.createdDate >= '"+param_announcement_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("announcement_createdDate_filter_start", param_announcement_createdDate_filter_start);

		String param_announcement_createdDate_filter_end = "";
		if(request.getParameter("announcement_createdDate_filter_end")!=null){
			param_announcement_createdDate_filter_end = request.getParameter("announcement_createdDate_filter_end");
			if(param_announcement_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_announcement_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_announcement_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_announcement_createdDate_filter_end));
					String param_announcement_createdDate_filter_end_cal_val = param_announcement_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_announcement_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_announcement_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					announcement_filterSb.append("  AND announcement.createdDate  <= '"+param_announcement_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("announcement_createdDate_filter_end", param_announcement_createdDate_filter_end);

		String param_announcement_createdBy_filter = "";
		if(request.getParameter("announcement_createdBy_filter")!=null){
			param_announcement_createdBy_filter = request.getParameter("announcement_createdBy_filter");
			if(param_announcement_createdBy_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.createdBy like '%"+param_announcement_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("announcement_createdBy_filter", param_announcement_createdBy_filter);
		String param_announcement_lastUpdatedDate_filter_start = "";
		if(request.getParameter("announcement_lastUpdatedDate_filter_start")!=null){
			param_announcement_lastUpdatedDate_filter_start = request.getParameter("announcement_lastUpdatedDate_filter_start");
			if(param_announcement_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_announcement_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_announcement_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_announcement_lastUpdatedDate_filter_start));
					String param_announcement_lastUpdatedDate_filter_start_cal_val = param_announcement_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_announcement_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_announcement_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					announcement_filterSb.append("  AND announcement.lastUpdatedDate >= '"+param_announcement_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("announcement_lastUpdatedDate_filter_start", param_announcement_lastUpdatedDate_filter_start);

		String param_announcement_lastUpdatedDate_filter_end = "";
		if(request.getParameter("announcement_lastUpdatedDate_filter_end")!=null){
			param_announcement_lastUpdatedDate_filter_end = request.getParameter("announcement_lastUpdatedDate_filter_end");
			if(param_announcement_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_announcement_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_announcement_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_announcement_lastUpdatedDate_filter_end));
					String param_announcement_lastUpdatedDate_filter_end_cal_val = param_announcement_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_announcement_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_announcement_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					announcement_filterSb.append("  AND announcement.lastUpdatedDate  <= '"+param_announcement_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("announcement_lastUpdatedDate_filter_end", param_announcement_lastUpdatedDate_filter_end);

		String param_announcement_lastUpdatedBy_filter = "";
		if(request.getParameter("announcement_lastUpdatedBy_filter")!=null){
			param_announcement_lastUpdatedBy_filter = request.getParameter("announcement_lastUpdatedBy_filter");
			if(param_announcement_lastUpdatedBy_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.lastUpdatedBy like '%"+param_announcement_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("announcement_lastUpdatedBy_filter", param_announcement_lastUpdatedBy_filter);
		String param_announcement_announcementType_filter = "";
		if(request.getParameter("announcement_announcementType_filter")!=null){
			param_announcement_announcementType_filter = request.getParameter("announcement_announcementType_filter");
			if(param_announcement_announcementType_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.announcementType = '"+param_announcement_announcementType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("announcement_announcementType_filter", param_announcement_announcementType_filter);
		String param_announcement_status_filter = "";
		if(request.getParameter("announcement_status_filter")!=null){
			param_announcement_status_filter = request.getParameter("announcement_status_filter");
			if(param_announcement_status_filter.length() > 0 ){				
				announcement_filterSb.append("  AND announcement.status = '"+param_announcement_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("announcement_status_filter", param_announcement_status_filter);
		
		if(announcement_fieldOrder!=null && announcement_orderType != null )announcement_filterSb.append(" ORDER BY "+announcement_fieldOrder+" "+announcement_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		AnnouncementService announcementService = AnnouncementService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList announcementList = announcementService.getPartialList(announcement_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, announcementList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, announcementList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("announcementList", announcementList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("announcement");
		
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
    		AnnouncementService announcementService = AnnouncementService.getInstance();
    		List announcementList = announcementService.getList(parentFilter , null);
    		request.setAttribute("announcementList", announcementList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do");
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
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do");
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
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null) announcement = new Announcement();
    		
			com.app.docmgr.service.LookupService announcementTypeService = com.app.docmgr.service.LookupService.getInstance();
			List announcementTypeList = announcementTypeService.getList("  and lookup.type='announcementType'  ", null);
			request.setAttribute("announcementTypeList", announcementTypeList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Announcement'  ", null);
			request.setAttribute("statusList", statusList);
 */ 
    		request.getSession().setAttribute("announcement", announcement);
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
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, announcement, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService announcementTypeService = com.app.docmgr.service.LookupService.getInstance();
			List announcementTypeList = announcementTypeService.getList("  and lookup.type='announcementType'  ", null);
			request.setAttribute("announcementTypeList", announcementTypeList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Announcement'  ", null);
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
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","new"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setCreatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
			announcement.setCreatedBy(_doneBy);
    		AnnouncementService.getInstance().add(announcement);
    		request.getSession().removeAttribute("announcement");
    		response.sendRedirect("announcement.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService announcementTypeService = com.app.docmgr.service.LookupService.getInstance();
			List announcementTypeList = announcementTypeService.getList("  and lookup.type='announcementType'  ", null);
			request.setAttribute("announcementTypeList", announcementTypeList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Announcement'  ", null);
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
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, announcement, errors);
    		
			com.app.docmgr.service.LookupService announcementTypeService = com.app.docmgr.service.LookupService.getInstance();
			List announcementTypeList = announcementTypeService.getList("  and lookup.type='announcementType'  ", null);
			request.setAttribute("announcementTypeList", announcementTypeList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Announcement'  ", null);
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
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=edit_confirm");
    		}
    		
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		request.getSession().removeAttribute("announcement");
    		response.sendRedirect("announcement.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=delete_confirm");
    		}
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","deleted"));
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("announcement.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=submit_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","submitted"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=approve_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","approved"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=reject_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","rejected"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=pending_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","pending"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=process_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","processed"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=close_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","closed"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=archive_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","archived"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=remove_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","removed"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if (announcement == null){
	    		announcement = AnnouncementService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("announcement", announcement);
	    	}
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("announcement.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Announcement announcement = (Announcement) request.getSession().getAttribute("announcement");
    		if(announcement == null){
    			response.sendRedirect("announcement.do?action=cancel_confirm");
    		}
    		announcement.setStatus(StatusService.getInstance().getByTypeandCode("Announcement","cancelled"));
			announcement.setLastUpdatedDate(new Date());
			announcement.setLastUpdatedBy(_doneBy);
    		AnnouncementService.getInstance().update(announcement);
    		response.sendRedirect("announcement.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("announcement.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Announcement announcement, ActionErrors errors){
    	try{    		
			String content = request.getParameter("content");
			announcement.setContent(content);
			if(content==null || content.trim().length() == 0 ){
				errors.add("announcement.content", new ActionError("error.announcement.content"));
			}
			String targetUsers = request.getParameter("targetUsers");
			announcement.setTargetUsers(targetUsers);
			String targetOrganizations = request.getParameter("targetOrganizations");
			announcement.setTargetOrganizations(targetOrganizations);
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				announcement.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					announcement.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("announcement.createdDate", new ActionError("error.announcement.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			announcement.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("announcement.createdBy", new ActionError("error.announcement.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				announcement.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					announcement.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			announcement.setLastUpdatedBy(lastUpdatedBy);
*/ 
			com.app.docmgr.model.Lookup  announcementTypeObj =null;
			com.app.docmgr.service.LookupService announcementTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String announcementTypeStr = request.getParameter("announcementType");
				
				if(announcementTypeStr == null || announcementTypeStr.trim().length() == 0 ){
					announcement.setAnnouncementType(null);
				}else{			
					announcementTypeObj = announcementTypeService.get(new Long(announcementTypeStr));
					announcement.setAnnouncementType(announcementTypeObj);
				}
			}catch(Exception ex){}	
			if(announcementTypeObj==null){
				errors.add("announcement.announcementType", new ActionError("error.announcement.announcementType"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					announcement.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					announcement.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("announcement.status", new ActionError("error.announcement.status"));
			}
*/ 
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

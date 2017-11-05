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


public class BookmarkActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.BookmarkActionBase");	
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
    	
    	request.getSession().removeAttribute("bookmark");
		
		String bookmark_filter = request.getParameter("bookmark_filter");
		//this for orderBy field ASC/DESC
		String bookmark_fieldOrder = request.getParameter("bookmark_fieldOrder");
		String bookmark_orderType = request.getParameter("bookmark_orderType");
		
		if(bookmark_fieldOrder == null || bookmark_fieldOrder.length() == 0) bookmark_fieldOrder = null;
		if(bookmark_orderType == null || bookmark_orderType.length() == 0) bookmark_orderType = null;
		request.getSession().setAttribute("bookmark_fieldOrder", bookmark_fieldOrder==null?"":bookmark_fieldOrder);	 
		request.getSession().setAttribute("bookmark_orderType", bookmark_orderType==null?"":bookmark_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService bookmarkTypeService = com.app.docmgr.service.LookupService.getInstance();
			List bookmarkTypeList = bookmarkTypeService.getList("  and lookup.type='bookmarkType'  ", null);
			request.setAttribute("bookmarkTypeList", bookmarkTypeList);
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Bookmark'  ", null);
			request.setAttribute("statusList", statusList);
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
		}catch(Exception ex){
		
		}
		StringBuffer bookmark_filterSb = new StringBuffer();
		String param_bookmark_url_filter = "";
		if(request.getParameter("bookmark_url_filter")!=null){
			param_bookmark_url_filter = request.getParameter("bookmark_url_filter");
			if(param_bookmark_url_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.url like '%"+param_bookmark_url_filter+"%' ");
			}
		}
		request.getSession().setAttribute("bookmark_url_filter", param_bookmark_url_filter);
		String param_bookmark_name_filter = "";
		if(request.getParameter("bookmark_name_filter")!=null){
			param_bookmark_name_filter = request.getParameter("bookmark_name_filter");
			if(param_bookmark_name_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.name like '%"+param_bookmark_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("bookmark_name_filter", param_bookmark_name_filter);
		String param_bookmark_category_filter = "";
		if(request.getParameter("bookmark_category_filter")!=null){
			param_bookmark_category_filter = request.getParameter("bookmark_category_filter");
			if(param_bookmark_category_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.category like '%"+param_bookmark_category_filter+"%' ");
			}
		}
		request.getSession().setAttribute("bookmark_category_filter", param_bookmark_category_filter);
		String param_bookmark_note_filter = "";
		if(request.getParameter("bookmark_note_filter")!=null){
			param_bookmark_note_filter = request.getParameter("bookmark_note_filter");
			if(param_bookmark_note_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.note like '%"+param_bookmark_note_filter+"%' ");
			}
		}
		request.getSession().setAttribute("bookmark_note_filter", param_bookmark_note_filter);
		String param_bookmark_createdDate_filter_start = "";
		if(request.getParameter("bookmark_createdDate_filter_start")!=null){
			param_bookmark_createdDate_filter_start = request.getParameter("bookmark_createdDate_filter_start");
			if(param_bookmark_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_bookmark_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_bookmark_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_bookmark_createdDate_filter_start));
					String param_bookmark_createdDate_filter_start_cal_val = param_bookmark_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_bookmark_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_bookmark_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					bookmark_filterSb.append("  AND bookmark.createdDate >= '"+param_bookmark_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("bookmark_createdDate_filter_start", param_bookmark_createdDate_filter_start);

		String param_bookmark_createdDate_filter_end = "";
		if(request.getParameter("bookmark_createdDate_filter_end")!=null){
			param_bookmark_createdDate_filter_end = request.getParameter("bookmark_createdDate_filter_end");
			if(param_bookmark_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_bookmark_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_bookmark_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_bookmark_createdDate_filter_end));
					String param_bookmark_createdDate_filter_end_cal_val = param_bookmark_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_bookmark_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_bookmark_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					bookmark_filterSb.append("  AND bookmark.createdDate  <= '"+param_bookmark_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("bookmark_createdDate_filter_end", param_bookmark_createdDate_filter_end);

		String param_bookmark_createdBy_filter = "";
		if(request.getParameter("bookmark_createdBy_filter")!=null){
			param_bookmark_createdBy_filter = request.getParameter("bookmark_createdBy_filter");
			if(param_bookmark_createdBy_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.createdBy like '%"+param_bookmark_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("bookmark_createdBy_filter", param_bookmark_createdBy_filter);
		String param_bookmark_lastUpdatedDate_filter_start = "";
		if(request.getParameter("bookmark_lastUpdatedDate_filter_start")!=null){
			param_bookmark_lastUpdatedDate_filter_start = request.getParameter("bookmark_lastUpdatedDate_filter_start");
			if(param_bookmark_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_bookmark_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_bookmark_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_bookmark_lastUpdatedDate_filter_start));
					String param_bookmark_lastUpdatedDate_filter_start_cal_val = param_bookmark_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_bookmark_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_bookmark_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					bookmark_filterSb.append("  AND bookmark.lastUpdatedDate >= '"+param_bookmark_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("bookmark_lastUpdatedDate_filter_start", param_bookmark_lastUpdatedDate_filter_start);

		String param_bookmark_lastUpdatedDate_filter_end = "";
		if(request.getParameter("bookmark_lastUpdatedDate_filter_end")!=null){
			param_bookmark_lastUpdatedDate_filter_end = request.getParameter("bookmark_lastUpdatedDate_filter_end");
			if(param_bookmark_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_bookmark_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_bookmark_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_bookmark_lastUpdatedDate_filter_end));
					String param_bookmark_lastUpdatedDate_filter_end_cal_val = param_bookmark_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_bookmark_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_bookmark_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					bookmark_filterSb.append("  AND bookmark.lastUpdatedDate  <= '"+param_bookmark_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("bookmark_lastUpdatedDate_filter_end", param_bookmark_lastUpdatedDate_filter_end);

		String param_bookmark_lastUpdatedBy_filter = "";
		if(request.getParameter("bookmark_lastUpdatedBy_filter")!=null){
			param_bookmark_lastUpdatedBy_filter = request.getParameter("bookmark_lastUpdatedBy_filter");
			if(param_bookmark_lastUpdatedBy_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.lastUpdatedBy like '%"+param_bookmark_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("bookmark_lastUpdatedBy_filter", param_bookmark_lastUpdatedBy_filter);
		String param_bookmark_bookmarkType_filter = "";
		if(request.getParameter("bookmark_bookmarkType_filter")!=null){
			param_bookmark_bookmarkType_filter = request.getParameter("bookmark_bookmarkType_filter");
			if(param_bookmark_bookmarkType_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.bookmarkType = '"+param_bookmark_bookmarkType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("bookmark_bookmarkType_filter", param_bookmark_bookmarkType_filter);
		String param_bookmark_status_filter = "";
		if(request.getParameter("bookmark_status_filter")!=null){
			param_bookmark_status_filter = request.getParameter("bookmark_status_filter");
			if(param_bookmark_status_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.status = '"+param_bookmark_status_filter+"' ");
			}
		}		
		request.getSession().setAttribute("bookmark_status_filter", param_bookmark_status_filter);
		String param_bookmark_owner_filter = "";
		if(request.getParameter("bookmark_owner_filter")!=null){
			param_bookmark_owner_filter = request.getParameter("bookmark_owner_filter");
			if(param_bookmark_owner_filter.length() > 0 ){				
				bookmark_filterSb.append("  AND bookmark.owner = '"+param_bookmark_owner_filter+"' ");
			}
		}		
		request.getSession().setAttribute("bookmark_owner_filter", param_bookmark_owner_filter);
		
		if(bookmark_fieldOrder!=null && bookmark_orderType != null )bookmark_filterSb.append(" ORDER BY "+bookmark_fieldOrder+" "+bookmark_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		BookmarkService bookmarkService = BookmarkService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList bookmarkList = bookmarkService.getPartialList(bookmark_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, bookmarkList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, bookmarkList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("bookmarkList", bookmarkList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("bookmark");
		
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
    		BookmarkService bookmarkService = BookmarkService.getInstance();
    		List bookmarkList = bookmarkService.getList(parentFilter , null);
    		request.setAttribute("bookmarkList", bookmarkList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do");
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
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do");
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
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null) bookmark = new Bookmark();
    		
			com.app.docmgr.service.LookupService bookmarkTypeService = com.app.docmgr.service.LookupService.getInstance();
			List bookmarkTypeList = bookmarkTypeService.getList("  and lookup.type='bookmarkType'  ", null);
			request.setAttribute("bookmarkTypeList", bookmarkTypeList);
 /* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Bookmark'  ", null);
			request.setAttribute("statusList", statusList);
 */ 			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);

    		request.getSession().setAttribute("bookmark", bookmark);
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
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, bookmark, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService bookmarkTypeService = com.app.docmgr.service.LookupService.getInstance();
			List bookmarkTypeList = bookmarkTypeService.getList("  and lookup.type='bookmarkType'  ", null);
			request.setAttribute("bookmarkTypeList", bookmarkTypeList);
/*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Bookmark'  ", null);
			request.setAttribute("statusList", statusList);
*/			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);
	
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
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","new"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setCreatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
			bookmark.setCreatedBy(_doneBy);
    		BookmarkService.getInstance().add(bookmark);
    		request.getSession().removeAttribute("bookmark");
    		response.sendRedirect("bookmark.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService bookmarkTypeService = com.app.docmgr.service.LookupService.getInstance();
			List bookmarkTypeList = bookmarkTypeService.getList("  and lookup.type='bookmarkType'  ", null);
			request.setAttribute("bookmarkTypeList", bookmarkTypeList);
/* 			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Bookmark'  ", null);
			request.setAttribute("statusList", statusList);
*/ 			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);

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
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, bookmark, errors);
    		
			com.app.docmgr.service.LookupService bookmarkTypeService = com.app.docmgr.service.LookupService.getInstance();
			List bookmarkTypeList = bookmarkTypeService.getList("  and lookup.type='bookmarkType'  ", null);
			request.setAttribute("bookmarkTypeList", bookmarkTypeList);
 /*			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			List statusList = statusService.getList("  and status.type='Bookmark'  ", null);
			request.setAttribute("statusList", statusList);
 */			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			List ownerList = ownerService.getList(null, null);
			request.setAttribute("ownerList", ownerList);

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
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=edit_confirm");
    		}
    		
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		request.getSession().removeAttribute("bookmark");
    		response.sendRedirect("bookmark.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=delete_confirm");
    		}
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","deleted"));
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("bookmark.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }

   	public ActionForward doSubmitConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("submit_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doSubmitOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=submit_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","submitted"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=submit_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doApproveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("approve_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doApproveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=approve_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","approved"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=approve_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRejectConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("reject_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRejectOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=reject_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","rejected"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=reject_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doPendingConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("pending_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doPendingOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=pending_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","pending"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=pending_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doProcessConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("process_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doProcessOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=process_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","processed"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=process_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCloseConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("close_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCloseOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=close_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","closed"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=close_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doArchiveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("archive_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doArchiveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=archive_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","archived"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=archive_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doRemoveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("remove_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doRemoveOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=remove_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","removed"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=remove_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }

   	public ActionForward doCancelConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if (bookmark == null){
	    		bookmark = BookmarkService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("bookmark", bookmark);
	    	}
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}
    		    		

    		forward = mapping.findForward("cancel_confirm");
    	}catch(Exception ex){
	    	ex.printStackTrace();
    		try{
	    		response.sendRedirect("bookmark.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}    	
    	return forward;
    }

    public void doCancelOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
       		Bookmark bookmark = (Bookmark) request.getSession().getAttribute("bookmark");
    		if(bookmark == null){
    			response.sendRedirect("bookmark.do?action=cancel_confirm");
    		}
    		bookmark.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark","cancelled"));
			bookmark.setLastUpdatedDate(new Date());
			bookmark.setLastUpdatedBy(_doneBy);
    		BookmarkService.getInstance().update(bookmark);
    		response.sendRedirect("bookmark.do?action=detail");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("bookmark.do?action=cancel_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Bookmark bookmark, ActionErrors errors){
    	try{    		
			String url = request.getParameter("url");
			bookmark.setUrl(url);
			if(url==null || url.trim().length() == 0 ){
				errors.add("bookmark.url", new ActionError("error.bookmark.url"));
			}
			String name = request.getParameter("name");
			bookmark.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("bookmark.name", new ActionError("error.bookmark.name"));
			}
			String category = request.getParameter("category");
			bookmark.setCategory(category);
			if(category==null || category.trim().length() == 0 ){
				errors.add("bookmark.category", new ActionError("error.bookmark.category"));
			}
			String note = request.getParameter("note");
			bookmark.setNote(note);
			if(note==null || note.trim().length() == 0 ){
				errors.add("bookmark.note", new ActionError("error.bookmark.note"));
			}
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				bookmark.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					bookmark.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("bookmark.createdDate", new ActionError("error.bookmark.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			bookmark.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("bookmark.createdBy", new ActionError("error.bookmark.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				bookmark.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					bookmark.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			bookmark.setLastUpdatedBy(lastUpdatedBy);
*/ 
			com.app.docmgr.model.Lookup  bookmarkTypeObj =null;
			com.app.docmgr.service.LookupService bookmarkTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String bookmarkTypeStr = request.getParameter("bookmarkType");
				
				if(bookmarkTypeStr == null || bookmarkTypeStr.trim().length() == 0 ){
					bookmark.setBookmarkType(null);
				}else{			
					bookmarkTypeObj = bookmarkTypeService.get(new Long(bookmarkTypeStr));
					bookmark.setBookmarkType(bookmarkTypeObj);
				}
			}catch(Exception ex){}	
			if(bookmarkTypeObj==null){
				errors.add("bookmark.bookmarkType", new ActionError("error.bookmark.bookmarkType"));
			}
/* 			com.app.docmgr.model.Status  statusObj =null;
			com.app.docmgr.service.StatusService statusService = com.app.docmgr.service.StatusService.getInstance();
			try{
				String statusStr = request.getParameter("status");
				
				if(statusStr == null || statusStr.trim().length() == 0 ){
					bookmark.setStatus(null);
				}else{			
					statusObj = statusService.get(new Long(statusStr));
					bookmark.setStatus(statusObj);
				}
			}catch(Exception ex){}	
			if(statusObj==null){
				errors.add("bookmark.status", new ActionError("error.bookmark.status"));
			}
*/ 			com.app.docmgr.model.User  ownerObj =null;
			com.app.docmgr.service.UserService ownerService = com.app.docmgr.service.UserService.getInstance();
			try{
				String ownerStr = request.getParameter("owner");
				
				if(ownerStr == null || ownerStr.trim().length() == 0 ){
					bookmark.setOwner(null);
				}else{			
					ownerObj = ownerService.get(new Long(ownerStr));
					bookmark.setOwner(ownerObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

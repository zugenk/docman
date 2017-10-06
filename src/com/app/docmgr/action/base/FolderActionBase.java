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
 * @createDate 07-10-2017 06:18:15
 */


public class FolderActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.FolderActionBase");	
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
    	
    	request.getSession().removeAttribute("folder");
		
		String folder_filter = request.getParameter("folder_filter");
		//this for orderBy field ASC/DESC
		String folder_fieldOrder = request.getParameter("folder_fieldOrder");
		String folder_orderType = request.getParameter("folder_orderType");
		
		if(folder_fieldOrder == null || folder_fieldOrder.length() == 0) folder_fieldOrder = null;
		if(folder_orderType == null || folder_orderType.length() == 0) folder_orderType = null;
		request.getSession().setAttribute("folder_fieldOrder", folder_fieldOrder==null?"":folder_fieldOrder);	 
		request.getSession().setAttribute("folder_orderType", folder_orderType==null?"":folder_orderType);
		
		try{ 
			com.app.docmgr.service.LookupService folderTypeService = com.app.docmgr.service.LookupService.getInstance();
			List folderTypeList = folderTypeService.getList("  and lookup.type='folderType'  ", null);
			request.setAttribute("folderTypeList", folderTypeList);
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
		}catch(Exception ex){
		
		}
		StringBuffer folder_filterSb = new StringBuffer();
		String param_folder_code_filter = "";
		if(request.getParameter("folder_code_filter")!=null){
			param_folder_code_filter = request.getParameter("folder_code_filter");
			if(param_folder_code_filter.length() > 0 ){				
				folder_filterSb.append("  AND folder.code like '%"+param_folder_code_filter+"%' ");
			}
		}
		request.getSession().setAttribute("folder_code_filter", param_folder_code_filter);
		String param_folder_name_filter = "";
		if(request.getParameter("folder_name_filter")!=null){
			param_folder_name_filter = request.getParameter("folder_name_filter");
			if(param_folder_name_filter.length() > 0 ){				
				folder_filterSb.append("  AND folder.name like '%"+param_folder_name_filter+"%' ");
			}
		}
		request.getSession().setAttribute("folder_name_filter", param_folder_name_filter);
		String param_folder_createdDate_filter_start = "";
		if(request.getParameter("folder_createdDate_filter_start")!=null){
			param_folder_createdDate_filter_start = request.getParameter("folder_createdDate_filter_start");
			if(param_folder_createdDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_folder_createdDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_folder_createdDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_folder_createdDate_filter_start));
					String param_folder_createdDate_filter_start_cal_val = param_folder_createdDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_folder_createdDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_folder_createdDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					folder_filterSb.append("  AND folder.createdDate >= '"+param_folder_createdDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("folder_createdDate_filter_start", param_folder_createdDate_filter_start);

		String param_folder_createdDate_filter_end = "";
		if(request.getParameter("folder_createdDate_filter_end")!=null){
			param_folder_createdDate_filter_end = request.getParameter("folder_createdDate_filter_end");
			if(param_folder_createdDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_folder_createdDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_folder_createdDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_folder_createdDate_filter_end));
					String param_folder_createdDate_filter_end_cal_val = param_folder_createdDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_folder_createdDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_folder_createdDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					folder_filterSb.append("  AND folder.createdDate  <= '"+param_folder_createdDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("folder_createdDate_filter_end", param_folder_createdDate_filter_end);

		String param_folder_createdBy_filter = "";
		if(request.getParameter("folder_createdBy_filter")!=null){
			param_folder_createdBy_filter = request.getParameter("folder_createdBy_filter");
			if(param_folder_createdBy_filter.length() > 0 ){				
				folder_filterSb.append("  AND folder.createdBy like '%"+param_folder_createdBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("folder_createdBy_filter", param_folder_createdBy_filter);
		String param_folder_lastUpdatedDate_filter_start = "";
		if(request.getParameter("folder_lastUpdatedDate_filter_start")!=null){
			param_folder_lastUpdatedDate_filter_start = request.getParameter("folder_lastUpdatedDate_filter_start");
			if(param_folder_lastUpdatedDate_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_folder_lastUpdatedDate_filter_start_cal = java.util.Calendar.getInstance();				
					param_folder_lastUpdatedDate_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_folder_lastUpdatedDate_filter_start));
					String param_folder_lastUpdatedDate_filter_start_cal_val = param_folder_lastUpdatedDate_filter_start_cal.get(Calendar.YEAR)+"-"+(param_folder_lastUpdatedDate_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_folder_lastUpdatedDate_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					folder_filterSb.append("  AND folder.lastUpdatedDate >= '"+param_folder_lastUpdatedDate_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("folder_lastUpdatedDate_filter_start", param_folder_lastUpdatedDate_filter_start);

		String param_folder_lastUpdatedDate_filter_end = "";
		if(request.getParameter("folder_lastUpdatedDate_filter_end")!=null){
			param_folder_lastUpdatedDate_filter_end = request.getParameter("folder_lastUpdatedDate_filter_end");
			if(param_folder_lastUpdatedDate_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_folder_lastUpdatedDate_filter_end_cal = java.util.Calendar.getInstance();				
					param_folder_lastUpdatedDate_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_folder_lastUpdatedDate_filter_end));
					String param_folder_lastUpdatedDate_filter_end_cal_val = param_folder_lastUpdatedDate_filter_end_cal.get(Calendar.YEAR)+"-"+(param_folder_lastUpdatedDate_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_folder_lastUpdatedDate_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					folder_filterSb.append("  AND folder.lastUpdatedDate  <= '"+param_folder_lastUpdatedDate_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("folder_lastUpdatedDate_filter_end", param_folder_lastUpdatedDate_filter_end);

		String param_folder_lastUpdatedBy_filter = "";
		if(request.getParameter("folder_lastUpdatedBy_filter")!=null){
			param_folder_lastUpdatedBy_filter = request.getParameter("folder_lastUpdatedBy_filter");
			if(param_folder_lastUpdatedBy_filter.length() > 0 ){				
				folder_filterSb.append("  AND folder.lastUpdatedBy like '%"+param_folder_lastUpdatedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("folder_lastUpdatedBy_filter", param_folder_lastUpdatedBy_filter);
		String param_folder_folderType_filter = "";
		if(request.getParameter("folder_folderType_filter")!=null){
			param_folder_folderType_filter = request.getParameter("folder_folderType_filter");
			if(param_folder_folderType_filter.length() > 0 ){				
				folder_filterSb.append("  AND folder.folderType = '"+param_folder_folderType_filter+"' ");
			}
		}		
		request.getSession().setAttribute("folder_folderType_filter", param_folder_folderType_filter);
		String param_folder_parentFolder_filter = "";
		if(request.getParameter("folder_parentFolder_filter")!=null){
			param_folder_parentFolder_filter = request.getParameter("folder_parentFolder_filter");
			if(param_folder_parentFolder_filter.length() > 0 ){				
				folder_filterSb.append("  AND folder.parentFolder = '"+param_folder_parentFolder_filter+"' ");
			}
		}		
		request.getSession().setAttribute("folder_parentFolder_filter", param_folder_parentFolder_filter);
		
		if(folder_fieldOrder!=null && folder_orderType != null )folder_filterSb.append(" ORDER BY "+folder_fieldOrder+" "+folder_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		FolderService folderService = FolderService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList folderList = folderService.getPartialList(folder_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, folderList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, folderList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("folderList", folderList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("folder");
		
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
    		FolderService folderService = FolderService.getInstance();
    		List folderList = folderService.getList(parentFilter , null);
    		request.setAttribute("folderList", folderList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if (folder == null){
	    		folder = FolderService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("folder", folder);
	    	}
    		if(folder == null){
    			response.sendRedirect("folder.do");
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
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if (folder == null){
	    		folder = FolderService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("folder", folder);
	    	}
    		if(folder == null){
    			response.sendRedirect("folder.do");
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
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if(folder == null) folder = new Folder();
    		
			com.app.docmgr.service.LookupService folderTypeService = com.app.docmgr.service.LookupService.getInstance();
			List folderTypeList = folderTypeService.getList("  and lookup.type='folderType'  ", null);
			request.setAttribute("folderTypeList", folderTypeList);
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);

    		request.getSession().setAttribute("folder", folder);
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
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if(folder == null){
    			response.sendRedirect("folder.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, folder, errors);
    		//set Many To One Property
    		
			com.app.docmgr.service.LookupService folderTypeService = com.app.docmgr.service.LookupService.getInstance();
			List folderTypeList = folderTypeService.getList("  and lookup.type='folderType'  ", null);
			request.setAttribute("folderTypeList", folderTypeList);
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);
	
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
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
			folder.setLastUpdatedDate(new Date());
			folder.setCreatedDate(new Date());
			folder.setLastUpdatedBy(_doneBy);
			folder.setCreatedBy(_doneBy);
    		FolderService.getInstance().add(folder);
    		request.getSession().removeAttribute("folder");
    		response.sendRedirect("folder.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("folder.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if (folder == null){
	    		folder = FolderService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("folder", folder);
	    	}
    		if(folder == null){
    			response.sendRedirect("folder.do");
    			return null;
    		}
    		
			com.app.docmgr.service.LookupService folderTypeService = com.app.docmgr.service.LookupService.getInstance();
			List folderTypeList = folderTypeService.getList("  and lookup.type='folderType'  ", null);
			request.setAttribute("folderTypeList", folderTypeList);
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);

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
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if(folder == null){
    			response.sendRedirect("folder.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, folder, errors);
    		
			com.app.docmgr.service.LookupService folderTypeService = com.app.docmgr.service.LookupService.getInstance();
			List folderTypeList = folderTypeService.getList("  and lookup.type='folderType'  ", null);
			request.setAttribute("folderTypeList", folderTypeList);
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			List parentFolderList = parentFolderService.getList(null, null);
			request.setAttribute("parentFolderList", parentFolderList);

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
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if(folder == null){
    			response.sendRedirect("folder.do?action=edit_confirm");
    		}
    		
			folder.setLastUpdatedDate(new Date());
			folder.setLastUpdatedBy(_doneBy);
    		FolderService.getInstance().update(folder);
    		request.getSession().removeAttribute("folder");
    		response.sendRedirect("folder.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("folder.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if (folder == null){
	    		folder = FolderService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("folder", folder);
	    	}
    		if(folder == null){
    			response.sendRedirect("folder.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("folder.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		Folder folder = (Folder) request.getSession().getAttribute("folder");
    		if(folder == null){
    			response.sendRedirect("folder.do?action=delete_confirm");
    		}
    		FolderService.getInstance().delete(folder);
    		request.getSession().removeAttribute("folder");
    		response.sendRedirect("folder.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("folder.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, Folder folder, ActionErrors errors){
    	try{    		
			String code = request.getParameter("code");
			folder.setCode(code);
			if(code==null || code.trim().length() == 0 ){
				errors.add("folder.code", new ActionError("error.folder.code"));
			}
			String name = request.getParameter("name");
			folder.setName(name);
			if(name==null || name.trim().length() == 0 ){
				errors.add("folder.name", new ActionError("error.folder.name"));
			}
/* 			String createdDate = request.getParameter("createdDate");
			if(createdDate==null || createdDate.trim().length() == 0 ){
				folder.setCreatedDate(null);
			}else{
				try{
					java.util.Calendar createdDateCalendar = java.util.Calendar.getInstance();
					createdDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(createdDate));			
					folder.setCreatedDate(createdDateCalendar.getTime());
				}catch(Exception ex){}
			}
			if(createdDate==null || createdDate.trim().length() == 0 ){
				errors.add("folder.createdDate", new ActionError("error.folder.createdDate"));
			}
*/ /* 			String createdBy = request.getParameter("createdBy");
			folder.setCreatedBy(createdBy);
			if(createdBy==null || createdBy.trim().length() == 0 ){
				errors.add("folder.createdBy", new ActionError("error.folder.createdBy"));
			}
*/ /* 			String lastUpdatedDate = request.getParameter("lastUpdatedDate");
			if(lastUpdatedDate==null || lastUpdatedDate.trim().length() == 0 ){
				folder.setLastUpdatedDate(null);
			}else{
				try{
					java.util.Calendar lastUpdatedDateCalendar = java.util.Calendar.getInstance();
					lastUpdatedDateCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastUpdatedDate));			
					folder.setLastUpdatedDate(lastUpdatedDateCalendar.getTime());
				}catch(Exception ex){}
			}
*/ /* 			String lastUpdatedBy = request.getParameter("lastUpdatedBy");
			folder.setLastUpdatedBy(lastUpdatedBy);
*/ 
			com.app.docmgr.model.Lookup  folderTypeObj =null;
			com.app.docmgr.service.LookupService folderTypeService = com.app.docmgr.service.LookupService.getInstance();
			try{
				String folderTypeStr = request.getParameter("folderType");
				
				if(folderTypeStr == null || folderTypeStr.trim().length() == 0 ){
					folder.setFolderType(null);
				}else{			
					folderTypeObj = folderTypeService.get(new Long(folderTypeStr));
					folder.setFolderType(folderTypeObj);
				}
			}catch(Exception ex){}	
			if(folderTypeObj==null){
				errors.add("folder.folderType", new ActionError("error.folder.folderType"));
			}
			com.app.docmgr.model.Folder  parentFolderObj =null;
			com.app.docmgr.service.FolderService parentFolderService = com.app.docmgr.service.FolderService.getInstance();
			try{
				String parentFolderStr = request.getParameter("parentFolder");
				
				if(parentFolderStr == null || parentFolderStr.trim().length() == 0 ){
					folder.setParentFolder(null);
				}else{			
					parentFolderObj = parentFolderService.get(new Long(parentFolderStr));
					folder.setParentFolder(parentFolderObj);
				}
			}catch(Exception ex){}	

    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

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


public class IPassportPoolActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.IPassportPoolActionBase");	
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
    	
    	request.getSession().removeAttribute("iPassportPool");
		
		String iPassportPool_filter = request.getParameter("iPassportPool_filter");
		//this for orderBy field ASC/DESC
		String iPassportPool_fieldOrder = request.getParameter("iPassportPool_fieldOrder");
		String iPassportPool_orderType = request.getParameter("iPassportPool_orderType");
		
		if(iPassportPool_fieldOrder == null || iPassportPool_fieldOrder.length() == 0) iPassportPool_fieldOrder = null;
		if(iPassportPool_orderType == null || iPassportPool_orderType.length() == 0) iPassportPool_orderType = null;
		request.getSession().setAttribute("iPassportPool_fieldOrder", iPassportPool_fieldOrder==null?"":iPassportPool_fieldOrder);	 
		request.getSession().setAttribute("iPassportPool_orderType", iPassportPool_orderType==null?"":iPassportPool_orderType);
		
		try{ 
		}catch(Exception ex){
		
		}
		StringBuffer iPassportPool_filterSb = new StringBuffer();
		String param_iPassportPool_ipassport_filter = "";
		if(request.getParameter("iPassportPool_ipassport_filter")!=null){
			param_iPassportPool_ipassport_filter = request.getParameter("iPassportPool_ipassport_filter");
			if(param_iPassportPool_ipassport_filter.length() > 0 ){				
				iPassportPool_filterSb.append("  AND iPassportPool.ipassport like '%"+param_iPassportPool_ipassport_filter+"%' ");
			}
		}
		request.getSession().setAttribute("iPassportPool_ipassport_filter", param_iPassportPool_ipassport_filter);
		String param_iPassportPool_loginId_filter = "";
		if(request.getParameter("iPassportPool_loginId_filter")!=null){
			param_iPassportPool_loginId_filter = request.getParameter("iPassportPool_loginId_filter");
			if(param_iPassportPool_loginId_filter.length() > 0 ){				
				iPassportPool_filterSb.append("  AND iPassportPool.loginId like '%"+param_iPassportPool_loginId_filter+"%' ");
			}
		}
		request.getSession().setAttribute("iPassportPool_loginId_filter", param_iPassportPool_loginId_filter);
		String param_iPassportPool_lastLogin_filter_start = "";
		if(request.getParameter("iPassportPool_lastLogin_filter_start")!=null){
			param_iPassportPool_lastLogin_filter_start = request.getParameter("iPassportPool_lastLogin_filter_start");
			if(param_iPassportPool_lastLogin_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_iPassportPool_lastLogin_filter_start_cal = java.util.Calendar.getInstance();				
					param_iPassportPool_lastLogin_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_iPassportPool_lastLogin_filter_start));
					String param_iPassportPool_lastLogin_filter_start_cal_val = param_iPassportPool_lastLogin_filter_start_cal.get(Calendar.YEAR)+"-"+(param_iPassportPool_lastLogin_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_iPassportPool_lastLogin_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					iPassportPool_filterSb.append("  AND iPassportPool.lastLogin >= '"+param_iPassportPool_lastLogin_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("iPassportPool_lastLogin_filter_start", param_iPassportPool_lastLogin_filter_start);

		String param_iPassportPool_lastLogin_filter_end = "";
		if(request.getParameter("iPassportPool_lastLogin_filter_end")!=null){
			param_iPassportPool_lastLogin_filter_end = request.getParameter("iPassportPool_lastLogin_filter_end");
			if(param_iPassportPool_lastLogin_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_iPassportPool_lastLogin_filter_end_cal = java.util.Calendar.getInstance();				
					param_iPassportPool_lastLogin_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_iPassportPool_lastLogin_filter_end));
					String param_iPassportPool_lastLogin_filter_end_cal_val = param_iPassportPool_lastLogin_filter_end_cal.get(Calendar.YEAR)+"-"+(param_iPassportPool_lastLogin_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_iPassportPool_lastLogin_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					iPassportPool_filterSb.append("  AND iPassportPool.lastLogin  <= '"+param_iPassportPool_lastLogin_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("iPassportPool_lastLogin_filter_end", param_iPassportPool_lastLogin_filter_end);

		String param_iPassportPool_lastActive_filter_start = "";
		if(request.getParameter("iPassportPool_lastActive_filter_start")!=null){
			param_iPassportPool_lastActive_filter_start = request.getParameter("iPassportPool_lastActive_filter_start");
			if(param_iPassportPool_lastActive_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_iPassportPool_lastActive_filter_start_cal = java.util.Calendar.getInstance();				
					param_iPassportPool_lastActive_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_iPassportPool_lastActive_filter_start));
					String param_iPassportPool_lastActive_filter_start_cal_val = param_iPassportPool_lastActive_filter_start_cal.get(Calendar.YEAR)+"-"+(param_iPassportPool_lastActive_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_iPassportPool_lastActive_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					iPassportPool_filterSb.append("  AND iPassportPool.lastActive >= '"+param_iPassportPool_lastActive_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("iPassportPool_lastActive_filter_start", param_iPassportPool_lastActive_filter_start);

		String param_iPassportPool_lastActive_filter_end = "";
		if(request.getParameter("iPassportPool_lastActive_filter_end")!=null){
			param_iPassportPool_lastActive_filter_end = request.getParameter("iPassportPool_lastActive_filter_end");
			if(param_iPassportPool_lastActive_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_iPassportPool_lastActive_filter_end_cal = java.util.Calendar.getInstance();				
					param_iPassportPool_lastActive_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_iPassportPool_lastActive_filter_end));
					String param_iPassportPool_lastActive_filter_end_cal_val = param_iPassportPool_lastActive_filter_end_cal.get(Calendar.YEAR)+"-"+(param_iPassportPool_lastActive_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_iPassportPool_lastActive_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					iPassportPool_filterSb.append("  AND iPassportPool.lastActive  <= '"+param_iPassportPool_lastActive_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("iPassportPool_lastActive_filter_end", param_iPassportPool_lastActive_filter_end);

		
		if(iPassportPool_fieldOrder!=null && iPassportPool_orderType != null )iPassportPool_filterSb.append(" ORDER BY "+iPassportPool_fieldOrder+" "+iPassportPool_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		IPassportPoolService iPassportPoolService = IPassportPoolService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList iPassportPoolList = iPassportPoolService.getPartialList(iPassportPool_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, iPassportPoolList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, iPassportPoolList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("iPassportPoolList", iPassportPoolList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("iPassportPool");
		
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
    		IPassportPoolService iPassportPoolService = IPassportPoolService.getInstance();
    		List iPassportPoolList = iPassportPoolService.getList(parentFilter , null);
    		request.setAttribute("iPassportPoolList", iPassportPoolList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if (iPassportPool == null){
	    		iPassportPool = IPassportPoolService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("iPassportPool", iPassportPool);
	    	}
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do");
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
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if (iPassportPool == null){
	    		iPassportPool = IPassportPoolService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("iPassportPool", iPassportPool);
	    	}
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do");
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
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if(iPassportPool == null) iPassportPool = new IPassportPool();
    		

    		request.getSession().setAttribute("iPassportPool", iPassportPool);
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
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, iPassportPool, errors);
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
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		IPassportPoolService.getInstance().add(iPassportPool);
    		request.getSession().removeAttribute("iPassportPool");
    		response.sendRedirect("iPassportPool.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("iPassportPool.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if (iPassportPool == null){
	    		iPassportPool = IPassportPoolService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("iPassportPool", iPassportPool);
	    	}
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do");
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
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, iPassportPool, errors);
    		

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
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do?action=edit_confirm");
    		}
    		
    		IPassportPoolService.getInstance().update(iPassportPool);
    		request.getSession().removeAttribute("iPassportPool");
    		response.sendRedirect("iPassportPool.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("iPassportPool.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if (iPassportPool == null){
	    		iPassportPool = IPassportPoolService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("iPassportPool", iPassportPool);
	    	}
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("iPassportPool.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		IPassportPool iPassportPool = (IPassportPool) request.getSession().getAttribute("iPassportPool");
    		if(iPassportPool == null){
    			response.sendRedirect("iPassportPool.do?action=delete_confirm");
    		}
    		IPassportPoolService.getInstance().delete(iPassportPool);
    		request.getSession().removeAttribute("iPassportPool");
    		response.sendRedirect("iPassportPool.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("iPassportPool.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, IPassportPool iPassportPool, ActionErrors errors){
    	try{    		
			String ipassport = request.getParameter("ipassport");
			iPassportPool.setIpassport(ipassport);
			if(ipassport==null || ipassport.trim().length() == 0 ){
				errors.add("iPassportPool.ipassport", new ActionError("error.iPassportPool.ipassport"));
			}
			String loginId = request.getParameter("loginId");
			iPassportPool.setLoginId(loginId);
			if(loginId==null || loginId.trim().length() == 0 ){
				errors.add("iPassportPool.loginId", new ActionError("error.iPassportPool.loginId"));
			}
			String lastLogin = request.getParameter("lastLogin");
			if(lastLogin==null || lastLogin.trim().length() == 0 ){
				iPassportPool.setLastLogin(null);
			}else{
				try{
					java.util.Calendar lastLoginCalendar = java.util.Calendar.getInstance();
					lastLoginCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastLogin));			
					iPassportPool.setLastLogin(lastLoginCalendar.getTime());
				}catch(Exception ex){}
			}
			String lastActive = request.getParameter("lastActive");
			if(lastActive==null || lastActive.trim().length() == 0 ){
				iPassportPool.setLastActive(null);
			}else{
				try{
					java.util.Calendar lastActiveCalendar = java.util.Calendar.getInstance();
					lastActiveCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(lastActive));			
					iPassportPool.setLastActive(lastActiveCalendar.getTime());
				}catch(Exception ex){}
			}


    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

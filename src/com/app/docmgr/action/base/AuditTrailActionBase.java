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


public class AuditTrailActionBase extends Action{
	private static Logger log = Logger.getLogger("com.app.docmgr.action.base.AuditTrailActionBase");	
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
    	
    	request.getSession().removeAttribute("auditTrail");
		
		String auditTrail_filter = request.getParameter("auditTrail_filter");
		//this for orderBy field ASC/DESC
		String auditTrail_fieldOrder = request.getParameter("auditTrail_fieldOrder");
		String auditTrail_orderType = request.getParameter("auditTrail_orderType");
		
		if(auditTrail_fieldOrder == null || auditTrail_fieldOrder.length() == 0) auditTrail_fieldOrder = null;
		if(auditTrail_orderType == null || auditTrail_orderType.length() == 0) auditTrail_orderType = null;
		request.getSession().setAttribute("auditTrail_fieldOrder", auditTrail_fieldOrder==null?"":auditTrail_fieldOrder);	 
		request.getSession().setAttribute("auditTrail_orderType", auditTrail_orderType==null?"":auditTrail_orderType);
		
		try{ 
		}catch(Exception ex){
		
		}
		StringBuffer auditTrail_filterSb = new StringBuffer();
		String param_auditTrail_auditTime_filter_start = "";
		if(request.getParameter("auditTrail_auditTime_filter_start")!=null){
			param_auditTrail_auditTime_filter_start = request.getParameter("auditTrail_auditTime_filter_start");
			if(param_auditTrail_auditTime_filter_start.length() > 0 ){
				try{
					java.util.Calendar param_auditTrail_auditTime_filter_start_cal = java.util.Calendar.getInstance();				
					param_auditTrail_auditTime_filter_start_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_auditTrail_auditTime_filter_start));
					String param_auditTrail_auditTime_filter_start_cal_val = param_auditTrail_auditTime_filter_start_cal.get(Calendar.YEAR)+"-"+(param_auditTrail_auditTime_filter_start_cal.get(Calendar.MONTH)+1)+"-"+param_auditTrail_auditTime_filter_start_cal.get(Calendar.DAY_OF_MONTH);
					auditTrail_filterSb.append("  AND auditTrail.auditTime >= '"+param_auditTrail_auditTime_filter_start_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("auditTrail_auditTime_filter_start", param_auditTrail_auditTime_filter_start);

		String param_auditTrail_auditTime_filter_end = "";
		if(request.getParameter("auditTrail_auditTime_filter_end")!=null){
			param_auditTrail_auditTime_filter_end = request.getParameter("auditTrail_auditTime_filter_end");
			if(param_auditTrail_auditTime_filter_end.length() > 0 ){
				try{
					java.util.Calendar param_auditTrail_auditTime_filter_end_cal = java.util.Calendar.getInstance();				
					param_auditTrail_auditTime_filter_end_cal.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(param_auditTrail_auditTime_filter_end));
					String param_auditTrail_auditTime_filter_end_cal_val = param_auditTrail_auditTime_filter_end_cal.get(Calendar.YEAR)+"-"+(param_auditTrail_auditTime_filter_end_cal.get(Calendar.MONTH)+1)+"-"+param_auditTrail_auditTime_filter_end_cal.get(Calendar.DAY_OF_MONTH);
					auditTrail_filterSb.append("  AND auditTrail.auditTime  <= '"+param_auditTrail_auditTime_filter_end_cal_val+"' ");
				}catch(Exception ex){}
			}
		}
		request.getSession().setAttribute("auditTrail_auditTime_filter_end", param_auditTrail_auditTime_filter_end);

		String param_auditTrail_entity_filter = "";
		if(request.getParameter("auditTrail_entity_filter")!=null){
			param_auditTrail_entity_filter = request.getParameter("auditTrail_entity_filter");
			if(param_auditTrail_entity_filter.length() > 0 ){				
				auditTrail_filterSb.append("  AND auditTrail.entity like '%"+param_auditTrail_entity_filter+"%' ");
			}
		}
		request.getSession().setAttribute("auditTrail_entity_filter", param_auditTrail_entity_filter);
		String param_auditTrail_doneBy_filter = "";
		if(request.getParameter("auditTrail_doneBy_filter")!=null){
			param_auditTrail_doneBy_filter = request.getParameter("auditTrail_doneBy_filter");
			if(param_auditTrail_doneBy_filter.length() > 0 ){				
				auditTrail_filterSb.append("  AND auditTrail.doneBy like '%"+param_auditTrail_doneBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("auditTrail_doneBy_filter", param_auditTrail_doneBy_filter);
		String param_auditTrail_sessionId_filter = "";
		if(request.getParameter("auditTrail_sessionId_filter")!=null){
			param_auditTrail_sessionId_filter = request.getParameter("auditTrail_sessionId_filter");
			if(param_auditTrail_sessionId_filter.length() > 0 ){				
				auditTrail_filterSb.append("  AND auditTrail.sessionId like '%"+param_auditTrail_sessionId_filter+"%' ");
			}
		}
		request.getSession().setAttribute("auditTrail_sessionId_filter", param_auditTrail_sessionId_filter);
		String param_auditTrail_approvedBy_filter = "";
		if(request.getParameter("auditTrail_approvedBy_filter")!=null){
			param_auditTrail_approvedBy_filter = request.getParameter("auditTrail_approvedBy_filter");
			if(param_auditTrail_approvedBy_filter.length() > 0 ){				
				auditTrail_filterSb.append("  AND auditTrail.approvedBy like '%"+param_auditTrail_approvedBy_filter+"%' ");
			}
		}
		request.getSession().setAttribute("auditTrail_approvedBy_filter", param_auditTrail_approvedBy_filter);
		String param_auditTrail_action_filter = "";
		if(request.getParameter("auditTrail_action_filter")!=null){
			param_auditTrail_action_filter = request.getParameter("auditTrail_action_filter");
			if(param_auditTrail_action_filter.length() > 0 ){				
				auditTrail_filterSb.append("  AND auditTrail.action like '%"+param_auditTrail_action_filter+"%' ");
			}
		}
		request.getSession().setAttribute("auditTrail_action_filter", param_auditTrail_action_filter);
		String param_auditTrail_description_filter = "";
		if(request.getParameter("auditTrail_description_filter")!=null){
			param_auditTrail_description_filter = request.getParameter("auditTrail_description_filter");
			if(param_auditTrail_description_filter.length() > 0 ){				
				auditTrail_filterSb.append("  AND auditTrail.description like '%"+param_auditTrail_description_filter+"%' ");
			}
		}
		request.getSession().setAttribute("auditTrail_description_filter", param_auditTrail_description_filter);
		
		if(auditTrail_fieldOrder!=null && auditTrail_orderType != null )auditTrail_filterSb.append(" ORDER BY "+auditTrail_fieldOrder+" "+auditTrail_orderType);
		
				
    	ActionForward forward = null;
		int start = 0;
		
		int count = ApplicationConstant.MAX_ITEM_PER_PAGE;
    	try{
    		AuditTrailService auditTrailService = AuditTrailService.getInstance();
    		try{
    			start = Integer.parseInt(request.getParameter("start"));
    		}catch(Exception ex){}
    		
    		PartialList auditTrailList = auditTrailService.getPartialList(auditTrail_filterSb.toString(), null, start, count);
    		String paging = ApplicationPaging.generatePaging(start, auditTrailList.getTotal());
    		String pagingItem = ApplicationPaging.generatePagingItem(start, auditTrailList.getTotal());
    		
    		request.setAttribute("paging", paging);
    		request.setAttribute("pagingItem", pagingItem);
    		request.setAttribute("auditTrailList", auditTrailList);	
    		forward = mapping.findForward("list");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }



    public ActionForward doListPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	request.getSession().removeAttribute("auditTrail");
		
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
    		AuditTrailService auditTrailService = AuditTrailService.getInstance();
    		List auditTrailList = auditTrailService.getList(parentFilter , null);
    		request.setAttribute("auditTrailList", auditTrailList);	
    		forward = mapping.findForward("list_popup");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return forward;
    }
    
    
    public ActionForward doDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    	    AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if (auditTrail == null){
	    		auditTrail = AuditTrailService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("auditTrail", auditTrail);
	    	}
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do");
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
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if (auditTrail == null){
	    		auditTrail = AuditTrailService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("auditTrail", auditTrail);
	    	}
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do");
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
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if(auditTrail == null) auditTrail = new AuditTrail();
    		

    		request.getSession().setAttribute("auditTrail", auditTrail);
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
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do?action=create&url=create_confirm");
    			return null;
    		}

    		loadParameter(request, form, auditTrail, errors);
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
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		AuditTrailService.getInstance().add(auditTrail);
    		request.getSession().removeAttribute("auditTrail");
    		response.sendRedirect("auditTrail.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("auditTrail.do?action=create");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}
    }

    public ActionForward doEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if (auditTrail == null){
	    		auditTrail = AuditTrailService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("auditTrail", auditTrail);
	    	}
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do");
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
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do?action=edit");
    			return null;
    		}

    		loadParameter(request, form, auditTrail, errors);
    		

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
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do?action=edit_confirm");
    		}
    		
    		AuditTrailService.getInstance().update(auditTrail);
    		request.getSession().removeAttribute("auditTrail");
    		response.sendRedirect("auditTrail.do?action=list");    		
    	}catch(Exception ex){
    		try{
    			response.sendRedirect("auditTrail.do?action=edit");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}
    		ex.printStackTrace();
    	}   
    }
    
   	public ActionForward doDeleteConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	ActionForward forward = null;
    	try{
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if (auditTrail == null){
	    		auditTrail = AuditTrailService.getInstance().get(new Long(request.getParameter("id")));
	    		request.getSession().setAttribute("auditTrail", auditTrail);
	    	}
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do?action=detail");
    			return null;
    		}

    		forward = mapping.findForward("delete_confirm");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
	    		response.sendRedirect("auditTrail.do?action=detail");
    			return null;
    		}catch(Exception rex){
    		}	
    	}
    	
    	return forward;
    }

    public void doDeleteOk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
       	try{
    		AuditTrail auditTrail = (AuditTrail) request.getSession().getAttribute("auditTrail");
    		if(auditTrail == null){
    			response.sendRedirect("auditTrail.do?action=delete_confirm");
    		}
    		AuditTrailService.getInstance().delete(auditTrail);
    		request.getSession().removeAttribute("auditTrail");
    		response.sendRedirect("auditTrail.do?action=list");    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			response.sendRedirect("auditTrail.do?action=delete_confirm");
    		}catch(Exception rex){
    			rex.printStackTrace();
    		}    		
    	}  
    }


    public void loadParameter(HttpServletRequest request, ActionForm form, AuditTrail auditTrail, ActionErrors errors){
    	try{    		
			String auditTime = request.getParameter("auditTime");
			if(auditTime==null || auditTime.trim().length() == 0 ){
				auditTrail.setAuditTime(null);
			}else{
				try{
					java.util.Calendar auditTimeCalendar = java.util.Calendar.getInstance();
					auditTimeCalendar.setTime(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(auditTime));			
					auditTrail.setAuditTime(auditTimeCalendar.getTime());
				}catch(Exception ex){}
			}
			if(auditTime==null || auditTime.trim().length() == 0 ){
				errors.add("auditTrail.auditTime", new ActionError("error.auditTrail.auditTime"));
			}
			String entity = request.getParameter("entity");
			auditTrail.setEntity(entity);
			String entityId = request.getParameter("entityId");
			try{
				auditTrail.setEntityId(new java.lang.Long(entityId));
			}catch(Exception ex){}
			String doneBy = request.getParameter("doneBy");
			auditTrail.setDoneBy(doneBy);
			if(doneBy==null || doneBy.trim().length() == 0 ){
				errors.add("auditTrail.doneBy", new ActionError("error.auditTrail.doneBy"));
			}
			String sessionId = request.getParameter("sessionId");
			auditTrail.setSessionId(sessionId);
			if(sessionId==null || sessionId.trim().length() == 0 ){
				errors.add("auditTrail.sessionId", new ActionError("error.auditTrail.sessionId"));
			}
			String approvedBy = request.getParameter("approvedBy");
			auditTrail.setApprovedBy(approvedBy);
			String action = request.getParameter("action");
			auditTrail.setAction(action);
			if(action==null || action.trim().length() == 0 ){
				errors.add("auditTrail.action", new ActionError("error.auditTrail.action"));
			}
			String description = request.getParameter("description");
			auditTrail.setDescription(description);


    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
}

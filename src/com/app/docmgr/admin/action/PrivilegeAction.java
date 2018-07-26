package com.app.docmgr.admin.action;

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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.*;

import com.app.docmgr.model.*;
import com.app.docmgr.admin.action.*;
import com.app.docmgr.service.*;

import com.app.docmgr.action.base.PrivilegeActionBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class PrivilegeAction extends PrivilegeActionBase{
	private static Logger log = Logger.getLogger("com.app.docmgr.admin.action.PrivilegeAction");	
    public  static final String allowableAction="list:detail:create:edit:delete:approve:activate:reject:pending:process:close:cancel:block";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ActionForward forward = null;
    	String action = request.getParameter("action");
    	if (action==null || action.length()==0) action="list";
    	String actionRoot=action;    	
    	if(action.indexOf("_")>0){
    		actionRoot=action.substring(0,action.indexOf("_"));
    	}
    	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    	response.setHeader("Pragma","no-cache"); //HTTP 1.0
    	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
    	User loginUser=(User) request.getSession().getAttribute("login.user");
    	if (loginUser==null){
    		request.getSession().setAttribute("url",request.getRequestURI());
    		forward = mapping.findForward("login");
    		return forward;
    	}
    	super._doneBy=loginUser.getLoginName();
		if (allowableAction.contains(actionRoot)) { 
	    	if("list".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_LIST")) forward = doList(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("list_popup".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_LIST")) forward = doListPopup(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("detail".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_DETAIL")) forward = doDetail(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("detail_popup".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_DETAIL")) forward = doDetailPopup(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("create".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_CREATE")) forward = doCreate(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("create_confirm".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_CREATE")) forward = doCreateConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("create_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_CREATE")) doCreateOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("edit".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_EDIT")) forward = doEdit(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("edit_confirm".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_EDIT")) forward = doEditConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("edit_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_EDIT")) doEditOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("delete_confirm".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_DELETE")) forward = doDeleteConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("delete_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_DELETE")) doDeleteOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
			}
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}
    	
		return forward;
    }	    

}

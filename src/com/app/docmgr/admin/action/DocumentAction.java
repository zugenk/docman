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

import com.app.docmgr.action.base.DocumentActionBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class DocumentAction extends DocumentActionBase{
	private static Logger log = Logger.getLogger("com.app.docmgr.admin.action.DocumentAction");	
    public  static final String allowableAction="list:detail:create:edit:delete:approve:reject:pending:process:close:cancel";

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
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_LIST")) forward = doList(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("list_popup".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_LIST")) forward = doListPopup(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("detail".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_DETAIL")) forward = doDetail(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("detail_popup".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_DETAIL")) forward = doDetailPopup(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("create".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CREATE")) forward = doCreate(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("create_confirm".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CREATE")) forward = doCreateConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("create_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CREATE")) doCreateOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("edit".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_EDIT")) forward = doEdit(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("edit_confirm".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_EDIT")) forward = doEditConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("edit_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_EDIT")) doEditOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("delete_confirm".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_DELETE")) forward = doDeleteConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("delete_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_DELETE")) doDeleteOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("submit_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_SUBMIT")) forward = doSubmitConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("submit_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_SUBMIT")) doSubmitOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("approve_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_APPROVE")) forward = doApproveConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("approve_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_APPROVE")) doApproveOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("reject_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_REJECT")) forward = doRejectConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("reject_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_REJECT")) doRejectOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("pending_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_PENDING")) forward = doPendingConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("pending_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_PENDING")) doPendingOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("process_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_PROCESS")) forward = doProcessConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("process_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_PROCESS")) doProcessOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("close_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CLOSE")) forward = doCloseConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("close_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CLOSE")) doCloseOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("remove_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_REMOVE")) forward = doRemoveConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("remove_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_REMOVE")) doRemoveOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
    		}else if("cancel_confirm".equalsIgnoreCase(action)){
    			if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CANCEL")) forward = doCancelConfirm(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
	    	}else if("cancel_ok".equalsIgnoreCase(action)){
	    		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CANCEL")) doCancelOk(mapping, form, request, response);
	    		else 	forward = mapping.findForward("not_authorized");
			}
    	}else{
    		forward = mapping.findForward("not_authorized");
    	}
    	
		return forward;
    }	    

}

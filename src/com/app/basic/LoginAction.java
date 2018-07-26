package com.app.basic;

import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.*;

import com.app.shared.ApplicationConstant;
import com.app.shared.ApplicationFactory;


import com.app.docmgr.model.*;
import com.app.docmgr.service.*;


/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */


public class LoginAction extends Action{
	private static Logger log = Logger.getLogger("com.app.basic.LoginAction");	

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        User loginUser = null;
		
        String action = request.getParameter("action");
        if("logout".equalsIgnoreCase(action)){
        	request.getSession().invalidate();
        	response.sendRedirect("login.jsp");
        	return null;
        }
        
        try {
        	String id = request.getParameter("username");
        	String pwd = request.getParameter("userpass");
        	
			UserService userService = UserService.getInstance();
			boolean next = true;
			if(id==null || id.trim().length() == 0 ){
				errors.add("login.name", new ActionError("error.login.name"));
				next=false;
			}

			if(pwd==null || pwd.trim().length() == 0 ){
				errors.add("login.password", new ActionError("error.login.password"));
				next=false;
			}
			User user = null;
			if(next){
				 //List userList = userService.getList(" AND lower(user.loginName) = lower('"+id+"') ", null); //AND appUser.loginPassword='"+pwd+"' 
				 List userList = userService.getList(" AND user.loginName = '"+id+"' ", null); 
				 if(userList.size()>0) {
				 	user = (User) userList.iterator().next();
				 	String encriptedPassword=ApplicationFactory.encrypt(pwd);
				 	//String encriptedPassword=pwd;
				 	if(!encriptedPassword.equals(user.getLoginPassword())){
				 		errors.add("login.password", new ActionError("error.login.password"));
				 	} else  request.getSession().setAttribute("login.user", user);
				 }else{
				  	errors.add("login.failed", new ActionError("error.login.failed"));
				 }
			}	
			saveErrors(request, errors);
			if(errors.isEmpty()){
				forward = mapping.findForward("success");
			}else{
				forward = mapping.findForward("failure");
			}	
		}catch(Exception ex){}
        return forward;
    }
}
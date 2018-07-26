package com.app.shared;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

import javax.servlet.http.*;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import org.apache.log4j.*;


public class ServerInitiationServlet extends HttpServlet {
	private boolean inited=false;
	private static Logger log = Logger.getLogger("com.app.shared.ServerInitiationServlet");	

	public void init() throws ServletException {
		if (inited) return;
		String context=getServletContext().getRealPath("/");
		ApplicationConstant.contextRealPath=context;
		log.info("Server Initialization Servlet inited, contextPath="+ApplicationConstant.contextRealPath);

		ApplicationConstant.defaultTemplate= getInitParameter("default-template");
		if (ApplicationConstant.defaultTemplate == null) ApplicationConstant.defaultTemplate="default";
				  
	
		
		super.init();
		inited=true;
		log.debug("Initialization ApplicationConstant.contextRealPath="+ApplicationConstant.contextRealPath);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		log.debug("Server Initialization Servlet: Test Sending eMail !");
		List<String> toAddr=new LinkedList<String>();
		toAddr.add(ApplicationFactory.getAdminEmail());
		toAddr.add(ApplicationFactory.getCustomerServiceEmail());
		ApplicationFactory.sendMail(ApplicationFactory.getAdminEmail(),toAddr , "Test SendMail Subject", "Test Send Message from System");
	}

}

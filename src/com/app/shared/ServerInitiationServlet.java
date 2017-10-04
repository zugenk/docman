package com.app.shared;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

import javax.servlet.http.*;
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
				  
		/*
		ApplicationConstant.log4jfile = getInitParameter("log4j-init-file");
		if (ApplicationConstant.log4jfile == null) ApplicationConstant.log4jfile="/WEB-INF/log4j.lcf";
		PropertyConfigurator.configure(context+ApplicationConstant.log4jfile);
		log.debug("log4jfile=("+context+ApplicationConstant.log4jfile+")");
		*/
		
		/*
		ApplicationConstant.msgPropertyName = getInitParameter("message-resource-file");
		log.debug("ApplicationConstant.msgPropertyName= "+ApplicationConstant.msgPropertyName);
		if (ApplicationConstant.msgPropertyName==null) ApplicationConstant.msgPropertyName="/WEB-INF/classes/MessageResources";
		ApplicationConstant.corebankConf= getInitParameter("core-conf-file");
		log.debug("ApplicationConstant.tcClientConf= "+ApplicationConstant.corebankConf);
		if (ApplicationConstant.corebankConf==null) ApplicationConstant.corebankConf="/WEB-INF/conf/channels.xml";
		*/
		
		super.init();
		inited=true;
		log.debug("Initialization ApplicationConstant.contextRealPath="+ApplicationConstant.contextRealPath);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		log.debug("Server Initialization Servlet: do Get !");
	}

}

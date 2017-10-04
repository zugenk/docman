<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<%
	if(request.getSession().getAttribute("login.user") == null){
		try{
			response.sendRedirect("login.jsp");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
%>

<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>


<html:html>
<HEAD>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">

<TITLE></TITLE>
</HEAD>

<BODY>

<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<logic:messagesPresent property="logout.failed">
	<DIV class=inputlabel><font color="red"><html:errors property="logout.failed"/></font><br><br></div>
</logic:messagesPresent>

</BODY>
</html:html>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>

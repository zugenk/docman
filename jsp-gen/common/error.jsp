<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html>
<%
	String errorcode = (String) request.getSession().getAttribute("errorCode");
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="page.default.error.title"/></title>
<LINK href="../template/<%=currentTemplate%>/css/theme.css" type="text/css rel=stylesheet" />
<LINK href="../template/<%=currentTemplate%>/css/template_css.css" type="text/css rel=stylesheet" />
</head>
<body>
<br><br><br>
<table align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="titleHeader">
			<bean:message key="page.default.error.title"/>
		</td>
	</tr>
	<tr>
		<td align="left">
			<font color="red"><%=ApplicationFactory.showErrorMessage(errorcode,null,null) %></font>
		</td>
	</tr>
	<tr>
		<td align="center"><bean:message key="page.default.error.note"/></td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
		<tr>
		<td align="center">
			<p><a href="login.do"><bean:message key="page.default.error.relogin"/></a>&nbsp;&nbsp;<a href="index.jsp"><bean:message key="page.default.error.goHome"/></a></p>
		</td>
	</tr>
	
</table>
</body>
</html>
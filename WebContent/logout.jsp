<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-c" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.app.shared.*" %>
<html>
<head>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
</head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="page.default.logout.title"/></title>
<LINK href="template/<%=currentTemplate%>/css/theme.css" type="text/css rel=stylesheet" />
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type="text/css rel=stylesheet" />
<body >
<br><br><br>
<table align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" class="titleHeader">
			<bean:message key="page.default.logout.title"/>
		</td>
	</tr>
	<tr>
		<td align="center" >
			<h1><bean:message key="page.default.logout.note"/></h1>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td align="center">
			<p><a href="login.do"><bean:message key="page.default.logout.relogin"/></a></p>
		</td>
	</tr>
</table>

</body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.LoginHistory.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 06-10-2017 22:19:39
 */
-->
</HEAD>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<BODY>
<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="loginHistory" action="loginHistory.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.LoginHistory.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.loginTime.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="loginHistory" property="loginTime" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.lastAccess.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="loginHistory" property="lastAccess" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.logoutTime.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="loginHistory" property="logoutTime" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.sessionId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="loginHistory" property="sessionId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="loginHistory" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.user.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="loginHistory"	property="user">			
					<bean:write name="loginHistory" property="user.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="loginHistory"	property="status">			
					<bean:write name="loginHistory" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<input type="button" value="<bean:message key="button.close" />" onclick="javascript:window.close();"/>
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->		

</form>
</BODY>
</html:html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.Status.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
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
<LINK href="../template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="../template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="status" action="status.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Status.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.type.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="type"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.state.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="state"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="description"/></td>
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

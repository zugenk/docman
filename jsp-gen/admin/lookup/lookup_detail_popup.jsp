<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.Lookup.DetailPopup"/></TITLE>
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

<form name="lookup" action="lookup.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Lookup.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.type.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="type"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.priority.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="priority"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.shortname.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="shortname"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.filter.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="filter"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="lookup"	property="status">			
					<bean:write name="lookup" property="status.id"/>
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

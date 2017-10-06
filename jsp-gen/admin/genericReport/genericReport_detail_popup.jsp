<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.GenericReport.DetailPopup"/></TITLE>
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
<LINK href="../template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="../template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="genericReport" action="genericReport.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.GenericReport.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="genericReport" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.reportFields.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="genericReport" property="reportFields"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.searchFields.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="genericReport" property="searchFields"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.reportSql.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="genericReport" property="reportSql"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="genericReport" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.columnAction.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="genericReport" property="columnAction"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="genericReport"	property="status">			
					<bean:write name="genericReport" property="status.id"/>
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

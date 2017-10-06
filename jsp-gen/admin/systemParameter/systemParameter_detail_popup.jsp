<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.SystemParameter.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
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

<form name="systemParameter" action="systemParameter.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.SystemParameter.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.vgroup.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="vgroup"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.parameter.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="parameter"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.svalue.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="svalue"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameter" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameter" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameter"	property="status">			
					<bean:write name="systemParameter" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.userLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameter"	property="userLevel">			
					<bean:write name="systemParameter" property="userLevel.id"/>
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

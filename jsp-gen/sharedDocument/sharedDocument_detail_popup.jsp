<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.SharedDocument.DetailPopup"/></TITLE>
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
<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="sharedDocument" action="sharedDocument.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.SharedDocument.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.grantAction.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="grantAction"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="sharedDocument" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.document.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="document">			
					<bean:write name="sharedDocument" property="document.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.targetUser.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="targetUser">			
					<bean:write name="sharedDocument" property="targetUser.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.targetOrganization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="targetOrganization">			
					<bean:write name="sharedDocument" property="targetOrganization.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="status">			
					<bean:write name="sharedDocument" property="status.id"/>
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

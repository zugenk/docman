<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.Document.DetailPopup"/></TITLE>
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

<form name="document" action="document.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Document.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.contentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="contentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.repositoryId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="repositoryId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentVersion.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentVersion"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.tag.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="tag"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.priority.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="priority"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="document" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="document" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="securityLevel">			
					<bean:write name="document" property="securityLevel.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="owner">			
					<bean:write name="document" property="owner.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="status">			
					<bean:write name="document" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.folder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="folder">			
					<bean:write name="document" property="folder.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="parent">			
					<bean:write name="document" property="parent.id"/>
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

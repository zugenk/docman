<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<HEAD>
<TITLE><bean:message key="page.Document.RemoveConfirm"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
 */
-->
</HEAD>

<BODY>

<form name="document" action="document.do" method="POST">
	<input type="hidden" name="action"/>
	<%@ include file="../common/header.jsp" %>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Document.RemoveConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.name.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.documentType.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentType"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.contentType.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="contentType"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.documentNumber.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentNumber"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.repositoryId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="repositoryId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.documentVersion.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentVersion"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.description.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.priority.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="priority"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="document" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="document" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.securityLevel.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document" property="securityLevel">			
					<bean:write name="document" property="securityLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.owner.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document" property="owner">			
					<bean:write name="document" property="owner.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document" property="status">			
					<bean:write name="document" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.folder.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document" property="folder">			
					<bean:write name="document" property="folder.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.parent.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document" property="parent">			
					<bean:write name="document" property="parent.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='remove_ok'" />
			    &nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='detail';this.form.submit()" />
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->		
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

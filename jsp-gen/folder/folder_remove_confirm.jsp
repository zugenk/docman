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
<TITLE><bean:message key="page.Folder.RemoveConfirm"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 05-11-2017 15:05:21
 */
-->
</HEAD>

<BODY>

<form name="folder" action="folder.do" method="POST">
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
				<bean:message key="page.Folder.RemoveConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.code.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.name.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.folderRepoId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="folderRepoId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="folder" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="folder" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.folderType.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder" property="folderType">			
					<bean:write name="folder" property="folderType.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder" property="status">			
					<bean:write name="folder" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.parentFolder.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder" property="parentFolder">			
					<bean:write name="folder" property="parentFolder.name"/>
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

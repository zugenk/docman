<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>
<%@ page import="com.app.docmgr.model.User" %>
<%@ page import="com.app.docmgr.service.UserService" %>
<%@ page import="java.util.List" %>

<html:html>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
	List privilegeList = (List) request.getSession().getAttribute("privilegeList");
%>
<HEAD>
<TITLE><bean:message key="page.Folder.Detail"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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
				<bean:message key="page.Folder.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="folder" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="folder" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.folderType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder"	property="folderType">			
					<bean:write name="folder" property="folderType.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.parentFolder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder"	property="parentFolder">			
					<bean:write name="folder" property="parentFolder.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.FolderAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("FOLDER_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.FolderAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("FOLDER_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='list';this.form.submit()" />
			</td>
		</tr>
	</table>

<!-- set one to many start -->
<!-- end one to many start -->		
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

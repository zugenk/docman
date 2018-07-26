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
	User loginUser=(User) request.getSession().getAttribute("login.user");
%>
<HEAD>
<TITLE><bean:message key="page.Folder.Detail"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
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
			<td width="150"><b><bean:message key="folder.folderRepoId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="folder" property="folderRepoId"/></td>
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
			<td width="150"><b><bean:message key="folder.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder"	property="status">			
					<bean:write name="folder" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="folder"	property="parent">			
					<bean:write name="folder" property="parent.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("edit")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("delete")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("submit")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("approve")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("reject")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("pending")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("process")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("activate")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("close")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("archive")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("remove")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("block")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("cancel")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_CANCEL")) { %>
				<input type="button" value="<bean:message key="button.cancel"/>" onclick="this.form.action.value='cancel_confirm';this.form.submit()" />
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

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
<TITLE><bean:message key="page.LoginHistory.Detail"/></TITLE>
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
<form name="loginHistory" action="loginHistory.do" method="POST">
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
				<bean:message key="page.LoginHistory.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.loginTime.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="loginHistory" property="loginTime" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.lastAccess.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="loginHistory" property="lastAccess" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.logoutTime.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="loginHistory" property="logoutTime" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.sessionId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="loginHistory" property="sessionId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="loginHistory" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.user.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="loginHistory"	property="user">			
					<bean:write name="loginHistory" property="user.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="loginHistory"	property="status">			
					<bean:write name="loginHistory" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("edit")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("delete")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("submit")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("approve")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("reject")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("pending")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("process")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("activate")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("close")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("archive")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("remove")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("block")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("cancel")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_CANCEL")) { %>
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

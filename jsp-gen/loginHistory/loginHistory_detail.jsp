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
<TITLE><bean:message key="page.LoginHistory.Detail"/></TITLE>
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
				if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("LOGIN_HISTORY_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("LOGIN_HISTORY_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("LOGIN_HISTORY_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("LOGIN_HISTORY_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("LOGIN_HISTORY_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("LOGIN_HISTORY_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("LOGIN_HISTORY_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("LOGIN_HISTORY_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("LOGIN_HISTORY_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LoginHistoryAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("LOGIN_HISTORY_CANCEL")) { %>
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
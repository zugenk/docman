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
<TITLE><bean:message key="page.UserHistory.Detail"/></TITLE>
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
<form name="userHistory" action="userHistory.do" method="POST">
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
				<bean:message key="page.UserHistory.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.historyDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="historyDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.historyBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="historyBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.auditTrailId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="auditTrailId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.userId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="userId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.loginName.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="loginName"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.loginPassword.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="loginPassword"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.pinCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="pinCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.picture.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="picture"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.language.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="language"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.title.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="title"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.alias.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="alias"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.email.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="email"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.fullName.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="fullName"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.homePhoneNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="homePhoneNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.mobilePhoneNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="mobilePhoneNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.employeeNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="employeeNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.firstLogin.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="firstLogin"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPasswordUpdate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="lastPasswordUpdate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPinCodeUpdate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="lastPinCodeUpdate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPassword.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="lastPassword"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPinCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="lastPinCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.loginFailed.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="loginFailed"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.maxRelease.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="maxRelease"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastReleasedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="lastReleasedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastActive.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="userHistory" property="lastActive" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.sessionCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="sessionCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.IPassport.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="userHistory" property="IPassport"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.userLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="userLevel">			
					<bean:write name="userHistory" property="userLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.position.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="position">			
					<bean:write name="userHistory" property="position.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="status">			
					<bean:write name="userHistory" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.organization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="organization">			
					<bean:write name="userHistory" property="organization.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="securityLevel">			
					<bean:write name="userHistory" property="securityLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("edit")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("delete")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("submit")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("approve")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("reject")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("pending")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("process")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("activate")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("close")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("archive")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("remove")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("block")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserHistoryAction.allowableAction.contains("cancel")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_HISTORY_CANCEL")) { %>
				<input type="button" value="<bean:message key="button.cancel"/>" onclick="this.form.action.value='cancel_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='list';this.form.submit()" />
			</td>
		</tr>
	</table>
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td><bean:message key="userHistory.roles.key"/></td>
		</tr>
		<tr>
			<td align=center>
				<div style="overflow:auto; height:110" >
				<table width="90%" >
					<logic:iterate id="role" name="roleSet" type="com.app.docmgr.model.Role">
						<tr><td><bean:write name="role" property="name"/></td></tr>
					</logic:iterate>	
				</table>
				</div>
			</td>
		</tr>
	</table>
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td><bean:message key="userHistory.favoriteTopic.key"/></td>
		</tr>
		<tr>
			<td align=center>
				<div style="overflow:auto; height:110" >
				<table width="90%" >
					<logic:iterate id="topic" name="topicSet" type="com.app.docmgr.model.Topic">
						<tr><td><bean:write name="topic" property="name"/></td></tr>
					</logic:iterate>	
				</table>
				</div>
			</td>
		</tr>
	</table>

<!-- set one to many start -->
<!-- end one to many start -->		
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

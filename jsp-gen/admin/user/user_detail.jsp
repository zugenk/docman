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
<TITLE><bean:message key="page.User.Detail"/></TITLE>
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
<form name="user" action="user.do" method="POST">
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
				<bean:message key="page.User.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.loginName.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="loginName"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.loginPassword.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="loginPassword"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.pinCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="pinCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.mobileNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="mobileNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.picture.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="picture"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.language.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="language"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.title.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="title"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.alias.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="alias"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.email.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="email"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.fullName.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="fullName"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.homePhoneNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="homePhoneNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.mobilePhoneNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="mobilePhoneNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.employeeNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="employeeNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.firstLogin.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="firstLogin"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastPasswordUpdate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastPasswordUpdate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastPinCodeUpdate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastPinCodeUpdate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastPassword.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="lastPassword"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastPinCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="lastPinCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.loginFailed.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="loginFailed"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.maxRelease.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="maxRelease"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastReleasedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastReleasedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.lastActive.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastActive" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.sessionCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="sessionCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.IPassport.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="IPassport"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.userLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="userLevel">			
					<bean:write name="user" property="userLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.position.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="position">			
					<bean:write name="user" property="position.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="status">			
					<bean:write name="user" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.organization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="organization">			
					<bean:write name="user" property="organization.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="securityLevel">			
					<bean:write name="user" property="securityLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("edit")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("delete")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("submit")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("approve")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("reject")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("pending")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("process")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("activate")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("close")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("archive")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("remove")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("block")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("cancel")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_CANCEL")) { %>
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
			<td><bean:message key="user.roles.key"/></td>
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
			<td><bean:message key="user.favoriteTopics.key"/></td>
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

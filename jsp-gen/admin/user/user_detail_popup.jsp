<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.User.DetailPopup"/></TITLE>
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

<form name="user" action="user.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.User.DetailPopup"/>
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
					<bean:write name="user" property="userLevel.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.position.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="position">			
					<bean:write name="user" property="position.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="status">			
					<bean:write name="user" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.organization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="organization">			
					<bean:write name="user" property="organization.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user"	property="securityLevel">			
					<bean:write name="user" property="securityLevel.id"/>
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

</form>
</BODY>
</html:html>

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
<TITLE><bean:message key="page.User.ProcessConfirm"/></TITLE>
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
				<bean:message key="page.User.ProcessConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.loginName.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="loginName"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.loginPassword.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="loginPassword"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.pinCode.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="pinCode"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.mobileNumber.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="mobileNumber"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.language.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="language"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.title.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="title"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.name.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.email.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="email"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.fullName.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="fullName"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.homePhoneNumber.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="homePhoneNumber"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.mobilePhoneNumber.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="mobilePhoneNumber"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.employeeNumber.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="employeeNumber"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.firstLogin.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="firstLogin"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastPasswordUpdate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastPasswordUpdate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastPinCodeUpdate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastPinCodeUpdate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastPassword.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="lastPassword"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastPinCode.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="lastPinCode"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.loginFailed.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="loginFailed"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.maxRelease.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="maxRelease"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastReleasedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastReleasedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.lastActive.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="user" property="lastActive" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.sessionCode.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="sessionCode"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.IPassport.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="user" property="IPassport"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.userLevel.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user" property="userLevel">			
					<bean:write name="user" property="userLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user" property="status">			
					<bean:write name="user" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.organization.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="user" property="organization">			
					<bean:write name="user" property="organization.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='process_ok'" />
			    &nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='detail';this.form.submit()" />
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
<!-- set one to many start -->
<!-- end one to many start -->		
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

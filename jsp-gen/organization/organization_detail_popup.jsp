<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.Organization.DetailPopup"/></TITLE>
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
<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="organization" action="organization.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Organization.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.mnemonic.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="mnemonic"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.address.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="address"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.mailingList.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="mailingList"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="organization" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="organization" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.filterCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="organization" property="filterCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="organization"	property="securityLevel">			
					<bean:write name="organization" property="securityLevel.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="organization"	property="parent">			
					<bean:write name="organization" property="parent.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.organizationType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="organization"	property="organizationType">			
					<bean:write name="organization" property="organizationType.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="organization"	property="status">			
					<bean:write name="organization" property="status.id"/>
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
<!-- set one to many start -->
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="30"><bean:message key="organization.members.key"/></td>
		</tr>

		<tr class="title">
			<td><bean:message key="user.loginName.key"/></td>
			<td><bean:message key="user.loginPassword.key"/></td>
			<td><bean:message key="user.pinCode.key"/></td>
			<td><bean:message key="user.mobileNumber.key"/></td>
			<td><bean:message key="user.picture.key"/></td>
			<td><bean:message key="user.language.key"/></td>
			<td><bean:message key="user.title.key"/></td>
			<td><bean:message key="user.name.key"/></td>
			<td><bean:message key="user.alias.key"/></td>
			<td><bean:message key="user.email.key"/></td>
			<td><bean:message key="user.fullName.key"/></td>
			<td><bean:message key="user.homePhoneNumber.key"/></td>
			<td><bean:message key="user.mobilePhoneNumber.key"/></td>
			<td><bean:message key="user.employeeNumber.key"/></td>
   <% /* %> 			<td><bean:message key="user.createdDate.key"/></td>
   <% */ %>    <% /* %> 			<td><bean:message key="user.createdBy.key"/></td>
   <% */ %>    <% /* %> 			<td><bean:message key="user.lastUpdatedDate.key"/></td>
   <% */ %>    <% /* %> 			<td><bean:message key="user.lastUpdatedBy.key"/></td>
   <% */ %> 			<td><bean:message key="user.firstLogin.key"/></td>
			<td><bean:message key="user.lastPasswordUpdate.key"/></td>
			<td><bean:message key="user.lastPinCodeUpdate.key"/></td>
			<td><bean:message key="user.lastPassword.key"/></td>
			<td><bean:message key="user.lastPinCode.key"/></td>
			<td><bean:message key="user.loginFailed.key"/></td>
			<td><bean:message key="user.maxRelease.key"/></td>
			<td><bean:message key="user.lastReleasedDate.key"/></td>
			<td><bean:message key="user.lastActive.key"/></td>
			<td><bean:message key="user.sessionCode.key"/></td>
			<td><bean:message key="user.IPassport.key"/></td>
			<td><bean:message key="user.userLevel.key"/></td>
			<td><bean:message key="user.position.key"/></td>
  <% /* %> 			<td><bean:message key="user.status.key"/></td>
  <% */ %> 			<td><bean:message key="user.organization.key"/></td>
			<td><bean:message key="user.securityLevel.key"/></td>
		</tr>
	<logic:iterate id="element_user" name="userSet" type="com.app.docmgr.model.User">		
		<tr>
			<td><bean:write name="element_user" property="loginName"/></td>
			<td><bean:write name="element_user" property="loginPassword"/></td>
			<td><bean:write name="element_user" property="pinCode"/></td>
			<td><bean:write name="element_user" property="mobileNumber"/></td>
			<td><bean:write name="element_user" property="picture"/></td>
			<td><bean:write name="element_user" property="language"/></td>
			<td><bean:write name="element_user" property="title"/></td>
			<td><bean:write name="element_user" property="name"/></td>
			<td><bean:write name="element_user" property="alias"/></td>
			<td><bean:write name="element_user" property="email"/></td>
			<td><bean:write name="element_user" property="fullName"/></td>
			<td><bean:write name="element_user" property="homePhoneNumber"/></td>
			<td><bean:write name="element_user" property="mobilePhoneNumber"/></td>
			<td><bean:write name="element_user" property="employeeNumber"/></td>
  <% /* %> 			<td><bean:write name="element_user" property="createdDate"/></td>
  <% */ %>   <% /* %> 			<td><bean:write name="element_user" property="createdBy"/></td>
  <% */ %>   <% /* %> 			<td><bean:write name="element_user" property="lastUpdatedDate"/></td>
  <% */ %>   <% /* %> 			<td><bean:write name="element_user" property="lastUpdatedBy"/></td>
  <% */ %> 			<td><bean:write name="element_user" property="firstLogin"/></td>
			<td><bean:write name="element_user" property="lastPasswordUpdate"/></td>
			<td><bean:write name="element_user" property="lastPinCodeUpdate"/></td>
			<td><bean:write name="element_user" property="lastPassword"/></td>
			<td><bean:write name="element_user" property="lastPinCode"/></td>
			<td><bean:write name="element_user" property="loginFailed"/></td>
			<td><bean:write name="element_user" property="maxRelease"/></td>
			<td><bean:write name="element_user" property="lastReleasedDate"/></td>
			<td><bean:write name="element_user" property="lastActive"/></td>
			<td><bean:write name="element_user" property="sessionCode"/></td>
			<td><bean:write name="element_user" property="IPassport"/></td>
			<td>
				<logic:notEmpty name="element_user" property="userLevel">
					<bean:write name="element_user" property="userLevel.id"/>
				</logic:notEmpty>				
			</td>
			<td>
				<logic:notEmpty name="element_user" property="position">
					<bean:write name="element_user" property="position.id"/>
				</logic:notEmpty>				
			</td>
  <% /* %> 			<td>
				<logic:notEmpty name="element_user" property="status">
					<bean:write name="element_user" property="status.id"/>
				</logic:notEmpty>				
			</td>
  <% */ %> 			<td>
				<logic:notEmpty name="element_user" property="organization">
					<bean:write name="element_user" property="organization.id"/>
				</logic:notEmpty>				
			</td>
			<td>
				<logic:notEmpty name="element_user" property="securityLevel">
					<bean:write name="element_user" property="securityLevel.id"/>
				</logic:notEmpty>				
			</td>
		</tr>						
	</logic:iterate>
	</table>
<!-- end one to many start -->		

</form>
</BODY>
</html:html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.UserHistory.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
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

<form name="userHistory" action="userHistory.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.UserHistory.DetailPopup"/>
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
					<bean:write name="userHistory" property="userLevel.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.position.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="position">			
					<bean:write name="userHistory" property="position.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="status">			
					<bean:write name="userHistory" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.organization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="organization">			
					<bean:write name="userHistory" property="organization.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="userHistory"	property="securityLevel">			
					<bean:write name="userHistory" property="securityLevel.id"/>
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

</form>
</BODY>
</html:html>

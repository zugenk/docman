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
<TITLE><bean:message key="page.Organization.Edit"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 06-10-2017 22:19:39
 */
-->
</HEAD>
<% int i=0; %>

<BODY onload="initOption()">
<script language="javascript" src="template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

		 function doAddUser(){
		 	document.forms.organization.action.value="add_User";
		 	document.forms.organization.submit();
		 }
		 
		 function doEditUser(sequence){
		 	document.forms.organization.action.value="edit_User";
		 	document.forms.organization.sequence.value=sequence;
		 	document.forms.organization.submit();		 	
		 }

		 function doDeleteUser(sequence){
		 	document.forms.organization.action.value="delete_User";
		 	document.forms.organization.sequence.value=sequence;
		 	document.forms.organization.submit();		 	
		 }
	-->
</script>

<form name="organization" action="organization.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="organization" property="id"/>
	<input type="hidden" name="sequence"/>
	<input type="hidden" name="redirect" value="edit"/>
	
	<%@ include file="../common/header.jsp" %>
	<table border="0" align="center" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				<bean:message key="page.Organization.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="organization.code.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="code"/>
			</td>
		</tr>
		<logic:messagesPresent property="organization.code">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="organization.code"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="organization.mnemonic.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="mnemonic"/>
			</td>
		</tr>
		<logic:messagesPresent property="organization.mnemonic">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="organization.mnemonic"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="organization.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="organization.name">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="organization.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="organization.address.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="address"/>
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="organization.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name="organization" property="createdDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="createdDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="organization.createdDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="organization.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="organization.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="organization.createdBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="organization.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="organization.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name="organization" property="lastUpdatedDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastUpdatedDate_cal" style="display: none"></div>
			</td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="organization.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="lastUpdatedBy"/>
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="organization.filterCode.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="organization" property="filterCode"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="organization.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="organization" property="parent" style="width:135"  value="${organization.parent.id}">
					<option value=""></option>
					<html:options collection="parentList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.save"/>" onclick="this.form.action.value='edit_confirm'" />
			    &nbsp;
			    <html:reset>
		        	<bean:message key="button.reset"/>
		      	</html:reset>
				&nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='detail';this.form.submit()" />
		    </td>
		</tr>
	</table>
<!-- set one to many start -->
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="28"><bean:message key="organization.members.key"/></td>
		</tr>
		
		<tr class="title">
			<td><b><bean:message key="user.loginName.key"/> <font color="#FF0000">*</font></b></td>
			<td><b><bean:message key="user.loginPassword.key"/> <font color="#FF0000">*</font></b></td>
			<td><b><bean:message key="user.pinCode.key"/></b></td>
			<td><b><bean:message key="user.mobileNumber.key"/></b></td>
			<td><b><bean:message key="user.language.key"/></b></td>
			<td><b><bean:message key="user.title.key"/></b></td>
			<td><b><bean:message key="user.name.key"/> <font color="#FF0000">*</font></b></td>
			<td><b><bean:message key="user.email.key"/></b></td>
			<td><b><bean:message key="user.fullName.key"/></b></td>
			<td><b><bean:message key="user.homePhoneNumber.key"/></b></td>
			<td><b><bean:message key="user.mobilePhoneNumber.key"/></b></td>
			<td><b><bean:message key="user.employeeNumber.key"/></b></td>
   <% /* %> 			<td><b><bean:message key="user.createdDate.key"/> <font color="#FF0000">*</font></b></td>
   <% */ %>    <% /* %> 			<td><b><bean:message key="user.createdBy.key"/> <font color="#FF0000">*</font></b></td>
   <% */ %>    <% /* %> 			<td><b><bean:message key="user.lastUpdatedDate.key"/></b></td>
   <% */ %>    <% /* %> 			<td><b><bean:message key="user.lastUpdatedBy.key"/></b></td>
   <% */ %> 			<td><b><bean:message key="user.firstLogin.key"/></b></td>
			<td><b><bean:message key="user.lastPasswordUpdate.key"/></b></td>
			<td><b><bean:message key="user.lastPinCodeUpdate.key"/></b></td>
			<td><b><bean:message key="user.lastPassword.key"/></b></td>
			<td><b><bean:message key="user.lastPinCode.key"/></b></td>
			<td><b><bean:message key="user.loginFailed.key"/></b></td>
			<td><b><bean:message key="user.maxRelease.key"/></b></td>
			<td><b><bean:message key="user.lastReleasedDate.key"/></b></td>
			<td><b><bean:message key="user.lastActive.key"/></b></td>
			<td><b><bean:message key="user.sessionCode.key"/></b></td>
			<td><b><bean:message key="user.IPassport.key"/></b></td>
			<td><b><bean:message key="user.userLevel.key"/> <font color="#FF0000">*</font></b></td>
   <% /* %> 			<td><b><bean:message key="user.status.key"/> <font color="#FF0000">*</font></b></td>
   <% */ %> 			<td><b><bean:message key="user.organization.key"/></b></td>
			<td><b><bean:message key="user.securityLevel.key"/></b></td>
			<td></td>	
		</tr>
	<logic:iterate id="element_user" name="userSet" type="com.app.docmgr.model.User">
		<%
			i++;
		%>		
		<tr>
			<td><bean:write name="element_user" property="loginName"/></td>
			<td><bean:write name="element_user" property="loginPassword"/></td>
			<td><bean:write name="element_user" property="pinCode"/></td>
			<td><bean:write name="element_user" property="mobileNumber"/></td>
			<td><bean:write name="element_user" property="language"/></td>
			<td><bean:write name="element_user" property="title"/></td>
			<td><bean:write name="element_user" property="name"/></td>
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
			<td><input type="button" value="edit" onclick="javascript:doEditUser(<% out.print(String.valueOf(i)); %>);"/><input type="button" value="remove" onclick="javascript:doDeleteUser(<% out.print(String.valueOf(i)); %>);"/></td>
		</tr>						
	</logic:iterate>		
		<tr>
			<td colspan="28">
				<logic:messagesPresent property="user.loginName">
					<html:errors property="user.loginName"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.loginPassword">
					<html:errors property="user.loginPassword"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.pinCode">
					<html:errors property="user.pinCode"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.mobileNumber">
					<html:errors property="user.mobileNumber"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.language">
					<html:errors property="user.language"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.title">
					<html:errors property="user.title"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.name">
					<html:errors property="user.name"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.email">
					<html:errors property="user.email"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.fullName">
					<html:errors property="user.fullName"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.homePhoneNumber">
					<html:errors property="user.homePhoneNumber"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.mobilePhoneNumber">
					<html:errors property="user.mobilePhoneNumber"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.employeeNumber">
					<html:errors property="user.employeeNumber"/>
				</logic:messagesPresent>			
   <% /* %> 				<logic:messagesPresent property="user.createdDate">
					<html:errors property="user.createdDate"/>
				</logic:messagesPresent>			
   <% */ %>    <% /* %> 				<logic:messagesPresent property="user.createdBy">
					<html:errors property="user.createdBy"/>
				</logic:messagesPresent>			
   <% */ %> 				<logic:messagesPresent property="user.lastUpdatedDate">
					<html:errors property="user.lastUpdatedDate"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastUpdatedBy">
					<html:errors property="user.lastUpdatedBy"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.firstLogin">
					<html:errors property="user.firstLogin"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastPasswordUpdate">
					<html:errors property="user.lastPasswordUpdate"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastPinCodeUpdate">
					<html:errors property="user.lastPinCodeUpdate"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastPassword">
					<html:errors property="user.lastPassword"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastPinCode">
					<html:errors property="user.lastPinCode"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.loginFailed">
					<html:errors property="user.loginFailed"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.maxRelease">
					<html:errors property="user.maxRelease"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastReleasedDate">
					<html:errors property="user.lastReleasedDate"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.lastActive">
					<html:errors property="user.lastActive"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.sessionCode">
					<html:errors property="user.sessionCode"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.IPassport">
					<html:errors property="user.IPassport"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.userLevel">
					<html:errors property="user.userLevel"/>
				</logic:messagesPresent>			
  <% /* %> 				<logic:messagesPresent property="user.status">
					<html:errors property="user.status"/>
				</logic:messagesPresent>			
  <% */ %> 				<logic:messagesPresent property="user.organization">
					<html:errors property="user.organization"/>
				</logic:messagesPresent>			
				<logic:messagesPresent property="user.securityLevel">
					<html:errors property="user.securityLevel"/>
				</logic:messagesPresent>			
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="loginName_item" value="<bean:write name="user" property="loginName"/>"/>
			</td>
			<td>
				<input type="text" name="loginPassword_item" value="<bean:write name="user" property="loginPassword"/>"/>
			</td>
			<td>
				<input type="text" name="pinCode_item" value="<bean:write name="user" property="pinCode"/>"/>
			</td>
			<td>
				<input type="text" name="mobileNumber_item" value="<bean:write name="user" property="mobileNumber"/>"/>
			</td>
			<td>
				<input type="text" name="language_item" value="<bean:write name="user" property="language"/>"/>
			</td>
			<td>
				<input type="text" name="title_item" value="<bean:write name="user" property="title"/>"/>
			</td>
			<td>
				<input type="text" name="name_item" value="<bean:write name="user" property="name"/>"/>
			</td>
			<td>
				<input type="text" name="email_item" value="<bean:write name="user" property="email"/>"/>
			</td>
			<td>
				<input type="text" name="fullName_item" value="<bean:write name="user" property="fullName"/>"/>
			</td>
			<td>
				<input type="text" name="homePhoneNumber_item" value="<bean:write name="user" property="homePhoneNumber"/>"/>
			</td>
			<td>
				<input type="text" name="mobilePhoneNumber_item" value="<bean:write name="user" property="mobilePhoneNumber"/>"/>
			</td>
			<td>
				<input type="text" name="employeeNumber_item" value="<bean:write name="user" property="employeeNumber"/>"/>
			</td>
  <% /* %> 			<td>
				<input type="text" name="createdDate_item" value="<bean:write name="user" property="createdDate"/>"/>
			</td>
  <% */ %>   <% /* %> 			<td>
				<input type="text" name="createdBy_item" value="<bean:write name="user" property="createdBy"/>"/>
			</td>
  <% */ %>   <% /* %> 			<td>
				<input type="text" name="lastUpdatedDate_item" value="<bean:write name="user" property="lastUpdatedDate"/>"/>
			</td>
  <% */ %>   <% /* %> 			<td>
				<input type="text" name="lastUpdatedBy_item" value="<bean:write name="user" property="lastUpdatedBy"/>"/>
			</td>
  <% */ %> 			<td>
				<input type="text" name="firstLogin_item" value="<bean:write name="user" property="firstLogin"/>"/>
			</td>
			<td>
				<input type="text" name="lastPasswordUpdate_item" value="<bean:write name="user" property="lastPasswordUpdate"/>"/>
			</td>
			<td>
				<input type="text" name="lastPinCodeUpdate_item" value="<bean:write name="user" property="lastPinCodeUpdate"/>"/>
			</td>
			<td>
				<input type="text" name="lastPassword_item" value="<bean:write name="user" property="lastPassword"/>"/>
			</td>
			<td>
				<input type="text" name="lastPinCode_item" value="<bean:write name="user" property="lastPinCode"/>"/>
			</td>
			<td>
				<input type="text" name="loginFailed_item" value="<bean:write name="user" property="loginFailed"/>"/>
			</td>
			<td>
				<input type="text" name="maxRelease_item" value="<bean:write name="user" property="maxRelease"/>"/>
			</td>
			<td>
				<input type="text" name="lastReleasedDate_item" value="<bean:write name="user" property="lastReleasedDate"/>"/>
			</td>
			<td>
				<input type="text" name="lastActive_item" value="<bean:write name="user" property="lastActive"/>"/>
			</td>
			<td>
				<input type="text" name="sessionCode_item" value="<bean:write name="user" property="sessionCode"/>"/>
			</td>
			<td>
				<input type="text" name="IPassport_item" value="<bean:write name="user" property="IPassport"/>"/>
			</td>
			<td>
				<html-el:select  name="element_user" property="userLevel" style="width:135"  value="${user.userLevel.id}">
					<option value=""></option>
					<html:options collection="userLevelList_user" property="id" labelProperty="id"/>
				</html-el:select>																		
			</td>
  <% /* %> 			<td>
				<html-el:select  name="element_user" property="status" style="width:135"  value="${user.status.id}">
					<option value=""></option>
					<html:options collection="statusList_user" property="id" labelProperty="id"/>
				</html-el:select>																		
			</td>
  <% */ %> 			<td>
				<html-el:select  name="element_user" property="organization" style="width:135"  value="${user.organization.id}">
					<option value=""></option>
					<html:options collection="organizationList_user" property="id" labelProperty="id"/>
				</html-el:select>																		
			</td>
			<td>
				<html-el:select  name="element_user" property="securityLevel" style="width:135"  value="${user.securityLevel.id}">
					<option value=""></option>
					<html:options collection="securityLevelList_user" property="id" labelProperty="id"/>
				</html-el:select>																		
			</td>
			<td><input type="button" value="add" onclick="javascript:doAddUser();"/></td>
		</tr>
	</table>
<!-- end one to many start -->	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

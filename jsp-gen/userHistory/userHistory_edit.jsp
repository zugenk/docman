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
<TITLE><bean:message key="page.UserHistory.Edit"/></TITLE>
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
		var optRole = new OptionTransfer("selected_role","role_choice");
		var optTopic = new OptionTransfer("selected_topic","topic_choice");
		function initOption(){
			optRole.init(document.forms[0]);
			optRole.clearOptions();
			optTopic.init(document.forms[0]);
			optTopic.clearOptions();
		}
		
		function markAll(){
			optRole.markAll();
			optTopic.markAll();
		}

	-->
</script>

<form name="userHistory" action="userHistory.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="userHistory" property="id"/>
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
				<bean:message key="page.UserHistory.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.historyDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="historyDate" onclick="calendarToogle('historyDate', 'historyDate_cal', null);"  onKeyDown="drawCalendar('historyDate', 'historyDate_cal', null);" value="<bean:write name="userHistory" property="historyDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="historyDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.historyDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.historyDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.historyBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="historyBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.historyBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.historyBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.auditTrailId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td><html:text name="userHistory" property="auditTrailId"/></td>
		</tr>
		<logic:messagesPresent property="userHistory.auditTrailId">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.auditTrailId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.userId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td><html:text name="userHistory" property="userId"/></td>
		</tr>
		<logic:messagesPresent property="userHistory.userId">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.userId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.loginName.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="loginName"/>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.loginName">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.loginName"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.loginPassword.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="loginPassword"/>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.loginPassword">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.loginPassword"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.pinCode.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="pinCode"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.mobileNumber.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="mobileNumber"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.language.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="language"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.title.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="title"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.name">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="userHistory.email.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="email"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.fullName.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="fullName"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.homePhoneNumber.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="homePhoneNumber"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.mobilePhoneNumber.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="mobilePhoneNumber"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.employeeNumber.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="employeeNumber"/>
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name="userHistory" property="createdDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="createdDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.createdDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.createdBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="userHistory.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name="userHistory" property="lastUpdatedDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastUpdatedDate_cal" style="display: none"></div>
			</td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="lastUpdatedBy"/>
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.firstLogin.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="firstLogin"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPasswordUpdate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastPasswordUpdate" onclick="calendarToogle('lastPasswordUpdate', 'lastPasswordUpdate_cal', null);"  onKeyDown="drawCalendar('lastPasswordUpdate', 'lastPasswordUpdate_cal', null);" value="<bean:write name="userHistory" property="lastPasswordUpdate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastPasswordUpdate_cal" style="display: none"></div>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPinCodeUpdate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastPinCodeUpdate" onclick="calendarToogle('lastPinCodeUpdate', 'lastPinCodeUpdate_cal', null);"  onKeyDown="drawCalendar('lastPinCodeUpdate', 'lastPinCodeUpdate_cal', null);" value="<bean:write name="userHistory" property="lastPinCodeUpdate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastPinCodeUpdate_cal" style="display: none"></div>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPassword.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="lastPassword"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastPinCode.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="lastPinCode"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.loginFailed.key"/></b></td>
			<td width="10">:</td>
			<td><html:text name="userHistory" property="loginFailed"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.maxRelease.key"/></b></td>
			<td width="10">:</td>
			<td><html:text name="userHistory" property="maxRelease"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastReleasedDate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastReleasedDate" onclick="calendarToogle('lastReleasedDate', 'lastReleasedDate_cal', null);"  onKeyDown="drawCalendar('lastReleasedDate', 'lastReleasedDate_cal', null);" value="<bean:write name="userHistory" property="lastReleasedDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastReleasedDate_cal" style="display: none"></div>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.lastActive.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastActive" onclick="calendarToogle('lastActive', 'lastActive_cal', null);"  onKeyDown="drawCalendar('lastActive', 'lastActive_cal', null);" value="<bean:write name="userHistory" property="lastActive" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastActive_cal" style="display: none"></div>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.sessionCode.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="sessionCode"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.IPassport.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="userHistory" property="IPassport"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="userHistory.userLevel.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="userHistory" property="userLevel" style="width:135"  value="${userHistory.userLevel.id}">
					<option value=""></option>
					<html:options collection="userLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.userLevel">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="userHistory.userLevel"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="userHistory" property="status" style="width:135"  value="${userHistory.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="userHistory.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="userHistory.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="userHistory.organization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="userHistory" property="organization" style="width:135"  value="${userHistory.organization.id}">
					<option value=""></option>
					<html:options collection="organizationList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="userHistory.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="userHistory" property="securityLevel" style="width:135"  value="${userHistory.securityLevel.id}">
					<option value=""></option>
					<html:options collection="securityLevelList" property="id" labelProperty="name"/>
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
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="2"><bean:message key="userHistory.roles.key"/></td>
		</tr>
		<tr>
			<td colspan="2" width="100%" align="center">
				<table width="95%">
					<%/*%>
					<tr> 
						<td width="100%" colspan="3" align="center">
							Role Type &nbsp;
							<select name="filter_role">
								<option value="" >All</option>
							</select>&nbsp;
							<input type="button" value="<bean:message key="button.search" />" onclick="addAll('role_choice','selected_role')"  alt="add all Role" />
						</td>
					</tr>
					<%*/%>
					<tr> 
						<td width="45%">
							<select name="selected_role" multiple style="width:100%" size="10" onDblClick="optRole.transferRight()">
							<logic:iterate id="element" name="roleSet" type="com.app.docmgr.model.Role">
								<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
							</logic:iterate>		
							</select>
						</td>
						<td width="5%" align="center">
						<input type="button" value="&laquo;&laquo;" onclick="optRole.transferAllLeft()"  alt="add all Role" /><br/>
						<input type="button" value="&laquo;" onclick="optRole.transferLeft()"  alt="add selected Role" /><br/>
						<input type="button" value="&raquo;" onclick="optRole.transferRight()"  alt="remove selected Role" /><br/>
						<input type="button" value="&raquo;&raquo;" onclick="optRole.transferAllRight()"  alt="remove all Role" />
						<td width="45%">
							<select name="role_choice" multiple style="width:100%" size="10" onDblClick="optRole.transferLeft()">
							<logic:iterate id="role" name="roleSetList" type="com.app.docmgr.model.Role">
								<option value="<bean:write name="role" property="id"/>"><bean:write name="role" property="name"/></option>
							</logic:iterate>		
							</select>				
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="2"><bean:message key="userHistory.favoriteTopic.key"/></td>
		</tr>
		<tr>
			<td colspan="2" width="100%" align="center">
				<table width="95%">
					<%/*%>
					<tr> 
						<td width="100%" colspan="3" align="center">
							Topic Type &nbsp;
							<select name="filter_topic">
								<option value="" >All</option>
							</select>&nbsp;
							<input type="button" value="<bean:message key="button.search" />" onclick="addAll('topic_choice','selected_topic')"  alt="add all Topic" />
						</td>
					</tr>
					<%*/%>
					<tr> 
						<td width="45%">
							<select name="selected_topic" multiple style="width:100%" size="10" onDblClick="optTopic.transferRight()">
							<logic:iterate id="element" name="topicSet" type="com.app.docmgr.model.Topic">
								<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
							</logic:iterate>		
							</select>
						</td>
						<td width="5%" align="center">
						<input type="button" value="&laquo;&laquo;" onclick="optTopic.transferAllLeft()"  alt="add all Topic" /><br/>
						<input type="button" value="&laquo;" onclick="optTopic.transferLeft()"  alt="add selected Topic" /><br/>
						<input type="button" value="&raquo;" onclick="optTopic.transferRight()"  alt="remove selected Topic" /><br/>
						<input type="button" value="&raquo;&raquo;" onclick="optTopic.transferAllRight()"  alt="remove all Topic" />
						<td width="45%">
							<select name="topic_choice" multiple style="width:100%" size="10" onDblClick="optTopic.transferLeft()">
							<logic:iterate id="topic" name="topicSetList" type="com.app.docmgr.model.Topic">
								<option value="<bean:write name="topic" property="id"/>"><bean:write name="topic" property="name"/></option>
							</logic:iterate>		
							</select>				
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

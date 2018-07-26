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
<TITLE><bean:message key="page.User.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
 */
-->
</HEAD>
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
<% int i=0; %>
<form name="user" action="user.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<input type="hidden" name="sequence"/>
	<input type="hidden" name="redirect" value="create"/>
	
	<%@ include file="../common/header.jsp" %>
	<table border="0" align="center" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				<bean:message key="page.User.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.loginName.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="loginName"/>
			</td>
		</tr>
		<logic:messagesPresent property="user.loginName">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.loginName"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="user.loginPassword.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="loginPassword"/>
			</td>
		</tr>
		<logic:messagesPresent property="user.loginPassword">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.loginPassword"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="user.pinCode.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="pinCode"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.mobileNumber.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="mobileNumber"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.picture.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="picture"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.language.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="language"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.title.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="title"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="user.name">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="user.alias.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="alias"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.email.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="email"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.fullName.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="fullName"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.homePhoneNumber.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="homePhoneNumber"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.mobilePhoneNumber.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="mobilePhoneNumber"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.employeeNumber.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="employeeNumber"/>
			</td>
		</tr>

 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="user.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name='user' property='createdDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="createdDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="user.createdDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="user.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="user.createdBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="user.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name='user' property='lastUpdatedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastUpdatedDate_cal" style="display: none"></div></td>
		</tr>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="user.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="lastUpdatedBy"/>
			</td>
		</tr>

 <% */ %> 		<tr>
			<td width="150"><b><bean:message key="user.firstLogin.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="firstLogin"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.lastPasswordUpdate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastPasswordUpdate" onclick="calendarToogle('lastPasswordUpdate', 'lastPasswordUpdate_cal', null);"  onKeyDown="drawCalendar('lastPasswordUpdate', 'lastPasswordUpdate_cal', null);" value="<bean:write name='user' property='lastPasswordUpdate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastPasswordUpdate_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.lastPinCodeUpdate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastPinCodeUpdate" onclick="calendarToogle('lastPinCodeUpdate', 'lastPinCodeUpdate_cal', null);"  onKeyDown="drawCalendar('lastPinCodeUpdate', 'lastPinCodeUpdate_cal', null);" value="<bean:write name='user' property='lastPinCodeUpdate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastPinCodeUpdate_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.lastPassword.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="lastPassword"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.lastPinCode.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="lastPinCode"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.loginFailed.key"/></b></td>
			<td width="10">:</td>			
			<td><html:text name="user" property="loginFailed"/></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.maxRelease.key"/></b></td>
			<td width="10">:</td>			
			<td><html:text name="user" property="maxRelease"/></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.lastReleasedDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastReleasedDate" onclick="calendarToogle('lastReleasedDate', 'lastReleasedDate_cal', null);"  onKeyDown="drawCalendar('lastReleasedDate', 'lastReleasedDate_cal', null);" value="<bean:write name='user' property='lastReleasedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastReleasedDate_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.lastActive.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastActive" onclick="calendarToogle('lastActive', 'lastActive_cal', null);"  onKeyDown="drawCalendar('lastActive', 'lastActive_cal', null);" value="<bean:write name='user' property='lastActive' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastActive_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.sessionCode.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="sessionCode"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="user.IPassport.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="user" property="IPassport"/>
			</td>
		</tr>


		<tr>
			<td width="150"><b><bean:message key="user.userLevel.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="user" property="userLevel" style="width:135"  value="${user.userLevel.id}">
					<option value=""></option>
					<html:options collection="userLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="user.userLevel">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.userLevel"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="user.position.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="user" property="position" style="width:135"  value="${user.position.id}">
					<option value=""></option>
					<html:options collection="positionList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
<% /* %> 		<tr>
			<td width="150"><b><bean:message key="user.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="user" property="status" style="width:135"  value="${user.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="user.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="user.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% */ %> 		<tr>
			<td width="150"><b><bean:message key="user.organization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="user" property="organization" style="width:135"  value="${user.organization.id}">
					<option value=""></option>
					<html:options collection="organizationList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="user.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="user" property="securityLevel" style="width:135"  value="${user.securityLevel.id}">
					<option value=""></option>
					<html:options collection="securityLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.save"/>" onclick="this.form.action.value='create_confirm'" />
			    &nbsp;
			    <html:reset><bean:message key="button.reset"/></html:reset>
				&nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='list';this.form.submit()" />
			</td>
		</tr>

	</table>	
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="2"><bean:message key="user.roles.key"/></td>
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
			<td colspan="2"><bean:message key="user.favoriteTopics.key"/></td>
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

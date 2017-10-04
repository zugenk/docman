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
<TITLE><bean:message key="page.LoginHistory.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->
</HEAD>
<BODY onload="initOption()">
<script language="javascript" src="template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

	-->
</script>
<% int i=0; %>
<form name="loginHistory" action="loginHistory.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.LoginHistory.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="loginHistory.loginTime.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="loginTime" onclick="calendarToogle('loginTime', 'loginTime_cal', null);"  onKeyDown="drawCalendar('loginTime', 'loginTime_cal', null);" value="<bean:write name='loginHistory' property='loginTime' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="loginTime_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="loginHistory.loginTime">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="loginHistory.loginTime"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="loginHistory.lastAccess.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastAccess" onclick="calendarToogle('lastAccess', 'lastAccess_cal', null);"  onKeyDown="drawCalendar('lastAccess', 'lastAccess_cal', null);" value="<bean:write name='loginHistory' property='lastAccess' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastAccess_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="loginHistory.lastAccess">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="loginHistory.lastAccess"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="loginHistory.logoutTime.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="logoutTime" onclick="calendarToogle('logoutTime', 'logoutTime_cal', null);"  onKeyDown="drawCalendar('logoutTime', 'logoutTime_cal', null);" value="<bean:write name='loginHistory' property='logoutTime' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="logoutTime_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="loginHistory.sessionId.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="loginHistory" property="sessionId"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="loginHistory.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="loginHistory" property="description"/>
			</td>
		</tr>


		<tr>
			<td width="150"><b><bean:message key="loginHistory.user.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="loginHistory" property="user" style="width:135"  value="${loginHistory.user.id}">
					<option value=""></option>
					<html:options collection="userList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="loginHistory.user">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="loginHistory.user"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% /* %> 		<tr>
			<td width="150"><b><bean:message key="loginHistory.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="loginHistory" property="status" style="width:135"  value="${loginHistory.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="loginHistory.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="loginHistory.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% */ %> 		<tr>
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
<!-- set one to many start -->
<!-- end one to many start -->	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

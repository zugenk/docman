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
<TITLE><bean:message key="page.EmailLog.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>
<BODY onload="initOption()">
<script language="javascript" src="../template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

	-->
</script>
<% int i=0; %>
<form name="emailLog" action="emailLog.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.EmailLog.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.type.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="emailLog" property="type"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="emailLog.sender.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="emailLog" property="sender"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="emailLog.rcptTo.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="emailLog" property="rcptTo"/>
			</td>
		</tr>
		<logic:messagesPresent property="emailLog.rcptTo">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="emailLog.rcptTo"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="emailLog.subject.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="emailLog" property="subject"/>
			</td>
		</tr>
		<logic:messagesPresent property="emailLog.subject">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="emailLog.subject"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="emailLog.message.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="emailLog" property="message"/>
			</td>
		</tr>
		<logic:messagesPresent property="emailLog.message">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="emailLog.message"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="emailLog.retry.key"/></b></td>
			<td width="10">:</td>			
			<td><html:text name="emailLog" property="retry"/></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="emailLog.dueDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="dueDate" onclick="calendarToogle('dueDate', 'dueDate_cal', null);"  onKeyDown="drawCalendar('dueDate', 'dueDate_cal', null);" value="<bean:write name='emailLog' property='dueDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="dueDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="emailLog.dueDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="emailLog.dueDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="emailLog.sentDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="sentDate" onclick="calendarToogle('sentDate', 'sentDate_cal', null);"  onKeyDown="drawCalendar('sentDate', 'sentDate_cal', null);" value="<bean:write name='emailLog' property='sentDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="sentDate_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="emailLog.lastTrialDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastTrialDate" onclick="calendarToogle('lastTrialDate', 'lastTrialDate_cal', null);"  onKeyDown="drawCalendar('lastTrialDate', 'lastTrialDate_cal', null);" value="<bean:write name='emailLog' property='lastTrialDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastTrialDate_cal" style="display: none"></div></td>
		</tr>


<% /* %> 		<tr>
			<td width="150"><b><bean:message key="emailLog.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="emailLog" property="status" style="width:135"  value="${emailLog.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="emailLog.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="emailLog.status"/></font>
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

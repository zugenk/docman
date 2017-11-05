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
<TITLE><bean:message key="page.EmailLog.EditConfirm"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 05-11-2017 15:05:21
 */
-->
</HEAD>
<BODY>
<form name="emailLog" action="emailLog.do" method="POST">
	<input type="hidden" name="action"/>
	<%@ include file="../common/header.jsp" %>
	<table border="0" align="center" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				<bean:message key="page.EmailLog.EditConfirm"/>
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
			<td><bean:write name="emailLog" property="type"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.sender.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="sender"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.rcptTo.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="rcptTo"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.subject.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="subject"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.message.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="message"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.retry.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="retry"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.dueDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="dueDate" format="dd/MM/yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.sentDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="sentDate" format="dd/MM/yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.lastTrialDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="emailLog" property="lastTrialDate" format="dd/MM/yyyy"/></td>
		</tr>

  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="emailLog.status.key"/></b></td>
			<td width="10">:</td>
			<td>
				<logic:notEmpty name="emailLog"	property="status">								
					<bean:write name="emailLog" property="status.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='edit_ok'" />
			    &nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='edit';this.form.submit()" />
			</td>
		</tr>
	</table>

<!-- set one to many start -->
<!-- end one to many start -->		    	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

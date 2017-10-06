<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.EmailLog.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 06-10-2017 22:19:39
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

<form name="emailLog" action="emailLog.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.EmailLog.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
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
			<td ><bean:write name="emailLog" property="dueDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.sentDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="emailLog" property="sentDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.lastTrialDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="emailLog" property="lastTrialDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="emailLog.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="emailLog"	property="status">			
					<bean:write name="emailLog" property="status.id"/>
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
<!-- end one to many start -->		

</form>
</BODY>
</html:html>

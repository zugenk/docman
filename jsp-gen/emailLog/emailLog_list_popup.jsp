<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<%
	int sequence = 0;
%>
<html:html>
<HEAD>
<TITLE><bean:message key="page.EmailLog.ListPopup"/></TITLE>
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
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<SCRIPT type="text/javascript" language="javascript">
	<!--

		function doAddParent(val){
			var formName = document.forms.emailLog.parentForm.value;
			var fieldName = document.forms.emailLog.parentField.value;
			window.opener.parent.document.forms[formName][fieldName].value=val;
			window.close();
		}
	-->
</SCRIPT>
<form name="emailLog" method="post" action="emailLog.do">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="parentForm" value="<bean:write name="parentForm"/>">
<INPUT type="hidden" name="parentField" value="<bean:write name="parentField"/>">

<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td class="titleHeader" colspan="19" align="left">
			<bean:message key="page.EmailLog.ListPopup"/>
		</td>
	</tr>

	<tr class="title">
	<logic:iterate id="element" name="emailLogList" scope="request" type="com.app.docmgr.model.EmailLog">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="type"/>');"><bean:write name="element" property="type"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="sender"/>');"><bean:write name="element" property="sender"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="rcptTo"/>');"><bean:write name="element" property="rcptTo"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="subject"/>');"><bean:write name="element" property="subject"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="message"/>');"><bean:write name="element" property="message"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="retry"/>');"><bean:write name="element" property="retry"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="dueDate"/>');"><bean:write name="element" property="dueDate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="sentDate"/>');"><bean:write name="element" property="sentDate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastTrialDate"/>');"><bean:write name="element" property="lastTrialDate" format="dd MMM yyyy"/></a></td>
		</tr>		
	</logic:iterate> 
</TABLE>

</form>
</BODY>
</html:html>

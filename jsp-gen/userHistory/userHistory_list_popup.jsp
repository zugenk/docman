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
<TITLE><bean:message key="page.UserHistory.ListPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
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
			var formName = document.forms.userHistory.parentForm.value;
			var fieldName = document.forms.userHistory.parentField.value;
			window.opener.parent.document.forms[formName][fieldName].value=val;
			window.close();
		}
	-->
</SCRIPT>
<form name="userHistory" method="post" action="userHistory.do">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="parentForm" value="<bean:write name="parentForm"/>">
<INPUT type="hidden" name="parentField" value="<bean:write name="parentField"/>">

<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td class="titleHeader" colspan="42" align="left">
			<bean:message key="page.UserHistory.ListPopup"/>
		</td>
	</tr>

	<tr class="title">
	<logic:iterate id="element" name="userHistoryList" scope="request" type="com.app.docmgr.model.UserHistory">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="historyDate"/>');"><bean:write name="element" property="historyDate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="historyBy"/>');"><bean:write name="element" property="historyBy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="auditTrailId"/>');"><bean:write name="element" property="auditTrailId"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="userId"/>');"><bean:write name="element" property="userId"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="loginName"/>');"><bean:write name="element" property="loginName"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="loginPassword"/>');"><bean:write name="element" property="loginPassword"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="pinCode"/>');"><bean:write name="element" property="pinCode"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="picture"/>');"><bean:write name="element" property="picture"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="language"/>');"><bean:write name="element" property="language"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="title"/>');"><bean:write name="element" property="title"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="name"/>');"><bean:write name="element" property="name"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="alias"/>');"><bean:write name="element" property="alias"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="email"/>');"><bean:write name="element" property="email"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="fullName"/>');"><bean:write name="element" property="fullName"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="homePhoneNumber"/>');"><bean:write name="element" property="homePhoneNumber"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="mobilePhoneNumber"/>');"><bean:write name="element" property="mobilePhoneNumber"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="employeeNumber"/>');"><bean:write name="element" property="employeeNumber"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="createdDate"/>');"><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="createdBy"/>');"><bean:write name="element" property="createdBy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastUpdatedDate"/>');"><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastUpdatedBy"/>');"><bean:write name="element" property="lastUpdatedBy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="firstLogin"/>');"><bean:write name="element" property="firstLogin"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastPasswordUpdate"/>');"><bean:write name="element" property="lastPasswordUpdate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastPinCodeUpdate"/>');"><bean:write name="element" property="lastPinCodeUpdate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastPassword"/>');"><bean:write name="element" property="lastPassword"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastPinCode"/>');"><bean:write name="element" property="lastPinCode"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="loginFailed"/>');"><bean:write name="element" property="loginFailed"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="maxRelease"/>');"><bean:write name="element" property="maxRelease"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastReleasedDate"/>');"><bean:write name="element" property="lastReleasedDate" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="lastActive"/>');"><bean:write name="element" property="lastActive" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="sessionCode"/>');"><bean:write name="element" property="sessionCode"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="IPassport"/>');"><bean:write name="element" property="IPassport"/></a></td>
		</tr>		
	</logic:iterate> 
</TABLE>

</form>
</BODY>
</html:html>

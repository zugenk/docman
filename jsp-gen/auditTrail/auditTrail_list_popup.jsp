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
<TITLE><bean:message key="page.AuditTrail.ListPopup"/></TITLE>
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
			var formName = document.forms.auditTrail.parentForm.value;
			var fieldName = document.forms.auditTrail.parentField.value;
			window.opener.parent.document.forms[formName][fieldName].value=val;
			window.close();
		}
	-->
</SCRIPT>
<form name="auditTrail" method="post" action="auditTrail.do">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="parentForm" value="<bean:write name="parentForm"/>">
<INPUT type="hidden" name="parentField" value="<bean:write name="parentField"/>">

<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td class="titleHeader" colspan="18" align="left">
			<bean:message key="page.AuditTrail.ListPopup"/>
		</td>
	</tr>

	<tr class="title">
	<logic:iterate id="element" name="auditTrailList" scope="request" type="com.app.docmgr.model.AuditTrail">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="auditTime"/>');"><bean:write name="element" property="auditTime" format="dd MMM yyyy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="entity"/>');"><bean:write name="element" property="entity"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="entityId"/>');"><bean:write name="element" property="entityId"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="doneBy"/>');"><bean:write name="element" property="doneBy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="sessionId"/>');"><bean:write name="element" property="sessionId"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="approvedBy"/>');"><bean:write name="element" property="approvedBy"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="action"/>');"><bean:write name="element" property="action"/></a></td>
		<td><a href="#" onclick="doAddParent('<bean:write name="element" property="description"/>');"><bean:write name="element" property="description"/></a></td>
		</tr>		
	</logic:iterate> 
</TABLE>

</form>
</BODY>
</html:html>

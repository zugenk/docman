<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.AuditTrail.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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

<form name="auditTrail" action="auditTrail.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.AuditTrail.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.auditTime.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="auditTrail" property="auditTime" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.entity.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="entity"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.doneBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="doneBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.sessionId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="sessionId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.approvedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="approvedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.actions.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="actions"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="description"/></td>
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

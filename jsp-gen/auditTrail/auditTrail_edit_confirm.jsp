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
<TITLE><bean:message key="page.AuditTrail.EditConfirm"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->
</HEAD>
<BODY>
<form name="auditTrail" action="auditTrail.do" method="POST">
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
				<bean:message key="page.AuditTrail.EditConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.auditTime.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="auditTrail" property="auditTime" format="dd/MM/yyyy"/></td>
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
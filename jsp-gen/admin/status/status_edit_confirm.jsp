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
<TITLE><bean:message key="page.Status.EditConfirm"/></TITLE>
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
<form name="status" action="status.do" method="POST">
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
				<bean:message key="page.Status.EditConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.type.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="type"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.state.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="state"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="status.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="status" property="description"/></td>
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

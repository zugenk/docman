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
<TITLE><bean:message key="page.Role.RejectConfirm"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>

<BODY>

<form name="role" action="role.do" method="POST">
	<input type="hidden" name="action"/>
	<%@ include file="../common/header.jsp" %>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Role.RejectConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.name.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="role" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.description.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="role" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="role" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="role" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="role" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="role" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.userLevel.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="role" property="userLevel">			
					<bean:write name="role" property="userLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="role" property="status">			
					<bean:write name="role" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='reject_ok'" />
			    &nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='detail';this.form.submit()" />
			</td>
		</tr>
	</table>
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td><bean:message key="role.privileges.key"/></td>
		</tr>
		<tr>
			<td align=center>
				<div style="overflow:auto; height:110" >
				<table width="90%" >
					<logic:iterate id="privilege" name="privilegeSet" type="com.app.docmgr.model.Privilege">
						<tr><td><bean:write name="privilege" property="name"/></td></tr>
					</logic:iterate>	
				</table>
				</div>
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->		
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

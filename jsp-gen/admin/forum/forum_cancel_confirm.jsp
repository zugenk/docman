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
<TITLE><bean:message key="page.Forum.CancelConfirm"/></TITLE>
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

<form name="forum" action="forum.do" method="POST">
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
				<bean:message key="page.Forum.CancelConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.code.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.icon.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="icon"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.name.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.description.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="forum" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="forum" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.filterCode.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="filterCode"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="forum" property="status">			
					<bean:write name="forum" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.forumType.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="forum" property="forumType">			
					<bean:write name="forum" property="forumType.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.parent.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="forum" property="parent">			
					<bean:write name="forum" property="parent.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='cancel_ok'" />
			    &nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='detail';this.form.submit()" />
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->		
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

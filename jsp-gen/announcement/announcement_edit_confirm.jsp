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
<TITLE><bean:message key="page.Announcement.EditConfirm"/></TITLE>
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
<form name="announcement" action="announcement.do" method="POST">
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
				<bean:message key="page.Announcement.EditConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="announcement.content.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="content"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="announcement.targetUsers.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="targetUsers"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="announcement.targetOrganizations.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="targetOrganizations"/></td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="announcement.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="createdDate" format="dd/MM/yyyy"/></td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="announcement.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="createdBy"/></td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="announcement.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="lastUpdatedDate" format="dd/MM/yyyy"/></td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="announcement.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="announcement" property="lastUpdatedBy"/></td>
		</tr>
  <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="announcement.announcementType.key"/></b></td>
			<td width="10">:</td>
			<td>
				<logic:notEmpty name="announcement"	property="announcementType">								
					<bean:write name="announcement" property="announcementType.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="announcement.status.key"/></b></td>
			<td width="10">:</td>
			<td>
				<logic:notEmpty name="announcement"	property="status">								
					<bean:write name="announcement" property="status.name"/>
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

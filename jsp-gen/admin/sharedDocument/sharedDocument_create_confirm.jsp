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
<TITLE><bean:message key="page.SharedDocument.CreateConfirm"/></TITLE>
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
<form name="sharedDocument" action="sharedDocument.do" method="POST">
	<input type="hidden" name="action"/>
	<%@ include file="../common/header.jsp" %>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
	
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="3" class="titleHeader">
				<bean:message key="page.SharedDocument.CreateConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="sharedDocument.grantAction.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="grantAction"/></td>
		</tr>
 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="createdDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="createdBy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="lastUpdatedDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="lastUpdatedBy"/></td>
		</tr>
 <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.document.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument" property="document">				
					<bean:write name="sharedDocument" property="document.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.targetUser.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument" property="targetUser">				
					<bean:write name="sharedDocument" property="targetUser.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.targetOrganization.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument" property="targetOrganization">				
					<bean:write name="sharedDocument" property="targetOrganization.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument" property="status">				
					<bean:write name="sharedDocument" property="status.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='create_ok'" />
				&nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='create';this.form.submit()" />
			</td>
		</tr>

	</table>	
<!-- set one to many start -->
<!-- end one to many start -->	
	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

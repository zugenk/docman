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
<TITLE><bean:message key="page.Document.EditConfirm"/></TITLE>
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
<form name="document" action="document.do" method="POST">
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
				<bean:message key="page.Document.EditConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.contentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="contentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.repositoryId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="repositoryId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentVersion.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentVersion"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="description"/></td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="createdDate" format="dd/MM/yyyy"/></td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="createdBy"/></td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="lastUpdatedDate" format="dd/MM/yyyy"/></td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="lastUpdatedBy"/></td>
		</tr>
  <% */ %> 
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.status.key"/></b></td>
			<td width="10">:</td>
			<td>
				<logic:notEmpty name="document"	property="status">								
					<bean:write name="document" property="status.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="document.parentFolder.key"/></b></td>
			<td width="10">:</td>
			<td>
				<logic:notEmpty name="document"	property="parentFolder">								
					<bean:write name="document" property="parentFolder.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.parentDocument.key"/></b></td>
			<td width="10">:</td>
			<td>
				<logic:notEmpty name="document"	property="parentDocument">								
					<bean:write name="document" property="parentDocument.name"/>
				</logic:notEmpty>	
			</td>
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

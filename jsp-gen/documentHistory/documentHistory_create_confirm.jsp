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
<TITLE><bean:message key="page.DocumentHistory.CreateConfirm"/></TITLE>
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
<form name="documentHistory" action="documentHistory.do" method="POST">
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
				<bean:message key="page.DocumentHistory.CreateConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.historyDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="historyDate" format="dd/MM/yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.historyBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="historyBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.auditTrailId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="auditTrailId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.contentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="contentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.repositoryId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="repositoryId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentVersion.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentVersion"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="description"/></td>
		</tr>
 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="createdDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="createdBy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="lastUpdatedDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="lastUpdatedBy"/></td>
		</tr>
 <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="documentHistory.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="securityLevel">				
					<bean:write name="documentHistory" property="securityLevel.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="owner">				
					<bean:write name="documentHistory" property="owner.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="status">				
					<bean:write name="documentHistory" property="status.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.parentFolder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="parentFolder">				
					<bean:write name="documentHistory" property="parentFolder.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.parentDocument.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="parentDocument">				
					<bean:write name="documentHistory" property="parentDocument.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
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

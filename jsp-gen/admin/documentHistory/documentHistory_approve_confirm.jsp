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
<TITLE><bean:message key="page.DocumentHistory.ApproveConfirm"/></TITLE>
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
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.DocumentHistory.ApproveConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.historyDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="documentHistory" property="historyDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.historyBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="historyBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.auditTrailId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="auditTrailId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.documentId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.name.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.documentType.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentType"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.contentType.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="contentType"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.documentNumber.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentNumber"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.repositoryId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="repositoryId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.documentVersion.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="documentVersion"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.description.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.tag.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="tag"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="documentHistory" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="documentHistory" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.securityLevel.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="securityLevel">			
					<bean:write name="documentHistory" property="securityLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.owner.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="owner">			
					<bean:write name="documentHistory" property="owner.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="status">			
					<bean:write name="documentHistory" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.folder.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="folder">			
					<bean:write name="documentHistory" property="folder.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.parent.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory" property="parent">			
					<bean:write name="documentHistory" property="parent.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='approve_ok'" />
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

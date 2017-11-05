<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.DocumentHistory.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 05-11-2017 15:05:21
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
<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="documentHistory" action="documentHistory.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.DocumentHistory.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.historyDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="documentHistory" property="historyDate" format="dd MMM yyyy"/></td>
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
		<tr>
			<td width="150"><b><bean:message key="documentHistory.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="documentHistory" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="documentHistory" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="documentHistory" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory"	property="securityLevel">			
					<bean:write name="documentHistory" property="securityLevel.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory"	property="owner">			
					<bean:write name="documentHistory" property="owner.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory"	property="status">			
					<bean:write name="documentHistory" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.parentFolder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory"	property="parentFolder">			
					<bean:write name="documentHistory" property="parentFolder.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.parentDocument.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="documentHistory"	property="parentDocument">			
					<bean:write name="documentHistory" property="parentDocument.id"/>
				</logic:notEmpty>
			</td>
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

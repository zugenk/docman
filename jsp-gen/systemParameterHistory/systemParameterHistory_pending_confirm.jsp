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
<TITLE><bean:message key="page.SystemParameterHistory.PendingConfirm"/></TITLE>
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

<form name="systemParameterHistory" action="systemParameterHistory.do" method="POST">
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
				<bean:message key="page.SystemParameterHistory.PendingConfirm"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.historyDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameterHistory" property="historyDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.historyBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="historyBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.auditTrailId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="auditTrailId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.systemParameterId.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="systemParameterId"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.vgroup.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="vgroup"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.parameter.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="parameter"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.svalue.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="svalue"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.description.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.createdDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameterHistory" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.createdBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.lastUpdatedDate.key"/></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameterHistory" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.lastUpdatedBy.key"/></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameterHistory" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.status.key"/></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameterHistory" property="status">			
					<bean:write name="systemParameterHistory" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='pending_ok'" />
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

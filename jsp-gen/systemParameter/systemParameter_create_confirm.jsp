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
<TITLE><bean:message key="page.SystemParameter.CreateConfirm"/></TITLE>
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
<form name="systemParameter" action="systemParameter.do" method="POST">
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
				<bean:message key="page.SystemParameter.CreateConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="systemParameter.vgroup.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="vgroup"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.parameter.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="parameter"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.svalue.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="svalue"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="description"/></td>
		</tr>
 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="createdDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="createdBy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="lastUpdatedDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="lastUpdatedBy"/></td>
		</tr>
 <% */ %> 
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameter" property="status">				
					<bean:write name="systemParameter" property="status.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.userLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameter" property="userLevel">				
					<bean:write name="systemParameter" property="userLevel.name"/>
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

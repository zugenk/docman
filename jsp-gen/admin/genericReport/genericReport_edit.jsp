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
<TITLE><bean:message key="page.GenericReport.Edit"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
 */
-->
</HEAD>
<% int i=0; %>

<BODY onload="initOption()">
<script language="javascript" src="../template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

	-->
</script>

<form name="genericReport" action="genericReport.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="genericReport" property="id"/>
	<input type="hidden" name="sequence"/>
	<input type="hidden" name="redirect" value="edit"/>
	
	<%@ include file="../common/header.jsp" %>
	<table border="0" align="center" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				<bean:message key="page.GenericReport.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="genericReport" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="genericReport.name">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="genericReport.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="genericReport.reportFields.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="genericReport" property="reportFields"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.searchFields.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="genericReport" property="searchFields"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.reportSql.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="genericReport" property="reportSql"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.description.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="genericReport" property="description"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="genericReport.columnAction.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="genericReport" property="columnAction"/>
			</td>
		</tr>

  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="genericReport.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="genericReport" property="status" style="width:135"  value="${genericReport.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="genericReport.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="genericReport.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %> 		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.save"/>" onclick="this.form.action.value='edit_confirm'" />
			    &nbsp;
			    <html:reset>
		        	<bean:message key="button.reset"/>
		      	</html:reset>
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

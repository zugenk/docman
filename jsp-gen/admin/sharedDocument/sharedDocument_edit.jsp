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
<TITLE><bean:message key="page.SharedDocument.Edit"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 06-10-2017 22:19:39
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

<form name="sharedDocument" action="sharedDocument.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="sharedDocument" property="id"/>
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
				<bean:message key="page.SharedDocument.Edit"/>
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
			<td>
				<html:text name="sharedDocument" property="grantAction"/>
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name="sharedDocument" property="createdDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="createdDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="sharedDocument.createdDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="sharedDocument.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="sharedDocument" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="sharedDocument.createdBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="sharedDocument.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.document.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="sharedDocument" property="document" style="width:135"  value="${sharedDocument.document.id}">
					<option value=""></option>
					<html:options collection="documentList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="sharedDocument.document">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="sharedDocument.document"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.user.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="sharedDocument" property="user" style="width:135"  value="${sharedDocument.user.id}">
					<option value=""></option>
					<html:options collection="userList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="sharedDocument.user">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="sharedDocument.user"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="sharedDocument.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="sharedDocument" property="status" style="width:135"  value="${sharedDocument.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="sharedDocument.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="sharedDocument.status"/></font>
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

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
<TITLE><bean:message key="page.SystemSequence.Edit"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>
<% int i=0; %>

<BODY onload="initOption()">
<script language="javascript" src="template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

	-->
</script>

<form name="systemSequence" action="systemSequence.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="systemSequence" property="id"/>
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
				<bean:message key="page.SystemSequence.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemSequence.type.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="systemSequence" property="type"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemSequence.type">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="systemSequence.type"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="systemSequence.param.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="systemSequence" property="param"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemSequence.param">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="systemSequence.param"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="systemSequence.sequence.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td><html:text name="systemSequence" property="sequence"/></td>
		</tr>
		<logic:messagesPresent property="systemSequence.sequence">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="systemSequence.sequence"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
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

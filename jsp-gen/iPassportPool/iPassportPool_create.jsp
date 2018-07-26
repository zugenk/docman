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
<TITLE><bean:message key="page.IPassportPool.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
 */
-->
</HEAD>
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
<% int i=0; %>
<form name="iPassportPool" action="iPassportPool.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<input type="hidden" name="sequence"/>
	<input type="hidden" name="redirect" value="create"/>
	
	<%@ include file="../common/header.jsp" %>
	<table border="0" align="center" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				<bean:message key="page.IPassportPool.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="iPassportPool.ipassport.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="iPassportPool" property="ipassport"/>
			</td>
		</tr>
		<logic:messagesPresent property="iPassportPool.ipassport">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="iPassportPool.ipassport"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="iPassportPool.loginId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="iPassportPool" property="loginId"/>
			</td>
		</tr>
		<logic:messagesPresent property="iPassportPool.loginId">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="iPassportPool.loginId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="iPassportPool.lastLogin.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastLogin" onclick="calendarToogle('lastLogin', 'lastLogin_cal', null);"  onKeyDown="drawCalendar('lastLogin', 'lastLogin_cal', null);" value="<bean:write name='iPassportPool' property='lastLogin' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastLogin_cal" style="display: none"></div></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="iPassportPool.lastActive.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastActive" onclick="calendarToogle('lastActive', 'lastActive_cal', null);"  onKeyDown="drawCalendar('lastActive', 'lastActive_cal', null);" value="<bean:write name='iPassportPool' property='lastActive' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastActive_cal" style="display: none"></div></td>
		</tr>


		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.save"/>" onclick="this.form.action.value='create_confirm'" />
			    &nbsp;
			    <html:reset><bean:message key="button.reset"/></html:reset>
				&nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='list';this.form.submit()" />
			</td>
		</tr>

	</table>	
<!-- set one to many start -->
<!-- end one to many start -->	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

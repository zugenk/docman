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
<TITLE><bean:message key="page.AuditTrail.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
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
<form name="auditTrail" action="auditTrail.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.AuditTrail.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="auditTrail.auditTime.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="auditTime" onclick="calendarToogle('auditTime', 'auditTime_cal', null);"  onKeyDown="drawCalendar('auditTime', 'auditTime_cal', null);" value="<bean:write name='auditTrail' property='auditTime' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="auditTime_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="auditTrail.auditTime">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="auditTrail.auditTime"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.entity.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="auditTrail" property="entity"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.entityId.key"/></b></td>
			<td width="10">:</td>			
			<td><html:text name="auditTrail" property="entityId"/></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.doneBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="auditTrail" property="doneBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="auditTrail.doneBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="auditTrail.doneBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.sessionId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="auditTrail" property="sessionId"/>
			</td>
		</tr>
		<logic:messagesPresent property="auditTrail.sessionId">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="auditTrail.sessionId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.approvedBy.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="auditTrail" property="approvedBy"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.action.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="auditTrail" property="action"/>
			</td>
		</tr>
		<logic:messagesPresent property="auditTrail.action">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="auditTrail.action"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="auditTrail.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="auditTrail" property="description"/>
			</td>
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

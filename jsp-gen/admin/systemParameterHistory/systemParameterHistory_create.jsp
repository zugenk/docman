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
<TITLE><bean:message key="page.SystemParameterHistory.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>
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
<% int i=0; %>
<form name="systemParameterHistory" action="systemParameterHistory.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.SystemParameterHistory.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.historyDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="historyDate" onclick="calendarToogle('historyDate', 'historyDate_cal', null);"  onKeyDown="drawCalendar('historyDate', 'historyDate_cal', null);" value="<bean:write name='systemParameterHistory' property='historyDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="historyDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.historyDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.historyDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.historyBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="historyBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.historyBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.historyBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.auditTrailId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><html:text name="systemParameterHistory" property="auditTrailId"/></td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.auditTrailId">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.auditTrailId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.systemParameterId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><html:text name="systemParameterHistory" property="systemParameterId"/></td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.systemParameterId">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.systemParameterId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.vgroup.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="vgroup"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.vgroup">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.vgroup"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.parameter.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="parameter"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.parameter">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.parameter"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.svalue.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="svalue"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="description"/>
			</td>
		</tr>

 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name='systemParameterHistory' property='createdDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="createdDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.createdDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.createdBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name='systemParameterHistory' property='lastUpdatedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastUpdatedDate_cal" style="display: none"></div></td>
		</tr>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameterHistory" property="lastUpdatedBy"/>
			</td>
		</tr>

 <% */ %> 
<% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameterHistory.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="systemParameterHistory" property="status" style="width:135"  value="${systemParameterHistory.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="systemParameterHistory.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameterHistory.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% */ %> 		<tr>
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

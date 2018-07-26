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
<TITLE><bean:message key="page.SystemParameter.Create"/></TITLE>
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
<form name="systemParameter" action="systemParameter.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.SystemParameter.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.vgroup.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameter" property="vgroup"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameter.vgroup">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameter.vgroup"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameter.parameter.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameter" property="parameter"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameter.parameter">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameter.parameter"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="systemParameter.svalue.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameter" property="svalue"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="systemParameter.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameter" property="description"/>
			</td>
		</tr>

 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name='systemParameter' property='createdDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="createdDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="systemParameter.createdDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameter.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameter" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="systemParameter.createdBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameter.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name='systemParameter' property='lastUpdatedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastUpdatedDate_cal" style="display: none"></div></td>
		</tr>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="systemParameter" property="lastUpdatedBy"/>
			</td>
		</tr>

 <% */ %> 
<% /* %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="systemParameter" property="status" style="width:135"  value="${systemParameter.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="systemParameter.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameter.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% */ %> 		<tr>
			<td width="150"><b><bean:message key="systemParameter.userLevel.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="systemParameter" property="userLevel" style="width:135"  value="${systemParameter.userLevel.id}">
					<option value=""></option>
					<html:options collection="userLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="systemParameter.userLevel">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="systemParameter.userLevel"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
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

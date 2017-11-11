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
<TITLE><bean:message key="page.Role.Create"/></TITLE>
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
<script language="javascript" src="../template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		var optPrivilege = new OptionTransfer("selected_privilege","privilege_choice");
		function initOption(){
			optPrivilege.init(document.forms[0]);
			optPrivilege.clearOptions();
		}
		
		function markAll(){
			optPrivilege.markAll();
		}

	-->
</script>
<% int i=0; %>
<form name="role" action="role.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.Role.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="role.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="role" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="role.name">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="role.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="role" property="description"/>
			</td>
		</tr>

 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="role.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name='role' property='createdDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="createdDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="role.createdDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="role.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="role" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="role.createdBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="role.lastUpdatedDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name='role' property='lastUpdatedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastUpdatedDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="role.lastUpdatedDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.lastUpdatedDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="role.lastUpdatedBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="role" property="lastUpdatedBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="role.lastUpdatedBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.lastUpdatedBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="role.userLevel.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="role" property="userLevel" style="width:135"  value="${role.userLevel.id}">
					<option value=""></option>
					<html:options collection="userLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="role.userLevel">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.userLevel"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% /* %> 		<tr>
			<td width="150"><b><bean:message key="role.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="role" property="status" style="width:135"  value="${role.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="role.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="role.status"/></font>
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
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="2"><bean:message key="role.privileges.key"/></td>
		</tr>
		<tr>
			<td colspan="2" width="100%" align="center">
				<table width="95%">
					<%/*%>
					<tr> 
						<td width="100%" colspan="3" align="center">
							Privilege Type &nbsp;
							<select name="filter_privilege">
								<option value="" >All</option>
							</select>&nbsp;
							<input type="button" value="<bean:message key="button.search" />" onclick="addAll('privilege_choice','selected_privilege')"  alt="add all Privilege" />
						</td>
					</tr>
					<%*/%>
					<tr> 
						<td width="45%">
							<select name="selected_privilege" multiple style="width:100%" size="10" onDblClick="optPrivilege.transferRight()">
							<logic:iterate id="element" name="privilegeSet" type="com.app.docmgr.model.Privilege">
								<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
							</logic:iterate>		
							</select>
						</td>
						<td width="5%" align="center">
						<input type="button" value="&laquo;&laquo;" onclick="optPrivilege.transferAllLeft()"  alt="add all Privilege" /><br/>
						<input type="button" value="&laquo;" onclick="optPrivilege.transferLeft()"  alt="add selected Privilege" /><br/>
						<input type="button" value="&raquo;" onclick="optPrivilege.transferRight()"  alt="remove selected Privilege" /><br/>
						<input type="button" value="&raquo;&raquo;" onclick="optPrivilege.transferAllRight()"  alt="remove all Privilege" />
						<td width="45%">
							<select name="privilege_choice" multiple style="width:100%" size="10" onDblClick="optPrivilege.transferLeft()">
							<logic:iterate id="privilege" name="privilegeSetList" type="com.app.docmgr.model.Privilege">
								<option value="<bean:write name="privilege" property="id"/>"><bean:write name="privilege" property="name"/></option>
							</logic:iterate>		
							</select>				
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

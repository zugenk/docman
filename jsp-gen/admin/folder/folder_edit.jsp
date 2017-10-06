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
<TITLE><bean:message key="page.Folder.Edit"/></TITLE>
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

<form name="folder" action="folder.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="folder" property="id"/>
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
				<bean:message key="page.Folder.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="folder.code.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="folder" property="code"/>
			</td>
		</tr>
		<logic:messagesPresent property="folder.code">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="folder.code"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="folder.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="folder" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="folder.name">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="folder.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="folder.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name="folder" property="createdDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="createdDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="folder.createdDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="folder.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="folder.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="folder" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="folder.createdBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="folder.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="folder.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name="folder" property="lastUpdatedDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastUpdatedDate_cal" style="display: none"></div>
			</td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="folder.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="folder" property="lastUpdatedBy"/>
			</td>
		</tr>
  <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="folder.folderType.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="folder" property="folderType" style="width:135"  value="${folder.folderType.id}">
					<option value=""></option>
					<html:options collection="folderTypeList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="folder.folderType">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="folder.folderType"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="folder.parentFolder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="folder" property="parentFolder" style="width:135"  value="${folder.parentFolder.id}">
					<option value=""></option>
					<html:options collection="parentFolderList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
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

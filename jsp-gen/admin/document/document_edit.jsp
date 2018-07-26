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
<TITLE><bean:message key="page.Document.Edit"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
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

<form name="document" action="document.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="document" property="id"/>
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
				<bean:message key="page.Document.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.name.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="name"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentType.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="documentType"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.contentType.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="contentType"/>
			</td>
		</tr>
		<logic:messagesPresent property="document.contentType">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="document.contentType"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="document.documentNumber.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="documentNumber"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.repositoryId.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="repositoryId"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentVersion.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="documentVersion"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.description.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="description"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.tag.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="tag"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.priority.key"/></b></td>
			<td width="10">:</td>
			<td><html:text name="document" property="priority"/></td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name="document" property="createdDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="createdDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="document.createdDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="document.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="document.createdBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="document.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name="document" property="lastUpdatedDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastUpdatedDate_cal" style="display: none"></div>
			</td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="document" property="lastUpdatedBy"/>
			</td>
		</tr>
  <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="document.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="document" property="securityLevel" style="width:135"  value="${document.securityLevel.id}">
					<option value=""></option>
					<html:options collection="securityLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.owner.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="document" property="owner" style="width:135"  value="${document.owner.id}">
					<option value=""></option>
					<html:options collection="ownerList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="document.owner">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="document.owner"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="document.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="document" property="status" style="width:135"  value="${document.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="document.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="document.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="document.folder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="document" property="folder" style="width:135"  value="${document.folder.id}">
					<option value=""></option>
					<html:options collection="folderList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="document" property="parent" style="width:135"  value="${document.parent.id}">
					<option value=""></option>
					<html:options collection="parentList" property="id" labelProperty="name"/>
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

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
<TITLE><bean:message key="page.Forum.Edit"/></TITLE>
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
<script language="javascript" src="template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

	-->
</script>

<form name="forum" action="forum.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<html:hidden name="forum" property="id"/>
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
				<bean:message key="page.Forum.Edit"/>
			</td>
		</tr>

		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.code.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="code"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.icon.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="icon"/>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="forum.name">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="forum.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="forum.description.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="description"/>
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="forum.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name="forum" property="createdDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="createdDate_cal" style="display: none"></div>
			</td>
		</tr>
		<logic:messagesPresent property="forum.createdDate">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="forum.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="forum.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="forum.createdBy">
			<tr>
				<td colspan="2">
					<font color="red"><html:errors property="forum.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="forum.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td>
				<input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name="forum" property="lastUpdatedDate" format="dd/MM/yyyy" />"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" />
			<td>
				<div id="lastUpdatedDate_cal" style="display: none"></div>
			</td>
		</tr>
  <% */ %>   <% /* %> 		<tr>
			<td width="150"><b><bean:message key="forum.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="lastUpdatedBy"/>
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="forum.filterCode.key"/></b></td>
			<td width="10">:</td>
			<td>
				<html:text name="forum" property="filterCode"/>
			</td>
		</tr>

  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="forum.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="forum" property="status" style="width:135"  value="${forum.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="forum.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="forum.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="forum.forumType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="forum" property="forumType" style="width:135"  value="${forum.forumType.id}">
					<option value=""></option>
					<html:options collection="forumTypeList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="forum" property="parent" style="width:135"  value="${forum.parent.id}">
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

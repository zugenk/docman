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
<TITLE><bean:message key="page.Topic.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->
</HEAD>
<BODY onload="initOption()">
<script language="javascript" src="../template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		var optUser = new OptionTransfer("selected_user","user_choice");
		function initOption(){
			optUser.init(document.forms[0]);
			optUser.clearOptions();
		}
		
		function markAll(){
			optUser.markAll();
		}

	-->
</script>
<% int i=0; %>
<form name="topic" action="topic.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
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
				<bean:message key="page.Topic.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.code.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="code"/>
			</td>
		</tr>
		<logic:messagesPresent property="topic.code">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="topic.code"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="topic.icon.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="icon"/>
			</td>
		</tr>
		<logic:messagesPresent property="topic.icon">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="topic.icon"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="topic.name.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="name"/>
			</td>
		</tr>
		<logic:messagesPresent property="topic.name">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="topic.name"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="topic.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="description"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="topic.numberOfLike.key"/></b></td>
			<td width="10">:</td>			
			<td><html:text name="topic" property="numberOfLike"/></td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="topic.numberOfPost.key"/></b></td>
			<td width="10">:</td>			
			<td><html:text name="topic" property="numberOfPost"/></td>
		</tr>

 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="topic.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name='topic' property='createdDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="createdDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="topic.createdDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="topic.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="topic.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="topic.createdBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="topic.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="topic.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name='topic' property='lastUpdatedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastUpdatedDate_cal" style="display: none"></div></td>
		</tr>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="topic.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="lastUpdatedBy"/>
			</td>
		</tr>

 <% */ %> 		<tr>
			<td width="150"><b><bean:message key="topic.filterCode.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="topic" property="filterCode"/>
			</td>
		</tr>


		<tr>
			<td width="150"><b><bean:message key="topic.parentForum.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="topic" property="parentForum" style="width:135"  value="${topic.parentForum.id}">
					<option value=""></option>
					<html:options collection="parentForumList" property="id" labelProperty="name"/>
				</html-el:select>															
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
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td colspan="2"><bean:message key="topic.followers.key"/></td>
		</tr>
		<tr>
			<td colspan="2" width="100%" align="center">
				<table width="95%">
					<%/*%>
					<tr> 
						<td width="100%" colspan="3" align="center">
							User Type &nbsp;
							<select name="filter_user">
								<option value="" >All</option>
							</select>&nbsp;
							<input type="button" value="<bean:message key="button.search" />" onclick="addAll('user_choice','selected_user')"  alt="add all User" />
						</td>
					</tr>
					<%*/%>
					<tr> 
						<td width="45%">
							<select name="selected_user" multiple style="width:100%" size="10" onDblClick="optUser.transferRight()">
							<logic:iterate id="element" name="userSet" type="com.app.docmgr.model.User">
								<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
							</logic:iterate>		
							</select>
						</td>
						<td width="5%" align="center">
						<input type="button" value="&laquo;&laquo;" onclick="optUser.transferAllLeft()"  alt="add all User" /><br/>
						<input type="button" value="&laquo;" onclick="optUser.transferLeft()"  alt="add selected User" /><br/>
						<input type="button" value="&raquo;" onclick="optUser.transferRight()"  alt="remove selected User" /><br/>
						<input type="button" value="&raquo;&raquo;" onclick="optUser.transferAllRight()"  alt="remove all User" />
						<td width="45%">
							<select name="user_choice" multiple style="width:100%" size="10" onDblClick="optUser.transferLeft()">
							<logic:iterate id="user" name="userSetList" type="com.app.docmgr.model.User">
								<option value="<bean:write name="user" property="id"/>"><bean:write name="user" property="name"/></option>
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

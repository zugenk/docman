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
<TITLE><bean:message key="page.Bookmark.CreateConfirm"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 06-10-2017 22:19:39
 */
-->
</HEAD>

<BODY>
<form name="bookmark" action="bookmark.do" method="POST">
	<input type="hidden" name="action"/>
	<%@ include file="../common/header.jsp" %>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
	
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="3" class="titleHeader">
				<bean:message key="page.Bookmark.CreateConfirm"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="bookmark.url.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="bookmark" property="url"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="bookmark" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.note.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="bookmark" property="note"/></td>
		</tr>
 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="bookmark.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="bookmark" property="createdDate" format="dd/MM/yyyy"/></td>
		</tr>
 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="bookmark.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="bookmark" property="createdBy"/></td>
		</tr>
 <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="bookmark.bookmarkType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark" property="bookmarkType">				
					<bean:write name="bookmark" property="bookmarkType.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="bookmark.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark" property="status">				
					<bean:write name="bookmark" property="status.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
  <% */ %> 		<tr>
			<td width="150"><b><bean:message key="bookmark.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark" property="owner">				
					<bean:write name="bookmark" property="owner.name"/>
				</logic:notEmpty>	
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.confirm"/>" onclick="this.form.action.value='create_ok'" />
				&nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='create';this.form.submit()" />
			</td>
		</tr>

	</table>	
<!-- set one to many start -->
<!-- end one to many start -->	
	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

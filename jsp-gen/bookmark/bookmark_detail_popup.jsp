<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.Bookmark.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<BODY>
<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="bookmark" action="bookmark.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Bookmark.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
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
		<tr>
			<td width="150"><b><bean:message key="bookmark.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="bookmark" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="bookmark" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.bookmarkType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark"	property="bookmarkType">			
					<bean:write name="bookmark" property="bookmarkType.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark"	property="status">			
					<bean:write name="bookmark" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark"	property="owner">			
					<bean:write name="bookmark" property="owner.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<input type="button" value="<bean:message key="button.close" />" onclick="javascript:window.close();"/>
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->		

</form>
</BODY>
</html:html>

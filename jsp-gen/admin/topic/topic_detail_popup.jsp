<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<HEAD>
<TITLE><bean:message key="page.Topic.DetailPopup"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
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
<LINK href="../template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="../template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>

<form name="topic" action="topic.do" method="POST">
	<input type="hidden" name="action"/>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Topic.DetailPopup"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.icon.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="icon"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.numberOfLike.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="numberOfLike"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.numberOfPost.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="numberOfPost"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="topic" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="topic" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.filterCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="topic" property="filterCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="topic"	property="status">			
					<bean:write name="topic" property="status.id"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.forum.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="topic"	property="forum">			
					<bean:write name="topic" property="forum.id"/>
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
	<br>
	<table border="0" width="98%" align="center">	
		<tr class="title">
			<td><bean:message key="topic.subscribers.key"/></td>
		</tr>
		<tr>
			<td align=center>
				<div style="overflow:auto; height:110" >
				<table width="90%" >
					<logic:iterate id="user" name="userSet" type="com.app.docmgr.model.User">
						<tr><td><bean:write name="user" property="name"/></td></tr>
					</logic:iterate>	
				</table>
				</div>
			</td>
		</tr>
	</table>
<!-- set one to many start -->
<!-- end one to many start -->		

</form>
</BODY>
</html:html>

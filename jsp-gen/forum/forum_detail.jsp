<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>
<%@ page import="com.app.docmgr.model.User" %>
<%@ page import="com.app.docmgr.service.UserService" %>
<%@ page import="java.util.List" %>

<html:html>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
	List privilegeList = (List) request.getSession().getAttribute("privilegeList");
%>
<HEAD>
<TITLE><bean:message key="page.Forum.Detail"/></TITLE>
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
<form name="forum" action="forum.do" method="POST">
	<input type="hidden" name="action"/>
	<%@ include file="../common/header.jsp" %>
	<TABLE border="0" width="98%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Forum.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.icon.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="icon"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.address.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="address"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="forum" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="forum" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.filterCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="forum" property="filterCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.forumType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="forum"	property="forumType">			
					<bean:write name="forum" property="forumType.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="forum.parentForum.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="forum"	property="parentForum">			
					<bean:write name="forum" property="parentForum.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.ForumAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("FORUM_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.ForumAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("FORUM_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
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

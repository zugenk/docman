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
	User loginUser=(User) request.getSession().getAttribute("login.user");
%>
<HEAD>
<TITLE><bean:message key="page.Topic.Detail"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
 */
-->
</HEAD>

<BODY>
<form name="topic" action="topic.do" method="POST">
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
				<bean:message key="page.Topic.Detail"/>
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
					<bean:write name="topic" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.forum.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="topic"	property="forum">			
					<bean:write name="topic" property="forum.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="topic.latestMessage.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="topic"	property="latestMessage">			
					<bean:write name="topic" property="latestMessage.content"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("edit")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("delete")) { 
						if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("submit")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("approve")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("reject")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("pending")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("process")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("activate")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("close")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("archive")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("remove")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("block")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.admin.action.TopicAction.allowableAction.contains("cancel")) { 
						if (com.app.docmgr.service.UserService.getInstance().hasPrivilege(loginUser,"ADMIN:TOPIC_CANCEL")) { %>
				<input type="button" value="<bean:message key="button.cancel"/>" onclick="this.form.action.value='cancel_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='list';this.form.submit()" />
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
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

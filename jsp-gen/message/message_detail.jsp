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
<TITLE><bean:message key="page.Message.Detail"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 05-11-2017 15:05:21
 */
-->
</HEAD>

<BODY>
<form name="message" action="message.do" method="POST">
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
				<bean:message key="page.Message.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.content.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="message" property="content"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="message" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="message" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="message" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="message" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.filterCode.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="message" property="filterCode"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.postType.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="message"	property="postType">			
					<bean:write name="message" property="postType.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="message"	property="status">			
					<bean:write name="message" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.topic.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="message"	property="topic">			
					<bean:write name="message" property="topic.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="message.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="message"	property="parent">			
					<bean:write name="message" property="parent.content"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.MessageAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("MESSAGE_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("MESSAGE_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("MESSAGE_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("MESSAGE_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("MESSAGE_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("MESSAGE_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("MESSAGE_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("MESSAGE_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("archive")) { 
						if (privilegeList.contains("MESSAGE_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("MESSAGE_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("MESSAGE_CANCEL")) { %>
				<input type="button" value="<bean:message key="button.cancel"/>" onclick="this.form.action.value='cancel_confirm';this.form.submit()" />
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

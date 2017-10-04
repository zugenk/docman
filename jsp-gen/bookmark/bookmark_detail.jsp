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
<TITLE><bean:message key="page.Bookmark.Detail"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
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
			<td class="titleHeader" colspan="3" >
				<bean:message key="page.Bookmark.Detail"/>
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
					<bean:write name="bookmark" property="bookmarkType.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark"	property="status">			
					<bean:write name="bookmark" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="bookmark.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="bookmark"	property="owner">			
					<bean:write name="bookmark" property="owner.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("BOOKMARK_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("BOOKMARK_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("BOOKMARK_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("BOOKMARK_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("BOOKMARK_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("BOOKMARK_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("BOOKMARK_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("BOOKMARK_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("BOOKMARK_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("BOOKMARK_CANCEL")) { %>
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

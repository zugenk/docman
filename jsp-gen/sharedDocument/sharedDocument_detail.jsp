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
<TITLE><bean:message key="page.SharedDocument.Detail"/></TITLE>
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
<form name="sharedDocument" action="sharedDocument.do" method="POST">
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
				<bean:message key="page.SharedDocument.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.grantAction.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="grantAction"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="sharedDocument" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="sharedDocument" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.document.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="document">			
					<bean:write name="sharedDocument" property="document.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.user.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="user">			
					<bean:write name="sharedDocument" property="user.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="sharedDocument.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="sharedDocument"	property="status">			
					<bean:write name="sharedDocument" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SharedDocumentAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("SHARED_DOCUMENT_CANCEL")) { %>
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

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
<TITLE><bean:message key="page.Document.Detail"/></TITLE>
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
<form name="document" action="document.do" method="POST">
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
				<bean:message key="page.Document.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.contentType.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="contentType"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentNumber.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentNumber"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.repositoryId.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="repositoryId"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.documentVersion.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="documentVersion"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.tag.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="tag"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.priority.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="priority"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="document" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="document" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="document" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="securityLevel">			
					<bean:write name="document" property="securityLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.owner.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="owner">			
					<bean:write name="document" property="owner.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="status">			
					<bean:write name="document" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.folder.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="folder">			
					<bean:write name="document" property="folder.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="document.parent.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="document"	property="parent">			
					<bean:write name="document" property="parent.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.DocumentAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("DOCUMENT_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("DOCUMENT_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("DOCUMENT_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("DOCUMENT_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("DOCUMENT_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("DOCUMENT_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("DOCUMENT_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("activate")) { 
						if (privilegeList.contains("DOCUMENT_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("DOCUMENT_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("archive")) { 
						if (privilegeList.contains("DOCUMENT_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("DOCUMENT_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("block")) { 
						if (privilegeList.contains("DOCUMENT_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.DocumentAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("DOCUMENT_CANCEL")) { %>
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

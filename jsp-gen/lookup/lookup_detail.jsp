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
<TITLE><bean:message key="page.Lookup.Detail"/></TITLE>
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
<form name="lookup" action="lookup.do" method="POST">
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
				<bean:message key="page.Lookup.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.type.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="type"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.code.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="code"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.name.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="name"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.priority.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="priority"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.shortname.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="shortname"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.filter.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="lookup" property="filter"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="lookup.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="lookup"	property="status">			
					<bean:write name="lookup" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.LookupAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("LOOKUP_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("LOOKUP_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("LOOKUP_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("LOOKUP_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("LOOKUP_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("LOOKUP_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("LOOKUP_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("LOOKUP_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("archive")) { 
						if (privilegeList.contains("LOOKUP_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("LOOKUP_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("LOOKUP_CANCEL")) { %>
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

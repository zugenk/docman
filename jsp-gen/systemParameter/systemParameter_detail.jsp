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
<TITLE><bean:message key="page.SystemParameter.Detail"/></TITLE>
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
<form name="systemParameter" action="systemParameter.do" method="POST">
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
				<bean:message key="page.SystemParameter.Detail"/>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.vgroup.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="vgroup"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.parameter.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="parameter"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.svalue.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="svalue"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.description.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="description"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameter" property="createdDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.createdBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="createdBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>
			<td ><bean:write name="systemParameter" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>
			<td><bean:write name="systemParameter" property="lastUpdatedBy"/></td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.status.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameter"	property="status">			
					<bean:write name="systemParameter" property="status.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="systemParameter.userLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<logic:notEmpty name="systemParameter"	property="userLevel">			
					<bean:write name="systemParameter" property="userLevel.name"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td>
				<% 
				if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("edit")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_EDIT")) { %>

					<input type="submit" value="<bean:message key="button.edit"/>" onclick="this.form.action.value='edit'" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("delete")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_DELETE")) { %>
					<input type="button" value="<bean:message key="button.delete"/>" onclick="this.form.action.value='delete_confirm';this.form.submit()" />
					&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("submit")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_SUBMIT")) { %>
				<input type="button" value="<bean:message key="button.submit"/>" onclick="this.form.action.value='submit_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("approve")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_APPROVE")) { %>
				<input type="button" value="<bean:message key="button.approve"/>" onclick="this.form.action.value='approve_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("reject")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_REJECT")) { %>
				<input type="button" value="<bean:message key="button.reject"/>" onclick="this.form.action.value='reject_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("pending")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_PENDING")) { %>
				<input type="button" value="<bean:message key="button.pending"/>" onclick="this.form.action.value='pending_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("process")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_PROCESS")) { %>
				<input type="button" value="<bean:message key="button.process"/>" onclick="this.form.action.value='process_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("activate")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_ACTIVATE")) { %>
				<input type="button" value="<bean:message key="button.activate"/>" onclick="this.form.action.value='activate_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("close")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_CLOSE")) { %>
				<input type="button" value="<bean:message key="button.close"/>" onclick="this.form.action.value='close_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("archive")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_ARCHIVE")) { %>
				<input type="button" value="<bean:message key="button.archive"/>" onclick="this.form.action.value='archive_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("remove")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_REMOVE")) { %>
				<input type="button" value="<bean:message key="button.remove"/>" onclick="this.form.action.value='remove_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("block")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_BLOCK")) { %>
				<input type="button" value="<bean:message key="button.block"/>" onclick="this.form.action.value='block_confirm';this.form.submit()" />
				&nbsp;
				<% 		}
					} %>
				<% if(com.app.docmgr.action.SystemParameterAction.allowableAction.contains("cancel")) { 
						if (privilegeList.contains("SYSTEM_PARAMETER_CANCEL")) { %>
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

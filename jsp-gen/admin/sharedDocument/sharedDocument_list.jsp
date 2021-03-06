<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>
<%@ page import="com.app.docmgr.model.User" %>
<%@ page import="com.app.docmgr.service.UserService" %>
<%@ page import="java.util.List" %>
<%
	int sequence = 0;
%>
<html:html>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
	boolean allowDetail= false;
	User loginUser=(User) request.getSession().getAttribute("login.user");
	if(com.app.docmgr.admin.action.SharedDocumentAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:SHARED_DOCUMENT_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.SharedDocument.List"/></TITLE>
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
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.sharedDocument.start.value=start;
			document.forms.sharedDocument.count.value=count;
			document.forms.sharedDocument.submit();
		}

		function doReset(){
			document.forms.sharedDocument.sharedDocument_grantAction_filter.value="";
			document.forms.sharedDocument.sharedDocument_createdDate_filter_start.value="";
			document.forms.sharedDocument.sharedDocument_createdDate_filter_end.value="";
			document.forms.sharedDocument.sharedDocument_createdBy_filter.value="";
			document.forms.sharedDocument.sharedDocument_lastUpdatedDate_filter_start.value="";
			document.forms.sharedDocument.sharedDocument_lastUpdatedDate_filter_end.value="";
			document.forms.sharedDocument.sharedDocument_lastUpdatedBy_filter.value="";
			document.forms.sharedDocument.sharedDocument_document_filter.value="";
			document.forms.sharedDocument.sharedDocument_targetUser_filter.value="";
			document.forms.sharedDocument.sharedDocument_targetOrganization_filter.value="";
			document.forms.sharedDocument.sharedDocument_status_filter.value="";
			document.forms.sharedDocument.submit();
		}

		function doOrder(field, type){
			document.forms.sharedDocument.sharedDocument_fieldOrder.value=field;
			document.forms.sharedDocument.sharedDocument_orderType.value=type;
			document.forms.sharedDocument.submit();
		}

	-->
</SCRIPT>
<form name="sharedDocument" method="post" action="sharedDocument.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="sharedDocument_fieldOrder" value="<bean:write name="sharedDocument_fieldOrder"/>">
<INPUT type="hidden" name="sharedDocument_orderType" value="<bean:write name="sharedDocument_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="15" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="15" align="left">
			<bean:message key="page.SharedDocument.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="15" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="15" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="sharedDocument.grantAction.key"/>
			<logic:equal name="sharedDocument_fieldOrder" value="grant_action">
				<logic:equal name="sharedDocument_orderType" value="ASC">
					<a href="#" onclick="doOrder('grant_action', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="sharedDocument_orderType" value="DESC">
					<a href="#" onclick="doOrder('grant_action', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="sharedDocument_fieldOrder" value="grant_action">
				<a href="#" onclick="doOrder('grant_action', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="sharedDocument.createdDate.key"/>
			<logic:equal name="sharedDocument_fieldOrder" value="created_date">
				<logic:equal name="sharedDocument_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="sharedDocument_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="sharedDocument_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="sharedDocument.createdBy.key"/>
			<logic:equal name="sharedDocument_fieldOrder" value="created_by">
				<logic:equal name="sharedDocument_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="sharedDocument_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="sharedDocument_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="sharedDocument.lastUpdatedDate.key"/>
			<logic:equal name="sharedDocument_fieldOrder" value="last_updated_date">
				<logic:equal name="sharedDocument_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="sharedDocument_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="sharedDocument_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="sharedDocument.lastUpdatedBy.key"/>
			<logic:equal name="sharedDocument_fieldOrder" value="last_updated_by">
				<logic:equal name="sharedDocument_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="sharedDocument_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="sharedDocument_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="sharedDocument.document.key"/></td>
		<td><bean:message key="sharedDocument.targetUser.key"/></td>
		<td><bean:message key="sharedDocument.targetOrganization.key"/></td>
		<td><bean:message key="sharedDocument.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="sharedDocumentList" scope="request" type="com.app.docmgr.model.SharedDocument">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="grantAction"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td >
				<logic:notEmpty name="element"	property="document">								
					<bean:write name="element" property="document.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="targetUser">								
					<bean:write name="element" property="targetUser.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="targetOrganization">								
					<bean:write name="element" property="targetOrganization.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="status">								
					<bean:write name="element" property="status.name"/>
				</logic:notEmpty>	
			
		</td>

			<td width="20">
				<% if(allowDetail) { %>
				<input type=button value='<bean:message key="button.detail"/>' onclick="this.form.id.value='<bean:write name="element" property="id"/>';this.form.action.value='detail';this.form.submit()" />
				<% } %>
			</td>
		</tr>		
	</logic:iterate> 
	<tr>
		<td colspan="15" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="15" align="right">
		<% if(com.app.docmgr.admin.action.SharedDocumentAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:SHARED_DOCUMENT_CREATE")) { %>
			<input type="button" value="<bean:message key="button.add"/>" onclick="this.form.action.value='create';this.form.submit()" />
		<% 		}
			} %>
		</td>
	</tr>
</TABLE>
<br>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr class="title">
		<td colspan="3" align="center">F I L T E R</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="sharedDocument.grantAction.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="sharedDocument_grantAction_filter" value="<bean:write name="sharedDocument_grantAction_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="sharedDocument.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('sharedDocument_createdDate_filter_start', 'sharedDocument_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('sharedDocument_createdDate_filter_start', 'sharedDocument_createdDate_filter_start_cal', null);" name="sharedDocument_createdDate_filter_start" value="<bean:write name="sharedDocument_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('sharedDocument_createdDate_filter_end', 'sharedDocument_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('sharedDocument_createdDate_filter_end', 'sharedDocument_createdDate_filter_end_cal', null);" name="sharedDocument_createdDate_filter_end" value="<bean:write name="sharedDocument_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="sharedDocument_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="sharedDocument_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="sharedDocument.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="sharedDocument_createdBy_filter" value="<bean:write name="sharedDocument_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="sharedDocument.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('sharedDocument_lastUpdatedDate_filter_start', 'sharedDocument_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('sharedDocument_lastUpdatedDate_filter_start', 'sharedDocument_lastUpdatedDate_filter_start_cal', null);" name="sharedDocument_lastUpdatedDate_filter_start" value="<bean:write name="sharedDocument_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('sharedDocument_lastUpdatedDate_filter_end', 'sharedDocument_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('sharedDocument_lastUpdatedDate_filter_end', 'sharedDocument_lastUpdatedDate_filter_end_cal', null);" name="sharedDocument_lastUpdatedDate_filter_end" value="<bean:write name="sharedDocument_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="sharedDocument_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="sharedDocument_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="sharedDocument.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="sharedDocument_lastUpdatedBy_filter" value="<bean:write name="sharedDocument_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="sharedDocument.document.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  sharedDocument_document_filter_value = (String)request.getSession().getAttribute("sharedDocument_document_filter");
					if("".equals(sharedDocument_document_filter_value)) sharedDocument_document_filter_value = "0";
				%>				
				<select name="sharedDocument_document_filter">
					<option value=""></option>
					<logic:iterate id="documentElement" name="documentList"  type="com.app.docmgr.model.Document">
						
						<option value="<bean:write name="documentElement" property="id"/>" 
							<%
								Long sharedDocument_document_id = documentElement.getId();							
								Long sharedDocument_document_filter_value_c = new Long(sharedDocument_document_filter_value);
								if(sharedDocument_document_filter_value_c.equals(sharedDocument_document_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="documentElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="sharedDocument.targetUser.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  sharedDocument_targetUser_filter_value = (String)request.getSession().getAttribute("sharedDocument_targetUser_filter");
					if("".equals(sharedDocument_targetUser_filter_value)) sharedDocument_targetUser_filter_value = "0";
				%>				
				<select name="sharedDocument_targetUser_filter">
					<option value=""></option>
					<logic:iterate id="targetUserElement" name="targetUserList"  type="com.app.docmgr.model.User">
						
						<option value="<bean:write name="targetUserElement" property="id"/>" 
							<%
								Long sharedDocument_targetUser_id = targetUserElement.getId();							
								Long sharedDocument_targetUser_filter_value_c = new Long(sharedDocument_targetUser_filter_value);
								if(sharedDocument_targetUser_filter_value_c.equals(sharedDocument_targetUser_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="targetUserElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="sharedDocument.targetOrganization.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  sharedDocument_targetOrganization_filter_value = (String)request.getSession().getAttribute("sharedDocument_targetOrganization_filter");
					if("".equals(sharedDocument_targetOrganization_filter_value)) sharedDocument_targetOrganization_filter_value = "0";
				%>				
				<select name="sharedDocument_targetOrganization_filter">
					<option value=""></option>
					<logic:iterate id="targetOrganizationElement" name="targetOrganizationList"  type="com.app.docmgr.model.Organization">
						
						<option value="<bean:write name="targetOrganizationElement" property="id"/>" 
							<%
								Long sharedDocument_targetOrganization_id = targetOrganizationElement.getId();							
								Long sharedDocument_targetOrganization_filter_value_c = new Long(sharedDocument_targetOrganization_filter_value);
								if(sharedDocument_targetOrganization_filter_value_c.equals(sharedDocument_targetOrganization_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="targetOrganizationElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="sharedDocument.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  sharedDocument_status_filter_value = (String)request.getSession().getAttribute("sharedDocument_status_filter");
					if("".equals(sharedDocument_status_filter_value)) sharedDocument_status_filter_value = "0";
				%>				
				<select name="sharedDocument_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long sharedDocument_status_id = statusElement.getId();							
								Long sharedDocument_status_filter_value_c = new Long(sharedDocument_status_filter_value);
								if(sharedDocument_status_filter_value_c.equals(sharedDocument_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>

	<tr>
		<td width="150"></td>
		<td width="10"></td>
		<td>
			<input type="submit" value="<bean:message key="button.search"/>"  />
			&nbsp;
			<input type="button" value='<bean:message key="button.reset"/>' onclick="doReset()" />			    	
		</td>
	</tr>
</table>
<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

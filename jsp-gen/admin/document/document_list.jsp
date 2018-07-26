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
	if(com.app.docmgr.admin.action.DocumentAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Document.List"/></TITLE>
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
			document.forms.document.start.value=start;
			document.forms.document.count.value=count;
			document.forms.document.submit();
		}

		function doReset(){
			document.forms.document.document_name_filter.value="";
			document.forms.document.document_documentType_filter.value="";
			document.forms.document.document_contentType_filter.value="";
			document.forms.document.document_documentNumber_filter.value="";
			document.forms.document.document_repositoryId_filter.value="";
			document.forms.document.document_documentVersion_filter.value="";
			document.forms.document.document_description_filter.value="";
			document.forms.document.document_tag_filter.value="";
			document.forms.document.document_createdDate_filter_start.value="";
			document.forms.document.document_createdDate_filter_end.value="";
			document.forms.document.document_createdBy_filter.value="";
			document.forms.document.document_lastUpdatedDate_filter_start.value="";
			document.forms.document.document_lastUpdatedDate_filter_end.value="";
			document.forms.document.document_lastUpdatedBy_filter.value="";
			document.forms.document.document_securityLevel_filter.value="";
			document.forms.document.document_owner_filter.value="";
			document.forms.document.document_status_filter.value="";
			document.forms.document.document_folder_filter.value="";
			document.forms.document.document_parent_filter.value="";
			document.forms.document.submit();
		}

		function doOrder(field, type){
			document.forms.document.document_fieldOrder.value=field;
			document.forms.document.document_orderType.value=type;
			document.forms.document.submit();
		}

	-->
</SCRIPT>
<form name="document" method="post" action="document.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="document_fieldOrder" value="<bean:write name="document_fieldOrder"/>">
<INPUT type="hidden" name="document_orderType" value="<bean:write name="document_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="23" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="23" align="left">
			<bean:message key="page.Document.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="23" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="23" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="document.name.key"/>
			<logic:equal name="document_fieldOrder" value="name">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.documentType.key"/>
			<logic:equal name="document_fieldOrder" value="document_type">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_type', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="document_type">
				<a href="#" onclick="doOrder('document_type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.contentType.key"/>
			<logic:equal name="document_fieldOrder" value="content_type">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('content_type', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('content_type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="content_type">
				<a href="#" onclick="doOrder('content_type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.documentNumber.key"/>
			<logic:equal name="document_fieldOrder" value="document_number">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_number', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="document_number">
				<a href="#" onclick="doOrder('document_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.repositoryId.key"/>
			<logic:equal name="document_fieldOrder" value="repository_id">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('repository_id', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('repository_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="repository_id">
				<a href="#" onclick="doOrder('repository_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.documentVersion.key"/>
			<logic:equal name="document_fieldOrder" value="document_version">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_version', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_version', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="document_version">
				<a href="#" onclick="doOrder('document_version', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.description.key"/>
			<logic:equal name="document_fieldOrder" value="description">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.tag.key"/>
			<logic:equal name="document_fieldOrder" value="tag">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('tag', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('tag', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="tag">
				<a href="#" onclick="doOrder('tag', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.priority.key"/>
			<logic:equal name="document_fieldOrder" value="priority">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('priority', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('priority', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="priority">
				<a href="#" onclick="doOrder('priority', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.createdDate.key"/>
			<logic:equal name="document_fieldOrder" value="created_date">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.createdBy.key"/>
			<logic:equal name="document_fieldOrder" value="created_by">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.lastUpdatedDate.key"/>
			<logic:equal name="document_fieldOrder" value="last_updated_date">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="document.lastUpdatedBy.key"/>
			<logic:equal name="document_fieldOrder" value="last_updated_by">
				<logic:equal name="document_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="document_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="document_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="document.securityLevel.key"/></td>
		<td><bean:message key="document.owner.key"/></td>
		<td><bean:message key="document.status.key"/></td>
		<td><bean:message key="document.folder.key"/></td>
		<td><bean:message key="document.parent.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="documentList" scope="request" type="com.app.docmgr.model.Document">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="documentType"/></td>
		<td><bean:write name="element" property="contentType"/></td>
		<td><bean:write name="element" property="documentNumber"/></td>
		<td><bean:write name="element" property="repositoryId"/></td>
		<td><bean:write name="element" property="documentVersion"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td><bean:write name="element" property="tag"/></td>
		<td><bean:write name="element" property="priority"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td >
				<logic:notEmpty name="element"	property="securityLevel">								
					<bean:write name="element" property="securityLevel.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="owner">								
					<bean:write name="element" property="owner.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="status">								
					<bean:write name="element" property="status.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="folder">								
					<bean:write name="element" property="folder.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="parent">								
					<bean:write name="element" property="parent.name"/>
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
		<td colspan="23" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="23" align="right">
		<% if(com.app.docmgr.admin.action.DocumentAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:DOCUMENT_CREATE")) { %>
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
		<td width="150"><bean:message key="document.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_name_filter" value="<bean:write name="document_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.documentType.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_documentType_filter" value="<bean:write name="document_documentType_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.contentType.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_contentType_filter" value="<bean:write name="document_contentType_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.documentNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_documentNumber_filter" value="<bean:write name="document_documentNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.repositoryId.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_repositoryId_filter" value="<bean:write name="document_repositoryId_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.documentVersion.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_documentVersion_filter" value="<bean:write name="document_documentVersion_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_description_filter" value="<bean:write name="document_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.tag.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_tag_filter" value="<bean:write name="document_tag_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="document.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('document_createdDate_filter_start', 'document_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('document_createdDate_filter_start', 'document_createdDate_filter_start_cal', null);" name="document_createdDate_filter_start" value="<bean:write name="document_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('document_createdDate_filter_end', 'document_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('document_createdDate_filter_end', 'document_createdDate_filter_end_cal', null);" name="document_createdDate_filter_end" value="<bean:write name="document_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="document_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="document_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_createdBy_filter" value="<bean:write name="document_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="document.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('document_lastUpdatedDate_filter_start', 'document_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('document_lastUpdatedDate_filter_start', 'document_lastUpdatedDate_filter_start_cal', null);" name="document_lastUpdatedDate_filter_start" value="<bean:write name="document_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('document_lastUpdatedDate_filter_end', 'document_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('document_lastUpdatedDate_filter_end', 'document_lastUpdatedDate_filter_end_cal', null);" name="document_lastUpdatedDate_filter_end" value="<bean:write name="document_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="document_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="document_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="document.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="document_lastUpdatedBy_filter" value="<bean:write name="document_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="document.securityLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  document_securityLevel_filter_value = (String)request.getSession().getAttribute("document_securityLevel_filter");
					if("".equals(document_securityLevel_filter_value)) document_securityLevel_filter_value = "0";
				%>				
				<select name="document_securityLevel_filter">
					<option value=""></option>
					<logic:iterate id="securityLevelElement" name="securityLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="securityLevelElement" property="id"/>" 
							<%
								Long document_securityLevel_id = securityLevelElement.getId();							
								Long document_securityLevel_filter_value_c = new Long(document_securityLevel_filter_value);
								if(document_securityLevel_filter_value_c.equals(document_securityLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="securityLevelElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.owner.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  document_owner_filter_value = (String)request.getSession().getAttribute("document_owner_filter");
					if("".equals(document_owner_filter_value)) document_owner_filter_value = "0";
				%>				
				<select name="document_owner_filter">
					<option value=""></option>
					<logic:iterate id="ownerElement" name="ownerList"  type="com.app.docmgr.model.User">
						
						<option value="<bean:write name="ownerElement" property="id"/>" 
							<%
								Long document_owner_id = ownerElement.getId();							
								Long document_owner_filter_value_c = new Long(document_owner_filter_value);
								if(document_owner_filter_value_c.equals(document_owner_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="ownerElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  document_status_filter_value = (String)request.getSession().getAttribute("document_status_filter");
					if("".equals(document_status_filter_value)) document_status_filter_value = "0";
				%>				
				<select name="document_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long document_status_id = statusElement.getId();							
								Long document_status_filter_value_c = new Long(document_status_filter_value);
								if(document_status_filter_value_c.equals(document_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.folder.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  document_folder_filter_value = (String)request.getSession().getAttribute("document_folder_filter");
					if("".equals(document_folder_filter_value)) document_folder_filter_value = "0";
				%>				
				<select name="document_folder_filter">
					<option value=""></option>
					<logic:iterate id="folderElement" name="folderList"  type="com.app.docmgr.model.Folder">
						
						<option value="<bean:write name="folderElement" property="id"/>" 
							<%
								Long document_folder_id = folderElement.getId();							
								Long document_folder_filter_value_c = new Long(document_folder_filter_value);
								if(document_folder_filter_value_c.equals(document_folder_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="folderElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="document.parent.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  document_parent_filter_value = (String)request.getSession().getAttribute("document_parent_filter");
					if("".equals(document_parent_filter_value)) document_parent_filter_value = "0";
				%>				
				<select name="document_parent_filter">
					<option value=""></option>
					<logic:iterate id="parentElement" name="parentList"  type="com.app.docmgr.model.Document">
						
						<option value="<bean:write name="parentElement" property="id"/>" 
							<%
								Long document_parent_id = parentElement.getId();							
								Long document_parent_filter_value_c = new Long(document_parent_filter_value);
								if(document_parent_filter_value_c.equals(document_parent_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="parentElement" property="name"/></option>
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

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
	List privilegeList = (List) request.getSession().getAttribute("privilegeList");
	if(com.app.docmgr.action.DocumentHistoryAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("DOCUMENT_HISTORY_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.DocumentHistory.List"/></TITLE>
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
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.documentHistory.start.value=start;
			document.forms.documentHistory.count.value=count;
			document.forms.documentHistory.submit();
		}

		function doReset(){
			document.forms.documentHistory.documentHistory_historyDate_filter_start.value="";
			document.forms.documentHistory.documentHistory_historyDate_filter_end.value="";
			document.forms.documentHistory.documentHistory_historyBy_filter.value="";
			document.forms.documentHistory.documentHistory_name_filter.value="";
			document.forms.documentHistory.documentHistory_documentType_filter.value="";
			document.forms.documentHistory.documentHistory_contentType_filter.value="";
			document.forms.documentHistory.documentHistory_documentNumber_filter.value="";
			document.forms.documentHistory.documentHistory_repositoryId_filter.value="";
			document.forms.documentHistory.documentHistory_documentVersion_filter.value="";
			document.forms.documentHistory.documentHistory_description_filter.value="";
			document.forms.documentHistory.documentHistory_createdDate_filter_start.value="";
			document.forms.documentHistory.documentHistory_createdDate_filter_end.value="";
			document.forms.documentHistory.documentHistory_createdBy_filter.value="";
			document.forms.documentHistory.documentHistory_lastUpdatedDate_filter_start.value="";
			document.forms.documentHistory.documentHistory_lastUpdatedDate_filter_end.value="";
			document.forms.documentHistory.documentHistory_lastUpdatedBy_filter.value="";
			document.forms.documentHistory.documentHistory_securityLevel_filter.value="";
			document.forms.documentHistory.documentHistory_owner_filter.value="";
			document.forms.documentHistory.documentHistory_status_filter.value="";
			document.forms.documentHistory.documentHistory_parentFolder_filter.value="";
			document.forms.documentHistory.documentHistory_parentDocument_filter.value="";
			document.forms.documentHistory.submit();
		}

		function doOrder(field, type){
			document.forms.documentHistory.documentHistory_fieldOrder.value=field;
			document.forms.documentHistory.documentHistory_orderType.value=type;
			document.forms.documentHistory.submit();
		}

	-->
</SCRIPT>
<form name="documentHistory" method="post" action="documentHistory.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="documentHistory_fieldOrder" value="<bean:write name="documentHistory_fieldOrder"/>">
<INPUT type="hidden" name="documentHistory_orderType" value="<bean:write name="documentHistory_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="25" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="25" align="left">
			<bean:message key="page.DocumentHistory.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="25" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="25" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="documentHistory.historyDate.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="history_date">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('history_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('history_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="history_date">
				<a href="#" onclick="doOrder('history_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.historyBy.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="history_by">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('history_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('history_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="history_by">
				<a href="#" onclick="doOrder('history_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.auditTrailId.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="audit_trail_id">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('audit_trail_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('audit_trail_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="audit_trail_id">
				<a href="#" onclick="doOrder('audit_trail_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.documentId.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="document_id">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="document_id">
				<a href="#" onclick="doOrder('document_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.name.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="name">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.documentType.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="document_type">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_type', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="document_type">
				<a href="#" onclick="doOrder('document_type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.contentType.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="content_type">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('content_type', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('content_type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="content_type">
				<a href="#" onclick="doOrder('content_type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.documentNumber.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="document_number">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_number', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="document_number">
				<a href="#" onclick="doOrder('document_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.repositoryId.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="repository_id">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('repository_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('repository_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="repository_id">
				<a href="#" onclick="doOrder('repository_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.documentVersion.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="document_version">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('document_version', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('document_version', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="document_version">
				<a href="#" onclick="doOrder('document_version', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.description.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="description">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.createdDate.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="created_date">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.createdBy.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="created_by">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.lastUpdatedDate.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="last_updated_date">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="documentHistory.lastUpdatedBy.key"/>
			<logic:equal name="documentHistory_fieldOrder" value="last_updated_by">
				<logic:equal name="documentHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="documentHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="documentHistory_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="documentHistory.securityLevel.key"/></td>
		<td><bean:message key="documentHistory.owner.key"/></td>
		<td><bean:message key="documentHistory.status.key"/></td>
		<td><bean:message key="documentHistory.parentFolder.key"/></td>
		<td><bean:message key="documentHistory.parentDocument.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="documentHistoryList" scope="request" type="com.app.docmgr.model.DocumentHistory">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="historyDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="historyBy"/></td>
		<td><bean:write name="element" property="auditTrailId"/></td>
		<td><bean:write name="element" property="documentId"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="documentType"/></td>
		<td><bean:write name="element" property="contentType"/></td>
		<td><bean:write name="element" property="documentNumber"/></td>
		<td><bean:write name="element" property="repositoryId"/></td>
		<td><bean:write name="element" property="documentVersion"/></td>
		<td><bean:write name="element" property="description"/></td>
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
				<logic:notEmpty name="element"	property="parentFolder">								
					<bean:write name="element" property="parentFolder.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="parentDocument">								
					<bean:write name="element" property="parentDocument.name"/>
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
		<td colspan="25" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="25" align="right">
		<% if(com.app.docmgr.action.DocumentHistoryAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("DOCUMENT_HISTORY_CREATE")) { %>
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
		<td width="150" valign="top"><bean:message key="documentHistory.historyDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('documentHistory_historyDate_filter_start', 'documentHistory_historyDate_filter_start_cal', null);"  onKeyDown="drawCalendar('documentHistory_historyDate_filter_start', 'documentHistory_historyDate_filter_start_cal', null);" name="documentHistory_historyDate_filter_start" value="<bean:write name="documentHistory_historyDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('documentHistory_historyDate_filter_end', 'documentHistory_historyDate_filter_end_cal', null);"  onKeyDown="drawCalendar('documentHistory_historyDate_filter_end', 'documentHistory_historyDate_filter_end_cal', null);" name="documentHistory_historyDate_filter_end" value="<bean:write name="documentHistory_historyDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="documentHistory_historyDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="documentHistory_historyDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.historyBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_historyBy_filter" value="<bean:write name="documentHistory_historyBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_name_filter" value="<bean:write name="documentHistory_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.documentType.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_documentType_filter" value="<bean:write name="documentHistory_documentType_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.contentType.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_contentType_filter" value="<bean:write name="documentHistory_contentType_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.documentNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_documentNumber_filter" value="<bean:write name="documentHistory_documentNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.repositoryId.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_repositoryId_filter" value="<bean:write name="documentHistory_repositoryId_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.documentVersion.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_documentVersion_filter" value="<bean:write name="documentHistory_documentVersion_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_description_filter" value="<bean:write name="documentHistory_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="documentHistory.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('documentHistory_createdDate_filter_start', 'documentHistory_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('documentHistory_createdDate_filter_start', 'documentHistory_createdDate_filter_start_cal', null);" name="documentHistory_createdDate_filter_start" value="<bean:write name="documentHistory_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('documentHistory_createdDate_filter_end', 'documentHistory_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('documentHistory_createdDate_filter_end', 'documentHistory_createdDate_filter_end_cal', null);" name="documentHistory_createdDate_filter_end" value="<bean:write name="documentHistory_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="documentHistory_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="documentHistory_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_createdBy_filter" value="<bean:write name="documentHistory_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="documentHistory.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('documentHistory_lastUpdatedDate_filter_start', 'documentHistory_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('documentHistory_lastUpdatedDate_filter_start', 'documentHistory_lastUpdatedDate_filter_start_cal', null);" name="documentHistory_lastUpdatedDate_filter_start" value="<bean:write name="documentHistory_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('documentHistory_lastUpdatedDate_filter_end', 'documentHistory_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('documentHistory_lastUpdatedDate_filter_end', 'documentHistory_lastUpdatedDate_filter_end_cal', null);" name="documentHistory_lastUpdatedDate_filter_end" value="<bean:write name="documentHistory_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="documentHistory_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="documentHistory_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="documentHistory.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="documentHistory_lastUpdatedBy_filter" value="<bean:write name="documentHistory_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.securityLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  documentHistory_securityLevel_filter_value = (String)request.getSession().getAttribute("documentHistory_securityLevel_filter");
					if("".equals(documentHistory_securityLevel_filter_value)) documentHistory_securityLevel_filter_value = "0";
				%>				
				<select name="documentHistory_securityLevel_filter">
					<option value=""></option>
					<logic:iterate id="securityLevelElement" name="securityLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="securityLevelElement" property="id"/>" 
							<%
								Long documentHistory_securityLevel_id = securityLevelElement.getId();							
								Long documentHistory_securityLevel_filter_value_c = new Long(documentHistory_securityLevel_filter_value);
								if(documentHistory_securityLevel_filter_value_c.equals(documentHistory_securityLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="securityLevelElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.owner.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  documentHistory_owner_filter_value = (String)request.getSession().getAttribute("documentHistory_owner_filter");
					if("".equals(documentHistory_owner_filter_value)) documentHistory_owner_filter_value = "0";
				%>				
				<select name="documentHistory_owner_filter">
					<option value=""></option>
					<logic:iterate id="ownerElement" name="ownerList"  type="com.app.docmgr.model.User">
						
						<option value="<bean:write name="ownerElement" property="id"/>" 
							<%
								Long documentHistory_owner_id = ownerElement.getId();							
								Long documentHistory_owner_filter_value_c = new Long(documentHistory_owner_filter_value);
								if(documentHistory_owner_filter_value_c.equals(documentHistory_owner_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="ownerElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  documentHistory_status_filter_value = (String)request.getSession().getAttribute("documentHistory_status_filter");
					if("".equals(documentHistory_status_filter_value)) documentHistory_status_filter_value = "0";
				%>				
				<select name="documentHistory_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long documentHistory_status_id = statusElement.getId();							
								Long documentHistory_status_filter_value_c = new Long(documentHistory_status_filter_value);
								if(documentHistory_status_filter_value_c.equals(documentHistory_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.parentFolder.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  documentHistory_parentFolder_filter_value = (String)request.getSession().getAttribute("documentHistory_parentFolder_filter");
					if("".equals(documentHistory_parentFolder_filter_value)) documentHistory_parentFolder_filter_value = "0";
				%>				
				<select name="documentHistory_parentFolder_filter">
					<option value=""></option>
					<logic:iterate id="parentFolderElement" name="parentFolderList"  type="com.app.docmgr.model.Folder">
						
						<option value="<bean:write name="parentFolderElement" property="id"/>" 
							<%
								Long documentHistory_parentFolder_id = parentFolderElement.getId();							
								Long documentHistory_parentFolder_filter_value_c = new Long(documentHistory_parentFolder_filter_value);
								if(documentHistory_parentFolder_filter_value_c.equals(documentHistory_parentFolder_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="parentFolderElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="documentHistory.parentDocument.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  documentHistory_parentDocument_filter_value = (String)request.getSession().getAttribute("documentHistory_parentDocument_filter");
					if("".equals(documentHistory_parentDocument_filter_value)) documentHistory_parentDocument_filter_value = "0";
				%>				
				<select name="documentHistory_parentDocument_filter">
					<option value=""></option>
					<logic:iterate id="parentDocumentElement" name="parentDocumentList"  type="com.app.docmgr.model.Document">
						
						<option value="<bean:write name="parentDocumentElement" property="id"/>" 
							<%
								Long documentHistory_parentDocument_id = parentDocumentElement.getId();							
								Long documentHistory_parentDocument_filter_value_c = new Long(documentHistory_parentDocument_filter_value);
								if(documentHistory_parentDocument_filter_value_c.equals(documentHistory_parentDocument_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="parentDocumentElement" property="name"/></option>
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

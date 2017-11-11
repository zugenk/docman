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
	if(com.app.docmgr.action.GenericReportAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("GENERIC_REPORT_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.GenericReport.List"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
 */
-->
</HEAD>
<BODY>
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.genericReport.start.value=start;
			document.forms.genericReport.count.value=count;
			document.forms.genericReport.submit();
		}

		function doReset(){
			document.forms.genericReport.genericReport_name_filter.value="";
			document.forms.genericReport.genericReport_reportFields_filter.value="";
			document.forms.genericReport.genericReport_searchFields_filter.value="";
			document.forms.genericReport.genericReport_reportSql_filter.value="";
			document.forms.genericReport.genericReport_description_filter.value="";
			document.forms.genericReport.genericReport_columnAction_filter.value="";
			document.forms.genericReport.genericReport_status_filter.value="";
			document.forms.genericReport.submit();
		}

		function doOrder(field, type){
			document.forms.genericReport.genericReport_fieldOrder.value=field;
			document.forms.genericReport.genericReport_orderType.value=type;
			document.forms.genericReport.submit();
		}

	-->
</SCRIPT>
<form name="genericReport" method="post" action="genericReport.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="genericReport_fieldOrder" value="<bean:write name="genericReport_fieldOrder"/>">
<INPUT type="hidden" name="genericReport_orderType" value="<bean:write name="genericReport_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="16" align="left">
			<bean:message key="page.GenericReport.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="16" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="16" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="genericReport.name.key"/>
			<logic:equal name="genericReport_fieldOrder" value="name">
				<logic:equal name="genericReport_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="genericReport_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="genericReport_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="genericReport.reportFields.key"/>
			<logic:equal name="genericReport_fieldOrder" value="report_fields">
				<logic:equal name="genericReport_orderType" value="ASC">
					<a href="#" onclick="doOrder('report_fields', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="genericReport_orderType" value="DESC">
					<a href="#" onclick="doOrder('report_fields', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="genericReport_fieldOrder" value="report_fields">
				<a href="#" onclick="doOrder('report_fields', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="genericReport.searchFields.key"/>
			<logic:equal name="genericReport_fieldOrder" value="search_fields">
				<logic:equal name="genericReport_orderType" value="ASC">
					<a href="#" onclick="doOrder('search_fields', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="genericReport_orderType" value="DESC">
					<a href="#" onclick="doOrder('search_fields', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="genericReport_fieldOrder" value="search_fields">
				<a href="#" onclick="doOrder('search_fields', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="genericReport.reportSql.key"/>
			<logic:equal name="genericReport_fieldOrder" value="report_sql">
				<logic:equal name="genericReport_orderType" value="ASC">
					<a href="#" onclick="doOrder('report_sql', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="genericReport_orderType" value="DESC">
					<a href="#" onclick="doOrder('report_sql', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="genericReport_fieldOrder" value="report_sql">
				<a href="#" onclick="doOrder('report_sql', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="genericReport.description.key"/>
			<logic:equal name="genericReport_fieldOrder" value="description">
				<logic:equal name="genericReport_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="genericReport_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="genericReport_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="genericReport.columnAction.key"/>
			<logic:equal name="genericReport_fieldOrder" value="column_action">
				<logic:equal name="genericReport_orderType" value="ASC">
					<a href="#" onclick="doOrder('column_action', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="genericReport_orderType" value="DESC">
					<a href="#" onclick="doOrder('column_action', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="genericReport_fieldOrder" value="column_action">
				<a href="#" onclick="doOrder('column_action', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="genericReport.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="genericReportList" scope="request" type="com.app.docmgr.model.GenericReport">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="reportFields"/></td>
		<td><bean:write name="element" property="searchFields"/></td>
		<td><bean:write name="element" property="reportSql"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td><bean:write name="element" property="columnAction"/></td>
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
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="16" align="right">
		<% if(com.app.docmgr.action.GenericReportAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("GENERIC_REPORT_CREATE")) { %>
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
		<td width="150"><bean:message key="genericReport.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="genericReport_name_filter" value="<bean:write name="genericReport_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="genericReport.reportFields.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="genericReport_reportFields_filter" value="<bean:write name="genericReport_reportFields_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="genericReport.searchFields.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="genericReport_searchFields_filter" value="<bean:write name="genericReport_searchFields_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="genericReport.reportSql.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="genericReport_reportSql_filter" value="<bean:write name="genericReport_reportSql_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="genericReport.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="genericReport_description_filter" value="<bean:write name="genericReport_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="genericReport.columnAction.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="genericReport_columnAction_filter" value="<bean:write name="genericReport_columnAction_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="genericReport.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  genericReport_status_filter_value = (String)request.getSession().getAttribute("genericReport_status_filter");
					if("".equals(genericReport_status_filter_value)) genericReport_status_filter_value = "0";
				%>				
				<select name="genericReport_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long genericReport_status_id = statusElement.getId();							
								Long genericReport_status_filter_value_c = new Long(genericReport_status_filter_value);
								if(genericReport_status_filter_value_c.equals(genericReport_status_id))out.print(" SELECTED ");
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

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
	if(com.app.docmgr.action.SystemParameterHistoryAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("SYSTEM_PARAMETER_HISTORY_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.SystemParameterHistory.List"/></TITLE>
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
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.systemParameterHistory.start.value=start;
			document.forms.systemParameterHistory.count.value=count;
			document.forms.systemParameterHistory.submit();
		}

		function doReset(){
			document.forms.systemParameterHistory.systemParameterHistory_historyDate_filter_start.value="";
			document.forms.systemParameterHistory.systemParameterHistory_historyDate_filter_end.value="";
			document.forms.systemParameterHistory.systemParameterHistory_historyBy_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_vgroup_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_parameter_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_svalue_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_description_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_createdDate_filter_start.value="";
			document.forms.systemParameterHistory.systemParameterHistory_createdDate_filter_end.value="";
			document.forms.systemParameterHistory.systemParameterHistory_createdBy_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_lastUpdatedDate_filter_start.value="";
			document.forms.systemParameterHistory.systemParameterHistory_lastUpdatedDate_filter_end.value="";
			document.forms.systemParameterHistory.systemParameterHistory_lastUpdatedBy_filter.value="";
			document.forms.systemParameterHistory.systemParameterHistory_status_filter.value="";
			document.forms.systemParameterHistory.submit();
		}

		function doOrder(field, type){
			document.forms.systemParameterHistory.systemParameterHistory_fieldOrder.value=field;
			document.forms.systemParameterHistory.systemParameterHistory_orderType.value=type;
			document.forms.systemParameterHistory.submit();
		}

	-->
</SCRIPT>
<form name="systemParameterHistory" method="post" action="systemParameterHistory.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="systemParameterHistory_fieldOrder" value="<bean:write name="systemParameterHistory_fieldOrder"/>">
<INPUT type="hidden" name="systemParameterHistory_orderType" value="<bean:write name="systemParameterHistory_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="22" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="22" align="left">
			<bean:message key="page.SystemParameterHistory.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="22" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="22" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="systemParameterHistory.historyDate.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="history_date">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('history_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('history_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="history_date">
				<a href="#" onclick="doOrder('history_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.historyBy.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="history_by">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('history_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('history_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="history_by">
				<a href="#" onclick="doOrder('history_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.auditTrailId.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="audit_trail_id">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('audit_trail_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('audit_trail_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="audit_trail_id">
				<a href="#" onclick="doOrder('audit_trail_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.systemParameterId.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="system_parameter_id">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('system_parameter_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('system_parameter_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="system_parameter_id">
				<a href="#" onclick="doOrder('system_parameter_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.vgroup.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="vgroup">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('vgroup', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('vgroup', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="vgroup">
				<a href="#" onclick="doOrder('vgroup', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.parameter.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="parameter">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('parameter', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('parameter', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="parameter">
				<a href="#" onclick="doOrder('parameter', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.svalue.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="svalue">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('svalue', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('svalue', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="svalue">
				<a href="#" onclick="doOrder('svalue', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.description.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="description">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.createdDate.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="created_date">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.createdBy.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="created_by">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.lastUpdatedDate.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="last_updated_date">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameterHistory.lastUpdatedBy.key"/>
			<logic:equal name="systemParameterHistory_fieldOrder" value="last_updated_by">
				<logic:equal name="systemParameterHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameterHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameterHistory_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="systemParameterHistory.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="systemParameterHistoryList" scope="request" type="com.app.docmgr.model.SystemParameterHistory">
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
		<td><bean:write name="element" property="systemParameterId"/></td>
		<td><bean:write name="element" property="vgroup"/></td>
		<td><bean:write name="element" property="parameter"/></td>
		<td><bean:write name="element" property="svalue"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
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
		<td colspan="22" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="22" align="right">
		<% if(com.app.docmgr.action.SystemParameterHistoryAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("SYSTEM_PARAMETER_HISTORY_CREATE")) { %>
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
		<td width="150" valign="top"><bean:message key="systemParameterHistory.historyDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameterHistory_historyDate_filter_start', 'systemParameterHistory_historyDate_filter_start_cal', null);"  onKeyDown="drawCalendar('systemParameterHistory_historyDate_filter_start', 'systemParameterHistory_historyDate_filter_start_cal', null);" name="systemParameterHistory_historyDate_filter_start" value="<bean:write name="systemParameterHistory_historyDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameterHistory_historyDate_filter_end', 'systemParameterHistory_historyDate_filter_end_cal', null);"  onKeyDown="drawCalendar('systemParameterHistory_historyDate_filter_end', 'systemParameterHistory_historyDate_filter_end_cal', null);" name="systemParameterHistory_historyDate_filter_end" value="<bean:write name="systemParameterHistory_historyDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="systemParameterHistory_historyDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="systemParameterHistory_historyDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.historyBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_historyBy_filter" value="<bean:write name="systemParameterHistory_historyBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.vgroup.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_vgroup_filter" value="<bean:write name="systemParameterHistory_vgroup_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.parameter.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_parameter_filter" value="<bean:write name="systemParameterHistory_parameter_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.svalue.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_svalue_filter" value="<bean:write name="systemParameterHistory_svalue_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_description_filter" value="<bean:write name="systemParameterHistory_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="systemParameterHistory.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameterHistory_createdDate_filter_start', 'systemParameterHistory_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('systemParameterHistory_createdDate_filter_start', 'systemParameterHistory_createdDate_filter_start_cal', null);" name="systemParameterHistory_createdDate_filter_start" value="<bean:write name="systemParameterHistory_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameterHistory_createdDate_filter_end', 'systemParameterHistory_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('systemParameterHistory_createdDate_filter_end', 'systemParameterHistory_createdDate_filter_end_cal', null);" name="systemParameterHistory_createdDate_filter_end" value="<bean:write name="systemParameterHistory_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="systemParameterHistory_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="systemParameterHistory_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_createdBy_filter" value="<bean:write name="systemParameterHistory_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="systemParameterHistory.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameterHistory_lastUpdatedDate_filter_start', 'systemParameterHistory_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('systemParameterHistory_lastUpdatedDate_filter_start', 'systemParameterHistory_lastUpdatedDate_filter_start_cal', null);" name="systemParameterHistory_lastUpdatedDate_filter_start" value="<bean:write name="systemParameterHistory_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameterHistory_lastUpdatedDate_filter_end', 'systemParameterHistory_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('systemParameterHistory_lastUpdatedDate_filter_end', 'systemParameterHistory_lastUpdatedDate_filter_end_cal', null);" name="systemParameterHistory_lastUpdatedDate_filter_end" value="<bean:write name="systemParameterHistory_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="systemParameterHistory_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="systemParameterHistory_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameterHistory.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameterHistory_lastUpdatedBy_filter" value="<bean:write name="systemParameterHistory_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="systemParameterHistory.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  systemParameterHistory_status_filter_value = (String)request.getSession().getAttribute("systemParameterHistory_status_filter");
					if("".equals(systemParameterHistory_status_filter_value)) systemParameterHistory_status_filter_value = "0";
				%>				
				<select name="systemParameterHistory_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long systemParameterHistory_status_id = statusElement.getId();							
								Long systemParameterHistory_status_filter_value_c = new Long(systemParameterHistory_status_filter_value);
								if(systemParameterHistory_status_filter_value_c.equals(systemParameterHistory_status_id))out.print(" SELECTED ");
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

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
	if(com.app.docmgr.admin.action.SystemParameterAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:SYSTEM_PARAMETER_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.SystemParameter.List"/></TITLE>
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
			document.forms.systemParameter.start.value=start;
			document.forms.systemParameter.count.value=count;
			document.forms.systemParameter.submit();
		}

		function doReset(){
			document.forms.systemParameter.systemParameter_vgroup_filter.value="";
			document.forms.systemParameter.systemParameter_parameter_filter.value="";
			document.forms.systemParameter.systemParameter_svalue_filter.value="";
			document.forms.systemParameter.systemParameter_description_filter.value="";
			document.forms.systemParameter.systemParameter_createdDate_filter_start.value="";
			document.forms.systemParameter.systemParameter_createdDate_filter_end.value="";
			document.forms.systemParameter.systemParameter_createdBy_filter.value="";
			document.forms.systemParameter.systemParameter_lastUpdatedDate_filter_start.value="";
			document.forms.systemParameter.systemParameter_lastUpdatedDate_filter_end.value="";
			document.forms.systemParameter.systemParameter_lastUpdatedBy_filter.value="";
			document.forms.systemParameter.systemParameter_status_filter.value="";
			document.forms.systemParameter.systemParameter_userLevel_filter.value="";
			document.forms.systemParameter.submit();
		}

		function doOrder(field, type){
			document.forms.systemParameter.systemParameter_fieldOrder.value=field;
			document.forms.systemParameter.systemParameter_orderType.value=type;
			document.forms.systemParameter.submit();
		}

	-->
</SCRIPT>
<form name="systemParameter" method="post" action="systemParameter.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="systemParameter_fieldOrder" value="<bean:write name="systemParameter_fieldOrder"/>">
<INPUT type="hidden" name="systemParameter_orderType" value="<bean:write name="systemParameter_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="18" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="18" align="left">
			<bean:message key="page.SystemParameter.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="18" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="18" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="systemParameter.vgroup.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="vgroup">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('vgroup', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('vgroup', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="vgroup">
				<a href="#" onclick="doOrder('vgroup', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.parameter.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="parameter">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('parameter', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('parameter', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="parameter">
				<a href="#" onclick="doOrder('parameter', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.svalue.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="svalue">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('svalue', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('svalue', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="svalue">
				<a href="#" onclick="doOrder('svalue', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.description.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="description">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.createdDate.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="created_date">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.createdBy.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="created_by">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.lastUpdatedDate.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="last_updated_date">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemParameter.lastUpdatedBy.key"/>
			<logic:equal name="systemParameter_fieldOrder" value="last_updated_by">
				<logic:equal name="systemParameter_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemParameter_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemParameter_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="systemParameter.status.key"/></td>
		<td><bean:message key="systemParameter.userLevel.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="systemParameterList" scope="request" type="com.app.docmgr.model.SystemParameter">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
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
		<td >
				<logic:notEmpty name="element"	property="userLevel">								
					<bean:write name="element" property="userLevel.name"/>
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
		<td colspan="18" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="18" align="right">
		<% if(com.app.docmgr.admin.action.SystemParameterAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:SYSTEM_PARAMETER_CREATE")) { %>
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
		<td width="150"><bean:message key="systemParameter.vgroup.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameter_vgroup_filter" value="<bean:write name="systemParameter_vgroup_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameter.parameter.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameter_parameter_filter" value="<bean:write name="systemParameter_parameter_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameter.svalue.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameter_svalue_filter" value="<bean:write name="systemParameter_svalue_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameter.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameter_description_filter" value="<bean:write name="systemParameter_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="systemParameter.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameter_createdDate_filter_start', 'systemParameter_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('systemParameter_createdDate_filter_start', 'systemParameter_createdDate_filter_start_cal', null);" name="systemParameter_createdDate_filter_start" value="<bean:write name="systemParameter_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameter_createdDate_filter_end', 'systemParameter_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('systemParameter_createdDate_filter_end', 'systemParameter_createdDate_filter_end_cal', null);" name="systemParameter_createdDate_filter_end" value="<bean:write name="systemParameter_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="systemParameter_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="systemParameter_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameter.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameter_createdBy_filter" value="<bean:write name="systemParameter_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="systemParameter.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameter_lastUpdatedDate_filter_start', 'systemParameter_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('systemParameter_lastUpdatedDate_filter_start', 'systemParameter_lastUpdatedDate_filter_start_cal', null);" name="systemParameter_lastUpdatedDate_filter_start" value="<bean:write name="systemParameter_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('systemParameter_lastUpdatedDate_filter_end', 'systemParameter_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('systemParameter_lastUpdatedDate_filter_end', 'systemParameter_lastUpdatedDate_filter_end_cal', null);" name="systemParameter_lastUpdatedDate_filter_end" value="<bean:write name="systemParameter_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="systemParameter_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="systemParameter_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemParameter.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemParameter_lastUpdatedBy_filter" value="<bean:write name="systemParameter_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="systemParameter.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  systemParameter_status_filter_value = (String)request.getSession().getAttribute("systemParameter_status_filter");
					if("".equals(systemParameter_status_filter_value)) systemParameter_status_filter_value = "0";
				%>				
				<select name="systemParameter_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long systemParameter_status_id = statusElement.getId();							
								Long systemParameter_status_filter_value_c = new Long(systemParameter_status_filter_value);
								if(systemParameter_status_filter_value_c.equals(systemParameter_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="systemParameter.userLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  systemParameter_userLevel_filter_value = (String)request.getSession().getAttribute("systemParameter_userLevel_filter");
					if("".equals(systemParameter_userLevel_filter_value)) systemParameter_userLevel_filter_value = "0";
				%>				
				<select name="systemParameter_userLevel_filter">
					<option value=""></option>
					<logic:iterate id="userLevelElement" name="userLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="userLevelElement" property="id"/>" 
							<%
								Long systemParameter_userLevel_id = userLevelElement.getId();							
								Long systemParameter_userLevel_filter_value_c = new Long(systemParameter_userLevel_filter_value);
								if(systemParameter_userLevel_filter_value_c.equals(systemParameter_userLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="userLevelElement" property="name"/></option>
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

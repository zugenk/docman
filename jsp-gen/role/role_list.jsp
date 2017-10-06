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
	if(com.app.docmgr.action.RoleAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("ROLE_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Role.List"/></TITLE>
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
			document.forms.role.start.value=start;
			document.forms.role.count.value=count;
			document.forms.role.submit();
		}

		function doReset(){
			document.forms.role.role_name_filter.value="";
			document.forms.role.role_description_filter.value="";
			document.forms.role.role_createdDate_filter_start.value="";
			document.forms.role.role_createdDate_filter_end.value="";
			document.forms.role.role_createdBy_filter.value="";
			document.forms.role.role_lastUpdatedDate_filter_start.value="";
			document.forms.role.role_lastUpdatedDate_filter_end.value="";
			document.forms.role.role_lastUpdatedBy_filter.value="";
			document.forms.role.role_userLevel_filter.value="";
			document.forms.role.role_status_filter.value="";
			document.forms.role.submit();
		}

		function doOrder(field, type){
			document.forms.role.role_fieldOrder.value=field;
			document.forms.role.role_orderType.value=type;
			document.forms.role.submit();
		}

	-->
</SCRIPT>
<form name="role" method="post" action="role.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="role_fieldOrder" value="<bean:write name="role_fieldOrder"/>">
<INPUT type="hidden" name="role_orderType" value="<bean:write name="role_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="16" align="left">
			<bean:message key="page.Role.List"/>
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
			<bean:message key="role.name.key"/>
			<logic:equal name="role_fieldOrder" value="name">
				<logic:equal name="role_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="role_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="role_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="role.description.key"/>
			<logic:equal name="role_fieldOrder" value="description">
				<logic:equal name="role_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="role_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="role_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="role.createdDate.key"/>
			<logic:equal name="role_fieldOrder" value="created_date">
				<logic:equal name="role_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="role_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="role_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="role.createdBy.key"/>
			<logic:equal name="role_fieldOrder" value="created_by">
				<logic:equal name="role_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="role_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="role_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="role.lastUpdatedDate.key"/>
			<logic:equal name="role_fieldOrder" value="last_updated_date">
				<logic:equal name="role_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="role_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="role_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="role.lastUpdatedBy.key"/>
			<logic:equal name="role_fieldOrder" value="last_updated_by">
				<logic:equal name="role_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="role_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="role_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="role.userLevel.key"/></td>
		<td><bean:message key="role.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="roleList" scope="request" type="com.app.docmgr.model.Role">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td >
				<logic:notEmpty name="element"	property="userLevel">								
					<bean:write name="element" property="userLevel.name"/>
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
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="16" align="right">
		<% if(com.app.docmgr.action.RoleAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("ROLE_CREATE")) { %>
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
		<td width="150"><bean:message key="role.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="role_name_filter" value="<bean:write name="role_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="role.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="role_description_filter" value="<bean:write name="role_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="role.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('role_createdDate_filter_start', 'role_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('role_createdDate_filter_start', 'role_createdDate_filter_start_cal', null);" name="role_createdDate_filter_start" value="<bean:write name="role_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('role_createdDate_filter_end', 'role_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('role_createdDate_filter_end', 'role_createdDate_filter_end_cal', null);" name="role_createdDate_filter_end" value="<bean:write name="role_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="role_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="role_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="role.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="role_createdBy_filter" value="<bean:write name="role_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="role.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('role_lastUpdatedDate_filter_start', 'role_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('role_lastUpdatedDate_filter_start', 'role_lastUpdatedDate_filter_start_cal', null);" name="role_lastUpdatedDate_filter_start" value="<bean:write name="role_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('role_lastUpdatedDate_filter_end', 'role_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('role_lastUpdatedDate_filter_end', 'role_lastUpdatedDate_filter_end_cal', null);" name="role_lastUpdatedDate_filter_end" value="<bean:write name="role_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="role_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="role_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="role.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="role_lastUpdatedBy_filter" value="<bean:write name="role_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="role.userLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  role_userLevel_filter_value = (String)request.getSession().getAttribute("role_userLevel_filter");
					if("".equals(role_userLevel_filter_value)) role_userLevel_filter_value = "0";
				%>				
				<select name="role_userLevel_filter">
					<option value=""></option>
					<logic:iterate id="userLevelElement" name="userLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="userLevelElement" property="id"/>" 
							<%
								Long role_userLevel_id = userLevelElement.getId();							
								Long role_userLevel_filter_value_c = new Long(role_userLevel_filter_value);
								if(role_userLevel_filter_value_c.equals(role_userLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="userLevelElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="role.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  role_status_filter_value = (String)request.getSession().getAttribute("role_status_filter");
					if("".equals(role_status_filter_value)) role_status_filter_value = "0";
				%>				
				<select name="role_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long role_status_id = statusElement.getId();							
								Long role_status_filter_value_c = new Long(role_status_filter_value);
								if(role_status_filter_value_c.equals(role_status_id))out.print(" SELECTED ");
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

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
	if(com.app.docmgr.action.OrganizationAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("ORGANIZATION_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Organization.List"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>
<BODY>
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.organization.start.value=start;
			document.forms.organization.count.value=count;
			document.forms.organization.submit();
		}

		function doReset(){
			document.forms.organization.organization_code_filter.value="";
			document.forms.organization.organization_mnemonic_filter.value="";
			document.forms.organization.organization_name_filter.value="";
			document.forms.organization.organization_address_filter.value="";
			document.forms.organization.organization_createdDate_filter_start.value="";
			document.forms.organization.organization_createdDate_filter_end.value="";
			document.forms.organization.organization_createdBy_filter.value="";
			document.forms.organization.organization_lastUpdatedDate_filter_start.value="";
			document.forms.organization.organization_lastUpdatedDate_filter_end.value="";
			document.forms.organization.organization_lastUpdatedBy_filter.value="";
			document.forms.organization.organization_filterCode_filter.value="";
			document.forms.organization.organization_parent_filter.value="";
			document.forms.organization.submit();
		}

		function doOrder(field, type){
			document.forms.organization.organization_fieldOrder.value=field;
			document.forms.organization.organization_orderType.value=type;
			document.forms.organization.submit();
		}

	-->
</SCRIPT>
<form name="organization" method="post" action="organization.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="organization_fieldOrder" value="<bean:write name="organization_fieldOrder"/>">
<INPUT type="hidden" name="organization_orderType" value="<bean:write name="organization_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="19" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="19" align="left">
			<bean:message key="page.Organization.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="19" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="19" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="organization.code.key"/>
			<logic:equal name="organization_fieldOrder" value="code">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="code">
				<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.mnemonic.key"/>
			<logic:equal name="organization_fieldOrder" value="mnemonic">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('mnemonic', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('mnemonic', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="mnemonic">
				<a href="#" onclick="doOrder('mnemonic', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.name.key"/>
			<logic:equal name="organization_fieldOrder" value="name">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.address.key"/>
			<logic:equal name="organization_fieldOrder" value="address">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('address', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('address', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="address">
				<a href="#" onclick="doOrder('address', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.createdDate.key"/>
			<logic:equal name="organization_fieldOrder" value="created_date">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.createdBy.key"/>
			<logic:equal name="organization_fieldOrder" value="created_by">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.lastUpdatedDate.key"/>
			<logic:equal name="organization_fieldOrder" value="last_updated_date">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.lastUpdatedBy.key"/>
			<logic:equal name="organization_fieldOrder" value="last_updated_by">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="organization.filterCode.key"/>
			<logic:equal name="organization_fieldOrder" value="filter_code">
				<logic:equal name="organization_orderType" value="ASC">
					<a href="#" onclick="doOrder('filter_code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="organization_orderType" value="DESC">
					<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="organization_fieldOrder" value="filter_code">
				<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="organization.parent.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="organizationList" scope="request" type="com.app.docmgr.model.Organization">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="code"/></td>
		<td><bean:write name="element" property="mnemonic"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="address"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td><bean:write name="element" property="filterCode"/></td>
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
		<td colspan="19" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="19" align="right">
		<% if(com.app.docmgr.action.OrganizationAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("ORGANIZATION_CREATE")) { %>
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
		<td width="150"><bean:message key="organization.code.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_code_filter" value="<bean:write name="organization_code_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="organization.mnemonic.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_mnemonic_filter" value="<bean:write name="organization_mnemonic_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="organization.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_name_filter" value="<bean:write name="organization_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="organization.address.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_address_filter" value="<bean:write name="organization_address_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="organization.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('organization_createdDate_filter_start', 'organization_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('organization_createdDate_filter_start', 'organization_createdDate_filter_start_cal', null);" name="organization_createdDate_filter_start" value="<bean:write name="organization_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('organization_createdDate_filter_end', 'organization_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('organization_createdDate_filter_end', 'organization_createdDate_filter_end_cal', null);" name="organization_createdDate_filter_end" value="<bean:write name="organization_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="organization_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="organization_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="organization.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_createdBy_filter" value="<bean:write name="organization_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="organization.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('organization_lastUpdatedDate_filter_start', 'organization_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('organization_lastUpdatedDate_filter_start', 'organization_lastUpdatedDate_filter_start_cal', null);" name="organization_lastUpdatedDate_filter_start" value="<bean:write name="organization_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('organization_lastUpdatedDate_filter_end', 'organization_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('organization_lastUpdatedDate_filter_end', 'organization_lastUpdatedDate_filter_end_cal', null);" name="organization_lastUpdatedDate_filter_end" value="<bean:write name="organization_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="organization_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="organization_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="organization.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_lastUpdatedBy_filter" value="<bean:write name="organization_lastUpdatedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="organization.filterCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="organization_filterCode_filter" value="<bean:write name="organization_filterCode_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="organization.parent.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  organization_parent_filter_value = (String)request.getSession().getAttribute("organization_parent_filter");
					if("".equals(organization_parent_filter_value)) organization_parent_filter_value = "0";
				%>				
				<select name="organization_parent_filter">
					<option value=""></option>
					<logic:iterate id="parentElement" name="parentList"  type="com.app.docmgr.model.Organization">
						
						<option value="<bean:write name="parentElement" property="id"/>" 
							<%
								Long organization_parent_id = parentElement.getId();							
								Long organization_parent_filter_value_c = new Long(organization_parent_filter_value);
								if(organization_parent_filter_value_c.equals(organization_parent_id))out.print(" SELECTED ");
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

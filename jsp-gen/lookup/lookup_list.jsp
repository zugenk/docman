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
	if(com.app.docmgr.action.LookupAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("LOOKUP_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Lookup.List"/></TITLE>
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
			document.forms.lookup.start.value=start;
			document.forms.lookup.count.value=count;
			document.forms.lookup.submit();
		}

		function doReset(){
			document.forms.lookup.lookup_type_filter.value="";
			document.forms.lookup.lookup_code_filter.value="";
			document.forms.lookup.lookup_name_filter.value="";
			document.forms.lookup.lookup_description_filter.value="";
			document.forms.lookup.lookup_shortname_filter.value="";
			document.forms.lookup.lookup_filter_filter.value="";
			document.forms.lookup.lookup_status_filter.value="";
			document.forms.lookup.submit();
		}

		function doOrder(field, type){
			document.forms.lookup.lookup_fieldOrder.value=field;
			document.forms.lookup.lookup_orderType.value=type;
			document.forms.lookup.submit();
		}

	-->
</SCRIPT>
<form name="lookup" method="post" action="lookup.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="lookup_fieldOrder" value="<bean:write name="lookup_fieldOrder"/>">
<INPUT type="hidden" name="lookup_orderType" value="<bean:write name="lookup_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="17" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="17" align="left">
			<bean:message key="page.Lookup.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="17" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="17" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="lookup.type.key"/>
			<logic:equal name="lookup_fieldOrder" value="type">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('type', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="type">
				<a href="#" onclick="doOrder('type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="lookup.code.key"/>
			<logic:equal name="lookup_fieldOrder" value="code">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="code">
				<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="lookup.name.key"/>
			<logic:equal name="lookup_fieldOrder" value="name">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="lookup.priority.key"/>
			<logic:equal name="lookup_fieldOrder" value="priority">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('priority', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('priority', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="priority">
				<a href="#" onclick="doOrder('priority', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="lookup.description.key"/>
			<logic:equal name="lookup_fieldOrder" value="description">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="lookup.shortname.key"/>
			<logic:equal name="lookup_fieldOrder" value="shortname">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('shortname', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('shortname', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="shortname">
				<a href="#" onclick="doOrder('shortname', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="lookup.filter.key"/>
			<logic:equal name="lookup_fieldOrder" value="filter">
				<logic:equal name="lookup_orderType" value="ASC">
					<a href="#" onclick="doOrder('filter', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="lookup_orderType" value="DESC">
					<a href="#" onclick="doOrder('filter', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="lookup_fieldOrder" value="filter">
				<a href="#" onclick="doOrder('filter', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="lookup.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="lookupList" scope="request" type="com.app.docmgr.model.Lookup">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="type"/></td>
		<td><bean:write name="element" property="code"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="priority"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td><bean:write name="element" property="shortname"/></td>
		<td><bean:write name="element" property="filter"/></td>
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
		<td colspan="17" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="17" align="right">
		<% if(com.app.docmgr.action.LookupAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("LOOKUP_CREATE")) { %>
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
		<td width="150"><bean:message key="lookup.type.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="lookup_type_filter" value="<bean:write name="lookup_type_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="lookup.code.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="lookup_code_filter" value="<bean:write name="lookup_code_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="lookup.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="lookup_name_filter" value="<bean:write name="lookup_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="lookup.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="lookup_description_filter" value="<bean:write name="lookup_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="lookup.shortname.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="lookup_shortname_filter" value="<bean:write name="lookup_shortname_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="lookup.filter.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="lookup_filter_filter" value="<bean:write name="lookup_filter_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="lookup.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  lookup_status_filter_value = (String)request.getSession().getAttribute("lookup_status_filter");
					if("".equals(lookup_status_filter_value)) lookup_status_filter_value = "0";
				%>				
				<select name="lookup_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long lookup_status_id = statusElement.getId();							
								Long lookup_status_filter_value_c = new Long(lookup_status_filter_value);
								if(lookup_status_filter_value_c.equals(lookup_status_id))out.print(" SELECTED ");
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

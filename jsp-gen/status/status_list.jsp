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
	if(com.app.docmgr.action.StatusAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("STATUS_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Status.List"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->
</HEAD>
<BODY>
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.status.start.value=start;
			document.forms.status.count.value=count;
			document.forms.status.submit();
		}

		function doReset(){
			document.forms.status.status_type_filter.value="";
			document.forms.status.status_code_filter.value="";
			document.forms.status.status_state_filter.value="";
			document.forms.status.status_name_filter.value="";
			document.forms.status.status_description_filter.value="";
			document.forms.status.submit();
		}

		function doOrder(field, type){
			document.forms.status.status_fieldOrder.value=field;
			document.forms.status.status_orderType.value=type;
			document.forms.status.submit();
		}

	-->
</SCRIPT>
<form name="status" method="post" action="status.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="status_fieldOrder" value="<bean:write name="status_fieldOrder"/>">
<INPUT type="hidden" name="status_orderType" value="<bean:write name="status_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="15" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="15" align="left">
			<bean:message key="page.Status.List"/>
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
			<bean:message key="status.type.key"/>
			<logic:equal name="status_fieldOrder" value="type">
				<logic:equal name="status_orderType" value="ASC">
					<a href="#" onclick="doOrder('type', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="status_orderType" value="DESC">
					<a href="#" onclick="doOrder('type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="status_fieldOrder" value="type">
				<a href="#" onclick="doOrder('type', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="status.code.key"/>
			<logic:equal name="status_fieldOrder" value="code">
				<logic:equal name="status_orderType" value="ASC">
					<a href="#" onclick="doOrder('code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="status_orderType" value="DESC">
					<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="status_fieldOrder" value="code">
				<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="status.state.key"/>
			<logic:equal name="status_fieldOrder" value="state">
				<logic:equal name="status_orderType" value="ASC">
					<a href="#" onclick="doOrder('state', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="status_orderType" value="DESC">
					<a href="#" onclick="doOrder('state', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="status_fieldOrder" value="state">
				<a href="#" onclick="doOrder('state', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="status.name.key"/>
			<logic:equal name="status_fieldOrder" value="name">
				<logic:equal name="status_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="status_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="status_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="status.description.key"/>
			<logic:equal name="status_fieldOrder" value="description">
				<logic:equal name="status_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="status_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="status_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="statusList" scope="request" type="com.app.docmgr.model.Status">
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
		<td><bean:write name="element" property="state"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="description"/></td>

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
		<% if(com.app.docmgr.action.StatusAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("STATUS_CREATE")) { %>
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
		<td width="150"><bean:message key="status.type.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="status_type_filter" value="<bean:write name="status_type_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="status.code.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="status_code_filter" value="<bean:write name="status_code_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="status.state.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="status_state_filter" value="<bean:write name="status_state_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="status.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="status_name_filter" value="<bean:write name="status_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="status.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="status_description_filter" value="<bean:write name="status_description_filter"/>"></td>
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

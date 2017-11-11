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
	if(com.app.docmgr.admin.action.PrivilegeAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Privilege.List"/></TITLE>
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
			document.forms.privilege.start.value=start;
			document.forms.privilege.count.value=count;
			document.forms.privilege.submit();
		}

		function doReset(){
			document.forms.privilege.privilege_name_filter.value="";
			document.forms.privilege.privilege_vgroup_filter.value="";
			document.forms.privilege.privilege_description_filter.value="";
			document.forms.privilege.submit();
		}

		function doOrder(field, type){
			document.forms.privilege.privilege_fieldOrder.value=field;
			document.forms.privilege.privilege_orderType.value=type;
			document.forms.privilege.submit();
		}

	-->
</SCRIPT>
<form name="privilege" method="post" action="privilege.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="privilege_fieldOrder" value="<bean:write name="privilege_fieldOrder"/>">
<INPUT type="hidden" name="privilege_orderType" value="<bean:write name="privilege_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="13" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="13" align="left">
			<bean:message key="page.Privilege.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="13" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="13" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="privilege.name.key"/>
			<logic:equal name="privilege_fieldOrder" value="name">
				<logic:equal name="privilege_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="privilege_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="privilege_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="privilege.vgroup.key"/>
			<logic:equal name="privilege_fieldOrder" value="vgroup">
				<logic:equal name="privilege_orderType" value="ASC">
					<a href="#" onclick="doOrder('vgroup', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="privilege_orderType" value="DESC">
					<a href="#" onclick="doOrder('vgroup', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="privilege_fieldOrder" value="vgroup">
				<a href="#" onclick="doOrder('vgroup', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="privilege.description.key"/>
			<logic:equal name="privilege_fieldOrder" value="description">
				<logic:equal name="privilege_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="privilege_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="privilege_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="privilegeList" scope="request" type="com.app.docmgr.model.Privilege">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="vgroup"/></td>
		<td><bean:write name="element" property="description"/></td>

			<td width="20">
				<% if(allowDetail) { %>
				<input type=button value='<bean:message key="button.detail"/>' onclick="this.form.id.value='<bean:write name="element" property="id"/>';this.form.action.value='detail';this.form.submit()" />
				<% } %>
			</td>
		</tr>		
	</logic:iterate> 
	<tr>
		<td colspan="13" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="13" align="right">
		<% if(com.app.docmgr.admin.action.PrivilegeAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:PRIVILEGE_CREATE")) { %>
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
		<td width="150"><bean:message key="privilege.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="privilege_name_filter" value="<bean:write name="privilege_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="privilege.vgroup.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="privilege_vgroup_filter" value="<bean:write name="privilege_vgroup_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="privilege.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="privilege_description_filter" value="<bean:write name="privilege_description_filter"/>"></td>
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

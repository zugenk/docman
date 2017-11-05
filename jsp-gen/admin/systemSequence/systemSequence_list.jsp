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
	if(com.app.docmgr.admin.action.SystemSequenceAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:SYSTEM_SEQUENCE_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.SystemSequence.List"/></TITLE>
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
			document.forms.systemSequence.start.value=start;
			document.forms.systemSequence.count.value=count;
			document.forms.systemSequence.submit();
		}

		function doReset(){
			document.forms.systemSequence.systemSequence_type_filter.value="";
			document.forms.systemSequence.systemSequence_param_filter.value="";
			document.forms.systemSequence.submit();
		}

		function doOrder(field, type){
			document.forms.systemSequence.systemSequence_fieldOrder.value=field;
			document.forms.systemSequence.systemSequence_orderType.value=type;
			document.forms.systemSequence.submit();
		}

	-->
</SCRIPT>
<form name="systemSequence" method="post" action="systemSequence.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="systemSequence_fieldOrder" value="<bean:write name="systemSequence_fieldOrder"/>">
<INPUT type="hidden" name="systemSequence_orderType" value="<bean:write name="systemSequence_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="13" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="13" align="left">
			<bean:message key="page.SystemSequence.List"/>
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
			<bean:message key="systemSequence.type.key"/>
			<logic:equal name="systemSequence_fieldOrder" value="type">
				<logic:equal name="systemSequence_orderType" value="ASC">
					<a href="#" onclick="doOrder('type', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemSequence_orderType" value="DESC">
					<a href="#" onclick="doOrder('type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemSequence_fieldOrder" value="type">
				<a href="#" onclick="doOrder('type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemSequence.param.key"/>
			<logic:equal name="systemSequence_fieldOrder" value="param">
				<logic:equal name="systemSequence_orderType" value="ASC">
					<a href="#" onclick="doOrder('param', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemSequence_orderType" value="DESC">
					<a href="#" onclick="doOrder('param', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemSequence_fieldOrder" value="param">
				<a href="#" onclick="doOrder('param', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="systemSequence.sequence.key"/>
			<logic:equal name="systemSequence_fieldOrder" value="sequence">
				<logic:equal name="systemSequence_orderType" value="ASC">
					<a href="#" onclick="doOrder('sequence', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="systemSequence_orderType" value="DESC">
					<a href="#" onclick="doOrder('sequence', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="systemSequence_fieldOrder" value="sequence">
				<a href="#" onclick="doOrder('sequence', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="systemSequenceList" scope="request" type="com.app.docmgr.model.SystemSequence">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="type"/></td>
		<td><bean:write name="element" property="param"/></td>
		<td><bean:write name="element" property="sequence"/></td>

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
		<% if(com.app.docmgr.admin.action.SystemSequenceAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:SYSTEM_SEQUENCE_CREATE")) { %>
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
		<td width="150"><bean:message key="systemSequence.type.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemSequence_type_filter" value="<bean:write name="systemSequence_type_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="systemSequence.param.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="systemSequence_param_filter" value="<bean:write name="systemSequence_param_filter"/>"></td>
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

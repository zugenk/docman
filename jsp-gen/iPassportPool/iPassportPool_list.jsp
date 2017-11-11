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
	if(com.app.docmgr.action.IPassportPoolAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("I_PASSPORT_POOL_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.IPassportPool.List"/></TITLE>
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
			document.forms.iPassportPool.start.value=start;
			document.forms.iPassportPool.count.value=count;
			document.forms.iPassportPool.submit();
		}

		function doReset(){
			document.forms.iPassportPool.iPassportPool_ipassport_filter.value="";
			document.forms.iPassportPool.iPassportPool_loginId_filter.value="";
			document.forms.iPassportPool.iPassportPool_lastLogin_filter_start.value="";
			document.forms.iPassportPool.iPassportPool_lastLogin_filter_end.value="";
			document.forms.iPassportPool.iPassportPool_lastActive_filter_start.value="";
			document.forms.iPassportPool.iPassportPool_lastActive_filter_end.value="";
			document.forms.iPassportPool.submit();
		}

		function doOrder(field, type){
			document.forms.iPassportPool.iPassportPool_fieldOrder.value=field;
			document.forms.iPassportPool.iPassportPool_orderType.value=type;
			document.forms.iPassportPool.submit();
		}

	-->
</SCRIPT>
<form name="iPassportPool" method="post" action="iPassportPool.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="iPassportPool_fieldOrder" value="<bean:write name="iPassportPool_fieldOrder"/>">
<INPUT type="hidden" name="iPassportPool_orderType" value="<bean:write name="iPassportPool_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="14" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="14" align="left">
			<bean:message key="page.IPassportPool.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="14" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="14" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="iPassportPool.ipassport.key"/>
			<logic:equal name="iPassportPool_fieldOrder" value="ipassport">
				<logic:equal name="iPassportPool_orderType" value="ASC">
					<a href="#" onclick="doOrder('ipassport', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="iPassportPool_orderType" value="DESC">
					<a href="#" onclick="doOrder('ipassport', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="iPassportPool_fieldOrder" value="ipassport">
				<a href="#" onclick="doOrder('ipassport', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="iPassportPool.loginId.key"/>
			<logic:equal name="iPassportPool_fieldOrder" value="login_id">
				<logic:equal name="iPassportPool_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="iPassportPool_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="iPassportPool_fieldOrder" value="login_id">
				<a href="#" onclick="doOrder('login_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="iPassportPool.lastLogin.key"/>
			<logic:equal name="iPassportPool_fieldOrder" value="last_login">
				<logic:equal name="iPassportPool_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_login', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="iPassportPool_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_login', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="iPassportPool_fieldOrder" value="last_login">
				<a href="#" onclick="doOrder('last_login', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="iPassportPool.lastActive.key"/>
			<logic:equal name="iPassportPool_fieldOrder" value="last_active">
				<logic:equal name="iPassportPool_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_active', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="iPassportPool_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_active', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="iPassportPool_fieldOrder" value="last_active">
				<a href="#" onclick="doOrder('last_active', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="iPassportPoolList" scope="request" type="com.app.docmgr.model.IPassportPool">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="ipassport"/></td>
		<td><bean:write name="element" property="loginId"/></td>
		<td><bean:write name="element" property="lastLogin" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastActive" format="dd MMM yyyy"/></td>

			<td width="20">
				<% if(allowDetail) { %>
				<input type=button value='<bean:message key="button.detail"/>' onclick="this.form.id.value='<bean:write name="element" property="id"/>';this.form.action.value='detail';this.form.submit()" />
				<% } %>
			</td>
		</tr>		
	</logic:iterate> 
	<tr>
		<td colspan="14" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="14" align="right">
		<% if(com.app.docmgr.action.IPassportPoolAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("I_PASSPORT_POOL_CREATE")) { %>
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
		<td width="150"><bean:message key="iPassportPool.ipassport.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="iPassportPool_ipassport_filter" value="<bean:write name="iPassportPool_ipassport_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="iPassportPool.loginId.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="iPassportPool_loginId_filter" value="<bean:write name="iPassportPool_loginId_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="iPassportPool.lastLogin.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('iPassportPool_lastLogin_filter_start', 'iPassportPool_lastLogin_filter_start_cal', null);"  onKeyDown="drawCalendar('iPassportPool_lastLogin_filter_start', 'iPassportPool_lastLogin_filter_start_cal', null);" name="iPassportPool_lastLogin_filter_start" value="<bean:write name="iPassportPool_lastLogin_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('iPassportPool_lastLogin_filter_end', 'iPassportPool_lastLogin_filter_end_cal', null);"  onKeyDown="drawCalendar('iPassportPool_lastLogin_filter_end', 'iPassportPool_lastLogin_filter_end_cal', null);" name="iPassportPool_lastLogin_filter_end" value="<bean:write name="iPassportPool_lastLogin_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="iPassportPool_lastLogin_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="iPassportPool_lastLogin_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="iPassportPool.lastActive.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('iPassportPool_lastActive_filter_start', 'iPassportPool_lastActive_filter_start_cal', null);"  onKeyDown="drawCalendar('iPassportPool_lastActive_filter_start', 'iPassportPool_lastActive_filter_start_cal', null);" name="iPassportPool_lastActive_filter_start" value="<bean:write name="iPassportPool_lastActive_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('iPassportPool_lastActive_filter_end', 'iPassportPool_lastActive_filter_end_cal', null);"  onKeyDown="drawCalendar('iPassportPool_lastActive_filter_end', 'iPassportPool_lastActive_filter_end_cal', null);" name="iPassportPool_lastActive_filter_end" value="<bean:write name="iPassportPool_lastActive_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="iPassportPool_lastActive_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="iPassportPool_lastActive_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
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

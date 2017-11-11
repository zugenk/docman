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
	if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.LoginHistory.List"/></TITLE>
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
			document.forms.loginHistory.start.value=start;
			document.forms.loginHistory.count.value=count;
			document.forms.loginHistory.submit();
		}

		function doReset(){
			document.forms.loginHistory.loginHistory_loginTime_filter_start.value="";
			document.forms.loginHistory.loginHistory_loginTime_filter_end.value="";
			document.forms.loginHistory.loginHistory_lastAccess_filter_start.value="";
			document.forms.loginHistory.loginHistory_lastAccess_filter_end.value="";
			document.forms.loginHistory.loginHistory_logoutTime_filter_start.value="";
			document.forms.loginHistory.loginHistory_logoutTime_filter_end.value="";
			document.forms.loginHistory.loginHistory_sessionId_filter.value="";
			document.forms.loginHistory.loginHistory_description_filter.value="";
			document.forms.loginHistory.loginHistory_user_filter.value="";
			document.forms.loginHistory.loginHistory_status_filter.value="";
			document.forms.loginHistory.submit();
		}

		function doOrder(field, type){
			document.forms.loginHistory.loginHistory_fieldOrder.value=field;
			document.forms.loginHistory.loginHistory_orderType.value=type;
			document.forms.loginHistory.submit();
		}

	-->
</SCRIPT>
<form name="loginHistory" method="post" action="loginHistory.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="loginHistory_fieldOrder" value="<bean:write name="loginHistory_fieldOrder"/>">
<INPUT type="hidden" name="loginHistory_orderType" value="<bean:write name="loginHistory_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="15" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="15" align="left">
			<bean:message key="page.LoginHistory.List"/>
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
			<bean:message key="loginHistory.loginTime.key"/>
			<logic:equal name="loginHistory_fieldOrder" value="login_time">
				<logic:equal name="loginHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_time', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="loginHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_time', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="loginHistory_fieldOrder" value="login_time">
				<a href="#" onclick="doOrder('login_time', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="loginHistory.lastAccess.key"/>
			<logic:equal name="loginHistory_fieldOrder" value="last_access">
				<logic:equal name="loginHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_access', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="loginHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_access', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="loginHistory_fieldOrder" value="last_access">
				<a href="#" onclick="doOrder('last_access', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="loginHistory.logoutTime.key"/>
			<logic:equal name="loginHistory_fieldOrder" value="logout_time">
				<logic:equal name="loginHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('logout_time', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="loginHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('logout_time', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="loginHistory_fieldOrder" value="logout_time">
				<a href="#" onclick="doOrder('logout_time', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="loginHistory.sessionId.key"/>
			<logic:equal name="loginHistory_fieldOrder" value="session_id">
				<logic:equal name="loginHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('session_id', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="loginHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('session_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="loginHistory_fieldOrder" value="session_id">
				<a href="#" onclick="doOrder('session_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="loginHistory.description.key"/>
			<logic:equal name="loginHistory_fieldOrder" value="description">
				<logic:equal name="loginHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="loginHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="loginHistory_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="loginHistory.user.key"/></td>
		<td><bean:message key="loginHistory.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="loginHistoryList" scope="request" type="com.app.docmgr.model.LoginHistory">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="loginTime" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastAccess" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="logoutTime" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="sessionId"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td >
				<logic:notEmpty name="element"	property="user">								
					<bean:write name="element" property="user.name"/>
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
		<td colspan="15" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="15" align="right">
		<% if(com.app.docmgr.admin.action.LoginHistoryAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:LOGIN_HISTORY_CREATE")) { %>
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
		<td width="150" valign="top"><bean:message key="loginHistory.loginTime.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('loginHistory_loginTime_filter_start', 'loginHistory_loginTime_filter_start_cal', null);"  onKeyDown="drawCalendar('loginHistory_loginTime_filter_start', 'loginHistory_loginTime_filter_start_cal', null);" name="loginHistory_loginTime_filter_start" value="<bean:write name="loginHistory_loginTime_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('loginHistory_loginTime_filter_end', 'loginHistory_loginTime_filter_end_cal', null);"  onKeyDown="drawCalendar('loginHistory_loginTime_filter_end', 'loginHistory_loginTime_filter_end_cal', null);" name="loginHistory_loginTime_filter_end" value="<bean:write name="loginHistory_loginTime_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="loginHistory_loginTime_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="loginHistory_loginTime_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="loginHistory.lastAccess.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('loginHistory_lastAccess_filter_start', 'loginHistory_lastAccess_filter_start_cal', null);"  onKeyDown="drawCalendar('loginHistory_lastAccess_filter_start', 'loginHistory_lastAccess_filter_start_cal', null);" name="loginHistory_lastAccess_filter_start" value="<bean:write name="loginHistory_lastAccess_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('loginHistory_lastAccess_filter_end', 'loginHistory_lastAccess_filter_end_cal', null);"  onKeyDown="drawCalendar('loginHistory_lastAccess_filter_end', 'loginHistory_lastAccess_filter_end_cal', null);" name="loginHistory_lastAccess_filter_end" value="<bean:write name="loginHistory_lastAccess_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="loginHistory_lastAccess_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="loginHistory_lastAccess_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="loginHistory.logoutTime.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('loginHistory_logoutTime_filter_start', 'loginHistory_logoutTime_filter_start_cal', null);"  onKeyDown="drawCalendar('loginHistory_logoutTime_filter_start', 'loginHistory_logoutTime_filter_start_cal', null);" name="loginHistory_logoutTime_filter_start" value="<bean:write name="loginHistory_logoutTime_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('loginHistory_logoutTime_filter_end', 'loginHistory_logoutTime_filter_end_cal', null);"  onKeyDown="drawCalendar('loginHistory_logoutTime_filter_end', 'loginHistory_logoutTime_filter_end_cal', null);" name="loginHistory_logoutTime_filter_end" value="<bean:write name="loginHistory_logoutTime_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="loginHistory_logoutTime_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="loginHistory_logoutTime_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="loginHistory.sessionId.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="loginHistory_sessionId_filter" value="<bean:write name="loginHistory_sessionId_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="loginHistory.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="loginHistory_description_filter" value="<bean:write name="loginHistory_description_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="loginHistory.user.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  loginHistory_user_filter_value = (String)request.getSession().getAttribute("loginHistory_user_filter");
					if("".equals(loginHistory_user_filter_value)) loginHistory_user_filter_value = "0";
				%>				
				<select name="loginHistory_user_filter">
					<option value=""></option>
					<logic:iterate id="userElement" name="userList"  type="com.app.docmgr.model.User">
						
						<option value="<bean:write name="userElement" property="id"/>" 
							<%
								Long loginHistory_user_id = userElement.getId();							
								Long loginHistory_user_filter_value_c = new Long(loginHistory_user_filter_value);
								if(loginHistory_user_filter_value_c.equals(loginHistory_user_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="userElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="loginHistory.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  loginHistory_status_filter_value = (String)request.getSession().getAttribute("loginHistory_status_filter");
					if("".equals(loginHistory_status_filter_value)) loginHistory_status_filter_value = "0";
				%>				
				<select name="loginHistory_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long loginHistory_status_id = statusElement.getId();							
								Long loginHistory_status_filter_value_c = new Long(loginHistory_status_filter_value);
								if(loginHistory_status_filter_value_c.equals(loginHistory_status_id))out.print(" SELECTED ");
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

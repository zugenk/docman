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
	if(com.app.docmgr.action.UserHistoryAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("USER_HISTORY_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.UserHistory.List"/></TITLE>
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
			document.forms.userHistory.start.value=start;
			document.forms.userHistory.count.value=count;
			document.forms.userHistory.submit();
		}

		function doReset(){
			document.forms.userHistory.userHistory_historyDate_filter_start.value="";
			document.forms.userHistory.userHistory_historyDate_filter_end.value="";
			document.forms.userHistory.userHistory_historyBy_filter.value="";
			document.forms.userHistory.userHistory_loginName_filter.value="";
			document.forms.userHistory.userHistory_loginPassword_filter.value="";
			document.forms.userHistory.userHistory_pinCode_filter.value="";
			document.forms.userHistory.userHistory_mobileNumber_filter.value="";
			document.forms.userHistory.userHistory_language_filter.value="";
			document.forms.userHistory.userHistory_title_filter.value="";
			document.forms.userHistory.userHistory_name_filter.value="";
			document.forms.userHistory.userHistory_alias_filter.value="";
			document.forms.userHistory.userHistory_picture_filter.value="";
			document.forms.userHistory.userHistory_email_filter.value="";
			document.forms.userHistory.userHistory_fullName_filter.value="";
			document.forms.userHistory.userHistory_homePhoneNumber_filter.value="";
			document.forms.userHistory.userHistory_mobilePhoneNumber_filter.value="";
			document.forms.userHistory.userHistory_employeeNumber_filter.value="";
			document.forms.userHistory.userHistory_createdDate_filter_start.value="";
			document.forms.userHistory.userHistory_createdDate_filter_end.value="";
			document.forms.userHistory.userHistory_createdBy_filter.value="";
			document.forms.userHistory.userHistory_lastUpdatedDate_filter_start.value="";
			document.forms.userHistory.userHistory_lastUpdatedDate_filter_end.value="";
			document.forms.userHistory.userHistory_lastUpdatedBy_filter.value="";
			document.forms.userHistory.userHistory_firstLogin_filter.value="";
			document.forms.userHistory.userHistory_lastPasswordUpdate_filter_start.value="";
			document.forms.userHistory.userHistory_lastPasswordUpdate_filter_end.value="";
			document.forms.userHistory.userHistory_lastPinCodeUpdate_filter_start.value="";
			document.forms.userHistory.userHistory_lastPinCodeUpdate_filter_end.value="";
			document.forms.userHistory.userHistory_lastPassword_filter.value="";
			document.forms.userHistory.userHistory_lastPinCode_filter.value="";
			document.forms.userHistory.userHistory_lastReleasedDate_filter_start.value="";
			document.forms.userHistory.userHistory_lastReleasedDate_filter_end.value="";
			document.forms.userHistory.userHistory_lastActive_filter_start.value="";
			document.forms.userHistory.userHistory_lastActive_filter_end.value="";
			document.forms.userHistory.userHistory_sessionCode_filter.value="";
			document.forms.userHistory.userHistory_IPassport_filter.value="";
			document.forms.userHistory.userHistory_userLevel_filter.value="";
			document.forms.userHistory.userHistory_position_filter.value="";
			document.forms.userHistory.userHistory_status_filter.value="";
			document.forms.userHistory.userHistory_organization_filter.value="";
			document.forms.userHistory.userHistory_securityLevel_filter.value="";
			document.forms.userHistory.submit();
		}

		function doOrder(field, type){
			document.forms.userHistory.userHistory_fieldOrder.value=field;
			document.forms.userHistory.userHistory_orderType.value=type;
			document.forms.userHistory.submit();
		}

	-->
</SCRIPT>
<form name="userHistory" method="post" action="userHistory.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="userHistory_fieldOrder" value="<bean:write name="userHistory_fieldOrder"/>">
<INPUT type="hidden" name="userHistory_orderType" value="<bean:write name="userHistory_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="43" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="43" align="left">
			<bean:message key="page.UserHistory.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="43" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="43" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="userHistory.historyDate.key"/>
			<logic:equal name="userHistory_fieldOrder" value="history_date">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('history_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('history_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="history_date">
				<a href="#" onclick="doOrder('history_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.historyBy.key"/>
			<logic:equal name="userHistory_fieldOrder" value="history_by">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('history_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('history_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="history_by">
				<a href="#" onclick="doOrder('history_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.auditTrailId.key"/>
			<logic:equal name="userHistory_fieldOrder" value="audit_trail_id">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('audit_trail_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('audit_trail_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="audit_trail_id">
				<a href="#" onclick="doOrder('audit_trail_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.userId.key"/>
			<logic:equal name="userHistory_fieldOrder" value="user_id">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('user_id', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('user_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="user_id">
				<a href="#" onclick="doOrder('user_id', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.loginName.key"/>
			<logic:equal name="userHistory_fieldOrder" value="login_name">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="login_name">
				<a href="#" onclick="doOrder('login_name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.loginPassword.key"/>
			<logic:equal name="userHistory_fieldOrder" value="login_password">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_password', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_password', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="login_password">
				<a href="#" onclick="doOrder('login_password', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.pinCode.key"/>
			<logic:equal name="userHistory_fieldOrder" value="pin_code">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('pin_code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('pin_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="pin_code">
				<a href="#" onclick="doOrder('pin_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.mobileNumber.key"/>
			<logic:equal name="userHistory_fieldOrder" value="mobile_number">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('mobile_number', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('mobile_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="mobile_number">
				<a href="#" onclick="doOrder('mobile_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.language.key"/>
			<logic:equal name="userHistory_fieldOrder" value="language">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('language', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('language', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="language">
				<a href="#" onclick="doOrder('language', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.title.key"/>
			<logic:equal name="userHistory_fieldOrder" value="title">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('title', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('title', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="title">
				<a href="#" onclick="doOrder('title', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.name.key"/>
			<logic:equal name="userHistory_fieldOrder" value="name">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.alias.key"/>
			<logic:equal name="userHistory_fieldOrder" value="alias">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('alias', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('alias', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="alias">
				<a href="#" onclick="doOrder('alias', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.picture.key"/>
			<logic:equal name="userHistory_fieldOrder" value="picture">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('picture', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('picture', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="picture">
				<a href="#" onclick="doOrder('picture', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.email.key"/>
			<logic:equal name="userHistory_fieldOrder" value="email">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('email', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('email', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="email">
				<a href="#" onclick="doOrder('email', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.fullName.key"/>
			<logic:equal name="userHistory_fieldOrder" value="full_name">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('full_name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('full_name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="full_name">
				<a href="#" onclick="doOrder('full_name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.homePhoneNumber.key"/>
			<logic:equal name="userHistory_fieldOrder" value="home_phone_number">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('home_phone_number', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('home_phone_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="home_phone_number">
				<a href="#" onclick="doOrder('home_phone_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.mobilePhoneNumber.key"/>
			<logic:equal name="userHistory_fieldOrder" value="mobile_phone_number">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('mobile_phone_number', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('mobile_phone_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="mobile_phone_number">
				<a href="#" onclick="doOrder('mobile_phone_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.employeeNumber.key"/>
			<logic:equal name="userHistory_fieldOrder" value="employee_number">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('employee_number', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('employee_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="employee_number">
				<a href="#" onclick="doOrder('employee_number', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.createdDate.key"/>
			<logic:equal name="userHistory_fieldOrder" value="created_date">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.createdBy.key"/>
			<logic:equal name="userHistory_fieldOrder" value="created_by">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastUpdatedDate.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_updated_date">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastUpdatedBy.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_updated_by">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.firstLogin.key"/>
			<logic:equal name="userHistory_fieldOrder" value="first_login">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('first_login', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('first_login', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="first_login">
				<a href="#" onclick="doOrder('first_login', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastPasswordUpdate.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_password_update">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_password_update', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_password_update', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_password_update">
				<a href="#" onclick="doOrder('last_password_update', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastPinCodeUpdate.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_pincode_update">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_pincode_update', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_pincode_update', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_pincode_update">
				<a href="#" onclick="doOrder('last_pincode_update', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastPassword.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_password">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_password', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_password', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_password">
				<a href="#" onclick="doOrder('last_password', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastPinCode.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_pincode">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_pincode', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_pincode', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_pincode">
				<a href="#" onclick="doOrder('last_pincode', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.loginFailed.key"/>
			<logic:equal name="userHistory_fieldOrder" value="login_failed">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_failed', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_failed', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="login_failed">
				<a href="#" onclick="doOrder('login_failed', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.maxRelease.key"/>
			<logic:equal name="userHistory_fieldOrder" value="max_release">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('max_release', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('max_release', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="max_release">
				<a href="#" onclick="doOrder('max_release', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastReleasedDate.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_released_date">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_released_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_released_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_released_date">
				<a href="#" onclick="doOrder('last_released_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.lastActive.key"/>
			<logic:equal name="userHistory_fieldOrder" value="last_active">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_active', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_active', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="last_active">
				<a href="#" onclick="doOrder('last_active', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.sessionCode.key"/>
			<logic:equal name="userHistory_fieldOrder" value="session_code">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('session_code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('session_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="session_code">
				<a href="#" onclick="doOrder('session_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="userHistory.IPassport.key"/>
			<logic:equal name="userHistory_fieldOrder" value="ipassport">
				<logic:equal name="userHistory_orderType" value="ASC">
					<a href="#" onclick="doOrder('ipassport', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="userHistory_orderType" value="DESC">
					<a href="#" onclick="doOrder('ipassport', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="userHistory_fieldOrder" value="ipassport">
				<a href="#" onclick="doOrder('ipassport', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="userHistory.userLevel.key"/></td>
		<td><bean:message key="userHistory.position.key"/></td>
		<td><bean:message key="userHistory.status.key"/></td>
		<td><bean:message key="userHistory.organization.key"/></td>
		<td><bean:message key="userHistory.securityLevel.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="userHistoryList" scope="request" type="com.app.docmgr.model.UserHistory">
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
		<td><bean:write name="element" property="userId"/></td>
		<td><bean:write name="element" property="loginName"/></td>
		<td><bean:write name="element" property="loginPassword"/></td>
		<td><bean:write name="element" property="pinCode"/></td>
		<td><bean:write name="element" property="mobileNumber"/></td>
		<td><bean:write name="element" property="language"/></td>
		<td><bean:write name="element" property="title"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="alias"/></td>
		<td><bean:write name="element" property="picture"/></td>
		<td><bean:write name="element" property="email"/></td>
		<td><bean:write name="element" property="fullName"/></td>
		<td><bean:write name="element" property="homePhoneNumber"/></td>
		<td><bean:write name="element" property="mobilePhoneNumber"/></td>
		<td><bean:write name="element" property="employeeNumber"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td><bean:write name="element" property="firstLogin"/></td>
		<td><bean:write name="element" property="lastPasswordUpdate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastPinCodeUpdate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastPassword"/></td>
		<td><bean:write name="element" property="lastPinCode"/></td>
		<td><bean:write name="element" property="loginFailed"/></td>
		<td><bean:write name="element" property="maxRelease"/></td>
		<td><bean:write name="element" property="lastReleasedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastActive" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="sessionCode"/></td>
		<td><bean:write name="element" property="IPassport"/></td>
		<td >
				<logic:notEmpty name="element"	property="userLevel">								
					<bean:write name="element" property="userLevel.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="position">								
					<bean:write name="element" property="position.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="status">								
					<bean:write name="element" property="status.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="organization">								
					<bean:write name="element" property="organization.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="securityLevel">								
					<bean:write name="element" property="securityLevel.name"/>
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
		<td colspan="43" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="43" align="right">
		<% if(com.app.docmgr.action.UserHistoryAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("USER_HISTORY_CREATE")) { %>
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
		<td width="150" valign="top"><bean:message key="userHistory.historyDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_historyDate_filter_start', 'userHistory_historyDate_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_historyDate_filter_start', 'userHistory_historyDate_filter_start_cal', null);" name="userHistory_historyDate_filter_start" value="<bean:write name="userHistory_historyDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_historyDate_filter_end', 'userHistory_historyDate_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_historyDate_filter_end', 'userHistory_historyDate_filter_end_cal', null);" name="userHistory_historyDate_filter_end" value="<bean:write name="userHistory_historyDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_historyDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_historyDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.historyBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_historyBy_filter" value="<bean:write name="userHistory_historyBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.loginName.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_loginName_filter" value="<bean:write name="userHistory_loginName_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.loginPassword.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_loginPassword_filter" value="<bean:write name="userHistory_loginPassword_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.pinCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_pinCode_filter" value="<bean:write name="userHistory_pinCode_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.mobileNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_mobileNumber_filter" value="<bean:write name="userHistory_mobileNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.language.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_language_filter" value="<bean:write name="userHistory_language_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.title.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_title_filter" value="<bean:write name="userHistory_title_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_name_filter" value="<bean:write name="userHistory_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.alias.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_alias_filter" value="<bean:write name="userHistory_alias_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.picture.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_picture_filter" value="<bean:write name="userHistory_picture_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.email.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_email_filter" value="<bean:write name="userHistory_email_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.fullName.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_fullName_filter" value="<bean:write name="userHistory_fullName_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.homePhoneNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_homePhoneNumber_filter" value="<bean:write name="userHistory_homePhoneNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.mobilePhoneNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_mobilePhoneNumber_filter" value="<bean:write name="userHistory_mobilePhoneNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.employeeNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_employeeNumber_filter" value="<bean:write name="userHistory_employeeNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="userHistory.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_createdDate_filter_start', 'userHistory_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_createdDate_filter_start', 'userHistory_createdDate_filter_start_cal', null);" name="userHistory_createdDate_filter_start" value="<bean:write name="userHistory_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_createdDate_filter_end', 'userHistory_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_createdDate_filter_end', 'userHistory_createdDate_filter_end_cal', null);" name="userHistory_createdDate_filter_end" value="<bean:write name="userHistory_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_createdBy_filter" value="<bean:write name="userHistory_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="userHistory.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastUpdatedDate_filter_start', 'userHistory_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_lastUpdatedDate_filter_start', 'userHistory_lastUpdatedDate_filter_start_cal', null);" name="userHistory_lastUpdatedDate_filter_start" value="<bean:write name="userHistory_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastUpdatedDate_filter_end', 'userHistory_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_lastUpdatedDate_filter_end', 'userHistory_lastUpdatedDate_filter_end_cal', null);" name="userHistory_lastUpdatedDate_filter_end" value="<bean:write name="userHistory_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_lastUpdatedBy_filter" value="<bean:write name="userHistory_lastUpdatedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.firstLogin.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_firstLogin_filter" value="<bean:write name="userHistory_firstLogin_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="userHistory.lastPasswordUpdate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastPasswordUpdate_filter_start', 'userHistory_lastPasswordUpdate_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_lastPasswordUpdate_filter_start', 'userHistory_lastPasswordUpdate_filter_start_cal', null);" name="userHistory_lastPasswordUpdate_filter_start" value="<bean:write name="userHistory_lastPasswordUpdate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastPasswordUpdate_filter_end', 'userHistory_lastPasswordUpdate_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_lastPasswordUpdate_filter_end', 'userHistory_lastPasswordUpdate_filter_end_cal', null);" name="userHistory_lastPasswordUpdate_filter_end" value="<bean:write name="userHistory_lastPasswordUpdate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_lastPasswordUpdate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_lastPasswordUpdate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="userHistory.lastPinCodeUpdate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastPinCodeUpdate_filter_start', 'userHistory_lastPinCodeUpdate_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_lastPinCodeUpdate_filter_start', 'userHistory_lastPinCodeUpdate_filter_start_cal', null);" name="userHistory_lastPinCodeUpdate_filter_start" value="<bean:write name="userHistory_lastPinCodeUpdate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastPinCodeUpdate_filter_end', 'userHistory_lastPinCodeUpdate_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_lastPinCodeUpdate_filter_end', 'userHistory_lastPinCodeUpdate_filter_end_cal', null);" name="userHistory_lastPinCodeUpdate_filter_end" value="<bean:write name="userHistory_lastPinCodeUpdate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_lastPinCodeUpdate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_lastPinCodeUpdate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.lastPassword.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_lastPassword_filter" value="<bean:write name="userHistory_lastPassword_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.lastPinCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_lastPinCode_filter" value="<bean:write name="userHistory_lastPinCode_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="userHistory.lastReleasedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastReleasedDate_filter_start', 'userHistory_lastReleasedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_lastReleasedDate_filter_start', 'userHistory_lastReleasedDate_filter_start_cal', null);" name="userHistory_lastReleasedDate_filter_start" value="<bean:write name="userHistory_lastReleasedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastReleasedDate_filter_end', 'userHistory_lastReleasedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_lastReleasedDate_filter_end', 'userHistory_lastReleasedDate_filter_end_cal', null);" name="userHistory_lastReleasedDate_filter_end" value="<bean:write name="userHistory_lastReleasedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_lastReleasedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_lastReleasedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="userHistory.lastActive.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastActive_filter_start', 'userHistory_lastActive_filter_start_cal', null);"  onKeyDown="drawCalendar('userHistory_lastActive_filter_start', 'userHistory_lastActive_filter_start_cal', null);" name="userHistory_lastActive_filter_start" value="<bean:write name="userHistory_lastActive_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('userHistory_lastActive_filter_end', 'userHistory_lastActive_filter_end_cal', null);"  onKeyDown="drawCalendar('userHistory_lastActive_filter_end', 'userHistory_lastActive_filter_end_cal', null);" name="userHistory_lastActive_filter_end" value="<bean:write name="userHistory_lastActive_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="userHistory_lastActive_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="userHistory_lastActive_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.sessionCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_sessionCode_filter" value="<bean:write name="userHistory_sessionCode_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="userHistory.IPassport.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="userHistory_IPassport_filter" value="<bean:write name="userHistory_IPassport_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="userHistory.userLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  userHistory_userLevel_filter_value = (String)request.getSession().getAttribute("userHistory_userLevel_filter");
					if("".equals(userHistory_userLevel_filter_value)) userHistory_userLevel_filter_value = "0";
				%>				
				<select name="userHistory_userLevel_filter">
					<option value=""></option>
					<logic:iterate id="userLevelElement" name="userLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="userLevelElement" property="id"/>" 
							<%
								Long userHistory_userLevel_id = userLevelElement.getId();							
								Long userHistory_userLevel_filter_value_c = new Long(userHistory_userLevel_filter_value);
								if(userHistory_userLevel_filter_value_c.equals(userHistory_userLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="userLevelElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="userHistory.position.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  userHistory_position_filter_value = (String)request.getSession().getAttribute("userHistory_position_filter");
					if("".equals(userHistory_position_filter_value)) userHistory_position_filter_value = "0";
				%>				
				<select name="userHistory_position_filter">
					<option value=""></option>
					<logic:iterate id="positionElement" name="positionList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="positionElement" property="id"/>" 
							<%
								Long userHistory_position_id = positionElement.getId();							
								Long userHistory_position_filter_value_c = new Long(userHistory_position_filter_value);
								if(userHistory_position_filter_value_c.equals(userHistory_position_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="positionElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="userHistory.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  userHistory_status_filter_value = (String)request.getSession().getAttribute("userHistory_status_filter");
					if("".equals(userHistory_status_filter_value)) userHistory_status_filter_value = "0";
				%>				
				<select name="userHistory_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long userHistory_status_id = statusElement.getId();							
								Long userHistory_status_filter_value_c = new Long(userHistory_status_filter_value);
								if(userHistory_status_filter_value_c.equals(userHistory_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="userHistory.organization.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  userHistory_organization_filter_value = (String)request.getSession().getAttribute("userHistory_organization_filter");
					if("".equals(userHistory_organization_filter_value)) userHistory_organization_filter_value = "0";
				%>				
				<select name="userHistory_organization_filter">
					<option value=""></option>
					<logic:iterate id="organizationElement" name="organizationList"  type="com.app.docmgr.model.Organization">
						
						<option value="<bean:write name="organizationElement" property="id"/>" 
							<%
								Long userHistory_organization_id = organizationElement.getId();							
								Long userHistory_organization_filter_value_c = new Long(userHistory_organization_filter_value);
								if(userHistory_organization_filter_value_c.equals(userHistory_organization_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="organizationElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="userHistory.securityLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  userHistory_securityLevel_filter_value = (String)request.getSession().getAttribute("userHistory_securityLevel_filter");
					if("".equals(userHistory_securityLevel_filter_value)) userHistory_securityLevel_filter_value = "0";
				%>				
				<select name="userHistory_securityLevel_filter">
					<option value=""></option>
					<logic:iterate id="securityLevelElement" name="securityLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="securityLevelElement" property="id"/>" 
							<%
								Long userHistory_securityLevel_id = securityLevelElement.getId();							
								Long userHistory_securityLevel_filter_value_c = new Long(userHistory_securityLevel_filter_value);
								if(userHistory_securityLevel_filter_value_c.equals(userHistory_securityLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="securityLevelElement" property="name"/></option>
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

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
	if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.User.List"/></TITLE>
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
			document.forms.user.start.value=start;
			document.forms.user.count.value=count;
			document.forms.user.submit();
		}

		function doReset(){
			document.forms.user.user_loginName_filter.value="";
			document.forms.user.user_loginPassword_filter.value="";
			document.forms.user.user_pinCode_filter.value="";
			document.forms.user.user_mobileNumber_filter.value="";
			document.forms.user.user_language_filter.value="";
			document.forms.user.user_title_filter.value="";
			document.forms.user.user_name_filter.value="";
			document.forms.user.user_email_filter.value="";
			document.forms.user.user_fullName_filter.value="";
			document.forms.user.user_homePhoneNumber_filter.value="";
			document.forms.user.user_mobilePhoneNumber_filter.value="";
			document.forms.user.user_employeeNumber_filter.value="";
			document.forms.user.user_createdDate_filter_start.value="";
			document.forms.user.user_createdDate_filter_end.value="";
			document.forms.user.user_createdBy_filter.value="";
			document.forms.user.user_lastUpdatedDate_filter_start.value="";
			document.forms.user.user_lastUpdatedDate_filter_end.value="";
			document.forms.user.user_lastUpdatedBy_filter.value="";
			document.forms.user.user_firstLogin_filter.value="";
			document.forms.user.user_lastPasswordUpdate_filter_start.value="";
			document.forms.user.user_lastPasswordUpdate_filter_end.value="";
			document.forms.user.user_lastPinCodeUpdate_filter_start.value="";
			document.forms.user.user_lastPinCodeUpdate_filter_end.value="";
			document.forms.user.user_lastPassword_filter.value="";
			document.forms.user.user_lastPinCode_filter.value="";
			document.forms.user.user_lastReleasedDate_filter_start.value="";
			document.forms.user.user_lastReleasedDate_filter_end.value="";
			document.forms.user.user_lastActive_filter_start.value="";
			document.forms.user.user_lastActive_filter_end.value="";
			document.forms.user.user_sessionCode_filter.value="";
			document.forms.user.user_IPassport_filter.value="";
			document.forms.user.user_userLevel_filter.value="";
			document.forms.user.user_status_filter.value="";
			document.forms.user.user_organization_filter.value="";
			document.forms.user.user_securityLevel_filter.value="";
			document.forms.user.submit();
		}

		function doOrder(field, type){
			document.forms.user.user_fieldOrder.value=field;
			document.forms.user.user_orderType.value=type;
			document.forms.user.submit();
		}

	-->
</SCRIPT>
<form name="user" method="post" action="user.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="user_fieldOrder" value="<bean:write name="user_fieldOrder"/>">
<INPUT type="hidden" name="user_orderType" value="<bean:write name="user_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="37" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="37" align="left">
			<bean:message key="page.User.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="37" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="37" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="user.loginName.key"/>
			<logic:equal name="user_fieldOrder" value="login_name">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="login_name">
				<a href="#" onclick="doOrder('login_name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.loginPassword.key"/>
			<logic:equal name="user_fieldOrder" value="login_password">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_password', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_password', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="login_password">
				<a href="#" onclick="doOrder('login_password', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.pinCode.key"/>
			<logic:equal name="user_fieldOrder" value="pin_code">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('pin_code', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('pin_code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="pin_code">
				<a href="#" onclick="doOrder('pin_code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.mobileNumber.key"/>
			<logic:equal name="user_fieldOrder" value="mobile_number">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('mobile_number', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('mobile_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="mobile_number">
				<a href="#" onclick="doOrder('mobile_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.language.key"/>
			<logic:equal name="user_fieldOrder" value="language">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('language', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('language', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="language">
				<a href="#" onclick="doOrder('language', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.title.key"/>
			<logic:equal name="user_fieldOrder" value="title">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('title', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('title', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="title">
				<a href="#" onclick="doOrder('title', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.name.key"/>
			<logic:equal name="user_fieldOrder" value="name">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.email.key"/>
			<logic:equal name="user_fieldOrder" value="email">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('email', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('email', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="email">
				<a href="#" onclick="doOrder('email', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.fullName.key"/>
			<logic:equal name="user_fieldOrder" value="full_name">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('full_name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('full_name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="full_name">
				<a href="#" onclick="doOrder('full_name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.homePhoneNumber.key"/>
			<logic:equal name="user_fieldOrder" value="home_phone_number">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('home_phone_number', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('home_phone_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="home_phone_number">
				<a href="#" onclick="doOrder('home_phone_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.mobilePhoneNumber.key"/>
			<logic:equal name="user_fieldOrder" value="mobile_phone_number">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('mobile_phone_number', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('mobile_phone_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="mobile_phone_number">
				<a href="#" onclick="doOrder('mobile_phone_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.employeeNumber.key"/>
			<logic:equal name="user_fieldOrder" value="employee_number">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('employee_number', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('employee_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="employee_number">
				<a href="#" onclick="doOrder('employee_number', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.createdDate.key"/>
			<logic:equal name="user_fieldOrder" value="created_date">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.createdBy.key"/>
			<logic:equal name="user_fieldOrder" value="created_by">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastUpdatedDate.key"/>
			<logic:equal name="user_fieldOrder" value="last_updated_date">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastUpdatedBy.key"/>
			<logic:equal name="user_fieldOrder" value="last_updated_by">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.firstLogin.key"/>
			<logic:equal name="user_fieldOrder" value="first_login">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('first_login', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('first_login', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="first_login">
				<a href="#" onclick="doOrder('first_login', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastPasswordUpdate.key"/>
			<logic:equal name="user_fieldOrder" value="last_password_update">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_password_update', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_password_update', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_password_update">
				<a href="#" onclick="doOrder('last_password_update', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastPinCodeUpdate.key"/>
			<logic:equal name="user_fieldOrder" value="last_pincode_update">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_pincode_update', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_pincode_update', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_pincode_update">
				<a href="#" onclick="doOrder('last_pincode_update', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastPassword.key"/>
			<logic:equal name="user_fieldOrder" value="last_password">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_password', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_password', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_password">
				<a href="#" onclick="doOrder('last_password', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastPinCode.key"/>
			<logic:equal name="user_fieldOrder" value="last_pincode">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_pincode', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_pincode', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_pincode">
				<a href="#" onclick="doOrder('last_pincode', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.loginFailed.key"/>
			<logic:equal name="user_fieldOrder" value="login_failed">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('login_failed', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('login_failed', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="login_failed">
				<a href="#" onclick="doOrder('login_failed', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.maxRelease.key"/>
			<logic:equal name="user_fieldOrder" value="max_release">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('max_release', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('max_release', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="max_release">
				<a href="#" onclick="doOrder('max_release', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastReleasedDate.key"/>
			<logic:equal name="user_fieldOrder" value="last_released_date">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_released_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_released_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_released_date">
				<a href="#" onclick="doOrder('last_released_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.lastActive.key"/>
			<logic:equal name="user_fieldOrder" value="last_active">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_active', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_active', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="last_active">
				<a href="#" onclick="doOrder('last_active', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.sessionCode.key"/>
			<logic:equal name="user_fieldOrder" value="session_code">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('session_code', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('session_code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="session_code">
				<a href="#" onclick="doOrder('session_code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="user.IPassport.key"/>
			<logic:equal name="user_fieldOrder" value="ipassport">
				<logic:equal name="user_orderType" value="ASC">
					<a href="#" onclick="doOrder('ipassport', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="user_orderType" value="DESC">
					<a href="#" onclick="doOrder('ipassport', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="user_fieldOrder" value="ipassport">
				<a href="#" onclick="doOrder('ipassport', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="user.userLevel.key"/></td>
		<td><bean:message key="user.status.key"/></td>
		<td><bean:message key="user.organization.key"/></td>
		<td><bean:message key="user.securityLevel.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="userList" scope="request" type="com.app.docmgr.model.User">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="loginName"/></td>
		<td><bean:write name="element" property="loginPassword"/></td>
		<td><bean:write name="element" property="pinCode"/></td>
		<td><bean:write name="element" property="mobileNumber"/></td>
		<td><bean:write name="element" property="language"/></td>
		<td><bean:write name="element" property="title"/></td>
		<td><bean:write name="element" property="name"/></td>
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
		<td colspan="37" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="37" align="right">
		<% if(com.app.docmgr.admin.action.UserAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:USER_CREATE")) { %>
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
		<td width="150"><bean:message key="user.loginName.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_loginName_filter" value="<bean:write name="user_loginName_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.loginPassword.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_loginPassword_filter" value="<bean:write name="user_loginPassword_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.pinCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_pinCode_filter" value="<bean:write name="user_pinCode_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.mobileNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_mobileNumber_filter" value="<bean:write name="user_mobileNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.language.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_language_filter" value="<bean:write name="user_language_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.title.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_title_filter" value="<bean:write name="user_title_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_name_filter" value="<bean:write name="user_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.email.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_email_filter" value="<bean:write name="user_email_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.fullName.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_fullName_filter" value="<bean:write name="user_fullName_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.homePhoneNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_homePhoneNumber_filter" value="<bean:write name="user_homePhoneNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.mobilePhoneNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_mobilePhoneNumber_filter" value="<bean:write name="user_mobilePhoneNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.employeeNumber.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_employeeNumber_filter" value="<bean:write name="user_employeeNumber_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="user.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('user_createdDate_filter_start', 'user_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('user_createdDate_filter_start', 'user_createdDate_filter_start_cal', null);" name="user_createdDate_filter_start" value="<bean:write name="user_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('user_createdDate_filter_end', 'user_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('user_createdDate_filter_end', 'user_createdDate_filter_end_cal', null);" name="user_createdDate_filter_end" value="<bean:write name="user_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="user_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="user_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_createdBy_filter" value="<bean:write name="user_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="user.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastUpdatedDate_filter_start', 'user_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('user_lastUpdatedDate_filter_start', 'user_lastUpdatedDate_filter_start_cal', null);" name="user_lastUpdatedDate_filter_start" value="<bean:write name="user_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastUpdatedDate_filter_end', 'user_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('user_lastUpdatedDate_filter_end', 'user_lastUpdatedDate_filter_end_cal', null);" name="user_lastUpdatedDate_filter_end" value="<bean:write name="user_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="user_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="user_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_lastUpdatedBy_filter" value="<bean:write name="user_lastUpdatedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.firstLogin.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_firstLogin_filter" value="<bean:write name="user_firstLogin_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="user.lastPasswordUpdate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastPasswordUpdate_filter_start', 'user_lastPasswordUpdate_filter_start_cal', null);"  onKeyDown="drawCalendar('user_lastPasswordUpdate_filter_start', 'user_lastPasswordUpdate_filter_start_cal', null);" name="user_lastPasswordUpdate_filter_start" value="<bean:write name="user_lastPasswordUpdate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastPasswordUpdate_filter_end', 'user_lastPasswordUpdate_filter_end_cal', null);"  onKeyDown="drawCalendar('user_lastPasswordUpdate_filter_end', 'user_lastPasswordUpdate_filter_end_cal', null);" name="user_lastPasswordUpdate_filter_end" value="<bean:write name="user_lastPasswordUpdate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="user_lastPasswordUpdate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="user_lastPasswordUpdate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="user.lastPinCodeUpdate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastPinCodeUpdate_filter_start', 'user_lastPinCodeUpdate_filter_start_cal', null);"  onKeyDown="drawCalendar('user_lastPinCodeUpdate_filter_start', 'user_lastPinCodeUpdate_filter_start_cal', null);" name="user_lastPinCodeUpdate_filter_start" value="<bean:write name="user_lastPinCodeUpdate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastPinCodeUpdate_filter_end', 'user_lastPinCodeUpdate_filter_end_cal', null);"  onKeyDown="drawCalendar('user_lastPinCodeUpdate_filter_end', 'user_lastPinCodeUpdate_filter_end_cal', null);" name="user_lastPinCodeUpdate_filter_end" value="<bean:write name="user_lastPinCodeUpdate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="user_lastPinCodeUpdate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="user_lastPinCodeUpdate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.lastPassword.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_lastPassword_filter" value="<bean:write name="user_lastPassword_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.lastPinCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_lastPinCode_filter" value="<bean:write name="user_lastPinCode_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="user.lastReleasedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastReleasedDate_filter_start', 'user_lastReleasedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('user_lastReleasedDate_filter_start', 'user_lastReleasedDate_filter_start_cal', null);" name="user_lastReleasedDate_filter_start" value="<bean:write name="user_lastReleasedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastReleasedDate_filter_end', 'user_lastReleasedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('user_lastReleasedDate_filter_end', 'user_lastReleasedDate_filter_end_cal', null);" name="user_lastReleasedDate_filter_end" value="<bean:write name="user_lastReleasedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="user_lastReleasedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="user_lastReleasedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="user.lastActive.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastActive_filter_start', 'user_lastActive_filter_start_cal', null);"  onKeyDown="drawCalendar('user_lastActive_filter_start', 'user_lastActive_filter_start_cal', null);" name="user_lastActive_filter_start" value="<bean:write name="user_lastActive_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('user_lastActive_filter_end', 'user_lastActive_filter_end_cal', null);"  onKeyDown="drawCalendar('user_lastActive_filter_end', 'user_lastActive_filter_end_cal', null);" name="user_lastActive_filter_end" value="<bean:write name="user_lastActive_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="user_lastActive_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="user_lastActive_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.sessionCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_sessionCode_filter" value="<bean:write name="user_sessionCode_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="user.IPassport.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="user_IPassport_filter" value="<bean:write name="user_IPassport_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="user.userLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  user_userLevel_filter_value = (String)request.getSession().getAttribute("user_userLevel_filter");
					if("".equals(user_userLevel_filter_value)) user_userLevel_filter_value = "0";
				%>				
				<select name="user_userLevel_filter">
					<option value=""></option>
					<logic:iterate id="userLevelElement" name="userLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="userLevelElement" property="id"/>" 
							<%
								Long user_userLevel_id = userLevelElement.getId();							
								Long user_userLevel_filter_value_c = new Long(user_userLevel_filter_value);
								if(user_userLevel_filter_value_c.equals(user_userLevel_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="userLevelElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  user_status_filter_value = (String)request.getSession().getAttribute("user_status_filter");
					if("".equals(user_status_filter_value)) user_status_filter_value = "0";
				%>				
				<select name="user_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long user_status_id = statusElement.getId();							
								Long user_status_filter_value_c = new Long(user_status_filter_value);
								if(user_status_filter_value_c.equals(user_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.organization.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  user_organization_filter_value = (String)request.getSession().getAttribute("user_organization_filter");
					if("".equals(user_organization_filter_value)) user_organization_filter_value = "0";
				%>				
				<select name="user_organization_filter">
					<option value=""></option>
					<logic:iterate id="organizationElement" name="organizationList"  type="com.app.docmgr.model.Organization">
						
						<option value="<bean:write name="organizationElement" property="id"/>" 
							<%
								Long user_organization_id = organizationElement.getId();							
								Long user_organization_filter_value_c = new Long(user_organization_filter_value);
								if(user_organization_filter_value_c.equals(user_organization_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="organizationElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="user.securityLevel.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  user_securityLevel_filter_value = (String)request.getSession().getAttribute("user_securityLevel_filter");
					if("".equals(user_securityLevel_filter_value)) user_securityLevel_filter_value = "0";
				%>				
				<select name="user_securityLevel_filter">
					<option value=""></option>
					<logic:iterate id="securityLevelElement" name="securityLevelList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="securityLevelElement" property="id"/>" 
							<%
								Long user_securityLevel_id = securityLevelElement.getId();							
								Long user_securityLevel_filter_value_c = new Long(user_securityLevel_filter_value);
								if(user_securityLevel_filter_value_c.equals(user_securityLevel_id))out.print(" SELECTED ");
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

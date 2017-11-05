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
	if(com.app.docmgr.admin.action.NotificationAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:NOTIFICATION_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Notification.List"/></TITLE>
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
			document.forms.notification.start.value=start;
			document.forms.notification.count.value=count;
			document.forms.notification.submit();
		}

		function doReset(){
			document.forms.notification.notification_flag_filter.value="";
			document.forms.notification.notification_notificationType_filter.value="";
			document.forms.notification.notification_postMessage_filter.value="";
			document.forms.notification.notification_subscriber_filter.value="";
			document.forms.notification.submit();
		}

		function doOrder(field, type){
			document.forms.notification.notification_fieldOrder.value=field;
			document.forms.notification.notification_orderType.value=type;
			document.forms.notification.submit();
		}

	-->
</SCRIPT>
<form name="notification" method="post" action="notification.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="notification_fieldOrder" value="<bean:write name="notification_fieldOrder"/>">
<INPUT type="hidden" name="notification_orderType" value="<bean:write name="notification_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="11" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="11" align="left">
			<bean:message key="page.Notification.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="11" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="11" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="notification.flag.key"/>
			<logic:equal name="notification_fieldOrder" value="flag">
				<logic:equal name="notification_orderType" value="ASC">
					<a href="#" onclick="doOrder('flag', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="notification_orderType" value="DESC">
					<a href="#" onclick="doOrder('flag', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="notification_fieldOrder" value="flag">
				<a href="#" onclick="doOrder('flag', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="notification.notificationType.key"/></td>
		<td><bean:message key="notification.postMessage.key"/></td>
		<td><bean:message key="notification.subscriber.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="notificationList" scope="request" type="com.app.docmgr.model.Notification">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="flag"/></td>
		<td >
				<logic:notEmpty name="element"	property="notificationType">								
					<bean:write name="element" property="notificationType.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="postMessage">								
					<bean:write name="element" property="postMessage.content"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="subscriber">								
					<bean:write name="element" property="subscriber.name"/>
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
		<td colspan="11" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="11" align="right">
		<% if(com.app.docmgr.admin.action.NotificationAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:NOTIFICATION_CREATE")) { %>
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
		<td width="150"><bean:message key="notification.flag.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="notification_flag_filter" value="<bean:write name="notification_flag_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="notification.notificationType.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  notification_notificationType_filter_value = (String)request.getSession().getAttribute("notification_notificationType_filter");
					if("".equals(notification_notificationType_filter_value)) notification_notificationType_filter_value = "0";
				%>				
				<select name="notification_notificationType_filter">
					<option value=""></option>
					<logic:iterate id="notificationTypeElement" name="notificationTypeList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="notificationTypeElement" property="id"/>" 
							<%
								Long notification_notificationType_id = notificationTypeElement.getId();							
								Long notification_notificationType_filter_value_c = new Long(notification_notificationType_filter_value);
								if(notification_notificationType_filter_value_c.equals(notification_notificationType_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="notificationTypeElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="notification.postMessage.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  notification_postMessage_filter_value = (String)request.getSession().getAttribute("notification_postMessage_filter");
					if("".equals(notification_postMessage_filter_value)) notification_postMessage_filter_value = "0";
				%>				
				<select name="notification_postMessage_filter">
					<option value=""></option>
					<logic:iterate id="postMessageElement" name="postMessageList"  type="com.app.docmgr.model.Message">
						
						<option value="<bean:write name="postMessageElement" property="id"/>" 
							<%
								Long notification_postMessage_id = postMessageElement.getId();							
								Long notification_postMessage_filter_value_c = new Long(notification_postMessage_filter_value);
								if(notification_postMessage_filter_value_c.equals(notification_postMessage_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="postMessageElement" property="content"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="notification.subscriber.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  notification_subscriber_filter_value = (String)request.getSession().getAttribute("notification_subscriber_filter");
					if("".equals(notification_subscriber_filter_value)) notification_subscriber_filter_value = "0";
				%>				
				<select name="notification_subscriber_filter">
					<option value=""></option>
					<logic:iterate id="subscriberElement" name="subscriberList"  type="com.app.docmgr.model.User">
						
						<option value="<bean:write name="subscriberElement" property="id"/>" 
							<%
								Long notification_subscriber_id = subscriberElement.getId();							
								Long notification_subscriber_filter_value_c = new Long(notification_subscriber_filter_value);
								if(notification_subscriber_filter_value_c.equals(notification_subscriber_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="subscriberElement" property="name"/></option>
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

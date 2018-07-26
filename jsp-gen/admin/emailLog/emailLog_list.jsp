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
	if(com.app.docmgr.admin.action.EmailLogAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:EMAIL_LOG_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.EmailLog.List"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 21:02:14
 */
-->
</HEAD>
<BODY>
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.emailLog.start.value=start;
			document.forms.emailLog.count.value=count;
			document.forms.emailLog.submit();
		}

		function doReset(){
			document.forms.emailLog.emailLog_type_filter.value="";
			document.forms.emailLog.emailLog_sender_filter.value="";
			document.forms.emailLog.emailLog_rcptTo_filter.value="";
			document.forms.emailLog.emailLog_subject_filter.value="";
			document.forms.emailLog.emailLog_message_filter.value="";
			document.forms.emailLog.emailLog_dueDate_filter_start.value="";
			document.forms.emailLog.emailLog_dueDate_filter_end.value="";
			document.forms.emailLog.emailLog_sentDate_filter_start.value="";
			document.forms.emailLog.emailLog_sentDate_filter_end.value="";
			document.forms.emailLog.emailLog_lastTrialDate_filter_start.value="";
			document.forms.emailLog.emailLog_lastTrialDate_filter_end.value="";
			document.forms.emailLog.emailLog_status_filter.value="";
			document.forms.emailLog.submit();
		}

		function doOrder(field, type){
			document.forms.emailLog.emailLog_fieldOrder.value=field;
			document.forms.emailLog.emailLog_orderType.value=type;
			document.forms.emailLog.submit();
		}

	-->
</SCRIPT>
<form name="emailLog" method="post" action="emailLog.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="emailLog_fieldOrder" value="<bean:write name="emailLog_fieldOrder"/>">
<INPUT type="hidden" name="emailLog_orderType" value="<bean:write name="emailLog_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="19" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="19" align="left">
			<bean:message key="page.EmailLog.List"/>
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
			<bean:message key="emailLog.type.key"/>
			<logic:equal name="emailLog_fieldOrder" value="type">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('type', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="type">
				<a href="#" onclick="doOrder('type', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.sender.key"/>
			<logic:equal name="emailLog_fieldOrder" value="sender">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('sender', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('sender', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="sender">
				<a href="#" onclick="doOrder('sender', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.rcptTo.key"/>
			<logic:equal name="emailLog_fieldOrder" value="rcpt_to">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('rcpt_to', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('rcpt_to', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="rcpt_to">
				<a href="#" onclick="doOrder('rcpt_to', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.subject.key"/>
			<logic:equal name="emailLog_fieldOrder" value="subject">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('subject', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('subject', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="subject">
				<a href="#" onclick="doOrder('subject', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.message.key"/>
			<logic:equal name="emailLog_fieldOrder" value="message">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('message', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('message', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="message">
				<a href="#" onclick="doOrder('message', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.retry.key"/>
			<logic:equal name="emailLog_fieldOrder" value="retry">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('retry', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('retry', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="retry">
				<a href="#" onclick="doOrder('retry', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.dueDate.key"/>
			<logic:equal name="emailLog_fieldOrder" value="due_date">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('due_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('due_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="due_date">
				<a href="#" onclick="doOrder('due_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.sentDate.key"/>
			<logic:equal name="emailLog_fieldOrder" value="sent_date">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('sent_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('sent_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="sent_date">
				<a href="#" onclick="doOrder('sent_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="emailLog.lastTrialDate.key"/>
			<logic:equal name="emailLog_fieldOrder" value="last_trial_date">
				<logic:equal name="emailLog_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_trial_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="emailLog_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_trial_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="emailLog_fieldOrder" value="last_trial_date">
				<a href="#" onclick="doOrder('last_trial_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="emailLog.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="emailLogList" scope="request" type="com.app.docmgr.model.EmailLog">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="type"/></td>
		<td><bean:write name="element" property="sender"/></td>
		<td><bean:write name="element" property="rcptTo"/></td>
		<td><bean:write name="element" property="subject"/></td>
		<td><bean:write name="element" property="message"/></td>
		<td><bean:write name="element" property="retry"/></td>
		<td><bean:write name="element" property="dueDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="sentDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastTrialDate" format="dd MMM yyyy"/></td>
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
		<td colspan="19" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="19" align="right">
		<% if(com.app.docmgr.admin.action.EmailLogAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:EMAIL_LOG_CREATE")) { %>
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
		<td width="150"><bean:message key="emailLog.type.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="emailLog_type_filter" value="<bean:write name="emailLog_type_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="emailLog.sender.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="emailLog_sender_filter" value="<bean:write name="emailLog_sender_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="emailLog.rcptTo.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="emailLog_rcptTo_filter" value="<bean:write name="emailLog_rcptTo_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="emailLog.subject.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="emailLog_subject_filter" value="<bean:write name="emailLog_subject_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="emailLog.message.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="emailLog_message_filter" value="<bean:write name="emailLog_message_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="emailLog.dueDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('emailLog_dueDate_filter_start', 'emailLog_dueDate_filter_start_cal', null);"  onKeyDown="drawCalendar('emailLog_dueDate_filter_start', 'emailLog_dueDate_filter_start_cal', null);" name="emailLog_dueDate_filter_start" value="<bean:write name="emailLog_dueDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('emailLog_dueDate_filter_end', 'emailLog_dueDate_filter_end_cal', null);"  onKeyDown="drawCalendar('emailLog_dueDate_filter_end', 'emailLog_dueDate_filter_end_cal', null);" name="emailLog_dueDate_filter_end" value="<bean:write name="emailLog_dueDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="emailLog_dueDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="emailLog_dueDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="emailLog.sentDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('emailLog_sentDate_filter_start', 'emailLog_sentDate_filter_start_cal', null);"  onKeyDown="drawCalendar('emailLog_sentDate_filter_start', 'emailLog_sentDate_filter_start_cal', null);" name="emailLog_sentDate_filter_start" value="<bean:write name="emailLog_sentDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('emailLog_sentDate_filter_end', 'emailLog_sentDate_filter_end_cal', null);"  onKeyDown="drawCalendar('emailLog_sentDate_filter_end', 'emailLog_sentDate_filter_end_cal', null);" name="emailLog_sentDate_filter_end" value="<bean:write name="emailLog_sentDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="emailLog_sentDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="emailLog_sentDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="emailLog.lastTrialDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('emailLog_lastTrialDate_filter_start', 'emailLog_lastTrialDate_filter_start_cal', null);"  onKeyDown="drawCalendar('emailLog_lastTrialDate_filter_start', 'emailLog_lastTrialDate_filter_start_cal', null);" name="emailLog_lastTrialDate_filter_start" value="<bean:write name="emailLog_lastTrialDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('emailLog_lastTrialDate_filter_end', 'emailLog_lastTrialDate_filter_end_cal', null);"  onKeyDown="drawCalendar('emailLog_lastTrialDate_filter_end', 'emailLog_lastTrialDate_filter_end_cal', null);" name="emailLog_lastTrialDate_filter_end" value="<bean:write name="emailLog_lastTrialDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="emailLog_lastTrialDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="emailLog_lastTrialDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
		<tr>
			<td width="150"><bean:message key="emailLog.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  emailLog_status_filter_value = (String)request.getSession().getAttribute("emailLog_status_filter");
					if("".equals(emailLog_status_filter_value)) emailLog_status_filter_value = "0";
				%>				
				<select name="emailLog_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long emailLog_status_id = statusElement.getId();							
								Long emailLog_status_filter_value_c = new Long(emailLog_status_filter_value);
								if(emailLog_status_filter_value_c.equals(emailLog_status_id))out.print(" SELECTED ");
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

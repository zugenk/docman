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
	if(com.app.docmgr.action.MessageAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("MESSAGE_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Message.List"/></TITLE>
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
			document.forms.message.start.value=start;
			document.forms.message.count.value=count;
			document.forms.message.submit();
		}

		function doReset(){
			document.forms.message.message_content_filter.value="";
			document.forms.message.message_createdDate_filter_start.value="";
			document.forms.message.message_createdDate_filter_end.value="";
			document.forms.message.message_createdBy_filter.value="";
			document.forms.message.message_lastUpdatedDate_filter_start.value="";
			document.forms.message.message_lastUpdatedDate_filter_end.value="";
			document.forms.message.message_lastUpdatedBy_filter.value="";
			document.forms.message.message_filterCode_filter.value="";
			document.forms.message.message_postType_filter.value="";
			document.forms.message.message_status_filter.value="";
			document.forms.message.message_topic_filter.value="";
			document.forms.message.message_parent_filter.value="";
			document.forms.message.submit();
		}

		function doOrder(field, type){
			document.forms.message.message_fieldOrder.value=field;
			document.forms.message.message_orderType.value=type;
			document.forms.message.submit();
		}

	-->
</SCRIPT>
<form name="message" method="post" action="message.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="message_fieldOrder" value="<bean:write name="message_fieldOrder"/>">
<INPUT type="hidden" name="message_orderType" value="<bean:write name="message_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="16" align="left">
			<bean:message key="page.Message.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="16" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="16" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="message.content.key"/>
			<logic:equal name="message_fieldOrder" value="content">
				<logic:equal name="message_orderType" value="ASC">
					<a href="#" onclick="doOrder('content', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="message_orderType" value="DESC">
					<a href="#" onclick="doOrder('content', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="message_fieldOrder" value="content">
				<a href="#" onclick="doOrder('content', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="message.createdDate.key"/>
			<logic:equal name="message_fieldOrder" value="created_date">
				<logic:equal name="message_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="message_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="message_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="message.createdBy.key"/>
			<logic:equal name="message_fieldOrder" value="created_by">
				<logic:equal name="message_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="message_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="message_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="message.lastUpdatedDate.key"/>
			<logic:equal name="message_fieldOrder" value="last_updated_date">
				<logic:equal name="message_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="message_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="message_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="message.lastUpdatedBy.key"/>
			<logic:equal name="message_fieldOrder" value="last_updated_by">
				<logic:equal name="message_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="message_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="message_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="message.filterCode.key"/>
			<logic:equal name="message_fieldOrder" value="filter_code">
				<logic:equal name="message_orderType" value="ASC">
					<a href="#" onclick="doOrder('filter_code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="message_orderType" value="DESC">
					<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="message_fieldOrder" value="filter_code">
				<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="message.postType.key"/></td>
		<td><bean:message key="message.status.key"/></td>
		<td><bean:message key="message.topic.key"/></td>
		<td><bean:message key="message.parent.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="messageList" scope="request" type="com.app.docmgr.model.Message">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="content"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td><bean:write name="element" property="filterCode"/></td>
		<td >
				<logic:notEmpty name="element"	property="postType">								
					<bean:write name="element" property="postType.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="status">								
					<bean:write name="element" property="status.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="topic">								
					<bean:write name="element" property="topic.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="parent">								
					<bean:write name="element" property="parent.content"/>
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
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="16" align="right">
		<% if(com.app.docmgr.action.MessageAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("MESSAGE_CREATE")) { %>
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
		<td width="150"><bean:message key="message.content.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="message_content_filter" value="<bean:write name="message_content_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="message.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('message_createdDate_filter_start', 'message_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('message_createdDate_filter_start', 'message_createdDate_filter_start_cal', null);" name="message_createdDate_filter_start" value="<bean:write name="message_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('message_createdDate_filter_end', 'message_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('message_createdDate_filter_end', 'message_createdDate_filter_end_cal', null);" name="message_createdDate_filter_end" value="<bean:write name="message_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="message_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="message_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="message.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="message_createdBy_filter" value="<bean:write name="message_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="message.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('message_lastUpdatedDate_filter_start', 'message_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('message_lastUpdatedDate_filter_start', 'message_lastUpdatedDate_filter_start_cal', null);" name="message_lastUpdatedDate_filter_start" value="<bean:write name="message_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('message_lastUpdatedDate_filter_end', 'message_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('message_lastUpdatedDate_filter_end', 'message_lastUpdatedDate_filter_end_cal', null);" name="message_lastUpdatedDate_filter_end" value="<bean:write name="message_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="message_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="message_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="message.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="message_lastUpdatedBy_filter" value="<bean:write name="message_lastUpdatedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="message.filterCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="message_filterCode_filter" value="<bean:write name="message_filterCode_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="message.postType.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  message_postType_filter_value = (String)request.getSession().getAttribute("message_postType_filter");
					if("".equals(message_postType_filter_value)) message_postType_filter_value = "0";
				%>				
				<select name="message_postType_filter">
					<option value=""></option>
					<logic:iterate id="postTypeElement" name="postTypeList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="postTypeElement" property="id"/>" 
							<%
								Long message_postType_id = postTypeElement.getId();							
								Long message_postType_filter_value_c = new Long(message_postType_filter_value);
								if(message_postType_filter_value_c.equals(message_postType_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="postTypeElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="message.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  message_status_filter_value = (String)request.getSession().getAttribute("message_status_filter");
					if("".equals(message_status_filter_value)) message_status_filter_value = "0";
				%>				
				<select name="message_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long message_status_id = statusElement.getId();							
								Long message_status_filter_value_c = new Long(message_status_filter_value);
								if(message_status_filter_value_c.equals(message_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="message.topic.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  message_topic_filter_value = (String)request.getSession().getAttribute("message_topic_filter");
					if("".equals(message_topic_filter_value)) message_topic_filter_value = "0";
				%>				
				<select name="message_topic_filter">
					<option value=""></option>
					<logic:iterate id="topicElement" name="topicList"  type="com.app.docmgr.model.Topic">
						
						<option value="<bean:write name="topicElement" property="id"/>" 
							<%
								Long message_topic_id = topicElement.getId();							
								Long message_topic_filter_value_c = new Long(message_topic_filter_value);
								if(message_topic_filter_value_c.equals(message_topic_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="topicElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="message.parent.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  message_parent_filter_value = (String)request.getSession().getAttribute("message_parent_filter");
					if("".equals(message_parent_filter_value)) message_parent_filter_value = "0";
				%>				
				<select name="message_parent_filter">
					<option value=""></option>
					<logic:iterate id="parentElement" name="parentList"  type="com.app.docmgr.model.Message">
						
						<option value="<bean:write name="parentElement" property="id"/>" 
							<%
								Long message_parent_id = parentElement.getId();							
								Long message_parent_filter_value_c = new Long(message_parent_filter_value);
								if(message_parent_filter_value_c.equals(message_parent_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="parentElement" property="content"/></option>
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

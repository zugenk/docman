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
	if(com.app.docmgr.action.TopicAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("TOPIC_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Topic.List"/></TITLE>
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
			document.forms.topic.start.value=start;
			document.forms.topic.count.value=count;
			document.forms.topic.submit();
		}

		function doReset(){
			document.forms.topic.topic_code_filter.value="";
			document.forms.topic.topic_icon_filter.value="";
			document.forms.topic.topic_name_filter.value="";
			document.forms.topic.topic_description_filter.value="";
			document.forms.topic.topic_createdDate_filter_start.value="";
			document.forms.topic.topic_createdDate_filter_end.value="";
			document.forms.topic.topic_createdBy_filter.value="";
			document.forms.topic.topic_lastUpdatedDate_filter_start.value="";
			document.forms.topic.topic_lastUpdatedDate_filter_end.value="";
			document.forms.topic.topic_lastUpdatedBy_filter.value="";
			document.forms.topic.topic_filterCode_filter.value="";
			document.forms.topic.topic_status_filter.value="";
			document.forms.topic.topic_forum_filter.value="";
			document.forms.topic.topic_latestMessage_filter.value="";
			document.forms.topic.submit();
		}

		function doOrder(field, type){
			document.forms.topic.topic_fieldOrder.value=field;
			document.forms.topic.topic_orderType.value=type;
			document.forms.topic.submit();
		}

	-->
</SCRIPT>
<form name="topic" method="post" action="topic.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="topic_fieldOrder" value="<bean:write name="topic_fieldOrder"/>">
<INPUT type="hidden" name="topic_orderType" value="<bean:write name="topic_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="21" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="21" align="left">
			<bean:message key="page.Topic.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="21" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="21" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="topic.code.key"/>
			<logic:equal name="topic_fieldOrder" value="code">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="code">
				<a href="#" onclick="doOrder('code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.icon.key"/>
			<logic:equal name="topic_fieldOrder" value="icon">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('icon', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('icon', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="icon">
				<a href="#" onclick="doOrder('icon', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.name.key"/>
			<logic:equal name="topic_fieldOrder" value="name">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.description.key"/>
			<logic:equal name="topic_fieldOrder" value="description">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.numberOfLike.key"/>
			<logic:equal name="topic_fieldOrder" value="">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="">
				<a href="#" onclick="doOrder('', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.numberOfPost.key"/>
			<logic:equal name="topic_fieldOrder" value="">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="">
				<a href="#" onclick="doOrder('', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.createdDate.key"/>
			<logic:equal name="topic_fieldOrder" value="created_date">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.createdBy.key"/>
			<logic:equal name="topic_fieldOrder" value="created_by">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.lastUpdatedDate.key"/>
			<logic:equal name="topic_fieldOrder" value="last_updated_date">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.lastUpdatedBy.key"/>
			<logic:equal name="topic_fieldOrder" value="last_updated_by">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="topic.filterCode.key"/>
			<logic:equal name="topic_fieldOrder" value="filter_code">
				<logic:equal name="topic_orderType" value="ASC">
					<a href="#" onclick="doOrder('filter_code', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="topic_orderType" value="DESC">
					<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="topic_fieldOrder" value="filter_code">
				<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="topic.status.key"/></td>
		<td><bean:message key="topic.forum.key"/></td>
		<td><bean:message key="topic.latestMessage.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="topicList" scope="request" type="com.app.docmgr.model.Topic">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="code"/></td>
		<td><bean:write name="element" property="icon"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="description"/></td>
		<td><bean:write name="element" property="numberOfLike"/></td>
		<td><bean:write name="element" property="numberOfPost"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td><bean:write name="element" property="filterCode"/></td>
		<td >
				<logic:notEmpty name="element"	property="status">								
					<bean:write name="element" property="status.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="forum">								
					<bean:write name="element" property="forum.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="latestMessage">								
					<bean:write name="element" property="latestMessage.content"/>
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
		<td colspan="21" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="21" align="right">
		<% if(com.app.docmgr.action.TopicAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("TOPIC_CREATE")) { %>
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
		<td width="150"><bean:message key="topic.code.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_code_filter" value="<bean:write name="topic_code_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="topic.icon.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_icon_filter" value="<bean:write name="topic_icon_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="topic.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_name_filter" value="<bean:write name="topic_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="topic.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_description_filter" value="<bean:write name="topic_description_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="topic.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('topic_createdDate_filter_start', 'topic_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('topic_createdDate_filter_start', 'topic_createdDate_filter_start_cal', null);" name="topic_createdDate_filter_start" value="<bean:write name="topic_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('topic_createdDate_filter_end', 'topic_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('topic_createdDate_filter_end', 'topic_createdDate_filter_end_cal', null);" name="topic_createdDate_filter_end" value="<bean:write name="topic_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="topic_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="topic_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="topic.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_createdBy_filter" value="<bean:write name="topic_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="topic.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('topic_lastUpdatedDate_filter_start', 'topic_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('topic_lastUpdatedDate_filter_start', 'topic_lastUpdatedDate_filter_start_cal', null);" name="topic_lastUpdatedDate_filter_start" value="<bean:write name="topic_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('topic_lastUpdatedDate_filter_end', 'topic_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('topic_lastUpdatedDate_filter_end', 'topic_lastUpdatedDate_filter_end_cal', null);" name="topic_lastUpdatedDate_filter_end" value="<bean:write name="topic_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="topic_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="topic_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="topic.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_lastUpdatedBy_filter" value="<bean:write name="topic_lastUpdatedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="topic.filterCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="topic_filterCode_filter" value="<bean:write name="topic_filterCode_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="topic.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  topic_status_filter_value = (String)request.getSession().getAttribute("topic_status_filter");
					if("".equals(topic_status_filter_value)) topic_status_filter_value = "0";
				%>				
				<select name="topic_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long topic_status_id = statusElement.getId();							
								Long topic_status_filter_value_c = new Long(topic_status_filter_value);
								if(topic_status_filter_value_c.equals(topic_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="topic.forum.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  topic_forum_filter_value = (String)request.getSession().getAttribute("topic_forum_filter");
					if("".equals(topic_forum_filter_value)) topic_forum_filter_value = "0";
				%>				
				<select name="topic_forum_filter">
					<option value=""></option>
					<logic:iterate id="forumElement" name="forumList"  type="com.app.docmgr.model.Forum">
						
						<option value="<bean:write name="forumElement" property="id"/>" 
							<%
								Long topic_forum_id = forumElement.getId();							
								Long topic_forum_filter_value_c = new Long(topic_forum_filter_value);
								if(topic_forum_filter_value_c.equals(topic_forum_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="forumElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="topic.latestMessage.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  topic_latestMessage_filter_value = (String)request.getSession().getAttribute("topic_latestMessage_filter");
					if("".equals(topic_latestMessage_filter_value)) topic_latestMessage_filter_value = "0";
				%>				
				<select name="topic_latestMessage_filter">
					<option value=""></option>
					<logic:iterate id="latestMessageElement" name="latestMessageList"  type="com.app.docmgr.model.Message">
						
						<option value="<bean:write name="latestMessageElement" property="id"/>" 
							<%
								Long topic_latestMessage_id = latestMessageElement.getId();							
								Long topic_latestMessage_filter_value_c = new Long(topic_latestMessage_filter_value);
								if(topic_latestMessage_filter_value_c.equals(topic_latestMessage_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="latestMessageElement" property="content"/></option>
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

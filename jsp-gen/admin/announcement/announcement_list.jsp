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
	if(com.app.docmgr.admin.action.AnnouncementAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:ANNOUNCEMENT_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Announcement.List"/></TITLE>
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
			document.forms.announcement.start.value=start;
			document.forms.announcement.count.value=count;
			document.forms.announcement.submit();
		}

		function doReset(){
			document.forms.announcement.announcement_content_filter.value="";
			document.forms.announcement.announcement_subject_filter.value="";
			document.forms.announcement.announcement_targetUsers_filter.value="";
			document.forms.announcement.announcement_targetOrganizations_filter.value="";
			document.forms.announcement.announcement_createdDate_filter_start.value="";
			document.forms.announcement.announcement_createdDate_filter_end.value="";
			document.forms.announcement.announcement_createdBy_filter.value="";
			document.forms.announcement.announcement_lastUpdatedDate_filter_start.value="";
			document.forms.announcement.announcement_lastUpdatedDate_filter_end.value="";
			document.forms.announcement.announcement_lastUpdatedBy_filter.value="";
			document.forms.announcement.announcement_announcementType_filter.value="";
			document.forms.announcement.announcement_status_filter.value="";
			document.forms.announcement.submit();
		}

		function doOrder(field, type){
			document.forms.announcement.announcement_fieldOrder.value=field;
			document.forms.announcement.announcement_orderType.value=type;
			document.forms.announcement.submit();
		}

	-->
</SCRIPT>
<form name="announcement" method="post" action="announcement.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="announcement_fieldOrder" value="<bean:write name="announcement_fieldOrder"/>">
<INPUT type="hidden" name="announcement_orderType" value="<bean:write name="announcement_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="18" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="18" align="left">
			<bean:message key="page.Announcement.List"/>
		</td>
	</tr>

	<tr>
		<td colspan="18" align="right">
			<bean:write name="paging" filter="false"/>
		</td>
	</tr>
	<tr>
		<td colspan="18" align="right">
			<bean:write name="pagingItem" filter="false"/>
		</td>
	</tr>

	<tr class="title">

		<td>			
			<bean:message key="announcement.content.key"/>
			<logic:equal name="announcement_fieldOrder" value="content">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('content', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('content', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="content">
				<a href="#" onclick="doOrder('content', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.subject.key"/>
			<logic:equal name="announcement_fieldOrder" value="subject">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('subject', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('subject', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="subject">
				<a href="#" onclick="doOrder('subject', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.targetUsers.key"/>
			<logic:equal name="announcement_fieldOrder" value="target_users">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('target_users', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('target_users', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="target_users">
				<a href="#" onclick="doOrder('target_users', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.targetOrganizations.key"/>
			<logic:equal name="announcement_fieldOrder" value="target_organizations">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('target_organizations', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('target_organizations', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="target_organizations">
				<a href="#" onclick="doOrder('target_organizations', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.createdDate.key"/>
			<logic:equal name="announcement_fieldOrder" value="created_date">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.createdBy.key"/>
			<logic:equal name="announcement_fieldOrder" value="created_by">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.lastUpdatedDate.key"/>
			<logic:equal name="announcement_fieldOrder" value="last_updated_date">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="announcement.lastUpdatedBy.key"/>
			<logic:equal name="announcement_fieldOrder" value="last_updated_by">
				<logic:equal name="announcement_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="announcement_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="announcement_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="announcement.announcementType.key"/></td>
		<td><bean:message key="announcement.status.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="announcementList" scope="request" type="com.app.docmgr.model.Announcement">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="content"/></td>
		<td><bean:write name="element" property="subject"/></td>
		<td><bean:write name="element" property="targetUsers"/></td>
		<td><bean:write name="element" property="targetOrganizations"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td >
				<logic:notEmpty name="element"	property="announcementType">								
					<bean:write name="element" property="announcementType.name"/>
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
		<td colspan="18" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="18" align="right">
		<% if(com.app.docmgr.admin.action.AnnouncementAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:ANNOUNCEMENT_CREATE")) { %>
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
		<td width="150"><bean:message key="announcement.content.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="announcement_content_filter" value="<bean:write name="announcement_content_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="announcement.subject.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="announcement_subject_filter" value="<bean:write name="announcement_subject_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="announcement.targetUsers.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="announcement_targetUsers_filter" value="<bean:write name="announcement_targetUsers_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="announcement.targetOrganizations.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="announcement_targetOrganizations_filter" value="<bean:write name="announcement_targetOrganizations_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="announcement.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('announcement_createdDate_filter_start', 'announcement_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('announcement_createdDate_filter_start', 'announcement_createdDate_filter_start_cal', null);" name="announcement_createdDate_filter_start" value="<bean:write name="announcement_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('announcement_createdDate_filter_end', 'announcement_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('announcement_createdDate_filter_end', 'announcement_createdDate_filter_end_cal', null);" name="announcement_createdDate_filter_end" value="<bean:write name="announcement_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="announcement_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="announcement_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="announcement.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="announcement_createdBy_filter" value="<bean:write name="announcement_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="announcement.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('announcement_lastUpdatedDate_filter_start', 'announcement_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('announcement_lastUpdatedDate_filter_start', 'announcement_lastUpdatedDate_filter_start_cal', null);" name="announcement_lastUpdatedDate_filter_start" value="<bean:write name="announcement_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('announcement_lastUpdatedDate_filter_end', 'announcement_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('announcement_lastUpdatedDate_filter_end', 'announcement_lastUpdatedDate_filter_end_cal', null);" name="announcement_lastUpdatedDate_filter_end" value="<bean:write name="announcement_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="announcement_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="announcement_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="announcement.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="announcement_lastUpdatedBy_filter" value="<bean:write name="announcement_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="announcement.announcementType.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  announcement_announcementType_filter_value = (String)request.getSession().getAttribute("announcement_announcementType_filter");
					if("".equals(announcement_announcementType_filter_value)) announcement_announcementType_filter_value = "0";
				%>				
				<select name="announcement_announcementType_filter">
					<option value=""></option>
					<logic:iterate id="announcementTypeElement" name="announcementTypeList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="announcementTypeElement" property="id"/>" 
							<%
								Long announcement_announcementType_id = announcementTypeElement.getId();							
								Long announcement_announcementType_filter_value_c = new Long(announcement_announcementType_filter_value);
								if(announcement_announcementType_filter_value_c.equals(announcement_announcementType_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="announcementTypeElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="announcement.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  announcement_status_filter_value = (String)request.getSession().getAttribute("announcement_status_filter");
					if("".equals(announcement_status_filter_value)) announcement_status_filter_value = "0";
				%>				
				<select name="announcement_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long announcement_status_id = statusElement.getId();							
								Long announcement_status_filter_value_c = new Long(announcement_status_filter_value);
								if(announcement_status_filter_value_c.equals(announcement_status_id))out.print(" SELECTED ");
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

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
	if(com.app.docmgr.admin.action.ForumAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FORUM_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Forum.List"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->
</HEAD>
<BODY>
<SCRIPT type="text/javascript" language="javascript">
	<!--
		function page(start, count){
			document.forms.forum.start.value=start;
			document.forms.forum.count.value=count;
			document.forms.forum.submit();
		}

		function doReset(){
			document.forms.forum.forum_code_filter.value="";
			document.forms.forum.forum_icon_filter.value="";
			document.forms.forum.forum_name_filter.value="";
			document.forms.forum.forum_address_filter.value="";
			document.forms.forum.forum_createdDate_filter_start.value="";
			document.forms.forum.forum_createdDate_filter_end.value="";
			document.forms.forum.forum_createdBy_filter.value="";
			document.forms.forum.forum_lastUpdatedDate_filter_start.value="";
			document.forms.forum.forum_lastUpdatedDate_filter_end.value="";
			document.forms.forum.forum_lastUpdatedBy_filter.value="";
			document.forms.forum.forum_filterCode_filter.value="";
			document.forms.forum.forum_forumType_filter.value="";
			document.forms.forum.forum_parentForum_filter.value="";
			document.forms.forum.submit();
		}

		function doOrder(field, type){
			document.forms.forum.forum_fieldOrder.value=field;
			document.forms.forum.forum_orderType.value=type;
			document.forms.forum.submit();
		}

	-->
</SCRIPT>
<form name="forum" method="post" action="forum.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="forum_fieldOrder" value="<bean:write name="forum_fieldOrder"/>">
<INPUT type="hidden" name="forum_orderType" value="<bean:write name="forum_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="19" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="19" align="left">
			<bean:message key="page.Forum.List"/>
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
			<bean:message key="forum.code.key"/>
			<logic:equal name="forum_fieldOrder" value="code">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('code', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="code">
				<a href="#" onclick="doOrder('code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.icon.key"/>
			<logic:equal name="forum_fieldOrder" value="icon">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('icon', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('icon', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="icon">
				<a href="#" onclick="doOrder('icon', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.name.key"/>
			<logic:equal name="forum_fieldOrder" value="name">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.address.key"/>
			<logic:equal name="forum_fieldOrder" value="address">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('address', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('address', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="address">
				<a href="#" onclick="doOrder('address', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.createdDate.key"/>
			<logic:equal name="forum_fieldOrder" value="created_date">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.createdBy.key"/>
			<logic:equal name="forum_fieldOrder" value="created_by">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.lastUpdatedDate.key"/>
			<logic:equal name="forum_fieldOrder" value="last_updated_date">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.lastUpdatedBy.key"/>
			<logic:equal name="forum_fieldOrder" value="last_updated_by">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="forum.filterCode.key"/>
			<logic:equal name="forum_fieldOrder" value="filter_code">
				<logic:equal name="forum_orderType" value="ASC">
					<a href="#" onclick="doOrder('filter_code', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="forum_orderType" value="DESC">
					<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="forum_fieldOrder" value="filter_code">
				<a href="#" onclick="doOrder('filter_code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="forum.forumType.key"/></td>
		<td><bean:message key="forum.parentForum.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="forumList" scope="request" type="com.app.docmgr.model.Forum">
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
		<td><bean:write name="element" property="address"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td><bean:write name="element" property="filterCode"/></td>
		<td >
				<logic:notEmpty name="element"	property="forumType">								
					<bean:write name="element" property="forumType.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="parentForum">								
					<bean:write name="element" property="parentForum.name"/>
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
		<% if(com.app.docmgr.admin.action.ForumAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FORUM_CREATE")) { %>
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
		<td width="150"><bean:message key="forum.code.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_code_filter" value="<bean:write name="forum_code_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="forum.icon.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_icon_filter" value="<bean:write name="forum_icon_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="forum.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_name_filter" value="<bean:write name="forum_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="forum.address.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_address_filter" value="<bean:write name="forum_address_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="forum.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('forum_createdDate_filter_start', 'forum_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('forum_createdDate_filter_start', 'forum_createdDate_filter_start_cal', null);" name="forum_createdDate_filter_start" value="<bean:write name="forum_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('forum_createdDate_filter_end', 'forum_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('forum_createdDate_filter_end', 'forum_createdDate_filter_end_cal', null);" name="forum_createdDate_filter_end" value="<bean:write name="forum_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="forum_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="forum_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="forum.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_createdBy_filter" value="<bean:write name="forum_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="forum.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('forum_lastUpdatedDate_filter_start', 'forum_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('forum_lastUpdatedDate_filter_start', 'forum_lastUpdatedDate_filter_start_cal', null);" name="forum_lastUpdatedDate_filter_start" value="<bean:write name="forum_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('forum_lastUpdatedDate_filter_end', 'forum_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('forum_lastUpdatedDate_filter_end', 'forum_lastUpdatedDate_filter_end_cal', null);" name="forum_lastUpdatedDate_filter_end" value="<bean:write name="forum_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="forum_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="forum_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="forum.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_lastUpdatedBy_filter" value="<bean:write name="forum_lastUpdatedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="forum.filterCode.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="forum_filterCode_filter" value="<bean:write name="forum_filterCode_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="forum.forumType.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  forum_forumType_filter_value = (String)request.getSession().getAttribute("forum_forumType_filter");
					if("".equals(forum_forumType_filter_value)) forum_forumType_filter_value = "0";
				%>				
				<select name="forum_forumType_filter">
					<option value=""></option>
					<logic:iterate id="forumTypeElement" name="forumTypeList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="forumTypeElement" property="id"/>" 
							<%
								Long forum_forumType_id = forumTypeElement.getId();							
								Long forum_forumType_filter_value_c = new Long(forum_forumType_filter_value);
								if(forum_forumType_filter_value_c.equals(forum_forumType_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="forumTypeElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="forum.parentForum.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  forum_parentForum_filter_value = (String)request.getSession().getAttribute("forum_parentForum_filter");
					if("".equals(forum_parentForum_filter_value)) forum_parentForum_filter_value = "0";
				%>				
				<select name="forum_parentForum_filter">
					<option value=""></option>
					<logic:iterate id="parentForumElement" name="parentForumList"  type="com.app.docmgr.model.Forum">
						
						<option value="<bean:write name="parentForumElement" property="id"/>" 
							<%
								Long forum_parentForum_id = parentForumElement.getId();							
								Long forum_parentForum_filter_value_c = new Long(forum_parentForum_filter_value);
								if(forum_parentForum_filter_value_c.equals(forum_parentForum_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="parentForumElement" property="name"/></option>
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

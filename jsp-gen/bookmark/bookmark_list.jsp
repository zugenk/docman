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
	if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("detail")) { 
		if (privilegeList.contains("BOOKMARK_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Bookmark.List"/></TITLE>
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
			document.forms.bookmark.start.value=start;
			document.forms.bookmark.count.value=count;
			document.forms.bookmark.submit();
		}

		function doReset(){
			document.forms.bookmark.bookmark_url_filter.value="";
			document.forms.bookmark.bookmark_name_filter.value="";
			document.forms.bookmark.bookmark_category_filter.value="";
			document.forms.bookmark.bookmark_note_filter.value="";
			document.forms.bookmark.bookmark_createdDate_filter_start.value="";
			document.forms.bookmark.bookmark_createdDate_filter_end.value="";
			document.forms.bookmark.bookmark_createdBy_filter.value="";
			document.forms.bookmark.bookmark_lastUpdatedDate_filter_start.value="";
			document.forms.bookmark.bookmark_lastUpdatedDate_filter_end.value="";
			document.forms.bookmark.bookmark_lastUpdatedBy_filter.value="";
			document.forms.bookmark.bookmark_bookmarkType_filter.value="";
			document.forms.bookmark.bookmark_status_filter.value="";
			document.forms.bookmark.bookmark_owner_filter.value="";
			document.forms.bookmark.submit();
		}

		function doOrder(field, type){
			document.forms.bookmark.bookmark_fieldOrder.value=field;
			document.forms.bookmark.bookmark_orderType.value=type;
			document.forms.bookmark.submit();
		}

	-->
</SCRIPT>
<form name="bookmark" method="post" action="bookmark.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="bookmark_fieldOrder" value="<bean:write name="bookmark_fieldOrder"/>">
<INPUT type="hidden" name="bookmark_orderType" value="<bean:write name="bookmark_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="18" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="18" align="left">
			<bean:message key="page.Bookmark.List"/>
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
			<bean:message key="bookmark.url.key"/>
			<logic:equal name="bookmark_fieldOrder" value="url">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('url', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('url', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="url">
				<a href="#" onclick="doOrder('url', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.name.key"/>
			<logic:equal name="bookmark_fieldOrder" value="name">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.category.key"/>
			<logic:equal name="bookmark_fieldOrder" value="category">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('category', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('category', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="category">
				<a href="#" onclick="doOrder('category', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.note.key"/>
			<logic:equal name="bookmark_fieldOrder" value="note">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('note', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('note', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="note">
				<a href="#" onclick="doOrder('note', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.createdDate.key"/>
			<logic:equal name="bookmark_fieldOrder" value="created_date">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.createdBy.key"/>
			<logic:equal name="bookmark_fieldOrder" value="created_by">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.lastUpdatedDate.key"/>
			<logic:equal name="bookmark_fieldOrder" value="last_updated_date">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="bookmark.lastUpdatedBy.key"/>
			<logic:equal name="bookmark_fieldOrder" value="last_updated_by">
				<logic:equal name="bookmark_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="bookmark_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="bookmark_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="bookmark.bookmarkType.key"/></td>
		<td><bean:message key="bookmark.status.key"/></td>
		<td><bean:message key="bookmark.owner.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="bookmarkList" scope="request" type="com.app.docmgr.model.Bookmark">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="url"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="category"/></td>
		<td><bean:write name="element" property="note"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td >
				<logic:notEmpty name="element"	property="bookmarkType">								
					<bean:write name="element" property="bookmarkType.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="status">								
					<bean:write name="element" property="status.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="owner">								
					<bean:write name="element" property="owner.name"/>
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
		<% if(com.app.docmgr.action.BookmarkAction.allowableAction.contains("create")) { 
				if (privilegeList.contains("BOOKMARK_CREATE")) { %>
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
		<td width="150"><bean:message key="bookmark.url.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="bookmark_url_filter" value="<bean:write name="bookmark_url_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="bookmark.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="bookmark_name_filter" value="<bean:write name="bookmark_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="bookmark.category.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="bookmark_category_filter" value="<bean:write name="bookmark_category_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="bookmark.note.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="bookmark_note_filter" value="<bean:write name="bookmark_note_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="bookmark.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('bookmark_createdDate_filter_start', 'bookmark_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('bookmark_createdDate_filter_start', 'bookmark_createdDate_filter_start_cal', null);" name="bookmark_createdDate_filter_start" value="<bean:write name="bookmark_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('bookmark_createdDate_filter_end', 'bookmark_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('bookmark_createdDate_filter_end', 'bookmark_createdDate_filter_end_cal', null);" name="bookmark_createdDate_filter_end" value="<bean:write name="bookmark_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="bookmark_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="bookmark_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="bookmark.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="bookmark_createdBy_filter" value="<bean:write name="bookmark_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="bookmark.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('bookmark_lastUpdatedDate_filter_start', 'bookmark_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('bookmark_lastUpdatedDate_filter_start', 'bookmark_lastUpdatedDate_filter_start_cal', null);" name="bookmark_lastUpdatedDate_filter_start" value="<bean:write name="bookmark_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('bookmark_lastUpdatedDate_filter_end', 'bookmark_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('bookmark_lastUpdatedDate_filter_end', 'bookmark_lastUpdatedDate_filter_end_cal', null);" name="bookmark_lastUpdatedDate_filter_end" value="<bean:write name="bookmark_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="bookmark_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="bookmark_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="bookmark.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="bookmark_lastUpdatedBy_filter" value="<bean:write name="bookmark_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="bookmark.bookmarkType.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  bookmark_bookmarkType_filter_value = (String)request.getSession().getAttribute("bookmark_bookmarkType_filter");
					if("".equals(bookmark_bookmarkType_filter_value)) bookmark_bookmarkType_filter_value = "0";
				%>				
				<select name="bookmark_bookmarkType_filter">
					<option value=""></option>
					<logic:iterate id="bookmarkTypeElement" name="bookmarkTypeList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="bookmarkTypeElement" property="id"/>" 
							<%
								Long bookmark_bookmarkType_id = bookmarkTypeElement.getId();							
								Long bookmark_bookmarkType_filter_value_c = new Long(bookmark_bookmarkType_filter_value);
								if(bookmark_bookmarkType_filter_value_c.equals(bookmark_bookmarkType_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="bookmarkTypeElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="bookmark.status.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  bookmark_status_filter_value = (String)request.getSession().getAttribute("bookmark_status_filter");
					if("".equals(bookmark_status_filter_value)) bookmark_status_filter_value = "0";
				%>				
				<select name="bookmark_status_filter">
					<option value=""></option>
					<logic:iterate id="statusElement" name="statusList"  type="com.app.docmgr.model.Status">
						
						<option value="<bean:write name="statusElement" property="id"/>" 
							<%
								Long bookmark_status_id = statusElement.getId();							
								Long bookmark_status_filter_value_c = new Long(bookmark_status_filter_value);
								if(bookmark_status_filter_value_c.equals(bookmark_status_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="statusElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="bookmark.owner.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  bookmark_owner_filter_value = (String)request.getSession().getAttribute("bookmark_owner_filter");
					if("".equals(bookmark_owner_filter_value)) bookmark_owner_filter_value = "0";
				%>				
				<select name="bookmark_owner_filter">
					<option value=""></option>
					<logic:iterate id="ownerElement" name="ownerList"  type="com.app.docmgr.model.User">
						
						<option value="<bean:write name="ownerElement" property="id"/>" 
							<%
								Long bookmark_owner_id = ownerElement.getId();							
								Long bookmark_owner_filter_value_c = new Long(bookmark_owner_filter_value);
								if(bookmark_owner_filter_value_c.equals(bookmark_owner_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="ownerElement" property="name"/></option>
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

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
	if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.Folder.List"/></TITLE>
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
			document.forms.folder.start.value=start;
			document.forms.folder.count.value=count;
			document.forms.folder.submit();
		}

		function doReset(){
			document.forms.folder.folder_code_filter.value="";
			document.forms.folder.folder_name_filter.value="";
			document.forms.folder.folder_createdDate_filter_start.value="";
			document.forms.folder.folder_createdDate_filter_end.value="";
			document.forms.folder.folder_createdBy_filter.value="";
			document.forms.folder.folder_lastUpdatedDate_filter_start.value="";
			document.forms.folder.folder_lastUpdatedDate_filter_end.value="";
			document.forms.folder.folder_lastUpdatedBy_filter.value="";
			document.forms.folder.folder_folderType_filter.value="";
			document.forms.folder.folder_parentFolder_filter.value="";
			document.forms.folder.submit();
		}

		function doOrder(field, type){
			document.forms.folder.folder_fieldOrder.value=field;
			document.forms.folder.folder_orderType.value=type;
			document.forms.folder.submit();
		}

	-->
</SCRIPT>
<form name="folder" method="post" action="folder.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="folder_fieldOrder" value="<bean:write name="folder_fieldOrder"/>">
<INPUT type="hidden" name="folder_orderType" value="<bean:write name="folder_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="16" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="16" align="left">
			<bean:message key="page.Folder.List"/>
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
			<bean:message key="folder.code.key"/>
			<logic:equal name="folder_fieldOrder" value="code">
				<logic:equal name="folder_orderType" value="ASC">
					<a href="#" onclick="doOrder('code', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="folder_orderType" value="DESC">
					<a href="#" onclick="doOrder('code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="folder_fieldOrder" value="code">
				<a href="#" onclick="doOrder('code', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="folder.name.key"/>
			<logic:equal name="folder_fieldOrder" value="name">
				<logic:equal name="folder_orderType" value="ASC">
					<a href="#" onclick="doOrder('name', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="folder_orderType" value="DESC">
					<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="folder_fieldOrder" value="name">
				<a href="#" onclick="doOrder('name', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="folder.createdDate.key"/>
			<logic:equal name="folder_fieldOrder" value="created_date">
				<logic:equal name="folder_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="folder_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="folder_fieldOrder" value="created_date">
				<a href="#" onclick="doOrder('created_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="folder.createdBy.key"/>
			<logic:equal name="folder_fieldOrder" value="created_by">
				<logic:equal name="folder_orderType" value="ASC">
					<a href="#" onclick="doOrder('created_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="folder_orderType" value="DESC">
					<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="folder_fieldOrder" value="created_by">
				<a href="#" onclick="doOrder('created_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="folder.lastUpdatedDate.key"/>
			<logic:equal name="folder_fieldOrder" value="last_updated_date">
				<logic:equal name="folder_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_date', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="folder_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="folder_fieldOrder" value="last_updated_date">
				<a href="#" onclick="doOrder('last_updated_date', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="folder.lastUpdatedBy.key"/>
			<logic:equal name="folder_fieldOrder" value="last_updated_by">
				<logic:equal name="folder_orderType" value="ASC">
					<a href="#" onclick="doOrder('last_updated_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="folder_orderType" value="DESC">
					<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="folder_fieldOrder" value="last_updated_by">
				<a href="#" onclick="doOrder('last_updated_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td><bean:message key="folder.folderType.key"/></td>
		<td><bean:message key="folder.parentFolder.key"/></td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="folderList" scope="request" type="com.app.docmgr.model.Folder">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="code"/></td>
		<td><bean:write name="element" property="name"/></td>
		<td><bean:write name="element" property="createdDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="createdBy"/></td>
		<td><bean:write name="element" property="lastUpdatedDate" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="lastUpdatedBy"/></td>
		<td >
				<logic:notEmpty name="element"	property="folderType">								
					<bean:write name="element" property="folderType.name"/>
				</logic:notEmpty>	
			
		</td>
		<td >
				<logic:notEmpty name="element"	property="parentFolder">								
					<bean:write name="element" property="parentFolder.name"/>
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
		<% if(com.app.docmgr.admin.action.FolderAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:FOLDER_CREATE")) { %>
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
		<td width="150"><bean:message key="folder.code.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="folder_code_filter" value="<bean:write name="folder_code_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="folder.name.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="folder_name_filter" value="<bean:write name="folder_name_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="folder.createdDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('folder_createdDate_filter_start', 'folder_createdDate_filter_start_cal', null);"  onKeyDown="drawCalendar('folder_createdDate_filter_start', 'folder_createdDate_filter_start_cal', null);" name="folder_createdDate_filter_start" value="<bean:write name="folder_createdDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('folder_createdDate_filter_end', 'folder_createdDate_filter_end_cal', null);"  onKeyDown="drawCalendar('folder_createdDate_filter_end', 'folder_createdDate_filter_end_cal', null);" name="folder_createdDate_filter_end" value="<bean:write name="folder_createdDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="folder_createdDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="folder_createdDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="folder.createdBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="folder_createdBy_filter" value="<bean:write name="folder_createdBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150" valign="top"><bean:message key="folder.lastUpdatedDate.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('folder_lastUpdatedDate_filter_start', 'folder_lastUpdatedDate_filter_start_cal', null);"  onKeyDown="drawCalendar('folder_lastUpdatedDate_filter_start', 'folder_lastUpdatedDate_filter_start_cal', null);" name="folder_lastUpdatedDate_filter_start" value="<bean:write name="folder_lastUpdatedDate_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('folder_lastUpdatedDate_filter_end', 'folder_lastUpdatedDate_filter_end_cal', null);"  onKeyDown="drawCalendar('folder_lastUpdatedDate_filter_end', 'folder_lastUpdatedDate_filter_end_cal', null);" name="folder_lastUpdatedDate_filter_end" value="<bean:write name="folder_lastUpdatedDate_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="folder_lastUpdatedDate_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="folder_lastUpdatedDate_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="folder.lastUpdatedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="folder_lastUpdatedBy_filter" value="<bean:write name="folder_lastUpdatedBy_filter"/>"></td>
	</tr>
		<tr>
			<td width="150"><bean:message key="folder.folderType.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  folder_folderType_filter_value = (String)request.getSession().getAttribute("folder_folderType_filter");
					if("".equals(folder_folderType_filter_value)) folder_folderType_filter_value = "0";
				%>				
				<select name="folder_folderType_filter">
					<option value=""></option>
					<logic:iterate id="folderTypeElement" name="folderTypeList"  type="com.app.docmgr.model.Lookup">
						
						<option value="<bean:write name="folderTypeElement" property="id"/>" 
							<%
								Long folder_folderType_id = folderTypeElement.getId();							
								Long folder_folderType_filter_value_c = new Long(folder_folderType_filter_value);
								if(folder_folderType_filter_value_c.equals(folder_folderType_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="folderTypeElement" property="name"/></option>
					</logic:iterate>
				</select>
			</td>
		</tr>
		<tr>
			<td width="150"><bean:message key="folder.parentFolder.key"/></td>
			<td width="10">:</td>
			<td>
				<%
					String  folder_parentFolder_filter_value = (String)request.getSession().getAttribute("folder_parentFolder_filter");
					if("".equals(folder_parentFolder_filter_value)) folder_parentFolder_filter_value = "0";
				%>				
				<select name="folder_parentFolder_filter">
					<option value=""></option>
					<logic:iterate id="parentFolderElement" name="parentFolderList"  type="com.app.docmgr.model.Folder">
						
						<option value="<bean:write name="parentFolderElement" property="id"/>" 
							<%
								Long folder_parentFolder_id = parentFolderElement.getId();							
								Long folder_parentFolder_filter_value_c = new Long(folder_parentFolder_filter_value);
								if(folder_parentFolder_filter_value_c.equals(folder_parentFolder_id))out.print(" SELECTED ");
							%>
						>
						<bean:write name="parentFolderElement" property="name"/></option>
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

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
	if(com.app.docmgr.admin.action.AuditTrailAction.allowableAction.contains("detail")) { 
		if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:AUDIT_TRAIL_DETAIL")) {
			allowDetail=true;			
		}
	}
%>
<HEAD>
<TITLE><bean:message key="page.AuditTrail.List"/></TITLE>
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
			document.forms.auditTrail.start.value=start;
			document.forms.auditTrail.count.value=count;
			document.forms.auditTrail.submit();
		}

		function doReset(){
			document.forms.auditTrail.auditTrail_auditTime_filter_start.value="";
			document.forms.auditTrail.auditTrail_auditTime_filter_end.value="";
			document.forms.auditTrail.auditTrail_entity_filter.value="";
			document.forms.auditTrail.auditTrail_doneBy_filter.value="";
			document.forms.auditTrail.auditTrail_sessionId_filter.value="";
			document.forms.auditTrail.auditTrail_approvedBy_filter.value="";
			document.forms.auditTrail.auditTrail_action_filter.value="";
			document.forms.auditTrail.auditTrail_description_filter.value="";
			document.forms.auditTrail.submit();
		}

		function doOrder(field, type){
			document.forms.auditTrail.auditTrail_fieldOrder.value=field;
			document.forms.auditTrail.auditTrail_orderType.value=type;
			document.forms.auditTrail.submit();
		}

	-->
</SCRIPT>
<form name="auditTrail" method="post" action="auditTrail.do">
<INPUT type="hidden" name="start" value="">
<INPUT type="hidden" name="action" value="">
<INPUT type="hidden" name="count" value="">
<input type="hidden" name="id"/>
<INPUT type="hidden" name="auditTrail_fieldOrder" value="<bean:write name="auditTrail_fieldOrder"/>">
<INPUT type="hidden" name="auditTrail_orderType" value="<bean:write name="auditTrail_orderType"/>">
<%@ include file="../common/header.jsp" %>
<TABLE border="0" width="98%" align="center" cellpadding="3" cellspacing="1">
	<tr>
		<td colspan="18" align="right">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="titleHeader" colspan="18" align="left">
			<bean:message key="page.AuditTrail.List"/>
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
			<bean:message key="auditTrail.auditTime.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="audit_time">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('audit_time', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('audit_time', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="audit_time">
				<a href="#" onclick="doOrder('audit_time', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.entity.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="entity">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('entity', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('entity', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="entity">
				<a href="#" onclick="doOrder('entity', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.entityId.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="entity_id">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('entity_id', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('entity_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="entity_id">
				<a href="#" onclick="doOrder('entity_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.doneBy.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="done_by">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('done_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('done_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="done_by">
				<a href="#" onclick="doOrder('done_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.sessionId.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="session_id">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('session_id', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('session_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="session_id">
				<a href="#" onclick="doOrder('session_id', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.approvedBy.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="approved_by">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('approved_by', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('approved_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="approved_by">
				<a href="#" onclick="doOrder('approved_by', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.action.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="action">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('action', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('action', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="action">
				<a href="#" onclick="doOrder('action', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>
		<td>			
			<bean:message key="auditTrail.description.key"/>
			<logic:equal name="auditTrail_fieldOrder" value="description">
				<logic:equal name="auditTrail_orderType" value="ASC">
					<a href="#" onclick="doOrder('description', 'DESC');"><img src="../template/<%=currentTemplate%>/images/desc.gif" border="0"></a>
				</logic:equal>
				<logic:equal name="auditTrail_orderType" value="DESC">
					<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="auditTrail_fieldOrder" value="description">
				<a href="#" onclick="doOrder('description', 'ASC');"><img src="../template/<%=currentTemplate%>/images/asc.gif"  border="0"></a>
			</logic:notEqual>
		</td>

		<td></td>
	</tr>	

	<logic:iterate id="element" name="auditTrailList" scope="request" type="com.app.docmgr.model.AuditTrail">
		<% 
			sequence++;
			String bgcolor = "FFFFFF";
			if(sequence%2 == 0){
				bgcolor = "EDEDE8";
			}
		 %>
		<tr bgcolor="<% out.print(bgcolor); %>">
		<td><bean:write name="element" property="auditTime" format="dd MMM yyyy"/></td>
		<td><bean:write name="element" property="entity"/></td>
		<td><bean:write name="element" property="entityId"/></td>
		<td><bean:write name="element" property="doneBy"/></td>
		<td><bean:write name="element" property="sessionId"/></td>
		<td><bean:write name="element" property="approvedBy"/></td>
		<td><bean:write name="element" property="action"/></td>
		<td><bean:write name="element" property="description"/></td>

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
		<% if(com.app.docmgr.admin.action.AuditTrailAction.allowableAction.contains("create")) { 
				if (UserService.getInstance().hasPrivilege(loginUser,"ADMIN:AUDIT_TRAIL_CREATE")) { %>
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
		<td width="150" valign="top"><bean:message key="auditTrail.auditTime.key"/></td>
		<td width="10" valign="top">:</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><input type="text" onclick="calendarToogle('auditTrail_auditTime_filter_start', 'auditTrail_auditTime_filter_start_cal', null);"  onKeyDown="drawCalendar('auditTrail_auditTime_filter_start', 'auditTrail_auditTime_filter_start_cal', null);" name="auditTrail_auditTime_filter_start" value="<bean:write name="auditTrail_auditTime_filter_start"/>"></td>
					<td width="20" align="center" valign="top">To</td>
					<td valign="top"><input type="text" onclick="calendarToogle('auditTrail_auditTime_filter_end', 'auditTrail_auditTime_filter_end_cal', null);"  onKeyDown="drawCalendar('auditTrail_auditTime_filter_end', 'auditTrail_auditTime_filter_end_cal', null);" name="auditTrail_auditTime_filter_end" value="<bean:write name="auditTrail_auditTime_filter_end"/>"></td>
				</tr>
				<tr>
					<td><div id="auditTrail_auditTime_filter_start_cal" style="display: none"></div></td>
					<td>&nbsp;</td>
					<td><div id="auditTrail_auditTime_filter_end_cal" style="display: none"></div></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="150"><bean:message key="auditTrail.entity.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="auditTrail_entity_filter" value="<bean:write name="auditTrail_entity_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="auditTrail.doneBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="auditTrail_doneBy_filter" value="<bean:write name="auditTrail_doneBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="auditTrail.sessionId.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="auditTrail_sessionId_filter" value="<bean:write name="auditTrail_sessionId_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="auditTrail.approvedBy.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="auditTrail_approvedBy_filter" value="<bean:write name="auditTrail_approvedBy_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="auditTrail.action.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="auditTrail_action_filter" value="<bean:write name="auditTrail_action_filter"/>"></td>
	</tr>
	<tr>
		<td width="150"><bean:message key="auditTrail.description.key"/></td>
		<td width="10">:</td>
		<td><input type="text" name="auditTrail_description_filter" value="<bean:write name="auditTrail_description_filter"/>"></td>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html-el" prefix="html-el" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>

<html:html>
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<HEAD>
<TITLE><bean:message key="page.DocumentHistory.Create"/></TITLE>
<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */
-->
</HEAD>
<BODY onload="initOption()">
<script language="javascript" src="../template/<%=currentTemplate%>/js/OptionTransferCompact.js"></script>
<script type="text/javascript" language="javascript">
	<!--
		function initOption(){
		}
		
		function markAll(){
		}

	-->
</script>
<% int i=0; %>
<form name="documentHistory" action="documentHistory.do" method="POST" enctype="multipart/form-data" onsubmit="markAll()">
	<input type="hidden" name="action"/>
	<input type="hidden" name="sequence"/>
	<input type="hidden" name="redirect" value="create"/>
	
	<%@ include file="../common/header.jsp" %>
	<table border="0" align="center" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" align="right">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				<bean:message key="page.DocumentHistory.Create"/>
			</td>
		</tr>
		<tr>
			<td class="titleHeader" colspan="3" align="left">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.historyDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="historyDate" onclick="calendarToogle('historyDate', 'historyDate_cal', null);"  onKeyDown="drawCalendar('historyDate', 'historyDate_cal', null);" value="<bean:write name='documentHistory' property='historyDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="historyDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="documentHistory.historyDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.historyDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.historyBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="historyBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.historyBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.historyBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.auditTrailId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><html:text name="documentHistory" property="auditTrailId"/></td>
		</tr>
		<logic:messagesPresent property="documentHistory.auditTrailId">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.auditTrailId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentId.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><html:text name="documentHistory" property="documentId"/></td>
		</tr>
		<logic:messagesPresent property="documentHistory.documentId">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.documentId"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.name.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="name"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentType.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="documentType"/>
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.documentType">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.documentType"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.contentType.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="contentType"/>
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.contentType">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.contentType"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentNumber.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="documentNumber"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.repositoryId.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="repositoryId"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.documentVersion.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="documentVersion"/>
			</td>
		</tr>

		<tr>
			<td width="150"><b><bean:message key="documentHistory.description.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="description"/>
			</td>
		</tr>

 <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.createdDate.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="createdDate" onclick="calendarToogle('createdDate', 'createdDate_cal', null);"  onKeyDown="drawCalendar('createdDate', 'createdDate_cal', null);" value="<bean:write name='documentHistory' property='createdDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="createdDate_cal" style="display: none"></div></td>
		</tr>
		<logic:messagesPresent property="documentHistory.createdDate">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.createdDate"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.createdBy.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="createdBy"/>
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.createdBy">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.createdBy"/></font>
				</td>
			</tr>
		</logic:messagesPresent>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.lastUpdatedDate.key"/></b></td>
			<td width="10">:</td>			
			<td><input type="text" name="lastUpdatedDate" onclick="calendarToogle('lastUpdatedDate', 'lastUpdatedDate_cal', null);"  onKeyDown="drawCalendar('lastUpdatedDate', 'lastUpdatedDate_cal', null);" value="<bean:write name='documentHistory' property='lastUpdatedDate' format='dd/MM/yyyy' />"/></td>
		</tr>
		<tr>
			<td colspan="2" />
			<td><div id="lastUpdatedDate_cal" style="display: none"></div></td>
		</tr>

 <% */ %>  <% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.lastUpdatedBy.key"/></b></td>
			<td width="10">:</td>			
			<td>
				<html:text name="documentHistory" property="lastUpdatedBy"/>
			</td>
		</tr>

 <% */ %> 
		<tr>
			<td width="150"><b><bean:message key="documentHistory.securityLevel.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="documentHistory" property="securityLevel" style="width:135"  value="${documentHistory.securityLevel.id}">
					<option value=""></option>
					<html:options collection="securityLevelList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.owner.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="documentHistory" property="owner" style="width:135"  value="${documentHistory.owner.id}">
					<option value=""></option>
					<html:options collection="ownerList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.owner">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.owner"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% /* %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.status.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="documentHistory" property="status" style="width:135"  value="${documentHistory.status.id}">
					<option value=""></option>
					<html:options collection="statusList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.status">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.status"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
<% */ %> 		<tr>
			<td width="150"><b><bean:message key="documentHistory.parentFolder.key"/> <font color="#FF0000">*</font></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="documentHistory" property="parentFolder" style="width:135"  value="${documentHistory.parentFolder.id}">
					<option value=""></option>
					<html:options collection="parentFolderList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<logic:messagesPresent property="documentHistory.parentFolder">
			<tr>
				<td colspan="3">
					<font color="red"><html:errors property="documentHistory.parentFolder"/></font>
				</td>
			</tr>
		</logic:messagesPresent>
		<tr>
			<td width="150"><b><bean:message key="documentHistory.parentDocument.key"/></b></td>
			<td width="10">:</td>
			<td>				
				<html-el:select  name="documentHistory" property="parentDocument" style="width:135"  value="${documentHistory.parentDocument.id}">
					<option value=""></option>
					<html:options collection="parentDocumentList" property="id" labelProperty="name"/>
				</html-el:select>															
			</td>
		</tr>
		<tr>
			<td width="150"></td>
			<td width="10"></td>
			<td >
				<input type="submit" value="<bean:message key="button.save"/>" onclick="this.form.action.value='create_confirm'" />
			    &nbsp;
			    <html:reset><bean:message key="button.reset"/></html:reset>
				&nbsp;
				<input type="button" value="<bean:message key="button.back"/>" onclick="this.form.action.value='list';this.form.submit()" />
			</td>
		</tr>

	</table>	
<!-- set one to many start -->
<!-- end one to many start -->	
	<%@ include file="../common/footer.jsp" %>
</form>
</BODY>
</html:html>

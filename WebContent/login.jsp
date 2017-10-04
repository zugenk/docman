<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.app.shared.*" %>


<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<% 
	String currentTemplate= (String) session.getAttribute("currentTemplate");
	if (currentTemplate==null) {
		currentTemplate=ApplicationConstant.defaultTemplate;
		session.setAttribute("currentTemplate",currentTemplate);
	}
%>
<LINK href="template/<%=currentTemplate%>/css/admin_login.css" type=text/css rel=stylesheet>

<SCRIPT language=javascript type=text/javascript>
	function setFocus() {
		document.loginForm.username.select();
		document.loginForm.username.focus();
	}
</SCRIPT>

<!--
/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
-->

<BODY onload=setFocus();>
<form name="loginForm" method="post" action="login.do">
<form name="loginForm" method="post" action="login.do">
	<DIV id=wrapper>
		<DIV id=ctr align=center>
			<DIV class=login>
				<DIV class=login-form>
					<DIV class=form-block>
						<DIV class=inputlabel>Username</DIV>
						<DIV><INPUT class=inputbox size=15 name=username></DIV>
						<logic:messagesPresent property="login.name">
							<DIV class=inputlabel><font color="red"><html:errors property="login.name"/></font><br><br></DIV>
						</logic:messagesPresent>
						<DIV class=inputlabel>Password</DIV>
						<DIV><INPUT class=inputbox type=password size=15 name=userpass></DIV>
						<logic:messagesPresent property="login.password">
							<DIV class=inputlabel><font color="red"><html:errors property="login.password"/></font><br><br></DIV>
						</logic:messagesPresent>
						<logic:messagesPresent property="login.failed">
							<DIV class=inputlabel><font color="red"><html:errors property="login.failed"/></font><br><br></DIV>
						</logic:messagesPresent>

						<DIV align=left><INPUT class=button type=submit value=Login name=submit></DIV>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
	</DIV>
	<DIV class=login-text>
		<DIV class=ctr><IMG height=64 alt=security src="template/<%=currentTemplate%>/images/security.png" width=64></DIV>
		<P>Welcome to Document Manager !</P>
		<P>Use a valid username and password to gain access to the system console.</P>
	</DIV>
	<NOSCRIPT>!Warning! Javascript must be enabled for proper operation of the Administrator </NOSCRIPT>
</form>
</BODY>
</HTML>

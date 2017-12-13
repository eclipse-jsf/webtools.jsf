<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">

<HTML>

<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
<TITLE>CarStore</TITLE>
<link rel="stylesheet" type="text/css"
	href='<%= request.getContextPath() + "/stylesheet.css" %>'>
</HEAD>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<BODY BGCOLOR="white">

<f:view>

	<h:form>

		<h:commandButton value="Button 1" action="action1" />

		<h:commandButton action="action2" value="Button 2" />

		<h:commandLink value="Link 1" action="action3" />

	</h:form>

	<jsp:include page="bottomMatter.jsp" />

</f:view>
</BODY>

</HTML>

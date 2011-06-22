<%/* author: Tsai-Yeh Tung */%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<html>
<head>
<title>Resume Robot</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
</head>
<body>
<%
UserService userService = UserServiceFactory.getUserService();
if (!userService.isUserLoggedIn()) {
%>
	Hello! Please <a href="<%=userService.createLoginURL("/RSS_Parser.html")%>">login</a> to work with the Resume Thing. 
<%
} else {
	//response.sendRedirect("/RSS_Parser.html");
}
%>
</body>
</html>
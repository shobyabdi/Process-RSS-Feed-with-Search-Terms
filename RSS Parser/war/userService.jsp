<%/* author: Tsai-Yeh Tung */%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%
UserService userService = UserServiceFactory.getUserService();
String userName = "";
if (!userService.isUserLoggedIn())
	response.sendRedirect("/index.jsp");
else {
	userName = userService.getCurrentUser().getNickname();
}
%>
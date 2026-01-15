<%--
  Created by IntelliJ IDEA.
  User: HUNGNX-PC
  Date: 15-Jan-26
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Simple Dictionary</title>
</head>
<body>
<%!
Map<String, String> dic = new HashMap<>();
%>

<%
dic.put("hello","Xin chao");
dic.put("how","The nao");
dic.put("book","Quyen vo");
dic.put("computer","May tinh");

String search = request.getParameter("search");
String result = dic.get(search);

if (result != null) {
    out.println("Word: " + search);
    out.println("<br>");
    out.println("Result: " + result);
    } else {
    out.println("Not found");
}
%>
</body>
</html>

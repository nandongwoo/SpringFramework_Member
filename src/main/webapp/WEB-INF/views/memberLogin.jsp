<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-09-05
  Time: 오후 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>
<body>
<%@include file="component/header.jsp"%>
<%@include file="component/nav.jsp"%>

<form action="/login" method="post">
    email : <input type="text" name="memberEmail"> <br>
    password : <input type="text" name="memberPassword"> <br>
    <input type="submit" value="전송">
</form>

<%@include file="component/footer.jsp"%>
</body>
</html>

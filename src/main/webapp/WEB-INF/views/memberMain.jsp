<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/main.css">

</head>
<body>
<%@include file="component/header.jsp"%>
<%@include file="component/nav.jsp"%>
<h2>마이페이지</h2>
로그인이메일: ${sessionScope.loginEmail} <br>
<br><br><br><br>

<button class="btn btn-primary" onclick="update_fn()">회원정보수정</button>  <span><a href="/list">회원목록</a></span> <br>
<br><br><br><br><br><br><br>

<%-- 로그인 계정이 admin일 경우에만 회원목록 링크가 보임 --%>
<c:if test="${sessionScope.loginEmail == 'admin'}">

</c:if>


<%@include file="component/footer.jsp"%>

</body>
<script>
    const update_fn = () => {
        location.href = "/update";
    }
</script>
</html>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-09-05
  Time: 오후 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@include file="component/header.jsp" %>
<%@include file="component/nav.jsp" %>
<div class="mt-5"></div>
<tr>
    <td>이메일</td>
    <td>이름</td>
    <td>생일</td>
    <td>전화번호</td>
</tr>
<br>


<c:forEach items="${memberList}" var="member">
    <tr>
        <td>${member.memberEmail}</td>
        <td>${member.memberName}</td>
        <td>${member.memberBirth}</td>
        <td>${member.memberMobile}</td>
        <td>
            <button class="btn btn-primary" onclick="detail_fn('${member.id}')">상세조회</button>
        </td>
        <td>
            <button class="btn btn-danger" onclick="delete_fn('${member.id}')">삭제</button>
        </td>
    </tr>
    <br>

</c:forEach>
<br>    <br>

<div>
    <tr>
        <td>아이디</td>
        <td>이름</td>
        <td>이메일</td>
        <td>생년월일</td>
        <td>번호</td>
    </tr>

</div>

<div id=detail>


</div>
<br>    <br>    <br>    <br>
<%@include file="component/footer.jsp" %>
</body>
<script>

    const detail_fn = (id) => {
        const detail = document.getElementById("detail");
        $.ajax({
            type: "post",
            url: "/detail",
            data: {memberId: id},
            success: function (res) {
                let result = "<table class = 'table table-dark'>";
                result += "<tr>";
                result += "<td>" + res.id + "<td>" + "<br>";
                result += "<td>" + res.memberName + "<td>" + "<br>";
                result += "<td>" + res.memberEmail + "<td>" + "<br>";
                result += "<td>" + res.memberBirth + "<td>" + "<br>";
                result += "<td>" + res.memberMobile + "<td>" + "<br>";
                result += "</tr>"
                result += "<table>";
                detail.innerHTML = result;

            },
            error: function () {


            }
        })
    }

    const delete_fn = (id) => {
        location.href = "/delete?id=" + id;
    }


</script>
</html>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-09-05
  Time: 오후 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 페이지</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@include file="component/header.jsp"%>
<%@include file="component/nav.jsp"%>
<form action="/save" method="post">
    email : <input id = "email" type="text" name="memberEmail" placeholder="이메일" onkeyup="email_check()"> <br>
    <p id = check_result></p>
    password : <input type="text" name="memberPassword" placeholder="비밀번호"> <br>
    name : <input type="text" name="memberName" placeholder="이름"> <br>
    birth : <input type="text" name="memberBirth" placeholder="생년월일(YYYY-MM-dd)"> <br>
    mobile : <input type="text" name="memberMobile" placeholder="전화번호"> <br>
    <input type="submit" value="전송">
</form>

<%@include file="component/footer.jsp"%>
</body>
<script>
    const email_check = () =>{
        const email = document.getElementById("email").value;
        const check = document.getElementById("check_result");
        $.ajax({
            type : "get",
            url : "/email_check",
            data : {memberEmail : email},
            success : function (res){
                if(res=="yes"){
                    check.style.color = "green";
                    check.innerHTML = "사용 가능한 이메일입니다.";
                }else {
                    check.style.color = "red";
                    check.innerHTML = "사용 중인 이메일입니다.";
                }
            },
            error : function (){

            }
        })
    }
</script>
</html>

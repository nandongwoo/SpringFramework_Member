<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-09-07
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<button onclick="ajax06()">함수호출</button>
</body>
<script>
    const ajax06 = () => {
        const email = "member@member.com";
        const password =  "1234";
        const name = "이름1"ㅣ;
        $.ajax({
            type: "post",
            url: "/ajax06",
            data : {
                memberEmail : email,
                memberPassword : password,
                memberName : name
            },
            success: function (res) {
                console.log("성공", res);
            },
            error: function () {
                console.log("실패")
            }

        });
    }
</script>
</html>

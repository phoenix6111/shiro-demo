<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user login page</title>
</head>
<body>
    <h1>用户登陆</h1>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="username"><br>
        <input type="text" name="password" placeholder="password"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>

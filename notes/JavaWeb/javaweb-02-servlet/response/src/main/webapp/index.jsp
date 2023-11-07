<html>
<body>
<h2>Hello World!</h2>
<%--
${pageContext.request.contextPath} 代表当前的项目
--%>
<form action="${pageContext.request.contextPath}/login" method="get" >
    Username: <input type="text" name="username"><br>

    Password: <input type="password" name="password"><br>
    <input type="submit">
</form>
</body>
</html>

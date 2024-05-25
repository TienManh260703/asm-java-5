<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center  align-items-center" style="margin-top: 15%">
        <div class="col-4">
            <h1 class="text-center"> Đăng nhập</h1>
            <div class="d-flex justify-content-center ">
                <form:form method="post" action="/shop-app/admin/login" modelAttribute="account">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <form:input path="username" id="username" class="form-control"></form:input>
                        <form:errors path="username" cssClass="text-danger"></form:errors>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <form:input path="password" id="password" class="form-control" type="password"></form:input>
                        <form:errors path="password" cssClass="text-danger"></form:errors>
                    </div>
                    <div class="text-danger">${message}</div>
                    <button type="submit" class="mt-4 btn btn-primary">Login</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

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
    <title>Products Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-body-secondary ">
<nav class="mb-5 navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="container bg-white" style="padding: 10px; border-radius: 10px">
<h1>${url}</h1>
    <form:form action="${url}" modelAttribute="product" method="post">
        <div style="display: flex; justify-content: center" class="row mt-5">
            <div class="col-7">
                <h6> Id :</h6> <form:input path="id" readonly="true" cssClass="form-control "></form:input> <br>
                <form:errors path="id"></form:errors>
            </div>
            <div class="col-7">
                <h6> Code :</h6> <form:input path="code" readonly="true" cssClass="form-control "></form:input> <br>
                <form:errors path="code"></form:errors>
            </div>
            <div class="col-7">
                <h6> Name :</h6> <form:input path="name"  cssClass="form-control "></form:input> <br>
                <form:errors path="name"></form:errors>
            </div>
            <div class="col-7 mb-5">
                <button class=" btn btn-primary">Save</button>
            </div>
        </div>
    </form:form>


    <%--    Table --%>
    <table class="table table-striped ">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Id</th>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody >
        <c:forEach var="p" items="${products}" varStatus="i">
            <tr >
                <th scope="row">${i.index + 1}</th>
                <td>${p.id}</td>
                <td>${p.code}</td>
                <td>${p.name}</td>
                <td class="${p.status ? "text-danger" : "text-success" }">${p.status ? "Ngưng bán" : "Đang kinh doanh"}</td>
                <td>
                    <a href="/shop-app/products/view-update?id=${p.id}" class="btn btn-warning">Edit</a>
                    <a class="btn btn-info">Info</a>
                    <a  href="/shop-app/products/update-status?id=${p.id}" class="btn btn-outline-danger " ${p.status ? "hidden" : ""} >Remove</a>
                    <a  href="/shop-app/products/update-status?id=${p.id}" class="btn  btn-outline-warning" ${!p.status ? "hidden" : ""} >Update status</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
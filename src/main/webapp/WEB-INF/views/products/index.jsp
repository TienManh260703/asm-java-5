<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</head>
<body class="bg-body-secondary ">
<nav class=" navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">----------</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Bán hàng</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Quản lý
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/shop-app/staffs">Nhân viên</a></li>
                        <li><a class="dropdown-item" href="/shop-app/customers">Khách hàng</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/shop-app/products">Sản phẩm</a></li>
                        <li><a class="dropdown-item" href="/shop-app/colors">Màu sắc</a></li>
                        <li><a class="dropdown-item" href="/shop-app/sizes">Kích thước</a></li>

                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/shop-app/orders">Hóa đơn</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                </li>
            </ul>
            <form action="/shop-app/admin/logout" method="get" class="d-flex" role="search">
                <button class="btn btn-outline-success" type="submit">Đăng xuất</button>
            </form>
        </div>
    </div>
</nav>
<c:set var="currentPage" value="${products.number}"/>
<c:set var="pageSize" value="${products.size}"/>
<c:set var="currentId" value="${id}"/>

<div class="container bg-white" style=" border-radius: 10px;">
    <h1 class="text-center">Quản lý sản phẩm</h1>
    <form:form action="/shop-app/products/search" method="get">
        <div class="input-group ">
            <input type="text" name="id" class="form-control" placeholder="Tìm kiếm theo tên" aria-label="Recipient's username" aria-describedby="button-addon2">
            <button class="btn btn-outline-primary" >Tìm</button>
        </div>
    </form:form>
    <form:form action="${url}" modelAttribute="product" method="post">
        <div class="row mt-5">
            <div class="col-6 mb-3">
                <div class="form-group">
                    <h6>Id:</h6>
                    <form:input path="id" readonly="true" cssClass="form-control"></form:input>
                    <form:errors path="id"></form:errors>
                </div>
            </div>
            <div class="col-6 mb-3">
                <div class="form-group">
                    <h6>Code:</h6>
                    <form:input path="code" readonly="true" cssClass="form-control"></form:input>
                    <form:errors path="code"></form:errors>
                </div>
            </div>
            <div class="col-6 mb-3">
                <div class="form-group">
                    <h6>Name:</h6>
                    <form:input path="name" cssClass="form-control" readonly="${isDetail}"></form:input>
                    <form:errors path="name" cssStyle="color: red"></form:errors>
                </div>
            </div>
            <div class="col-6  text-center pb-2">

                <button class="btn btn-primary mt-4" ${isDetail? "hidden" : ""}>${isEdit ? "Update" : "Add"}</button>
            </div>
        </div>
    </form:form>

    <!-- Table -->
    <table class="table table-striped ">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="o" items="${products.content}" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${o.id}</td>
                <td>${o.code}</td>
                <td>${o.name}</td>
                <td class="${o.status ? "text-danger" : "text-success" }">${o.status ? "Ngưng bán" : "Đang kinh doanh"}</td>
                <td>
                    <a href="/shop-app/products/view-update?id=${o.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-warning">Edit</a>
                    <a href="/shop-app/products/products-detail?productIdParam=${o.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-info">Info</a>
                    <a href="/shop-app/products/update-status?id=${o.id}"
                       class="btn ${o.status ? 'btn-outline-success' : 'btn-outline-danger'} ">${o.status ? "Update status":"Remove"}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${products.number == 0 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${currentPage - 1}&size=${pageSize}&id=${currentId}">Previous</a>
            </li>
            <c:forEach var="i" begin="0" end="${products.totalPages - 1}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}&size=${pageSize}&id=${currentId}">${i + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item ${products.number == products.totalPages - 1 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${currentPage + 1}&size=${pageSize}&id=${currentId}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>

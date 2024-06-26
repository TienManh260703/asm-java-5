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
    <title>Product Detail Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</head>
<body class="bg-body-secondary ">
<nav class=" navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Shop app</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/shop-app/sells">Bán hàng</a>
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
<c:set var="currentPage" value="${productsDetail.number}"/>
<c:set var="pageSize" value="${productsDetail.size}"/>
<c:set var="currentId" value="${id}"/>
<c:set var="productIdParam" value="${productId}"/>
<div class="container bg-white" style="padding: 10px; border-radius: 10px;">
    <h1 class="text-center">Quản lý chi tiết sản phẩm</h1>
    <form:form action="/shop-app/products/products-detail/search" method="get">

        <div class="input-group mb-3 ">
            <input type="text" name="min" class="form-control" placeholder="Giá min">
            <input type="text" name="max" class="form-control" placeholder="Giá max">
            <button class="btn btn-outline-primary">Lọc</button>
        </div>
    </form:form>
<a href="/shop-app/products" class="btn btn-primary">Quay lại</a>

    <%--    Table --%>
    <table class="table table-striped ">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <%--            <th scope="col">Quantity</th>--%>
            <%--            <th scope="col">Price</th>--%>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="o" items="${productsDetail.content}" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${o.id}</td>
                <td>${o.code}</td>
                <td>${o.name}</td>
                    <%--                <td>${pd.sizeName}</td>--%>
                    <%--                <td>${pd.colorName}</td>--%>
                <td>${o.quantity} </td>
                <td>${o.price} VND</td>
                <td class="${o.deleted ? "text-danger" : "text-success" }">${o.deleted ? "Tạm ngừng" : "Đang kinh doanh"}</td>
                <td>
                    <a href="/shop-app/products/products-detail/view-update?id=${o.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-warning">Edit</a>
                    <a href="/shop-app/products/products-detail/detail?id=${o.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-info">Info</a>
                    <a href="/shop-app/products/products-detail/update-status?id=${o.id}"
                       class="btn  ${o.deleted ? 'btn-outline-success' : 'btn-outline-danger'} "> ${o.deleted ? "Update status":"Remove"  } </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${productsDetail.number == 0 ? 'disabled' : ''}">
                <a class="page-link" href="?productIdParam=${productId}&page=${currentPage - 1}&size=${pageSize}&id=${currentId}">Previous</a>
            </li>
            <c:forEach var="i" begin="0" end="${productsDetail.totalPages <=0 ? 1 :productsDetail.totalPages - 1}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?productIdParam=${productId}&page=${i}&size=${pageSize}&id=${currentId}">${i + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item ${productsDetail.number == productsDetail.totalPages - 1 ? 'disabled' : ''}">
                <a class="page-link" href="?productIdParam=${productId}&page=${currentPage + 1}&size=${pageSize}&id=${currentId}">Next</a>
            </li>
        </ul>
    </nav>
<%--    --%>
    <form:form action="${url}" modelAttribute="productDetail" method="post">
        <div style="display: flex; justify-content: center" class="row mt-5">
            <div class="col-6">
                <h6> Code :</h6> <form:input path="code" readonly="true" cssClass="form-control "></form:input> <br>
                <form:errors path="code"></form:errors>
            </div>
            <div class="col-6">
                <h6> Sản phẩm :</h6>
                <select name="productId" class="form-control" ${isDetail ? "disabled" : ""}>
                    <c:forEach items="${products}" var="p">
                        <option value="${p.id}" ${p.id == pId ? "selected" :""} >${p.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <h6> Màu sắc :</h6>
                <select name="colorId" class="form-control" ${isDetail ? "disabled" : ""}>
                    <c:forEach items="${colors}" var="s">
                        <option value="${s.id}" ${s.id == cId ? "selected" :""} >${s.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-6">
                <h6>Kích thước :</h6>
                <select name="sizeId" class="form-control" ${isDetail ? "disabled" : ""} >
                    <c:forEach items="${sizes}" var="s">
                        <option value="${s.id}" ${s.id == sId ? "selected" :""} >${s.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <h6> Số lượng :</h6>
                <form:input path="quantity" cssClass="form-control " readonly="${isDetail}"></form:input> <br>
                <form:errors path="quantity" cssClass="text-danger"></form:errors>
            </div>
            <div class="col-6">
                <h6> Đơn giá :</h6>
                <form:input path="price" cssClass="form-control " readonly="${isDetail}"></form:input> <br>
                <form:errors path="price" cssClass="text-danger"></form:errors>
            </div>
            <div class="col-1 mt-2 mb-5">
                <button class=" btn btn-primary"  ${isDetail? "hidden" : ""}> ${isEdit ? "Update" : "Add"}</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
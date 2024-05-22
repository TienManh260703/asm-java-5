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
</head>
<body class="bg-body-secondary ">

<c:set var="currentPage" value="${productsDetail.number}"/>
<c:set var="pageSize" value="${productsDetail.size}"/>
<c:set var="currentId" value="${id}"/>
<c:set var="productIdParam" value="${productId}"/>
<div class="container bg-white" style="padding: 10px; border-radius: 10px;">
    <h1 class="text-center">Quản lý chi tiết sản phẩm</h1>
    <h1 class="text-center">${url}</h1>
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
        <c:forEach var="st" items="${productsDetail.content}" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${st.id}</td>
                <td>${st.code}</td>
                <td>${st.name}</td>
                    <%--                <td>${pd.sizeName}</td>--%>
                    <%--                <td>${pd.colorName}</td>--%>
                <td>${st.quantity} </td>
                <td>${st.price} VND</td>
                <td class="${st.deleted ? "text-danger" : "text-success" }">${st.deleted ? "Tạm ngừng" : "Đang kinh doanh"}</td>
                <td>
                    <a href="/shop-app/products/products-detail/view-update?id=${st.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-warning">Edit</a>
                    <a href="/shop-app/products/products-detail/detail?id=${st.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-info">Info</a>
                    <a href="/shop-app/products/products-detail/update-status?id=${st.id}"
                       class="btn  ${st.deleted ? 'btn-outline-success' : 'btn-outline-danger'} "> ${st.deleted ? "Update status":"Remove"  } </a>
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
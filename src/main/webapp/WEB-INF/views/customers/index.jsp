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
    <title>Customer Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-body-secondary ">

<c:set var="currentPage" value="${customers.number}"/>
<c:set var="pageSize" value="${customers.size}"/>
<c:set var="currentId" value="${id}"/>

<div class=" bg-white" style="padding: 10px; border-radius: 10px;">
    <h1 class="text-center">Quản lý khách hàng</h1>
    <h1 class="text-center">${url}</h1>
    <div style="display: flex;justify-content: center;" class="mt-5">
        <form:form action="/shop-app/customers/search" method="get">
            <div class="input-group mb-3 ">
                <input type="text" name="id" class="form-control" placeholder="Tìm kiếm theo tên hoặc sđt"
                       aria-label="Recipient's username" aria-describedby="button-addon2">
                <button class="btn btn-outline-primary">Tìm</button>
            </div>
        </form:form>
    </div>
    <div style="display: flex;justify-content: center" class="row pe-5 ps-5">
        <div class="col-5">
            <h3 class="text-center">Thông tin khách hàng</h3>
            <form:form action="${url}" modelAttribute="customer" method="post">
                <div style="display: flex; justify-content: center" class="row mt-3">
                    <div class="col-6">
                        <h6> Id :</h6> <form:input path="id" readonly="true" cssClass="form-control "></form:input> <br>
                        <form:errors path="id"></form:errors>
                    </div>
                    <div class="col-6">
                        <h6> Code :</h6> <form:input path="code" readonly="true" cssClass="form-control "></form:input>
                        <br>
                        <form:errors path="code"></form:errors>
                    </div>
                    <div class="col-6">
                        <h6> Name :</h6> <form:input path="name" cssClass="form-control "
                                                     readonly="${isDetail}"></form:input>
                        <br>
                        <form:errors path="name" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="col-6">
                        <h6> Phone number :</h6>
                        <form:input path="phoneNumber" cssClass="form-control " readonly="${isDetail}"></form:input>
                        <br>
                        <p class="text-dander">${message}</p>
                        <form:errors path="phoneNumber" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="col-7 mt-2 mb-5">
                        <button class=" btn btn-primary"  ${isDetail? "hidden" : ""}> ${isEdit ? "Update" : "Add"}</button>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-7">
            <h3 class="text-center">Danh sách hóa đơn</h3>
            <table class="table mt-2">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Staff name</th>
                    <th scope="col"> Customer name</th>
                    <th scope="col">Create at</th>
                    <th scope="col">Date of Payment</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                    <td>A</td>
                    <td>A</td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>

    <%--    Table --%>
    <div class="container">
        <table class="table table-striped ">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">ID</th>
                <th scope="col">Code</th>
                <th scope="col">Name</th>
                <th scope="col">Phone Number</th>
                <th scope="col">Status</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${customers.content}" varStatus="i">
                <tr>
                    <th scope="row">${i.index + 1}</th>
                    <td>${o.id}</td>
                    <td>${o.code}</td>
                    <td>${o.name}</td>
                    <td>${o.phoneNumber}</td>

                    <td class="${o.status ? "text-danger" : "text-success" }">${o.status ? "Xóa" : "Đang quản lý"}</td>
                    <td>
                        <a href="/shop-app/customers/view-update?id=${o.id}&page=${currentPage}&size=${currentSize}"
                           class="btn btn-warning">Edit</a>
                        <a href="/shop-app/customers/detail?id=${o.id}&page=${currentPage}&size=${currentSize}"
                           class="btn btn-info">Info</a>
                        <a href="/shop-app/customers/update-status?id=${o.id}"
                           class="btn  ${o.status ? 'btn-outline-success' : 'btn-outline-danger'} "> ${o.status ? "Update status":"Remove"  }</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item ${customers.number == 0 ? 'disabled' : ''}">
                    <a class="page-link" href="?page=${currentPage - 1}&size=${pageSize}&id=${currentId}">Previous</a>
                </li>
                <c:forEach var="i" begin="0" end="${customers.totalPages - 1}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&size=${pageSize}&id=${currentId}">${i + 1}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${customers.number == customers.totalPages - 1 ? 'disabled' : ''}">
                    <a class="page-link" href="?page=${currentPage + 1}&size=${pageSize}&id=${currentId}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
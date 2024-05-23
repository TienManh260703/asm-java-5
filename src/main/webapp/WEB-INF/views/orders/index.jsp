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
    <title>Order Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-body-secondary ">

<c:set var="currentPage" value="${orders.number}"/>
<c:set var="pageSize" value="${orders.size}"/>
<c:set var="currentId" value="${id}"/>

<div class=" bg-white" style="padding: 10px; border-radius: 10px;">
    <h1 class="text-center">Quản lý hóa đơn</h1>
    <div style="display: flex;justify-content: center;" class="mt-2">
        <form:form action="/shop-app/orders/search" method="get">
            <div class="input-group mb-3 ">
                <input type="text" name="id" class="form-control" placeholder="Tìm kiếm theo tên hoặc sđt"
                       aria-label="Recipient's username" aria-describedby="button-addon2">
                <button class="btn btn-outline-primary">Tìm</button>
            </div>
        </form:form>
    </div>
    <div style="display: flex;justify-content: center" class="row pe-5 ps-5">
        <div class="col-4">
            <h3 class="text-center">Thông tin đơn hàng</h3>
            <form:form action="${url}" modelAttribute="order" method="post">
                <div style="display: flex; justify-content: center" class="row mt-3">
                    <div class="col-6">
                        <h6> staff :</h6>
                        <form:input path="staffUserName" cssClass="form-control "></form:input> <br>
                        <form:errors path="staffUserName"></form:errors>

                    </div>
                    <div class="col-6">
                        <h6> phoneNumber :</h6>
                        <form:input path="phoneNumber" cssClass="form-control "></form:input> <br>
                        <form:errors path="phoneNumber"></form:errors>
                            <%--                        <select>--%>
                            <%--                            <c:forEach var="s" items="${staffs}">--%>
                            <%--                                <option value="${s.id}">${s.userName}</option>--%>
                            <%--                            </c:forEach>--%>
                            <%--                        </select>--%>
                    </div>
                    <div class="col-7 mt-2 mb-5">
                        <h6></h6>
                        <button class=" btn btn-primary"  ${isDetail? "hidden" : ""}> ${isEdit ? "Update" : "Add"}</button>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-8">
            <h3 class="text-center">Danh sách hóa đơn</h3>
            <table class="table table-striped ">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">ID</th>
                    <th scope="col">Staff</th>
                    <th scope="col">Customer</th>
                    <th scope="col">Created at</th>
                    <th scope="col">dateOfPayment</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${orders.content}" varStatus="i">
                    <tr onclick="return location.href ='/shop-app/staffs'" >
                        <th scope="row">${i.index + 1}</th>
                        <td>${o.id}</td>
                        <td>${o.staff.userName}</td>
                        <td>${o.customer.name}</td>
                        <td>${o.createdAt}</td>
                        <td>${o.dateOfPayment}</td>
                        <td class="${o.status == 0 ? 'text-warning' :( o.status == 1 ? 'text-success':'text-danger'  ) }">
                                ${o.status == 0 ? 'Chưa thanh toán' :( o.status == 2 ? 'Đã hủy' : 'Đã thanh toán') }
                        </td>
                        <td>
                            <a href="/shop-app/orders/update-status?id=${o.id}&status=${1}"  ${o.status == 1 || o.status == 2   ? "hidden": ""}
                               class="btn btn-outline-success">Pay</a>
                            <a href="/shop-app/orders/update-status?id=${o.id}&status=${2}" ${o.status == 1 || o.status == 2   ? "hidden": ""}
                               class="btn btn-outline-danger">Cancel</a>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${orders.number == 0 ? 'disabled' : ''}">
                        <a class="page-link"
                           href="?page=${currentPage - 1}&size=${pageSize}&id=${currentId}">Previous</a>
                    </li>
                    <c:forEach var="i" begin="0" end="${orders.totalPages - 1}">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${i}&size=${pageSize}&id=${currentId}">${i + 1}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${orders.number == orders.totalPages - 1 ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage + 1}&size=${pageSize}&id=${currentId}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <%--    Table --%>
    <%--    <div class="container">--%>
    <%--        <table class="table table-striped ">--%>
    <%--            <thead>--%>
    <%--            <tr>--%>
    <%--                <th scope="col">STT</th>--%>
    <%--                <th scope="col">ID</th>--%>
    <%--                <th scope="col">Code</th>--%>
    <%--                <th scope="col">Name</th>--%>
    <%--                <th scope="col">Phone Number</th>--%>
    <%--                <th scope="col">Status</th>--%>
    <%--                <th scope="col">Action</th>--%>
    <%--            </tr>--%>
    <%--            </thead>--%>
    <%--            <tbody>--%>
    <%--            <c:forEach var="ct" items="${customers.content}" varStatus="i">--%>
    <%--                <tr>--%>
    <%--                    <th scope="row">${i.index + 1}</th>--%>
    <%--                    <td>${ct.id}</td>--%>
    <%--                    <td>${ct.code}</td>--%>
    <%--                    <td>${ct.name}</td>--%>
    <%--                    <td>${ct.phoneNumber}</td>--%>

    <%--                    <td class="${ct.status ? "text-danger" : "text-success" }">${ct.status ? "Xóa" : "Đang quản lý"}</td>--%>
    <%--                    <td>--%>
    <%--                        <a href="/shop-app/customers/view-update?id=${ct.id}&page=${currentPage}&size=${currentSize}"--%>
    <%--                           class="btn btn-warning">Edit</a>--%>
    <%--                        <a href="/shop-app/customers/detail?id=${ct.id}&page=${currentPage}&size=${currentSize}"--%>
    <%--                           class="btn btn-info">Info</a>--%>
    <%--                        <a href="/shop-app/customers/update-status?id=${ct.id}"--%>
    <%--                           class="btn  ${ct.status ? 'btn-outline-success' : 'btn-outline-danger'} "> ${ct.status ? "Update status":"Remove"  }</a>--%>
    <%--                    </td>--%>
    <%--                </tr>--%>
    <%--            </c:forEach>--%>
    <%--            </tbody>--%>
    <%--        </table>--%>

    <%--        <nav aria-label="Page navigation">--%>
    <%--            <ul class="pagination justify-content-center">--%>
    <%--                <li class="page-item ${customers.number == 0 ? 'disabled' : ''}">--%>
    <%--                    <a class="page-link" href="?page=${currentPage - 1}&size=${pageSize}&id=${currentId}">Previous</a>--%>
    <%--                </li>--%>
    <%--                <c:forEach var="i" begin="0" end="${customers.totalPages - 1}">--%>
    <%--                    <li class="page-item ${i == currentPage ? 'active' : ''}">--%>
    <%--                        <a class="page-link" href="?page=${i}&size=${pageSize}&id=${currentId}">${i + 1}</a>--%>
    <%--                    </li>--%>
    <%--                </c:forEach>--%>
    <%--                <li class="page-item ${customers.number == customers.totalPages - 1 ? 'disabled' : ''}">--%>
    <%--                    <a class="page-link" href="?page=${currentPage + 1}&size=${pageSize}&id=${currentId}">Next</a>--%>
    <%--                </li>--%>
    <%--            </ul>--%>
    <%--        </nav>--%>
    <%--    </div>--%>
</div>
</body>
</html>
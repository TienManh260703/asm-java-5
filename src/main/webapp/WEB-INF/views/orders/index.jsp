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
<c:set var="currentPage" value="${orders.number}"/>
<c:set var="pageSize" value="${orders.size}"/>
<c:set var="currentId" value="${id}"/>



<div class=" bg-white" style="padding: 10px; border-radius: 10px;">
    <h1 class="text-center">Quản lý hóa đơn</h1>
    <div style="display: flex;justify-content: center;" class="mt-2">
<%--        <form:form action="/shop-app/orders/search" method="get">--%>
<%--            <div class="input-group mb-3 ">--%>
<%--                <input type="text" name="id" class="form-control" placeholder="Tìm kiếm theo tên hoặc sđt"--%>
<%--                       aria-label="Recipient's username" aria-describedby="button-addon2">--%>
<%--                <button class="btn btn-outline-primary">Tìm</button>--%>
<%--            </div>--%>
<%--        </form:form>--%>
    </div>
    <div style="display: flex;justify-content: center" class="row pe-5 ps-5">
        <div class="col-2">
            <h3 class="text-center">Thông tin đơn hàng</h3>
            <form:form action="${url}" modelAttribute="order" method="post">
            <form:input path="moneyReceived" hidden="true"  class="form-control" value="1"  />
                <form:input path="staffUserName" hidden="true"  value="aaaaa"  cssClass="form-control "></form:input>
            <div  class="row mt-3">
                    <div class="col-12">
                        <h6> phoneNumber :</h6>
                        <form:input path="phoneNumber" cssClass="form-control "></form:input> <br>
                        <form:errors path="phoneNumber"></form:errors>
                        <p class="text-danger">${error}</p>
                    </div>
                    <div class="col-7 mt-2 mb-5">
                        <h6></h6>
                        <button class=" btn btn-primary"  ${isDetail? "hidden" : ""}> ${isEdit ? "Update" : "Add"}</button>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-10">
            <h3 class="text-center">Danh sách hóa đơn</h3>
            <table class="table table-striped ">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">ID</th>
                    <th scope="col">Người tạo</th>
                    <th scope="col">Khách hàng</th>
                    <th scope="col">Ngày thanh toán</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${orders.content}" varStatus="i">
                    <tr onclick="return location.href ='/shop-app/orders/orders-detail?id=${o.id}&page=${currentPage}'">
                        <th scope="row">${i.index + 1}</th>
                        <td>${o.id}</td>
                        <td>${o.staff.userName}</td>
                        <td>${o.customer.name}</td>
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
                           href="?page=${ saveCurrentPage = currentPage - 1}&size=${pageSize}&id=${currentId}" >Previous</a>
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
    <div class="container">
        <h1 class="text-center">Danh sách hóa đơn chi tiết</h1>
        <table class="table table-striped ">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Sản phẩm</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Thành tiền</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="odt" items="${ordersDetail}" varStatus="i">
                <tr>
                    <th scope="row">${i.index + 1}</th>
                    <td>${odt.productDetailName}</td>
                    <td>${odt.quantity}</td>
                    <td>${odt.price}</td>
                    <td>${odt.totalMoney}</td>

                    <td class="${odt.status ? "text-danger" : "text-success" }">${odt.status ? "Xóa" : "Đang quản lý"}</td>
                    <td>
                        <a class="btn btn-danger" ${odt.statusOrder ==1 ? "hidden": "" }  href="/shop-app/orders/order-detail/deleted?orderDetailId=${odt.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>
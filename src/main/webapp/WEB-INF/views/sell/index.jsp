<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Shop App</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Orders</a>
                </li>
            </ul>
            <form class="d-flex" action="/shop-app/admin/logout" method="get">
                <button class="btn btn-outline-success" type="submit">Logout</button>
            </form>
        </div>
    </div>
</nav>
<c:set var="currentPageO" value="${orders.number}"/>
<c:set var="pageSizeO" value="${orders.size}"/>
<c:set var="currentId" value="${_idOrder}"/>
<c:set var="_idOrder" value="${_idOrder}"/>

<c:set var="currentPageP" value="${productDetail.number}"/>
<c:set var="pageSizeP" value="${productDetail.size}"/>

<div class="ps-5 pe-5 mt-1">
    <div class="row">
        <div class="col-md-4">
            <h4>Danh sách hóa đơn</h4>
            <button class="btn btn-primary">Add</button>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NV</th>
                    <th>Khách hàng</th>
                    <th>Tt</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders.content}">
                    <tr onclick="location.href='/shop-app/sells/show-orders-detail?pageO=${orders.number}&sizeO=${orders.size}&idOrder=${order.id}'">
                        <td>${order.id}</td>

                        <td>${order.staffUserName}</td>
                        <td>${order.customerName}</td>
                        <td>${order.totalMoney}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${orders.totalPages > 0}">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li class="page-item ${orders.number == 0 ? 'disabled' : ''}">
                            <a class="page-link" href="?pageO=${orders.number - 1}&sizeO=${orders.size}">Previous</a>
                        </li>
                        <c:forEach var="i" begin="0" end="${orders.totalPages - 1}">
                            <li class="page-item ${i == orders.number ? 'active' : ''}">
                                <a class="page-link" href="?pageO=${i}&sizeO=${orders.size}">${i + 1}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item ${orders.number == orders.totalPages - 1 ? 'disabled' : ''}">
                            <a class="page-link" href="?pageO=${orders.number + 1}&sizeO=${orders.size}">Next</a>
                        </li>
                    </ul>
                </nav>
            </c:if>

        </div>

        <div class="col-md-6">
            <h4>Chi tiết hóa đơn</h4>
            <div style="overflow-y: auto; max-height: 200px;">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="odt" items="${ordersDetail}" varStatus="i">
                        <tr>
                            <form action="/shop-app/sells/update-orders-detail"
                                  method="get">
                                <input type="hidden" name="idOrder" value="${odt.orderId}">
                                <input type="hidden" name="orderDetailId" value="${odt.id}">
                                <input type="hidden" name="pageO" value="${orders.number}">
                                <input type="hidden" name="pageO" value="${orders.size}">

                                <td>${odt.productDetailName}</td>
                                <td>${odt.price}</td>
                                <td>
                                    <input type="number" id="quantityInput${i.index + 1}" style="width: 100px"
                                           name="quantity"
                                           value="${odt.quantity}"
                                           class="form-control"
                                           onchange="validateQuantity(this, 'quantityError${i.index + 1}')"
                                           max="${odt.maxQuantity}"
                                    >
                                    <span id="quantityError${i.index + 1}" style="color: red;"></span>
                                </td>
                                <td>${odt.totalMoney}</td>
                                <td>
                                    <button type="submit" class="btn btn-warning">Cập nhập</button>
                                    <a class="btn btn-danger"
                                       href="/shop-app/sells/delete-order-detail?orderDetailId=${odt.id}">Xóa</a>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Order Details Form -->
        <div class="col-md-2">
            <h4>Thông tin đơn hàng</h4>
            <form:form method="post" action="/shop-app/sells/pay" modelAttribute="infoOrder">
                <div class="mb-3">
                    <label class="form-label">ID</label>
                    <form:input path="id" class="form-control" readonly="true"/>
                    <form:input  path="phoneNumber" class="form-control "  hidden="true" readonly="true"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Khách hàng</label>
                    <form:input path="customerName"  class="form-control" readonly="true"/>
                    <form:input path="staffUserName" hidden="true"  class="form-control" readonly="true"/>
                </div>
                <div class="mb-3">
                    <label  class="form-label">Tổng tiền</label>
                    <form:input path="totalMoney"  class="form-control" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label  class="form-label">Tiền khách đưa</label>
                    <form:input path="moneyReceived"  class="form-control"  />
                    <form:errors path="moneyReceived" cssStyle="color: red"></form:errors>
                    <p class="text-danger">${message}</p>
                </div>
                <button type="submit" class="btn btn-primary">Thanh toán</button>
            </form:form>
        </div>
    </div>

    <div class="row ">
        <h2>Danh sach san pham</h2>
        <table class="table ">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Giá</th>
                <th scope="col">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach varStatus="i" var="pd" items="${productDetail.content}">
                <tr>

                    <th scope="row">${i.index +1}</th>
                    <td>${pd.name}</td>
                    <td>${pd.quantity}</td>
                    <td>${pd.price}</td>
                    <td>
                        <a href="/shop-app/sells/add-order-detail?pageO=${orders.number }&sizeO=${orders.size}&pageP=${productDetail.number}&sizeP=${productDetail.size}&productDetailId=${pd.id}"
                           class="btn btn-primary">Thêm</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item ${productDetail.number == 0 ? 'disabled' : ''}">
                    <a class="page-link"
                       href="?pageO=${orders.number }&sizeO=${orders.size}&pageP=${productDetail.number -1}&sizeP=${productDetail.size}&idOrder=${_idOrder}">Previous</a>
                </li>
                <c:forEach var="i" begin="0" end="${productDetail.totalPages - 1}">
                    <li class="page-item ${i == productDetail.number ? 'active' : ''}">
                        <a class="page-link"
                           href="?pageO=${currentPageO}&sizeO=${pageSizeO}&pageP=${i}&sizeP=${productDetail.size}&idOrder=${_idOrder}">${i + 1}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${productDetail.number == productDetail.totalPages - 1 ? 'disabled' : ''}">
                    <a class="page-link"
                       href="?pageO=${orders.number }&sizeO=${orders.size}&pageP=${productDetail.number + 1}&sizeP=${productDetail.size}&idOrder=${_idOrder}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<script>
    function validateQuantity(input, errorId) {
        var quantityError = document.getElementById(errorId);
        var quantity = input.value;
        var maxQuantity = parseInt(input.getAttribute("max")); // Lấy giá trị max từ thuộc tính max của input

        if (quantity < 1) {
            quantityError.textContent = "Số lượng phải lớn hơn hoặc bằng 1!";
            input.value = 1; // Nếu nhỏ hơn 1, giữ nguyên là 1
            return false; // Trả về false để không submit form
        } else if (quantity > maxQuantity) { // Kiểm tra số lượng nhập vào có vượt quá max không
            quantityError.textContent = "Sản phẩm chỉ có  " + maxQuantity + " !";
            input.value = maxQuantity; // Nếu vượt quá max, giữ nguyên giá trị là maxQuantity
            return false; // Trả về false để không submit form
        } else {
            quantityError.textContent = ""; // Xóa thông báo lỗi nếu hợp lệ
            return true; // Trả về true để submit form
        }
    }


</script>
</body>
</html>

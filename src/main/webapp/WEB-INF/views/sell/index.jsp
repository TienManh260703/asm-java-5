<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Point of Sale</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-9">
            <h4>Chi tiết hóa đơn</h4>
            <table class="table">
                <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                </tr>
                </thead>
                <tbody id="invoiceDetails">
                </tbody>
            </table>
        </div>
        <div class="col-md-3">
            <h4>Thông tin hóa đơn</h4>
            <form:form method="post" action="/sale/save" modelAttribute="invoice">
                <div class="mb-3">
                    <label for="staffName" class="form-label">Tên nhân viên</label>
                    <form:input path="staffName" id="staffName" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="customerName" class="form-label">Tên khách hàng</label>
                    <form:input path="customerName" id="customerName" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="totalAmount" class="form-label">Tổng tiền</label>
                    <form:input path="totalAmount" id="totalAmount" class="form-control" readonly="true"/>
                </div>
                <button type="submit" class="btn btn-primary">Lưu hóa đơn</button>
            </form:form>
        </div>
    </div>
    <div class="row mt-5">
        <div class="col-md-12">
            <h4>Sản phẩm</h4>
            <table class="table">
                <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thêm vào hóa đơn</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>
                            <button type="button" class="btn btn-success" onclick="addToInvoice('${product.name}', ${product.price})">Thêm</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

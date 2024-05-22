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
    <title>Size Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-body-secondary ">

<c:set var="currentPage" value="${sizes.number}"/>
<c:set var="pageSize" value="${sizes.size}"/>
<c:set var="currentId" value="${id}"/>

<div class="container bg-white" style="padding: 10px; border-radius: 10px;">
    <h1 class="text-center">Quản lý kích thước</h1>
    <h1 class="text-center">${url}</h1>

    <form:form action="/shop-app/sizes/search" method="get">
        <div class="input-group mb-3 ">
            <input type="text" name="name" class="form-control" placeholder="Tìm kiếm theo tên"
                   aria-label="Recipient's username" aria-describedby="button-addon2">
            <button class="btn btn-outline-primary">Tìm</button>
        </div>
    </form:form>
    <form:form action="${url}" modelAttribute="size" method="post">
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
                <h6> Name :</h6> <form:input path="name" cssClass="form-control " readonly="${isDetail}"></form:input>
                <br>
                <form:errors path="name" cssStyle="color: red"></form:errors>
                <p class="text-danger">${message}</p>
            </div>
            <div class="col-7 mt-2 mb-5">
                <button class=" btn btn-primary"  ${isDetail? "hidden" : ""}> ${isEdit ? "Update" : "Add"}</button>
            </div>
        </div>
    </form:form>

    <%--    Table --%>
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
        <c:forEach var="pd" items="${sizes.content}" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${pd.id}</td>
                <td>${pd.code}</td>
                <td>${pd.name}</td>
                <td class="${pd.status ? "text-danger" : "text-success" }">${pd.status ? "Không dùng" : "Đang kinh doanh"}</td>
                <td>
                    <a href="/shop-app/sizes/view-update?id=${pd.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-warning">Edit</a>
                    <a href="/shop-app/sizes/detail?id=${pd.id}&page=${currentPage}&size=${currentSize}"
                       class="btn btn-info">Info</a>
                    <a href="/shop-app/sizes/update-status?id=${pd.id}"
                       class="btn  ${pd.status ? 'btn-outline-success' : 'btn-outline-danger'} "> ${pd.status ? "Update status":"Remove"  }</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${sizes.number == 0 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${currentPage - 1}&size=${pageSize}&id=${currentId}">Previous</a>
            </li>
            <c:forEach var="i" begin="0" end="${sizes.totalPages - 1}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}&size=${pageSize}&id=${currentId}">${i + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item ${sizes.number == sizes.totalPages - 1 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${currentPage + 1}&size=${pageSize}&id=${currentId}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>
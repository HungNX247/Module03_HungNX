<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <title>Danh sách bác sĩ - Clinic Booking</title>


    <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />


    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
            crossorigin="anonymous"></script>

    <style>

        .table th, .table td {
            vertical-align: middle !important;
        }
    </style>
</head>

<body class="sb-nav-fixed">


<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div id="layoutSidenav">

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">

                <h1 class="mt-4">Danh sách bác sĩ</h1>

                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-user-md mr-1"></i>
                        Doctor List
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên bác sĩ</th>
                                    <th>Chuyên nghành</th>
                                    <th>Số điện thoại</th>
                                    <th>Giá khám</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:choose>
                                    <c:when test="${empty doctors}">
                                        <tr>
                                            <td colspan="5" class="text-center text-muted">
                                                Không có bác sĩ nào.
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="d" items="${doctors}">
                                            <tr>
                                                <td>${d.id}</td>
                                                <td>${d.fullName}</td>
                                                <td>${d.specialtyName}</td>
                                                <td>${d.phone}</td>
                                                <td>
                                                    <fmt:formatNumber value="${d.price}" type="number" groupingUsed="true" /> VNĐ
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>

</body>
</html>

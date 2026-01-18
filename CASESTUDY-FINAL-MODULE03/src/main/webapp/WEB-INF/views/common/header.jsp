<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Clinic Booking</title>

    <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">

<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Brand -->
    <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/doctors">
        Clinic Booking
    </a>


    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" type="button">
        <i class="fas fa-bars"></i>
    </button>


    <ul class="navbar-nav ms-auto me-3 me-lg-4 align-items-center">


        <c:if test="${not empty sessionScope.user}">
            <li class="nav-item me-3 text-white small">
                Xin chào,
                <strong>${sessionScope.user.fullName}</strong>
            </li>
        </c:if>

        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/doctors">
                <i class="fas fa-user-md"></i> Bác sĩ
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/appointments">
                <i class="fas fa-calendar-alt"></i> Lịch của tôi
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/appointments/create">
                <i class="fas fa-plus-circle"></i> Đặt lịch
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link text-danger"
               href="${pageContext.request.contextPath}/logout"
               onclick="return confirm('Bạn chắc chắn muốn đăng xuất không?');">
                <i class="fas fa-sign-out-alt"></i> Đăng xuất
            </a>
        </li>
    </ul>
</nav>

<div id="layoutSidenav">

    <div id="layoutSidenav_nav"></div>

    <div id="layoutSidenav_content">

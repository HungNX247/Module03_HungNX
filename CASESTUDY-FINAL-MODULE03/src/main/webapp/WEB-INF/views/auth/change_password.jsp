<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container mt-4">
    <h2>Đổi mật khẩu</h2>


    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/change-password"
          method="post"
          novalidate>

        <div class="mb-3">
            <label class="form-label">Mật khẩu hiện tại</label>
            <input type="password" name="currentPassword" class="form-control">

            <c:if test="${not empty currentPasswordError}">
                <small class="text-danger">${currentPasswordError}</small>
            </c:if>
        </div>

        <div class="mb-3">
            <label class="form-label">Mật khẩu mới</label>
            <input type="password" name="newPassword" class="form-control">

            <c:if test="${not empty newPasswordError}">
                <small class="text-danger">${newPasswordError}</small>
            </c:if>
        </div>

        <div class="mb-3">
            <label class="form-label">Nhập lại mật khẩu mới</label>
            <input type="password" name="confirmPassword" class="form-control">

            <c:if test="${not empty confirmPasswordError}">
                <small class="text-danger">${confirmPasswordError}</small>
            </c:if>
        </div>

        <button type="submit" class="btn btn-primary"
                onclick="return confirm('Sau khi Xác nhận, hệ thống sẽ đăng xuất tài khoản của bạn. Bạn chắc chắn muốn đổi mật khẩu?');">
            Đổi mật khẩu
        </button>
    </form>
</div>

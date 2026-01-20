<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Đăng nhập - Clinic Booking</title>

    <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
            crossorigin="anonymous"></script>

    <style>
        .error-box {
            background: #ffe5e5;
            border: 1px solid #ffb3b3;
            color: #cc0000;
            padding: 10px 12px;
            border-radius: 6px;
            margin-bottom: 14px;
            font-size: 14px;
        }

        .field-error {
            color: #cc0000;
            font-size: 13px;
            margin-top: 6px;
        }
    </style>
</head>

<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">

                            <div class="card-header">
                                <h3 class="text-center font-weight-light my-4">Đăng nhập</h3>
                            </div>

                            <div class="card-body">

                                <!-- error chung -->
                                <c:if test="${not empty error}">
                                    <div class="error-box">
                                        <i class="fas fa-exclamation-triangle"></i>
                                            ${error}
                                    </div>
                                </c:if>

                                <!-- success từ session -->
                                <c:if test="${not empty sessionScope.success}">
                                    <div class="alert alert-success" role="alert" style="margin-bottom: 14px;">
                                        <i class="fas fa-check-circle"></i>
                                            ${sessionScope.success}
                                    </div>
                                    <c:remove var="success" scope="session"/>
                                </c:if>

                                <form method="post"
                                      action="${pageContext.request.contextPath}/login"
                                      novalidate>

                                    <div class="form-group">
                                        <label class="small mb-1" for="phone">Số điện thoại</label>
                                        <input class="form-control py-4"
                                               id="phone"
                                               name="phone"
                                               type="text"
                                               placeholder="Ví dụ: 0901234567"
                                               value="${phone}" />

                                        <c:if test="${not empty phoneError}">
                                            <div class="field-error">${phoneError}</div>
                                        </c:if>
                                    </div>

                                    <div class="form-group">
                                        <label class="small mb-1" for="password">Mật khẩu</label>
                                        <input class="form-control py-4"
                                               id="password"
                                               name="password"
                                               type="password"
                                               placeholder="Nhập mật khẩu" />

                                        <c:if test="${not empty passwordError}">
                                            <div class="field-error">${passwordError}</div>
                                        </c:if>
                                    </div>

                                    <div class="form-group d-flex align-items-center justify-content-center mt-4 mb-0">
                                        <button type="submit" class="btn btn-primary">
                                            Đăng nhập
                                        </button>
                                    </div>

                                </form>
                            </div>

                            <div class="card-footer text-center">
                                <div class="small">
                                    Chưa có tài khoản?
                                    <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid">
                <div class="d-flex align-items-center justify-content-center small">
                    <div class="text-muted">© Clinic Booking 2026</div>
                </div>
            </div>
        </footer>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
</body>
</html>

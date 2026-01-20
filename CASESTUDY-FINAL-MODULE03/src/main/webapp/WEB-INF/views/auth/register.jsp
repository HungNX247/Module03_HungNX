<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Đăng ký - Clinic Booking</title>

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

        .form-group label {
            min-height: 18px;
        }
    </style>
</head>

<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">

                            <div class="card-header">
                                <h3 class="text-center font-weight-light my-4">Tạo tài khoản</h3>
                            </div>

                            <div class="card-body">

                                <c:if test="${not empty error}">
                                    <div class="error-box">
                                        <i class="fas fa-exclamation-triangle"></i>
                                            ${error}
                                    </div>
                                </c:if>

                                <form method="post" action="${pageContext.request.contextPath}/register" novalidate>

                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-group d-flex flex-column">
                                                <label class="small mb-1" for="fullName">Họ và tên</label>
                                                <input class="form-control py-4"
                                                       id="fullName"
                                                       name="fullName"
                                                       type="text"
                                                       placeholder="Nhập họ và tên"
                                                       value="${fullName}"
                                                       required />

                                                <c:if test="${not empty fullNameError}">
                                                    <div class="field-error">${fullNameError}</div>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group d-flex flex-column">
                                                <label class="small mb-1" for="phone">Số điện thoại</label>
                                                <input class="form-control py-4"
                                                       id="phone"
                                                       name="phone"
                                                       type="text"
                                                       placeholder="Ví dụ: 0901234567"
                                                       value="${phone}"
                                                       required />

                                                <c:if test="${not empty phoneError}">
                                                    <div class="field-error">${phoneError}</div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-group d-flex flex-column">
                                                <label class="small mb-1" for="password">Mật khẩu</label>
                                                <input class="form-control py-4"
                                                       id="password"
                                                       name="password"
                                                       type="password"
                                                       placeholder="Nhập mật khẩu"
                                                       required />

                                                <c:if test="${not empty passwordError}">
                                                    <div class="field-error">${passwordError}</div>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group d-flex flex-column">
                                                <label class="small mb-1" for="confirmPassword">Nhập lại mật khẩu</label>
                                                <input class="form-control py-4"
                                                       id="confirmPassword"
                                                       name="confirmPassword"
                                                       type="password"
                                                       placeholder="Nhập lại mật khẩu"
                                                       required />

                                                <c:if test="${not empty confirmPasswordError}">
                                                    <div class="field-error">${confirmPasswordError}</div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group mt-4 mb-0">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Đăng ký
                                        </button>
                                    </div>

                                </form>
                            </div>

                            <div class="card-footer text-center">
                                <div class="small">
                                    Đã có tài khoản?
                                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
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

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<main>
    <div class="container-fluid px-4">

        <h1 class="mt-4">Lịch khám của tôi</h1>
        <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/doctors">Bác sĩ</a>
            </li>
            <li class="breadcrumb-item active">Lịch khám của tôi</li>
        </ol>

        <div class="card shadow-sm mb-4">
            <div class="card-header">
                <i class="fas fa-calendar-alt"></i>
                Danh sách lịch hẹn
            </div>

            <div class="card-body">

                <c:if test="${empty appointments}">
                    <div class="alert alert-info" role="alert">
                        <i class="fas fa-info-circle"></i>
                        Bạn chưa có lịch hẹn nào.
                        <a href="${pageContext.request.contextPath}/appointments/create" class="alert-link">
                            Đặt lịch ngay
                        </a>
                    </div>
                </c:if>

                <c:if test="${not empty appointments}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover align-middle">
                            <thead class="table-light">
                            <tr>
                                <th style="width: 70px;">ID</th>
                                <th style="width: 100px;">Doctor ID</th>
                                <th style="width: 140px;">Ngày</th>
                                <th style="width: 120px;">Giờ</th>
                                <th style="width: 130px;">Trạng thái</th>
                                <th>Ghi chú</th>
                                <th style="width: 120px;">Hành động</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="ap" items="${appointments}">
                                <tr>
                                    <td>${ap.id}</td>
                                    <td>${ap.doctorId}</td>
                                    <td>${ap.appointmentDate}</td>
                                    <td>${ap.appointmentTime}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${ap.status == 'BOOKED'}">
                                                <span class="badge bg-primary">BOOKED</span>
                                            </c:when>
                                            <c:when test="${ap.status == 'CANCELED'}">
                                                <span class="badge bg-danger">CANCELED</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-secondary">${ap.status}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                        <c:out value="${ap.note}" default="(Không có)"/>
                                    </td>

                                    <td>
                                        <c:if test="${ap.status == 'BOOKED'}">
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/appointments/cancel"
                                                  style="margin: 0;">
                                                <input type="hidden" name="id" value="${ap.id}">
                                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                                        onclick="return confirm('Bạn chắc chắn muốn huỷ lịch hẹn này?');">
                                                    <i class="fas fa-times"></i> Huỷ
                                                </button>
                                            </form>
                                        </c:if>

                                        <c:if test="${ap.status != 'BOOKED'}">
                                            <span class="text-muted">—</span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

            </div>
        </div>

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/appointments/create">
            <i class="fas fa-plus-circle"></i> Đặt lịch mới
        </a>

    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

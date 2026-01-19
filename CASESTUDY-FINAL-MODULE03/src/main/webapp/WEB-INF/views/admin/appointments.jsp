<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<main>
    <div class="container-fluid px-4">
        <h1 class="mt-4">Quản lý lịch khám</h1>

        <div class="mb-3">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/appointments/create">
                <i class="fas fa-plus-circle"></i> Đặt lịch cho bệnh nhân
            </a>
        </div>

        <div class="card shadow-sm mb-4">
            <div class="card-header">
                <i class="fas fa-calendar-alt"></i>
                Danh sách lịch hẹn (Admin)
            </div>

            <div class="card-body">

                <c:if test="${not empty success}">
                    <div class="alert alert-success">
                            ${success}
                    </div>
                </c:if>


                <c:if test="${empty appointments}">
                    <div class="alert alert-info">
                        Chưa có lịch hẹn nào.
                    </div>
                </c:if>

                <c:if test="${not empty appointments}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover align-middle">
                            <thead class="table-light">
                            <tr>
                                <th>ID</th>
                                <th>Bệnh nhân</th>
                                <th>SĐT</th>
                                <th>Bác sĩ</th>
                                <th>Chuyên ngành</th>
                                <th>Ngày</th>
                                <th>Giờ</th>
                                <th>Trạng thái</th>
                                <th>Ghi chú</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="ap" items="${appointments}">
                                <tr>
                                    <td>${ap.id}</td>
                                    <td>${ap.patientName}</td>
                                    <td>${ap.patientPhone}</td>
                                    <td>${ap.doctorName}</td>
                                    <td>${ap.specialtyName}</td>
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
                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<main>
    <div class="container-fluid px-4">
        <h1 class="mt-4">Đặt lịch cho bệnh nhân</h1>

        <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/admin/appointments">Quản lý lịch khám</a>
            </li>
            <li class="breadcrumb-item active">Tạo lịch mới</li>
        </ol>

        <div class="card shadow-sm mb-4">
            <div class="card-header">
                <i class="fas fa-plus-circle"></i>
                Tạo lịch hẹn
            </div>

            <div class="card-body">

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        <i class="fas fa-exclamation-triangle"></i>
                            ${error}
                    </div>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/admin/appointments/create">

                    <div class="mb-3">
                        <label class="form-label">Bệnh nhân</label>
                        <select name="patientId" class="form-control" required>
                            <option value="">-- Chọn bệnh nhân --</option>
                            <c:forEach var="p" items="${patients}">
                                <option value="${p.id}">
                                        ${p.fullName} - ${p.phone}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Bác sĩ</label>
                        <select name="doctorId" class="form-control" required>
                            <option value="">-- Chọn bác sĩ --</option>
                            <c:forEach var="d" items="${doctors}">
                                <option value="${d.id}">
                                        ${d.fullName} (${d.specialtyName})
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Ngày khám</label>
                            <input type="date" name="date" class="form-control" required />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="form-label">Giờ khám</label>
                            <input type="time" name="time" class="form-control" required />
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ghi chú</label>
                        <textarea name="note" class="form-control" rows="3" placeholder="(Không bắt buộc)"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-check"></i> Tạo lịch
                    </button>

                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/appointments">
                        Quay lại
                    </a>

                </form>

            </div>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<main>
    <div class="container-fluid px-4">

        <h1 class="mt-4">Đặt lịch khám</h1>
        <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/doctors">Danh sách bác sĩ</a>
            </li>
            <li class="breadcrumb-item active">Đặt lịch</li>
        </ol>


        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-7">

                <!-- Error -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fas fa-exclamation-triangle"></i>
                            ${error}
                    </div>
                </c:if>

                <div class="card shadow-sm">
                    <div class="card-header">
                        <i class="fas fa-calendar-plus"></i>
                        Thông tin đặt lịch
                    </div>

                    <div class="card-body p-4">

                        <form method="post" action="${pageContext.request.contextPath}/appointments/create">

                            <!-- Doctor -->
                            <div class="form-group mb-3">
                                <label class="font-weight-bold">Chọn bác sĩ</label>
                                <select class="form-control" name="doctorId" required>
                                    <c:forEach var="d" items="${doctors}">
                                        <option value="${d.id}">
                                                ${d.fullName} - ${d.price} VNĐ
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Date -->
                            <div class="form-group mb-3">
                                <label class="font-weight-bold">Ngày khám</label>
                                <input class="form-control" type="date" name="date" value="${oldDate}"  />
                                <c:if test="${not empty dateError}">
                                    <small class="text-danger">${dateError}</small>
                                </c:if>
                            </div>

                            <!-- Time -->
                            <div class="form-group mb-3">
                                <label class="font-weight-bold">Giờ khám</label>
                                <input class="form-control" type="time" name="time" value="${oldTime}" />
                                <c:if test="${not empty timeError}">
                                    <small class="text-danger">${timeError}</small>
                                </c:if>
                            </div>

                            <!-- Note -->
                            <div class="form-group mb-4">
                                <label class="font-weight-bold">Ghi chú</label>
                                <input class="form-control"
                                       type="text"
                                       name="note"
                                       placeholder="Ví dụ: đau họng, ho, sốt..." />
                                <small class="text-muted">
                                    (Không bắt buộc)
                                </small>
                            </div>

                            <!-- Buttons -->
                            <div class="d-flex">
                                <button type="submit" class="btn btn-primary mr-2">
                                    <i class="fas fa-check"></i> Đặt lịch
                                </button>

                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/doctors">
                                    <i class="fas fa-arrow-left"></i> Quay lại
                                </a>
                            </div>

                        </form>

                    </div>
                </div>

            </div>
        </div>

    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

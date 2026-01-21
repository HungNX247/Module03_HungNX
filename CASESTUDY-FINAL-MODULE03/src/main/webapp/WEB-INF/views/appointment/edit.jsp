<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<main>
    <div class="container-fluid px-4">


        <h1 class="mt-4">Sửa lịch khám</h1>


        <div class="breadcrumb mb-4 bg-light rounded p-3">
            <a href="${pageContext.request.contextPath}/doctors" class="text-decoration-none">Danh sách bác sĩ</a>
            <span class="mx-2">/</span>
            <span>Sửa lịch</span>
        </div>


        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8 col-xl-7">

                <div class="card shadow-sm">
                    <div class="card-header">
                        <i class="fas fa-calendar-alt"></i>
                        Thông tin đặt lịch
                    </div>

                    <div class="card-body">

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                <i class="fas fa-exclamation-triangle"></i>
                                    ${error}
                            </div>
                        </c:if>


                        <form method="post" action="${pageContext.request.contextPath}/appointments/edit">


                            <input type="hidden" name="id" value="${appointment.id}" />

                            <div class="form-group mb-3">
                                <label class="form-label fw-semibold">Chọn bác sĩ</label>


                                <select class="form-control" name="doctorId" required>
                                    <c:forEach var="d" items="${doctors}">
                                        <option value="${d.id}" ${d.id == appointment.doctorId ? "selected" : ""}>
                                                ${d.fullName} - ${d.price} VNĐ
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-semibold">Ngày khám</label>


                                <input type="date"
                                       class="form-control"
                                       name="appointmentDate"
                                       value="${appointment.appointmentDate}"
                                       required />
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-semibold">Giờ khám</label>


                                <input type="time"
                                       class="form-control"
                                       name="appointmentTime"
                                       value="${appointment.appointmentTime}"
                                       required />
                            </div>

                            <div class="mb-4">
                                <label class="form-label fw-semibold">Ghi chú</label>


                                <textarea class="form-control"
                                          name="note"
                                          rows="3"
                                          placeholder="Ví dụ: đau họng, ho, sốt...">${appointment.note}</textarea>

                                <div class="form-text">(Không bắt buộc)</div>
                            </div>


                            <div class="d-flex gap-2">
                                <button type="submit" class="btn btn-primary mr-2">
                                    <i class="fas fa-check"></i> Cập nhât
                                </button>


                                <a class="btn btn-secondary"
                                   href="${pageContext.request.contextPath}/appointments">
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

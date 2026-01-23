<%--
  Created by IntelliJ IDEA.
  User: HUNGNX-PC
  Date: 1/23/2026
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Rental Space</title>

    <style>
        body { font-family: Arial; margin: 20px; }
        .form-box { max-width: 650px; margin: auto; border: 1px solid #ddd; padding: 16px; border-radius: 10px; }
        label { display: block; margin-top: 10px; font-weight: 600; }
        input, select, textarea { width: 100%; padding: 8px; margin-top: 4px; }
        .btn { padding: 8px 12px; border: none; border-radius: 6px; cursor: pointer; }
        .btn-save { background: #007bff; color: white; }
        .btn-cancel { background: #6c757d; color: white; text-decoration:none; display:inline-block; padding: 8px 12px; border-radius: 6px; }
        .error { color: red; margin-bottom: 10px; font-weight: 600; }
    </style>
</head>
<body>

<div class="form-box">
    <h2>Create Rental Space</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/rental-space/create" method="post" onsubmit="return validateForm()">

        <label>Mã mặt bằng (*)</label>
        <input type="text" id="spaceId" name="spaceId" placeholder="ABC-12-XY" required>

        <label>Diện tích (m²) (*)</label>
        <input type="number" id="area" name="area" required>

        <label>Trạng thái (*)</label>
        <select id="status" name="status" required>
            <option value="Trống">Trống</option>
            <option value="Hạ tầng">Hạ tầng</option>
            <option value="Đầy đủ">Đầy đủ</option>
        </select>

        <label>Tầng (*)</label>
        <select id="floor" name="floor" required>
            <c:forEach var="i" begin="1" end="15">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>

        <label>Loại mặt bằng (*)</label>
        <select id="type" name="type" required>
            <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
            <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
        </select>

        <label>Mô tả</label>
        <textarea id="description" name="description" rows="3"></textarea>

        <label>Giá thuê (VND) (*)</label>
        <input type="number" id="price" name="price" required>

        <label>Ngày bắt đầu (*)</label>
        <input type="date" id="startDate" name="startDate" required>

        <label>Ngày kết thúc (*)</label>
        <input type="date" id="endDate" name="endDate" required>

        <br><br>
        <button class="btn btn-save" type="submit">Save</button>
        <a class="btn-cancel" href="${pageContext.request.contextPath}/rental-space">Cancel</a>
    </form>
</div>

<script>
    function validateForm() {
        const spaceId = document.getElementById("spaceId").value.trim();
        const area = parseFloat(document.getElementById("area").value);
        const price = parseInt(document.getElementById("price").value);

        const regex = /^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$/;
        if (!regex.test(spaceId)) {
            alert("Mã số không gian phải khớp với định dạng XXX-XX-XX (chữ cái viết hoa hoặc chữ số).");
            return false;
        }


        if (isNaN(area) || area <= 20) {
            alert("Diện tích phải lớn hơn 20 m².");
            return false;
        }

        // Price > 1,000,000
        if (isNaN(price) || price <= 1000000) {
            alert("Giá phải lớn hơn 1.000.000 VND.");
            return false;
        }

        return true;
    }
</script>

</body>
</html>


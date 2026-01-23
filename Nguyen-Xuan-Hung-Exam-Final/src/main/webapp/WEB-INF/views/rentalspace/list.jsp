<%--
  Created by IntelliJ IDEA.
  User: HUNGNX-PC
  Date: 1/23/2026
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rental Space List</title>

    <style>
        body { font-family: Arial; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #f3f3f3; }
        .toolbar { display: flex; justify-content: space-between; align-items: center; }
        .btn { padding: 8px 12px; text-decoration: none; background: #007bff; color: white; border-radius: 6px; border: none; cursor: pointer;}
        .btn:hover { opacity: 0.9; }
        .btn-danger { background: #dc3545; }
        .btn-gray { background: #6c757d; }
        .search-box { padding: 12px; border: 1px solid #ddd; border-radius: 8px; }
        .search-box select, .search-box input { padding: 6px; margin-right: 6px; }
    </style>
</head>
<body>

<div class="toolbar">
    <h2>Quản lý mặt bằng</h2>
    <a class="btn" href="${pageContext.request.contextPath}/rental-space/create">+ Tạo mới</a>
</div>

<div class="search-box">
    <form action="${pageContext.request.contextPath}/rental-space" method="get">
        Loại mặt bằng:
        <select name="type">
            <option value="">--Tất cả--</option>
            <option value="Shared Office">Văn phòng chia sẻ</option>
            <option value="Full-service Office">Văn phòng trọn gói</option>
        </select>

        Giá thuê:
        <input type="number" name="price" placeholder="e.g. 2000000" />

        Tầng:
        <select name="floor">
            <option value="">--All--</option>
            <c:forEach var="i" begin="1" end="15">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>

        <button class="btn" type="submit">Tìm kiếm</button>
        <a class="btn btn-gray" href="${pageContext.request.contextPath}/rental-space">Reset</a>
    </form>
</div>

<table>
    <thead>
    <tr>
        <th>Mã mặt bằng</th>
        <th>Diện tích</th>
        <th>Trạng thái</th>
        <th>Tầng</th>
        <th>Loại mặt bằng</th>
        <th>Mô tả</th>
        <th>Giá thuê</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Xóa</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="s" items="${list}">
        <tr>
            <td>${s.spaceId}</td>
            <td>${s.area}</td>
            <td>${s.status}</td>
            <td>${s.floor}</td>
            <td>${s.type}</td>
            <td>${s.description}</td>
            <td>${s.price}</td>
            <td>${s.startDate}</td>
            <td>${s.endDate}</td>
            <td>
                <a class="btn btn-danger"
                   href="${pageContext.request.contextPath}/rental-space/delete?spaceId=${s.spaceId}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa không gian cho thuê có ID ${s.spaceId} không?')">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>


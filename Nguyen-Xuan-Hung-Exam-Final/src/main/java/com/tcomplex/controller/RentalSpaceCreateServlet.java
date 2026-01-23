package com.tcomplex.controller;

import com.tcomplex.dao.RentalSpaceDAO;
import com.tcomplex.model.RentalSpace;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

@WebServlet("/rental-space/create")
public class RentalSpaceCreateServlet extends HttpServlet {
    private final RentalSpaceDAO rentalSpaceDAO = new RentalSpaceDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/rentalspace/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String spaceId = req.getParameter("spaceId");
        String areaStr = req.getParameter("area");
        String status = req.getParameter("status");
        String floorStr = req.getParameter("floor");
        String type = req.getParameter("type");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");

        if (spaceId == null || areaStr == null || floorStr == null || priceStr == null
                || startDateStr == null || endDateStr == null
                || spaceId.isBlank() || areaStr.isBlank() || floorStr.isBlank() || priceStr.isBlank()
                || startDateStr.isBlank() || endDateStr.isBlank()) {

            req.setAttribute("error", "Vui lòng điền đầy đủ tất cả các trường bắt buộc!");
            req.getRequestDispatcher("/WEB-INF/views/rentalspace/create.jsp").forward(req, resp);
            return;
        }

        double area;
        int floor;
        long price;
        Date startDate;
        Date endDate;

        try {
            area = Double.parseDouble(areaStr);
            floor = Integer.parseInt(floorStr);
            price = Long.parseLong(priceStr);
            startDate = Date.valueOf(startDateStr);
            endDate = Date.valueOf(endDateStr);
        } catch (Exception e) {
            req.setAttribute("error", "Dữ liệu đầu vào không hợp lệ");
            req.getRequestDispatcher("/WEB-INF/views/rentalspace/create.jsp").forward(req, resp);
            return;
        }

        if (rentalSpaceDAO.existsById(spaceId)) {
            req.setAttribute("error","Mã số không gian cho thuê đã tồn tại!");
            req.getRequestDispatcher("/WEB-INF/views/rentalspace/create.jsp").forward(req, resp);
            return;
        }

        LocalDate date1 = startDate.toLocalDate();
        LocalDate date2 = endDate.toLocalDate();;

        if (date1.plusMonths(6).isAfter(date2)) {
            req.setAttribute("error","Ngày bắt đầu phải cách ngày kết thúc ít nhất 6 tháng!");
            req.getRequestDispatcher("/WEB-INF/views/rentalspace/create.jsp").forward(req, resp);
            return;
        }

        RentalSpace rentalSpace = new RentalSpace(spaceId, area, status, floor, type,
                description, price, startDate, endDate);

        rentalSpaceDAO.insert(rentalSpace);
        resp.sendRedirect(req.getContextPath() + "/rental-space");
    }
}

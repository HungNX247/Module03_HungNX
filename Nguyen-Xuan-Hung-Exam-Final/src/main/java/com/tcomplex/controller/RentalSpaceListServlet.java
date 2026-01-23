package com.tcomplex.controller;

import com.tcomplex.dao.RentalSpaceDAO;
import com.tcomplex.model.RentalSpace;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/rental-space")
public class RentalSpaceListServlet extends HttpServlet {

    private final RentalSpaceDAO rentalSpaceDAO = new RentalSpaceDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String priceStr = req.getParameter("price");
        String floorStr = req.getParameter("floor");

        Long price = null;
        Integer floor = null;

        if (priceStr != null && !priceStr.isBlank()) {
            try {
                price = Long.parseLong(priceStr);
            } catch (Exception ignored) {}
        }

        if (floorStr != null && !floorStr.isBlank()) {
            try {
                floor = Integer.parseInt(floorStr);
            } catch (Exception ignored) {}
        }

        boolean hasSearch = (type != null && !type.isBlank() || price != null || floor != null);

        List<RentalSpace> list = hasSearch ?rentalSpaceDAO.search(type,price,floor):rentalSpaceDAO.findAllOrderByAreaAsc();

        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/views/rentalspace/list.jsp").forward(req,resp);
    }
}

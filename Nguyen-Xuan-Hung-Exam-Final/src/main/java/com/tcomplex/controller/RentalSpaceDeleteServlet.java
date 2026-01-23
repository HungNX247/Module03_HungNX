package com.tcomplex.controller;

import com.tcomplex.dao.RentalSpaceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/rental-space/delete")
public class RentalSpaceDeleteServlet extends HttpServlet {
    private final RentalSpaceDAO rentalSpaceDAO = new RentalSpaceDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String spaceId = req.getParameter("spaceId");
        System.out.println("DELETE spaceId = " + spaceId);

        if (spaceId != null && !spaceId.isBlank()) {
            boolean ok = rentalSpaceDAO.deleteById(spaceId);
            System.out.println("Delete result = " + ok);
        }

        resp.sendRedirect(req.getContextPath() + "/rental-space");
    }
}

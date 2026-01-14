package com.codegym.controller;

import com.codegym.model.dto.CustomerDto;
import com.codegym.model.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "customerServlet",
            urlPatterns = { "/customers", "/customer/detail", "/customer/add",
                            "/customer/edit", "/customer/remove" })
public class CustomerController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private CustomerService customerService = null;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/customers":
                req.setAttribute("customers", customerService.findAll());
                req.getRequestDispatcher("/WEB-INF/view/customer/index.jsp").forward(req, resp);
                break;
            case "/customer/detail":
                int detailId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("customer", customerService.find(detailId));
                req.getRequestDispatcher("/WEB-INF/view/customer/detail.jsp").forward(req, resp);
                break;
            case "/customer/add":
                req.getRequestDispatcher("/WEB-INF/view/customer/add.jsp").forward(req, resp);
                break;
            case "/customer/edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("customer", customerService.find(editId));
                req.getRequestDispatcher("/WEB-INF/view/customer/edit.jsp").forward(req, resp);
                break;
            case "/customer/remove":
                int removeId = Integer.parseInt(req.getParameter("id"));
                customerService.remove(removeId);
                resp.sendRedirect(req.getContextPath() + "/customers");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        String name = req.getParameter("name");
        String position = req.getParameter("position");
        String office = req.getParameter("office");
        Integer age = Integer.parseInt(req.getParameter("age"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = req.getParameter("startDate");
        Date startDate;
        try {
            startDate = formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Double salary = Double.parseDouble(req.getParameter("salary"));

        switch (action) {
            case "/customer/add":
                CustomerDto newCustomer = new CustomerDto(name, position, office, age, startDate, salary);
                customerService.add(newCustomer);
                resp.sendRedirect(req.getContextPath() + "/customers");
                break;
            case "/customer/edit":
                int id = Integer.parseInt(req.getParameter("id"));
                CustomerDto editingCustomer = customerService.find(id);

                editingCustomer.setName(name);
                editingCustomer.setPosition(position);
                editingCustomer.setOffice(office);
                editingCustomer.setAge(age);
                editingCustomer.setStartDate(startDate);
                editingCustomer.setSalary(salary);

                customerService.edit(editingCustomer);
                resp.sendRedirect(req.getContextPath() + "/customers");
                break;
            case "/customer/search":
                String searchingName = req.getParameter("searchingName");
                req.setAttribute("customers", customerService.search(searchingName));
                req.getRequestDispatcher("/WEB-INF/view/customer/search.jsp").forward(req, resp);
                break;
        }
    }
}


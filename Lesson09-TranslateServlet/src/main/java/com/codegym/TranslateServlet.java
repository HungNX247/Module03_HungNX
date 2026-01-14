package com.codegym;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TranslateServlet", urlPatterns = "/translate")
public class TranslateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("hello","Xin chào");
        dictionary.put("how","Thế nào");
        dictionary.put("book","Quyển vở");
        dictionary.put("computer","Máy tính");
        dictionary.put("smart","Thông minh");

        String search = req.getParameter("txtSearch");
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        String result = dictionary.get(search);
        if (result != null) {
            writer.println("Word: " + search + "</br>");
            writer.println("Result: " + result);
        } else {
            writer.println("Not found!");
        }
        writer.println("</html>");

    }
}

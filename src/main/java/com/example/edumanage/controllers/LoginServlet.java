package com.example.edumanage.controllers;

import com.example.edumanage.dao.UserDAO;
import com.example.edumanage.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/", "/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/log_in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        if (isValidUser(username, password)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("isLoggedIn", true);
//
//            response.sendRedirect(request.getContextPath() + "/etu?action=new");
//        } else {
//            response.sendRedirect(request.getContextPath() + "/login?error=1");
//        }

        User user = userDAO.checkLogin(username, password);

        if (user != null) {
            request.getSession().setAttribute("isLoggedIn", true);
            response.sendRedirect("/etu?action=new");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            response.sendRedirect("/login");
        }
    }


    }

//    private boolean isValidUser(String username, String password) {
//        return true;
//    }

//        User user = userDAO.checkLogin(username, password);

//        if ("admin".equals(username) && "123456".equals(password)) {
//            resp.sendRedirect("/etu?action=new");
//        } else {
//            resp.sendRedirect("/login");
//        }

//        if (user != null) {
//            request.getSession().setAttribute("user", user);
//            response.sendRedirect("/etu?action=new");
//        } else {
//            request.setAttribute("errorMessage", "Invalid username or password");
//            response.sendRedirect("/login");
//        }
//    }


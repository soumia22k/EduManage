package com.example.edumanage.controllers;

import com.example.edumanage.dao.CoursDAO;
import com.example.edumanage.model.Cours;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cours")
public class CoursServlet extends HttpServlet {
    private CoursDAO coursDAO;

    @Override
    public void init() {
        coursDAO = new CoursDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                request.getRequestDispatcher("ajouterCours.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Cours cours = coursDAO.getCoursById(id);
                request.setAttribute("cours", cours);
                request.getRequestDispatcher("modifierCours.jsp").forward(request, response);
                break;
            case "delete":
                deleteCours(request, response);
                break;
            default:
                listCours(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addCours(request, response);
        } else if ("update".equals(action)) {
            updateCours(request, response);
        }
    }

    private void listCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listCours", coursDAO.getAllCours());
        request.getRequestDispatcher("listCours.jsp").forward(request, response);
    }

    private void addCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Cours newCours = new Cours(0, title, description);
        coursDAO.ajouterCours(newCours);
        response.sendRedirect("cours");
    }

    private void updateCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Cours updatedCours = new Cours(id, title, description);
        coursDAO.modifierCours(updatedCours);
        response.sendRedirect("cours");
    }

    private void deleteCours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        coursDAO.supprimerCours(id);
        response.sendRedirect("cours");
    }
}
package com.example.edumanage.controllers;

import com.example.edumanage.dao.EtudiantDAO;
import com.example.edumanage.model.Etudiant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/etu")
public class EtudiantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EtudiantDAO etudiantDAO;

    @Override
    public void init() {
        etudiantDAO = new EtudiantDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEtudiant(request, response);
                    break;
                case "list":
                    listEtudiant(request, response);
                    break;
                case "delete":
                    deleteEtudiant(request, response);
                    break;
                case "edit_form":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateEtudiant(request, response);
                    break;
                default:
//                    showNewForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ajouter_etu.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String family_name = request.getParameter("family_name");
        String first_name = request.getParameter("first_name");
        String email = request.getParameter("email");
        String birth_date = request.getParameter("birth_date");

        Etudiant newEtudiant = new Etudiant(family_name, first_name, email, birth_date);
        etudiantDAO.addEtudiant(newEtudiant);

        response.sendRedirect("/etu?action=list");
    }

    private void listEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Etudiant> listUser = etudiantDAO.selectAllEtudiant();
        request.setAttribute("etudiantList", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("liste_etu.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant existingEtudiant = etudiantDAO.selectEtudiantById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("modifier_etu.jsp");
        request.setAttribute("etudiant", existingEtudiant);
        dispatcher.forward(request, response);
    }

    private void updateEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String family_name = request.getParameter("family_name");
        String first_name = request.getParameter("first_name");
        String email = request.getParameter("email");
        String birth_date = request.getParameter("birth_date");

        Etudiant etudiant = new Etudiant(id, family_name, first_name, email, birth_date);
        etudiantDAO.updateEtudiant(etudiant);
        response.sendRedirect("/etu?action=list");
    }

    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantDAO.deleteEtudiant(id);
        response.sendRedirect("etu?action=list");
    }
}

package com.example.edumanage.controllers;

import com.example.edumanage.dao.CoursDAO;
import com.example.edumanage.dao.EtudiantDAO;
import com.example.edumanage.model.Cours;
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
        CoursDAO coursDAO = new CoursDAO();
        List<Cours> allCourses = coursDAO.getAllCours();

        System.out.println("Number of courses fetched: " + allCourses.size());

        request.setAttribute("allCourses", allCourses);

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

        int student_id = etudiantDAO.getLastInsertedId();

        String[] courseIds = request.getParameterValues("courses");
        if (courseIds != null) {
            for (String course_id : courseIds) {
                etudiantDAO.assignCourseToStudent(student_id, Integer.parseInt(course_id));
            }
        }

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

        CoursDAO coursDAO = new CoursDAO();
        List<Cours> allCourses = coursDAO.getAllCours();
        request.setAttribute("allCourses", allCourses);

        List<Integer> etudiantCourses = etudiantDAO.getCoursesForStudent(id);
        request.setAttribute("etudiantCourses", etudiantCourses);

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

        String[] courseIds = request.getParameterValues("courses");
        if (courseIds != null) {
            etudiantDAO.removeAllCoursesFromStudent(id);

            for (String courseId : courseIds) {
                etudiantDAO.assignCourseToStudent(id, Integer.parseInt(courseId));
            }
        }

        response.sendRedirect("/etu?action=list");
    }

    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantDAO.deleteEtudiant(id);
        response.sendRedirect("etu?action=list");
    }
}

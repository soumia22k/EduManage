<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.edumanage.model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.edumanage.dao.EtudiantDAO" %>
<%@ page import="com.example.edumanage.dao.CoursDAO" %>
<%@ page import="com.example.edumanage.model.Cours" %>
<%
    EtudiantDAO etudiantDAO = new EtudiantDAO();
    List<Etudiant> etudiantList = etudiantDAO.selectAllEtudiant();
    CoursDAO coursDAO = new CoursDAO();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles/header.css">
    <link rel="stylesheet" href="./styles/etud_liste.css">
    <title>Liste des Étudiants</title>
</head>
<body>
<header>
    <button class="menu-toggle">☰</button>
    <nav class="sidebar">
        <div class="option"><a href="etu?action=new">Add student <img src="./assets/icons/add-user (1).png" alt="add"></a></div>
        <div class="option"><a href="cours?action=add">Add cours <img src="./assets/icons/book.png" alt="add"></a></div>
        <div class="option"><a href="etu?action=list">Student list <img src="./assets/icons/friend.png" alt="add"></a></div>
        <div class="option"><a href="cours">Cours list <img src="./assets/icons/list (1).png" alt="add"></a></div>
        <div class="option"><a href="log_in.jsp">Déconnecter<img src="./assets/icons/log_out.png" alt="add"></a></div>
    </nav>
</header>
<main>
    <section>
        <div id="list">
            <h1>List des étudiants</h1>
            <div>
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nom</th>
                        <th scope="col">Prénom</th>
                        <th scope="col">Email</th>
                        <th scope="col">Date de naissance</th>
                        <th scope="col">Courses</th>
                        <th scope="col">Options</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Etudiant etudiant : etudiantList) {
                            List<Integer> courseIds = etudiantDAO.getCoursesForStudent(etudiant.getId());
                            StringBuilder courses = new StringBuilder();
                            for (Integer courseId : courseIds) {
                                Cours course = coursDAO.getCoursById(courseId);
                                courses.append(course.getTitle()).append(", ");
                            }
                            if (courses.length() > 0) {
                                courses.setLength(courses.length() - 2);
                            }
                    %>
                    <tr>
                        <td><%= etudiant.getId() %></td>
                        <td><%= etudiant.getFamily_name() %></td>
                        <td><%= etudiant.getFirst_name() %></td>
                        <td><%= etudiant.getEmail() %></td>
                        <td><%= etudiant.getBirth_date() %></td>
                        <td><%= courses.toString() %></td>
                        <td>
                            <a href="etu?action=edit_form&&id=<%= etudiant.getId() %>"><button type="submit" class="btn btn-primary editBtn">Edit</button></a>
                            <a href="etu?action=delete&&id=<%= etudiant.getId() %>" onclick="return confirm('Voulez-vous vraiment supprimer ce cours ?')"><button type="submit" class="btn btn-primary deleteBtn">Delete</button></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./scripts/header.js"></script>
</body>
</html>
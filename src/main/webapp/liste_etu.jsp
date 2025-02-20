<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.edumanage.model.Etudiant" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles/header.css">
    <link rel="stylesheet" href="./styles/etud_liste.css">
    <title>Ajouter</title>
</head>
<body>
<header>
    <button class="menu-toggle">☰</button>
    <nav class="sidebar">
        <div class="option"><a href="/new">Add student <img src="./assets/icons/add-user (1).png" alt="add"></a></div>
        <div class="option"><a href="cours.jsp">Add cours <img src="./assets/icons/book.png" alt="add"></a></div>
        <div class="option"><a href="/list">Student list <img src="./assets/icons/friend.png" alt="add"></a></div>
        <div class="option"><a href="cours_liste.jsp">Cours list <img src="./assets/icons/list (1).png" alt="add"></a></div>
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
                        List<Etudiant> etudiantList = (List<Etudiant>) request.getAttribute("etudiantList");

                        for (Etudiant etudiant : etudiantList) {
                    %>
                    <tr>
                        <td><%= etudiant.getId() %></td>
                        <td><%= etudiant.getFamily_name() %></td>
                        <td><%= etudiant.getFirst_name() %></td>
                        <td><%= etudiant.getEmail() %></td>
                        <td><%= etudiant.getBirth_date() %></td>
                        <td>empty</td>
<%--                    <td>${etudiant.courses}</td>--%>
                        <td>
                            <a href="./modifier.jsp?id=${etudiant.id}"><button type="submit" class="btn btn-primary editBtn">Edit</button></a>
                            <a href="/delete?id=${etudiant.id}"><button type="submit" class="btn btn-primary deleteBtn">Delete</button></a>
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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7
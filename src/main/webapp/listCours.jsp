<%@ page import="java.util.List" %>
<%@ page import="com.example.edumanage.model.Cours" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles/header.css">
    <link rel="stylesheet" href="./styles/etud_liste.css">
    <title>Liste des Cours</title>
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
    <div id="list">
        <div class="container mt-5">
            <h1>Liste des Cours</h1>
            <a href="ajouterCours.jsp" class="btn btn-success mb-3">Ajouter un Cours</a>
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <%
                    List<Cours> listCours = (List<Cours>) request.getAttribute("listCours");
                    if (listCours != null) {
                        for (Cours cours : listCours) {
                %>
                <tr>
                    <td><%= cours.getId() %></td>
                    <td><%= cours.getTitle() %></td>
                    <td><%= cours.getDescription() %></td>
                    <td>
                        <a href="cours?action=edit&id=<%= cours.getId() %>" class="btn btn-primary">Modifier</a>
                        <a href="cours?action=delete&id=<%= cours.getId() %>" class="btn btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer ce cours ?')">Supprimer</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="4">Aucun cours trouvé.</td></tr>
                <%
                    }
                %>

            </table>
        </div>
    </div>
</main>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./scripts/header.js"></script>
</body>
</html>
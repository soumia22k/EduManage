<%@ page import="java.util.List" %>
<%@ page import="com.example.edumanage.model.Cours" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <title>Liste des Cours</title>
</head>
<body>
<div class="container mt-5">
    <h2>Liste des Cours</h2>
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
            <td><%= cours.getName() %></td>
            <td><%= cours.getDescription() %></td>
            <td>
                <a href="CoursServlet?action=edit&id=<%= cours.getId() %>" class="btn btn-primary">Modifier</a>
                <a href="CoursServlet?action=delete&id=<%= cours.getId() %>" class="btn btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer ce cours ?')">Supprimer</a>
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
</body>
</html>
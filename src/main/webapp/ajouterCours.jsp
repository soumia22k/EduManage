<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <title>Ajouter un Cours</title>
</head>
<body>
<div class="container mt-5">
    <h2>Ajouter un Nouveau Cours</h2>
    <form action="CoursServlet" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label for="nom">Nom du Cours</label>
            <input type="text" class="form-control" id="nom" name="nom" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
        <a href="listCours.jsp" class="btn btn-secondary">Retour</a>
    </form>
</div>
</body>
</html>
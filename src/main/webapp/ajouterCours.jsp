<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles/header.css">
    <link rel="stylesheet" href="./styles/home.css">
    <title>Ajouter un Cours</title>
</head>
<body>
<header>
    <button class="menu-toggle">☰</button>
    <nav class="sidebar">
        <div class="option"><a href="etu?action=new">Add student <img src="./assets/icons/add-user (1).png" alt="add"></a></div>
        <div class="option"><a href="cours?action=add">Add cours <img src="./assets/icons/book.png" alt="add"></a></div>
        <div class="option"><a href="etu?action=list">Student list <img src="./assets/icons/friend.png" alt="add"></a></div>
        <div class="option"><a href="cours">Cours list <img src="./assets/icons/list (1).png" alt="add"></a></div>
        <div class="option"><a href="logout">Déconnecter<img src="./assets/icons/log_out.png" alt="add"></a></div>
    </nav>
</header>
<div class="container mt-5">
    <div id="form">

        <form id="form1" action="cours?action=add" method="post">
            <h2>Ajouter un Nouveau Cours</h2>
            <input type="hidden" name="action" value="add">

            <div class="form-group">
                <label for="title">Nom du Cours</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Ajouter</button>
            <a href="listCours.jsp" class="btn btn-secondary">Retour</a>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./scripts/header.js"></script>
</body>
</html>
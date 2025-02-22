<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.edumanage.model.Etudiant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles/header.css">
    <link rel="stylesheet" href="./styles/home.css">
    <title>Modifier Étudiant</title>
</head>
<body>
<header>
    <button class="menu-toggle">☰</button>
    <nav class="sidebar">
        <div class="option"><a href="etu?action=new">Add student <img src="./assets/icons/add-user (1).png" alt="add"></a></div>
        <div class="option"><a href="cours?action=add">Add cours <img src="./assets/icons/book.png" alt="add"></a></div>
        <div class="option"><a href="etu?action=list">Student list <img src="./assets/icons/friend.png" alt="add"></a></div>
        <div class="option"><a href="cours">Cours list <img src="./assets/icons/list (1).png" alt="add"></a></div>
    </nav>
</header>
<main>
    <section>
        <div id="form">
            <form id="form1" action="etu?action=update" method="post">
                <input type="hidden" name="id" value="${etudiant.id}">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNom">Nom</label>
                        <input type="text" class="form-control" id="inputNom" name="family_name" value="${etudiant.family_name}" placeholder="Nom">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPrenom">Prénom</label>
                        <input type="text" class="form-control" id="inputPrenom" name="first_name" value="${etudiant.first_name}" placeholder="Prénom">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input type="email" class="form-control" id="inputEmail" name="email" value="${etudiant.email}" placeholder="Email@exemple.com">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="Date">Date de naissance</label>
                        <input type="date" class="form-control" id="Date" name="birth_date" value="${etudiant.birth_date}" placeholder="">
                    </div>
                </div>
                <div id="btn">
                    <button type="submit" class="btn btn-primary">Modifier</button>
                </div>
            </form>
        </div>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./scripts/header.js"></script>
</body>
</html>
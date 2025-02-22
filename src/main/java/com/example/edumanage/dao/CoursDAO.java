package com.example.edumanage.dao;

import com.example.edumanage.model.Cours;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    private Connection connection;


    public CoursDAO() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/edumanage", "root", "");

            // Create the 'Cours' table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Cours ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nom VARCHAR(255) NOT NULL, "
                    + "description VARCHAR(255) NOT NULL"
                    + ")";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableSQL);
                System.out.println("Table 'Cours' is ready.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection or table creation error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Cours> getAllCours() {
        List<Cours> listCours = new ArrayList<>();
        String query = "SELECT * FROM Cours";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listCours.add(new Cours(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all courses: " + e.getMessage());
            e.printStackTrace();
        }

        return listCours;
    }

    public void ajouterCours(Cours cours) {
        String query = "INSERT INTO Cours (nom, description) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cours.getName());
            ps.setString(2, cours.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public Cours getCoursById(int id) {
        String query = "SELECT * FROM Cours WHERE id = ?";
        Cours cours = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cours = new Cours(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching course by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return cours;
    }


    public void modifierCours(Cours cours) {
        String query = "UPDATE Cours SET nom = ?, description = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cours.getName());
            ps.setString(2, cours.getDescription());
            ps.setInt(3, cours.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void supprimerCours(int id) {
        String query = "DELETE FROM Cours WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
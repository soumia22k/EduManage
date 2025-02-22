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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EduManage", "root", "123456789");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public List<Cours> getAllCours() {
        List<Cours> listCours = new ArrayList<>();
        String query = "SELECT * FROM Courses";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listCours.add(new Cours(
                        rs.getInt("id"),
                        rs.getString("title"),
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
        String query = "INSERT INTO Courses (title, description) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cours.getTitle());
            ps.setString(2, cours.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Cours getCoursById(int id) {
        String query = "SELECT * FROM Courses WHERE id = ?";
        Cours cours = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cours = new Cours(
                            rs.getInt("id"),
                            rs.getString("title"),
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
        String query = "UPDATE Courses SET title = ?, description = ? WHERE id = ?"; // Use 'title' instead of 'nom'

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cours.getTitle()); // Use 'title' instead of 'nom'
            ps.setString(2, cours.getDescription());
            ps.setInt(3, cours.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void supprimerCours(int id) {
        String query = "DELETE FROM Courses WHERE id = ?";

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
package com.example.edumanage.dao;

import com.example.edumanage.model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class EtudiantDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/edumanage";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456789";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_USERS_SQL = "INSERT INTO students(family_name, first_name, email, birth_date) VALUES (?, ?, ?, ?)";

    private static final String SELECT_USER_BY_ID = "select id,family_name,first_name,email,birth_Date from Students where id =?;";
    private static final String SELECT_ALL_USERS = "select * from Students;";
    private static final String DELETE_USERS_SQL = "delete from Students where id = ?;";
    private static final String UPDATE_USERS_SQL = "update Students set family_name = ?,first_name = ?,email= ?, birth_date =? where id = ?;";

    public EtudiantDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public Etudiant selectEtudiantById(int id) {
        Etudiant etudiant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String family_name = rs.getString("family_name");
                String first_name = rs.getString("first_name");
                String email = rs.getString("email");
                String birth_date = rs.getString("birth_date");
                etudiant = new Etudiant(id, family_name, first_name, email, birth_date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return etudiant;
    }

    public void addEtudiant(Etudiant etudiant) {
        System.out.println(INSERT_USERS_SQL);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, etudiant.getFamily_name());
            preparedStatement.setString(2, etudiant.getFirst_name());
            preparedStatement.setString(3, etudiant.getEmail());
            preparedStatement.setDate(4, java.sql.Date.valueOf(etudiant.getBirth_date()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<Etudiant> selectAllEtudiant() {

        List<Etudiant> etudiant = new ArrayList<>();
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String family_name = rs.getString("family_name");
                String first_name = rs.getString("first_name");
                String email = rs.getString("email");
                String birth_date = rs.getString("birth_date");
                etudiant.add(new Etudiant(id, family_name, first_name, email, birth_date));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return etudiant;
    }

    public boolean updateEtudiant(Etudiant etudiant) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, etudiant.getFamily_name());
            preparedStatement.setString(2, etudiant.getFirst_name());
            preparedStatement.setString(3, etudiant.getEmail());
            preparedStatement.setDate(4, java.sql.Date.valueOf(etudiant.getBirth_date()));
            preparedStatement.setInt(5, etudiant.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    public boolean deleteEtudiant(int id) {
        boolean flag;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

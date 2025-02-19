package com.example.edumanage.dao;

import com.example.edumanage.model.Etudiant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class EtudiantDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/edumanage";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private String jdbcDriver = "com.mysql.jdbc.Driver";

    private static final String INSERT_USERS_SQL = "INSERT INTO Students" + "  (family_name, first_name, email, birth_date) VALUES "
            + " (?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,family_name,first_email,email,birth_Date from Students where id =?;";
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

    public void addEtudiant(Etudiant etudiant) {
        System.out.println(INSERT_USERS_SQL);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, etudiant.getFamily_name());
            preparedStatement.setString(1, etudiant.getFirst_name());
            preparedStatement.setString(3, etudiant.getEmail());
            preparedStatement.setString(4, etudiant.getBirth_date());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
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

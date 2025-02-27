package com.example.edumanage.dao;

import com.example.edumanage.model.User;
import java.sql.*;
import static java.sql.DriverManager.drivers;
import static java.sql.DriverManager.getConnection;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/edumanage";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456789";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public User checkLogin(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String dbUsername = resultSet.getString("user_name");
                String dbPassword = resultSet.getString("password");

                user = new User();
                user.setId(id);
                user.setUsername(dbUsername);
                user.setPassword(dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}

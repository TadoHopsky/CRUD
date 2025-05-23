package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataAccessObject {
    private int currentMaxID;

    // JDBC connection
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "postgres";
            connection = DriverManager.getConnection(url, username, password
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<People> index() throws SQLException {
        String sqlQuery = "select * from people";
        List<People> peopleList = new ArrayList<>();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            People p = new People();
            p.setId(resultSet.getInt("id"));
            p.setAge(resultSet.getInt("age"));
            p.setName(resultSet.getString("name"));
            p.setEmail(resultSet.getString("email"));
            p.setAddress(resultSet.getString("address"));

            peopleList.add(p);
        }
        return peopleList;
    }

    public void saveUser(People people) throws SQLException {
        String updateSQL = "insert into people values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

        findMaxId();

        preparedStatement.setInt(1, currentMaxID + 1);
        preparedStatement.setInt(2, people.getAge());
        preparedStatement.setString(3, people.getName());
        preparedStatement.setString(4, people.getEmail());
        preparedStatement.setString(5, people.getAddress());

        preparedStatement.executeUpdate();
    }

    public void findMaxId() throws SQLException {
        String sql = "select max(id) from people";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            currentMaxID = resultSet.getInt(1);
        }
    }

    public People show(int id) throws SQLException {
        People p = new People();

        String sql = "select * from people where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            p.setId(resultSet.getInt("id"));
            p.setAge(resultSet.getInt("age"));
            p.setName(resultSet.getString("name"));
            p.setEmail(resultSet.getString("email"));
            p.setAddress(resultSet.getString("address"));
        } else {
            throw new RuntimeException("Пользователь с ID " + id + " не найден.");
        }
        return p;
    }

    public void removeUserById(Integer id) throws SQLException {
        String sql = "delete from people where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    public void updateUserById(int id, People newData) throws SQLException {
        String sql = "update people set age = ?, name = ?, email = ?, address = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(5, id);
        preparedStatement.setInt(1, newData.getAge());
        preparedStatement.setString(2, newData.getName());
        preparedStatement.setString(3, newData.getEmail());
        preparedStatement.setString(4, newData.getAddress());

        preparedStatement.executeUpdate();
    }
}

package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.People;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataAccessObject {
    private final List<People> peopleList = new ArrayList<>();

    public DataAccessObject(People people) {
    }

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
        int currentMaxID;
        String updateSQL = "insert into people values(?,?,?,?,?)";
        String maxIdFromSQL = "select max(id) from people";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(maxIdFromSQL);
        if (resultSet.next()) {
            currentMaxID = resultSet.getInt(1);
        } else {
            currentMaxID = 0;
        }
        preparedStatement.setInt(1, currentMaxID + 1);
        preparedStatement.setInt(2, people.getAge());
        preparedStatement.setString(3, people.getName());
        preparedStatement.setString(4, people.getEmail());
        preparedStatement.setString(5, people.getAddress());

        preparedStatement.executeUpdate();
    }

    public People show(int id) throws SQLException {
        People p = new People();

        String sql = "select * from people where id = " + id;
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

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

    public void removeUserById(Integer id) {
        peopleList.removeIf(p -> p.getId() == id);
    }

    public void updateUserById(int id, People newData) {
        People person = peopleList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + id + " не найден."));
        person.copyUserInfoIntoNewPerson(newData);
    }
}

package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.People;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataAccessObject {
    private final List<People> peopleList = new ArrayList<>();
    private int CURRENT_ID = 0;

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
        List<People> peopleList = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sqlQuery = "select * from people";

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

    public void saveUser(People people) {

//        Statement statement = connection.
        people.setId(CURRENT_ID++);
        peopleList.add(people);
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

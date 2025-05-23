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

    // Затычка пока не подключена БД
//    {
//        peopleList.add(new People(CURRENT_ID, 27, "Егор Антипов", "egor@antipov.com", "Moscow"));
//        peopleList.add(new People(CURRENT_ID++, 31, "Анна Смирнова", "anna@smirnova.com", "Tomsk"));
//        peopleList.add(new People(CURRENT_ID++, 34, "Иван Козлов", "ivan@kozlov.com", "Moscow"));
//        peopleList.add(new People(CURRENT_ID++, 29, "Мария Соколова", "maria@sokolova.com", "Moscow"));
//        peopleList.add(new People(CURRENT_ID++, 38, "Алексей Орлов", "alexey@orlov.com", "Moscow"));
//        peopleList.add(new People(CURRENT_ID++, 26, "Ольга Белова", "olga@belova.com", "Moscow"));
//        peopleList.add(new People(CURRENT_ID++, 40, "Дмитрий Павлов", "dmitry@pavlov.com", "Moscow"));
//    }

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "postgres";

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
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
        people.setId(CURRENT_ID++);
        peopleList.add(people);
    }

    public People show(int id) {
        return peopleList.stream()
                .filter(people -> people.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + id + " не найден."));
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

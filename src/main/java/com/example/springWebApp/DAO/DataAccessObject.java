package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.Book;
import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataAccessObject {
    private final JdbcTemplate jdbcTemplate;

    /*
    ========================================================================
    =============================== People =================================
    ========================================================================
     */

    public List<People> index() {
        return jdbcTemplate.query("select * from people", new BeanPropertyRowMapper<>(People.class));
    }

    public void saveUser(People people) {
        String sql = "insert into people(age, name, email, address) values(?,?,?,?)";

        jdbcTemplate.update(sql,
                people.getAge(),
                people.getName(),
                people.getEmail(),
                people.getAddress());
    }

    public People showByID(int id) {
        String sql = "select * from people where id = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(People.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<People> showByEmail(String email) {
        String sql = "select * from people where email = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(People.class), email)
                .stream()
                .findAny();
    }

    public void removeUserById(Integer id) {
        String sql = "delete from people where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateUserById(int id, People newData) {
        String sql = "update people set age = ?, name = ?, email = ?, address = ? where id = ?";
        jdbcTemplate.update(sql, newData.getAge(), newData.getName(), newData.getEmail(), newData.getAddress(), id);
    }


    /*
    ========================================================================
    =============================== Books ==================================
    ========================================================================
     */

    public List<Book> indexBooks() {
        String sql = "select * from books";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBookById(int id) {
        String sql = "select * from books where id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }
}

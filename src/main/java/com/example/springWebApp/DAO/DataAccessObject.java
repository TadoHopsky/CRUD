package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataAccessObject {
    private final JdbcTemplate jdbcTemplate;

    public List<People> index() {
        return jdbcTemplate.query("select * from people", new BeanPropertyRowMapper<>(People.class));
    }

    public void saveUser(People people) {
        String sql = "insert into people values(?,?,?,?,?)";

        int id = findMaxId();

        jdbcTemplate.update(sql,
                id + 1,
                people.getAge(),
                people.getName(),
                people.getEmail(),
                people.getAddress());
    }

    public People show(int id) {
        String sql = "select * from people where id = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(People.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void removeUserById(Integer id) {
        String sql = "delete from people where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateUserById(int id, People newData) {
        String sql = "update people set age = ?, name = ?, email = ?, address = ? where id = ?";
        jdbcTemplate.update(sql, newData.getAge(), newData.getName(), newData.getEmail(), newData.getAddress(), id);
    }

    private int findMaxId() {
        String sql = "select max(id) from people";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        if (result == null) {
            return 0;
        }
        return result;
    }
}

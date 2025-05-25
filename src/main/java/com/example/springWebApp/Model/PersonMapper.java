package com.example.springWebApp.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<People> {
    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People p = new People();
        p.setId(rs.getInt("id"));
        p.setAge(rs.getInt("age"));
        p.setName(rs.getString("name"));
        p.setEmail(rs.getString("email"));
        p.setAddress(rs.getString("address"));
        return p;
    }
}

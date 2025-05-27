package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final JdbcTemplate jdbcTemplate;

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

    public Optional<Book> showBookByTitle(String title) {
        String sql = "select * from books where title = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), title)
                .stream()
                .findAny();
    }

    public void createNewBook(Book book) {
        String sql = "insert into books(author, title, age, people_id) values(?,?,?,?)";
        jdbcTemplate.update(sql, book.getAuthor(), book.getTitle(), book.getAge(), book.getPeople_id());
    }

    public void deleteBookById(int id) {
        String sql = "delete from books where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateBookById(int id, Book newData) {
        String sql = "update books set title = ?, author = ?, age = ?, people_id = ? where id = ?";
        jdbcTemplate.update(sql, newData.getTitle(), newData.getAuthor(), newData.getAge(), newData.getPeople_id(), id);
    }

    public List<Book> getBooksByUserId(int id) {
        String sql = "select * from books where people_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void assignBookToUser(int bookId, int userId) {
        String sql = "update books set people_id = ? where id = ?";
        jdbcTemplate.update(sql, userId, bookId);
    }

    public void setBookFree(Integer bookId) {
        String sql = "update books set people_id = null where id = ?";
        jdbcTemplate.update(sql, bookId);
    }
}


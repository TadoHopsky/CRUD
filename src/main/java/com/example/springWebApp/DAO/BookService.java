package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.Book;
import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final SessionFactory sessionFactory;

    public List<Book> indexBooks() {
        Session session = sessionFactory.openSession();
        List<Book> books;
        try {
            session.beginTransaction();
            books = session.createQuery("from Book", Book.class).list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return books;
    }

    public Book showBookById(Integer id) {
        Session session = sessionFactory.openSession();
        Book book;
        try {
            session.beginTransaction();
            book = session.find(Book.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return book;
    }

    public Optional<Book> showBookByTitle(String title) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Book book = session.createQuery("from Book where title = :title", Book.class)
                .setParameter("title", title)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();

        return Optional.ofNullable(book);
    }

    public void createNewBook(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(book);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBookById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.find(Book.class, id);
        if (book != null) {
            session.remove(book);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateBookById(int id, Book newData) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Book book = session.find(Book.class, id);
        if (book != null) {
            book.setAuthor(newData.getAuthor());
            book.setTitle(newData.getTitle());
            book.setAge(newData.getAge());
            session.merge(book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }

        session.getTransaction().commit();
        session.close();
    }

    public List<Book> getBooksByUserId(int id) {
        Session session = sessionFactory.openSession();
        List<Book> books;
        try {
            session.beginTransaction();
            books = session.createQuery("from Book where people.people_id = :id", Book.class)
                    .setParameter("id", id)
                    .list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return books;
    }

    public void assignBookToUser(int bookId, int userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.find(Book.class, bookId);
        People owner = session.find(People.class, userId);
        if (book != null && owner != null) {
            book.setPeople(owner);
            session.merge(book);
        } else {
            System.out.println("Book or People not found.");
        }
        session.getTransaction().commit();
        session.close();
    }

    public void setBookFree(Integer bookId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.find(Book.class, bookId);
        if (book != null) {
            book.setPeople(null);
            session.merge(book);
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
        session.getTransaction().commit();
        session.close();
    }
}

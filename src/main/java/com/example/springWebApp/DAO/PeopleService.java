package com.example.springWebApp.DAO;

import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final SessionFactory sessionFactory;

    public List<People> index() {
        Session session = sessionFactory.openSession();
        List<People> peopleList;
        try {
            session.beginTransaction();
            peopleList = session.createQuery("from People ", People.class).list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return peopleList;
    }

    public void saveUser(People people) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(people);
        session.getTransaction().commit();
        session.close();
    }

    public People showByID(Integer id) {
        Session session = sessionFactory.openSession();
        People people;
        try {
            session.beginTransaction();
            people = session.find(People.class, id);
            if (people != null) {
                System.out.println("=======================================================");
                System.out.println("People with ID " + id + ": " + people);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return people;
    }

    public Optional<People> showByEmail(String email) {
        Session session = sessionFactory.openSession();
        People people;
        try {
            session.beginTransaction();
            people = session.createQuery("from People where email = :email", People.class)
                    .setParameter("email", email)
                    .uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return Optional.ofNullable(people);
    }

    public void removeUserById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            People people = session.find(People.class, id);
            if (people != null) {
                session.remove(people);
            }
            session.getTransaction().commit();
        }
    }

    public void updateUserById(Integer id, People newData) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            People people = session.find(People.class, id);
            if (people != null) {
                if (!people.getName().equals(newData.getName())) {
                    people.setName(newData.getName());
                }
                if (!people.getEmail().equals(newData.getEmail())) {
                    people.setEmail(newData.getEmail());
                }
                if (!people.getAddress().equals(newData.getAddress())) {
                    people.setAddress(newData.getAddress());
                }
                if (!people.getAge().equals(newData.getAge())) {
                    people.setAge(newData.getAge());
                }
                session.merge(people);
            }
            session.getTransaction().commit();
        }
    }
}
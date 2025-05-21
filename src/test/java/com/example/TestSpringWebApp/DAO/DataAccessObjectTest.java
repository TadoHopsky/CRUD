package com.example.TestSpringWebApp.DAO;

import com.example.springWebApp.DAO.DataAccessObject;
import com.example.springWebApp.Model.People;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessObjectTest {

    private DataAccessObject dao;

    @BeforeEach
    void setUp() {
        dao = new DataAccessObject(new People());
    }

    @Test
    void testIndex() {
        List<People> peopleList = dao.index();
        assertNotNull(peopleList);
        assertFalse(peopleList.isEmpty());
    }

    @Test
    void testShow() {
        People person = dao.show(0);
        assertNotNull(person);
        assertEquals("Егор Антипов", person.getName());
    }

    @Test
    void testSaveUser() {
        People newPerson = new People(0, 25, "Иван Иванов", "ivan@ivanov.com", "Moscow");
        dao.saveUser(newPerson);
        assertEquals(8, dao.index().size());
        assertEquals("Иван Иванов", dao.index().get(7).getName());
    }

    @Test
    void testRemoveUserById() {
        dao.removeUserById(0);
        assertEquals(5, dao.index().size());
    }

    @Test
    void testUpdateUserById() {
        People updatedPerson = new People(0, 35, "Иван Иванов", "ivan@ivanov.com", "Moscow");
        dao.updateUserById(0, updatedPerson);
        People person = dao.show(0);
        assertEquals("Иван Иванов", person.getName());
        assertEquals(35, person.getAge());
    }
}

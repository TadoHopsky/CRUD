package com.example.sprintwebapp.Model;

import com.example.springWebApp.Model.People;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeopleTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidPeople() {
        People person = new People(1, 30, "Иван Иванов", "ivan@ivanov.com", "Moscow");
        Set violations = validator.validate(person);
        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidAge() {
        People person = new People(1, 150, "Иван Иванов", "ivan@ivanov.com", "Moscow");
        Set violations = validator.validate(person);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidEmail() {
        People person = new People(1, 30, "Иван Иванов", "invalid-email", "Moscow");
        Set violations = validator.validate(person);
        assertEquals(1, violations.size());
    }

    @Test
    void testEmptyName() {
        People person = new People(1, 30, "", "ivan@ivanov.com", "Moscow");
        Set violations = validator.validate(person);
        assertEquals(1, violations.size());
    }
}

package com.example.springWebApp.util;


import com.example.springWebApp.DAO.DataAccessObject;
import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final DataAccessObject dataAccessObject;

    @Override
    public boolean supports(Class<?> clazz) {
        return People.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        People people = (People) target;

        if (dataAccessObject.showByEmail(people.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}

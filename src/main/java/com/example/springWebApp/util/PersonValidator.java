package com.example.springWebApp.util;


import com.example.springWebApp.DAO.PeopleService;
import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return People.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        People people = (People) target;

        Optional<People> existingUser = peopleService.showByEmail(people.getEmail());

        System.out.println(people.getEmail());

        if (existingUser.isPresent() && !existingUser.get().getPeople_id().equals(people.getPeople_id())) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}

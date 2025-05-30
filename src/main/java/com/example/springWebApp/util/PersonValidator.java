package com.example.springWebApp.util;


import com.example.springWebApp.DAO.PeopleService;
import com.example.springWebApp.Model.People;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

/*
 *   Переписать нахуй это гавно
 *   Валидация не работает
 */

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

        System.out.println(people.isEditMode);

        if (!people.getIsEditMode()) {
            Optional<People> existingUser = peopleService.showByEmail(people.getEmail());
            System.out.println(people.getEmail());

            if (existingUser.isPresent()) {
                errors.rejectValue("email", "", "This email is already taken");
            }
        } else {
            List<People> peopleList = peopleService.index();
            for (People p : peopleList) {
                if (p.getEmail().equals(people.getEmail())) {
                    errors.rejectValue("email", "", "This email is already taken");
                }
            }
        }
    }
}

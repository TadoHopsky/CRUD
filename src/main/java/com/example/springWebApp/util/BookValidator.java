package com.example.springWebApp.util;

import com.example.springWebApp.DAO.BookService;
import com.example.springWebApp.Model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {
    private final BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookService.showBookByTitle(book.getTitle()).isPresent()) {
            errors.rejectValue("title", "", "Это название уже существует");
        }
    }
}

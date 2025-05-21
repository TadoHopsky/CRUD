package com.example.springWebApp.controller.exceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException e, Model model) {
        model.addAttribute("error",
                "Некорректный идентификатор пользователя в адресе: " + e.getValue());
        return "error";
    }
}

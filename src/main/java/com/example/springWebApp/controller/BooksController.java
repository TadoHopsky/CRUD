package com.example.springWebApp.controller;

import com.example.springWebApp.DAO.DataAccessObject;
import com.example.springWebApp.Model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final DataAccessObject dataAccessObject;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", dataAccessObject.indexBooks());
        return "booksViews/list";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable int id, Model model) {
        model.addAttribute("book", dataAccessObject.showBookById(id));
        return "booksViews/show";
    }
}

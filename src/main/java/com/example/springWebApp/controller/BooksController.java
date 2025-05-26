package com.example.springWebApp.controller;

import com.example.springWebApp.DAO.DataAccessObject;
import com.example.springWebApp.Model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "booksViews/new";
    }

    @PostMapping("/new")
    public String createNewBook(@ModelAttribute("book") Book book) { // Дописать валидацию
        dataAccessObject.createNewBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable int id, Model model) {
        model.addAttribute("book", dataAccessObject.showBookById(id));
        return "booksViews/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable int id) {
        dataAccessObject.updateBookById(id, book);
        return "redirect:/books/list";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        dataAccessObject.deleteBookById(id);
        return "redirect:/books/list";
    }
}

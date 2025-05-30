package com.example.springWebApp.controller;

import com.example.springWebApp.DAO.BookService;
import com.example.springWebApp.DAO.PeopleService;
import com.example.springWebApp.Model.Book;
import com.example.springWebApp.util.BookValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private final BookValidator bookValidator;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.indexBooks());
        return "booksViews/list";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable int id, Model model) {
        Book book = bookService.showBookById(id);
        model.addAttribute("book", book);

        if (book.getPeople() != null) {
            model.addAttribute("userInModel", book.getPeople());
        }

        model.addAttribute("allUsers", peopleService.index());
        return "booksViews/show";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable int id) {
        bookService.setBookFree(id);
        return "redirect:/books/list";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("allUsers", peopleService.index());
        return "booksViews/new";
    }

    @PostMapping("/new")
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "booksViews/new";
        }
        bookService.createNewBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.showBookById(id));
        model.addAttribute("allUsers", peopleService.index());
        return "booksViews/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "booksViews/edit";
        }
        bookService.updateBookById(id, book);
        return "redirect:/books/list";
    }

    @PostMapping("/{id}/assign")
    public String assignBookToUser(@PathVariable int id, @RequestParam("userId") int userId) {
        bookService.assignBookToUser(id, userId);
        return "redirect:/books/list";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return "redirect:/books/list";
    }
}

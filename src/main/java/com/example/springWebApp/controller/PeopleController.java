package com.example.springWebApp.controller;

import com.example.springWebApp.DAO.BookService;
import com.example.springWebApp.DAO.PeopleService;
import com.example.springWebApp.Model.People;
import com.example.springWebApp.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private final PersonValidator personValidator;

    @GetMapping("/all")
    public String index(Model model) {
        model.addAttribute("allUsers", peopleService.index());
        return "people";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("people", new People());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("people") @Valid People people, BindingResult bindingResult) {
        personValidator.validate(people, bindingResult);
        if (bindingResult.hasErrors()) {
            return "new";
        }
        peopleService.saveUser(people);
        return "redirect:/people/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("people", peopleService.showByID(id));
        model.addAttribute("booksForUser", bookService.getBooksByUserId(id));
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("people", peopleService.showByID(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("people") @Valid People people, BindingResult bindingResult,
                         @PathVariable Integer id) {
        personValidator.validate(people, bindingResult);
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        peopleService.updateUserById(id, people);
        return "redirect:/people/all";
    }

    @DeleteMapping("remove/{id}")
    public String remove(@PathVariable Integer id) {
        peopleService.removeUserById(id);
        return "redirect:/people/all";
    }
}

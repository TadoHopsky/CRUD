package com.example.springWebApp.controller;

import com.example.springWebApp.DAO.DataAccessObject;
import com.example.springWebApp.Model.People;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final DataAccessObject dataAccessObject;
    
    @GetMapping("/all")
    public String index(Model model) throws SQLException {
        model.addAttribute("allUsers", dataAccessObject.index());
        return "people";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("people", new People());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("people") @Valid People people, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        dataAccessObject.saveUser(people);
        return "redirect:/people/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) throws SQLException {
        model.addAttribute("people", dataAccessObject.show(id));
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) throws SQLException {
        model.addAttribute("people", dataAccessObject.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("people") @Valid People people, BindingResult bindingResult,
                         @PathVariable Integer id) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        dataAccessObject.updateUserById(id, people);
        return "redirect:/people/all";
    }

    @DeleteMapping("remove/{id}")
    public String remove(@PathVariable Integer id) throws SQLException {
        dataAccessObject.removeUserById(id);
        return "redirect:/people/all";
    }
}

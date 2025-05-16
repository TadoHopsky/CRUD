package com.example.sprintwebapp.controller;

import com.example.sprintwebapp.DAO.DataAcscessObject;
import com.example.sprintwebapp.Model.People;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class MainController {
    private final DataAcscessObject dataAcscessObject;

    public MainController(DataAcscessObject dataAcscessObject) {
        this.dataAcscessObject = dataAcscessObject;
    }

    @GetMapping("/all")
    public String index(Model model) {
        model.addAttribute("allUsers", dataAcscessObject.index());
        return "people";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("people", dataAcscessObject.newUser());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("people") People people) {
        dataAcscessObject.save(people);
        return "redirect:/people/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("people", dataAcscessObject.show(id));
        return "show";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Integer id) {
        dataAcscessObject.remove(id);
        return "redirect:/people/all";
    }


    //======================= Error Handling ==========================

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}

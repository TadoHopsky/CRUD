package com.example.sprintwebapp.controller;

import com.example.sprintwebapp.DAO.DataAcscessObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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

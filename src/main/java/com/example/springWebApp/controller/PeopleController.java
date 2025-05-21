package com.example.sprintwebapp.controller;

import com.example.sprintwebapp.DAO.dataAccessObject;
import com.example.sprintwebapp.Model.People;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final dataAccessObject dataAccessObject;

    public PeopleController(dataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    @GetMapping("/all")
    public String index(Model model) {
        model.addAttribute("allUsers", dataAccessObject.index());
        return "people";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("people", new People());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("people") @Valid People people, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        dataAccessObject.saveUser(people);
        return "redirect:/people/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("people", dataAccessObject.show(id));
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("people", dataAccessObject.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("people") @Valid People people, BindingResult bindingResult,
                         @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        dataAccessObject.updateUserById(id, people);
        return "redirect:/people/all";
    }

    @DeleteMapping("remove/{id}")
    public String remove(@PathVariable Integer id) {
        dataAccessObject.removeUserById(id);
        return "redirect:/people/all";
    }


    //================= HiddenHttpMethodFilter ========================
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    //======================= Error Handling ==========================
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException e, Model model) {
        model.addAttribute("error", "Некорректный идентификатор пользователя в адресе: " + e.getValue());
        return "error";
    }
}

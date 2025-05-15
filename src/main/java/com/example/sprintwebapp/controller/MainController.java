package com.example.sprintwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/hello")
public class MainController {
    @GetMapping("/first")
    public String firstMethod(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "surname", required = false) String surname,
                              Model model) {
        if (name != null && surname != null) {
            System.out.println("Hello " + name + " " + surname);
            model.addAttribute("seyHelloMethod", "Hello " + name + " " + surname);
        }
        return "/hello";
    }

    @GetMapping("/goodbye")
    public String secondMethod() {
        return "/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "firstNumber", required = false) int firstNumber,
                             @RequestParam(value = "secondNumber", required = false) int secondNumber,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        double result = 0;
        switch (action) {
            case "add":
                result = firstNumber + secondNumber;
                break;
            case "subtract":
                result = firstNumber - secondNumber;
                break;
            case "multiply":
                result = firstNumber * secondNumber;
                break;
            case "divide":
                result = (double) firstNumber / secondNumber;
                break;
            default:
                result = 0;
        }
        model.addAttribute("calculation", result);

        return "/calculator";
    }
}

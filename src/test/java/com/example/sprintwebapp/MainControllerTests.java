package com.example.sprintwebapp;

import com.example.sprintwebapp.controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFirstMethod() throws Exception {
        mockMvc.perform(get("/hello/first")
                        .param("name", "Tado")
                        .param("surname", "Hopsky"))
                .andExpect(status().isOk())
                .andExpect(view().name("/hello"))
                .andExpect(model().attribute("seyHelloMethod", "Hello Tado Hopsky"));
    }

    @Test
    void testSecondMethod() throws Exception {
        mockMvc.perform(get("/hello/goodbye"))
                .andExpect(status().isOk())
                .andExpect(view().name("/goodbye"));
    }

    @Test
    void testCalculatorAdd() throws Exception {
        mockMvc.perform(get("/hello/calculator")
                        .param("firstNumber", "10")
                        .param("secondNumber", "5")
                        .param("action", "add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/calculator"))
                .andExpect(model().attribute("calculation", 15.0));
    }

    @Test
    void testCalculatorSubtract() throws Exception {
        mockMvc.perform(get("/hello/calculator")
                        .param("firstNumber", "10")
                        .param("secondNumber", "5")
                        .param("action", "subtract"))
                .andExpect(status().isOk())
                .andExpect(view().name("/calculator"))
                .andExpect(model().attribute("calculation", 5.0));
    }

    @Test
    void testCalculatorMultiply() throws Exception {
        mockMvc.perform(get("/hello/calculator")
                        .param("firstNumber", "10")
                        .param("secondNumber", "5")
                        .param("action", "multiply"))
                .andExpect(status().isOk())
                .andExpect(view().name("/calculator"))
                .andExpect(model().attribute("calculation", 50.0));
    }

    @Test
    void testCalculatorDivide() throws Exception {
        mockMvc.perform(get("/hello/calculator")
                        .param("firstNumber", "10")
                        .param("secondNumber", "5")
                        .param("action", "divide"))
                .andExpect(status().isOk())
                .andExpect(view().name("/calculator"))
                .andExpect(model().attribute("calculation", 2.0));
    }

    @Test
    void testCalculatorInvalidAction() throws Exception {
        mockMvc.perform(get("/hello/calculator")
                        .param("firstNumber", "10")
                        .param("secondNumber", "5")
                        .param("action", "invalid"))
                .andExpect(status().isOk())
                .andExpect(view().name("/calculator"))
                .andExpect(model().attribute("calculation", 0.0));
    }
}

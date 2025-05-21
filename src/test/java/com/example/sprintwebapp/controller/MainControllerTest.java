package com.example.sprintwebapp.controller;

import com.example.sprintwebapp.DAO.dataAccessObject;
import com.example.sprintwebapp.Model.People;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private dataAccessObject dataAccessObject;

    private List<People> mockPeopleList;

    @BeforeEach
    void setUp() {
        mockPeopleList = List.of(
                new People(1, 27, "Егор Антипов", "egor@antipov.com", "Moscow"),
                new People(2, 31, "Анна Смирнова", "anna@smirnova.com", "Tomsk")
        );

        Mockito.when(dataAccessObject.index()).thenReturn(mockPeopleList);
        Mockito.when(dataAccessObject.show(anyInt())).thenAnswer(invocation -> {
            int id = invocation.getArgument(0);
            return mockPeopleList.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Пользователь с ID " + id + " не найден."));
        });
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/people/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("people"))
                .andExpect(model().attributeExists("allUsers"));
    }

    @Test
    void testShow() throws Exception {
        mockMvc.perform(get("/people/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("show"))
                .andExpect(model().attributeExists("people"))
                .andExpect(model().attribute("people", mockPeopleList.get(0)));
    }

    @Test
    void testNewUser() throws Exception {
        mockMvc.perform(get("/people/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("new"))
                .andExpect(model().attributeExists("people"));
    }

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/people/new")
                        .param("name", "Иван Иванов")
                        .param("age", "30")
                        .param("email", "ivan@ivanov.com")
                        .param("address", "Moscow"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/people/all"));
    }

    @Test
    void testEdit() throws Exception {
        mockMvc.perform(get("/people/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("people"))
                .andExpect(model().attribute("people", mockPeopleList.get(0)));
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(patch("/people/1")
                        .param("name", "Иван Иванов")
                        .param("age", "30")
                        .param("email", "ivan@ivanov.com")
                        .param("address", "Moscow"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/people/all"));
    }

    @Test
    void testRemove() throws Exception {
        mockMvc.perform(delete("/people/remove/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/people/all"));
    }
}

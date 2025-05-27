package com.example.springWebApp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;

    @NotEmpty
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+",
            message = "Имя автора должно быть в формате: Имя Отчество Фамилия")
    private String author;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String title;

    @NotNull
    @Min(0)
    @Max(2025)
    private int age;

    private Integer people_id;
}


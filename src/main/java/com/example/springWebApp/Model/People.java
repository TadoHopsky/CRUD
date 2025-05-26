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
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private int id;

    @NotNull(message = "Can not be null:(")
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 110, message = "Age must be less than 110")
    private int age;

    @NotEmpty(message = "Can not be empty:(")
    @Size(min = 3, max = 40)
    private String name;

    @NotEmpty(message = "Can not be empty:(")
    @Size(min = 3, max = 40)
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Can not be empty:(")
    @Pattern(regexp = "[А-ЯA-Z][а-яА-Яa-zA-ZёЁ]+\\s*,\\s*[А-ЯA-Z][а-яА-Яa-zA-ZёЁ]+\\s*,\\s*\\d+",
            message = "Address must be in the format: City, State, house number")
    @Size(min = 3, max = 40)
    private String address;
}

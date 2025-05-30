package com.example.springWebApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer book_id;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String author;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String title;

    @NotNull
    @Min(0)
    @Max(2025)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "people_id", referencedColumnName = "people_id")
    private People people;

    public Book(People people, Integer age, String title, String author, Integer book_id) {
        this.people = people;
        this.age = age;
        this.title = title;
        this.author = author;
        this.book_id = book_id;
    }
}

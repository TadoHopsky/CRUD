package com.example.springWebApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class People {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer people_id;


    @Column(name = "people_age")
    private Integer age;


    @Column(name = "people_name")
    private String name;


    @Column(name = "email")
    private String email;

    @Column(name = "people_address")
    private String address;

    @Transient
    public Boolean isEditMode;
}

package com.example.sprintwebapp.DAO;

import com.example.sprintwebapp.Model.People;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataAcscessObject {
    private final People people;

    public DataAcscessObject(People people) {
        this.people = people;
    }

    List<People> peopleList;

    {
        peopleList = List.of(
                new People(1, "John Doe", "john123123@mail.ru", "Moscow"),
                new People(2, "Tado Hopsky", "tadohopsky@mail.ru", "Moscow"),
                new People(3, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
    }

    public List<People> index() {
        return peopleList;
    }

    public People show(int id) {
        return peopleList.stream().filter(people -> people.getId() == id).findFirst().orElse(null);
    }


}

package com.example.sprintwebapp.DAO;

import com.example.sprintwebapp.Model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataAcscessObject {

    @Autowired
    public DataAcscessObject(People people) {
    }

    List<People> peopleList = new ArrayList<>();

    {
        peopleList.add(new People(0, "John Doe", "john123123@mail.ru", "Moscow"));
        peopleList.add(new People(1, "Tado Hopsky", "tadohopsky@mail.ru", "Tomsk"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(0, "John Doe", "john123123@mail.ru", "Moscow"));
        peopleList.add(new People(1, "Tado Hopsky", "tadohopsky@mail.ru", "Tomsk"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(0, "John Doe", "john123123@mail.ru", "Moscow"));
        peopleList.add(new People(1, "Tado Hopsky", "tadohopsky@mail.ru", "Tomsk"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));

    }

    public People newUser() {
        return new People();
    }

    public List<People> index() {
        return peopleList;
    }

    public People show(int id) {
        return peopleList.stream()
                .filter(people -> people.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + id + " не найден."));
    }

    public void remove(int id) {
        boolean removed = peopleList.removeIf(people -> people.getId() == id);
        if (!removed) {
            throw new RuntimeException("Пользователь с ID " + id + " не найден.");
        }
    }

    public void save(People people) {
        // Назначаем уникальный ID (максимальный + 1)
        int nextId = peopleList.stream()
                .mapToInt(People::getId)
                .max()
                .orElse(0) + 1;
        people.setId(nextId);
        peopleList.add(people);
    }
}

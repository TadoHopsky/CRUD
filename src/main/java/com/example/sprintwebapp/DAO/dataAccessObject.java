package com.example.sprintwebapp.DAO;

import com.example.sprintwebapp.Model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class dataAccessObject {

    @Autowired
    public dataAccessObject(People people) {
    }

    List<People> peopleList = new ArrayList<>();
    int CURRENT_ID = 0;

    {
        peopleList.add(new People(CURRENT_ID, "John Doe", "john123123@mail.ru", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Tado Hopsky", "tadohopsky@mail.ru", "Tomsk"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));

    }

    public People newUser() {
        return new People();
    }

    public void saveUser(People people) {
        people.setId(CURRENT_ID++);
        peopleList.add(people);
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

    public void removeUserById(Integer id) {
        peopleList.removeIf(p -> p.getId() == id);
    }

    public void updateUserById(int id, People newData) {
        peopleList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        p -> {
                            p.setName(newData.getName());
                            p.setEmail(newData.getEmail());
                            p.setAddress(newData.getAddress());
                        },
                        () -> {
                            throw new RuntimeException("Пользователь с ID " + id + " не найден.");
                        }
                );
    }
}

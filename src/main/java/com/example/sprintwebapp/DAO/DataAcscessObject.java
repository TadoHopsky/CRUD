package com.example.sprintwebapp.DAO;

import com.example.sprintwebapp.Model.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataAcscessObject {
    private final People people;

    public DataAcscessObject(People people) {
        this.people = people;
    }

    List<People> peopleList = new ArrayList<>();

    {
        peopleList.add(new People(0, "John Doe", "john123123@mail.ru", "Moscow"));
        peopleList.add(new People(1, "Tado Hopsky", "tadohopsky@mail.ru", "Tomsk"));
        peopleList.add(new People(2, "Masha Ivanova", "masha13241@gmail.com", "Moscow"));
    }

    public List<People> index() {
        return peopleList;
    }

    public People show(int id) {
        return peopleList.stream().filter(people -> people.getId() == id).findFirst().orElse(null);
    }

    public void remove(int id) {
        try {
            for (People people : peopleList) {
                if (people.getId() == id) {
                    peopleList.remove(people);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении пользователя с ID: " + id, e);
        }

    }
}

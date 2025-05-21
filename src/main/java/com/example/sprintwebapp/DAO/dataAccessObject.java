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
        peopleList.add(new People(CURRENT_ID, 27, "Егор Антипов", "egor@antipov.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, 31, "Анна Смирнова", "anna@smirnova.com", "Tomsk"));
        peopleList.add(new People(CURRENT_ID++, 34, "Иван Козлов", "ivan@kozlov.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, 29, "Мария Соколова", "maria@sokolova.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, 38, "Алексей Орлов", "alexey@orlov.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, 26, "Ольга Белова", "olga@belova.com", "Moscow"));
        peopleList.add(new People(CURRENT_ID++, 40, "Дмитрий Павлов", "dmitry@pavlov.com", "Moscow"));
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
                            p.setAge(newData.getAge());
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

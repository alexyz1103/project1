package ru.kost.project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2,max = 50,message = "ФИО должно должно содержать от 2 до 30 символов")

    private String fio;

    //@Min(value = 1900, message = "Это проверка в анотацииГод рождения дожен быть больше 1900")
    private int year_birth;

    public Person(int id, String fio, int year_birth) {
        this.id = id;
        this.fio = fio;
        this.year_birth = year_birth;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear_birth() {
        return year_birth;
    }

    public void setYear_birth(int year_birth) {
        this.year_birth = year_birth;
    }
}

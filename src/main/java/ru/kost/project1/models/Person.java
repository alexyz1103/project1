package ru.kost.project1.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class Person {
    private int id;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2,max = 50,message = "ФИО должно должно содержать от 2 до 30 символов")
    private String fio;
//    Calendar cal = Calendar.getInstance();
//    private final int MAX_YEAR = cal.get(Calendar.YEAR);
    @Min(value = 1900, message = "Год рождения дожен быть больше 1900")
    @Max(value = 2022, message = "Год рождения не должен превышать текущий год")
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

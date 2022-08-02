package ru.kost.project1.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Books {
    private int book_id;
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название книги должно быть не меньше 2х символов, и не превышать 50 символов")
    private String book_name;
    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя автора должно быть не меньше 2х символов, и не превышать 50 символов")
    private String book_autor;
    @Max(value = 2023, message = "Дата публикации не должна быть больше текущего года")
    private int book_year;

    public Books() {
    }

    public Books(int book_id, String book_name, String book_autor, int book_year) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_autor = book_autor;
        this.book_year = book_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_autor() {
        return book_autor;
    }

    public void setBook_autor(String book_autor) {
        this.book_autor = book_autor;
    }

    public int getBook_year() {
        return book_year;
    }

    public void setBook_year(int book_year) {
        this.book_year = book_year;
    }
}

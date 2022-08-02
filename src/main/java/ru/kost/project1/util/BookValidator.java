package ru.kost.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kost.project1.DAO.BookDAO;
import ru.kost.project1.models.Books;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Books.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Books books = (Books) target;

        if(bookDAO.show(books.getBook_name(), books.getBook_id()).isPresent()){
            errors.rejectValue("book_name","","Книга с таким названием уже есть в базе данных");
        }
    }
}

package ru.kost.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kost.project1.DAO.BookDAO;
import ru.kost.project1.models.Books;
import ru.kost.project1.util.BookValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    //Связь с БД через ДАО и его методы
    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    @Autowired
    public BooksController(BookDAO bookDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }
//метод контролера для отображения всех книг
    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
//метод контроллера для отображения одной книги
    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, Model model){
        model.addAttribute("book", bookDAO.show(book_id));
        return "books/show";
    }
//    метод контроллера для отображения формы добаления новой книги в БД
    @GetMapping("/new")
    public  String newBook(@ModelAttribute("book") Books books){  return "books/new";   }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Books books,
                         BindingResult bindingResult){
        bookValidator.validate(books, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(books);
        return "redirect:/books";
    }

    @GetMapping("/{book_id}/edit")
    public String editBook(Model model, @PathVariable("book_id") int book_id){
        model.addAttribute("book", bookDAO.show(book_id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") @Valid Books book,
                             BindingResult bindingResult,
                             @PathVariable("book_id") int book_id){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(book_id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id){
        bookDAO.delete(book_id);
        return "redirect:/books";
    }

}

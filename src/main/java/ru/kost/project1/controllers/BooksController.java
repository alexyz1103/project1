package ru.kost.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kost.project1.DAO.BookDAO;
import ru.kost.project1.DAO.PersonDAO;
import ru.kost.project1.models.Books;
import ru.kost.project1.models.Person;
import ru.kost.project1.util.BookValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    //Связь с БД через ДАО и его методы
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
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
    public String show(@PathVariable("book_id") int book_id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(book_id));

        Optional<Person> bookOwner = bookDAO.bookOwner(book_id);
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        }
        else{
            model.addAttribute("people", personDAO.index());
        }
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

    @PatchMapping("/{book_id}/assign")
    public String assign(@PathVariable("book_id") int book_id,
                         @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assign(book_id, selectedPerson);
        return "redirect:/books/" + book_id;
    }
    @PatchMapping("/{book_id}/release")
    public String release(@PathVariable("book_id") int book_id) {
        bookDAO.release(book_id);
        return "redirect:/books/" + book_id;
    }

}

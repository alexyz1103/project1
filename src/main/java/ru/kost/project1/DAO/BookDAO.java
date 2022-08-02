package ru.kost.project1.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kost.project1.models.Books;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    //обращение к БД

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //отображение всех книг
    public List<Books> index(){
        return jdbcTemplate.query("SELECT * FROM book ORDER BY book_id",
                new BeanPropertyRowMapper<>(Books.class));
    }
    //отображение одной книги
    public Books show(int book_id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[] {book_id},
                new BeanPropertyRowMapper<>(Books.class)).stream().findAny().orElse(null);
    }

    public Optional<Books> show(String book_name, int book_id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_name=? AND book_id!=?",
                new Object[] {book_name, book_id},
                new BeanPropertyRowMapper<>(Books.class)).stream().findAny();
    }
    // сохранение в БД книги
    public void save(Books books) {
        jdbcTemplate.update("INSERT INTO book(book_name, book_autor, book_year) VALUES (?,?,?)",
                books.getBook_name(), books.getBook_autor(),books.getBook_year());
    }
    //обновление данных о книге
    public void update(int book_id, Books updatedBook) {
        jdbcTemplate.update("UPDATE book SET book_name=?,book_autor=?, book_year=? WHERE book_id=?",
                updatedBook.getBook_name(), updatedBook.getBook_autor(), updatedBook.getBook_year(), book_id);
    }
    //Удаление из БД книги
    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", book_id);
    }
}

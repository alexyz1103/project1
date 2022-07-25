package ru.kost.project1.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kost.project1.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    //Обращение к БД
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fio){
        return jdbcTemplate.query("SELECT * FROM person WHERE fio=?", new Object[] {fio},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(fio, year_birth) VALUES (?,?)",
                person.getFio(), person.getYear_birth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET fio=?,year_birth=? WHERE id=?",
                updatedPerson.getFio(),updatedPerson.getYear_birth(), id);
    }

//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
//    }
//

}

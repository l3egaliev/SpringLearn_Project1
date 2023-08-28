package kg.rakhim.classes.dao;

import kg.rakhim.classes.model.Book;
import kg.rakhim.classes.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("Select * from people", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> showFullName(String fullName){
        return jdbcTemplate.query("SELECT * FROM people where fullName = ?", new Object[]{fullName}, new BeanPropertyRowMapper
                <>(Person.class)).stream().findAny();
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * from people where person_id = ?", new Object[]{id} ,new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }


    public void save(Person person){
        jdbcTemplate.update("INSERT INTO people(fullName, yearOfBirth) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE people set fullName = ?, yearOfBirth = ? where person_id = ?",
                updatedPerson.getFullName(),updatedPerson.getYearOfBirth(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE person_id = ?", id);
    }


    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("select books.* from people join books on books.person_id = people.person_id " +
                        "where people.person_id =?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}

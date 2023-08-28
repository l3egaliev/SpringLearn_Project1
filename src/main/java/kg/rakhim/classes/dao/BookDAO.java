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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("Select * from books", new BeanPropertyRowMapper<>(Book.class));
    }



    public Book show(int id){
        return jdbcTemplate.query("SELECT * from books where book_id = ?", new Object[]{id} ,new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public void save(Book book){
        jdbcTemplate.update("INSERT INTO books(title,author, year) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from books where book_id = ?", id);
    }

    public void edit(int id, Book book) {
        jdbcTemplate.update("update books set title = ?, author = ?, year = ? where book_id = ?", book.getTitle()
        , book.getAuthor(), book.getYear(),id);
    }

    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("select people.* from books join people on books.person_id = people.person_id " +
                        "where books.book_id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream()
                .findAny();
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE books set person_id = null where book_id = ?", id);
    }

    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("update books set person_id=? where book_id =?", selectedPerson.getPerson_id(), id);
    }
}

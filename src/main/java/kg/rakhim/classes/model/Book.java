package kg.rakhim.classes.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Book {
    private Integer book_id;
    private Integer person_id;
    @NotEmpty(message = "Пишите название книги")
    private String title;
    @NotEmpty(message = "Имя автора!")
    private String author;
    @Min(value = 1900, message = "Книги не ранее 1900 года")
    private Integer year;

    public Book() {
    }

    public Book(Integer book_id, Integer person_id, String title, String author, Integer year) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title= title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}

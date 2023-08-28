package kg.rakhim.classes.controllers;


import jakarta.validation.Valid;
import kg.rakhim.classes.dao.BookDAO;
import kg.rakhim.classes.dao.PeopleDAO;
import kg.rakhim.classes.model.Book;
import kg.rakhim.classes.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PeopleDAO peopleDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PeopleDAO peopleDAO){
        this.bookDAO = bookDAO;
        this.peopleDAO = peopleDAO;
    }

    @GetMapping
    public String index(Model model){
        List<Book> index = bookDAO.index();
        model.addAttribute("books", index);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(id));

        if(bookDAO.getBookOwner(id).isPresent()){
            Optional<Person> owner = bookDAO.getBookOwner(id);
            model.addAttribute("owner", owner.get());
        }else {
            model.addAttribute("people",peopleDAO.index());
        }

        return "books/show";
    }
//    @PatchMapping("{id}")
//    public String makePerson(@PathVariable("id") int id, @ModelAttribute("book") Book book){
//        book.setPerson_id(id);
//        return "redirect:/books";
//    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("updatedBook", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("{id}")
    public String edit(@PathVariable("id") int id, @Valid Book book,
                       BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.edit(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person")Person selectedPerson){
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/"+id;
    }

}

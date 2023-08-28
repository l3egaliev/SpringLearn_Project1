package kg.rakhim.classes.controllers;


import jakarta.validation.Valid;
import kg.rakhim.classes.dao.PeopleDAO;
import kg.rakhim.classes.model.Person;
import kg.rakhim.classes.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;
    private PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, PersonValidator personValidator) {
        this.peopleDAO = peopleDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDAO.show(id));
        model.addAttribute("books", peopleDAO.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPeople(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("UpdatePerson", peopleDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("UpdatePerson") @Valid Person person,
                               BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleDAO.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}

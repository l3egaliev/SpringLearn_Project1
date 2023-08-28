package kg.rakhim.classes.controllers;

import kg.rakhim.classes.dao.PeopleDAO;
import kg.rakhim.classes.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin ")
public class AdminController {
    private final PeopleDAO peopleDAO;

    @Autowired
    public AdminController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping
    public String adminPage(@ModelAttribute("person")Person person, Model model){
        model.addAttribute("people", peopleDAO.index());
        return "admin";
    }

    @PatchMapping("/add")
    public String admin(@ModelAttribute("person") Person person){
        System.out.println(person.getPerson_id());
        return "redirect:/people";
    }
}

package kg.rakhim.classes.util;

import kg.rakhim.classes.dao.PeopleDAO;
import kg.rakhim.classes.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleDAO peopleDAO;

    @Autowired
    public PersonValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleDAO.showFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Имена уникальные!");
    }
}

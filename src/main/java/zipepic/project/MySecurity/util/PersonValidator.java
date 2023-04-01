package zipepic.project.MySecurity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import zipepic.project.MySecurity.models.Person;
import zipepic.project.MySecurity.services.PersonService;
@Component
public class PersonValidator implements Validator {
    private final PersonService personService;
    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personService.tryLoadByName(person.getUsername()).isPresent()){
            errors.rejectValue("username","","Человек с таким именем уже есть!");
        }
    }
}

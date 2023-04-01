package zipepic.project.MySecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zipepic.project.MySecurity.models.Person;
import zipepic.project.MySecurity.services.PersonService;
import zipepic.project.MySecurity.util.PersonValidator;

@Controller
@RequestMapping("auth")
public class AuthController {
    private final PersonService personService;
    private final PersonValidator personValidator;
    @Autowired
    public AuthController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String login_page(){
        return "oauth_login";
    }
    @GetMapping("/registration")
    public String addNewPerson(@ModelAttribute("person") Person person){
        //personService.addPerson(new Person(username,"mail@mail.ru",password));
        return "page";
    }
    @PostMapping("/registration")
    public String reg(@ModelAttribute("person") @Valid Person person,
                      BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "page";
        personService.register(person);
        return "redirect:/auth/login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}

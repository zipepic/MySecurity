package zipepic.project.MySecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zipepic.project.MySecurity.models.Person;
import zipepic.project.MySecurity.repositories.PeopleRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public PersonService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
    public Optional<Person> tryLoadByName(String name){
        return peopleRepository.findByUsername(name);
    }
}

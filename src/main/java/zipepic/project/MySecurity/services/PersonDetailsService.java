package zipepic.project.MySecurity.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zipepic.project.MySecurity.models.Person;
import zipepic.project.MySecurity.repositories.PeopleRepository;
import zipepic.project.MySecurity.security.PersonDetails;

import java.util.Optional;

@Service
@NoArgsConstructor
public class PersonDetailsService implements UserDetailsService {
    private PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if(person.isEmpty())
            throw new UsernameNotFoundException("Username not found!!!");

        return new PersonDetails(person.get());
    }
}

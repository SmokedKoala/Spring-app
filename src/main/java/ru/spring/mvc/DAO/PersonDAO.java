package ru.spring.mvc.DAO;

import org.springframework.stereotype.Component;
import ru.spring.mvc.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * todo Document type PersonDAO
 */
@Component
public class PersonDAO {
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(1, "Tom"));
        people.add(new Person(2, "Jack"));
        people.add(new Person(3, "Sam"));
    }

    public List<Person> showAll(){
        return people;
    }

    public Person showOne(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}

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
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();

        people.add(new Person(++ PEOPLE_COUNT, "Tom"));
        people.add(new Person(++ PEOPLE_COUNT, "Jack"));
        people.add(new Person(++ PEOPLE_COUNT, "Sam"));
    }

    public List<Person> showAll(){
        return people;
    }

    public Person showOne(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

}



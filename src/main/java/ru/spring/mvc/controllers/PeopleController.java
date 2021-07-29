package ru.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.mvc.DAO.PersonDAO;
import ru.spring.mvc.models.Person;

/**
 * todo Document type PeopleController
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("people", personDAO.showAll());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showOne(id));
        return "people/one";
    }

    @GetMapping("/new")
    public String addOne(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createOne(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
}

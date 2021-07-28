package ru.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.mvc.DAO.PersonDAO;

/**
 * todo Document type PeopleController
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

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
}

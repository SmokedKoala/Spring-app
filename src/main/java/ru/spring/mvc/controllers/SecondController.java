package ru.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * todo Document type SecondController
 */
@Controller
@RequestMapping("/second")
public class SecondController {

    @GetMapping("/exit")
    public String exit(){
        return "second/exit";
    }
}

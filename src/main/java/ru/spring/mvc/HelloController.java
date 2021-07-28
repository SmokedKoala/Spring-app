package ru.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * todo Document type HelloController
 */
@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }
}

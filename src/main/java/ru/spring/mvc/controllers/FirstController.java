package ru.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * todo Document type FirstController
 */
@Controller
public class FirstController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        //        System.out.println("Hello, " + name + " " + surname);

        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbye(@RequestParam(value = "name", required = false) String name) {
        System.out.println("Goodbye, " + name);
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(
        @RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("sign") String sign, Model model) {
        String result;
        switch (sign) {
            case "mult":
                result = a + " * " + b + " = " + a * b;
                break;
            case "div":
                result = a + " / " + b + " = " + a / b;
                break;
            case "sum":
                result = a + " + " + b + " = " + (a + b);
                break;
            case "del":
                result = a + " - " + b + " = " + (a - b);
                break;
            default:
                result = "Incorrect values. Try: 'div' for / 'sum' for + 'del' for - 'mult' for *";
                break;
        }
        model.addAttribute("result", result);
        return "first/calculator";
    }
}

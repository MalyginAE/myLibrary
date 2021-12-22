package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hicontroller {
    @GetMapping("/hi")
    public String hi(){
        return "site";
    }
}

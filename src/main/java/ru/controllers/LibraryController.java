package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class LibraryController {
    @GetMapping
    public String mainPage(){
        return "/main_page/main_page";
    }
}

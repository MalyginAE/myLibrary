package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/manage")
    public String mainPage() {
        return "manage";
    }

    @GetMapping("/deleteDatabase")
    public String deleteDatabase() {
        System.out.println("Dleted data base");
        return "redirect:/manage";
    }

    @GetMapping("/createDatabase")
    public String createDatabase() {
        return "redirect:/manage";
    }

    @GetMapping("/deleteAllTables")
    public String deleteAllTables() {

        return "redirect:/manage";
    }

    @GetMapping("/deleteBookTable")
    public String deleteBookTable() {

        return "redirect:/manage";
    }


}

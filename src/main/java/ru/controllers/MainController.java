package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.workWithDatabase.DataBaseWorkDao;

@Controller
public class MainController {
    @Autowired
    DataBaseWorkDao workDao;
    @GetMapping("/manage")
    public String mainPage() {
        return "manage";
    }

    @GetMapping("/deleteDatabase")
    public String deleteDatabase() {
        System.out.println("Dleted data base");
        return "redirect:/manage";
    }

    @GetMapping("/createTables")
    public String createTables() {
        workDao.createAllTable();
        return "redirect:/manage";
    }
    @GetMapping("/createDatabase")
    public String createDatabase() {
        workDao.createDataBase();
        return "redirect:/manage";
    }

    @GetMapping("/deleteDatabase")
    public String deleteDatabases() {
        workDao.deleteDatabase();
        return "redirect:/manage";
    }

    @GetMapping("/deleteAllTables")
    public String deleteAllTables() {
        workDao.dropTables();
        return "redirect:/manage";
    }

    @GetMapping("/deleteBookTable")
    public String deleteBookTable() {

        return "redirect:/manage";
    }


}

package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
@SessionAttributes("current_user")
public class BookController {
    @PostMapping("/addBook")
    public String add(Model model, @RequestParam("file")File file)
    {
        System.out.println("Запрос пришел успешно");
        file.toString();
        return "";
    }


}

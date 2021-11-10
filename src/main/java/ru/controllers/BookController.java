package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.books.BookDAO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/book")
@SessionAttributes("current_user")
public class BookController {
    @Autowired
    private BookDAO bookDAO;
    @PostMapping("/addBook")
   public String addBook(@RequestParam("file") MultipartFile multipartFile , @RequestParam("url") String url, Model model){
        try {
            System.out.println(multipartFile.getOriginalFilename());
            bookDAO.upload(multipartFile,url, Integer.parseInt(model.getAttribute("current_user").toString()));
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла");
        }
        return "added";
    }

}
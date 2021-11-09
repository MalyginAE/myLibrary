package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;

@Controller
@RequestMapping("/book")
@SessionAttributes("current_user")
@RestController
public class MaterialController {

//    @PostMapping("/addBook")
//   public String addBook(@RequestParam("file") MultipartFile multipartFile , Model model){
//        System.out.println(multipartFile.getName());
//        return "add_book";
//    }
    @PostMapping("/addBook")
    public String addBook(MultipartHttpServletRequest httpServletRequest){
        MultipartFile multipartFile = httpServletRequest.getFile("file");
        System.out.println(multipartFile.isEmpty());
        return "add_book";
    }
}
package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.user.User;

@Controller
@RequestMapping("/main")
@SessionAttributes("current_user")
public class LibraryController {
    @GetMapping
    public String mainPage(Model model){
        if (model.getAttribute("current_user")==null){
            return "redirect:/user/input";
        }
        return "/main_page/main_page";
    }
//    @GetMapping("/addBook")
//    public String add(Model model){
//        return
//    }
    @GetMapping("/changeDataUser")
    public String changeUser(Model model){

        return "";
    }

    @GetMapping("/exist")
    public String existSession(Model model){
        model.addAttribute("current_user", null);
        return "redirect:/user/input";
    }
//    @GetMapping("/addNewBook")
//    public String addBook(){
//        return "/main_page/main_page";
//    }


}

package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.user.User;
import ru.user.UserDAO;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;


    @GetMapping("/signup")
    public String create(Model model){
        model.addAttribute("user",new User());
        return "sign_up";
    }

    @PostMapping()
    public String add(Model model, @ModelAttribute User user){
        userDAO.addUser(user);
        return "redirect:/user/input";
    }

    @GetMapping("/input")
    public String input(){
        return "user/input/input_to_site";
    }

}

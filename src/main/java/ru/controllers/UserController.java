package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.user.EnterToPage;
import ru.user.User;
import ru.user.UserDAO;

@Controller
@RequestMapping("/user")
@SessionAttributes("current_user")
public class UserController {
    @Autowired
    private  UserDAO userDAO;


    @GetMapping("/signup")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping()
    public String add(Model model, @ModelAttribute User user) {
        userDAO.addUser(user);
        return "redirect:/user/input";
    }

    @GetMapping("/input")
    public String input(Model model) {
        model.addAttribute("enter_to_page", new EnterToPage());
        return "user/input/input_to_site";
    }

    @PostMapping("/inputs")
    public String enter(Model model, @ModelAttribute EnterToPage enterToPage) {
        if (userDAO.checkUser(enterToPage)) {
            //взять объект пользователя и предать на мэйн для отображения
            model.addAttribute("current_user",userDAO.getUser(enterToPage.getEmail()));
            System.out.println("Сюда Заходим");
            return "redirect:/main";
        }
        return "redirect:/user/input";
    }



}

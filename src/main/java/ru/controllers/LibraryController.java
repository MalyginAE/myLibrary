package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.books.BookDAO;
import ru.user.User;
import ru.workWithDatabase.DataBaseWorkDao;


@Controller
@RequestMapping("/main")
@SessionAttributes("current_user")
public class LibraryController {
    @Autowired
    private BookDAO bookDAO;
    @GetMapping
    public String mainPage(Model model){
        Object obj = model.getAttribute("current_user");
        if (obj!=null)System.out.println(obj.getClass()+" |"+ User.class);
        if (obj==null || !obj.getClass().equals(User.class)){

            return "redirect:/user/input";
        }
        model.addAttribute("books", bookDAO.allUserBook(Integer.parseInt(model.getAttribute("current_user").toString())));
        System.out.println(model.getAttribute("current_user").toString());
        return "main_page";
    }
//    @GetMapping("/addBook")
//    public String add(Model model){
//        return "";
//    }
    @GetMapping("/changeDataUser")
    public String changeUser(Model model){
        Object obj = model.getAttribute("current_user");
        if (obj==null || !obj.getClass().equals(User.class)){
            return "redirect:/user/input";
        }
        model.addAttribute("books", bookDAO.allUserBook(Integer.parseInt(model.getAttribute("current_user").toString())));
        return "change_data_user";
    }

    @GetMapping("/exist")
    public String existSession(Model model){
        model.addAttribute("current_user", new String());
        System.out.println("Текущее значение после выхода = "+model.getAttribute("current_user"));
        System.out.println("был произведен выход");
        return "redirect:/user/input";
    }
    @GetMapping("/addNewBook")
    public String addBook(Model model){
        Object obj = model.getAttribute("current_user");
        if (obj==null || !obj.getClass().equals(User.class)){
            return "redirect:/user/input";
        }
        return "add_book";
    }


}

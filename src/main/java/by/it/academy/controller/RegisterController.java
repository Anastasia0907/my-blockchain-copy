package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView showRegisterPage(){
        return new ModelAndView("register");
    }

    @PostMapping
    public String registerNewUser(User user){
        userService.createNewUser(user);
        return "redirect:home";
    }
}

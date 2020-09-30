package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public ModelAndView showLoginPage() {
        logger.info("Calling login user form.");
        return new ModelAndView("login");
    }

    @PostMapping(value = "/{id}/login")
    public ModelAndView loginUser(
            @PathVariable String id
    ) {
        logger.info("login - POST");

        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");

        return modelAndView;
    }
}

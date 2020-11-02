package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateUserController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/edit-user-info")
    public ModelAndView showUserForm(
            @PathVariable String id,
            ModelAndView modelAndView
    ) {
        logger.info("Calling showUserForm - GET");
        User user = userService.get(id);
        logger.info(user.toString());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("edit-user-info");
        return modelAndView;
    }

    @PostMapping("/{id}/edit-user-info")
    public String updateUser(
            @PathVariable String id,
            @ModelAttribute User user
    ) {
        logger.info("Calling updateUser controller - POST");
        logger.info("User after update form : \n" + user.toString());
        userService.update(user);
        return "redirect:userpage";
    }
}

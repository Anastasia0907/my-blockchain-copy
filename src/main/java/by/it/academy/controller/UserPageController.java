package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import by.it.academy.service.UserService;
import by.it.academy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {

    private static final Logger logger = LoggerFactory.getLogger(UserPageController.class);

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}/userpage")
    public ModelAndView userpage(
            @PathVariable String id,
            ModelAndView modelAndView
    ){
        logger.info("Calling userpage - GET");

        User user = userService.get(id);
        modelAndView.addObject("user", user);
        logger.info(user.getWallet().getPrivateKeyString());
        modelAndView.addObject("privateKey", user.getWallet().getPrivateKeyString());
        logger.info(user.getWallet().getPublicKeyString());
        modelAndView.addObject("publicKey", user.getWallet().getPublicKeyString());

        modelAndView.setViewName("userpage");

        return modelAndView;
    }

    @PostMapping(value = "/userpage")
    public ModelAndView showUserPage(
            @RequestParam String userName,
            ModelAndView modelAndView
    ){
        logger.info("User successfully logged in, calling userpage : {}", userName);

        User user = userService.findByUserName(userName);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userpage");
        return modelAndView;
    }

}

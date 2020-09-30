package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import by.it.academy.service.UserService;
import by.it.academy.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserPageController {

    private static final Logger logger = LoggerFactory.getLogger(UserPageController.class);

    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    @GetMapping(value = "/{id}/userpage")
    public ModelAndView userpage(
            @PathVariable String id,
            ModelAndView modelAndView
    ) {
        logger.info("Calling userpage - GET");

        User user = userService.get(id);
        List<Wallet> wallets = walletService.getAllByUser(user);
        modelAndView.addObject("user", user);
        modelAndView.addObject("wallets", wallets);
        modelAndView.setViewName("userpage");

        return modelAndView;
    }

    @PostMapping(value = "/userpage")
    public ModelAndView showUserPage(
            @RequestParam String userName,
            ModelAndView modelAndView
    ) {
        logger.info("User successfully logged in, calling userpage : {}", userName);

        User user = userService.findByUserName(userName);
        List<Wallet> wallets = walletService.getAllByUser(user);
        modelAndView.addObject("user", user);
        modelAndView.addObject("wallets", wallets);
        modelAndView.setViewName("userpage");
        return modelAndView;
    }

}

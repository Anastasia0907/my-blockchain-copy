package by.it.academy.controller;

import by.it.academy.pojo.User;
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

@Controller
public class NewWalletController {

    private static final Logger logger = LoggerFactory.getLogger(NewWalletController.class);

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/{id}/new-wallet")
    public String createNewWallet(
            @PathVariable String id,
            @RequestParam String currency
    ){
        logger.info("Calling createNewWallet - POST");
        User user = userService.get(id);
        walletService.createWallet(user, currency);
        return "redirect:userpage";
    }
}

package by.it.academy.controller;

import by.it.academy.pojo.Transaction;
import by.it.academy.service.TransactionService;
import by.it.academy.service.UserService;
import by.it.academy.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserTransactionListController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}/{walletId}/transactions-list")
    public ModelAndView transactionsList(
            @PathVariable String id,
            @PathVariable String walletId,
            ModelAndView modelAndView
    ){
        List<Transaction> inputs = transactionService.findInputsByWalletId(walletId);
        List<Transaction> outputs = transactionService.findOutputsByWalletId(walletId);
        modelAndView.addObject("inputs", inputs);
        modelAndView.addObject("outputs", outputs);
        modelAndView.addObject("user", userService.get(id));
        modelAndView.setViewName("transactions-list");

        return modelAndView;
    }

}

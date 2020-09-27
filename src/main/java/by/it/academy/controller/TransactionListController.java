package by.it.academy.controller;

import by.it.academy.pojo.Transaction;
import by.it.academy.service.TransactionService;
import by.it.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransactionListController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}/all-transactions-list")
    public ModelAndView allTransactionsList(
            @PathVariable String id,
            ModelAndView modelAndView
    ) {
        List<Transaction> transactions = transactionService.readAll();
        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("user", userService.get(id));
        modelAndView.setViewName("all-transactions-list");
        return modelAndView;
    }
}

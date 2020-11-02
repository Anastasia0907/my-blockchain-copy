package by.it.academy.controller;

import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import by.it.academy.service.TransactionService;
import by.it.academy.service.UserService;
import by.it.academy.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(NewTransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @GetMapping(value = "/{id}/{walletId}/new-transaction")
    public ModelAndView showNewTransactionForm(
            @PathVariable String id,
            @PathVariable String walletId,
            ModelAndView modelAndView
    ) {
        logger.info("Calling showNewTransactionForm - GET");

        User user = userService.get(id);
        Wallet wallet = walletService.read(walletId);
        modelAndView.addObject("user", user);
        modelAndView.addObject("publicKey", wallet.getPublicKeyString());
        modelAndView.addObject("currency", wallet.getCurrency());
        modelAndView.setViewName("new-transaction");

        return modelAndView;
    }

    @PostMapping(value = "/{id}/new-transaction")
    public String createNewTransaction(
            @PathVariable String id,
            @RequestParam String senderPublicKey,
            @RequestParam String recipientPublicKey,
            @RequestParam double value,
            Model model
    ) {
        logger.info("Calling createNewTransaction - POST");
        try {
            transactionService.createNewTransaction(senderPublicKey, recipientPublicKey, value);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", userService.get(id));
            return "transaction-failed";
        }

        return "redirect:userpage";
    }
}

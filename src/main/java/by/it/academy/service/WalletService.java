package by.it.academy.service;

import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import by.it.academy.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionService transactionService;

    @Transactional(readOnly = true)
    public Wallet read(String id) {
        return walletRepository.read(id);
    }

    @Transactional
    public void createWallet(User user, String currency) {
        logger.info("Creating a wallet for user : {}", user.getUserName());
        Wallet wallet = new Wallet(user, currency);
        logger.info("New wallet : {}", wallet);
        walletRepository.create(wallet);
        logger.info("Wallet created : {} ", wallet);
        transactionService.createFirstTransaction(user, wallet);
    }

    @Transactional(readOnly = true)
    public Wallet getByPublicKeyString(String publicKeyString) {
        return walletRepository.findByPublicKeyString(publicKeyString);
    }

    @Transactional
    public void addBalance(Wallet wallet, double value) {
        wallet.setBalance(wallet.getBalance() + value);
        walletRepository.update(wallet);
    }

    @Transactional
    public void subtractBalance(Wallet wallet, double value) {
        wallet.setBalance(wallet.getBalance() - value);
        walletRepository.update(wallet);
    }

    @Transactional(readOnly = true)
    public List<Wallet> getAllByUser(User user) {
        return walletRepository.findAllByUser(user);
    }
}

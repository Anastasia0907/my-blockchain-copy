package by.it.academy.service;

import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import by.it.academy.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    WalletRepository walletRepository;

    @Transactional
    public Wallet read(String id){
        return walletRepository.read(id);
    }

    @Transactional
    public void createWallet(User user) {
        logger.info("Creating a wallet for user : {}", user.getUserName());
        Wallet wallet = new Wallet(user);
        walletRepository.create(wallet);
        user.setWallet(wallet);
        logger.info("Wallet created : {} ", wallet.toString());
    }

    @Transactional
    public PublicKey getPublicKeyByString(String publicKeyString) {
        Wallet wallet = walletRepository.findByPublicKeyString(publicKeyString);
        return wallet.getPublicKey();
    }

    @Transactional
    public PrivateKey getPrivateKeyByPublicString(String publicKeyString) {
        Wallet wallet = walletRepository.findByPublicKeyString(publicKeyString);
        return wallet.getPrivateKey();
    }

    @Transactional
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
}

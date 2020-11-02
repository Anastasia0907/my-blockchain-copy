package by.it.academy.service;

import by.it.academy.pojo.Transaction;
import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import by.it.academy.repository.TransactionRepository;
import by.it.academy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PrivateKey;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletService walletService;

    @Transactional(readOnly = true)
    public List<Transaction> findInputsByWalletId(String walletId) {
        Wallet wallet = walletService.read(walletId);
        return transactionRepository.findInputsByRecipientPublicKey(wallet.getPublicKeyString());
    }

    @Transactional(readOnly = true)
    public List<Transaction> findOutputsByWalletId(String walletId) {
        Wallet wallet = walletService.read(walletId);
        return transactionRepository.findOutputsBySenderPublicKey(wallet.getPublicKeyString());
    }

    @Transactional
    public void createNewTransaction(Transaction transaction) {
        transactionRepository.create(transaction);
    }

    @Transactional
    public void createFirstTransaction(User user, Wallet wallet) {
        logger.info("Creating first transaction for user :{}", user.getUserName());
        logger.info("{}", user);
        Transaction transaction = Transaction.builder()
                .recipientPublicKey(wallet.getPublicKey())
                .recipientPublicKeyString(StringUtil.getStringFromKey(wallet.getPublicKey()))
                .value(100)
                .currency(wallet.getCurrency())
                .build();

        logger.info("\nTransaction info : \nvalue={}, \npublicKey={}",
                transaction.getValue(),
                transaction.getRecipientPublicKeyString());
        generateSignature(transaction, wallet.getPrivateKey());
        transactionRepository.create(transaction);
    }

    @Transactional
    public void createNewTransaction(String senderPublicKeyString,
                                     String recipientPublicKeyString,
                                     double value) throws Exception {
        logger.info("Creating new transaction, value={}", value);
        logger.info("senderPublicKeyString={}", senderPublicKeyString);
        logger.info("recipientPublicKeyString={}", recipientPublicKeyString);

        Wallet senderWallet = walletService.getByPublicKeyString(senderPublicKeyString);
        Wallet recipientWallet = walletService.getByPublicKeyString(recipientPublicKeyString);

        if (value <= 0) {
            throw new Exception("Transaction failed : transaction cannot be null or negative");
        }
        if (senderWallet.getBalance() < value) {
            throw new Exception("Transaction failed : not enough money on your wallet");
        }
        if (!senderWallet.getCurrency().equals(recipientWallet.getCurrency())) {
            throw new Exception("Transaction failed : you cannot transfer money to different currency wallet");
        }

        Transaction transaction = Transaction.builder()
                .recipientPublicKey(recipientWallet.getPublicKey())
                .recipientPublicKeyString(recipientWallet.getPublicKeyString())
                .senderPublicKey(senderWallet.getPublicKey())
                .senderPublicKeyString(senderWallet.getPublicKeyString())
                .value(value)
                .currency(senderWallet.getCurrency())
                .build();
        generateSignature(transaction, senderWallet.getPrivateKey());

        walletService.addBalance(recipientWallet, value);
        walletService.subtractBalance(senderWallet, value);

        transactionRepository.create(transaction);

    }

    private static void generateSignature(Transaction transaction, PrivateKey privateKey) {
        String data = transaction.getSenderPublicKeyString()
                + transaction.getRecipientPublicKeyString()
                + transaction.getValue();
        transaction.setSignature(StringUtil.applyECDSASig(privateKey, data));
    }

    @Transactional(readOnly = true)
    public List<Transaction> readAll() {
        return transactionRepository.findAll();
    }

}

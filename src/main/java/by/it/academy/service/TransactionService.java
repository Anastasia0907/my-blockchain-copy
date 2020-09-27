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

import javax.transaction.Transactional;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    @Transactional
    public List<Transaction> findUserInputsById(String id) {
        User user = userService.get(id);
        return transactionRepository.findInputsByRecipientPublicKey(user.getWallet().getPublicKeyString());
    }

    @Transactional
    public List<Transaction> findUserOutputsById(String id) {
        User user = userService.get(id);
        return transactionRepository.findOutputsBySenderPublicKey(user.getWallet().getPublicKeyString());
    }

    @Transactional
    public void createNewTransaction(Transaction transaction) {
        transactionRepository.create(transaction);
    }

    @Transactional
    public void createFirstTransaction(User user) {
        logger.info("Creating first transaction for user :{}",user.getUserName());
        logger.info(user.toString());
        Transaction transaction = Transaction.builder()
                .recipientPublicKey(user.getWallet().getPublicKey())
                .recipientPublicKeyString(StringUtil.getStringFromKey(user.getWallet().getPublicKey()))
                .value(100)
                .build();

        logger.info("\nTransaction info : \nvalue={}, \npublicKey={}",
                transaction.getValue(),
                transaction.getRecipientPublicKeyString());
        generateSignature(transaction, user.getWallet().getPrivateKey());
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

        if (senderWallet.getBalance() < value) {
            throw new Exception("Transaction failed : not enough money on your wallet");
        }

        Transaction transaction = Transaction.builder()
                    .recipientPublicKey(recipientWallet.getPublicKey())
                    .recipientPublicKeyString(recipientWallet.getPublicKeyString())
                    .senderPublicKey(senderWallet.getPublicKey())
                    .senderPublicKeyString(senderWallet.getPublicKeyString())
                    .value(value)
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

    @Transactional
    public List<Transaction> readAll() {
        return transactionRepository.findAll();
    }

//    public boolean verifySignature(){
//        String data = senderPublicKeyString
//                + recipientPublicKeyString
//                + value;
//        return StringUtil.verifyECDSASig(senderPublicKey, data, signature);
//    }
}

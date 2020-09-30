package by.it.academy.service;

import by.it.academy.pojo.User;
import by.it.academy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WalletService walletService;

    @Transactional
    public void createNewUser(User user, String currency) {
        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        logger.info("Save a new user with name and password : {} {}",
                user.getUserName(), encodedPassword);
        user.setPassword(encodedPassword);

        walletService.createWallet(user, currency);

        userRepository.create(user);
    }

    @Transactional
    public User findByUserName(String userName) {
        return userRepository.find(userName);
    }

    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public User get(String id) {
        return userRepository.read(id);
    }

    @Transactional
    public void update(User user) {
        logger.info("Calling userService.update()");
        logger.info("Updated user info : {}", user);
        User savedUser = userRepository.read(user.getId());
        logger.info("Saved user info : {}", savedUser);
        if (user.getUserName() != null){
            logger.info("Changing userName");
            savedUser.setUserName(user.getUserName());
        }
        if (user.getPassword() != null){
            logger.info("Changing user password");
            savedUser.setPassword(user.getPassword());
        }
        if (user.getEmail() != null){
            logger.info("Changing user email");
            savedUser.setEmail(user.getEmail());
        }
        if (user.getMobileNumber() != null){
            logger.info("Changing user mobileNumber");
            savedUser.setMobileNumber(user.getMobileNumber());
        }
        userRepository.update(savedUser);
    }
}

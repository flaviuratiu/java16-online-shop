package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistence.UserRepository;
import org.fasttrackit.onlineshop.transfer.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// spring bean (object controlled by Spring Boot)
// IoC Container (context of all spring beans)
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    // Inversion of Control (IoC)
    private final UserRepository userRepository;

    // Dependency Injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SaveUserRequest request) {
        LOGGER.info("Creating user: {}", request);

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return userRepository.save(user);
    }

    public User getUser(long id) {
        LOGGER.info("Retrieving user {}", id);

        // optional example
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            return userOptional.get();
//        } else {
//            throw new ResourceNotFoundException("User " + id + " does not exist");
//        }

        return userRepository.findById(id)
                // lambda expression
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " does not exist"));
    }

}

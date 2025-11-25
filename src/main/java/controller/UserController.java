package controller;

import exceptions.NoResultsFound;
import models.Users;
import models.enums.UserType;
import repository.UserRepository;
import utils.PasswordHashing;

public class UserController {
    UserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    public UserType login(String email, String password) {
        try {
            Users user = userRepository.getUserData(email);
            if(user == null || !PasswordHashing.verifyPassword(password, user.getPassword())) {
                return null;
            }
            return user.getUserType();

        } catch (NoResultsFound e) {
            return null;
        }
    }
}

package validators;

import controller.LoginController;
import controller.RegisterController;
import entity.User;
import exception.CustomExceptionMessages;
import repository.UserRepo;

public class UserValidator {



    public static void validateUserNullOrEmptyTextFields(RegisterController registerController, String username, String password, String name, String phone, String email, String address) {
        if (username == null || username.equals("")){
            registerController.setInfoLabel("Username cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_USERNAME_MESSAGE);
        }

        if (password == null || password.equals("")){
            registerController.setInfoLabel("Password cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_PASSWORD_MESSAGE);
        }

        if (name == null || name.equals("")){
            registerController.setInfoLabel("Name cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_NAME_MESSAGE);
        }

        if (phone == null || phone.equals("")){
            registerController.setInfoLabel("Phone cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_PHONE_MESSAGE);
        }

        if (email == null || email.equals("")){
            registerController.setInfoLabel("Email cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_EMAIL_MESSAGE);
        }

        if (address == null || address.equals("")){
            registerController.setInfoLabel("Address cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ADDRESS_MESSAGE);
        }
    }

    public static void validateUserLengthTextFields(RegisterController registerController, String username, String password){
        if (username.length() < 6){
            registerController.setInfoLabel("Username must be at least 6 characters");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_USERNAME_LENGTH_MESSAGE);
        }

        if (password.length() < 6){
            registerController.setInfoLabel("Password must be at least 6 characters");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_PASSWORD_LENGTH_MESSAGE);
        }
    }

    public static void validateUserPhoneEmailExistence(RegisterController registerController, UserRepo userRepo, String username, String phone, String email){
        if (userRepo.checkUsernameExistence(username)){
            registerController.setInfoLabel("Username already exists");
            throw new IllegalArgumentException(CustomExceptionMessages.USERNAME_ALREADY_EXISTS_MESSAGE);
        }

        if (userRepo.checkPhoneExistence(phone)){
            registerController.setInfoLabel("Phone number already exists");
            throw new IllegalArgumentException(CustomExceptionMessages.PHONE_ALREADY_EXISTS_MESSAGE);
        }

        if (userRepo.checkEmailExistence(email)){
            registerController.setInfoLabel("Email already exists");
            throw new IllegalArgumentException(CustomExceptionMessages.EMAIL_ALREADY_EXISTS_MESSAGE);
        }
    }

    public static void validateLoginNullOrEmpty(LoginController loginController, String username, String password){
        if (username == null || username.equals("")){
            loginController.setInfoLabel("Username cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_USERNAME_MESSAGE);
        }

        if (password == null || password.equals("")){
            loginController.setInfoLabel("Password cannot be empty");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_PASSWORD_MESSAGE);
        }
    }
}
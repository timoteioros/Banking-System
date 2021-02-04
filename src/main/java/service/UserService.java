package service;

import controller.LoginController;
import controller.RegisterController;
import entity.User;
import repository.UserRepo;
import utils.ApplicationUtils;
import validators.UserValidator;

public class UserService {

    private UserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo();
        //userRepo.deleteAllUsers();
    }

    public void addUser(RegisterController registerController, String username, String password, String name, String phone, String email, String address) {
        UserValidator.validateUserNullOrEmptyTextFields(registerController, username, password, name, phone, email, address);
        UserValidator.validateUserLengthTextFields(registerController, username, password);
        UserValidator.validateUserPhoneEmailExistence(registerController, userRepo, username, phone, email);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        user.setId_user(ApplicationUtils.generateUUID());

        registerController.setInfoLabel("New user added successfully. Now you can login.");
        registerController.resetTextFields();

        userRepo.insertNewUser(user);
    }

    public boolean loginRequest(LoginController loginController, String username, String password){
        UserValidator.validateLoginNullOrEmpty(loginController, username, password);
        return userRepo.checkLoginCredentials(username, password);
    }

    public User getCurrentUser(String username, String password){
        User user = userRepo.getCurrentUser(username, password);
        return user;
    }

    public  User getUser(String username){
        User user = userRepo.getUser(username);
        return  user;
    }

    public void updateUser(User user){
        userRepo.updateUser(user);
    }

    public void deleteUser(User user){
        userRepo.deleteUser(user);
    }
}

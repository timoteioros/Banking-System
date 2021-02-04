package controller;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.CardService;
import service.DepositService;
import service.TransactionService;
import service.UserService;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    private UserService userService;
    private CardService cardService;
    private DepositService depositService;
    private TransactionService transactionService;

    private LoginController loginController;

    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    @FXML
    private  Button backButton;
    @FXML
    private DatePicker datePick;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private  TextField cardTextField;

    public void updateData(ActionEvent event){
        String username= usernameTextField.getText();
        User user = userService.getUser(username);
        user.setEmail(emailTextField.getText());
        user.setPhone(phoneTextField.getText());
        user.setAddress(addressTextField.getText());
        userService.updateUser(user);
    }

    public void deleteClient(ActionEvent event){
        String username= usernameTextField.getText();
        User user = userService.getUser(username);
        userService.deleteUser(user);
    }



    public void backToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/login.fxml"));

        loader.setController(this.loginController);

        Parent root = loader.load();
        stage.setTitle("Online Banking System: Login");
        stage.setScene(new Scene(root, 793, 531));
        stage.show();
    }



    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public DepositService getDepositService() {
        return depositService;
    }

    public void setDepositService(DepositService depositService) {
        this.depositService = depositService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

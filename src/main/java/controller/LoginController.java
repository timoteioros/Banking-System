package controller;

import entity.Transaction;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.CardService;
import service.DepositService;
import service.TransactionService;
import service.UserService;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private UserService userService;
    private CardService cardService;
    private DepositService depositService;
    private TransactionService transactionService;

    private RegisterController registerController = new RegisterController();
    private MainPageController mainPageController = new MainPageController();
    private AdminController adminController=new AdminController();

    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;


    @FXML
    private Label infoLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if (userService.loginRequest(this, usernameTextField.getText(), passwordTextField.getText())){
            User user = userService.getUser(usernameTextField.getText());
            if (user.getAdmin()==1) {
                Stage stage = (Stage) loginButton.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/admin.fxml"));

                //mainPageController.setUser(userService.getCurrentUser(usernameTextField.getText(), passwordTextField.getText()));
                adminController.setCardService(cardService);
                adminController.setDepositService(depositService);
                adminController.setUserService(userService);
                adminController.setTransactionService(transactionService);
                adminController.setLoginController(this);

                loader.setController(adminController);

                Parent root = loader.load();
                stage.setTitle("Online Banking System: Admin");
                stage.setScene(new Scene(root, 793, 531));
                stage.show();
            }
            else {
                Stage stage = (Stage) loginButton.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/mainpage.fxml"));

                mainPageController.setUser(userService.getCurrentUser(usernameTextField.getText(), passwordTextField.getText()));
                mainPageController.setUserService(userService);
                mainPageController.setCardService(cardService);
                mainPageController.setDepositService(depositService);
                mainPageController.setTransactionService(transactionService);
                mainPageController.setLoginController(this);
                loader.setController(mainPageController);

                Parent root = loader.load();
                stage.setTitle("Online Banking System: Main Page");
                stage.setScene(new Scene(root, 793, 531));
                stage.show();
            }
        } else {
            this.setInfoLabel("Incorrect username or password.");
        }
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/register.fxml"));

        registerController.setLoginController(this);
        registerController.setUserService(userService);
        loader.setController(registerController);

        Parent root = loader.load();
        stage.setTitle("Online Banking System: Register");
        stage.setScene(new Scene(root, 793, 531));
        stage.show();
    }



    public void exitButtonOnAction(ActionEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void setInfoLabel(String message) {
        infoLabel.setText(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("images/cover.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/padlock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
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
}

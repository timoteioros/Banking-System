package controller;

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
import service.UserService;
import validators.UserValidator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private LoginController loginController;


    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button backToLoginButton;
    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField addressTextField;

    @FXML
    private Label infoLabel;

    private UserService userService;


    public void registerButtonOnAction(ActionEvent event){

        userService.addUser(this,
                            usernameTextField.getText(),
                            passwordTextField.getText(),
                            nameTextField.getText(),
                            phoneTextField.getText(),
                            emailTextField.getText(),
                            addressTextField.getText()
                            );
    }

    public void backToLoginButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) backToLoginButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/login.fxml"));

        loader.setController(this.loginController);

        Parent root = loader.load();
        stage.setTitle("Online Banking System: Login");
        stage.setScene(new Scene(root, 793, 531));
        stage.show();
    }

    public void setInfoLabel(String message) {
        infoLabel.setText(message);
    }

    public void resetTextFields(){
        usernameTextField.setText("");
        passwordTextField.setText("");
        nameTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        addressTextField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("images/cover.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}

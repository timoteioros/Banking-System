package controller;

import entity.Card;
import entity.Deposit;
import entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.CardService;
import service.DepositService;
import service.TransactionService;
import service.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private UserService userService;
    private CardService cardService;
    private DepositService depositService;
    private TransactionService transactionService;
    private User user;

    private ManagerController managerController = new ManagerController();

    private LoginController loginController;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private Button managerButton;
    @FXML
    private Button insertMoneyButton;
    @FXML
    private Button withdrawMoneyButton;
    @FXML
    private Button sendMoneyButton;
    @FXML
    private Button backToLoginButton;
    @FXML
    private Button showCardDetailsButton;
    @FXML
    private Button hideCardDetailsButton;

    @FXML
    private ChoiceBox cardsChoiceBox;
    @FXML
    private ChoiceBox depositsChoiceBox;

    @FXML
    private Label balanceLabel;
    @FXML
    private Label currencyLabel;

    @FXML
    private TextField insertWithdrawAmountTextField;
    @FXML
    private TextField sendMoneyAmountTextField;
    @FXML
    private TextField ibanTextField;

    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label cardExpDateLabel;
    @FXML
    private Label cardCvvLabel;
    @FXML
    private Label cardOwnerLabel;



    public void managerButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) managerButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/manager.fxml"));

        managerController.setCardService(cardService);
        managerController.setDepositService(depositService);
        managerController.setUser(user);
        managerController.setMainPageController(this);
        loader.setController(managerController);

        Parent root = loader.load();
        stage.setTitle("Online Banking System: Manager");
        stage.setScene(new Scene(root, 793, 531));
        stage.show();
        managerController.refreshCards();
    }

    public void insertMoneyButtonOnAction(ActionEvent event){
        String deposit_name = (String)depositsChoiceBox.getValue();
        Deposit deposit = depositService.getCurrentDeposit(deposit_name);
        Double amount = Double.valueOf(insertWithdrawAmountTextField.getText());
        depositService.updateDeposit(deposit, amount);
        balanceLabel.setText(String.valueOf(deposit.getBalance()));
    }

    public void withdrawMoneyButtonOnAction(ActionEvent event){
        String deposit_name = (String)depositsChoiceBox.getValue();
        Deposit deposit = depositService.getCurrentDeposit(deposit_name);
        Double amount = Double.valueOf(insertWithdrawAmountTextField.getText());
        if(amount  > deposit.getBalance()) return;
        depositService.updateDeposit(deposit, -amount);
        balanceLabel.setText(String.valueOf(deposit.getBalance()));
    }

    public void sendMoneyButtonOnAction(ActionEvent event){
        String deposit_name = (String)depositsChoiceBox.getValue();
        Deposit deposit1 = depositService.getCurrentDeposit(deposit_name);
        Double amount = Double.valueOf(sendMoneyAmountTextField.getText());
        System.out.println(deposit1.getDeposit_name());
        String iban = ibanTextField.getText();
        System.out.println(iban);
        Deposit deposit2 = depositService.getCurrentDepositIban(iban);
        System.out.println(deposit2.getIban());
        transactionService.tranferMoney(deposit1,deposit2,amount,depositService);


    }


    public void showCardDetailsButtonOnAction(ActionEvent event){
        String id_card = (String)cardsChoiceBox.getValue();
        Card card=cardService.getCurrentCard(id_card);
        cardCvvLabel.setText(card.getCvv());
        cardOwnerLabel.setText(card.getUser().getName());
        cardNumberLabel.setText(card.getNumber());
        cardExpDateLabel.setText(card.getExp_date());


    }

    public void hideCardDetailsButtonOnAction(ActionEvent event){

        cardCvvLabel.setText("");
        cardOwnerLabel.setText("");
        cardNumberLabel.setText("");
        cardExpDateLabel.setText("");

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

    public void refreshCards(){
        this.cardsChoiceBox.setItems(FXCollections.observableArrayList(user.getCardIds()));
    }

    public void refreshDeposits(){
        Card card = cardService.getCurrentCard((String)cardsChoiceBox.getValue());
        this.depositsChoiceBox.setItems(FXCollections.observableArrayList(card.getDepositsNames()));
    }

    public void setDefaultValue()
    {
        cardsChoiceBox.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("images/cover.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        this.cardsChoiceBox.setItems(FXCollections.observableArrayList(user.getCardIds()));
        this.cardsChoiceBox.setOnAction(this::showDeposits);
        this.depositsChoiceBox.setOnAction(this::getBalance);
    }

    private void getBalance(Event event) {
        String deposit_name = (String)depositsChoiceBox.getValue();
        Deposit deposit = depositService.getCurrentDeposit(deposit_name);
        balanceLabel.setText(String.valueOf(deposit.getBalance()));
        currencyLabel.setText(deposit.getCurrency());
    }

    private void showDeposits(Event event) {
        this.refreshDeposits();
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

}

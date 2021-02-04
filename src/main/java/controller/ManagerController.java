package controller;

import com.sun.org.apache.xerces.internal.impl.xs.util.XSInputSource;
import entity.Card;
import entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    private CardService cardService;
    private DepositService depositService;
    private User user;
    //private List<Card> cards;
    private MainPageController mainPageController;

    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button backToMainButton;
    @FXML
    private Button createCardButton;
    @FXML
    private Button deleteCardButton;
    @FXML
    private Button createDepositButton;
    @FXML
    private Button deleteDepositButton;
    @FXML
    private Label infoLabel;
    @FXML
    private ChoiceBox cardsChoiceBox ;
    @FXML
    private ChoiceBox depositsChoiceBox ;
    @FXML
    private ChoiceBox currencyChoiceBox;
    @FXML
    private TextField depositNameTextField;

    public void createDepositButtonOnAction(ActionEvent event){
        String currentCardId = (String)cardsChoiceBox.getValue();
        depositService.createNewDeposit(this, cardService.getCurrentCard(currentCardId),
                (String) currencyChoiceBox.getValue(), depositNameTextField.getText());
        this.refreshDeposits();
    }

    public void deleteDepositButtonOnAction(ActionEvent event){
        String currentDeposit = (String)depositsChoiceBox.getValue();
        String currentCardId = (String)cardsChoiceBox.getValue();
        Card currentCard = cardService.getCurrentCard(currentCardId);
        depositService.deleteDeposit(this, currentCard, currentDeposit);
        if(currentCard.getDeposits().isEmpty())
            this.clearDeposits();
        else
            this.refreshDeposits();
    }

    public void createCardButtonOnAction(ActionEvent event) {
        cardService.createNewCard(this, this.user);
        this.refreshCards();
    }

    public void deleteCardButtonOnAction(ActionEvent event){
        String currentCard = (String) cardsChoiceBox.getValue();
        cardService.deleteCard(this, this.user, currentCard);
        this.refreshCards();
        this.clearDeposits();
    }

    public void backToMainButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) backToMainButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/mainpage.fxml"));

        loader.setController(mainPageController);

        Parent root = loader.load();
        stage.setTitle("Online Banking System: Main Page");
        stage.setScene(new Scene(root, 793, 531));
        stage.show();
        mainPageController.refreshCards();
    }

    public void refreshCards(){
        this.cardsChoiceBox.setItems(FXCollections.observableArrayList(user.getCardIds()));
    }

    public void refreshDeposits(){
        Card card = cardService.getCurrentCard((String)cardsChoiceBox.getValue());
        this.depositsChoiceBox.setItems(FXCollections.observableArrayList(card.getDepositsNames()));
    }

    public void clearDeposits(){
        this.depositsChoiceBox.setItems(FXCollections.observableArrayList());
    }


    public void setInfoLabel(String message){
        infoLabel.setText(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("images/cover.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
        currencyChoiceBox.getItems().add("RON");
        currencyChoiceBox.getItems().add("EUR");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public DepositService getDepositService() {
        return depositService;
    }

    public void setDepositService(DepositService depositService) {
        this.depositService = depositService;
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
}

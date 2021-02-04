package start;
import controller.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.CardService;
import service.DepositService;
import service.TransactionService;
import service.UserService;
import utils.CreditCardGenerator;

public class ApplicationStart extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        TransactionService transactionService = new TransactionService();
        DepositService depositService = new DepositService();
        CardService cardService = new CardService();
        UserService userService = new UserService();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/login.fxml"));

        LoginController loginController = new LoginController();
        loginController.setUserService(userService);
        loginController.setCardService(cardService);
        loginController.setDepositService(depositService);
        loginController.setTransactionService(transactionService);
        loader.setController(loginController);

        Parent root = loader.load();
        primaryStage.setTitle("Online Banking System: Login");
        primaryStage.setScene(new Scene(root, 793, 531));
        primaryStage.show();


        /*Parent root = FXMLLoader.load(getClass().getResource("/frames/login.fxml"));
        primaryStage.setTitle("Online Banking System: Login");
        primaryStage.setScene(new Scene(root, 793, 531));
        primaryStage.show(); */
    }

    public static void main(String[] args){


        launch(args);

        /* UserRepo userRepo = new UserRepo();
        CardRepo cardRepo = new CardRepo();
        DepositRepo depositRepo = new DepositRepo();
        TransactionRepo transactionRepo = new TransactionRepo();

        transactionRepo.deleteAllTransactions();
        depositRepo.deleteAllDeposits();
        cardRepo.deleteAllCards();
        userRepo.deleteAllUsers();

        User user = new User();

        user.setId_user(UUID.randomUUID().toString());
        user.setUsername("adrian");
        user.setPassword("pateu");
        user.setName("Tufan Constantin-Adrian");
        user.setEmail("adrian@ceva.com");
        user.setPhone("0723232323");
        user.setAddress("Str.Observatorului Nr.136");

        Card card = new Card();

        card.setId_card(UUID.randomUUID().toString());
        card.setUser(user);
        card.setNumber("1234123412341234");
        card.setExp_date("11/24");
        card.setCvv("877");
        card.setName(user.getName());
        user.addCard(card);

        Deposit deposit = new Deposit();
        deposit.setIban("RORNCB123");
        deposit.setCurrency("EUR");
        deposit.setBalance(15);
        deposit.setCard(card);


        Deposit deposit2 = new Deposit();

        deposit2.setIban("RORNCB124");
        deposit2.setCurrency("LEI");
        deposit2.setBalance(25);
        deposit2.setCard(card);

        Transaction transaction = new Transaction();
        transaction.setAmount("15");
        transaction.setCurrency("EUR");
        transaction.setIban_receiver(deposit2.getIban());
        transaction.setId_trans(UUID.randomUUID().toString());

        deposit.addTransaction(transaction);

        //transaction.addDeposit(deposit);

        userRepo.insertNewUser(user);
        cardRepo.insertNewCard(card);
        depositRepo.insertNewDeposit(deposit);
        depositRepo.insertNewDeposit(deposit2);
        //transaction.setId_trans("dsadasfsa"); */
    }
}

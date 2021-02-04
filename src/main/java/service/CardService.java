package service;

import controller.ManagerController;
import entity.Card;
import entity.Deposit;
import entity.Transaction;
import entity.User;
import repository.CardRepo;
import utils.ApplicationUtils;
import utils.CreditCardGenerator;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CardService {

    private CardRepo cardRepo;
    private int nr;

    public CardService() {
        cardRepo = new CardRepo();
        //cardRepo.deleteAllCards();
    }

    public void createNewCard(ManagerController manageCardsController, User user){
        Card card = new Card();

        card.setId_card(ApplicationUtils.generateUUID());
        card.setUser(user);
        card.setNumber(CreditCardGenerator.generateNumber());
        card.setExp_date(CreditCardGenerator.generateExpDate());
        card.setCvv(CreditCardGenerator.generateCCV());
        card.setName(user.getName());
        user.addCard(card);

        cardRepo.insertNewCard(card);
        manageCardsController.setInfoLabel("Card successfully created.");
    }

    public void deleteCard(ManagerController manageCardsController, User user, String id_card){
        Card card = getCurrentCard(id_card);
        card.setDeposits();
        Iterator<Card> iter = user.getCards().iterator();
        while (iter.hasNext()) {
            Card c = iter.next();
            if (c.getId_card() == id_card){
                iter.remove();
            }
        }
        cardRepo.deleteCard(id_card);
        manageCardsController.setInfoLabel("Card deleted successfully.");
    }

    public Card getCurrentCard(String id_card){
        Card card = cardRepo.getCurrentCard(id_card);
       return card;
    }


}
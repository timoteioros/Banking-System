package service;

import controller.ManagerController;
import entity.Card;
import entity.Deposit;
import entity.Transaction;
import entity.User;
import repository.DepositRepo;
import utils.ApplicationUtils;

import java.util.Iterator;

public class DepositService {

    private DepositRepo depositRepo;

    public DepositService() {
        depositRepo = new DepositRepo();
        //depositRepo.deleteAllDeposits();
    }

    public void createNewDeposit(ManagerController manageDepositsController, Card card, String currency, String deposit_name){
        Deposit deposit = new Deposit();

        deposit.setIban(ApplicationUtils.generateUUID());
        deposit.setCard(card);
        deposit.setBalance(0.0);
        deposit.setCurrency(currency);
        deposit.setDeposit_name(deposit_name);
        depositRepo.insertNewDeposit(deposit);
        manageDepositsController.setInfoLabel("Deposit successfully created.");
    }

    public void deleteDeposit(ManagerController manageDepositsController, Card card, String deposit_name) {
        Iterator<Deposit> iter = card.getDeposits().iterator();
        Deposit currentDeposit = depositRepo.getCurrentDeposit(deposit_name);
        while(iter.hasNext()) {
            Deposit d = iter.next();
            if(d.getIban().equals(currentDeposit.getIban()))
                iter.remove();
        }
        depositRepo.deleteDeposit(currentDeposit.getIban());
        manageDepositsController.setInfoLabel("Deposit deleted successfully.");
    }

    public void updateDeposit(Deposit deposit, Double amount){
        Double newBalance = deposit.getBalance() + amount;
        depositRepo.updateDeposit(deposit.getIban(), newBalance);
        deposit.setBalance(newBalance);
    }

    public Deposit getCurrentDeposit(String deposit_name){
        Deposit deposit = depositRepo.getCurrentDeposit(deposit_name);
        return deposit;
    }

    public Deposit getCurrentDepositIban(String iban){
        Deposit deposit = depositRepo.getCurrentDepositIban(iban);
        return deposit;
    }
}

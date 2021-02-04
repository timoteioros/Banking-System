package service;

import entity.Deposit;
import entity.Transaction;
import repository.TransactionRepo;

public class TransactionService {

    private TransactionRepo transactionRepo;

    public TransactionService (){
        transactionRepo = new TransactionRepo();
        //transactionRepo.deleteAllTransactions();
    }

    public Boolean tranferMoney(Deposit deposit1, Deposit deposit2, Double amount, DepositService depositService){
        Double amount2=amount;
        if(!deposit1.getCurrency().equals(deposit2.getCurrency()))
            if(deposit1.getCurrency().equals("RON"))
                amount2/=4.8;
            else
                amount2*=4.8;

        if(deposit1.getBalance() < amount) return false;
        Transaction transaction = new Transaction();
        //transaction.addDeposit(deposit2);
        transaction.setAmount(String.valueOf(amount2));
        transaction.setCurrency(deposit2.getCurrency());
        transaction.setId_trans(deposit1.getIban());
        transaction.setIban_receiver(deposit2.getIban());
        transactionRepo.insertNewTransaction(transaction);
        deposit1.addTransaction(transaction);
        depositService.updateDeposit(deposit1, -amount2);
        depositService.updateDeposit(deposit2, amount2);
        return true;
    }

}

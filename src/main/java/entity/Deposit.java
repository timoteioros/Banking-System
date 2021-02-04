package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="deposit")
public class Deposit {

    @Id
    private String iban;

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "deposit_transaction", joinColumns = @JoinColumn(name = "iban"), inverseJoinColumns = @JoinColumn(name = "id_trans"))
    private Set<Transaction> transactions = new HashSet<Transaction>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Column
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeposit_name() {
        return deposit_name;
    }

    public void setDeposit_name(String deposit_name) {
        this.deposit_name = deposit_name;
    }

    @Column
    private String deposit_name;


    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}

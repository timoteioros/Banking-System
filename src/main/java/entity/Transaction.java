package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    private String id_trans;

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public String getId_trans() {
        return id_trans;
    }

    @ManyToMany(mappedBy = "transactions", fetch = FetchType.EAGER)
    private Set<Deposit> deposits = new HashSet<Deposit>();

    public void addDeposit(Deposit deposit){
        deposits.add(deposit);
    }


    @Column
    private String iban_receiver;

    public String getIban_receiver() {
        return iban_receiver;
    }

    public void setIban_receiver(String iban_receiver) {
        this.iban_receiver = iban_receiver;
    }

    @Column
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Column
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}

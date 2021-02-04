package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="card")
public class Card {

    @Id
    private String id_card;

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getId_card() {
        return id_card;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "card")
    private List<Deposit> deposits = new ArrayList<Deposit>();

    public void addDeposit(Deposit deposit){
        deposits.add(deposit);
    }
    public List<Deposit> getDeposits(){return deposits;}

    public List<String> getDepositsNames(){
        List<String> depositsNames = new ArrayList<String>();
        for (Deposit deposit: deposits) {
            depositsNames.add(deposit.getDeposit_name());
        }
        return depositsNames;
    }

    @Column
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDeposits() {
        this.deposits = new ArrayList<>();
    }

    @Column
    private String exp_date;

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    @Column
    private String cvv;

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

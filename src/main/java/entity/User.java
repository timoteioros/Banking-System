package entity;

import javassist.CodeConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    private String id_user;

    public User() { }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Card> cards = new ArrayList<Card>();

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards(){
        return cards;
    }

    public List<String> getCardIds(){
        List<String> cardIds = new ArrayList<String>();
        for (Card card : cards) {
            cardIds.add(card.getId_card());
        }
        return cardIds;
    }

    @Column
    private String username;

    @Column
    private int admin;

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return "Name: " + name;
    }
}

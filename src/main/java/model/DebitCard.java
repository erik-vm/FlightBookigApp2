package model;

import javax.persistence.*;

@Entity
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int debitCardId;
    private int cardNumber;
    private double balance;
    @OneToOne(mappedBy = "debitCard")
    private Client client;

    public DebitCard() {
        balance = 0;
    }

    public int getDebitCardId() {
        return debitCardId;
    }

    public void setDebitCardId(int debitCardId) {
        this.debitCardId = debitCardId;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card number: " + cardNumber +
                " | Balance: " + balance;
    }
}

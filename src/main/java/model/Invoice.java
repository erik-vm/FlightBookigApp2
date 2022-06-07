package model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;
    private Date dateIssued;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;
    private int passengers;
    private double totalPrice;

    public Invoice() {
    }

    public Invoice(Client client, Trip trip, int passengers, double totalPrice) {
        dateIssued = Date.valueOf(LocalDate.now());
        this.client = client;
        this.trip = trip;
        this.passengers = passengers;
        this.totalPrice = totalPrice;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "--------------------------------------------------------------------------------------------------------------------------------------" +
                "\nId: " + invoiceId + " | Date issued: " + dateIssued + " | Client: " + client.getFirstName() + " " + client.getLastName()
                + "\nTrip from " + trip.getDepartureCity().getName() + "(" + trip.getDepartureCity().getCountry().getName() + ") to "
                + trip.getDestinationCity().getName() + "(" + trip.getDestinationCity().getCountry().getName() + ") " + " | Duration: " + trip.getDuration()
                + " | Passengers: " + passengers + "\nTotal: " + totalPrice
                + "\n--------------------------------------------------------------------------------------------------------------------------------------";
    }
}

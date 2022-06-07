package model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;

@Entity
public class Trip {
    @Id
    @GeneratedValue
    private int tripId;
    @ManyToOne
    @JoinColumn(name = "planeId")
    private Plane plane;
    @ManyToOne
    @JoinColumn(name = "departureCityId")
    private City departureCity;
    @ManyToOne
    @JoinColumn(name = "destinationCityId")
    private City destinationCity;
    private Calendar dayOfDeparture;
    private Time timeOfDeparture;
    private Time duration;
    private double pricePerPassenger;

    public Trip() {
    }

    public Trip(Plane plane, City departureCity, City destinationCity, Calendar dayOfDeparture, Time duration, double pricePerPassenger) {
        this.plane = plane;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.dayOfDeparture = dayOfDeparture;
        this.duration = duration;
        this.pricePerPassenger = pricePerPassenger;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Calendar getDayOfDeparture() {
        return dayOfDeparture;
    }

    public void setDayOfDeparture(Calendar dayOfDeparture) {
        this.dayOfDeparture = dayOfDeparture;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public double getPricePerPassenger() {
        return pricePerPassenger;
    }

    public void setPricePerPassenger(double pricePerPassenger) {
        this.pricePerPassenger = pricePerPassenger;
    }

    public Time getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(Time timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    @Override
    public String toString() {
        return "--------------------------------------------------------------------------------------------------------------------------------------" +
                "\nId: " + tripId + " | Plane: " + plane.getName() + " | Company: " + plane.getPlaneCompany().getName()
                + "\nDeparture from " + departureCity.getName() + "(" + departureCity.getCountry().getName() + ") to "
                + destinationCity.getName() + "(" + destinationCity.getCountry().getName() + ") "
                + "Departure: " + timeOfDeparture + " ("+dayOfDeparture.get(Calendar.DAY_OF_WEEK)+")"+ " | Duration: " + duration
                + " | Price per passenger: " + pricePerPassenger
                + "\n--------------------------------------------------------------------------------------------------------------------------------------";
    }
}

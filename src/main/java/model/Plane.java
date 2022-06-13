package model;

import javax.persistence.*;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planeId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "planeCompanyName")
    private PlaneCompany planeCompany;
    private int maxCapacity;
    private int currentCapacity;

    public Plane() {
    }

    public Plane(String name, PlaneCompany planeCompany, int maxCapacity) {
        this.name = name;
        this.planeCompany = planeCompany;
        this.maxCapacity = maxCapacity;
        currentCapacity = maxCapacity;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaneCompany getPlaneCompany() {
        return planeCompany;
    }

    public void setPlaneCompany(PlaneCompany planeCompany) {
        this.planeCompany = planeCompany;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    @Override
    public String toString() {
        return "Id: " + planeId + " | Name: " + name + " | Company: " + planeCompany.getName() + " | Max capacity: " + maxCapacity;
    }
}

package model;

import javax.persistence.*;

@Entity
public class PlaneCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planeCompanyId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    public PlaneCompany() {
    }

    public PlaneCompany(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public int getPlaneCompanyId() {
        return planeCompanyId;
    }

    public void setPlaneCompanyId(int planeCompanyId) {
        this.planeCompanyId = planeCompanyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Id: " + planeCompanyId + " | Name: " + name + " | City: " + city + "(" + city.getCountry()+")";
    }
}

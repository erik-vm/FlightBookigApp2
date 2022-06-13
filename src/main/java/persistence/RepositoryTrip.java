package persistence;

import model.City;
import model.Trip;

import javax.persistence.EntityManager;
import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryTrip {
    EntityManager entityManager;

    public RepositoryTrip() {
        entityManager = getEntityManager();
    }

    public void saveTrip(Trip trip) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(trip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
    public void deleteTrip(Trip trip){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(trip);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    public Trip getTripById(int tripId){
        return entityManager.find(Trip.class, tripId);
    }

    public List<Trip> tripList() {
        return entityManager.createQuery("FROM Trip", Trip.class).getResultList();
    }

    public List<Trip> tripListByDepartureCity(City city) {
        return entityManager.createQuery("FROM Trip WHERE departureCityName= :depCity", Trip.class).setParameter("depCity", city).getResultList();
    }

    public List<Trip> tripListByDestinationCity(City city) {
        return entityManager.createQuery("FROM Trip WHERE destinationCityName= :desCity", Trip.class).setParameter("desCity", city).getResultList();
    }
    public List<Trip> tripListMaxPrice(double price) {
        return entityManager.createQuery("FROM Trip WHERE pricePerPassenger <= :price", Trip.class).setParameter("price", price).getResultList();
    }

}

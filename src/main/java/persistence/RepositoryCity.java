package persistence;

import model.City;
import model.Country;

import javax.persistence.EntityManager;

import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryCity {
    EntityManager entityManager;

    public RepositoryCity() {
        entityManager = getEntityManager();
    }


    public void saveCity(City city) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(city);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteCity(int cityId){
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM City WHERE cityId= : id").setParameter("id", cityId).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    public List<City> cityList() {
        return entityManager.createQuery("FROM City", City.class).getResultList();
    }


    public List<City> cityListByCountry(Country country) {
        return entityManager.createQuery("FROM City WHERE countryName = :country", City.class).setParameter("country", country).getResultList();
    }

    public City getCityById(int cityId){
        return entityManager.find(City.class, cityId);
    }


    public City getCityByName(String name) {
        return entityManager.createQuery("FROM City WHERE name= :name", City.class).setParameter("name", name).getSingleResult();
    }

    public void changeCityNameById(int cityId ,String newName){
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE City SET name= : newName WHERE cityId= :id")
                    .setParameter("id", cityId).setParameter("newName", newName).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public Long totalCityCount(){
        return (Long) entityManager.createQuery("SELECT count(*) FROM City").getSingleResult();
    }
    public Long cityCountByCountry(Country country){
        return (Long) entityManager.createQuery("SELECT count(*) FROM City WHERE countryName= :country").setParameter("country", country).getSingleResult();
    }
}

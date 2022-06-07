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


public void saveCity(City city){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(city);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
}
public List<City> cityList(){
        return entityManager.createQuery("FROM City", City.class).getResultList();
}
public List<City> cityListByCountry(Country country){
        return entityManager.createQuery("FROM City WHERE Country = :country", City.class).setParameter("country", country).getResultList();
}

    public City getCityByName(String name){
        return entityManager.createQuery("FROM City WHERE name= :name", City.class).setParameter("name", name).getSingleResult();
    }
}

package persistence;

import model.Country;

import javax.persistence.EntityManager;

import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryCountry {

    EntityManager entityManager;

    public RepositoryCountry() {
        entityManager = getEntityManager();
    }
    public void saveCountry(Country country){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(country);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public List<Country> countryList(){
        return entityManager.createQuery("FROM Country", Country.class).getResultList();
    }
    public Country getCountryById(int countryId){
        return entityManager.find(Country.class, countryId);
    }
    public Country getCountryByName(String name){
        return entityManager.createQuery("FROM Country WHERE name= :name", Country.class).setParameter("name", name).getSingleResult();
    }
    public void changeNameById(int countryId, String newName){
        try {
            entityManager.clear();
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Country SET name = :newName WHERE countryId= :countryId")
                    .setParameter("newName", newName).setParameter("countryId", countryId).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public void deleteCountryById(int countryId){
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE Country WHERE countryId= :countryId")
                    .setParameter("countryId", countryId).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public Long totalCountryCount(){
        return (Long) entityManager.createQuery("SELECT count(*) FROM Country").getSingleResult();
    }
}

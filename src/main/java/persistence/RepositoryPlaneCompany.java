package persistence;

import model.City;
import model.Plane;
import model.PlaneCompany;

import javax.persistence.EntityManager;

import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryPlaneCompany {
    EntityManager entityManager;

    public RepositoryPlaneCompany() {
        entityManager = getEntityManager();
    }

    public void savePlaneCompany(PlaneCompany planeCompany){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(planeCompany);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public List<PlaneCompany> planeCompanyList(){
        return entityManager.createQuery("FROM PlaneCompany", PlaneCompany.class).getResultList();
    }
    public List<PlaneCompany> planeCompanyListByCity(City city){
        return entityManager.createQuery("From PlaneCompany WHERE cityId= city", PlaneCompany.class)
                .setParameter("city", city).getResultList();
    }
    public void deletePlaneCompany(int id){
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM PlaneCompany WHERE planeCompanyId= :id")
                    .setParameter("id", id).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
}

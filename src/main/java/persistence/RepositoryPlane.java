package persistence;

import model.Plane;
import model.PlaneCompany;

import javax.persistence.EntityManager;

import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryPlane {

    EntityManager entityManager;

    public RepositoryPlane() {
        entityManager= getEntityManager();
    }

  public  List<Plane> planeList(){
        return entityManager.createQuery("FROM Plane", Plane.class).getResultList();
    }
    List<Plane> planeListByCompany(PlaneCompany company){
        return entityManager.createQuery("FROM Plane WHERE planeCompanyName= :company", Plane.class).getResultList();
    }

    public void savePlane(Plane plane){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(plane);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public void deletePlaneById(int planeId){
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM Plane WHERE planeId= : id").setParameter("id", planeId).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public Plane getPlaneById(int planeId){
        return entityManager.find(Plane.class, planeId);
    }

    public int getPlaneMaxCapacity(int planeId){
        return (int) entityManager.createQuery("SELECT maxCapacity FROM Plane WHERE planeId= :id").setParameter("id", planeId).getSingleResult();
    }
    public int getCurrentPlaneCapacity(int planeId){
        return (int) entityManager.createQuery("SELECT currentCapacity FROM Plane WHERE planeId= :id").setParameter("id", planeId).getSingleResult();
    }
    public void setCurrentPlaneCapacity(int planeId, int newCapacity){
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Plane SET currentCapacity = :newCapacity WHERE planeId= :id")
                    .setParameter("id", planeId).setParameter("newCapacity", newCapacity).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

}

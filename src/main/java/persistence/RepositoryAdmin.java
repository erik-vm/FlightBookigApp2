package persistence;

import model.Admin;

import javax.persistence.EntityManager;

import static util.DBUtil.getEntityManager;

public class RepositoryAdmin {

    EntityManager entityManager;

    public RepositoryAdmin() {
        entityManager = getEntityManager();
    }

    public void saveAdmin(Admin admin){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(admin);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public void modifyAdminPassword(int adminId, String newPassword){
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Admin SET password = :newPassword WHERE adminId = :adminId")
                    .setParameter("newPassword", newPassword).setParameter("adminId", adminId).executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    public Admin getAdminById(int adminId){
        return entityManager.find(Admin.class, adminId);
    }
    public Admin getAdminByUserName(String userName){
        return entityManager.createQuery("FROM Admin WHERE username= :userName", Admin.class).setParameter("userName", userName).getSingleResult();
    }
    public String getPasswordByUserName(String userName){
        return getAdminByUserName(userName).getPassword();
    }
}

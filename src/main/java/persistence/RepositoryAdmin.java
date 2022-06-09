package persistence;

import model.Admin;

import javax.persistence.EntityManager;

import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryAdmin {

    EntityManager entityManager;

    public RepositoryAdmin() {
        entityManager = getEntityManager();
    }

    public void saveAdmin(Admin admin) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(admin);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteAdmin(int adminId) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM Admin WHERE adminId= :adminId").setParameter("adminId", adminId).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void modifyAdminPassword(int adminId, String newPassword) {
        try {
            entityManager.clear();
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Admin SET password = :newPassword WHERE adminId = :adminId")
                    .setParameter("newPassword", newPassword).setParameter("adminId", adminId).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Admin> adminList() {
        return entityManager.createQuery("FROM Admin", Admin.class).getResultList();
    }

    public Admin getAdminById(int adminId) {
        return entityManager.find(Admin.class, adminId);
    }

    public Admin getAdminByUserName(String userName) {
        return entityManager.createQuery("FROM Admin WHERE username= :userName", Admin.class).setParameter("userName", userName).getSingleResult();
    }

    public String getPasswordByUserName(String userName) {
        return getAdminByUserName(userName).getPassword();
    }

    public Admin getAdminByPassword(String password) {
        return entityManager.createQuery("FROM Admin WHERE password= :password", Admin.class).setParameter("password", password).getSingleResult();
    }
}

package persistence;

import model.Client;
import model.DebitCard;

import javax.persistence.EntityManager;

import static util.DBUtil.getEntityManager;

public class RepositoryDebitCard {

    EntityManager entityManager;

    public RepositoryDebitCard() {
        entityManager = getEntityManager();
    }

    public void saveDebitCard(DebitCard debitCard) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(debitCard);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public DebitCard getDebitCardById(int id) {
        return entityManager.createQuery("FROM DebitCard WHERE debitCardId = : id", DebitCard.class).setParameter("id", id).getSingleResult();
    }


    public void makeDeposit(int id, double deposit) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE DebitCard d INNER JOIN Client c SET d.balance = d.balance + :deposit  WHERE d.debitCardId = :id AND c.debitCardId = d.debitCardId")
                    .setParameter("id", id).setParameter("deposit", deposit).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void setNewBalance(int id, double newBalance) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE DebitCard SET balance = :newBalance  WHERE debitCardId = :id")
                    .setParameter("id", id).setParameter("newBalance", newBalance).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
    public double getBalanceById(int id) {
         return (double)  entityManager.createQuery("SELECT balance FROM DebitCard WHERE debitCardId = :id")
                    .setParameter("id", id).getSingleResult();

    }

}

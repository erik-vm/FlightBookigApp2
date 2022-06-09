package persistence;

import model.DebitCard;

import javax.persistence.EntityManager;

import java.util.concurrent.ExecutionException;

import static util.DBUtil.getEntityManager;

public class RepositoryDebitCard {

    EntityManager entityManager;

    public RepositoryDebitCard() {
        entityManager = getEntityManager();
    }
    public void saveDebitCard(DebitCard debitCard){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(debitCard);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public DebitCard getDebitCardByNumber(int number){
        return entityManager.createQuery("FROM DebitCard WHERE cardNumber = : number", DebitCard.class).setParameter("number", number).getSingleResult();
    }
}

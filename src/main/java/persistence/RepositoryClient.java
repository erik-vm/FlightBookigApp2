package persistence;

import model.Client;

import javax.persistence.EntityManager;

import java.util.List;

import static util.DBUtil.getEntityManager;

public class RepositoryClient {

    EntityManager entityManager;

    public RepositoryClient() {
        entityManager = getEntityManager();
    }

    public void saveClient(Client client) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public Client getClientById(int clientId){
        return entityManager.find(Client.class, clientId);
    }

    public Client getClientByEmail(String email) {
        return entityManager.createQuery("FROM Client WHERE email= :email", Client.class).setParameter("email", email).getSingleResult();
    }

    public void deleteClientById(int clientId) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE From Client WHERE clientId= :clientId").setParameter("clientId", clientId).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Client> clientList() {
        return entityManager.createQuery("FROM Client", Client.class).getResultList();
    }

    public String getClientsPasswordByEmail(String email) {
        return String.valueOf(entityManager.createQuery("FROM Client WHERE email = :email", Client.class).setParameter("email", email).getSingleResult());
    }
}

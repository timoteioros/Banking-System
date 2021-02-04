package repository;


import entity.Card;
import entity.Deposit;
import queries.CardQueries;
import queries.DepositQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DepositRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewDeposit(Deposit deposit) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(deposit);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAllDeposits(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Deposit");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteDeposit(String iban){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(DepositQueries.DELETE_DEPOSIT).setParameter("iban", iban);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public Deposit getCurrentDeposit(String deposit_name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(DepositQueries.DEPOSIT_BY_NAME, Deposit.class).setParameter("deposit_name", deposit_name);
        List<Deposit> deposits = jpqlQuery.getResultList();
        em.close();
        return deposits.get(0);
    }

    public Deposit getCurrentDepositIban(String iban) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(DepositQueries.DEPOSIT_BY_IBAN, Deposit.class).setParameter("iban", iban);
        List<Deposit> deposits = jpqlQuery.getResultList();
        em.close();
        return deposits.get(0);
    }

    public void updateDeposit(String iban, Double newBalance){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(DepositQueries.UPDATE_DEPOSIT).setParameter("balance", newBalance).setParameter("iban", iban);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
    }
}

package repository;


import entity.Deposit;
import entity.Transaction;
import queries.DepositQueries;
import queries.TransactionQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TransactionRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewTransaction(Transaction transaction) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAllTransactions(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Transaction");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<Transaction> getTransactions(String iban){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(TransactionQueries.TRANSACTION_BY_IBAN, Transaction.class).setParameter("id_trans", iban);
        List<Transaction> transactions = jpqlQuery.getResultList();
        em.close();
        return transactions;

    }
}

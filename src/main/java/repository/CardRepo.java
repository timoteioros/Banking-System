package repository;


import entity.Card;
import entity.User;
import queries.CardQueries;
import queries.UserQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CardRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewCard(Card card) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteCard(String id_card){
        //System.out.println("ASDASDSADASDAS: " + id_card);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(CardQueries.DELETE_CARD).setParameter("id_card", id_card);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAllCards(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Card");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public Card getCurrentCard(String id_card){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(CardQueries.CARD_BY_ID, Card.class).setParameter("id_card", id_card);
        List<Card> cards = jpqlQuery.getResultList();
        em.close();
        return cards.get(0);
    }
}

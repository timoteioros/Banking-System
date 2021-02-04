package repository;

import entity.User;
import queries.CardQueries;
import queries.DepositQueries;
import queries.UserQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAllUsers(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM User");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public boolean checkUsernameExistence(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.USER_BY_USERNAME_QUERY, User.class).setParameter("username", username);
        List<User> users = jpqlQuery.getResultList();
        em.close();
        return (users.size() != 0) ? true : false;
    }

    public boolean checkPhoneExistence(String phone) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.USER_BY_PHONE_QUERY, User.class).setParameter("phone", phone);
        List<User> users = jpqlQuery.getResultList();
        em.close();
        return (users.size() != 0) ? true : false;
    }

    public boolean checkEmailExistence(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.USER_BY_EMAIL_QUERY, User.class).setParameter("email", email);
        List<User> users = jpqlQuery.getResultList();
        em.close();
        return (users.size() != 0) ? true : false;
    }

    public boolean checkLoginCredentials(String username, String password){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.USER_LOGIN_QUERY, User.class).setParameter("username", username).setParameter("password", password);
        List<User> users = jpqlQuery.getResultList();
        em.close();
        return (users.size() != 0) ? true : false;
    }

    public User getCurrentUser(String username, String password){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.USER_LOGIN_QUERY, User.class).setParameter("username", username).setParameter("password", password);
        List<User> users = jpqlQuery.getResultList();
        em.close();
        return users.get(0);
    }

    public User getUser(String username){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.USER_BY_USERNAME_QUERY, User.class).setParameter("username", username);
        List<User> users = jpqlQuery.getResultList();
        em.close();
        return users.get(0);
    }

    public void updateUser(User user ){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.UPDATE_MAIL_QUERY).setParameter("email", user.getEmail()).setParameter("phone",user.getPhone()).setParameter("address", user.getAddress()).setParameter("username", user.getUsername());
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
    }

    public void deleteUser(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UserQueries.DELETE_USER).setParameter("id_user", user.getId_user());
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}

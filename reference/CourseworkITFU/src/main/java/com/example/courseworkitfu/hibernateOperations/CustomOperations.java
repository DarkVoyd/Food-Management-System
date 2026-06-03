package com.example.courseworkitfu.hibernateOperations;

import com.example.courseworkitfu.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CustomOperations extends GenericOperations {
    public CustomOperations(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public User getUserByCredentials(String username, String password) {
        User user = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder(); //I will need methods that represent different SQL query conditions, such as AND, OR, LIKE, etc.
            CriteriaQuery<User> query = cb.createQuery(User.class); //I give instructions that i will have a query for User table and I will write conditions
            Root<User> root = query.from(User.class); //This is required to indicate which attributes/columns i would like to access

            //SELECT * FROM USER WHERE `username` = username AND `password` = password
            query.select(root).where(cb.and(
                    cb.equal(root.get("username"), username),
                    cb.equal(root.get("password"), password)
            ));

            Query q = entityManager.createQuery(query);
            user = (User) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

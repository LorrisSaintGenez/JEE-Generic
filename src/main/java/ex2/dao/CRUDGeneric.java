package ex2.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/06/2017.
 */

public @ApplicationScoped class CRUDGeneric {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistenceName");
    private EntityManager em = emf.createEntityManager();

    public <T> ArrayList<T> listEntity(Class classEntity) {
        Query q = em.createQuery("SELECT x from " + classEntity.getSimpleName() + " x" );
        return (ArrayList<T>) q.getResultList();
    }

    public <T> void createEntity(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public <T> void deleteEntity(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public <T> void updateEntity(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }
}

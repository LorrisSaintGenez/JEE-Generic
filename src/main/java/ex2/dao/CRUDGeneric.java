package ex2.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/06/2017.
 */

public @ApplicationScoped class CRUDGeneric {

    @Inject
    private EntityManager em;

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

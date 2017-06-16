package ex2.dao;

import ex2.interceptor.Transaction;

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

    @Transaction
    public <T> ArrayList<T> listEntity(Class classEntity) {
        Query q = em.createQuery("SELECT x from " + classEntity.getSimpleName() + " x" );
        return (ArrayList<T>) q.getResultList();
    }

    @Transaction
    public <T> void createEntity(T entity) {
        em.persist(entity);
    }

    @Transaction
    public <T> void deleteEntity(T entity) {
        em.remove(entity);
    }

    @Transaction
    public <T> void updateEntity(T entity) {
        em.merge(entity);
    }
}

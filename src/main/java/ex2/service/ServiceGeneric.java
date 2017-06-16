package ex2.service;

import ex2.dao.CRUDGeneric;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/06/2017.
 */

public @ApplicationScoped class ServiceGeneric {

    @Inject
    private CRUDGeneric crudGeneric;

    public <T> ArrayList<T> listEntity(Class<T> classEntity) {
        return crudGeneric.listEntity(classEntity);
    }

    public <T> void createEntity(T entity) {
        crudGeneric.createEntity(entity);
    }

    public <T> void deleteEntity(T entity) {
        crudGeneric.deleteEntity(entity);
    }

    public <T> void updateEntity(T entity) {
        crudGeneric.updateEntity(entity);
    }
}

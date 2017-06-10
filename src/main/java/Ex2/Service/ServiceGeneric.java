package Ex2.Service;

import Ex2.DAO.CRUDGeneric;

import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Lorris on 10/06/2017.
 */
public class ServiceGeneric {

    private final static CRUDGeneric crudGeneric = new CRUDGeneric();

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

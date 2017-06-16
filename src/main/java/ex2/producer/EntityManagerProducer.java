package ex2.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Lorris on 16/06/2017.
 */
public class EntityManagerProducer {

    @Produces @ApplicationScoped
    public EntityManager create() {
        return Persistence.createEntityManagerFactory("JPAPersistenceName").createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager em) {
        em.close();
    }


}

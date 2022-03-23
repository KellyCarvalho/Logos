package Logos.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("logos");

    //TODO passar o nome da unidade por argumento
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static EntityManager getEntityManagerTest() {
       final EntityManagerFactory FACTORY_TEST = Persistence.createEntityManagerFactory("test");
        return FACTORY_TEST.createEntityManager();
    }
}

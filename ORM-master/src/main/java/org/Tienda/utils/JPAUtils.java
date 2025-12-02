package org.Tienda.utils;


import jakarta.persistence.*;

public class JPAUtils {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tienda");

    public static EntityManager em() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}

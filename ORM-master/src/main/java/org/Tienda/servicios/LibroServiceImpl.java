package org.Tienda.servicios;

import jakarta.persistence.EntityManager;
import org.Tienda.modelo.Libro;
import org.Tienda.utils.JPAUtils;

import java.util.List;

public class LibroServiceImpl {
    public List<Libro> listar() {
        EntityManager em = JPAUtils.em();
        try {
            return em.createQuery("select l from Libro l order by l.titulo", Libro.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Libro porIsbn(String isbn) {
        EntityManager em = JPAUtils.em();
        try {
            return em.find(Libro.class, isbn);
        } finally {
            em.close();
        }
    }
}


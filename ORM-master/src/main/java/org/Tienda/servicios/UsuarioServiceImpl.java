package org.Tienda.servicios;

import jakarta.persistence.EntityManager;
import org.Tienda.modelo.Libro;
import org.Tienda.modelo.Usuario;
import org.Tienda.utils.JPAUtils;

public class UsuarioServiceImpl implements Usuarioservice{

    @Override
    public void registrarUsuario(String nombre, String email) {
        EntityManager em = JPAUtils.em();
        try {
            Usuario usuarioNuevo = new Usuario(nombre, email);

            em.getTransaction().begin();

            em.persist(usuarioNuevo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Usuario porId(Long id) {
        EntityManager em = JPAUtils.em();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public void borrarUsario(Long id) {
        EntityManager em = JPAUtils.em();
        try {

            em.getTransaction().begin();
            Usuario user =  em.find(Usuario.class, id);
            em.remove(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}

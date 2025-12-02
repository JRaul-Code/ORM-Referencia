package org.Tienda.servicios;


import jakarta.persistence.EntityManager;
import org.Tienda.modelo.Libro;
import org.Tienda.modelo.LineaPedido;
import org.Tienda.modelo.Pedido;
import org.Tienda.modelo.Usuario;
import org.Tienda.utils.JPAUtils;

public class PedidoServiceImpl {
    public Pedido crearPedido(long usuarioId) {
        EntityManager em = JPAUtils.em();
        try {
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class, usuarioId);
            if (u == null) throw new IllegalArgumentException("Usuario no existe: " + usuarioId);
            Pedido p = new Pedido();
            p.setUsuario(u);
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } catch (RuntimeException ex) {
            em.getTransaction().rollback(); throw ex;
        } finally { em.close(); }
    }

    public void agregarLinea(long pedidoId, String isbn, int cantidad) {
        EntityManager em = JPAUtils.em();
        try {
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class, pedidoId);
            if (p == null) throw new IllegalArgumentException("Pedido no existe: " + pedidoId);
            Libro l = em.find(Libro.class, isbn);
            if (l == null) throw new IllegalArgumentException("Libro no existe: " + isbn);
            if (l.getCantidad() < cantidad) throw new IllegalArgumentException("Stock insuficiente");

            LineaPedido lp = new LineaPedido();
            lp.setLibro(l);
            lp.setCantidad(cantidad);
            lp.setPvp(l.getPrecio());

            p.addLinea(lp);              // asocia bidireccional
            l.setCantidad(l.getCantidad() - cantidad);

            em.merge(l);
            em.merge(p);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback(); throw ex;
        } finally { em.close(); }
    }

    public void finalizarPedido(long pedidoId) {
        EntityManager em = JPAUtils.em();
        try {
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class, pedidoId);
            if (p == null) throw new IllegalArgumentException("Pedido no existe: " + pedidoId);
            // Aquí podrías marcar estado, calcular totales, etc.
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback(); throw ex;
        } finally { em.close(); }
    }
}

package org.Tienda;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.Tienda.controlador.AppControlador;
import org.Tienda.modelo.Libro;
import org.Tienda.utils.JPAUtils;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        new AppControlador().run();
        EntityManager em = JPAUtils.em();


        System.out.println("\n===== CICLO DE VIDA COMPLETO =====\n");
        // 1️⃣ PERSIST → INSERT

        System.out.println("1️⃣ persist(): creando libro...");

        Libro libro = new Libro();
        libro.setIsbn("9781234567890");
        libro.setTitulo("Aprendiendo JPA");
        libro.setAutor("Juan Pérez");
        libro.setCantidad(10);
        libro.setPrecio(20.00);


        em.getTransaction().begin();
        em.persist(libro);      // INSERT
        em.getTransaction().commit();

        System.out.println("Libro guardado: " + libro + "\n");


        // 2️⃣ MERGE → UPDATE

        System.out.println("2️⃣ merge(): actualizando precio...");

        em.getTransaction().begin();

        libro.setPrecio(25.00); // Cambiamos el objeto

        em.merge(libro);        // UPDATE

        em.getTransaction().commit();


        System.out.println("Libro actualizado: " + libro + "\n");


        // 3️⃣ REFRESH → Vuelve a BD ignorando lo que haya en memoria

        System.out.println("3️⃣ refresh(): recargando desde BD...");

        libro.setPrecio(30.00);       // Lo cambio en Java

        em.refresh(libro);            // Pero JPA lo pisa con el valor de BD


        System.out.println("Tras refresh() → precio real: " + libro + "\n");


        // 4️⃣ DETACH → los cambios ya no se guardan

        System.out.println("4️⃣ detach(): desasociando la entidad...");

        em.detach(libro);


        libro.setPrecio(50.00);  // Esto YA NO se guarda


        em.getTransaction().begin();

        em.merge(libro);         // merge lo vuelve a asociar → sí actualiza

        em.getTransaction().commit();


        System.out.println("Tras detach + merge → " + libro + "\n");


        // 5️⃣ REMOVE → DELETE
        System.out.println("5️⃣ remove(): eliminando libro...");

        em.getTransaction().begin();
        Libro l = em.find(Libro.class, "9781234567890");
        em.remove(l);            // DELETE
        em.getTransaction().commit();
        System.out.println("Libro borrado.\n");



        JPAUtils.close(); // cierra EntityManagerFactory al salir


    }
}
package org.Tienda.vista;

// view/ConsoleView.java
import org.Tienda.modelo.Libro;

import java.util.Scanner;
import java.util.List;

public class VistaConsola {
    private final Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("\n=== TIENDA ===");
        System.out.println("1) Listar libros");
        System.out.println("2) Crear pedido (usuario existente)");
        System.out.println("3) Añadir línea a pedido");
        System.out.println("4) Finalizar pedido");
        System.out.println("5) Añadir usuario");
        System.out.println("6) Buscar usuario por ID (prueba)");
        System.out.println("7) Borrar usuario por ID (prueba)");

        System.out.println("0) Salir");
        System.out.print("Opción: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public void mostrarLibros(List<Libro> libros) {
        System.out.println("\n--- Catálogo ---");
        for (Libro l : libros) {
            System.out.printf("%s | %s | %.2f€ | stock=%d%n",
                    l.getIsbn(), l.getTitulo(), l.getPrecio(), l.getCantidad());
        }
    }

    public String pedirIsbn() {
        System.out.print("ISBN: ");
        return sc.nextLine().trim();
    }

    public int pedirCantidad() {
        System.out.print("Cantidad: ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    public long pedirUsuarioId() {
        System.out.print("ID usuario: ");
        return Long.parseLong(sc.nextLine().trim());
    }

    public long pedirPedidoId() {
        System.out.print("ID pedido: ");
        return Long.parseLong(sc.nextLine().trim());
    }

    public String pedirNombre() {
        System.out.print("Nombre: ");
        return sc.nextLine().trim();
    }

    public String pedirEmail() {
        System.out.print("Email: ");
        return sc.nextLine().trim();
    }

    public void msg(String m) {
        System.out.println(m);
    }
}


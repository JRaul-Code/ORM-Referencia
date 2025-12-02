package org.Tienda.controlador;

import org.Tienda.modelo.Usuario;
import org.Tienda.servicios.LibroServiceImpl;
import org.Tienda.servicios.PedidoServiceImpl;
import org.Tienda.servicios.UsuarioServiceImpl;
import org.Tienda.vista.VistaConsola;

public class AppControlador {

    private final VistaConsola view = new VistaConsola();
    private final LibroServiceImpl libroService = new LibroServiceImpl();
    private final PedidoServiceImpl pedidoService = new PedidoServiceImpl();
    private final UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();


    public void run(){
        int op;
        do {
            op = view.menu();
            try {
                switch (op) {
                    case 1 -> view.mostrarLibros(libroService.listar());
                    case 2 -> {
                        long uid = view.pedirUsuarioId();
                        var p = pedidoService.crearPedido(uid);
                        view.msg("Pedido creado con id " + p.getId());
                    }
                    case 3 -> {
                        long pid = view.pedirPedidoId();
                        String isbn = view.pedirIsbn();
                        int cant = view.pedirCantidad();
                        pedidoService.agregarLinea(pid, isbn, cant);
                        view.msg("Línea añadida.");
                    }
                    case 4 -> {
                        long pid = view.pedirPedidoId();
                        pedidoService.finalizarPedido(pid);
                        view.msg("Pedido finalizado.");
                    }
                    case 5 -> {
                        view.msg("Introduccion de nuevo usuario:");
                        String nombre = view.pedirNombre();
                        String email =  view.pedirEmail();
                        usuarioService.registrarUsuario(nombre, email);
                        view.msg("Usuario añadido.");
                    }
                    case 6 -> {
                        // opción de prueba para ver usuario por id
                        long uid = view.pedirUsuarioId();
                        Usuario u = usuarioService.porId(uid);
                        if (u != null) {
                            view.msg("Usuario encontrado: ID=" + u.getId() + ", Nombre=" + u.getNombre() + ", Email=" + u.getEmail());
                        } else {
                            view.msg("Usuario no encontrado.");
                        }
                    }
                    case 7 -> {
                        // opción de prueba para borrar usuario por id
                        long uid = view.pedirUsuarioId();
                        usuarioService.borrarUsario(uid);
                        view.msg("Usuario borrado si existía.");
                    }

                    case 0 -> view.msg("Saliendo...");
                    default -> view.msg("Opción inválida.");
                }
            } catch (Exception e) {
                view.msg("ERROR: " + e.getMessage());
            }
        } while (op != 0);
    }


}

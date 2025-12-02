package org.Tienda.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="pedidos")
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="usuario_id")
    private Usuario usuario;

    private LocalDateTime fecha = LocalDateTime.now();

    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<LineaPedido> lineas = new ArrayList<>();

    public void addLinea(LineaPedido lp) {
        lp.setPedido(this);
        lineas.add(lp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }
}


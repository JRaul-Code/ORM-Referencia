package org.Tienda.modelo;


import jakarta.persistence.*;

@Entity @Table(name="lineas_pedido")
public class LineaPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @ManyToOne(optional=false) @JoinColumn(name="libro_isbn")
    private Libro libro;

    private Integer cantidad;
    private Double pvp;

    public void setPedido(Pedido p) {
        this.pedido = p;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }
}


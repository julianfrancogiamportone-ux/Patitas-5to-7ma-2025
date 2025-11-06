package com.patitas.patitas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class CarritoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación: un cliente puede tener varios carritos (historial)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientesModel cliente;

    // Relación: un carrito puede contener muchos productos
    @ManyToMany
    @JoinTable(
        name = "carrito_producto",
        joinColumns = @JoinColumn(name = "carrito_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private double total = 0.0;

    // --- Constructor vacío ---
    public CarritoModel() {}

    // --- Constructor con cliente ---
    public CarritoModel(ClientesModel cliente) {
        this.cliente = cliente;
        this.fechaCreacion = LocalDateTime.now();
    }

    // --- Lógica para calcular el total ---
    public void calcularTotal() {
        this.total = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    // --- Métodos auxiliares ---
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        calcularTotal();
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ClientesModel getCliente() { return cliente; }
    public void setCliente(ClientesModel cliente) { this.cliente = cliente; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        calcularTotal();
    }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}

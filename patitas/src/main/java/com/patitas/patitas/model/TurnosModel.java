package com.patitas.patitas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class TurnosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private String motivo;
    private String estado; // simple String en lugar de Enum

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientesModel cliente;

    public TurnosModel() { }

    public TurnosModel(Long id, LocalDateTime fechaHora, String motivo, String estado, ClientesModel cliente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public ClientesModel getCliente() { return cliente; }
    public void setCliente(ClientesModel cliente) { this.cliente = cliente; }
}

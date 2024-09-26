package com.ultima.entrega.models;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "VENTA")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la venta", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    @Schema(description = "Fecha de la venta", example = "2024-09-02", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date fecha;

    @Column(name = "TOTAL")
    @Schema(description = "Total de la venta", example = "1499.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "CLIENTE")
    @JsonIgnore
    @Schema(description = "Cliente asociado a la venta",example = "1")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @Schema(description = "Lista de productos en la venta")
    private List<VentaProducto> ventaProductos;

    // Getters y Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @JsonIgnore
    public List<VentaProducto> getVentaProductos() {
        return ventaProductos;
    }

    public void setVentaProductos(List<VentaProducto> ventaProductos) {
        this.ventaProductos = ventaProductos;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}

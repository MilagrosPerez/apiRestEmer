package com.ultima.entrega.models;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la venta-producto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    @JsonIgnoreProperties(value = "venta")
    @Schema(description = "Venta asociada a este detalle de producto")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @Schema(description = "Producto asociado a esta venta")
    private Producto producto;

    @Column(name = "CANTIDAD")
    @Schema(description = "Cantidad del producto en la venta", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer cantidad;


// Getters y Setters


    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
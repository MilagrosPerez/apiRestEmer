package com.ultima.entrega.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del producto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "NOMBRE")
    @Schema(description = "Nombre del producto", example = "Teclado Mecánico", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Column(name = "PRECIO")
    @Schema(description = "Precio del producto", example = "199.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double precio;

    @Column(name = "STOCK")
    @Schema(description = "Cantidad de stock disponible del producto", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private int stock;

    // Getters y Setters...
    // Getters y Setters


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int sotck) {
        this.stock = sotck;
    }


}
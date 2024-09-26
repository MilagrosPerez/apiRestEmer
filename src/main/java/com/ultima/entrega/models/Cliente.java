package com.ultima.entrega.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador ID único del Cliente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Column(name = "NOMBRE")
    @Schema(description = "Primer y segundo nombre del Cliente", example = "Emerson Gabriel", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Column(name = "APELLIDO")
    @Schema(description = "Apellidos del Cliente", example = "Matijasevic Sbardella", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;

    @Column(name = "DNI")
    @Schema(description = "DNI del Cliente sin puntos", example = "40586118", requiredMode = Schema.RequiredMode.REQUIRED)
    private long dni;

    @Column(name = "ISACTIVO")
    @Schema(description = "Estado de actividad del Cliente", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean isactivo;

    @Column(name = "REPUTACION")
    @Schema(description = "Reputación del Cliente, en un rango de 0 a 100", example = "85", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int reputacion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Schema(description = "NO INCLUIR DOMICILIO - CAMPO EN DESARROLLO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, accessMode = Schema.AccessMode.READ_ONLY)
    private List<Domicilio> domicilio;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "Lista de ventas realizadas por el Cliente", requiredMode = Schema.RequiredMode.NOT_REQUIRED, accessMode = Schema.AccessMode.READ_ONLY)
    private List<Venta> venta;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, long dni, Boolean isactivo, int reputacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.isactivo = isactivo;
        this.reputacion = reputacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public Boolean getIsactivo() {
        return isactivo;
    }

    public void setIsactivo(Boolean isactivo) {
        this.isactivo = isactivo;
    }

    public int getReputacion() {
        return reputacion;
    }

    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }

    public List<Domicilio> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(List<Domicilio> domicilio) {
        this.domicilio = domicilio;
    }

    public List<Venta> getVenta() {
        return venta;
    }

    public void setVenta(List<Venta> venta) {
        this.venta = venta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return getId() == cliente.getId() &&
                getDni() == cliente.getDni() &&
                getReputacion() == cliente.getReputacion() &&
                getNombre().equals(cliente.getNombre()) &&
                getApellido().equals(cliente.getApellido()) &&
                Objects.equals(getIsactivo(), cliente.getIsactivo()) &&
                Objects.equals(getDomicilio(), cliente.getDomicilio()) &&
                Objects.equals(getVenta(), cliente.getVenta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellido(), getDni(), getIsactivo(), getReputacion(), getDomicilio(), getVenta());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", isactivo=" + isactivo +
                ", reputacion=" + reputacion +
                '}';
    }
}

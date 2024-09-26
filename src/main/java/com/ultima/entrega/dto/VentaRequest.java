package com.ultima.entrega.dto;
import com.ultima.entrega.dto.ProductoVentaRequest;

import java.util.List;

public class VentaRequest {
    private Long clienteId;
    private List<ProductoVentaRequest> productos;

    // Getters y Setters

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ProductoVentaRequest> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoVentaRequest> productos) {
        this.productos = productos;
    }
}


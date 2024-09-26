package com.ultima.entrega.services;

import com.ultima.entrega.models.Producto;
import com.ultima.entrega.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto agregarProducto(Producto producto) {
        // Validar stock y precio no negativos
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        // Guardar producto
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarProducto(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

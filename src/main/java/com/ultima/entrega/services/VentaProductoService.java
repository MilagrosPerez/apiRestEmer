package com.ultima.entrega.services;

import com.ultima.entrega.models.Producto;
import com.ultima.entrega.models.Venta;
import com.ultima.entrega.models.VentaProducto;
import com.ultima.entrega.repositories.ProductoRepository;
import com.ultima.entrega.repositories.VentaRepository;
import com.ultima.entrega.repositories.VentaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentaProductoService {

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Transactional
    public VentaProducto agregarVentaProducto(VentaProducto ventaProducto) {
        // Verificar y cargar la entidad Producto
        if (ventaProducto.getProducto() != null && ventaProducto.getProducto().getId() != null) {
            Producto producto = productoRepository.findById(ventaProducto.getProducto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
            ventaProducto.setProducto(producto);
        }

        // Verificar y cargar la entidad Venta
        if (ventaProducto.getVenta() != null && ventaProducto.getVenta().getId() != null) {
            Venta venta = ventaRepository.findById(ventaProducto.getVenta().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
            ventaProducto.setVenta(venta);
        }

        // Guardar el VentaProducto
        return ventaProductoRepository.save(ventaProducto);
    }

    public Optional<VentaProducto> buscarVentaProducto(Long id) {
        return ventaProductoRepository.findById(id);
    }

    public List<VentaProducto> listarVentaProductos() {
        return ventaProductoRepository.findAll();
    }

    public void eliminarVentaProducto(Long id) {
        ventaProductoRepository.deleteById(id);
    }
}

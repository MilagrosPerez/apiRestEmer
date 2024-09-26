package com.ultima.entrega.services;

import com.ultima.entrega.models.Cliente;
import com.ultima.entrega.models.Producto;
import com.ultima.entrega.models.Venta;
import com.ultima.entrega.models.VentaProducto;
import com.ultima.entrega.dto.VentaRequest;
import com.ultima.entrega.dto.ProductoVentaRequest;
import com.ultima.entrega.repositories.ClienteRepository;
import com.ultima.entrega.repositories.ProductoRepository;
import com.ultima.entrega.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Venta agregarVenta(VentaRequest ventaRequest) {
        // Validar existencia del cliente
        Optional<Cliente> clienteOpt = clienteRepository.findById(ventaRequest.getClienteId());
        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("El cliente no existe.");
        }

        // Crear la venta
        Venta venta = new Venta();
        venta.setCliente(clienteOpt.get());
        venta.setFecha(new Date());

        List<VentaProducto> ventaProductos = new ArrayList<>();

        // Validar productos y stock
        for (ProductoVentaRequest productoVentaReq : ventaRequest.getProductos()) { // Cambiado para usar ProductoVentaRequest
            Optional<Producto> productoOpt = productoRepository.findById(productoVentaReq.getProductoId());

            if (productoOpt.isEmpty()) {
                throw new IllegalArgumentException("El producto con ID " + productoVentaReq.getProductoId() + " no existe.");
            }

            Producto producto = productoOpt.get();

            // Verificar stock
            if (producto.getStock() < productoVentaReq.getCantidad()) {
                throw new IllegalArgumentException("No hay suficiente stock para el producto: " + producto.getNombre());
            }

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - productoVentaReq.getCantidad());
            productoRepository.save(producto);

            // Crear VentaProducto
            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setVenta(venta);
            ventaProducto.setProducto(producto);
            ventaProducto.setCantidad(productoVentaReq.getCantidad());
            ventaProductos.add(ventaProducto);
        }

        venta.setVentaProductos(ventaProductos);
        venta.setTotal(ventaProductos.stream()
                .mapToDouble(vp -> vp.getProducto().getPrecio() * vp.getCantidad())
                .sum());

        // Guardar la venta
        return ventaRepository.save(venta);
    }

    public Optional<Venta> buscarVenta(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}

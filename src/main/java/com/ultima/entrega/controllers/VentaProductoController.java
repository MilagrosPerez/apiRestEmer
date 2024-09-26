package com.ultima.entrega.controllers;

import com.ultima.entrega.models.VentaProducto;
import com.ultima.entrega.services.VentaProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta-productos")
@Tag(name = "VentaProducto", description = "Operaciones con los productos vendidos en cada venta")
public class VentaProductoController {

    @Autowired
    private VentaProductoService ventaProductoService;

    @PostMapping("/agregar")
    @Operation(summary = "Agregar un Producto a una Venta", description = "Permite agregar un nuevo producto a una venta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado a la venta con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - producto o venta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<VentaProducto> agregarVentaProducto(@RequestBody VentaProducto ventaProducto) {
        try {
            VentaProducto nuevoVentaProducto = ventaProductoService.agregarVentaProducto(ventaProducto);
            return new ResponseEntity<>(nuevoVentaProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar un Producto en una Venta por ID", description = "Recupera los detalles de un producto vendido en una venta dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto en la venta encontrado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto en la venta no encontrado con el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<VentaProducto> buscarVentaProducto(@PathVariable Long id) {
        Optional<VentaProducto> ventaProducto = ventaProductoService.buscarVentaProducto(id);
        return ventaProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos los Productos en Ventas", description = "Recupera una lista de todos los productos vendidos en todas las ventas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos en ventas obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<VentaProducto> listarVentaProductos() {
        return ventaProductoService.listarVentaProductos();
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un Producto de una Venta", description = "Elimina un producto de una venta dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado de la venta con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado en la venta con el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Void> eliminarVentaProducto(@PathVariable Long id) {
        try {
            ventaProductoService.eliminarVentaProducto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.ultima.entrega.controllers;

import com.ultima.entrega.models.Venta;
import com.ultima.entrega.dto.VentaRequest;
import com.ultima.entrega.services.VentaService;
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
@RequestMapping("/ventas")
@Tag(name = "Venta", description = "Operaciones con las ventas ABML")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/agregar")
    @Operation(summary = "Agregar una Venta", description = "Permite agregar una nueva venta al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta agregada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - error de validación de campos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Venta> agregarVenta(@RequestBody VentaRequest ventaRequest) {
        try {
            Venta venta = ventaService.agregarVenta(ventaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(venta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar una Venta por ID", description = "Recupera los detalles de una venta dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada con éxito"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada con el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Venta> buscarVenta(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.buscarVenta(id);
        if (venta.isPresent()) {
            return ResponseEntity.ok(venta.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas las Ventas", description = "Recupera una lista de todas las ventas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ventas obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        return ResponseEntity.ok(ventas);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar una Venta", description = "Elimina una venta del sistema dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venta eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada con el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.buscarVenta(id);
        if (venta.isPresent()) {
            ventaService.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

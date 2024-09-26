package com.ultima.entrega.controllers;

import com.ultima.entrega.models.Cliente;
import com.ultima.entrega.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Operaciones con los clientes ABML")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los Clientes", description = "Recupera una lista de todos los clientes en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/agregar")
    @Operation(summary = "Alta de un Cliente", description = "Agregar un cliente. Asegúrate de que contiene todos los campos obligatorios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente agregado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - error de validación de campos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Cliente agregarCliente(@RequestBody Cliente cliente) {
        return clienteService.agregarCliente(cliente);
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Ver un Cliente", description = "Recupera los detalles de un cliente dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado con el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Optional<Cliente> buscarCliente(@PathVariable Long id) {
        return clienteService.buscarCliente(id);
    }

    // Nuevo método para eliminar un cliente
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un Cliente", description = "Elimina un cliente dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado con el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}

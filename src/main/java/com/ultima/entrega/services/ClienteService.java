package com.ultima.entrega.services;


import com.ultima.entrega.models.Cliente;
import com.ultima.entrega.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(Long id) {
        return clienteRepository.findById(id);
    }
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

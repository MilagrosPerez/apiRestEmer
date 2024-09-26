package com.ultima.entrega.repositories;

import com.ultima.entrega.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
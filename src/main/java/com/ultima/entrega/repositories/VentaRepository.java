package com.ultima.entrega.repositories;

import com.ultima.entrega.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> { }
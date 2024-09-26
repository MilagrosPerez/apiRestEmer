package com.ultima.entrega.repositories;

import com.ultima.entrega.models.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, Long> {
}

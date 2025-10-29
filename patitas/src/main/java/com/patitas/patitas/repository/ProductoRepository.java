package com.patitas.patitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patitas.patitas.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

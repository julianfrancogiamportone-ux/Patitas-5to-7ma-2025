package com.patitas.patitas.repository;

import com.patitas.patitas.model.CarritoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<CarritoModel, Long> {
}

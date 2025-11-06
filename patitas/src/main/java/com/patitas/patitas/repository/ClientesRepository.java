package com.patitas.patitas.repository;

import com.patitas.patitas.model.ClientesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<ClientesModel, Long> {
}

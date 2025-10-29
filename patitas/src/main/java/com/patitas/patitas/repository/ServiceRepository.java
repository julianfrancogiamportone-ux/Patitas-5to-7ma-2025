package com.patitas.patitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patitas.patitas.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}

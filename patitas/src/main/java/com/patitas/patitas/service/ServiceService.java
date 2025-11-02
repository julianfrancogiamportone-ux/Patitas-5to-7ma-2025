package com.patitas.patitas.service;

import com.patitas.patitas.model.Service;
import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<Service> getAllServices();
    Optional<Service> getServiceById(Long id);
    Service saveService(Service service);
    Service updateService(Long id, Service service);
    void deleteService(Long id);
}

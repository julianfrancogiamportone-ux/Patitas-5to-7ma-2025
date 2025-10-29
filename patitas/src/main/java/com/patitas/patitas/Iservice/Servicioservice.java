package com.patitas.patitas.Iservice;

import com.patitas.patitas.model.Service;
import com.patitas.patitas.repository.ServiceRepository;
import com.patitas.patitas.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Servicioservice implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Long id, Service serviceDetails) {
        return serviceRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(serviceDetails.getNombre());
                    existing.setDescripcion(serviceDetails.getDescripcion());
                    existing.setPrecio(serviceDetails.getPrecio());
                    return serviceRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id " + id));
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}

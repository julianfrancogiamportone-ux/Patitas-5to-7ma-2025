package com.patitas.patitas.controller;

import com.patitas.patitas.model.Service;
import com.patitas.patitas.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<Service> listarServicios() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> obtenerServicio(@PathVariable Long id) {
        Optional<Service> opt = serviceService.getServiceById(id);
        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Service> crearServicio(@RequestBody Service service) {
        Service creado = serviceService.saveService(service);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> actualizarServicio(@PathVariable Long id, @RequestBody Service service) {
        try {
            Service actualizado = serviceService.updateService(id, service);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}

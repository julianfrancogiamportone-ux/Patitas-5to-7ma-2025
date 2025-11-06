package com.patitas.patitas.controller;

import com.patitas.patitas.Iservice.TurnosIservice;
import com.patitas.patitas.model.TurnosModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*")
public class TurnosController {

    private final TurnosIservice service;

    public TurnosController(TurnosIservice service) {
        this.service = service;
    }

    @GetMapping
    public List<TurnosModel> getAllTurnos() {
        return service.findAllTurnos();
    }

    @GetMapping("/{id}")
    public Optional<TurnosModel> getTurnoById(@PathVariable Long id) {
        return service.findTurnoById(id);
    }

    @PostMapping
    public TurnosModel createTurno(@RequestBody TurnosModel turno) {
        return service.saveTurno(turno);
    }

    @PutMapping("/{id}")
    public TurnosModel updateTurno(@PathVariable Long id, @RequestBody TurnosModel turno) {
        turno.setId(id);
        return service.saveTurno(turno);
    }

    @DeleteMapping("/{id}")
    public void deleteTurno(@PathVariable Long id) {
        service.deleteTurno(id);
    }

    @DeleteMapping
    public void deleteTurno(@RequestBody TurnosModel turno) {
        service.deleteTurno(turno);
    }

    @DeleteMapping("/all")
    public void deleteAllTurnos() {
        service.deleteAllTurnos();
    }
}

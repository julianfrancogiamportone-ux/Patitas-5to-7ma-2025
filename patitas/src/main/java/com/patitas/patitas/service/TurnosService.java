package com.patitas.patitas.service;

import com.patitas.patitas.Iservice.TurnosIservice;
import com.patitas.patitas.model.TurnosModel;
import com.patitas.patitas.repository.TurnosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnosService implements TurnosIservice {

    private final TurnosRepository repo;

    public TurnosService(TurnosRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TurnosModel> findAllTurnos() {
        return repo.findAll();
    }

    @Override
    public Optional<TurnosModel> findTurnoById(Long id) {
        return repo.findById(id);
    }

    @Override
    public TurnosModel saveTurno(TurnosModel turno) {
        return repo.save(turno);
    }

    @Override
    public void deleteTurno(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteTurno(TurnosModel turno) {
        repo.delete(turno);
    }

    @Override
    public void deleteAllTurnos() {
        repo.deleteAll();
    }
}

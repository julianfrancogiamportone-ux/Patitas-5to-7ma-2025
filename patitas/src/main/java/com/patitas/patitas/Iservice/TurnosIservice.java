package com.patitas.patitas.Iservice;

import com.patitas.patitas.model.TurnosModel;
import java.util.List;
import java.util.Optional;

public interface TurnosIservice {
    List<TurnosModel> findAllTurnos();
    Optional<TurnosModel> findTurnoById(Long id);
    TurnosModel saveTurno(TurnosModel turno);
    void deleteTurno(Long id);
    void deleteTurno(TurnosModel turno);
    void deleteAllTurnos();
}

package com.patitas.patitas.service;

import com.patitas.patitas.Iservice.CarritoIservice;
import com.patitas.patitas.model.CarritoModel;
import com.patitas.patitas.repository.CarritoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService implements CarritoIservice {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Override
    public List<CarritoModel> findAllCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public CarritoModel saveCarrito(CarritoModel carrito) {
        carrito.calcularTotal();
        return carritoRepository.save(carrito);
    }

    @Override
    public Optional<CarritoModel> findCarritoById(Long id) {
        return carritoRepository.findById(id);
    }

    @Override
    public void deleteCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public void deleteCarrito(CarritoModel carrito) {
        carritoRepository.delete(carrito);
    }

    @Override
    public void deleteAllCarritos() {
        carritoRepository.deleteAll();
    }
}

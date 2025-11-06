package com.patitas.patitas.controller;

import com.patitas.patitas.model.CarritoModel;
import com.patitas.patitas.Iservice.CarritoIservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoIservice carritoService;

    public CarritoController(CarritoIservice carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public List<CarritoModel> getAllCarritos() {
        return carritoService.findAllCarritos();
    }

    @GetMapping("/{id}")
    public Optional<CarritoModel> getCarritoById(@PathVariable Long id) {
        return carritoService.findCarritoById(id);
    }

    @PostMapping
    public CarritoModel createCarrito(@RequestBody CarritoModel carrito) {
        return carritoService.saveCarrito(carrito);
    }

    @DeleteMapping("/{id}")
    public void deleteCarrito(@PathVariable Long id) {
        carritoService.deleteCarrito(id);
    }

    @DeleteMapping
    public void deleteCarrito(@RequestBody CarritoModel carrito) {
        carritoService.deleteCarrito(carrito);
    }

    @DeleteMapping("/all")
    public void deleteAllCarritos() {
        carritoService.deleteAllCarritos();
    }
}

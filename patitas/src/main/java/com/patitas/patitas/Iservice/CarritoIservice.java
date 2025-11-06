package com.patitas.patitas.Iservice;

import com.patitas.patitas.model.CarritoModel;
import java.util.List;
import java.util.Optional;

public interface CarritoIservice {
    List<CarritoModel> findAllCarritos();
    CarritoModel saveCarrito(CarritoModel carrito);
    Optional<CarritoModel> findCarritoById(Long id);
    void deleteCarrito(Long id);
    void deleteCarrito(CarritoModel carrito);
    void deleteAllCarritos();
}

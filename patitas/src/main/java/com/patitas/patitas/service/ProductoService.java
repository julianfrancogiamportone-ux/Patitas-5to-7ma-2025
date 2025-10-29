package com.patitas.patitas.service;

import com.patitas.patitas.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listarProductos();
    Optional<Producto> obtenerProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
}

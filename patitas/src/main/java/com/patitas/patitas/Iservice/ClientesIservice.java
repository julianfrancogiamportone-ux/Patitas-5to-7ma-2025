package com.patitas.patitas.Iservice;

import com.patitas.patitas.model.ClientesModel;
import java.util.List;
import java.util.Optional;

public interface ClientesIservice {
    List<ClientesModel> findAllClientes();
    Optional<ClientesModel> findClienteById(Long id);
    ClientesModel saveCliente(ClientesModel cliente);
    void deleteCliente(Long id);
    void deleteCliente(ClientesModel cliente);
    void deleteAllClientes();
}

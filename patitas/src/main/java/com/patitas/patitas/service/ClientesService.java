package com.patitas.patitas.service;

import com.patitas.patitas.Iservice.ClientesIservice;
import com.patitas.patitas.model.ClientesModel;
import com.patitas.patitas.repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService implements ClientesIservice {

    private final ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<ClientesModel> findAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Optional<ClientesModel> findClienteById(Long id) {
        return clientesRepository.findById(id);
    }

    @Override
    public ClientesModel saveCliente(ClientesModel cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clientesRepository.deleteById(id);
    }

    @Override
    public void deleteCliente(ClientesModel cliente) {
        clientesRepository.delete(cliente);
    }

    @Override
    public void deleteAllClientes() {
        clientesRepository.deleteAll();
    }
}

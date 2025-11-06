package com.patitas.patitas.controller;

import com.patitas.patitas.model.ClientesModel;
import com.patitas.patitas.Iservice.ClientesIservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClientesController {

    private final ClientesIservice clientesService;

    public ClientesController(ClientesIservice clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<ClientesModel> getAllClientes() {
        return clientesService.findAllClientes();
    }

    @GetMapping("/{id}")
    public Optional<ClientesModel> getClienteById(@PathVariable Long id) {
        return clientesService.findClienteById(id);
    }

    @PostMapping
    public ClientesModel createCliente(@RequestBody ClientesModel cliente) {
        return clientesService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public ClientesModel updateCliente(@PathVariable Long id, @RequestBody ClientesModel cliente) {
        cliente.setId(id);
        return clientesService.saveCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clientesService.deleteCliente(id);
    }

    @DeleteMapping
    public void deleteCliente(@RequestBody ClientesModel cliente) {
        clientesService.deleteCliente(cliente);
    }

    @DeleteMapping("/all")
    public void deleteAllClientes() {
        clientesService.deleteAllClientes();
    }
}

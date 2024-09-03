package com.project.gestclients.gest.clients.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;
    @Autowired
    private final ClienteMapper clienteMapper;

    @GetMapping
    public List<ClienteDTO> getAll() {
        return clienteService.getClientes().stream()
                .map(clienteMapper::clienteToClienteDTO) // Mapea cada Cliente a ClienteDTO
                .collect(Collectors.toList());
    }

    @PostMapping
    public void saveUpdate(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO); // Convierte el DTO a Cliente
        clienteService.saveOrUpdateCliente(cliente);
    }

    @GetMapping("{clienteId}")
    public Optional<ClienteDTO> getCliente(@PathVariable("clienteId") Long id) {
        return clienteService.getCliente(id)
                .map(clienteMapper::clienteToClienteDTO); // Convierte el Cliente a ClienteDTO si existe
    }

    @DeleteMapping("{clienteId}")
    public void delete(@PathVariable("clienteId") Long id) {
        clienteService.deleteCliente(id);
    }
}

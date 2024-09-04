package com.project.gestclients.gest.clients.Service;

import java.util.List;
import java.util.Optional;

import com.project.gestclients.gest.clients.Entidad.HCliente;
import com.project.gestclients.gest.clients.DTO.ClienteDTO;
import com.project.gestclients.gest.clients.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ClienteService {

    public List<ClienteDTO> getClientes();

    public ClienteDTO getCliente(Long id);

    public ClienteDTO saveOrUpdateCliente(ClienteDTO clienteDTO);

    public void deleteCliente(Long id);

}

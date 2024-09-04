package com.project.gestclients.gest.clients.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.project.gestclients.gest.clients.DTO.ClienteDTO;
import com.project.gestclients.gest.clients.Repository.ClienteRepository;
import com.project.gestclients.gest.clients.Mapper.ClienteMapper;
import com.project.gestclients.gest.clients.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> getClientes() {
        return clienteMapper.clientesToClientesDTO(clienteRepository.findAll());
    }

    @Override
    public ClienteDTO getCliente(Long id) {
        return clienteMapper.clienteToClienteDTO(clienteRepository.findById(id));
    }


    @Override
    public ClienteDTO saveOrUpdateCliente(ClienteDTO clienteDTO) {
        return clienteMapper.clienteToClienteDTO(clienteRepository.save(clienteMapper.clienteDTOToCliente(clienteDTO)));
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}

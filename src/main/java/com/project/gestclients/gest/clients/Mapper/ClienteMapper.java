package com.project.gestclients.gest.clients.Mapper;

import com.project.gestclients.gest.clients.Entidad.HCliente;
import com.project.gestclients.gest.clients.DTO.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring") // Configura MapStruct para que use el modelo de componente de Spring
public interface ClienteMapper {

    ClienteDTO clienteToClienteDTO(HCliente hCliente);

    HCliente clienteDTOToCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> clientesToClientesDTO(List<HCliente> hClientes);

    List<HCliente> clientesDTOToClientes(List<ClienteDTO> clientesDTO);

    ClienteDTO clienteToClienteDTO(Optional<HCliente> byId);
}
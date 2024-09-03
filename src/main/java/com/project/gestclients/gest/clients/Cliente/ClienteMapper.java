package com.project.gestclients.gest.clients.Cliente;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Configura MapStruct para que use el modelo de componente de Spring
public interface ClienteMapper {

    ClienteDTO clienteToClienteDTO(Cliente cliente);

    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
}
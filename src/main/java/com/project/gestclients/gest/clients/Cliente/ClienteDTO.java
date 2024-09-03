package com.project.gestclients.gest.clients.Cliente;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    Long id;
    String nombre;
    String apellidos;
    String dni;
    String direccion;
    String telefono;
    Date fechaNacimiento;
    Date fechaAlta;
    Date fechaProximoPago;

}

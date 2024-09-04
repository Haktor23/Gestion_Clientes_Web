package com.project.gestclients.gest.clients.Entidad;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class HCliente {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String nombre;
    @Column(nullable = false)
    String apellidos;
    @Column(nullable = false, unique = true)
    String dni;
    @Column(nullable = false)
    String telefono;
    @Column(nullable = false)
    String direccion;
    @Column(nullable = false)
    Date fechaNacimiento;
    @Column(nullable = false)
    Date fechaAlta;
    @Column(nullable = false)
    Date fechaProximoPago;

}

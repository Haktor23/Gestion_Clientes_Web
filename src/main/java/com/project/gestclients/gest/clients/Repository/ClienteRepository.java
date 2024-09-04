package com.project.gestclients.gest.clients.Repository;

import com.project.gestclients.gest.clients.Entidad.HCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<HCliente, Long> {


}

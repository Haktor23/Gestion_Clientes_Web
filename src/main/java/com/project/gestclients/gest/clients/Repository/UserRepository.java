package com.project.gestclients.gest.clients.Repository;

import java.util.Optional;

import com.project.gestclients.gest.clients.Entidad.HUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<HUser, Long> {

    Optional<HUser> findByUsername(String username);

}

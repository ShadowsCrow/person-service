package com.person.person_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.person.person_service.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}

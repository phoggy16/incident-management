package com.incident.incident_management.repository;

import com.incident.incident_management.model.Incident;
import com.incident.incident_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    Optional<Incident> findByIncidentIdAndUser(UUID incidentId, User user);

    Optional<Incident> findByIncidentNoAndUser(String incidentNo, User user);

    Page<Incident> findByUser(User user, Pageable pageable);
}

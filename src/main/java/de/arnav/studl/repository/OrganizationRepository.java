package de.arnav.studl.repository;

import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByDomain(String domain);
    List<Organization> findByNameContaining(String keyword);
}

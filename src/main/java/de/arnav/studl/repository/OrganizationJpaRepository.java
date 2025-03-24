package de.arnav.studl.repository;

import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationJpaRepository extends JpaRepository<Organization, Long> {
}

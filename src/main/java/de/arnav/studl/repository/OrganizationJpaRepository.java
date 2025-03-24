package de.arnav.studl.repository;

import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationJpaRepository extends JpaRepository<Organization, Long> {
    Integer countUsersByOrganization(Long organizationId);

    List<Organization> findByName(String name);
}

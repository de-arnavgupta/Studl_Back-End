package de.arnav.studl.repository;

import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;]

import java.util.Optional;

@Repository
public interface OrganizationJpaRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByOrganizationName(String organizationName);
    Optional<Organization> findByDomainName(String domainName);
}

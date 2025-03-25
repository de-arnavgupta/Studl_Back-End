package de.arnav.studl.repository;

import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationJpaRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByOrganizationName(String name);
    Optional<Organization> findByDomainName(String domainName);
    boolean existsByDomain(String domain);
    Integer countUsersByOrganization(Long organizationId);
}

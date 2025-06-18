package de.arnav.studl.repository;

import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrganizationJpaRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByOrganizationName(String organizationName);
    Optional<Organization> findByDomainName(String domainName);
    boolean existsByDomainName(String domain);
    Integer countUsersByOrganizationId(Long organizationId);

    @Query("SELECT o.tld FROM Organization o WHERE o.id = :organizationId")
    Set<String> findTldsByOrganizationId(@Param("organizationId") Long organizationId);

    @Query("SELECT o.subDomainNames FROM Organization o WHERE o.id = :organizationId")
    Set<String> findSubDomainsByOrganizationId(@Param("organizationId") Long organizationId);

}

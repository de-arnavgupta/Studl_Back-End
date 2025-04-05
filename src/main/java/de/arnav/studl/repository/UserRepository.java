package de.arnav.studl.repository;

import de.arnav.studl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByOrganization_OrganizationId(Long organizationId);
    List<User> findByNameContaining(String keyword);
}

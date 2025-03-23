package de.arnav.studl.repository;

import de.arnav.studl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByOrganization_OrganizationId(Long organizationId);
    List<User> findByNameContaining(String keyword);
}

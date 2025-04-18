package de.arnav.studl.repository;

import de.arnav.studl.model.UserRole;
import de.arnav.studl.model.User;
import de.arnav.studl.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUser_UserId(Long userId);
    Optional<UserRole> findByUserAndRole(User user, Role role);
}

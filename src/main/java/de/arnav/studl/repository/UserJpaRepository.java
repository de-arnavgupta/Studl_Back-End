package de.arnav.studl.repository;

import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.model.Organization;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.Set;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    List<User> findAllByOrganization(Organization organization);
    void deleteByUserEmail(String userEmail);
    List<User> findByUserName(String username);

    @Query("SELECT u.roleType FROM User u WHERE u.userEmail = :email")
    Set<RoleType> findRolesByUserEmail(@Param("email") String email);
}
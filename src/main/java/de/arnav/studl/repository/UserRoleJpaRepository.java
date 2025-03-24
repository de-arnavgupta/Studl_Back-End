package de.arnav.studl.repository;

import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleJpaRepository extends JpaRepository<UserRole, Long> {
    Integer countUsersBy(RoleType roleType);

    List<User> findUsersByRoleType();
}

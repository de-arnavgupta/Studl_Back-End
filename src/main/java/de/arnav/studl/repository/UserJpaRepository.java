package de.arnav.studl.repository;

import de.arnav.studl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findByRoleType();
}

package de.arnav.studl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.arnav.studl.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
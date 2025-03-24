package de.arnav.studl.repository;

import de.arnav.studl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findByRoleType();

    User findByEmail(String email);

    List<User> findByUsername(String username);

    void deleteByEmail();
}

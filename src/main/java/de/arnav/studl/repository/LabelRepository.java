package de.arnav.studl.repository;

import de.arnav.studl.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByName(String name);
    List<Label> findByNameContaining(String keyword);
}

package de.arnav.studl.repository;

import de.arnav.studl.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByName(String name);
    List<Label> findByNameContaining(String keyword);
}

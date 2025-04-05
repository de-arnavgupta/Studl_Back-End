package de.arnav.studl.repository;

import de.arnav.studl.model.Task;
import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
    List<Task> findAllByPriority(TaskPriority priority);
    List<Task> findAllByAssignee_UserId(Long userId);
    List<Task> findAllByReporter_UserId(Long userId);
    List<Task> findAllByDueAtBefore(LocalDateTime dueAt);
    List<Task> findAllByLabels_LabelId(Long labelId);
}

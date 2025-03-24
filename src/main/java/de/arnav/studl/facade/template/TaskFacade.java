package de.arnav.studl.facade.template;

import de.arnav.studl.dto.task.TaskCreateDto;
import de.arnav.studl.dto.task.TaskResponseDto;
import de.arnav.studl.dto.task.TaskUpdateDto;
import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskFacade {
    TaskResponseDto createTask(TaskCreateDto dto);
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto updateTask(Long id, TaskUpdateDto dto);
    void deleteTask(Long id);
    List<TaskResponseDto> getTasksByStatus(TaskStatus status);
    List<TaskResponseDto> getTasksByPriority(TaskPriority priority);
    List<TaskResponseDto> getTasksByAssignee(Long userId);
    List<TaskResponseDto> getTasksByReporter(Long userId);
    List<TaskResponseDto> getTasksDueBefore(LocalDateTime date);
    List<TaskResponseDto> getTasksByLabel(Long labelId);
}

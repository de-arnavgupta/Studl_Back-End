package de.arnav.studl.facade;

import de.arnav.studl.dto.task.TaskCreateDto;
import de.arnav.studl.dto.task.TaskResponseDto;
import de.arnav.studl.dto.task.TaskUpdateDto;
import de.arnav.studl.facade.template.TaskFacade;
import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;
import de.arnav.studl.service.template.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    public TaskFacadeImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public TaskResponseDto createTask(TaskCreateDto dto) {
        return taskService.createTask(dto);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        return taskService.getTaskById(id);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskUpdateDto dto) {
        return taskService.updateTask(id, dto);
    }

    @Override
    public void deleteTask(Long id) {
        taskService.deleteTask(id);
    }

    @Override
    public List<TaskResponseDto> getTasksByStatus(TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @Override
    public List<TaskResponseDto> getTasksByPriority(TaskPriority priority) {
        return taskService.getTasksByPriority(priority);
    }

    @Override
    public List<TaskResponseDto> getTasksByAssignee(Long userId) {
        return taskService.getTasksByAssignee(userId);
    }

    @Override
    public List<TaskResponseDto> getTasksByReporter(Long userId) {
        return taskService.getTasksByReporter(userId);
    }

    @Override
    public List<TaskResponseDto> getTasksDueBefore(LocalDateTime date) {
        return taskService.getTasksDueBefore(date);
    }

    @Override
    public List<TaskResponseDto> getTasksByLabel(Long labelId) {
        return taskService.getTasksByLabel(labelId);
    }
}

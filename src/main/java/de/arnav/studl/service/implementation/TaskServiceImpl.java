package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.task.TaskAdapter;
import de.arnav.studl.dto.task.TaskCreateDto;
import de.arnav.studl.dto.task.TaskResponseDto;
import de.arnav.studl.dto.task.TaskUpdateDto;
import de.arnav.studl.exception.ResourceNotFoundException;
import de.arnav.studl.model.Label;
import de.arnav.studl.model.Task;
import de.arnav.studl.model.User;
import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;
import de.arnav.studl.repository.LabelRepository;
import de.arnav.studl.repository.TaskRepository;
import de.arnav.studl.repository.UserRepository;
import de.arnav.studl.service.template.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskAdapter taskAdapter;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskAdapter taskAdapter,
                           UserRepository userRepository,
                           LabelRepository labelRepository) {
        this.taskRepository = taskRepository;
        this.taskAdapter = taskAdapter;
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public TaskResponseDto createTask(TaskCreateDto dto) {
        Task task = taskAdapter.fromCreateDto(dto);
        if (dto.getAssigneeId() != null) {
            userRepository.findById(dto.getAssigneeId())
                    .ifPresent(task::setAssignee);
        }
        if (dto.getReporterId() != null) {
            userRepository.findById(dto.getReporterId())
                    .ifPresent(task::setReporter);
        }
        if (dto.getLabelIds() != null) {
            dto.getLabelIds().forEach(labelId ->
                    labelRepository.findById(labelId)
                            .ifPresent(label -> task.getLabels().add(label))
            );
        }
        Task savedTask = taskRepository.save(task);
        return taskAdapter.toResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        return taskAdapter.toResponseDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskUpdateDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        task = taskAdapter.updateEntityFromUpdateDto(dto, task);

       if (dto.getAssigneeId() != null) {
            User assignee = userRepository.findById(dto.getAssigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getAssigneeId()));
            task.setAssignee(assignee);
       }

       if (dto.getReporterId() != null) {
            User reporter = userRepository.findById(dto.getReporterId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getReporterId()));
            task.setReporter(reporter);
       }

       if (dto.getLabelIds() != null) {
            Set<Label> labels = dto.getLabelIds().stream()
                    .map(labelId -> labelRepository.findById(labelId)
                            .orElseThrow(() -> new ResourceNotFoundException("Label not found with id " + labelId)))
                    .collect(Collectors.toSet());
            task.getLabels().clear();
            task.setLabels(labels);
       }

       Task updatedTask = taskRepository.save(task);
       return taskAdapter.toResponseDto(updatedTask);
    }


    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }

    @Override
    public List<TaskResponseDto> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status).stream()
                .map(taskAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByPriority(TaskPriority priority) {
        return taskRepository.findAllByPriority(priority).stream()
                .map(taskAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByAssignee(Long userId) {
        return taskRepository.findAllByAssignee_UserId(userId).stream()
                .map(taskAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByReporter(Long userId) {
        return taskRepository.findAllByReporter_UserId(userId).stream()
                .map(taskAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksDueBefore(LocalDateTime date) {
        return taskRepository.findAllByDueAtBefore(date).stream()
                .map(taskAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByLabel(Long labelId) {
        return taskRepository.findAllByLabels_LabelId(labelId).stream()
                .map(taskAdapter::toResponseDto)
                .collect(Collectors.toList());
    }
}

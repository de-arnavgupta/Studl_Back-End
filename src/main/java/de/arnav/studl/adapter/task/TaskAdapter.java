package de.arnav.studl.adapter.task;

import de.arnav.studl.adapter.DtoAdapter;
import de.arnav.studl.dto.label.LabelSummaryDto;
import de.arnav.studl.dto.task.TaskCreateDto;
import de.arnav.studl.dto.task.TaskResponseDto;
import de.arnav.studl.dto.task.TaskUpdateDto;
import de.arnav.studl.dto.user.UserSummaryDto;
import de.arnav.studl.model.Task;
import de.arnav.studl.model.User;
import de.arnav.studl.model.Label;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class TaskAdapter implements DtoAdapter<Task, TaskResponseDto, TaskCreateDto, TaskUpdateDto> {

    @Override
    public TaskResponseDto toResponseDto(Task task) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setTaskId(task.getTaskId());
        dto.setTitle(task.getTitle());
        dto.setContent(task.getContent());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueAt(task.getDueAt());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        dto.setStartAt(task.getStartAt());
        dto.setCompletedAt(task.getCompletedAt());

        if (task.getAssignee() != null) {
            dto.setAssignee(mapUserToUserSummary(task.getAssignee()));
        }
        if (task.getReporter() != null) {
            dto.setReporter(mapUserToUserSummary(task.getReporter()));
        }
        if (task.getLabels() != null) {
            dto.setLabels(task.getLabels().stream()
                    .map(this::mapLabelToLabelSummary)
                    .collect(Collectors.toList()));
        } else {
            dto.setLabels(new ArrayList<>());
        }
        return dto;
    }

    @Override
    public Task fromCreateDto(TaskCreateDto createDto) {
        Task task = new Task();
        task.setTitle(createDto.getTitle());
        task.setContent(createDto.getContent());
        task.setStatus(createDto.getStatus());
        task.setPriority(createDto.getPriority());
        task.setDueAt(createDto.getDueAt());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return task;
    }

    @Override
    public Task updateEntityFromUpdateDto(TaskUpdateDto updateDto, Task task) {
        if (updateDto.getTitle() != null) {
            task.setTitle(updateDto.getTitle());
        }
        if (updateDto.getContent() != null) {
            task.setContent(updateDto.getContent());
        }
        if (updateDto.getStatus() != null) {
            task.setStatus(updateDto.getStatus());
        }
        if (updateDto.getPriority() != null) {
            task.setPriority(updateDto.getPriority());
        }
        if (updateDto.getDueAt() != null) {
            task.setDueAt(updateDto.getDueAt());
        }
        task.setUpdatedAt(LocalDateTime.now());
        return task;
    }

    private UserSummaryDto mapUserToUserSummary(User user) {
        UserSummaryDto summary = new UserSummaryDto();
        summary.setUserId(user.getUserId());
        summary.setName(user.getName());
        return summary;
    }

    private LabelSummaryDto mapLabelToLabelSummary(Label label) {
        LabelSummaryDto summary = new LabelSummaryDto();
        summary.setLabelId(label.getLabelId());
        summary.setName(label.getName());
        return summary;
    }
}

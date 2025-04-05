package de.arnav.studl.dto.task;

import de.arnav.studl.dto.label.LabelSummaryDto;
import de.arnav.studl.dto.user.UserSummaryDto;
import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TaskResponseDto {
    private Long taskId;
    private String title;
    private String content;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime startAt;
    private LocalDateTime completedAt;
    private UserSummaryDto assignee;
    private UserSummaryDto reporter;
    private List<LabelSummaryDto> labels;

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public LocalDateTime getDueAt() { return dueAt; }
    public void setDueAt(LocalDateTime dueAt) { this.dueAt = dueAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public LocalDateTime getStartAt() { return startAt; }
    public void setStartAt(LocalDateTime startAt) { this.startAt = startAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public UserSummaryDto getAssignee() { return assignee; }
    public void setAssignee(UserSummaryDto assignee) { this.assignee = assignee; }
    public UserSummaryDto getReporter() { return reporter; }
    public void setReporter(UserSummaryDto reporter) { this.reporter = reporter; }
    public List<LabelSummaryDto> getLabels() { return labels; }
    public void setLabels(List<LabelSummaryDto> labels) { this.labels = labels; }
}

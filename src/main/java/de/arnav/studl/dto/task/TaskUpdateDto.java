package de.arnav.studl.dto.task;

import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TaskUpdateDto {
    private String title;
    private String content;
    private TaskStatus status;
    private TaskPriority priority;
    private Long assigneeId;
    private Long reporterId;
    private LocalDateTime dueAt;
    private List<Long> labelIds;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    public Long getReporterId() { return reporterId; }
    public void setReporterId(Long reporterId) { this.reporterId = reporterId; }
    public LocalDateTime getDueAt() { return dueAt; }
    public void setDueAt(LocalDateTime dueAt) { this.dueAt = dueAt; }
    public List<Long> getLabelIds() { return labelIds; }
    public void setLabelIds(List<Long> labelIds) { this.labelIds = labelIds; }
}

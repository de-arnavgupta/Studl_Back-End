package de.arnav.studl.controller;

import de.arnav.studl.dto.task.TaskCreateDto;
import de.arnav.studl.dto.task.TaskResponseDto;
import de.arnav.studl.dto.task.TaskUpdateDto;
import de.arnav.studl.facade.template.TaskFacade;
import de.arnav.studl.model.enums.TaskPriority;
import de.arnav.studl.model.enums.TaskStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskFacade taskFacade;

    public TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskCreateDto dto) {
        TaskResponseDto response = taskFacade.createTask(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto response = taskFacade.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto dto) {
        TaskResponseDto response = taskFacade.updateTask(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskFacade.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskResponseDto> responses = taskFacade.getTasksByStatus(status);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByPriority(@PathVariable TaskPriority priority) {
        List<TaskResponseDto> responses = taskFacade.getTasksByPriority(priority);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignee/{userId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByAssignee(@PathVariable Long userId) {
        List<TaskResponseDto> responses = taskFacade.getTasksByAssignee(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/reporter/{userId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByReporter(@PathVariable Long userId) {
        List<TaskResponseDto> responses = taskFacade.getTasksByReporter(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/due-before")
    public ResponseEntity<List<TaskResponseDto>> getTasksDueBefore(@RequestParam("date") String dateStr) {
        LocalDateTime date = LocalDateTime.parse(dateStr); // Adjust parsing & error handling as needed
        List<TaskResponseDto> responses = taskFacade.getTasksDueBefore(date);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/label/{labelId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByLabel(@PathVariable Long labelId) {
        List<TaskResponseDto> responses = taskFacade.getTasksByLabel(labelId);
        return ResponseEntity.ok(responses);
    }
}
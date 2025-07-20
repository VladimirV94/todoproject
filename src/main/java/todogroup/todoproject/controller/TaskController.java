package todogroup.todoproject.controller;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import todogroup.todoproject.dto.TaskMapper;
import todogroup.todoproject.dto.TaskRequestDTO;
import todogroup.todoproject.dto.TaskResponseDTO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.service.TaskService;
import todogroup.todoproject.service.sorting.SortBy;
import todogroup.todoproject.service.sorting.SortDirection;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/todoTasks")
@AllArgsConstructor
@Validated
public class TaskController {

	private TaskService taskService;
	private TaskMapper taskMapper;

	@GetMapping
	public List<TaskResponseDTO> findAllTasks() {
		return taskService.findAllTasks();
	}

	@GetMapping("/{id}")
	public Optional<TaskResponseDTO> findTaskById(@PathVariable @PositiveOrZero Integer id) {
		return taskService.findTaskById(id);
	}

	@PutMapping
	public Optional<TaskResponseDTO> updateTask(@RequestBody @Validated TaskRequestDTO taskDTO) {
		return taskService.updateTask(taskMapper.fromDTO(taskDTO));
	}

	@PostMapping
	public Optional<TaskResponseDTO> saveTask(@RequestBody @Validated TaskRequestDTO taskDTO) {
		return taskService.saveTask(taskMapper.fromDTO(taskDTO));
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Integer id) {
		taskService.deleteTaskById(id);
	}

	@GetMapping("filteredTasks/{status}")
	public List<TaskResponseDTO> findTasksByStatus(@PathVariable TaskStatus status) {
		return taskService.findTasksByStatus(status);
	}

	@GetMapping("sortedTasks")
	public List<TaskResponseDTO> sortAllTasks(@RequestParam SortBy sortBy, @RequestParam SortDirection sortDirection) {
		return switch (sortBy) {
			case STATUS -> taskService.sortAllTasksByStatus(sortDirection);
			case DEADLINE -> taskService.sortAllTasksByDeadline(sortDirection);
		};
	}
}

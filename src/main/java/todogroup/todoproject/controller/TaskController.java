package todogroup.todoproject.controller;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.entity.dto.TaskContainerDTO;
import todogroup.todoproject.service.TaskService;
import todogroup.todoproject.service.sorting.SortBy;
import todogroup.todoproject.service.sorting.SortDirection;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/taskTasks")
@AllArgsConstructor
public class TaskController {

	private TaskService taskService;

	@GetMapping
	public TaskContainerDTO findAllTasks() {
		return taskService.findAllTasks();
	}

	@GetMapping("/{id}")
	public TaskContainerDTO findTaskById(@PathVariable String id) {
		return isNumeric(id) ?
				taskService.findTaskById(Integer.parseInt(id)) :
				TaskContainerDTO.empty();
	}

	@PutMapping("update_task")
	public TaskContainerDTO updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}

	@PostMapping("save_task")
	public TaskContainerDTO saveTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}

	@PostMapping("delete_task/{id}")
	public void deleteTask(@PathVariable String id) {
		if(isNumeric(id))
			taskService.deleteTaskById(Integer.parseInt(id));
	}

	@GetMapping("filteredTasks/{status}")
	public TaskContainerDTO findTasksByStatus(@PathVariable String status) {
		return isStringValid(status) ?
				taskService.findTasksByStatus(TaskStatus.valueOf(status.toUpperCase())) :
				taskService.findAllTasks();
	}

	@GetMapping("sortedTasks")
	public TaskContainerDTO sortAllTasks(@RequestParam String sortingBy, @RequestParam String sortDirection) {
		if(!isStringValid(sortingBy) || !isStringValid(sortDirection)) {
			return taskService.findAllTasks();
		}

		if (SortBy.STATUS.equals(SortBy.valueOf(sortingBy.toUpperCase()))) {
			return taskService.sortAllTasksByStatus(SortDirection.valueOf(sortDirection.toUpperCase()));
		} else if (SortBy.DEADLINE.equals(SortBy.valueOf(sortingBy.toUpperCase()))) {
			return taskService.sortAllTasksByDeadline(SortDirection.valueOf(sortDirection.toUpperCase()));
		}
		return taskService.findAllTasks();
	}

	public boolean isNumeric(String strNum) {
		return isStringValid(strNum) && Pattern.compile("\\d+").matcher(strNum).matches();
	}

	private boolean isStringValid(String string) {
		return string != null && !string.isBlank();
	}
}

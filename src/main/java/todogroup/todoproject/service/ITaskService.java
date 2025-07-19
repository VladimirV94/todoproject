package todogroup.todoproject.service;

import todogroup.todoproject.dto.TaskRequestDTO;
import todogroup.todoproject.dto.TaskResponseDTO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.service.sorting.SortDirection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITaskService {

	List<TaskResponseDTO> findAllTasks();

	Optional<TaskResponseDTO> findTaskById(int id);

	Optional<TaskResponseDTO> updateTask(Task task);

	Optional<TaskResponseDTO> saveTask(Task task);

	void deleteTaskById(int id);

	List<TaskResponseDTO> findTasksByStatus(TaskStatus status);

//	List<TaskResponseDTO> sortTasksByStatus(Collection<Integer> tasksId, SortDirection SortDirection);
//
//	List<TaskResponseDTO> sortTasksByDeadline(Collection<Integer> tasksId, SortDirection SortDirection);

	List<TaskResponseDTO> sortAllTasksByStatus(SortDirection sortDirection);

	List<TaskResponseDTO> sortAllTasksByDeadline(SortDirection sortDirection);
}

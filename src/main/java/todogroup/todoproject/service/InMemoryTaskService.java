package todogroup.todoproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todogroup.todoproject.dao.InMemoryTaskDAO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.entity.dto.TaskContainerDTO;
import todogroup.todoproject.service.sorting.SortDirection;
import todogroup.todoproject.service.sorting.SortingService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InMemoryTaskService implements ITaskService {

	private InMemoryTaskDAO repository;

	public TaskContainerDTO findAllTasks() {
		return new TaskContainerDTO(repository.findAllTasks());
	}

	@Override
	public TaskContainerDTO findTaskById(int id) {
		return new TaskContainerDTO(repository.findTaskById(id));
	}

	@Override
	public TaskContainerDTO updateTask(Task task) {
		return new TaskContainerDTO(repository.updateTask(task));
	}

	@Override
	public TaskContainerDTO saveTask(Task task) {
		return new TaskContainerDTO(repository.saveTask(task));
	}

	@Override
	public void deleteTaskById(int id) {
		repository.deleteTaskById(id);
	}

	@Override
	public TaskContainerDTO findTasksByStatus(TaskStatus status) {
		return new TaskContainerDTO(repository.findAllTasks().stream()
			.filter(task -> task.getStatus() == status)
			.toList());
	}

	@Override
	public TaskContainerDTO sortTasksByStatus(Collection<Integer> tasksId, SortDirection sortDirection) {
		var tasks = findTaskByIds(tasksId);
		return new TaskContainerDTO(SortingService.sortTasksByStatus(tasks, sortDirection));
	}

	@Override
	public TaskContainerDTO sortTasksByDeadline(Collection<Integer> tasksId, SortDirection sortDirection) {
		var tasks = findTaskByIds(tasksId);
		return new TaskContainerDTO(SortingService.sortTasksByDeadline(tasks, sortDirection));
	}

	@Override
	public TaskContainerDTO sortAllTasksByStatus(SortDirection sortDirection) {
		return new TaskContainerDTO(SortingService.sortTasksByStatus(repository.findAllTasks(), sortDirection));
	}

	@Override
	public TaskContainerDTO sortAllTasksByDeadline(SortDirection sortDirection) {
		return new TaskContainerDTO(SortingService.sortTasksByDeadline(repository.findAllTasks(), sortDirection));
	}

	private List<Task> findTaskByIds(Collection<Integer> tasksId) {
		return tasksId.stream().collect(Collectors.toSet()) // убираем дубликаты
			.stream()
			.map(taskId -> repository.findTaskById(taskId))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList()); // обратно возвращаем лист для возможности сортировки
	}
}

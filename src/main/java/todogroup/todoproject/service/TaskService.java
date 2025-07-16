package todogroup.todoproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todogroup.todoproject.dao.TaskRepository;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.entity.dto.TaskContainerDTO;
import todogroup.todoproject.service.sorting.SortDirection;
import todogroup.todoproject.service.sorting.SortingService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {

	private TaskRepository repository;

	public TaskContainerDTO findAllTasks() {
		return new TaskContainerDTO(repository.findAll());
	}

	@Override
	public TaskContainerDTO findTaskById(int id) {
		return new TaskContainerDTO(repository.findById(id));
	}

	@Override
	public TaskContainerDTO updateTask(Task task) {
		return new TaskContainerDTO(repository.save(task));
	}

	@Override
	public TaskContainerDTO saveTask(Task task) {
		return new TaskContainerDTO(repository.save(task));
	}

	@Override
	public void deleteTaskById(int id) {
		repository.deleteById(id);
	}

	@Override
	public TaskContainerDTO findTasksByStatus(TaskStatus status) {
		return new TaskContainerDTO(repository.findAll().stream()
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
		return new TaskContainerDTO(SortingService.sortTasksByStatus(repository.findAll(), sortDirection));
	}

	@Override
	public TaskContainerDTO sortAllTasksByDeadline(SortDirection sortDirection) {
		return new TaskContainerDTO(SortingService.sortTasksByDeadline(repository.findAll(), sortDirection));
	}

	private List<Task> findTaskByIds(Collection<Integer> tasksId) {
		return new HashSet<>(tasksId) // убираем дубликаты
				.stream()
				.map(taskId -> repository.findById(taskId))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());// обратно возвращаем модифицируемый лист для возможности сортировки
	}
}
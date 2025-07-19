package todogroup.todoproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todogroup.todoproject.dao.InMemoryTaskDAO;
import todogroup.todoproject.dto.TaskMapper;
import todogroup.todoproject.dto.TaskRequestDTO;
import todogroup.todoproject.dto.TaskResponseDTO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
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

	public List<TaskResponseDTO> findAllTasks() {
		return TaskMapper.INSTANCE.toDTO(repository.findAllTasks());
	}

	@Override
	public Optional<TaskResponseDTO> findTaskById(int id) {
		return TaskMapper.INSTANCE.toDTO(repository.findTaskById(id));
	}

	@Override
	public Optional<TaskResponseDTO> updateTask(Task task) {
		return TaskMapper.INSTANCE.toDTO(repository.saveTask(task));
	}

	@Override
	public Optional<TaskResponseDTO> saveTask(Task task) {
		return TaskMapper.INSTANCE.toDTO(repository.saveTask(task));
	}

	@Override
	public void deleteTaskById(int id) {
		repository.deleteTaskById(id);
	}

	@Override
	public List<TaskResponseDTO> findTasksByStatus(TaskStatus status) {
		return TaskMapper.INSTANCE.toDTO(repository.findAllTasks().stream()
			.filter(task -> task.getStatus() == status)
			.toList());
	}

//	@Override
//	public List<TaskResponseDTO> sortTasksByStatus(Collection<Integer> tasksId, SortDirection sortDirection) {
//		var tasks = findTaskByIds(tasksId);
//		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByStatus(tasks, sortDirection));
//	}
//
//	@Override
//	public List<TaskResponseDTO> sortTasksByDeadline(Collection<Integer> tasksId, SortDirection sortDirection) {
//		var tasks = findTaskByIds(tasksId);
//		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByDeadline(tasks, sortDirection));
//	}

	@Override
	public List<TaskResponseDTO> sortAllTasksByStatus(SortDirection sortDirection) {
		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByStatus(repository.findAllTasks(), sortDirection));
	}

	@Override
	public List<TaskResponseDTO> sortAllTasksByDeadline(SortDirection sortDirection) {
		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByDeadline(repository.findAllTasks(), sortDirection));
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

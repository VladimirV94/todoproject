package todogroup.todoproject.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todogroup.todoproject.dao.TaskRepository;
import todogroup.todoproject.dto.TaskMapper;
import todogroup.todoproject.dto.TaskRequestDTO;
import todogroup.todoproject.dto.TaskResponseDTO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
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

	public List<TaskResponseDTO> findAllTasks() {
		return  TaskMapper.INSTANCE.toDTO(repository.findAll());
	}

	@Override
	public Optional<TaskResponseDTO> findTaskById(int id) {
		return TaskMapper.INSTANCE.toDTO(repository.findById(id));
	}

	@Override
	@Transactional
	public Optional<TaskResponseDTO> updateTask(Task task) {
		return TaskMapper.INSTANCE.toDTO(repository.save(task));
	}

	@Override
	@Transactional
	public Optional<TaskResponseDTO> saveTask(Task task) {
		return TaskMapper.INSTANCE.toDTO(repository.save(task));
	}

	@Override
	@Transactional
	public void deleteTaskById(int id) {
		repository.deleteById(id);
	}

	@Override
	public List<TaskResponseDTO> findTasksByStatus(TaskStatus status) {
		return TaskMapper.INSTANCE.toDTO(repository.findAll().stream()
				.filter(task -> status == task.getStatus())
				.toList());
	}

//	@Override
//	public List<TaskResponseDTO> sortTasksByStatus(Collection<Integer> tasksId, SortDirection sortDirection) {
//		var tasks = repository.findAllById(tasksId);
//		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByStatus(tasks, sortDirection));
//	}
//
//	@Override
//	public List<TaskResponseDTO> sortTasksByDeadline(Collection<Integer> tasksId, SortDirection sortDirection) {
//		var tasks = repository.findAllById(tasksId);
//		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByDeadline(tasks, sortDirection));
//	}

	@Override
	public List<TaskResponseDTO> sortAllTasksByStatus(SortDirection sortDirection) {
		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByStatus(repository.findAll(), sortDirection));
	}

	@Override
	public List<TaskResponseDTO> sortAllTasksByDeadline(SortDirection sortDirection) {
		return TaskMapper.INSTANCE.toDTO(SortingService.sortTasksByDeadline(repository.findAll(), sortDirection));
	}
}
package todogroup.todoproject.dto;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import todogroup.todoproject.entity.Task;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	/**
	 * Использование только внутри {@link TaskMapper}
	 * @param task
	 * @return
	 */
	TaskResponseDTO taskToTaskDTO(Task task);

	default List<TaskResponseDTO> toDTO(List<Task> tasks) {
		return tasks != null && !tasks.isEmpty() ?
			tasks.stream().map(this::taskToTaskDTO).toList()
			: Collections.emptyList();
	}

	default Optional<TaskResponseDTO> toDTO(Optional<Task> task) {
		return task.isPresent() ?
			Optional.of(taskToTaskDTO(task.get())) :
			Optional.empty();
	}

	default Optional<TaskResponseDTO> toDTO(Task task) {
		return task != null ?
			Optional.of(taskToTaskDTO(task)) :
			Optional.empty();
	}

	/**
	 * Использование только внутри {@link TaskMapper}
	 * @param task
	 * @return
	 */
	Task taskDTOToTask(TaskRequestDTO task);

	default Task fromDTO(TaskRequestDTO task) {
		return taskDTOToTask(task);
	}
}

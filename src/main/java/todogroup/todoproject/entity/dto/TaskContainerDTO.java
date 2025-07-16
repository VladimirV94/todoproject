package todogroup.todoproject.entity.dto;

import lombok.Getter;
import todogroup.todoproject.entity.Task;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class TaskContainerDTO {

	List<TaskDTO> tasks;

	public TaskContainerDTO(List<Task> tasks) {
		this.tasks = tasks.stream().map(TaskDTO::toDTO).toList();
	}

	public TaskContainerDTO(Optional<Task> task) {
		this.tasks = task.isPresent() ?
			Collections.singletonList(TaskDTO.toDTO(task.get())) :
			Collections.emptyList();
	}

	public TaskContainerDTO(Task task) {
		this.tasks = task != null ?
			Collections.singletonList(TaskDTO.toDTO(task)) :
			Collections.emptyList();
	}

	public static TaskContainerDTO empty() {
		return new TaskContainerDTO(Collections.emptyList());
	}
}

package todogroup.todoproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class TaskDTO {

	private String name;
	private String description;
	private TaskStatus status;
	private LocalDate deadline;

	public static TaskDTO toDTO(Task task){

		return new TaskDTO(task.getName(), task.getDescription(), task.getStatus(), task.getDeadline());
	}
}

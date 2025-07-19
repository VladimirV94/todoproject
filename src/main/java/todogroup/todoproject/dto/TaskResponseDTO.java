package todogroup.todoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
	private String name;
	private String description;
	private TaskStatus status;
	private LocalDate deadline;
}

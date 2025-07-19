package todogroup.todoproject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import todogroup.todoproject.entity.TaskStatus;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class TaskRequestDTO {
	@NotNull
	@PositiveOrZero
	private int id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	private TaskStatus status;
	@NotNull
	private LocalDate deadline;
}

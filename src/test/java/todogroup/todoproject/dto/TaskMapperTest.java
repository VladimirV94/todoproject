package todogroup.todoproject.dto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import todogroup.todoproject.controller.TaskController;
import todogroup.todoproject.entity.Task;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {


	@Test
	void toDTO_fromListToList() {
		final Task task = Mockito.mock(Task.class);

		List<TaskResponseDTO> result = TaskMapper.INSTANCE.toDTO(List.of(task));

		assertNotNull(result);
	}

	@Test
	void toDTO_fromOptionalToOptional() {
		final Task task = Mockito.mock(Task.class);

		Optional<TaskResponseDTO> result = TaskMapper.INSTANCE.toDTO(Optional.of(task));

		assertNotNull(result);
	}

	@Test
	void toDTO_fromTaskToOptional() {
		final Task task = Mockito.mock(Task.class);

		Optional<TaskResponseDTO> result = TaskMapper.INSTANCE.toDTO(task);

		assertNotNull(result);
	}

	@Test
	void fromDTO() {
		final TaskRequestDTO responseTaskDTO = Mockito.mock(TaskRequestDTO.class);

		Task result = TaskMapper.INSTANCE.fromDTO(responseTaskDTO);

		assertNotNull(result);
	}

}
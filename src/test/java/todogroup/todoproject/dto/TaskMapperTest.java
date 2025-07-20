package todogroup.todoproject.dto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todogroup.todoproject.controller.TaskController;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.service.sorting.SortingService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

	@Autowired
	TaskMapper taskMapper;

	@Test
	void toDTO_fromListToList() {
		final Task task = Mockito.mock(Task.class);

		List<TaskResponseDTO> result = taskMapper.toDTO(List.of(task));

		assertNotNull(result);
	}

	@Test
	void toDTO_fromOptionalToOptional() {
		final Task task = Mockito.mock(Task.class);

		Optional<TaskResponseDTO> result = taskMapper.toDTO(Optional.of(task));

		assertNotNull(result);
	}

	@Test
	void toDTO_fromTaskToOptional() {
		final Task task = Mockito.mock(Task.class);

		Optional<TaskResponseDTO> result = taskMapper.toDTO(task);

		assertNotNull(result);
	}

	@Test
	void fromDTO() {
		final TaskRequestDTO responseTaskDTO = Mockito.mock(TaskRequestDTO.class);

		Task result = taskMapper.fromDTO(responseTaskDTO);

		assertNotNull(result);
	}

}
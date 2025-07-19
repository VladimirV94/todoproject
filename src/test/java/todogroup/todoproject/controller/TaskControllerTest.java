package todogroup.todoproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import todogroup.todoproject.dto.TaskMapper;
import todogroup.todoproject.dto.TaskRequestDTO;
import todogroup.todoproject.dto.TaskResponseDTO;
import todogroup.todoproject.entity.Task;
import todogroup.todoproject.entity.TaskStatus;
import todogroup.todoproject.service.TaskService;
import todogroup.todoproject.service.sorting.SortBy;
import todogroup.todoproject.service.sorting.SortDirection;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

	private final static int ID = 1;
	private final static String SOMESTRING = "string";

	@Mock
	private TaskService taskService;

	@InjectMocks
	private TaskController taskController;

	@Test
	void findAllTasks_shouldReturnTaskResponseDTOs() {
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskService.findAllTasks()).thenReturn(List.of(responseTaskDTO));

		List<TaskResponseDTO> result = taskController.findAllTasks();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(responseTaskDTO, result.get(0));
		Mockito.verify(taskService).findAllTasks();
	}

	@Test
	void findTaskById_shouldReturnOptionalTaskResponseDTO() {
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskService.findTaskById(ID)).thenReturn(Optional.of(responseTaskDTO));

		Optional<TaskResponseDTO> result = taskController.findTaskById(ID);

		assertNotNull(result);
		assertEquals(responseTaskDTO, result.get());
	}

	@Test
	void updateTask_shouldReturnOptionalTaskResponseDTO() {
		final TaskRequestDTO requestDTO = Mockito.mock(TaskRequestDTO.class);
		final TaskResponseDTO responseDTO = Mockito.mock(TaskResponseDTO.class);

		Mockito.when(taskService.updateTask(Mockito.any(Task.class))).thenReturn(Optional.of(responseDTO));

		Optional<TaskResponseDTO> result = taskController.updateTask(requestDTO);

		// тут true
		assertTrue(result.isPresent());
		assertEquals(responseDTO, result.get());
		Mockito.verify(taskService).updateTask(Mockito.any(Task.class));
	}

	@Test
	void saveTask_shouldReturnOptionalTaskResponseDTO() {
		final TaskRequestDTO requestDTO = Mockito.mock(TaskRequestDTO.class);
		final TaskResponseDTO responseDTO = Mockito.mock(TaskResponseDTO.class);

		Mockito.when(taskService.updateTask(Mockito.any(Task.class))).thenReturn(Optional.of(responseDTO));

		Optional<TaskResponseDTO> result = taskController.saveTask(requestDTO);

		// Я не понимаю, почему тут false, но в идентичном тесте выше true, хотя он юзает тот же метод save в репозитории
		assertTrue(result.isPresent());
		assertEquals(responseDTO, result.get());
		Mockito.verify(taskService).updateTask(Mockito.any(Task.class));
	}

	@Test
	void deleteTask_shouldCallService() {
		taskController.deleteTask(ID);

		Mockito.verify(taskService).deleteTaskById(ID);
	}

	@Test
	void findTasksByStatus_shouldReturnTaskResponseDTOs() {
		final TaskResponseDTO responseTaskDTO = Mockito.mock(TaskResponseDTO.class);
		Mockito.when(taskService.findTasksByStatus(TaskStatus.DONE)).thenReturn(List.of(responseTaskDTO));

		List<TaskResponseDTO> result = taskController.findTasksByStatus(TaskStatus.DONE);

		assertNotNull(result);
		assertEquals(responseTaskDTO, result.get(0));
		Mockito.verify(taskService).findTasksByStatus(TaskStatus.DONE);
	}

	@Test
	void sortAllTasks_shouldSortByDeadLineDirect() {
		final TaskResponseDTO responseTaskDTOByDeadline1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTOByDeadline2 = Mockito.mock(TaskResponseDTO.class);
		List<TaskResponseDTO> sourceByDeadlineDirect = List.of(responseTaskDTOByDeadline1, responseTaskDTOByDeadline2);
		Mockito.when(taskService.sortAllTasksByDeadline(SortDirection.DIRECT)).thenReturn(sourceByDeadlineDirect);

		List<TaskResponseDTO> resultByDeadlineDirect = taskController.sortAllTasks(SortBy.DEADLINE, SortDirection.DIRECT);

		assertNotNull(resultByDeadlineDirect);
		assertEquals(sourceByDeadlineDirect.size(), resultByDeadlineDirect.size());
		assertEquals(sourceByDeadlineDirect.get(0), resultByDeadlineDirect.get(0));
		assertEquals(sourceByDeadlineDirect.get(1), resultByDeadlineDirect.get(1));
	}

	@Test
	void sortAllTasks_shouldSortByDeadLineReverse() {
		final TaskResponseDTO responseTaskDTOByDeadline1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTOByDeadline2 = Mockito.mock(TaskResponseDTO.class);
		List<TaskResponseDTO> sourceByDeadlineReverse = List.of(responseTaskDTOByDeadline2, responseTaskDTOByDeadline1);
		Mockito.when(taskService.sortAllTasksByDeadline(SortDirection.REVERSE)).thenReturn(sourceByDeadlineReverse);

		List<TaskResponseDTO> resultByDeadlineReverse = taskController.sortAllTasks(SortBy.DEADLINE, SortDirection.REVERSE);

		assertNotNull(resultByDeadlineReverse);
		assertEquals(sourceByDeadlineReverse.size(), resultByDeadlineReverse.size());
		assertEquals(sourceByDeadlineReverse.get(0), resultByDeadlineReverse.get(0));
		assertEquals(sourceByDeadlineReverse.get(1), resultByDeadlineReverse.get(1));
	}

	@Test
	void sortAllTasks_shouldSortByStatusDirect() {
		final TaskResponseDTO responseTaskDTOByStatus1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTOByStatus2 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTOByStatus3 = Mockito.mock(TaskResponseDTO.class);
		List<TaskResponseDTO> sourceByStatusDirect = List.of(responseTaskDTOByStatus1, responseTaskDTOByStatus2, responseTaskDTOByStatus3);
		Mockito.when(taskService.sortAllTasksByStatus(SortDirection.DIRECT)).thenReturn(sourceByStatusDirect);

		List<TaskResponseDTO> resultByStatusDirect = taskController.sortAllTasks(SortBy.STATUS, SortDirection.DIRECT);

		assertNotNull(resultByStatusDirect);
		assertEquals(sourceByStatusDirect.size(), resultByStatusDirect.size());
		assertEquals(sourceByStatusDirect.get(0), resultByStatusDirect.get(0));
		assertEquals(sourceByStatusDirect.get(1), resultByStatusDirect.get(1));
		assertEquals(sourceByStatusDirect.get(2), resultByStatusDirect.get(2));
	}

	@Test
	void sortAllTasks_shouldSortByStatusReverse() {
		final TaskResponseDTO responseTaskDTOByStatus1 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTOByStatus2 = Mockito.mock(TaskResponseDTO.class);
		final TaskResponseDTO responseTaskDTOByStatus3 = Mockito.mock(TaskResponseDTO.class);
		List<TaskResponseDTO> sourceByStatusReverse = List.of(responseTaskDTOByStatus3, responseTaskDTOByStatus2, responseTaskDTOByStatus1);
		Mockito.when(taskService.sortAllTasksByStatus(SortDirection.REVERSE)).thenReturn(sourceByStatusReverse);

		List<TaskResponseDTO> resultByStatusReverse = taskController.sortAllTasks(SortBy.STATUS, SortDirection.REVERSE);

		assertNotNull(resultByStatusReverse);
		assertEquals(sourceByStatusReverse.size(), resultByStatusReverse.size());
		assertEquals(sourceByStatusReverse.get(0), resultByStatusReverse.get(0));
		assertEquals(sourceByStatusReverse.get(1), resultByStatusReverse.get(1));
		assertEquals(sourceByStatusReverse.get(2), resultByStatusReverse.get(2));
	}
}